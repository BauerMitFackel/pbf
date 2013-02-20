
package de.pbf.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import de.pbf.R;
import de.pbf.model.sensor.Sensor;

/**
 * {@link ListView} adapter for sensors.
 * @author Ulrich Raab
 */
public class SensorsAdapter extends ArrayAdapter<Sensor> {

    /**
     * The current context.
     */
    private Context context;

    /**
     * Resource ID for a layout file to use as row layout.
     */
    private int resourceId;

    /**
     * List of sensor stations.
     */
    private List<Sensor> sensors;

    /**
     * Constructor.
     * @param context The current context.
     * @param resourceId The resource ID for a layout file to use when instantiating views.
     * @param sensors The sensors to represent in the ListView.
     */
    public SensorsAdapter(Context context, int resourceId, List<Sensor> sensors) {

        super(context, resourceId, sensors);

        this.context = context;
        this.resourceId = resourceId;
        this.sensors = sensors;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {

        ViewHolder holder = null;

        if (row == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            holder.nameView = (TextView) row.findViewById(R.id.sensor_row_name_view);
            holder.valueView = (TextView) row.findViewById(R.id.sensor_row_value_view);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        Sensor sensor = sensors.get(position);
        holder.nameView.setText(sensor.label());
        
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumFractionDigits(1);
        numberFormat.setMaximumFractionDigits(1);
        
        String valueText = numberFormat.format(sensor.value()) + " " + sensor.unit().label();
        holder.valueView.setText(valueText);

        return row;
    }

    /**
     * View holder class.
     * @author Ulrich Raab
     */
    private static class ViewHolder {

        /**
         * TextView which should display the name of a sensor.
         */
        private TextView nameView;

        /**
         * TextView which should display the value of a sensor.
         */
        private TextView valueView;
    }
}
