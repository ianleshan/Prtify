package com.prtify.android.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.prtify.android.R;
import com.prtify.android.objects.QueueItem;
import com.prtify.android.objects.RequestItem;
import com.prtify.android.requests.TrackUriRequest;
import com.prtify.android.response.CurrentlyPlayingResponse;
import com.prtify.android.response.SearchResponse;
import com.prtify.android.services.SpotifyApi;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Random;

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

    TextView title, artist, album, partyNameText;
    ImageView albumPicture;
    LinearLayout container;
    SeekBar volumeBar;

    String currentID = "";
    boolean wait = false;

    String partyName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        title = (TextView) findViewById(R.id.title);
        artist = (TextView) findViewById(R.id.artist);
        album = (TextView) findViewById(R.id.album);
        partyNameText = (TextView) findViewById(R.id.party_name);
        albumPicture = (ImageView) findViewById(R.id.album_picture);
        container = (LinearLayout) findViewById(R.id.container);
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);

        albumPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), QueueActivity.class);
                i.putExtra("party", partyName);
                startActivity(i);
            }
        });


        key = "Bearer " + getIntent().getStringExtra("key");
        partyName = getIntent().getStringExtra("partyName");

        partyNameText.setText(partyName);

        onPlayClick(title);
        updateSong();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference partyRef = database.getReference("parties").child(partyName);
        final DatabaseReference requestRef = partyRef.child("requests");
        final DatabaseReference queueRef = partyRef.child("queue");

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Call<Void> call = service.setVolume(key, seekBar.getProgress());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        requestRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals("artist"))return;
                if(dataSnapshot.getKey().equals("name"))return;
                RequestItem item = dataSnapshot.getValue(RequestItem.class);
                requestRef.child(dataSnapshot.getKey()).removeValue();
                Call<SearchResponse> call = service.search(key, item.getName() + " " + item.getArtist(), "track");
                call.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        if(response.body() == null){
                            return;
                        }
                        Log.d("TO_STRING", response.toString() + "");
                        Log.d("MESSAGE", response.message() + "");
                        Log.d("CODE", response.code() + "");

                        QueueItem queueItem = response.body().getFirst();
                        if(queueItem == null){
                            Log.d("ARTIST", response.toString() + "");
                            return;
                        }
                        DatabaseReference newRef = queueRef.child(queueItem.getId());

                        newRef.child("name").setValue(queueItem.getName());
                        newRef.child("artist").setValue(queueItem.getArtist());
                        newRef.child("album").setValue(queueItem.getAlbum());
                        newRef.child("image").setValue(queueItem.getImage());
                        newRef.child("key").setValue(queueItem.getId());
                        newRef.child("votes").setValue(0);
                        newRef.child("upvotes").setValue(0);
                        newRef.child("downvotes").setValue(0);

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

    private void playFirstSong() {
        playSong("5Nm32R9spCURiGw0MRMzyd");

    }

    private void playSong(String id){
        Call<Void> call = service.playTrack(key, new TrackUriRequest(new String[]{"spotify:track:" + id}));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            wait = false;
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void updateSong() {
        Call<CurrentlyPlayingResponse> call = service.getCurrentlyPlaying(key);
        call.enqueue(new Callback<CurrentlyPlayingResponse>() {
            @Override
            public void onResponse(Call<CurrentlyPlayingResponse> call, Response<CurrentlyPlayingResponse> response) {
                switch (response.code()){
                    case 400:
                        Intent i = new Intent(MainActivity.this, MyLoginActivity.class);
                        startActivity(i);
                        finish();
                        break;
                }
                CurrentlyPlayingResponse b = response.body();
                if (b == null) {
                    updateSong();
                } else {
                    title.setText(b.getSongName());
                    artist.setText(b.getArtistName());
                    album.setText(b.getAlbumName());
//                    if(!currentID.equals(b.getAlbumPicture())) {
//                        currentID = b.getAlbumPicture();
                    Picasso.with(getApplicationContext()).load(b.getAlbumPicture()).into(albumPicture);
                    Picasso.with(getApplicationContext()).load(b.getAlbumPicture()).into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            Palette.from(bitmap).maximumColorCount(16).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {
                                    // Get the "vibrant" color swatch based on the bitmap
                                    Palette.Swatch swatch = palette.getVibrantSwatch();

                                    if (swatch == null) {
                                        swatch = palette.getDominantSwatch();
                                    }

                                    if (swatch == null) return;
                                    // Set the background color of a layout based on the vibrant color
                                    container.setBackgroundColor(swatch.getRgb());
                                    // Update the title TextView with the proper text color
                                    partyNameText.setTextColor(swatch.getTitleTextColor());
                                    title.setTextColor(swatch.getTitleTextColor());
                                    artist.setTextColor(swatch.getBodyTextColor());
                                    album.setTextColor(swatch.getBodyTextColor());
                                    volumeBar.getProgressDrawable().setColorFilter(swatch.getTitleTextColor(), PorterDuff.Mode.MULTIPLY);
                                    volumeBar.getThumb().setColorFilter(swatch.getTitleTextColor(), PorterDuff.Mode.MULTIPLY);
                                    ((Button) (findViewById(R.id.connect_to_device))).setTextColor(swatch.getTitleTextColor());
                                }
                            });
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
//                    }

                    if (!wait && b.getTimeLeft() < 1500){
                        wait = true;
                        playNextSong();
                    }
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
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference partyRef = database.getReference("parties").child(partyName);
        final DatabaseReference queueRef = partyRef.child("queue");


        final Query mDatabaseHighestPlayer = queueRef.orderByChild("votes").limitToLast(1);
        mDatabaseHighestPlayer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    playSong(ds.getKey());
                    queueRef.child(ds.getKey()).removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showDevicesMenu() {
        Intent i = new Intent(this, ChooseDevice.class);
        i.putExtra("key", key);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference partyRef = database.getReference("parties").child(partyName);
        partyRef.removeValue();
    }

    public void onSelectDevicesClick(View view) {
        showDevicesMenu();
    }


    public void onNextClick(View view) {
        playNextSong();
    }

    public void onPlayClick(View view) {
        Call<Void> call = service.playTrack(key);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                Log.d("TO_STRING", response.toString() + "");
//                Log.d("MESSAGE", response.message() + "");
//                Log.d("CODE", response.code() + "");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void onPauseClick(View view) {
        Call<Void> call = service.pauseTrack(key);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                Log.d("TO_STRING", response.toString() + "");
//                Log.d("MESSAGE", response.message() + "");
//                Log.d("CODE", response.code() + "");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
