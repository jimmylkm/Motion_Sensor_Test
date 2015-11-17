package fyp.motionsensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import java.util.ArrayList;

/**
 * Created by Jimmy on 1/11/2015.
 */
public class DataSet {
    private ArrayList<EventData> data;
    private String type;

    public ArrayList<EventData> getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    private class EventData {
        //    timestamp is in nano second since last reboot
        private long timestamp;
        private float values[];

        public float[] getValues() {
            return values;
        }

        public long getTimestamp() {
            return timestamp;
        }


        public EventData(SensorEvent event) {
            this.timestamp = event.timestamp;
            this.values = event.values.clone();
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                type = "Magnetic field";
            else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                type = "Acceleration";
        }

        public String toString() {
            String result = "";
            result += timestamp;
            for (int i = 0; i < 3; i++) {
                result += "|" + values[i];
            }
            return result;
        }
    }

    public void addData(SensorEvent event) {
        data.add(new EventData(event));
    }

    public DataSet(String type) {
        this.type = type;
        data = new ArrayList<>();
    }

    public String printToString() {
        String result = "";
        result += "Timestamp|x-value|y-value|z-value\n";
        for (EventData entry : data) {
            result += entry.toString() + "\n";
        }
        return result;
    }
}
