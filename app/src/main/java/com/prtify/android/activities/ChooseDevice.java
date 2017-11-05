package com.prtify.android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.prtify.android.R;
import com.prtify.android.adapters.DeviceListAdapter;
import com.prtify.android.requests.DeviceRequest;
import com.prtify.android.response.Device;
import com.prtify.android.response.DeviceResponse;
import com.prtify.android.services.SpotifyApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseDevice extends AppCompatActivity {

    private RecyclerView deviceList;
    private DeviceListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SpotifyApi service = retrofit.create(SpotifyApi.class);

    List<Device> devices = new ArrayList<>();

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_device);

        key = getIntent().getStringExtra("key");
        deviceList = (RecyclerView) findViewById(R.id.device_list);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        deviceList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
//        devices.add(new Device("", true, true, "HEYYYYY", "", 50));
        mAdapter = new DeviceListAdapter(devices, new DeviceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String id) {
                Log.d("TAG", id);
                Call<Void> call = service.playTrackOnDevice(key, new DeviceRequest(new String[]{id}));
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("RESPONSE", response + "");
                        Log.d("TO_STRING", response.toString() + "");
                        Log.d("MESSAGE", response.message() + "");
                        Log.d("CODE", response.code() + "");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
        deviceList.setAdapter(mAdapter);

        Call<DeviceResponse> call = service.getDevices(key);

        call.enqueue(new Callback<DeviceResponse>() {
            @Override
            public void onResponse(Call<DeviceResponse> call, Response<DeviceResponse> response) {
                Log.d("RESPONSE", response + "");
                Log.d("TO_STRING", response.toString() + "");
                Log.d("MESSAGE", response.message() + "");
                Log.d("CODE", response.code() + "");
                switch (response.code()){
                    case 200:

                        List<Device> lol = response.body().getDevices();

                        Log.d("TAG", devices.size() + "");

                        for (Device f : lol){
                            devices.add(f);
                            Log.d("DEVICE", f.getName());
                            Log.d("DEVICE", f.getId());
                        }

                        mAdapter.notifyDataSetChanged();

                        break;

                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<DeviceResponse> call, Throwable t) {

            }
        });

    }
}
