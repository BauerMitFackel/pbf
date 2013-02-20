package de.pbf.model.sensor;

/**
 * Abstract {@link Sensor} implementation which could be used by concrete Sensor
 * implementations. Provides a {@link Builder} for creating instances.
 * 
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
     * The sensor label.
     */
    private String label;

    /**
     * Constructor.
     * 
     * @param builder
     *            The {@link Builder} instance to use for construction.
     */
    public GenericSensor(Builder builder) {

        this.id = builder.id;
        this.value = builder.value;
        this.unit = builder.unit;
        this.label = builder.label;
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

    @Override
    public String label() {

        return label;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        GenericSensor other = (GenericSensor) obj;

        if (id == null) {
            if (other.id != null) {
                return false;
            }

        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }

    // BUILDER

    /**
     * Builder class for {@link GenericSensor} objects.
     * 
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
         * The sensor label. Default value is <b>undefined</b>.
         */
        private String label = "undefined";

        /**
         * Setter for {@link #id}.
         * 
         * @param id
         *            The id to set
         */
        public void id(String id) {

            this.id = id;
        }

        /**
         * Setter for {@link #value}.
         * 
         * @param value
         *            The value to set
         */
        protected void value(float value) {

            this.value = value;
        }

        /**
         * Setter for {@link #unit}.
         * 
         * @param unit
         *            The unit to set
         */
        public void unit(Unit unit) {

            this.unit = unit;
        }

        public void label(String label) {

            this.label = label;
        }

        /**
         * Subclasses must implement this method to create instances of concrete
         * sensors.
         * 
         * @return A sensor instance
         */
        public abstract Sensor build();
    }
}
