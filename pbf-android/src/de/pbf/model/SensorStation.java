
package de.pbf.model;

import java.util.List;

import de.pbf.model.sensor.Sensor;

/**
 * SensorStation object.
 * @author Ulrich Raab
 */
public class SensorStation {

    /**
     * The url of the sensor station.
     */
    private String url;

    /**
     * The name of the sensor station.
     */
    private String name;

    /**
     * List of {@link Sensor} objects.
     */
    private List<Sensor> sensors;

    /**
     * Getter for {@link #url}.
     * @return The url as {@link String}
     */
    public String getUrl() {

        return url;
    }

    /**
     * Setter for {@link #url}.
     * @param url The url to set
     */
    public void setUrl(String url) {

        this.url = url;
    }

    /**
     * Getter for {@link #name}.
     * @return The name as {@link String}
     */
    public String getName() {

        return name;
    }

    /**
     * Setter for {@link #name}.
     * @param name The name to set
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Getter for {@link #sensors}.
     * @return The sensors as {@link List}
     */
    public List<Sensor> getSensors() {

        return sensors;
    }

    /**
     * Setter for {@link #sensors}.
     * @param sensors The sensors to set
     */
    public void setSensors(List<Sensor> sensors) {

        this.sensors = sensors;
    }
}
