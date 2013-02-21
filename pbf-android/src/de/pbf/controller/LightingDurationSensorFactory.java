package de.pbf.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.impl.LightingDurationSensor;
import de.pbf.util.LightingDurationCalculator;

public class LightingDurationSensorFactory {
    
    public LightingDurationSensor makeLightingDurationSensor(Map<Date, List<Sensor>> sensorsOverTime) {
        
        LightingDurationCalculator calculator = LightingDurationCalculator.INSTANCE;
        long lightingDuration = calculator.calculateLightingDuration(sensorsOverTime, TimeUnit.MINUTES);
        
        LightingDurationSensor.Builder builder = new LightingDurationSensor.Builder();
        builder.id(UUID.randomUUID().toString());
        builder.lightingDuration(lightingDuration);
        
        return builder.build();
    }
}
