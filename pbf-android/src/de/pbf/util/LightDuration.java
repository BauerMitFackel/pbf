package de.pbf.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.impl.LightSensor;

public class LightDuration {
    
    private static LightDuration instance;
    
    static {
        instance = new LightDuration();
    }
    
    public static LightDuration getInstance() {
        return instance;
    }


    /**
     *  Calculate the duration of light since the last dark period
     * @param sensorsOverTime
     * @param minLightIntensity
     * @return the time in seconds of the light availability since the last dark period
     */
    public long lightDuration(Map<Date, List<Sensor>> sensorsOverTime, int minLightIntensity){
        
        
        Set<Date> keySet = sensorsOverTime.keySet();
        List<Date> keys = new ArrayList<Date>(keySet);
        Collections.sort(keys);
        
        Date currentDate = keys.get(keys.size()-1);
        long lightDuration = 0;
        Boolean isDone = false;
        
        for(int i = keys.size()-2; i >= 0; i--){
            
            for(Sensor sensor:sensorsOverTime.get(keys.get(i))){
                
                if(sensor instanceof LightSensor){
                    
                    if(sensor.value() >= minLightIntensity){
                        
                        long currentTime = currentDate.getTime();
                        long time = keys.get(i).getTime();
                        long lightDurationMS = (currentTime - time);
                        
                        lightDuration = (lightDurationMS / (1000));
                    } else {
                        
                        isDone = true;
                        break; 
                    }
                    
                }
            }
            
            if(isDone)break;               
        }
        
        return lightDuration;
    }
    
    
}
