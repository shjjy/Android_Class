package lesson.android.yangchang.ls8.serv;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import lesson.android.yangchang.demo.R;

public class ServActivity extends ActionBarActivity {
    private Button playBtn, stopBtn;
    private Button startServiceBtn, stopServiceBtn;
    private Button listSensorBtn, sensorValueBtn;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv);
        playBtn = (Button) findViewById(R.id.servPlay);
        final MediaPlayer mp = MediaPlayer.create(ServActivity.this, R.raw.music);
        playBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });

        stopBtn = (Button) findViewById(R.id.servStop);
        stopBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });

        startServiceBtn = (Button) findViewById(R.id.servicePlay);
        startServiceBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServActivity.this, MyService.class);
                startService(intent);
            }
        });

        stopServiceBtn = (Button) findViewById(R.id.serviceStop);
        stopServiceBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServActivity.this, MyService.class);
                stopService(intent);
            }
        });

        listSensorBtn = (Button) findViewById(R.id.sensor);
        listSensorBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm = (SensorManager) getSystemService(SENSOR_SERVICE);
                List<Sensor> list = sm.getSensorList(Sensor.TYPE_ALL);
                TextView textView = (TextView) findViewById(R.id.sensorText);
                StringBuffer sb = new StringBuffer("size ").append(list.size());
                for(Sensor sensor : list){
                    sb.append(" , ").append(sensor.getName());
                }
                textView.setText(sb.toString());
            }
        });

        sensorValueBtn = (Button) findViewById(R.id.sensorValue);
        sensorValueBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm = (SensorManager) getSystemService(SENSOR_SERVICE);
                List<Sensor> list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
                if(list.size() > 0) {
                    sm.registerListener(sensorEventListener, list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
                }
            }
        });
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            TextView textView = (TextView) findViewById(R.id.sensorValueText);
            StringBuffer sb = new StringBuffer("start ");
            for(float value:values){
                sb.append(" , ").append(value);
            }
            textView.setText(sb.toString());
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
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
}
