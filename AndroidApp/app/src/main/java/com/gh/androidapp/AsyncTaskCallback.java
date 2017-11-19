package com.gh.androidapp;

import android.content.Context;

import okhttp3.Response;

public interface AsyncTaskCallback {
    void run(Context context, String response);
}
