package com.example.gyroscope_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener sensorEventListener;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        textView = findViewById(R.id.text);







               if (gyroscopeSensor == null){
            Toast.makeText(this,"gyroscope sensor yok! ", Toast.LENGTH_SHORT).show();
            finish();
        }


        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.values[0] < 0.01 && event.values[0]> -0.01){
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);





                }
                else {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
                DecimalFormat df = new DecimalFormat("###.##");
                textView.setText(df.format(event.values[0]*100)+" "+df.format(event.values[1]*100)+" "+df.format(event.values[2]*100));


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(sensorEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}
