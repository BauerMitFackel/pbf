package de.pbf.controller;

import java.util.ArrayList;
import java.util.List;

import de.pbf.model.SensorStation;
import de.pbf.util.observer.Observable;
import de.pbf.util.observer.Observer;

public enum SensorStationsHolder implements Observable<SensorStationsHolder> {
    
    INSTANCE;
    
    private List<SensorStation> sensorStations = new ArrayList<SensorStation>();
    private List<Observer<SensorStationsHolder>> observers = new ArrayList<Observer<SensorStationsHolder>>();
    
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

    @Override
    public void register(Observer<SensorStationsHolder> observer) {

        observers.add(observer);
    }

    @Override
    public void unregister(Observer<SensorStationsHolder> observer) {
        
        boolean success = observers.remove(observer);
        if (success) {
            unregister(observer);
            
        }
    }

    @Override
    public void informOnChanges() {
        
        for (Observer<SensorStationsHolder> observer : observers) {
            observer.observableChanged(this);
        }
    }
}
