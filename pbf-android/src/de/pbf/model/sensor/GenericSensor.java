
package de.pbf.model.sensor;

/**
 * Abstract {@link Sensor} implementation which could be used by concrete Sensor implementations. Provides a {@link Builder} for creating instances.
 * @author Ulrich Raab
 */
public abstract class GenericSensor implements Sensor {

    /**
     * The sensor id.
     */
    private String id;

    /**
     * The measured value.
     */
    private float value;

    /**
     * The measuring unit.
     */
    private Unit unit;

    /**
     * Constructor.
     * @param builder The {@link Builder} instance to use for construction.
     */
    public GenericSensor(Builder builder) {

        this.id = builder.id;
        this.value = builder.value;
        this.unit = builder.unit;
    }

    @Override
    public String id() {

        return id;
    }

    @Override
    public float value() {

        return value;
    }

    @Override
    public Unit unit() {

        return unit;
    }

    // BUILDER
    
    /**
     * Builder class for {@link GenericSensor} objects.
     * @author Ulrich Raab
     */
    public abstract static class Builder implements Sensor.Builder {

        /**
         * The sensor id. Default value is <b>unknown</b>.
         */
        private String id = "unknown";

        /**
         * The measured value. Default value is {@link Float.MIN_VALUE}.
         */
        private float value = Float.MIN_VALUE;

        /**
         * The measuring unit. Default value is <b>unknown</b>.
         */
        private Unit unit = Unit.UNKNOWN;

        /**
         * Setter for {@link #id}.
         * @param id The id to set
         */
        public void id(String id) {

            this.id = id;
        }

        /**
         * Setter for {@link #value}.
         * @param value The value to set
         */
        protected void value(float value) {

            this.value = value;
        }

        /**
         * Setter for {@link #unit}.
         * @param unit The unit to set
         */
        public void unit(Unit unit) {

            this.unit = unit;
        }
        
        /**
         * Subclasses must implement this method to create instances of concrete sensors.
         * @return A sensor instance
         */
        public abstract Sensor build();
    }
}
