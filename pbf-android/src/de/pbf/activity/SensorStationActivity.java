
package de.pbf.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import de.pbf.R;
import de.pbf.controller.SensorStationsHolder;
import de.pbf.controller.SensorsAdapter;
import de.pbf.model.SensorStation;
import de.pbf.model.sensor.Sensor;
import de.pbf.model.sensor.impl.LightDurationSensor;
import de.pbf.model.sensor.impl.LightSensor;
import de.pbf.model.sensor.impl.MoistureSensor;

public class SensorStationActivity extends Activity {

    private SensorStation sensorStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_station_activity);

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("SENSOR_STATION_URL");
        this.sensorStation = SensorStationsHolder.INSTANCE.get(url);
        
        //Build LightDuration "Sensor"
        LightDurationSensor.Builder ldBuilder = new LightDurationSensor.Builder();
        ldBuilder.id("L1");
        ldBuilder.rawValue(0);
        
        LightDurationSensor lightDuration = ldBuilder.build();
        lightDuration.setlightDuration(sensorStation.lightDuration());
        
        // add LightDuration to sensors
        List<Sensor> sensors = new ArrayList<Sensor>();
        
        for(Sensor sensor: sensorStation.sensors()){
            sensors.add(sensor);
        }
        sensors.add(lightDuration);
        
        SensorsAdapter adapter = new SensorsAdapter(this, R.layout.sensor_row, sensors);

        ListView sensorView = (ListView) findViewById(R.id.sensor_station_activity_sensors_list_view);
        sensorView.setAdapter(adapter);
        
        sensorView.setOnItemClickListener(makeOnItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(sensorStation.getName());

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                
                onBackPressed();
                return true;
                
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private AdapterView.OnItemClickListener makeOnItemClickListener() {
        
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                
                List<Sensor> sensors = sensorStation.sensors();
                Sensor sensor = null;
                LightSensor ls = null;
                
                if(position < sensors.size()) {
                    
                    sensor = sensors.get(position);
                    

                    
   
                } else {
                    
                    // if LightDuration list item was clicked, show LightSensor chart instead
                    for(Sensor s: sensors){
                        
                        if(s instanceof LightSensor) sensor = s;
                    }
                    
                }
                
                Intent intent = new Intent(getApplicationContext(), SensorGraphActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("SENSOR_STATION_URL", sensorStation.getUrl());
                bundle.putString("SENSOR_ID", sensor.id());
                
                intent.putExtras(bundle);
                
                startActivity(intent);
                //finish();
                

            }
        };
        
        return listener;
    }
}
