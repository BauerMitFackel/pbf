
package de.pbf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import de.pbf.R;
import de.pbf.controller.SensorStationsAdapter;
import de.pbf.controller.SensorStationsHolder;
import de.pbf.io.SensorStationWebserviceController;
import de.pbf.model.SensorStation;

/**
 * MainActivity class.
 * @author Ulrich Raab
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
        if (SensorStationsHolder.INSTANCE.sensorStations().isEmpty()) {
            SensorStation sensorStation = new SensorStation();
            sensorStation.setName("Arduino Testsystem");
            sensorStation.setUrl("http://192.168.178.59");
            SensorStationsHolder.INSTANCE.add(sensorStation);
        }
        
        executeBgThread();
        
        setContentView(R.layout.main_activity);

        SensorStationsAdapter adapter = new SensorStationsAdapter(this, R.layout.sensor_station_row);

        ListView sensorStationsView = (ListView) findViewById(R.id.main_activity_sensor_stations_list_view);
        sensorStationsView.setAdapter(adapter);
        
        sensorStationsView.setOnItemClickListener(makeOnItemClickListener());
    }
    
    private AdapterView.OnItemClickListener makeOnItemClickListener() {
        
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                
                SensorStation sensorStation = SensorStationsHolder.INSTANCE.sensorStations().get(position);
                
                Intent intent = new Intent(getApplicationContext(), SensorStationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("SENSOR_STATION_URL", sensorStation.getUrl());
                
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
        
        return listener;
    }
    
    private void executeBgThread() {
        
        SensorStationWebserviceController sswc = new SensorStationWebserviceController(SensorStationsHolder.INSTANCE.sensorStations());
        sswc.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_activity_ab_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       
        switch (item.getItemId()) {
            case R.id.main_activity_ab_menu_add:
                
                Intent intent = new Intent(this, SensorStationDetailsActivity.class);
                startActivity(intent);
                
                return true;
                
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
