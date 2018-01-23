package com.sensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * * Created by pydroiddev on 12/16/17.
 * <p>
 * Class is used to show the details of the sensors.
 * We will show the name of the sensors and also show the value of the sensors as and when they got changed.
 *
 * Also we will implement the sensorevent listener so when there is any change in sensor accuracy and sensor data
 * we can capture them using sensoreventlistener.
 */

public class SensorDetailActivity extends AppCompatActivity implements SensorEventListener {

    // textview instance to show sensor value
    private TextView mTxtSensor;
    // textview instance to show sensor name
    private TextView mTxtSensorName;
    // object to get access to sensor manager
    private SensorManager mSensorManager;
    // object to get selected sensor
    private Sensor mCurrentSensor;
    // object to store selected sensor value
    private int mSensorType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // load the xml UI in the screen
        setContentView(R.layout.activity_sensor_details);

        //method to initialize the UI and sensor.
        init();

    }

    /**
     * method to initialize the UI element.
     */
    private void init() {

        // initialize the sensor manager object to get the access of the senors.
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //initialize the textview object in the application.
        mTxtSensor = (TextView) findViewById(R.id.txt_sensor);
        mTxtSensorName = (TextView) findViewById(R.id.txt_sensor_name);
        mTxtSensor.setText("");

        /**
         * we will first check that do we have extras with the intent, if we have,
         * we will get the value of the sensor type
         */
        if (getIntent().getExtras() != null) {

            Bundle bundle = getIntent().getExtras();
            // get the value of sensor type set in the previous application.
            mSensorType = bundle.getInt(Constant.SENSOR_TYPE, -1);

            if (mSensorType != -1) {
                // initialize the sensor with selected sensor type.
                mCurrentSensor = mSensorManager.getDefaultSensor(mSensorType);
                //get the value of the sensor name in the application.
                mTxtSensorName.append(" " + mCurrentSensor.getName());
            }

        }

    }

    /**
     * To get the sensor current and changging value we need to
     * @param sensorEvent
     */

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        StringBuilder sb = new StringBuilder();
        for (float value : sensorEvent.values)
            sb.append(String.valueOf(value)).append(" ");

        // and print these
        mTxtSensor.append(sb.append("\r\n"));

    }

    /**
     * used when sensor accuracy is get changed,
     * but we will not used this here as we want only sensor values.
     * @param sensor
     * @param i
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        // start listening for new values here
        mSensorManager.registerListener(this, mCurrentSensor, SensorManager.SENSOR_DELAY_NORMAL);
        // we can use faster data delays like: SENSOR_DELAY_GAME (20,000 microsecond delay),
        // SENSOR_DELAY_UI (60,000 microsecond delay), or SENSOR_DELAY_FASTEST
    }

    @Override
    public void onPause() {
        super.onPause();

        // never forget to unregister
        mSensorManager.unregisterListener(this);
    }

}
