
package de.pbf.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import de.pbf.R;
import de.pbf.model.SensorStation;
import de.pbf.util.observer.Observer;

/**
 * {@link ListView} adapter for sensor stations.
 * @author Ulrich Raab
 */
public class SensorStationsAdapter extends ArrayAdapter<SensorStation> implements Observer<SensorStationsHolder>{

    /**
     * The current context.
     */
    private Context context;

    /**
     * Resource ID for a layout file to use as row layout.
     */
    private int resourceId;

    /**
     * Constructor.
     * @param context The current context.
     * @param resourceId The resource ID for a layout file to use when instantiating views.
     */
    public SensorStationsAdapter(Context context, int resourceId) {

        super(context, resourceId, SensorStationsHolder.INSTANCE.sensorStations());

        this.context = context;
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {

        ViewHolder holder = null;

        if (row == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            holder.nameView = (TextView) row.findViewById(R.id.sensor_station_row_name_view);
            holder.colorIndicatorView = row.findViewById(R.id.sensor_station_row_color_indicator_view);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        SensorStation sensorStation = getItem(position);
        holder.nameView.setText(sensorStation.getName());

        return row;
    }

    /**
     * View holder class.
     * @author Ulrich Raab
     */
    public static class ViewHolder {

        /**
         * TextView which should display the name of a sensor station.
         */
        private TextView nameView;

        /**
         * View which should display the overall condition.
         */
        @SuppressWarnings("unused")
        private View colorIndicatorView;
    }

    @Override
    public void observableChanged(SensorStationsHolder observable) {
        
        clear();
        addAll(observable.sensorStations());
        
        notifyDataSetInvalidated();
    }
}
