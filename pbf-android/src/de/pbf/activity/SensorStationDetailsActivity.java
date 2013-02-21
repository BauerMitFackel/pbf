package de.pbf.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import de.pbf.R;

public class SensorStationDetailsActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_station_details_activity);
        
        /*
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(yourEditText, InputMethodManager.SHOW_IMPLICIT);
        */
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
        // TODO Auto-generated method stub
        return super.onOptionsItemSelected(item);
    }
    
    
}
