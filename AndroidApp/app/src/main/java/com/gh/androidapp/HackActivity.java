package com.gh.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack);

        Intent intent = getIntent();

        TextView tvDesc = findViewById(R.id.tv_desc);
        tvDesc.setText(intent.getStringExtra("data"));
    }
}
