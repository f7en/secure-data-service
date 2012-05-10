package org.slc.sli.ingestion.xml.idref;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import org.slc.sli.ingestion.FileProcessStatus;
import org.slc.sli.ingestion.handler.AbstractIngestionHandler;
import org.slc.sli.ingestion.landingzone.IngestionFileEntry;
import org.slc.sli.ingestion.referenceresolution.ReferenceResolutionStrategy;
import org.slc.sli.ingestion.util.FileUtils;
import org.slc.sli.ingestion.validation.ErrorReport;

/**
 * @author okrook
 *
 */
public class IdRefResolutionHandler extends AbstractIngestionHandler<IngestionFileEntry, IngestionFileEntry> {
    public static final Logger LOG = LoggerFactory.getLogger(IdRefResolutionHandler.class);
    private ErrorReport errorReport;

    private static final QName ID_ATTR = new QName("id");
    private static final QName REF_ATTR = new QName("ref");
    private static final QName REF_RESOLVED_ATTR = new QName("refResolved");

    private static final String NEW_LINE = System.getProperty("line.separator");

    private static final XMLInputFactory INPUT_FACTORY = XMLInputFactory.newInstance();
    private static final XMLOutputFactory OUTPUT_FACTORY = XMLOutputFactory.newInstance();
    private static final XMLEventFactory EVENT_FACTORY = XMLEventFactory.newInstance();

    private Map<String, ReferenceResolutionStrategy> supportedResolvers;

    @Override
    protected IngestionFileEntry doHandling(IngestionFileEntry fileEntry, ErrorReport errorReport, FileProcessStatus fileProcessStatus) {

        this.errorReport = errorReport;

        File file = fileEntry.getFile();

        file = process(file);

        fileEntry.setFile(file);

        return fileEntry;
    }

    protected File process(File xml) {
        StopWatch sw = new StopWatch("Processing " + xml.getName());

        sw.start("Find IDRefs to resolve");
        Set<String> idRefsToResolve = this.findIDRefsToResolve(xml);
        sw.stop();

        if (idRefsToResolve.isEmpty()) {
            return xml;
        }

        Map<String, File> refContent = null;
        File semiResolvedXml = null;

        try {
            sw.start("Find matching entities");
            refContent = this.findMatchingEntities(xml, idRefsToResolve);
            sw.stop();

            sw.start("Resolve IDRefs");
            semiResolvedXml = this.resolveIdRefs(xml, refContent);
            sw.stop();
        } finally {
            for (File snippet : refContent.values()) {
                org.apache.commons.io.FileUtils.deleteQuietly(snippet);
            }
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug(sw.prettyPrint());
        }

        if (semiResolvedXml == null) {
            return xml;
        } else {
            FileUtils.renameFile(semiResolvedXml, xml);
            return process(xml);
        }
    }

    protected Set<String> findIDRefsToResolve(final File xml) {
        final Set<String> idRefs = new HashSet<String>();

        XmlEventVisitor collectIdRefsToResolve = new XmlEventVisitor() {

            @Override
            public boolean isSupported(XMLEvent xmlEvent) {
                if (xmlEvent.isStartElement()) {
                    StartElement start = xmlEvent.asStartElement();

                    return start.getAttributeByName(REF_RESOLVED_ATTR) == null
                            && start.getAttributeByName(REF_ATTR) != null;
                }

                return false;
            }

            @Override
            public void visit(XMLEvent xmlEvent, XMLEventReader eventReader) {
                StartElement start = xmlEvent.asStartElement();

                Attribute ref = start.getAttributeByName(REF_ATTR);

                idRefs.add(ref.getValue());
            }

            @Override
            public boolean canAcceptMore() {
                return true;
            }

        };

        browse(xml, collectIdRefsToResolve);

        return idRefs;
    }

    protected Map<String, File> findMatchingEntities(final File xml, final Set<String> ids) {
        final Map<String, File> refContent = new HashMap<String, File>();

        for (String id : ids) {
            refContent.put(id, null);
        }

        XmlEventVisitor collectRefContent = new XmlEventVisitor() {
            private int idsToProcess = ids.size();

            @Override
            public boolean isSupported(XMLEvent xmlEvent) {
                if (xmlEvent.isStartElement()) {
                    StartElement start = xmlEvent.asStartElement();

                    Attribute id = start.getAttributeByName(ID_ATTR);

                    return id != null && refContent.containsKey(id.getValue()) && refContent.get(id.getValue()) == null;
                }

                return false;
            }

            @Override
            public void visit(XMLEvent xmlEvent, XMLEventReader eventReader) throws XMLStreamException {
                StartElement start = xmlEvent.asStartElement();

                Attribute id = start.getAttributeByName(ID_ATTR);

                File content = getContent(xml, xmlEvent, eventReader);
                refContent.put(id.getValue(), content);

                idsToProcess--;
            }

            @Override
            public boolean canAcceptMore() {
                return idsToProcess > 0;
            }

        };

        browse(xml, collectRefContent);

        return refContent;
    }

    protected File resolveIdRefs(final File xml, final Map<String, File> refContent) {
        File newXml = null;

        BufferedOutputStream out = null;
        XMLEventWriter writer = null;

        try {
            newXml = File.createTempFile("tmp", ".xml", xml.getParentFile());

            out = new BufferedOutputStream(new FileOutputStream(newXml));

            writer = OUTPUT_FACTORY.createXMLEventWriter(out);
            final XMLEventWriter wr = writer;

            XmlEventVisitor replaceRefContent = new XmlEventVisitor() {
                Stack<StartElement> parents = new Stack<StartElement>();

                @Override
                public boolean isSupported(XMLEvent xmlEvent) {
                    return true;
                }

                @Override
                public void visit(XMLEvent xmlEvent, XMLEventReader eventReader) throws XMLStreamException {
                    File contentToAdd = null;

                    if (xmlEvent.isStartElement()) {
                        StartElement start = xmlEvent.asStartElement();
                        parents.push(start);

                        Attribute refResolved = start.getAttributeByName(REF_RESOLVED_ATTR);

                        if (refResolved == null) {
                            Attribute id = start.getAttributeByName(ID_ATTR);
                            Attribute ref = start.getAttributeByName(REF_ATTR);

                            if (ref != null && refContent.containsKey(ref.getValue())) {
                                @SuppressWarnings("unchecked")
                                Iterator<Attribute> attrs = start.getAttributes();
                                ArrayList<Attribute> newAttrs = new ArrayList<Attribute>();

                                while (attrs.hasNext()) {
                                    newAttrs.add(attrs.next());
                                }

                                if (id != null && id.getValue().equals(ref.getValue())) {
                                    newAttrs.add(EVENT_FACTORY.createAttribute(REF_RESOLVED_ATTR, "false"));
                                } else {
                                    contentToAdd = refContent.get(ref.getValue());

                                    if (contentToAdd != null) {
                                        String currentXPath = this.getCurrentXPath();
                                        ReferenceResolutionStrategy rrs = supportedResolvers.get(currentXPath);

                                        if (rrs != null) {
                                            File resolvedContent = rrs.resolve(currentXPath, contentToAdd);

                                            if (resolvedContent != null && !resolvedContent.equals(contentToAdd)) {
                                                FileUtils.renameFile(resolvedContent, contentToAdd);
                                            } else {
                                                contentToAdd = null;
                                                LOG.debug("Reference was not resolved.");
                                            }
                                        } else {
                                            contentToAdd = null;
                                            LOG.debug("Current XPath [{}] is not supported", currentXPath);
                                        }
                                    }

                                    newAttrs.add(EVENT_FACTORY.createAttribute(REF_RESOLVED_ATTR,
                                            (contentToAdd == null) ? "false" : "true"));
                                }

                                xmlEvent = EVENT_FACTORY.createStartElement(start.getName(), newAttrs.iterator(),
                                        start.getNamespaces());
                            }
                        }
                    } else if (xmlEvent.isEndElement()) {
                        parents.pop();
                    }

                    wr.add(xmlEvent);

                    if (xmlEvent.isStartDocument()) {
                        XMLEvent newLine = EVENT_FACTORY.createCharacters(NEW_LINE);
                        wr.add(newLine);
                    }

                    if (contentToAdd != null) {
                        addContent(contentToAdd, wr);
                    }
                }

                @Override
                public boolean canAcceptMore() {
                    return true;
                }

                private String getCurrentXPath() {
                    StringBuffer sb = new StringBuffer();

                    for (StartElement start : this.parents) {
                        sb.append('/').append(start.getName().getLocalPart());
                    }

                    return sb.toString();
                }
            };

            browse(xml, replaceRefContent);

            writer.flush();
        } catch (Exception e) {
            closeResources(writer, out);

            org.apache.commons.io.FileUtils.deleteQuietly(newXml);
            newXml = null;

            LOG.debug("Error resolving references in XML file {}", xml.getName());
            errorReport.error("Error resolving references in XML file " + xml.getName(), IdRefResolutionHandler.class);
        } finally {
            closeResources(writer, out);
        }

        return newXml;
    }

    private void browse(final File xml, XmlEventVisitor browser) {
        BufferedInputStream xmlStream = null;
        XMLEventReader eventReader = null;
        try {
            xmlStream = new BufferedInputStream(new FileInputStream(xml));

            eventReader = INPUT_FACTORY.createXMLEventReader(xmlStream);

            browse(eventReader, browser);

        } catch (Exception e) {
            LOG.debug("Exception happened while processing {}", xml.getName());
            errorReport.error("Error resolving references in XML file " + xml.getName(), IdRefResolutionHandler.class);
        } finally {
            if (eventReader != null) {
                try {
                    eventReader.close();
                } catch (XMLStreamException e) {
                    eventReader = null;
                }
            }

            IOUtils.closeQuietly(xmlStream);
        }
    }

    private void browse(XMLEventReader eventReader, XmlEventVisitor browser) throws XMLStreamException {
        while (eventReader.hasNext() && browser.canAcceptMore()) {
            XMLEvent xmlEvent = eventReader.nextEvent();

            if (browser.isSupported(xmlEvent)) {
                browser.visit(xmlEvent, eventReader);
            }
        }
    }

    private File getContent(File xml, XMLEvent xmlEvent, XMLEventReader eventReader) {
        File snippet = null;

        BufferedOutputStream out = null;
        XMLEventWriter writer = null;

        try {
            snippet = File.createTempFile("snippet", ".xml", xml.getParentFile());

            out = new BufferedOutputStream(new FileOutputStream(snippet));

            writer = OUTPUT_FACTORY.createXMLEventWriter(out);

            final XMLEventWriter wr = writer;

            XmlEventVisitor writeRefContent = new XmlEventVisitor() {
                Stack<StartElement> parents = new Stack<StartElement>();
                Map<String, Integer> parentIds = new HashMap<String, Integer>();

                @Override
                public boolean isSupported(XMLEvent xmlEvent) {
                    return true;
                }

                @Override
                public void visit(XMLEvent xmlEvent, XMLEventReader eventReader) throws XMLStreamException {
                    if (xmlEvent.isStartElement()) {
                        StartElement start = xmlEvent.asStartElement();
                        pushParent(start);

                        Attribute refResolved = start.getAttributeByName(REF_RESOLVED_ATTR);

                        if (refResolved == null) {
                            Attribute ref = start.getAttributeByName(REF_ATTR);

                            // Set "ref" as unresolved if circular reference is detected
                            if (ref != null && isParent(ref.getValue())) {
                                @SuppressWarnings("unchecked")
                                Iterator<Attribute> attrs = start.getAttributes();
                                ArrayList<Attribute> newAttrs = new ArrayList<Attribute>();

                                while (attrs.hasNext()) {
                                    newAttrs.add(attrs.next());
                                }

                                newAttrs.add(EVENT_FACTORY.createAttribute(REF_RESOLVED_ATTR, "false"));

                                xmlEvent = EVENT_FACTORY.createStartElement(start.getName(), newAttrs.iterator(),
                                        start.getNamespaces());
                            }
                        }
                    } else if (xmlEvent.isEndElement()) {
                        popParent();
                    }

                    wr.add(xmlEvent);
                }

                @Override
                public boolean canAcceptMore() {
                    return !parents.isEmpty();
                }

                private void pushParent(StartElement parent) {
                    parents.push(parent);
                    Attribute id = parent.getAttributeByName(ID_ATTR);
                    if (id != null) {
                        Integer count = parentIds.get(id.getValue());
                        if (count == null) {
                            count = 1;
                        } else {
                            count = count++;
                        }

                        parentIds.put(id.getValue(), count);
                    }
                }

                private void popParent() {
                    StartElement parent = parents.pop();
                    Attribute id = parent.getAttributeByName(ID_ATTR);
                    if (id != null) {
                        Integer count = parentIds.get(id.getValue());
                        if (count <= 1) {
                            parentIds.remove(id.getValue());
                        } else {
                            parentIds.put(id.getValue(), count--);
                        }
                    }
                }

                private boolean isParent(String id) {
                    return parentIds.containsKey(id);
                }
            };

            // persist the input event manually
            writeRefContent.visit(xmlEvent, eventReader);

            browse(eventReader, writeRefContent);

            writer.flush();
        } catch (Exception e) {
            closeResources(writer, out);
            org.apache.commons.io.FileUtils.deleteQuietly(snippet);
            snippet = null;
        } finally {
            closeResources(writer, out);
        }

        return snippet;
    }

    private void addContent(File contentToAdd, final XMLEventWriter xmlEventWriter) {
        XmlEventVisitor addToXml = new XmlEventVisitor() {

            @Override
            public void visit(XMLEvent xmlEvent, XMLEventReader eventReader) throws XMLStreamException {
                if (xmlEvent.isStartElement()) {
                    StartElement start = xmlEvent.asStartElement();

                    @SuppressWarnings("unchecked")
                    Iterator<Attribute> attrs = start.getAttributes();
                    ArrayList<Attribute> newAttrs = new ArrayList<Attribute>();

                    // Strip out "id" attribute if found, so it does not interfere with findMatchingEntities logic
                    while (attrs.hasNext()) {
                        Attribute attribute = attrs.next();
                        if (!ID_ATTR.getLocalPart().equals(attribute.getName().getLocalPart())) {
                            newAttrs.add(attribute);
                        }
                    }

                    xmlEvent = EVENT_FACTORY.createStartElement(start.getName(), newAttrs.iterator(), start.getNamespaces());
                }

                xmlEventWriter.add(xmlEvent);
            }

            @Override
            public boolean isSupported(XMLEvent xmlEvent) {
                return !(xmlEvent.isStartDocument() || xmlEvent.isEndDocument());
            }

            @Override
            public boolean canAcceptMore() {
                return true;
            }
        };

        browse(contentToAdd, addToXml);
    }

    private void closeResources(XMLEventWriter writer, BufferedOutputStream out) {

        if (writer != null) {
            try {
                writer.close();
            } catch (XMLStreamException e) {
                writer = null;
            }
        }
        IOUtils.closeQuietly(out);

    }

    public Map<String, ReferenceResolutionStrategy> getSupportedResolvers() {
        return supportedResolvers;
    }

    public void setSupportedResolvers(Map<String, ReferenceResolutionStrategy> supportedResolvers) {
        this.supportedResolvers = supportedResolvers;
    }
}
