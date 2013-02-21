package de.pbf.util.observer;

public interface Observable<T> {
    
    /**
     * Registers the the given {@link Observer}
     * @param observer The {@link Observer} which should be registered
     */
    public void register (Observer<T> observer);
    
    
    /**
     * Unregisters the given {@link Observer}
     * @param observer The {@link Observer} which should be unregistered
     */
    public void unregister (Observer<T> observer);
    
    /**
     * Tells the observable to notify all observers.
     */
    public void informOnChanges();
}
