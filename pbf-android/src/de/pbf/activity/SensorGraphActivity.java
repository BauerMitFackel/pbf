package de.pbf.activity;

import com.jjoe64.graphview.GraphView;

import de.pbf.App;
import de.pbf.controller.GraphViewController;
import de.pbf.controller.SensorStationsHolder;
import de.pbf.model.SensorStation;
import de.pbf.model.sensor.Sensor;
import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class SensorGraphActivity extends Activity {
    
    private SensorStation sensorStation;
    private String sensorID;
    private GraphViewController graphViewController;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(App.context());
        setContentView(layout);
        
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("SENSOR_STATION_URL");
        this.sensorStation = SensorStationsHolder.INSTANCE.get(url);
        this.sensorID = bundle.getString("SENSOR_ID");
        
        graphViewController = new GraphViewController();
        GraphView graphView = graphViewController.graphView(sensorStation, sensorID);
        
        layout.addView(graphView);

        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, SensorStationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                
                Bundle bundle = new Bundle();
                bundle.putString("SENSOR_STATION_URL", sensorStation.getUrl());
                intent.putExtras(bundle);
                
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    
    

}
