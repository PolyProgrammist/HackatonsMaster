package com.gh.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hack);

        Intent intent = getIntent();

        Log.e("INTENT", intent.getExtras().toString());

        TextView tvDesc = findViewById(R.id.tv_desc);
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        String date = intent.getStringExtra("date");
        tvDesc.setText(intent.getStringExtra("desc"));
    }
}
