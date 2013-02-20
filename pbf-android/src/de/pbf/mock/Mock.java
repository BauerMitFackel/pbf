
package de.pbf.mock;

import java.util.ArrayList;
import java.util.List;

import de.pbf.model.Plant;
import de.pbf.model.SensorStation;
import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.impl.LightSensor;
import de.pbf.model.sensor.impl.MoistureSensor;
import de.pbf.model.sensor.impl.TemperatureSensor;

/**
 * Mock.
 * @author Ulrich Raab
 */
public final class Mock {

    private static final float TEMPERATURE_SENSOR_RAW_VALUE = 20.0F;
    private static final float LIGHT_SENSOR_RAW_VALUE = 352.3F;
    private static final float MOISTURE_SENSOR_RAW_VALUE_1 = 754.0F;
    private static final float MOISTURE_SENSOR_RAW_VALUE_2 = 1.0F;

    /**
     * Mock class.
     */
    private Mock() {

    }

    /**
     * Make method for sensor stations.
     * @return the sensor stations
     */
    public static List<SensorStation> makeSensorStations() {

        SensorStation sensorStation = new SensorStation();
        sensorStation.setUrl("http://192.168.0.1");
        sensorStation.setName("Wohnzimmer");
        
        sensorStation.setSensors(makeSensors());
                        
        List<SensorStation> stations = new ArrayList<SensorStation>();
        stations.add(sensorStation);

        return stations;
    }
    
    
    private static List<Sensor> makeSensors(){
        
        // Sensor 1
        
        MoistureSensor.Builder msBuilder = new MoistureSensor.Builder();
        msBuilder.id("S1");
        msBuilder.rawValue(MOISTURE_SENSOR_RAW_VALUE_1);
        
        MoistureSensor sensor1 = msBuilder.build();
        
        // Sensor 2
        
        msBuilder = new MoistureSensor.Builder();
        msBuilder.id("S2");
        msBuilder.rawValue(MOISTURE_SENSOR_RAW_VALUE_2);

        MoistureSensor sensor2 = msBuilder.build();
        
        // Sensor 3
        
        LightSensor.Builder lsBuilder = new LightSensor.Builder();
        lsBuilder.id("S3");
        lsBuilder.rawValue(LIGHT_SENSOR_RAW_VALUE);
        
        LightSensor sensor3 = lsBuilder.build();
        
        // Sensor 4
        
        TemperatureSensor.Builder tsBuilder = new TemperatureSensor.Builder();
        tsBuilder.id("S4");
        tsBuilder.rawValue(TEMPERATURE_SENSOR_RAW_VALUE);
        
        TemperatureSensor sensor4 = tsBuilder.build();
        
        
        
        List<Sensor> sensors = new ArrayList<Sensor>();
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);
        sensors.add(sensor4);
        
        return sensors;
    }
    

    /**
     * Make method for plants.
     * @param pSensorStation the sensor station
     * @return the plant list
     */
    private static List<Plant> makePlants(SensorStation pSensorStation) {

        Plant plant1 = new Plant();
        plant1.setSensorStation(pSensorStation);

        Plant plant2 = new Plant();
        plant2.setSensorStation(pSensorStation);

        List<Plant> plants = new ArrayList<Plant>();
        plants.add(plant1);
        plants.add(plant2);

        return plants;
    }
}
