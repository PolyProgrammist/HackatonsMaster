package com.example.polyprogrammist.androidjava;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Aspire on 18.11.2017.
 */

public class HackatonInfoSimple {
    int id;
    String name;
    String date;
    String extraInfo;
    Bitmap logo;

    public HackatonInfoSimple(String name, String date, String extraInfo, Bitmap logo, int id) {
        this.name = name;
        this.date = date;
        this.extraInfo = extraInfo;
        this.logo = logo;
    }
}
