package de.pbf.io;

import android.test.AndroidTestCase;

public class AsyncWebserviceRequestTest extends AndroidTestCase {

    public void testExecute() {
        
        AsyncWebserviceRequest asyncWebserviceRequest = new AsyncWebserviceRequest();
        asyncWebserviceRequest.setListener(makeListener());
        
        asyncWebserviceRequest.execute("http://www.google.de");

    }
    
    private AsyncWebserviceRequest.Listener makeListener() {
        
        AsyncWebserviceRequest.Listener listener = new AsyncWebserviceRequest.Listener() {

            @Override
            public void executionFinished(String response) {

                assertNotNull(response);
                assertTrue(response.isEmpty());
            }
        };
        
        return listener;
    }

}
