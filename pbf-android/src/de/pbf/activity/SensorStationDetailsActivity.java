package de.pbf.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

import de.pbf.App;
import de.pbf.R;
import de.pbf.controller.SensorStationsHolder;
import de.pbf.model.SensorStation;

public class SensorStationDetailsActivity extends Activity {
    
    private EditText urlEditText;
    private EditText nameEditText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_station_details_activity);
        
        urlEditText = (EditText) findViewById(R.id.sensor_station_details_activity_url_edittext);
        nameEditText = (EditText) findViewById(R.id.sensor_station_details_activity_name_edittext);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("Sensor station");
        
        getMenuInflater().inflate(R.menu.sensor_station_details_activity_ab_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        
        switch (item.getItemId()) {
            case R.id.sensor_station_details_activity_ab_menu_cancel:
                onBackPressed();                
                return true;
            case R.id.sensor_station_details_activity_ab_menu_accept:
                return onAccept();
                
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean onAccept() {
        
        String url = urlEditText.getText().toString();
        String name = nameEditText.getText().toString();
        
        if (isAccurateUserInput(url, name)) {
            
            SensorStation sensorStation = new SensorStation();
            sensorStation.setName(nameEditText.getText().toString());
            sensorStation.setUrl(urlEditText.getText().toString());
            
            SensorStationsHolder.INSTANCE.add(sensorStation);
            SensorStationsHolder.INSTANCE.informOnChanges();
            
            onBackPressed();
        }
        else {
            Toast.makeText(App.context(), "Invalid input", Toast.LENGTH_LONG).show();
        }
        
        return true;
    }
    
    private boolean isAccurateUserInput(String url, String name) {
        
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            Log.e("SSDA", "Invalid url " + url, e);
            return false;
        }
        
        if (name == null || name.isEmpty()) {
            Log.e("SSDA", "Invalid name " + name);
            return false;
        }
        
        return true;
    }
}
