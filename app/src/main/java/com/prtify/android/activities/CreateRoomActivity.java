package com.prtify.android.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prtify.android.R;

public class CreateRoomActivity extends AppCompatActivity {

    Button cParty;
    EditText pName;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        cParty = (Button)findViewById(R.id.createParty);
        pName   = (EditText)findViewById(R.id.partyName);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        cParty.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        DatabaseReference ref = myRef.child("parties").child(pName.getText().toString());
                        ref.child("name").setValue(pName.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent i = new Intent(CreateRoomActivity.this, MainActivity.class);
                                i.putExtra("partyName", pName.getText().toString());
                                i.putExtra("key", getIntent().getStringExtra("key"));
                                startActivity(i);
                                finish();
                            }
                        });
                    }
                }
        );
    }



}
