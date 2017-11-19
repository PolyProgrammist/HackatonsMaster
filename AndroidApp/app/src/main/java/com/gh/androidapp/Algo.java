package com.gh.androidapp;

import android.content.Context;

/**
 * Created by Asus on 19.11.2017.
 */

public class Algo {
    public static int dpToPx(Context context, int dpValue){
        float d = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * d);
    }
}
