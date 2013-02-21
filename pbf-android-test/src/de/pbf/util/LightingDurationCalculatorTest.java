package de.pbf.util;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.impl.LightSensor;
import de.pbf.model.sensor.impl.MoistureSensor;

public class LightingDurationCalculatorTest extends AndroidTestCase {

    
    private float[] brightnessValues = {100, 200, 500, 970, 800, 170};
    
    
    public void testCalculateLightingDuration() {

        LightingDurationCalculator calculator = LightingDurationCalculator.INSTANCE;
        long minutes = calculator.calculateLightingDuration(makeTestdata(), TimeUnit.MINUTES);
        
        assertEquals(30, minutes, 0.0001);
    }
    
    public Map<Date, List<Sensor>> makeTestdata () {
        
        Map<Date, List<Sensor>> sensorsOverTime = new HashMap<Date, List<Sensor>>();
        GregorianCalendar calendar = new GregorianCalendar();
        
        calendar.set(2013, 02, 20, 15, 00);
        sensorsOverTime.put(calendar.getTime(), makeSensors(brightnessValues[0]));
        
        calendar.set(2013, 02, 20, 15, 15);
        sensorsOverTime.put(calendar.getTime(), makeSensors(brightnessValues[1]));
        
        calendar.set(2013, 02, 20, 15, 30);
        sensorsOverTime.put(calendar.getTime(), makeSensors(brightnessValues[2]));
        
        calendar.set(2013, 02, 20, 15, 45);
        sensorsOverTime.put(calendar.getTime(), makeSensors(brightnessValues[3]));
        
        calendar.set(2013, 02, 20, 16, 00);
        sensorsOverTime.put(calendar.getTime(), makeSensors(brightnessValues[4]));
        
        calendar.set(2013, 02, 20, 16, 15);
        sensorsOverTime.put(calendar.getTime(), makeSensors(brightnessValues[5]));
        
        return sensorsOverTime;
    }
    
    private List<Sensor> makeSensors(float brightness) {
        
        // MOISTURE SENOR
        
        MoistureSensor.Builder msBuilder = new MoistureSensor.Builder();
        msBuilder.id("MoistureSensor_1");
        msBuilder.rawValue((float) (Math.random() * 1024));
        
        MoistureSensor moistureSensor = msBuilder.build();

        
        // LIGHT SENSOR
        
        LightSensor.Builder lsBuilder = new LightSensor.Builder();
        lsBuilder.id("LightSensor_1");
        lsBuilder.rawValue(brightness);
        
        LightSensor lightSensor = lsBuilder.build();

        
        
        List<Sensor> sensors = new ArrayList<Sensor>();
        sensors.add(moistureSensor);
        sensors.add(lightSensor);
        
        return sensors;
    }
}
