package de.pbf.io;

import android.test.AndroidTestCase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.impl.LightSensor;
import de.pbf.model.sensor.impl.MoistureSensor;
import de.pbf.model.sensor.impl.TemperatureSensor;

public class SensorDeserializerTest extends AndroidTestCase {

    private static final String JSON_STRING = "[{\"type\":\"moisture_sensor\",\"id\":\"S2\",\"value\":1024},{\"type\":\"light_sensor\",\"id\":\"S3\",\"value\":520},{\"type\":\"temperature_sensor\",\"id\":\"S4\",\"value\":21}]";
    
    
    private Gson gson;
    private Type type;
    
    public void testDeserialize() {
        
        initializeGson();
        
        List<Sensor> sensors = gson.fromJson(JSON_STRING, type);
        assertEquals(3, sensors.size());
        
        for (Sensor sensor : sensors) {
            
            if (sensor.id().equals("S2")) {
                assertTrue(sensor instanceof MoistureSensor);
            }
            else if (sensor.id().equals("S3")) {
                assertTrue(sensor instanceof LightSensor);
            }
            else if (sensor.id().equals("S4")) {
                assertTrue(sensor instanceof TemperatureSensor);
            }
        }
    }
    
    private void initializeGson() {
        
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Sensor.class, new SensorDeserializer());

        gson = builder.create();

        TypeToken<List<Sensor>> typeToken = new TypeToken<List<Sensor>>() {};
        type = (Type) typeToken.getType();
    }
}
