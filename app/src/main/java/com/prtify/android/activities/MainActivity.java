package com.prtify.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.prtify.android.R;
import com.prtify.android.services.SpotifyApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    String key = "";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spotify.com")
            .build();

    SpotifyApi service = retrofit.create(SpotifyApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = " Bearer " + getIntent().getStringExtra("key");
        Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();

    }

    private void showDevicesMenu() {
        Intent i = new Intent(this, ChooseDevice.class);
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

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
