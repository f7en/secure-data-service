package org.slc.sli.api.init;

import static junit.framework.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slc.sli.domain.Entity;
import org.slc.sli.domain.NeutralQuery;
import org.slc.sli.domain.Repository;
import org.springframework.core.io.ByteArrayResource;

/**
 * 
 *
 */
public class ApplicationInitializerTest {
    
    @InjectMocks
    private ApplicationInitializer appInit;
    
    @Mock
    private Repository<Entity> mockRepo;
    
    Properties props = new Properties();
    
    @Before
    public void setUp() throws Exception {
        appInit = new ApplicationInitializer();
        MockitoAnnotations.initMocks(this);
        
        props.put("bootstrap.app.keys", "admin,dashboard");
        props.put("bootstrap.app.dashboard.template", "applications/dashboard.json");
        props.put("bootstrap.app.dashboard.url", "https://dashboard");
        props.put("bootstrap.app.dashboard.client_id", "XXXXXXXX");
        props.put("bootstrap.app.dashboard.client_secret", "YYYYYYYYYYYY");
        props.put("bootstrap.app.admin.template", "applications/admin.json");
        props.put("bootstrap.app.admin.url", "https://admin");
        props.put("bootstrap.app.admin.client_id", "XXXXXXXX");
        props.put("bootstrap.app.admin.client_secret", "YYYYYYYYYYYY");
        saveProps();
        
    }
    
    private void saveProps() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        props.store(baos, "");
        appInit.bootstrapProperties = new ByteArrayResource(baos.toByteArray());
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testAppNotExist() throws Exception {
        final List<Map<String, Object>> apps = new ArrayList<Map<String, Object>>();
        Mockito.when(mockRepo.create(Mockito.anyString(), Mockito.anyMap())).thenAnswer(new Answer<Entity>() {
            
            @Override
            public Entity answer(InvocationOnMock invocation) throws Throwable {
                apps.add((Map<String, Object>) invocation.getArguments()[1]);
                return null;
            }
        });
        appInit.init();
        assertEquals("Two apps registered", 2, apps.size());
        assertEquals("Value replaced", "https://admin", apps.get(0).get("application_url"));
        assertEquals("Value replaced", "https://dashboard", apps.get(1).get("application_url"));
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testCreateWithGuid() throws Exception {
        // when a GUID is specified, we have to use an update instead of a create
        final List<Entity> apps = new ArrayList<Entity>();
        props.put("bootstrap.app.admin.guid", "111");
        props.put("bootstrap.app.dashboard.guid", "222");
        saveProps();
        Mockito.when(mockRepo.update(Mockito.anyString(), Mockito.any(Entity.class))).thenAnswer(new Answer<Boolean>() {
            
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                apps.add((Entity) invocation.getArguments()[1]);
                return true;
            }
        });
        appInit.init();
        assertEquals("Two apps updated", 2, apps.size());
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testExistingApps() throws Exception {
        final List<Entity> apps = new ArrayList<Entity>();
        props.put("bootstrap.app.keys", "admin");
        saveProps();
        Entity mockEntity = Mockito.mock(Entity.class);
        Mockito.when(mockRepo.findOne(Mockito.anyString(), Mockito.any(NeutralQuery.class))).thenReturn(mockEntity);
        
        Mockito.when(mockRepo.update(Mockito.anyString(), Mockito.any(Entity.class))).thenAnswer(new Answer<Boolean>() {
            
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                apps.add((Entity) invocation.getArguments()[1]);
                return true;
            }
        });
        appInit.init();
        assertEquals("One app updated", 1, apps.size());
    }
    
}
