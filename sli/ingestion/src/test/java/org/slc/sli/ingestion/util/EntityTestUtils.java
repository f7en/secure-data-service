package org.slc.sli.ingestion.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
import org.milyn.Smooks;
import org.xml.sax.SAXException;

import org.slc.sli.domain.Entity;
import org.slc.sli.ingestion.NeutralRecordFileReader;
import org.slc.sli.ingestion.NeutralRecordFileWriter;
import org.slc.sli.ingestion.smooks.SmooksEdFiVisitor;
import org.slc.sli.validation.EntityValidationException;
import org.slc.sli.validation.EntityValidator;
import org.slc.sli.validation.ValidationError;

/**
 *
 * @author ablum
 *
 */
public class EntityTestUtils {

    public static NeutralRecordFileReader getNeutralRecords(InputStream dataSource, String smooksConfig,
            String targetSelector) throws IOException, SAXException {
        File outputFile = File.createTempFile("test", ".dat");
        outputFile.deleteOnExit();
        NeutralRecordFileWriter nrfWriter = new NeutralRecordFileWriter(outputFile);

        Smooks smooks = new Smooks(smooksConfig);

        smooks.addVisitor(SmooksEdFiVisitor.createInstance("record", nrfWriter), targetSelector);

        try {
            smooks.filterSource(new StreamSource(dataSource));
        } finally {
            nrfWriter.close();
        }

        return new NeutralRecordFileReader(new File(outputFile.getAbsolutePath()));
    }

    public static void mapValidation(Map<String, Object> obj, String schemaName, EntityValidator validator) {

        Entity e = mock(Entity.class);
        when(e.getBody()).thenReturn(obj);
        when(e.getType()).thenReturn(schemaName);

        try {
            Assert.assertTrue(validator.validate(e));
        } catch (EntityValidationException ex) {
            for (ValidationError err : ex.getValidationErrors()) {
                System.err.println(err);
            }
            Assert.fail();
        }
    }

    /**
     * Utility to make checking values in a map less verbose.
     *
     * @param map
     *            The map containing the entry we want to check.
     * @param key
     *            The string key for the entry
     * @param expectedValue
     *            The Object value we will assertEquals against
     */
    public static void assertObjectInMapEquals(Map map, String key, Object expectedValue) {
        assertEquals("Object value in map does not match expected.", expectedValue, map.get(key));
    }
}
