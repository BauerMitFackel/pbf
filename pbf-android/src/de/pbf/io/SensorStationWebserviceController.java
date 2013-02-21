package de.pbf.io;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.pbf.model.SensorStation;
import de.pbf.model.sensor.Sensor;


/**
 * Controller for easy sensor station webservice use.
 * @author Ulrich Raab
 */
public class SensorStationWebserviceController {
    
    
    private static final String LOG_TAG = "SSWC";
    
    //private static final long TIMER_SCHEDULE_PERIOD_IN_MS = 900000; // 15 Minutes
    private static final long TIMER_SCHEDULE_PERIOD_IN_MS = 3000; // 30 Seconds
    
    
    private List<SensorStation> sensorStations;
    
    private Gson gson;
    private Type type;
    
    private Timer timer;
    
    
    /**
     * Constructor.
     * @param sensorStations List of sensor stations.
     */
    public SensorStationWebserviceController(List<SensorStation> sensorStations) {
        
        this.sensorStations = sensorStations;
        initializeGson();
    }
    
    
    private void initializeGson() {
        
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Sensor.class, new SensorDeserializer());

        gson = builder.create();

        TypeToken<List<Sensor>> typeToken = new TypeToken<List<Sensor>>() {};
        type = (Type) typeToken.getType();
    }
    
    
    public void updateSensorStations(List<SensorStation> sensorStations) {
        
        this.sensorStations = sensorStations;
    }
    
    /**
     * Executes recurring http request to get actual sensor data.
     */
    public void execute() {
        
        final Handler handler = new Handler();
        timer = new Timer();
        
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                handler.post(makeRunnable());
            }
        };
        
        timer.schedule(timerTask, 0, TIMER_SCHEDULE_PERIOD_IN_MS);
    }
    
    
    /**
     * Cancel the execution.
     * @see Timer#cancel()
     */
    public void cancel() {
        
        timer.cancel();
    }
    
    
    private Runnable makeRunnable() {
          
        Runnable runnable = new Runnable() {
            
            @Override
            public void run() {
                
                for (SensorStation sensorStation : sensorStations) {
                    AsyncWebserviceRequest asyncWebserviceRequest = new AsyncWebserviceRequest();
                    asyncWebserviceRequest.setListener(makeListener(sensorStation));
                    asyncWebserviceRequest.execute(sensorStation.getUrl());
                }
            }
        };
        
        return runnable;
    }

    
    private AsyncWebserviceRequest.Listener makeListener(final SensorStation sensorStation) {
        
        AsyncWebserviceRequest.Listener listener = new AsyncWebserviceRequest.Listener() {

            @Override
            public void executionFinished(String response) {
                
                try {
                    Date date = new Date();
                    List<Sensor> sensors = gson.fromJson(response, type);
                    sensorStation.sensorsOverTime().put(date, sensors);
                    sensorStation.informOnChanges();
                    
                    Log.e(LOG_TAG, "Added new sensors to sensor station with url " + sensorStation.getUrl());
                }
                catch (Exception exception) {
                    Log.e(LOG_TAG, exception.toString());
                }
            }
        };
        
        return listener;
    }
}
