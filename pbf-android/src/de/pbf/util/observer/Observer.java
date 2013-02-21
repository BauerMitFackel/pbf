package de.pbf.util.observer;

public interface Observer<T> {
    
    public void observableChanged (T observable);
}