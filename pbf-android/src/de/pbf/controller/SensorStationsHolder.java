package de.pbf.controller;

import java.util.ArrayList;
import java.util.List;

import de.pbf.model.SensorStation;

public enum SensorStationsHolder {
    
    INSTANCE;
    
    private List<SensorStation> sensorStations = new ArrayList<SensorStation>();
    
    
    public void add(SensorStation sensorStation) {
        
        sensorStations.add(sensorStation);
    }
    
    public SensorStation get(String url) {
        
        for (SensorStation sensorStation : sensorStations) {
            if (sensorStation.getUrl().equals(url)) {
                return sensorStation;
            }
        }
        
        return null;
    }
    
    public List<SensorStation> sensorStations() {
        
        return sensorStations;
    }
    
    
    public void remove(SensorStation sensorStation) {
        
        sensorStations.remove(sensorStation);
    }
    
    public void clear() {
        
        sensorStations = new ArrayList<SensorStation>();
    }
}
