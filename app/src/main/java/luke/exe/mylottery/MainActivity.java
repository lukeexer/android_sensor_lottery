package luke.exe.mylottery;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager mSensorManager;
    float xLateral = 0;
    float yLateral = 0;
    float zLateral = 0;
    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.number);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//        SensorManager mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
//
//        for (Sensor s : sensorList) {
//            Log.d("Sensor", "Name=" + s.getName());
//            Log.d("Sensor", "Vendor=" + s.getVendor());
//            Log.d("Sensor", "Version=" + s.getVersion());
//            Log.d("Sensor", "MaximumRange=" + s.getMaximumRange());
//            Log.d("Sensor", "Power=" + s.getPower());
//            Log.d("Sensor", "Type=" + s.getType());
//        }
    }

    final SensorEventListener myAccelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float xVar = Math.abs(xLateral - event.values[0]);
            float yVar = Math.abs(yLateral - event.values[1]);
            float zVar = Math.abs(zLateral - event.values[2]);
            if (xVar > 10 || yVar > 10 || zVar > 10) {
                long randNum = Math.round(Math.random() * 10000) % 46 + 1;
                number.setText(Long.toString(randNum));
            }
            xLateral = event.values[0];
            yLateral = event.values[1];
            zLateral = event.values[2];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void onResume() {
        mSensorManager.registerListener(myAccelerometerListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    public void onPause() {
        mSensorManager.unregisterListener(myAccelerometerListener);
        super.onPause();
    }
}