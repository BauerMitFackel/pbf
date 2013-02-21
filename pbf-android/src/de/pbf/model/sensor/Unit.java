
package de.pbf.model.sensor;

import de.pbf.App;
import de.pbf.R;

/**
 * A unit of measurement is a standardized quantity of a physical property, used as a factor to express occurring quantities of that property.
 * @author Ulrich Raab
 */
public enum Unit {

    /**
     * Temperature.
     */
    DEGREE_CELSIUS(R.string.unit_label_degree_celsius),

    /**
     * Moisture.
     */
    MOISTURE_IN_PERCENT(R.string.unit_label_moisture_in_percent),

    /**
     * Brightness.
     */
    BRIGHTNESS_IN_PERCENT(R.string.unit_label_brightness_in_percent),
    
    /**
     * Light duration.
     */
    LIGHT_DURATION_IN_MINUTS(R.string.unit_label_duration_in_minutes),
    
    /**
     * Unknown unit.
     */
    UNKNOWN(R.string.unit_label_unknown);
    
    /**
     * The label of the unit.
     */
    private String label;
    
    /**
     * Constructor.
     * @param resourceId The resource id of the label
     */
    private Unit(int resourceId) {
        
        this.label = App.context().getString(resourceId);
    }
    
    /**
     * Getter for {@link #label}.
     * @return The label of the unit
     */
    public String label() {
        
        return label;
    }
}
