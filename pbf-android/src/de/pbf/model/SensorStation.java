
package de.pbf.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.pbf.model.sensor.Sensor;

/**
 * SensorStation object.
 * @author Ulrich Raab
 */
public class SensorStation {

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

    
    public List<Sensor> sensors() {
        
        Set<Date> keySet = sensorsOverTime.keySet();
        List<Date> keys = new ArrayList<Date>(keySet);
        Collections.sort(keys);
        
        return sensorsOverTime.get(keys.size() - 1);
    }
    
        
    public Map<Date, List<Sensor>> sensorsOverTime() {
        
        return sensorsOverTime;
    }
}
