
package de.pbf.model.sensor.impl;

import de.pbf.model.sensor.GenericSensor;
import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.Unit;
import de.pbf.util.RangeTransformer;

/**
 * A moisture sensor detects moisture in soil.
 * @author Ulrich Raab
 */
public class MoistureSensor extends GenericSensor implements Sensor {

    /**
     * The minimum value in the sensor range.
     */
    private static final float ORIGINAL_SENSOR_RANGE_MIN = 0F;

    /**
     * The maximum value in the sensor range.
     */
    private static final float ORIGINAL_SENSOR_RANGE_MAX = 1024F;

    /**
     * The minimum value in the target range.
     */
    private static final float TARGET_RANGE_MIN = 0F;

    /**
     * The maximum value in the target range.
     */
    private static final float TARGET_RANGE_MAX = 100F;

    /**
     * Constructor.
     * @param builder The {@link Builder} instance to use for construction.
     */
    public MoistureSensor(Builder builder) {

        super(builder);
    }

    // BUILDER

    /**
     * Builder class for {@link MoistureSensor} objects.
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

            unit(Unit.MOISTURE_IN_PERCENT);
        }

        /**
         * Setter for {@link #rawValue}. Also transforms the raw value dependent on the the unit and sets the value after transformation.
         * @param rawValue The raw value to set and transform
         */
        public void rawValue(float rawValue) {

            this.rawValue = rawValue;

            float result = RangeTransformer.INSTANCE.transform(ORIGINAL_SENSOR_RANGE_MIN, ORIGINAL_SENSOR_RANGE_MAX, TARGET_RANGE_MIN, TARGET_RANGE_MAX, rawValue);
            value(result);
        }

        /**
         * Creates a new {@link MoistureSensor} instance.
         * @return A new {@link MoistureSensor} instance.
         */
        @Override
        public MoistureSensor build() {

            return new MoistureSensor(this);
        }
    }
}
