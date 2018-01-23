package com.sensortest;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * * Created by pydroiddev on 12/16/17.
 *
 * Adapter class will show the data in the list.
 */

public class SensorAdapter extends BaseAdapter {

    //sensors list instance
    private List<Sensor> mSensorList;
    // inflator instant to inflate the rows of the listview
    private LayoutInflater inflater;
    // context of the activity
    private Context mContext;


    /**
     * constructor of the class to initialize the instances.
     * @param context
     * @param sensorsList
     */
    public SensorAdapter(Context context, List<Sensor> sensorsList) {
        mContext = context;
        this.mSensorList = sensorsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    /**
     * method used to get each row view and feed the data in that row
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        // initialize the view for each row
        view = inflater.inflate(R.layout.adapter_sensor, viewGroup, false);

        // textview instance to show sensor name in the list.
        TextView txtName = (TextView) view.findViewById(R.id.txt_name);
        // button instance to start sensor data screen.
        Button btnShowData = (Button) view.findViewById(R.id.btn_show_data);

        // we will check we have sensor value and sensor name value is null or not,
        // if null then we will not load data in the screen
        if (mSensorList.get(i) != null && mSensorList.get(i).getName() != null) {
            txtName.setText(mSensorList.get(i).getName());
        }

        // click listener of the button to show the data of the selected sensor
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the sensor which is selected for showing data
                Sensor sensor = (Sensor) mSensorList.get(i);

                // bundle object to pass data from one activity to another activity.
                Bundle bundle = new Bundle();
                // put the int value of the sensor type and pass to the next activity.
                bundle.putInt(Constant.SENSOR_TYPE, sensor.getType());

                // below line of code is used to start activity with bundle value
                // put as extras.
                Intent intent = new Intent();
                intent.setClass(mContext, SensorDetailActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    /**
     * overridden method used to get the count of the data to be shown in the list view
     * @return
     */
    @Override
    public int getCount() {
        return mSensorList.size();
    }

    /**
     * returns the position of the selected sensor
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * returns the sensor object
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return mSensorList.get(i);
    }

}
