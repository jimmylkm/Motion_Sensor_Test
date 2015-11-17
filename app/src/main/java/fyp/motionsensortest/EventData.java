package fyp.motionsensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Jimmy on 1/11/2015.
 */
public class EventData {
//    timestamp is in nano second since last reboot
    private long timestamp;
    private float values[];
    private String type;


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
        result += type;
        result += "|" + timestamp;
        for (int i = 0; i < 3; i++) {
            result += "|" + values[i];
        }
        return result;
    }

}
