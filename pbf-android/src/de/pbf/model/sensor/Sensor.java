
package de.pbf.model.sensor;

/**
 * A sensor is a converter that measures a physical quantity and converts it into a signal which can be read by an observer or by an instrument.
 * @author Ulrich Raab
 */
public interface Sensor {

    /**
     * Returns the sensor id.
     * @return The sensor id as {@link String}
     */
    String id();

    /**
     * Returns the measured value.
     * @return The measured value as float.
     */
    float value();
    
    /**
     * Returns the measuring unit.
     * @return The measuring unit as {@link Unit}
     */
    Unit unit();
    
    /**
     * Builder interface for sensor builders.
     * @author Ulrich Raab
     */
    public interface Builder {
        
        /**
         * Setter for the id.
         * @param id The id to set
         */
        void id(String id);

        /**
         * Setter for the measured raw value.
         * @param rawValue The measured raw value to set
         */
        void rawValue(float rawValue);
        
        /**
         * Setter for the unit.
         * @param unit The unit to set
         */
        void unit(Unit unit);
        
        /**
         * Builds a new sensor instance.
         * @return A new sensor instance
         */
        Sensor build();
    }
}
