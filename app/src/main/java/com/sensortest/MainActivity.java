package com.sensortest;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Main activity used to list all the sensors list available in the mobile phone
 * of the user.
 */
public class MainActivity extends AppCompatActivity {

    //reference to listview UI element
    private ListView mLstSensors;

    // reference to the sensorManager
    private SensorManager mSensorManager;

    // get the sensors list and store in the list
    private List<Sensor> mSensorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //load the UI xml file in the screen
        setContentView(R.layout.activity_main);

        // initialize the required
        init();

    }

    /**
     * initialize the UI and sensors manager and get the list of sensors and show them in the listview.
     */
    private void init() {

        //initialize listview UI element with xml mapping
        mLstSensors = (ListView) findViewById(R.id.lst_sensors);

        // initialize sensor manager to get the access of the sensors
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // get the list of sensors available in the device
        mSensorsList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

//        final ArrayAdapter<Sensor> listAdapter = new ArrayAdapter<Sensor>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, mSensorsList.toArray(new Sensor[mSensorsList.size()]));

        // adapter class to show and feed data in the listview.
        final SensorAdapter listAdapter = new SensorAdapter(this, mSensorsList);
        //sets adapter object in the listview.
        mLstSensors.setAdapter(listAdapter);
    }

}
