package fyp.motionsensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import java.util.ArrayList;

/**
 * Created by Jimmy on 1/11/2015.
 */
public class DataSet {
    private ArrayList<EventData> rawData;
    private ArrayList<EventData> smoothedData;
    private String type;


    public ArrayList<EventData> getRawData() {
        return rawData;
    }

    public String getType() {
        return type;
    }

    public void addData(SensorEvent event) {
        rawData.add(new EventData(event));
//        Has to call smooth rawData before the rawData can be used
        smoothedData.add(new EventData(event));
    }

    public DataSet(String type) {
        this.type = type;
        rawData = new ArrayList<>();
        smoothedData = new ArrayList<>();
    }

    public String printToString() {
        String result = "";
        result += "Timestamp|x-value|y-value|z-value\n";
        for (EventData entry : rawData) {
            result += entry.toString() + "\n";
        }
        return result;
    }

    private float[] lowPassFilter(float[] input, float[] output) {
        if (output == null) return input;
        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + Constants.ALPHA * (input[i] - output[i]);
        }
        return output;
    }

    public void smoothData() {
        float[] tmp = null;
        for (int i = 0; i < smoothedData.size(); i++) {
            smoothedData.get(i).values = lowPassFilter(smoothedData.get(i).values, tmp);
        }
    }

    //TODO: implement data calculations
    private class EventData {
        //    timestamp is in nano second since last reboot
        public long timestamp;
        public float values[];

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
}
