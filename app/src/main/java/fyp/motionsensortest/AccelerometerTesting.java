package fyp.motionsensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class AccelerometerTesting extends AppCompatActivity implements SensorEventListener {
    private DataSet accReading = new DataSet("Accelerometer");
    private DataSet magReading = new DataSet("Magnetic Field");

    private int startIndex = 0;
    private int endIndex = 0;


    private SensorManager sensorManager;
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_testing);
        view = (TextView) findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accelerometer_testing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            synchronized (event) {
                accReading.addData(event);
            }
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            synchronized (event) {
                magReading.addData(event);
            }
        }
    }

//    public void processData(int startIndex, int endIndex) {
////        Taking first value as gravity benchmark
//        float[] gravity = accReading.get(startIndex).getValues();
//        long lastDataTime = accReading.get(startIndex).getTimestamp();
//        final double azimuthOffset = getOriInc(gravity, magReading.get(startIndex).getValues())[0];
//
//        for (startIndex++; startIndex <= endIndex; startIndex++) {
//            final long lapseTime = accReading.get(startIndex).getTimestamp() - lastDataTime;
//
//            float[] linAccVal = accReading.get(startIndex).getValues();
////            get linear acceleration values (raw - gravity)
//            for (int i = 0; i < 3; i++) {
//                linAccVal[i] -= gravity[i];
//            }
//
////            get raw magnetic field value
//            final float[] magVal = magReading.get(startIndex).getValues();
//
////            [0] rotation from North (degree), [1] cos(pitch angel)
//            final double[] oriInc = getOriInc(gravity, magVal);
//
////             compute total horizontal acceleration
//            final double totalAcceleration = (linAccVal[2] + linAccVal[1]) * oriInc[1];
//
////            determine direction
////            TODO: function for calculation
//
//
//        }
//    }

//    TODO: getRelative degree (Value + offset / 360 + value + offset)

//    public void getAccelerometer(SensorEvent event) {
//        if (graVal == null) {
//            graVal = event.values.clone();
//            for (int index = 0; index < graVal.length; index++) {
//                Log.i("gravity", "gravity[" + index + "]:" + graVal[index]);
//            }
//        }
//
////        Time to get orientation and inclination (Z,X,Y)
////        Z gives direction, Y gives inclination
//        if (mag == null) // Wait till magnetic field data is collected
//            return;
//
//        float[] oriInc = getOriInc(graVal, mag);
//
//
////        Direction the phone heading
//        double dir = -Math.toDegrees(oriInc[0]); //East: +90, West: -90
//        Log.i("Ori", "Bearing:" + dir);
//
//
////        Forward acceleration resFac
//        if (inc == 0.0)
//            inc = oriInc[1];
////        Log.i("Ori", "Inc: " + inc);
//        Log.i("Ori", "Inc: " + Math.toDegrees(inc));
////        Correct inclination value
//        if (inc > (Constants.LOWBOUND) && inc < (Constants.UPPBOUND)) {
//            if (inc < 0)
//                inc = 0;
//            if (inc > (Math.PI / 2))
//                inc = Math.PI / 2;
//        } else {
////            inclination is invalid, ignore motion
//            Log.i("Ori", "Inc: upper" + Constants.LOWBOUND);
//            Log.i("Ori", "Inc: upper" + Constants.UPPBOUND);
//            Log.i("Ori", "Inc: Filtered" + inc);
//            return;
//        }
//
//
////        Component in X,Y,Z (Forward acceleration: Z (negative))
//        double[] linearAcceleration = new double[3];
//        linearAcceleration[0] = event.values[0] - graVal[0];
//        linearAcceleration[1] = event.values[1] - graVal[1];
//        linearAcceleration[2] = event.values[2] - graVal[2];
//
//        double linAcc_y = linearAcceleration[1] * Math.cos(inc);
//        double linAcc_z = -(linearAcceleration[2] * Math.cos(inc));
//        double linAcc = linAcc_y + linAcc_z;
//
//        Log.i("LinAcc", "fac_y:" + Math.cos(inc));
//        Log.i("LinAcc", "fac_z:" + Math.cos((Math.PI / 2) - inc));
//        Log.i("LinAcc", "y:" + linAcc_y);
//        Log.i("LinAcc", "z:" + linAcc_z);
//        Log.i("LinAcc", "total:" + linAcc);
//
//        final double lapseTime = (event.timestamp - lastUpdate) * Constants.NS2S;
//        Log.i("Freq", lapseTime + "");
//        lastUpdate = event.timestamp;
//
//        if (lapseTime < 1) { //Make sure event are successive;
//
////            Determine direction and return Constants direction
//            final double direction = determineDirection(dir);
//            Log.i("Dir", "Current Dir is: " + direction);
//            if (lastDirection == 360) {
//                lastDirection = direction;
//                velocity = linAcc * lapseTime;
//                displacement = velocity * lapseTime;
//
//            } else if (lastDirection == direction) {
////                TODO: Update heap acceleration and displacement
////                These are the value calculated based on current value
//                velocity += linAcc * lapseTime;
//                displacement += velocity * lapseTime;
//
//            } else {
////                TODO: Write current displacement and direction data to database, time
//                PathSegment segment = new PathSegment(displacement, lastDirection, lastDirChange,
//                        event.timestamp, startCord);
//                resultArray.add(segment);
//                lastDirection = direction;
//                lastDirChange = event.timestamp;
////                Restart calculation for the next direction
//                velocity = linAcc * lapseTime;
//                displacement = velocity * lapseTime;
//                graVal = null;
//            }
//        }
//    }

    public double[] getOriInc(float[] gravity, float[] geomagnetic) {
        float[] rotMat = new float[9];
        if (!SensorManager.getRotationMatrix(rotMat, null, gravity, geomagnetic)) {
            Log.e("getInclinDegree", "Fail to get Rotation Matrix");
        }

        float[] ori = new float[3];
        SensorManager.getOrientation(rotMat, ori);

        for (int index = 0; index < ori.length; index++) {
            Log.i("getInclinDegree", "result[" + index + "]: " + ori[index]);
        }

        double[] result = new double[2];
        result[0] = Math.toDegrees(ori[0]);
        result[1] = Math.cos(ori[1]);
        return result;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//                , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
//      Testing1: output all event data to log file

    }


    public void resumeSensor(View view) {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                , SensorManager.SENSOR_DELAY_GAME);

//        feed to getRotationMatrix
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
                , SensorManager.SENSOR_DELAY_GAME);

        TextView t = (TextView) findViewById(R.id.textView2);
        t.setText("Resuming Sensor");
    }

    public void pauseSensor(View view) {
        sensorManager.unregisterListener(this);
        TextView t = (TextView) findViewById(R.id.textView2);
        t.setText("Pausing Sensor");
        File file = new File("/sdcard/" + Constants.MAGLOG);
        if (!file.delete())
            Log.i("Delete", "MagLog delete failed");
        file = new File("/sdcard/" + Constants.ACCLOG);
        if (!file.delete())
            Log.i("Delete", "AccLog delete failed");
//        TODO: Log the data
        appendLog(magReading.printToString(), Constants.MAGLOG);
        appendLog(accReading.printToString(), Constants.ACCLOG);

        accReading = new DataSet("Accelerometer");
        magReading = new DataSet("Magnetic Field");
    }

    public void appendLog(String text, String filename) {
        File logFile = new File("sdcard/" + filename);
//        File logFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/test.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            //BufferedWriter for performance, set to true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    private double determineDirection(double direction){
//
//    }


}
