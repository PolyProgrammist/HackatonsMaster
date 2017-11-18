package com.gh.androidapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;

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

public class TabActivity extends android.app.TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        final TabHost tabHost = getTabHost();
        new JsonLoadTask(this, "http://192.168.137.1/api/hackathons/1",
                new AsyncTaskCallback(){
                    @Override
                    public void run(Context context, Response response) {
                        String name = "", desc = "", logo = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            name = (String) jsonObject.get("name");
                            desc = (String) jsonObject.get("desc");
                            logo = (String) jsonObject.get("logo");
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }

                        TabHost.TabSpec tabSpec;
                        tabSpec = tabHost.newTabSpec("tag1");
                        tabSpec.setIndicator("Описание");

                        Intent hackIntent = new Intent(context, HackActivity.class);
                        hackIntent.putExtra("name", name);
                        hackIntent.putExtra("desc", desc);
                        hackIntent.putExtra("logo", logo);

                        tabSpec.setContent(hackIntent);
                        tabHost.addTab(tabSpec);

                        tabSpec = tabHost.newTabSpec("tag2");
                        tabSpec.setIndicator("Проекты");
                        tabSpec.setContent(new Intent(context, ProjectsActivity.class));
                        tabHost.addTab(tabSpec);
                    }
                }).execute();
    }

    static class JsonLoadTask extends AsyncTask<Void, Void, Response> {
        String url;
        Context context;
        AsyncTaskCallback callback;

        private JsonLoadTask(Context context, String url, AsyncTaskCallback callback) {
            this.url = url;
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected Response doInBackground(Void... params) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .method("GET", null)
                        .build();

                return okHttpClient.newCall(request).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            callback.run(context, response);
        }
    }
}
