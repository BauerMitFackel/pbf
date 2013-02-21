package de.pbf.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import de.pbf.App;
import de.pbf.model.SensorStation;
import de.pbf.model.sensor.Sensor;

public class GraphViewController {

    /**
     * Build a GraphView
     * 
     * @param sensorStation
     *            with contains the sensor
     * @param id
     *            of the specific sensor
     * @return a GraphView for one sensor with the id
     */
    public GraphView graphView(SensorStation sensorStation, String id) {

        Map<Date, List<Sensor>> sensorsMap = new HashMap<Date, List<Sensor>>();
        sensorsMap = sensorStation.sensorsOverTime();
        Sensor sensor = null;
        
        for(Sensor s : sensorStation.sensors()){
            if(s.id().equals(id)){
                sensor = s;
                break;
            }
        }

        GraphView gv = new LineGraphView(App.context(), sensorStation.getName() + "-" + sensor.label());

        Map<Date, Sensor> sensorMap = specificSensorMap(sensorsMap, id);

        GraphViewSeries gvs = graphViewSeries(sensorMap);
        gv.addSeries(gvs);
        
        setViewParams(gv);
        
        return gv;
    }

    /**
     * Build a GraphView
     * 
     * @param sensorStation
     * @return a GraphView for all Sensors of sensorStation
     */
    public GraphView graphView(SensorStation sensorStation) {

        Map<Date, List<Sensor>> sensorsMap = new HashMap<Date, List<Sensor>>();
        GraphView gv = new LineGraphView(App.context(), sensorStation.getName() + "-Graph");
        sensorsMap = sensorStation.sensorsOverTime();

        Map<String, Map<Date, Sensor>> sensorMaps = sensorMaps(sensorsMap);
        
        List<GraphViewSeries> graphViewSeriesList = graphViewSeriesList(sensorMaps);
        
        
        for (GraphViewSeries gvs : graphViewSeriesList) {

            gv.addSeries(gvs);
        }
        
        setViewParams(gv);
        
        return gv;
    }
    
    private void setViewParams(GraphView graphView){
        
        graphView.setScrollable(true);
        graphView.setScalable(true);
        
    }

    /**
     * splitt a sensorsMap in a List with a map each Sensor
     * 
     * @param sensorMap
     * @return a list witch contains a map for each Sensor(by id)
     */
    private Map<String, Map<Date, Sensor>> sensorMaps(Map<Date, List<Sensor>> sensorsMap) {

        Map<String, Map<Date, Sensor>> sensorMaps = new HashMap<String, Map<Date, Sensor>>();

        Iterator<?> it = sensorsMap.entrySet().iterator();
        while (it.hasNext()) {

            Map.Entry<Date, List<Sensor>> pair = (Entry<Date, List<Sensor>>) it.next();

            for (Sensor sensor : pair.getValue()) {
                
                String id = sensor.id();

                if (sensorMaps.containsKey(id)) {
                    sensorMaps.get(id).put(pair.getKey(), sensor);
                } else {
                    
                    Map<Date, Sensor> map = new HashMap<Date, Sensor>();
                    map.put(pair.getKey(), sensor);
                    sensorMaps.put(id, map);
                }
            }

        }

        return sensorMaps;
    }

    /**
     * Select all entries of one sensor with id in a sensorMap
     * 
     * @param sensorMap
     * @param id
     *            of a Sensor
     * @return a sensormap for one Sensor with the id
     */
    private Map<Date, Sensor> specificSensorMap(Map<Date, List<Sensor>> sensorsMap, String id) {

        
        Map<Date, Sensor> sensorMap = new HashMap<Date, Sensor>();
        
        Iterator<?> it = sensorsMap.entrySet().iterator();
        
        while (it.hasNext()) {

            Map.Entry<Date, List<Sensor>> pair = (Entry<Date, List<Sensor>>) it.next();

            for (Sensor sensor : pair.getValue()) {
                
                if(id.equals(sensor.id())){
                    
                    sensorMap.put(pair.getKey(), sensor);
                    
                }
                
            }

        }
        
        return sensorMap;

    }
    
    

    private GraphViewSeries graphViewSeries(Map<Date, Sensor> sensorMap) {
        
        
        List<GraphViewData> gvdList = new ArrayList<GraphView.GraphViewData>();
        
        Set<Date> keyset = sensorMap.keySet();
        List<Date> keys = new ArrayList<Date>(keyset);
        Collections.sort(keys);
        
        for(int i = (keys.size()-1); i >= 0; i--) {
              
                          
              long date = keys.get(i).getTime();
              long value = (long)sensorMap.get(keys.get(i)).value();
              
              GraphViewData gvd = new GraphViewData(date, value);
              
              gvdList.add(gvd);
 
          }
        
        
        GraphViewData[] gvdArray = new GraphViewData[gvdList.size()];
        gvdArray = gvdList.toArray(gvdArray);
        
        GraphViewSeries gvs = new GraphViewSeries(gvdArray);
        
        return gvs;
    }
    
    
    
    private List<GraphViewSeries> graphViewSeriesList(Map<String, Map<Date, Sensor>> sensorMaps) {

        List<GraphViewSeries> graphViewSeriesList = new ArrayList<GraphViewSeries>();
                        
        Iterator<?> it = sensorMaps.entrySet().iterator();
        while (it.hasNext()) {

            Map.Entry<String, Map<Date, Sensor>> pair = (Entry<String, Map<Date, Sensor>>) it.next();
                            
            graphViewSeriesList.add(graphViewSeries(pair.getValue()));
                
        }
                
        return graphViewSeriesList;
    }
    

}
