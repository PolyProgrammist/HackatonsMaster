package com.gh.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TabActivity extends android.app.TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .build();

        Request request = new Request.Builder()
                .url("http://192.168.137.1/api/hackathons")
                .method("GET", null)
                .build();

        String desc = "";

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    callActivities(response);
                }
            }
        });
    }

    private void callActivities(Response response){
        String desc = response.body().toString();

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Описание");

        Intent hackIntent = new Intent(this, HackActivity.class);
        hackIntent.putExtra("data", desc);

        tabSpec.setContent(new Intent(this, HackActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Проекты");
        tabSpec.setContent(new Intent(this, ProjectHistoryActivity.class));
        tabHost.addTab(tabSpec);
    }
}
