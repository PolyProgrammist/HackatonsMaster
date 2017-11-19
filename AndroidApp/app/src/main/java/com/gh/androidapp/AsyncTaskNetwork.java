package com.gh.androidapp;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskNetwork extends AsyncTask<Void, Void, String> {
    String url;
    Context context;
    AsyncTaskCallback callback;

    public AsyncTaskNetwork(Context context, String url, AsyncTaskCallback callback) {
        this.url = url;
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            return StringEscapeUtils.unescapeJava(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        callback.run(context, response);
    }
}
