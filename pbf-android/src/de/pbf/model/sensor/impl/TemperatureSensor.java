
package de.pbf.model.sensor.impl;

import de.pbf.model.sensor.GenericSensor;
import de.pbf.model.sensor.Unit;

/**
 * A temperature sensor measures the temperature.
 * @author Ulrich Raab
 */
public class TemperatureSensor extends GenericSensor {

    /**
     * Constructor.
     * @param builder The {@link Builder} instance to use for construction.
     */
    public TemperatureSensor(Builder builder) {

        super(builder);
    }

    // BUILDER

    /**
     * Builder class for {@link TemperatureSensor} objects.
     * @author Ulrich Raab
     */
    public static class Builder extends GenericSensor.Builder {

        /**
         * The raw value obtained from the sensor.
         */
        @SuppressWarnings("unused")
        private float rawValue;

        /**
         * Constructor.
         */
        public Builder() {

            unit(Unit.DEGREE_CELSIUS);
            label("Temperatursensor");
        }

        /**
         * Setter for {@link #rawValue}. Also transforms the raw value dependent on the the unit and sets the value after transformation.
         * @param rawValue The raw value to set and transform
         */
        public void rawValue(float rawValue) {

            this.rawValue = rawValue;
            value(rawValue);
        }

        /**
         * Creates a new {@link TemperatureSensor} instance.
         * @return A new {@link TemperatureSensor} instance.
         */
        @Override
        public TemperatureSensor build() {

            return new TemperatureSensor(this);
        }
    }
}
