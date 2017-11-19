package com.gh.androidapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Function;
import java.util.function.Supplier;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.gh.androidapp.MainActivity.SERVER_URL;

public class TabActivity extends android.app.TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Integer HACK_ID = 0;
        HACK_ID = getIntent().getIntExtra("id", 0);

        final TabHost tabHost = getTabHost();

        final Integer finalHACK_ID = HACK_ID;
        new AsyncTaskNetwork(this, SERVER_URL + "hackathons/" + HACK_ID,
                new AsyncTaskCallback(){
                    @Override
                    public void run(Context context, String response) {
                        String name = "", desc = "", wlogo = "", date = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            name = (String) jsonObject.get("name");
                            desc = (String) jsonObject.get("desc");
                            date = (String) jsonObject.get("date");
                            wlogo = (String) jsonObject.get("wlogo");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        TabHost.TabSpec tabSpec;

                        Intent hackIntent = new Intent(context, HackActivity.class);
                        hackIntent.putExtra("name", name);
                        hackIntent.putExtra("desc", desc);
                        hackIntent.putExtra("date", date);
                        hackIntent.putExtra("wlogo", wlogo);

                        tabSpec = tabHost.newTabSpec("tag1");
                        tabSpec.setIndicator("Описание");
                        tabSpec.setContent(hackIntent);
                        tabHost.addTab(tabSpec);

                        Intent projectIntent = new Intent(context, ProjectsActivity.class);
                        projectIntent.putExtra("id", finalHACK_ID);

                        tabSpec = tabHost.newTabSpec("tag2");
                        tabSpec.setIndicator("Проекты");
                        tabSpec.setContent(projectIntent);
                        tabHost.addTab(tabSpec);

                        Intent usersIntent = new Intent(context, UsersActivity.class);
                        usersIntent.putExtra("id", finalHACK_ID);

                        tabSpec = tabHost.newTabSpec("tag3");
                        tabSpec.setIndicator("Участники");
                        tabSpec.setContent(usersIntent);
                        tabHost.addTab(tabSpec);

//                        final TabWidget tw = tabHost.findViewById(android.R.id.tabs);
//                        for (int i = 0; i < tw.getChildCount(); ++i)
//                        {
//                            final View tabView = tw.getChildTabViewAt(i);
//                            final TextView tv = tabView.findViewById(android.R.id.title);
//                            tv.setTextSize(Algo.dpToPx(context, 8));
//                        }
                    }
                }).execute();
    }
}
