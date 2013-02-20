
package de.pbf.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import de.pbf.R;
import de.pbf.controller.SensorStationsHolder;
import de.pbf.controller.SensorsAdapter;
import de.pbf.model.SensorStation;

public class SensorStationActivity extends Activity {

    private SensorStation sensorStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_station_activity);

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("SENSOR_STATION_URL");
        this.sensorStation = SensorStationsHolder.INSTANCE.get(url);

        SensorsAdapter adapter = new SensorsAdapter(this, R.layout.sensor_row, sensorStation.getSensors());

        ListView sensorView = (ListView) findViewById(R.id.sensor_station_activity_sensors_list_view);
        sensorView.setAdapter(adapter);
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
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        // TODO Auto-generated method stub

    }

}
