
package de.pbf.io;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncWebserviceRequest extends AsyncTask<String, String, String> {
    
    
    private Listener listener;
    

    public void setListener (Listener listener) {
        
        this.listener = listener;
    }
    
    
    @Override
    protected String doInBackground(String... params) {
        
        String responseString = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            responseString = read(in);

        } catch (Exception ex) {
            Log.e("AsyncWebserviceRequest", "Error", ex);
        }
        
        String jsonResponse = obtainJsonString(responseString);
        return jsonResponse;
    }
    
    
    private String obtainJsonString(String response) {
        
        response = response.replaceAll(".*<body>", "");
        response = response.replaceAll("</body>.*", "");
        
        return response;
    }
    

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        
        if (listener != null) {
            listener.executionFinished(result);
        }
    }


    /**
     * Read the contents from the input stream.
     * @param inputStream The input stream
     * @return A string
     */
    private String read(InputStream inputStream) {

        StringBuffer stringBuffer = new StringBuffer();

        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }

        } catch (IOException ex) {
            Log.e("AsyncWebserviceRequest", "Error", ex);
        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Log.e("AsyncWebserviceRequest", "Error", ex);
                }
            }
        }

        return stringBuffer.toString();
    }
    
    
    public interface Listener {
        
        public void executionFinished(String response);
    }
}
