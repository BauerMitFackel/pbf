
package de.pbf.io;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.Sensor.Builder;
import de.pbf.model.sensor.impl.LightSensor;
import de.pbf.model.sensor.impl.MoistureSensor;
import de.pbf.model.sensor.impl.TemperatureSensor;

/**
 * Custom sensor deserializer for Json.
 * @author Ulrich Raab
 */
public class SensorDeserializer implements JsonDeserializer<Sensor> {

    /**
     * The id key.
     */
    private static final String ID_KEY = "id";

    /**
     * The raw value key.
     */
    private static final String RAW_VALUE_KEY = "value";

    /**
     * The sensor type key.
     */
    private static final String SENSOR_TYPE_KEY = "type";

    /**
     * The moisture sensor type identifier.
     */
    private static final String SENSOR_TYPE_MOISTURE_SENSOR = "moisture_sensor";

    /**
     * The light sensor type identifier.
     */
    private static final String SENSOR_TYPE_LIGHT_SENSOR = "light_sensor";

    /**
     * The temperature sensor type identifier.
     */
    private static final String SENSOR_TYPE_TEMPERATURE_SENSOR = "temperature_sensor";

    @Override
    public Sensor deserialize(JsonElement json, Type type, JsonDeserializationContext context) {

        JsonObject jsonObject = json.getAsJsonObject();

        Sensor.Builder builder = makeBuilder(jsonObject);
        deserialize(jsonObject, builder);

        return builder.build();
    }

    /**
     * Instantiates a builder depending on the given json object.
     * @param jsonObject The json object
     * @return A builder instance
     */
    private Builder makeBuilder(JsonObject jsonObject) {

        String sensorType = jsonObject.get(SENSOR_TYPE_KEY).getAsString();

        if (sensorType.equals(SENSOR_TYPE_MOISTURE_SENSOR)) {
            return new MoistureSensor.Builder();
        }

        if (sensorType.equals(SENSOR_TYPE_LIGHT_SENSOR)) {
            return new LightSensor.Builder();
        }

        if (sensorType.equals(SENSOR_TYPE_TEMPERATURE_SENSOR)) {
            return new TemperatureSensor.Builder();
        }

        return null;
    }

    /**
     * Deserializes the given json object and uses therefore the given builder.
     * @param jsonObject The json object
     * @param builder The builder object
     */
    private void deserialize(JsonObject jsonObject, Builder builder) {

        String id = jsonObject.get(ID_KEY).getAsString();
        float rawValue = jsonObject.get(RAW_VALUE_KEY).getAsFloat();

        builder.id(id);
        builder.rawValue(rawValue);
    }
}
