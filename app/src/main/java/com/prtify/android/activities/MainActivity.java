package com.prtify.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.prtify.android.R;
import com.prtify.android.services.SpotifyApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String key = "";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SpotifyApi service = retrofit.create(SpotifyApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = "Bearer " + getIntent().getStringExtra("key");
        Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();

        Call<ResponseBody> call = service.getCurrentlyPlaying(key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("RESPONSE", response + "");
                Log.d("TO_STRING", response.toString() + "");
                Log.d("MESSAGE", response.message() + "");
                Log.d("CODE", response.code() + "");
                if(response.body() != null){
                    Log.d("Body", response.body().toString() + "");
                }else {
                    Log.d("Body", "is_NULL");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private void showDevicesMenu() {
        Intent i = new Intent(this, ChooseDevice.class);
        i.putExtra("key", key);
        startActivity(i);
    }

    public void onSelectDevicesClick(View view) {
        showDevicesMenu();
    }



    public void onPlayClick(View view){
        Call<Void> call = service.playTrack(key);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("TO_STRING", response.toString() + "");
                Log.d("MESSAGE", response.message() + "");
                Log.d("CODE", response.code() + "");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void onPauseClick(View view){
        Call<Void> call = service.pauseTrack(key);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("TO_STRING", response.toString() + "");
                Log.d("MESSAGE", response.message() + "");
                Log.d("CODE", response.code() + "");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
