package com.prtify.android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.prtify.android.R;
import com.prtify.android.adapters.DeviceListAdapter;
import com.prtify.android.adapters.QueueListAdapter;
import com.prtify.android.objects.SongItem;
import com.prtify.android.requests.DeviceRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueueActivity extends AppCompatActivity {

    private RecyclerView queueList;
    private QueueListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<SongItem> queue = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        queueList = (RecyclerView) findViewById(R.id.queue_list);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        queueList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
//        devices.add(new Device("", true, true, "HEYYYYY", "", 50));
        mAdapter = new QueueListAdapter(queue);
        queueList.setAdapter(mAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference partyRef = database.getReference("parties").child(getIntent().getStringExtra("party"));
        final DatabaseReference queueRef = partyRef.child("queue");
        final Query q = queueRef.orderByChild("votes");

        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String artist = dataSnapshot.child("artist").getValue(String.class);
                String id = dataSnapshot.child("key").getValue(String.class);

                SongItem song = new SongItem(name, artist, id);
                queue.add(song);

                mAdapter.notifyDataSetChanged();

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
}
