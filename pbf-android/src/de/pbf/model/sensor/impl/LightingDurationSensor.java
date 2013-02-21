
package de.pbf.model.sensor.impl;

import de.pbf.model.sensor.GenericSensor;
import de.pbf.model.sensor.Unit;

/**
 * The Light duration of a light.
 * @author Ulrich Raab
 */
public class LightingDurationSensor extends GenericSensor {

    /**
     * Constructor.
     * @param builder The {@link Builder} instance to use for construction.
     */
    public LightingDurationSensor(Builder builder) {

        super(builder);
    }

    // BUILDER

    /**
     * Builder class for {@link LightingDurationSensor} objects.
     * @author Ulrich Raab
     */
    public static class Builder extends GenericSensor.Builder {

        /**
         * Constructor.
         */
        public Builder() {

            unit(Unit.LIGHT_DURATION_IN_MINUTS);
            label("Lichtdauer");
        }

        /**
         * Setter for {@link #rawValue}. Calling this method has no effect.
         * Use {@link #lightingDuration(float)} to set the lighting duration.
         * @param rawValue The raw value
         */
        public void rawValue(float rawValue) {

            // EMPTY
        }
        
        
        public void lightingDuration(float minutes) {
            
            value(minutes);
        }
        

        /**
         * Creates a new {@link LightingDurationSensor} instance.
         * @return A new {@link LightingDurationSensor} instance.
         */
        @Override
        public LightingDurationSensor build() {

            return new LightingDurationSensor(this);
        }
    }
}
