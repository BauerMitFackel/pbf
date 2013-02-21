package de.pbf.model;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.impl.LightSensor;
import de.pbf.model.sensor.impl.MoistureSensor;
import de.pbf.util.observer.Observer;

public class SensorStationTest extends AndroidTestCase {
    
    
    public void testGetterAndSetter() {
        
        SensorStation sensorStation = new SensorStation();
        sensorStation.setName("Name");
        sensorStation.setUrl("http://www.google.de");
        
        assertEquals("Name", sensorStation.getName());
        assertEquals("http://www.google.de", sensorStation.getUrl());
    }
    
    
    public void testObservable() {
        
        final SensorStation sensorStation = new SensorStation();
        sensorStation.register(new Observer<SensorStation>() {
            
            @Override
            public void observableChanged(SensorStation observable) {
            
                assertEquals(sensorStation, observable);
            }
        });
        
        sensorStation.informOnChanges();
    }
    
    
    public void testSensors() {
        
        SensorStation sensorStation = new SensorStation();
        
        assertNotNull(sensorStation.sensors());
        assertTrue(sensorStation.sensors().isEmpty());
        
        GregorianCalendar calendar = new GregorianCalendar();
        
        calendar.set(2013, 02, 20, 15, 00);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors(1));
        
        calendar.set(2013, 02, 20, 16, 15);
        List<Sensor> expected = makeSensors(2);
        sensorStation.sensorsOverTime().put(calendar.getTime(), expected);
        
        assertTrue(sensorStation.sensors().equals(expected));
    }

    private List<Sensor> makeSensors(int nr) {
        
        // MOISTURE SENOR
        
        MoistureSensor.Builder msBuilder = new MoistureSensor.Builder();
        msBuilder.id("MoistureSensor_" + nr);
        msBuilder.rawValue((float) (Math.random() * 1024));
        
        MoistureSensor moistureSensor = msBuilder.build();

        
        // LIGHT SENSOR
        
        LightSensor.Builder lsBuilder = new LightSensor.Builder();
        lsBuilder.id("LightSensor_" + nr);
        lsBuilder.rawValue((float) (Math.random() * 1024));
        
        LightSensor lightSensor = lsBuilder.build();

        
        
        List<Sensor> sensors = new ArrayList<Sensor>();
        sensors.add(moistureSensor);
        sensors.add(lightSensor);
        
        return sensors;
    }
}
