
package de.pbf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import de.pbf.R;
import de.pbf.controller.SensorStationsAdapter;
import de.pbf.controller.SensorStationsHolder;
import de.pbf.io.AsyncWebserviceRequest;
import de.pbf.io.SensorDeserializer;
import de.pbf.mock.Mock;
import de.pbf.model.SensorStation;
import de.pbf.model.sensor.Sensor;

/**
 * MainActivity class.
 * @author Ulrich Raab
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
        setupData();
        
        
        setContentView(R.layout.main_activity);

        SensorStationsAdapter adapter = new SensorStationsAdapter(this, R.layout.sensor_station_row);

        ListView sensorStationsView = (ListView) findViewById(R.id.main_activity_sensor_stations_list_view);
        sensorStationsView.setAdapter(adapter);
        
        sensorStationsView.setOnItemClickListener(makeOnItemClickListener());

        // doExport();
        //doImport();
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
                //finish();
            }
        };
        
        return listener;
    }
    
    
    private void setupData() {
        
        List<SensorStation> sensorStations = Mock.makeSensorStations();
        
        for (SensorStation sensorStation : sensorStations) {
            SensorStationsHolder.INSTANCE.add(sensorStation);
        }
    }
    

    private void doImport() {

        AsyncWebserviceRequest.Listener listener = new AsyncWebserviceRequest.Listener() {

            @Override
            public void executionFinished(String response) {

                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(Sensor.class, new SensorDeserializer());

                Gson gson = builder.create();

                TypeToken<List<Sensor>> typeToken = new TypeToken<List<Sensor>>() {};
                Type type = (Type) typeToken.getType();

                List<Sensor> sensors = gson.fromJson(response, type);
            }
        };

        AsyncWebserviceRequest asyncWebserviceRequest = new AsyncWebserviceRequest();
        asyncWebserviceRequest.setListener(listener);

        asyncWebserviceRequest.execute("http://192.168.178.59");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
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
