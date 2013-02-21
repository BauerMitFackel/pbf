
package de.pbf.mock;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
        sensorStation.setName("Arduino");
        
        GregorianCalendar calendar = new GregorianCalendar();
        
        calendar.set(2013, 02, 20, 15, 00);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 15, 15);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 15, 30);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 15, 45);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 16, 00);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 16, 15);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 16, 30);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 16, 45);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 17, 00);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 17, 15);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 17, 30);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 17, 45);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 18, 00);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 18, 15);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 18, 30);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 18, 45);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 19, 00);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 19, 15);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 19, 30);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 19, 45);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 20, 00);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 20, 15);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 20, 30);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 20, 45);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 21, 00);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 21, 15);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 21, 30);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 21, 45);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        calendar.set(2013, 02, 20, 22, 00);
        sensorStation.sensorsOverTime().put(calendar.getTime(), makeSensors());
        
        List<SensorStation> stations = new ArrayList<SensorStation>();
        stations.add(sensorStation);
        
        return stations;
    }
    
    
    private static List<Sensor> makeSensors(){
        
        // Sensor 1
        
        MoistureSensor.Builder msBuilder = new MoistureSensor.Builder();
        msBuilder.id("S1");
        msBuilder.rawValue((float) (Math.random() * 1024));
        
        MoistureSensor sensor1 = msBuilder.build();
        
        // Sensor 2
        
        msBuilder = new MoistureSensor.Builder();
        msBuilder.id("S2");
        msBuilder.rawValue((float) (Math.random() * 1024));

        MoistureSensor sensor2 = msBuilder.build();
        
        // Sensor 3
        
        LightSensor.Builder lsBuilder = new LightSensor.Builder();
        lsBuilder.id("S3");
        lsBuilder.rawValue((float) (Math.random() * 1024));
        
        LightSensor sensor3 = lsBuilder.build();
        
        // Sensor 4
        
        TemperatureSensor.Builder tsBuilder = new TemperatureSensor.Builder();
        tsBuilder.id("S4");
        tsBuilder.rawValue((float) (Math.random() * 30));
        
        TemperatureSensor sensor4 = tsBuilder.build();
        
        
        
        List<Sensor> sensors = new ArrayList<Sensor>();
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);
        sensors.add(sensor4);
        
        return sensors;
    }
}
