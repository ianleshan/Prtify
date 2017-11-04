package com.prtify.android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.prtify.android.R;

public class MainActivity extends AppCompatActivity {

    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = " Bearer " + getIntent().getStringExtra("key");
        Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();

    }

    public void onPlayClick(View view){

    }

    public void onPauseClick(View view){

    }
}
