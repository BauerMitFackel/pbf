
package de.pbf.model.sensor.impl;

import de.pbf.model.sensor.GenericSensor;
import de.pbf.model.sensor.Unit;
import de.pbf.util.RangeTransformer;

/**
 * The Light duration of a light.
 * @author Ulrich Raab
 */
public class LightDurationSensor extends GenericSensor {

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

    
    private float lightDuration;
    /**
     * Constructor.
     * @param builder The {@link Builder} instance to use for construction.
     */
    public LightDurationSensor(Builder builder) {

        super(builder);
    }
    
    public void setlightDuration(long lightDuration){
        this.lightDuration = lightDuration;
    }
    
    @Override
    public float value() {
       
        return (this.lightDuration / 60);
    }


    // BUILDER

    /**
     * Builder class for {@link LightDurationSensor} objects.
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

            unit(Unit.LIGHT_DURATION_IN_MINUTS);
            label("Lichtdauer");
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
         * Creates a new {@link LightDurationSensor} instance.
         * @return A new {@link LightDurationSensor} instance.
         */
        @Override
        public LightDurationSensor build() {

            return new LightDurationSensor(this);
        }
    }
}
