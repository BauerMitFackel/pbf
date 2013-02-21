package de.pbf.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.impl.LightSensor;

public enum LightingDurationCalculator {
    
    INSTANCE(20);
    
    private int minLightingBrightness;
    
    
    private LightingDurationCalculator(int minLightingBrightness) {
        
        this.minLightingBrightness = minLightingBrightness;
    }
    
    
    public long calculateLightingDuration(Map<Date, List<Sensor>> sensorsOverTime, TimeUnit timeUnit) {
        
        Set<Date> keySet = sensorsOverTime.keySet();
        List<Date> keys = new ArrayList<Date>(keySet);
        Collections.sort(keys);
        
        Date end = null;
        Date last = null;
        
        for (int i = keys.size() - 1; i >= 0; i--) {
            Date date = keys.get(i);
            List<Sensor> sensors = sensorsOverTime.get(date);
            LightSensor lightSensor = obtainLightSensor(sensors);
            
            if (isLightingBrightnessGreaterThanMinimum(lightSensor)) {
                if (end == null) {
                    end = date;
                }
                
                last = date;
            }
            else if (end != null && last != null) {
                break;
            }
        }
        
        long durationInMilliseconds = end.getTime() - last.getTime();
        return timeUnit.convert(durationInMilliseconds, TimeUnit.MILLISECONDS);
    }
    
    
    private LightSensor obtainLightSensor(List<Sensor> sensors) {
        
        for (Sensor sensor : sensors) {
            if (sensor instanceof LightSensor) {
                return (LightSensor) sensor;
            }
        }
        
        return null;
    }
    
    
    private boolean isLightingBrightnessGreaterThanMinimum(LightSensor lightSensor) {
        
        if (lightSensor == null) {
            return false;
        }
        
        return lightSensor.value() >= minLightingBrightness;
    }
}
