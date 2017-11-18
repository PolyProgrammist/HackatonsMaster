package com.gh.androidapp;

import android.content.Context;

import okhttp3.Response;

/**
 * Created by Asus on 19.11.2017.
 */

public interface AsyncTaskCallback {
    void run(Context context, Response response);
}
