package org.slc.sli.api.resources.v1.association;

import org.slc.sli.api.config.ResourceNames;
import org.slc.sli.api.representation.EntityBody;
import org.slc.sli.api.resources.util.ResourceUtil;
import org.slc.sli.api.resources.v1.CrudEndpoint;
import org.slc.sli.api.resources.v1.HypermediaType;
import org.slc.sli.api.resources.v1.ParameterConstants;
import org.slc.sli.api.resources.v1.PathConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Prototype new api end points and versioning
 */
@Path(PathConstants.V1 + "/" + PathConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATIONS)
@Component
@Scope("request")
@Produces({ MediaType.APPLICATION_JSON, HypermediaType.VENDOR_SLC_JSON })
public class StudentDisciplineIncidentAssociationResource {
    /**
     * Logging utility.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDisciplineIncidentAssociationResource.class);

    /*
    * Interface capable of performing CRUD operations.
    */
    private final CrudEndpoint crudDelegate;

    @Autowired
    public StudentDisciplineIncidentAssociationResource(CrudEndpoint crudDelegate) {
        this.crudDelegate = crudDelegate;
        LOGGER.debug("New resource handler created {}", this);
    }

    /**
     * Returns all $$studentDisciplineIncidentAssociations$$ entities for which the logged in User has permission and context.
     *
     * @param offset
     *            starting position in results to return to user
     * @param limit
     *            maximum number of results to return to user (starting from offset)
     * @param headers 
     *            HTTP Request Headers
     * @param uriInfo 
     *            URI information including path and query parameters
     * @return result of CRUD operation
     */
    @Produces({ MediaType.APPLICATION_JSON, HypermediaType.VENDOR_SLC_JSON })
    @GET
    public Response readAll(@QueryParam(ParameterConstants.OFFSET) @DefaultValue(ParameterConstants.DEFAULT_OFFSET) final int offset,
                            @QueryParam(ParameterConstants.LIMIT) @DefaultValue(ParameterConstants.DEFAULT_LIMIT) final int limit,
                            @Context HttpHeaders headers, @Context final UriInfo uriInfo) {
        ResourceUtil.putValue(headers.getRequestHeaders(), ParameterConstants.LIMIT, limit);
        ResourceUtil.putValue(headers.getRequestHeaders(), ParameterConstants.OFFSET, offset);
        return this.crudDelegate.readAll(ResourceNames.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATIONS, headers, uriInfo);
    }

    /**
     * Create a new $$studentDisciplineIncidentAssociations$$ entity.
     *
     * @param newEntityBody 
     *            entity data
     * @param headers
     *            HTTP Request Headers
     * @param uriInfo
     *            URI information including path and query parameters
     * @return result of CRUD operation
     * @response.param {@name Location} {@style header} {@type
     * {http://www.w3.org/2001/XMLSchema}anyURI} {@doc The URI where the created
     * item is accessable.}
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, HypermediaType.VENDOR_SLC_JSON })
    public Response create(final EntityBody newEntityBody,
                           @Context HttpHeaders headers, @Context final UriInfo uriInfo) {
        return this.crudDelegate.create(ResourceNames.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATIONS, newEntityBody, headers, uriInfo);
    }

    /**
     * Get a single $$studentDisciplineIncidentAssociations$$ entity
     *
     * @param studentDisciplineIncidentAssociationId 
     *            The Id of the $$studentDisciplineIncidentAssociations$$.
     * @param headers
     *            HTTP Request Headers
     * @param uriInfo
     *            URI information including path and query parameters
     * @return A single $$studentDisciplineIncidentAssociations$$ entity
     */
    @GET
    @Path("{" + ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID + "}")
    @Produces({ MediaType.APPLICATION_JSON, HypermediaType.VENDOR_SLC_JSON })
    public Response read(@PathParam(ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID) final String studentDisciplineIncidentAssociationId,
                         @Context HttpHeaders headers, @Context final UriInfo uriInfo) {
        return this.crudDelegate.read(ResourceNames.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATIONS, studentDisciplineIncidentAssociationId, headers, uriInfo);
    }

    /**
     * Delete a $$studentDisciplineIncidentAssociations$$ entity
     *
     * @param studentDisciplineIncidentAssociationId 
     *            The Id of the $$studentDisciplineIncidentAssociations$$.
     * @param headers
     *            HTTP Request Headers
     * @param uriInfo
     *            URI information including path and query parameters
     * @return Returns a NOT_CONTENT status code
     * @response.representation.204.mediaType HTTP headers with a Not-Content status code.
     */
    @DELETE
    @Path("{" + ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID + "}")
    public Response delete(@PathParam(ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID) final String studentDisciplineIncidentAssociationId,
                           @Context HttpHeaders headers, @Context final UriInfo uriInfo) {
        return this.crudDelegate.delete(ResourceNames.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATIONS, studentDisciplineIncidentAssociationId, headers, uriInfo);
    }

    /**
     * Update an existing $$studentDisciplineIncidentAssociations$$ entity.
     *
     * @param studentDisciplineIncidentAssociationId 
     *            The id of the $$studentDisciplineIncidentAssociations$$.
     * @param newEntityBody
     *            entity data
     * @param headers
     *            HTTP Request Headers
     * @param uriInfo
     *            URI information including path and query parameters
     * @return Response with a NOT_CONTENT status code
     * @response.representation.204.mediaType HTTP headers with a Not-Content status code.
     */
    @PUT
    @Path("{" + ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID + "}")
    @Consumes({ MediaType.APPLICATION_JSON, HypermediaType.VENDOR_SLC_JSON })
    public Response update(@PathParam(ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID) final String studentDisciplineIncidentAssociationId,
                           final EntityBody newEntityBody,
                           @Context HttpHeaders headers, @Context final UriInfo uriInfo) {
        return this.crudDelegate.update(ResourceNames.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATIONS, studentDisciplineIncidentAssociationId, newEntityBody, headers, uriInfo);
    }

    /**
     * Returns each $$students$$ that
     * references the given $$studentDisciplineIncidentAssociations$$
     *
     * @param studentDisciplineIncidentAssociationId 
     *            The Id of the studentDisciplineIncidentAssociationId.
     * @param offset
     *            Index of the first result to return
     * @param limit
     *            Maximum number of results to return.
     * @param headers
     *            HTTP Request Headers
     * @param uriInfo
     *            URI information including path and query parameters
     * @return
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, HypermediaType.VENDOR_SLC_JSON })
    @Path("{" + ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID + "}" + "/" + PathConstants.STUDENTS)
    public Response getStudents(@PathParam(ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID) final String studentDisciplineIncidentAssociationId,
                                @QueryParam(ParameterConstants.OFFSET) @DefaultValue(ParameterConstants.DEFAULT_OFFSET) final int offset,
                                @QueryParam(ParameterConstants.LIMIT) @DefaultValue(ParameterConstants.DEFAULT_LIMIT) final int limit,
                                @Context HttpHeaders headers, @Context final UriInfo uriInfo) {
        return this.crudDelegate.read(ResourceNames.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATIONS, "_id", studentDisciplineIncidentAssociationId, "studentId", ResourceNames.STUDENTS, headers, uriInfo);
    }

    /**
     * Returns each $$disciplineIncidents$$ that
     * references the given $$studentDisciplineIncidentAssociations$$
     *
     * @param studentDisciplineIncidentAssociationId 
     *            The Id of the studentDisciplineIncidentAssociationId.
     * @param offset
     *            Index of the first result to return
     * @param limit
     *            Maximum number of results to return.
     * @param headers
     *            HTTP Request Headers
     * @param uriInfo
     *            URI information including path and query parameters
     * @return
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, HypermediaType.VENDOR_SLC_JSON })
    @Path("{" + ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID + "}" + "/" + PathConstants.DISCIPLINE_INCIDENTS)
    public Response getDisciplineIncidents(@PathParam(ParameterConstants.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATION_ID) final String studentDisciplineIncidentAssociationId,
                                           @QueryParam(ParameterConstants.OFFSET) @DefaultValue(ParameterConstants.DEFAULT_OFFSET) final int offset,
                                           @QueryParam(ParameterConstants.LIMIT) @DefaultValue(ParameterConstants.DEFAULT_LIMIT) final int limit,
                                           @Context HttpHeaders headers, @Context final UriInfo uriInfo) {
        return this.crudDelegate.read(ResourceNames.STUDENT_DISCIPLINE_INCIDENT_ASSOCIATIONS, "_id", studentDisciplineIncidentAssociationId, "disciplineIncidentId", ResourceNames.DISCIPLINE_INCIDENTS, headers, uriInfo);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
