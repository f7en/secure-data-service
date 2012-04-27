package org.slc.sli.ingestion.handler;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.slc.sli.ingestion.landingzone.IngestionFileEntry;
import org.slc.sli.ingestion.landingzone.LocalFileSystemLandingZone;
import org.slc.sli.ingestion.validation.ErrorReport;

/**
 *
 * @author ccheng
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-test.xml" })
public class MatchDiffHandlerTest {
    @Autowired
    private MatchDiffHandler matchDiffHandler;

    @Autowired
    LocalFileSystemLandingZone lz;

    @Test
    public void testDoHandling() throws IOException {
        IngestionFileEntry mockedIngestionFileEntry = mock(IngestionFileEntry.class);
        ErrorReport mockedErrorReport = mock(ErrorReport.class);

        File newRecordFile = File.createTempFile("newRecord_", ".tmp");
        newRecordFile.deleteOnExit();

        when(mockedIngestionFileEntry.getNeutralRecordFile()).thenReturn(newRecordFile);
        when(mockedIngestionFileEntry.getTopLevelLandingZonePath()).thenReturn(lz.getLZId());

        matchDiffHandler.doHandling(mockedIngestionFileEntry, mockedErrorReport);

        verify(mockedIngestionFileEntry).setDeltaNeutralRecordFile(newRecordFile);
    }

}
