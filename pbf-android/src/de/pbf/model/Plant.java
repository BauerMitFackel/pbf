
package de.pbf.model;

import java.util.ArrayList;
import java.util.List;

import de.pbf.model.sensor.Sensor;

/**
 * Plants, also called green plants, are living organisms of the kingdom Plantae
 * including such multicellular groups as flowering plants, conifers, ferns and
 * mosses, as well as, depending on definition, the green algae.
 * @author Ulrich Raab
 */
public class Plant {

    /**
     * The {@link SensorStation} of this plant.
     */
    private SensorStation mSensorStation;

    /**
     * List of {@link Station} objects.
     */
    private List<Sensor> mSensors;

    /**
     * Returns the {@link SensorStation} of this plant.
     * @return A {@link SensorStation} instance
     */
    public SensorStation getSensorStation() {
        return mSensorStation;
    }

    /**
     * Sets the {@link SensorStation} of this plant.
     * @param pSensorStation The {@link SensorStation} to set
     */
    public void setSensorStation(SensorStation pSensorStation) {
        this.mSensorStation = pSensorStation;
    }

    /**
     * Returns the sensors associated with this plant. This also includes
     * sensors which belong to the {@link SensorStation} associated with this
     * plant.
     * @return The sensors as {@link List}
     */
    public List<Sensor> getSensors() {

        List<Sensor> sensors = new ArrayList<Sensor>();
        sensors.addAll(mSensors);
        sensors.addAll(mSensorStation.getSensors());

        return sensors;
    }

    /**
     * Setter for sensors which are directly associated with this plant.
     * @param pSensors The sensors to set
     */
    public void setSensors(List<Sensor> pSensors) {
        this.mSensors = pSensors;
    }

}
