package com.prtify.android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.prtify.android.R;
import com.prtify.android.adapters.DeviceListAdapter;

public class ChooseDevice extends AppCompatActivity {

    private RecyclerView deviceList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_device);

//        deviceList = (RecyclerView) findViewById(R.id.device_list);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        deviceList.setHasFixedSize(true);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        deviceList.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        String[] devices = new String[]{"Not Xbox", "Not Iphone"};
//        mAdapter = new DeviceListAdapter(devices, new DeviceListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, String id) {
//
//            }
//        });
//        deviceList.setAdapter(mAdapter);
    }
}
