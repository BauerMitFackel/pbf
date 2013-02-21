
package de.pbf.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import de.pbf.model.sensor.Sensor;
import de.pbf.util.observer.Observable;
import de.pbf.util.observer.Observer;

/**
 * SensorStation object.
 * @author Ulrich Raab
 */
public class SensorStation implements Observable<SensorStation> {
    
    /**
     * The url of the sensor station.
     */
    private String url;

    /**
     * The name of the sensor station.
     */
    private String name;
    
    
    private List<Observer<SensorStation>> observers = new ArrayList<Observer<SensorStation>>();
    private Map<Date, List<Sensor>> sensorsOverTime = new ConcurrentHashMap<Date, List<Sensor>>();
    

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
        
        if (keys.isEmpty()) {
            return new ArrayList<Sensor>();
        }
        
        Date key = keys.get(keys.size() - 1);
        List<Sensor> sensors = sensorsOverTime().get(key);
        
        return sensors;
    }
    
        
    public Map<Date, List<Sensor>> sensorsOverTime() {
        
        return sensorsOverTime;
    }

    
    @Override
    public void register(Observer<SensorStation> observer) {
        
        observers.add(observer);
    }

    @Override
    public void unregister(Observer<SensorStation> observer) {
        
        boolean success = observers.remove(observer);
        if (success) {
            unregister(observer);
            
        }
    }

    @Override
    public void informOnChanges() {
        
        for (Observer<SensorStation> observer : observers) {
            observer.observableChanged(this);
        }
    }
}
