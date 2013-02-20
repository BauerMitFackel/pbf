package de.pbf.controller;

import java.util.ArrayList;
import java.util.List;

import de.pbf.model.SensorStation;
import de.pbf.model.sensor.Sensor;

public enum SensorHolder {
    
    INSTANCE;
    
    private List<Sensor> sensors = new ArrayList<Sensor>();
    
    
    public void add(Sensor sensor) {
        
        sensors.add(sensor);
    }
    
    public Sensor get(String id) {
        
        for (Sensor sensor : sensors) {
            if (sensor.id().equals(id)) {
                return sensor;
            }
        }
        
        return null;
    }
    
    public List<Sensor> sensors() {
        
        return sensors;
    }
    
    
    public void remove(Sensor sensor) {
        
        sensors.remove(sensor);
    }
    
    public void clear() {
        
        sensors = new ArrayList<Sensor>();
    }
}
