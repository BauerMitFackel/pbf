
package de.pbf.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.impl.LightSensor;

/**
 * SensorStation object.
 * @author Ulrich Raab
 */
public class SensorStation {

    
    private final int MIN_LIGHT_INTENSITY = 20;
    
    /**
     * The url of the sensor station.
     */
    private String url;

    /**
     * The name of the sensor station.
     */
    private String name;

    
    private Map<Date, List<Sensor>> sensorsOverTime = new HashMap<Date, List<Sensor>>();
    

    /**
     * Getter for {@link #url}.
     * @return The url as {@link String}
     */
    public String getUrl() {

        return url;
    }

    /**
     * Setter for {@link #url}.
     * @param url The url to set
     */
    public void setUrl(String url) {

        this.url = url;
    }

    /**
     * Getter for {@link #name}.
     * @return The name as {@link String}
     */
    public String getName() {

        return name;
    }

    /**
     * Setter for {@link #name}.
     * @param name The name to set
     */
    public void setName(String name) {

        this.name = name;
    }
    
    /**
     * @return the time in minutes of the light availability since the last darkperiode  
     */
    public long lightDuration(){
        
        Map<Date, List<Sensor>> sensorsOverTime = sensorsOverTime();
        
        Set<Date> keySet = sensorsOverTime.keySet();
        List<Date> keys = new ArrayList<Date>(keySet);
        Collections.sort(keys);
        
        Date currentDate = keys.get(keys.size()-1);
        long lightDuration = 0;
        Boolean isDone = false;
        
        for(int i = keys.size()-2; i >= 0; i--){
            
            for(Sensor sensor:sensorsOverTime.get(keys.get(i))){
                
                if(sensor instanceof LightSensor){
                    
                    if(sensor.value() >= MIN_LIGHT_INTENSITY){
                        lightDuration = (keys.get(i).getTime() - currentDate.getTime()) / (60*1000) % 60;
                    }
                    break;
                }
                break;
            }
            
            if(isDone)break;               
        }
        
        return lightDuration;
    }

    
    public List<Sensor> sensors() {
        
        Set<Date> keySet = sensorsOverTime.keySet();
        List<Date> keys = new ArrayList<Date>(keySet);
        Collections.sort(keys);
        
        Date key = keys.get(keys.size() - 1);
        List<Sensor> sensors = sensorsOverTime().get(key);
        
        return sensors;
    }
    
        
    public Map<Date, List<Sensor>> sensorsOverTime() {
        
        return sensorsOverTime;
    }
}
