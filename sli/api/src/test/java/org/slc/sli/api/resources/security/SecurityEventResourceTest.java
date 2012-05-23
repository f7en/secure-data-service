package org.slc.sli.api.resources.security;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import org.slc.sli.api.init.RoleInitializer;
import org.slc.sli.api.representation.EntityBody;
import org.slc.sli.api.representation.EntityResponse;
import org.slc.sli.api.representation.ErrorResponse;
import org.slc.sli.api.resources.SecurityContextInjector;
import org.slc.sli.api.resources.util.ResourceTestUtil;
import org.slc.sli.api.resources.v1.HypermediaType;
import org.slc.sli.api.test.WebContextTestExecutionListener;

/**
 * Unit Tests for Security Event Resource class.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-test.xml" })
@TestExecutionListeners({ WebContextTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class })
public class SecurityEventResourceTest {

    @Autowired
    SecurityEventResource resource;

    @Autowired
    SecurityContextInjector injector;

    private UriInfo uriInfo;
    private HttpHeaders httpHeaders;

    @Before
    public void setup() throws Exception {
        uriInfo = ResourceTestUtil.buildMockUriInfo(null);

        // inject SLC Operator security context for unit testing
        injector.setAdminContextWithElevatedRights();

        List<String> acceptRequestHeaders = new ArrayList<String>();
        acceptRequestHeaders.add(HypermediaType.VENDOR_SLC_JSON);

        httpHeaders = mock(HttpHeaders.class);
        when(httpHeaders.getRequestHeader("accept")).thenReturn(acceptRequestHeaders);
        when(httpHeaders.getRequestHeaders()).thenReturn(new MultivaluedMapImpl());

        // create one entity
        resource.createSecurityEvent(new EntityBody(sampleEntity1()), httpHeaders, uriInfo);
        resource.createSecurityEvent(new EntityBody(sampleEntity2()), httpHeaders, uriInfo);
        resource.createSecurityEvent(new EntityBody(sampleEntity3()), httpHeaders, uriInfo);
        resource.createSecurityEvent(new EntityBody(sampleEntity4()), httpHeaders, uriInfo);
        resource.createSecurityEvent(new EntityBody(sampleEntity5()), httpHeaders, uriInfo);
    }

    private Map<String, Object> sampleEntity1() {
        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("user", "LoginUser");

        List<String> roles = Arrays.asList(RoleInitializer.REALM_ADMINISTRATOR, RoleInitializer.SEA_ADMINISTRATOR);
        entity.put("roles", roles);

        entity.put("targetEdOrg", "some-faked-EdOrg");
        entity.put("actionUri", "/simple-idp/login");
        entity.put("appId", "SimpleIDP");
        entity.put("executedOn", "localhost.localdomain");
        entity.put("userOrigin", "127.0.0.1");
        entity.put("timeStamp", "1337185304942");
        entity.put("processNameOrId", "18325@devmegatron");
        entity.put("className", "org.slc.sli.sandbox.idp.controller.Login");
        entity.put("logLevel", "TYPE_ERROR");
        entity.put("logMessage", "Failed login to SLIAdmin by devtest.");

        return entity;
    }

    private Map<String, Object> sampleEntity2() {
        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("user", "LoginUser");

        List<String> roles = Arrays.asList(RoleInitializer.REALM_ADMINISTRATOR);
        entity.put("roles", roles);

        entity.put("targetEdOrg", "some-faked-EdOrg");
        entity.put("actionUri", "/simple-idp/login");
        entity.put("appId", "SimpleIDP");
        entity.put("executedOn", "localhost.localdomain");
        entity.put("userOrigin", "127.0.0.1");
        entity.put("timeStamp", "1337185304942");
        entity.put("processNameOrId", "18325@devmegatron");
        entity.put("className", "org.slc.sli.sandbox.idp.controller.Login");
        entity.put("logLevel", "TYPE_ERROR");
        entity.put("logMessage", "Failed login to SLIAdmin by devtest.");

        return entity;
    }

    private Map<String, Object> sampleEntity3() {
        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("user", "LoginUser");

        List<String> roles = Arrays.asList(RoleInitializer.SEA_ADMINISTRATOR);
        entity.put("roles", roles);

        entity.put("targetEdOrg", "some-faked-EdOrg");
        entity.put("actionUri", "/simple-idp/login");
        entity.put("appId", "SimpleIDP");
        entity.put("executedOn", "localhost.localdomain");
        entity.put("userOrigin", "127.0.0.1");
        entity.put("timeStamp", "1337185304942");
        entity.put("processNameOrId", "18325@devmegatron");
        entity.put("className", "org.slc.sli.sandbox.idp.controller.Login");
        entity.put("logLevel", "TYPE_ERROR");
        entity.put("logMessage", "Failed login to SLIAdmin by devtest.");

        return entity;
    }

    private Map<String, Object> sampleEntity4() {
        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("user", "LoginUser");

        List<String> roles = Arrays.asList(RoleInitializer.LEA_ADMINISTRATOR);
        entity.put("roles", roles);

        entity.put("targetEdOrg", "some-faked-EdOrg");
        entity.put("actionUri", "/simple-idp/login");
        entity.put("appId", "SimpleIDP");
        entity.put("executedOn", "localhost.localdomain");
        entity.put("userOrigin", "127.0.0.1");
        entity.put("timeStamp", "1337185304942");
        entity.put("processNameOrId", "18325@devmegatron");
        entity.put("className", "org.slc.sli.sandbox.idp.controller.Login");
        entity.put("logLevel", "TYPE_ERROR");
        entity.put("logMessage", "Failed login to SLIAdmin by devtest.");

        return entity;
    }

    private Map<String, Object> sampleEntity5() {
        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("user", "LoginUser");

        List<String> roles = Arrays.asList(RoleInitializer.SLC_OPERATOR);
        entity.put("roles", roles);

        entity.put("targetEdOrg", "some-faked-EdOrg");
        entity.put("actionUri", "/simple-idp/login");
        entity.put("appId", "SimpleIDP");
        entity.put("executedOn", "localhost.localdomain");
        entity.put("userOrigin", "127.0.0.1");
        entity.put("timeStamp", "1337185304942");
        entity.put("processNameOrId", "18325@devmegatron");
        entity.put("className", "org.slc.sli.sandbox.idp.controller.Login");
        entity.put("logLevel", "TYPE_ERROR");
        entity.put("logMessage", "Failed login to SLIAdmin by devtest.");

        return entity;
    }


    // @Test
    // public void testSecurityEventResource() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testCreateSecurityEvent() {
    // fail("Not yet implemented");
    // }

    //TODO: unit test for SeaAdmin and LeaAdmin are not available at this time because
    //      the "IN" operation of NeutralCriteria is not correctly mocked.

    @Test
    public void testSLCOperatorGetSecurityEvents() {
        injector.setOperatorContext();

        Response response = resource.getSecurityEvents(0, 100, httpHeaders, uriInfo);

        Object responseEntityObj = null;

        if (response.getEntity() instanceof EntityResponse) {
            EntityResponse resp = (EntityResponse) response.getEntity();
            responseEntityObj = resp.getEntity();
        } else {
            fail("Should always return EntityResponse: " + response);
        }

        if (responseEntityObj instanceof EntityBody) {
            assertNotNull(responseEntityObj);
        } else if (responseEntityObj instanceof List<?>) {
            @SuppressWarnings("unchecked")
            List<EntityBody> results = (List<EntityBody>) responseEntityObj;
            assertTrue("Should have five entity", results.size() == 5);
        } else {
            fail("Response entity not recognized: " + response);
        }

    }

    @Test
    public void testDeveloperGetSecurityEvents() {
        injector.setDeveloperContext();

        Response response = resource.getSecurityEvents(0, 100, httpHeaders, uriInfo);

        if (!(response.getEntity() instanceof ErrorResponse)) {
            fail("Should not have any return results: " + response);
        }
    }

    @Test
    public void testRealmAdminGetSecurityEvents() {
        injector.setRealmAdminContext();

        Response response = resource.getSecurityEvents(0, 100, httpHeaders, uriInfo);

        if (!(response.getEntity() instanceof ErrorResponse)) {
            fail("Should not have any return results: " + response);
        }
    }

    @Test
    public void testEducatorGetSecurityEvents() {
        injector.setEducatorContext();

        Response response = resource.getSecurityEvents(0, 100, httpHeaders, uriInfo);

        if (!(response.getEntity() instanceof ErrorResponse)) {
            fail("Should not have any return results: " + response);
        }
    }

    // @Test
    // public void testGetSecurityEvent() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testDeleteSecurityEvent() {
    // fail("Not yet implemented");
    // }
    //
    // @Test
    // public void testUpdateSecurityEventn() {
    // fail("Not yet implemented");
    // }

}
