package com.gh.androidapp;

import android.content.Context;
import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTaskNetwork extends AsyncTask<Void, Void, Response> {
    String url;
    Context context;
    AsyncTaskCallback callback;

    public AsyncTaskNetwork(Context context, String url, AsyncTaskCallback callback) {
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
