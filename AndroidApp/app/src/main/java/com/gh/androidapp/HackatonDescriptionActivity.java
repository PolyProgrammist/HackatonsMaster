package com.gh.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Aspire on 19.11.2017.
 */

public class HackatonDescriptionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack);

        Intent intent = getIntent();

        TextView tvDesc = findViewById(R.id.tv_desc);
        tvDesc.setText(intent.getStringExtra("data"));
    }
}
