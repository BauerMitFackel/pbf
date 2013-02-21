package de.pbf.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;

import de.pbf.R;
import de.pbf.controller.GraphViewController;
import de.pbf.controller.SensorStationsHolder;
import de.pbf.model.SensorStation;

public class SensorGraphActivity extends Activity {
    
    private SensorStation sensorStation;
    private String sensorID;
    private GraphViewController graphViewController;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.graph_view_activity);
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph_view_linear_layout);
                
                
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("SENSOR_STATION_URL");
        this.sensorStation = SensorStationsHolder.INSTANCE.get(url);
        this.sensorID = bundle.getString("SENSOR_ID");
        
        graphViewController = new GraphViewController();
        GraphView graphView = graphViewController.graphView(sensorStation, sensorID);
        
        layout.addView(graphView);

        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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
}
