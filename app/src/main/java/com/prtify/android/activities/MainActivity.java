package com.prtify.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prtify.android.R;
import com.prtify.android.objects.QueueItem;
import com.prtify.android.objects.RequestItem;
import com.prtify.android.response.CurrentlyPlayingResponse;
import com.prtify.android.response.SearchResponse;
import com.prtify.android.services.SpotifyApi;
import com.squareup.picasso.Picasso;

import okhttp3.Request;
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

    TextView title, artist, album;
    ImageView albumPicture;

    String currentID = null;
    boolean wait = false;

    String partyName = "orgy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title);
        artist = (TextView) findViewById(R.id.artist);
        album = (TextView) findViewById(R.id.album);
        albumPicture = (ImageView) findViewById(R.id.album_picture);

        key = "Bearer " + getIntent().getStringExtra("key");

        updateSong();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference partyRef = database.getReference("parties").child(partyName);
        DatabaseReference requestRef = partyRef.child("requests");
        final DatabaseReference queueRef = partyRef.child("queue");

        requestRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                RequestItem item = dataSnapshot.getValue(RequestItem.class);
                Call<SearchResponse> call = service.search(key, item.getName() + " " + item.getAlbum() + " " + item.getArtist(), "track");
                call.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                        Log.d("TO_STRING", response.toString() + "");
                        Log.d("MESSAGE", response.message() + "");
                        Log.d("CODE", response.code() + "");

                        QueueItem queueItem = response.body().getFirst();
                        queueRef.child("name").setValue(queueItem.getName());
                        queueRef.child("artist").setValue(queueItem.getArtist());
                        queueRef.child("album").setValue(queueItem.getAlbum());
                        queueRef.child("image").setValue(queueItem.getImage());
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateSong() {
        Call<CurrentlyPlayingResponse> call = service.getCurrentlyPlaying(key);
        call.enqueue(new Callback<CurrentlyPlayingResponse>() {
            @Override
            public void onResponse(Call<CurrentlyPlayingResponse> call, Response<CurrentlyPlayingResponse> response) {
                CurrentlyPlayingResponse b = response.body();
                if(b == null){
                    updateSong();
                }else {
                    title.setText(b.getSongName());
                    artist.setText(b.getArtistName());
                    album.setText(b.getAlbumName());
                    Picasso.with(getApplicationContext()).load(b.getAlbumPicture()).into(albumPicture);
                    if(!wait && b.getTimeLeft() < 1000)playNextSong();
                    updateSong();
                }
            }

            @Override
            public void onFailure(Call<CurrentlyPlayingResponse> call, Throwable t) {
                updateSong();
            }
        });
    }

    private void playNextSong() {
        wait = true;
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
