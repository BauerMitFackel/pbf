package de.pbf.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import de.pbf.R;
import de.pbf.controller.SensorsAdapter;
import de.pbf.model.SensorStation;

public class SensorStationActivity extends Activity {
    
    private SensorStation sensorStation;
    
    public SensorStationActivity(SensorStation sensorStation) {

        super();
        this.sensorStation = sensorStation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_station_activity);
        
        SensorsAdapter adapter = new SensorsAdapter(this, R.layout.sensor_row, sensorStation.getSensors());
        
        ListView sensorView = (ListView) findViewById(R.id.sensor_station_activity_sensors_list_view);
        sensorView.setAdapter(adapter);
    }
    
    
}
