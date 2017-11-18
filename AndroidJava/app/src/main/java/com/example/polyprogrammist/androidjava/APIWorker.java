package com.example.polyprogrammist.androidjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Aspire on 18.11.2017.
 */

public class APIWorker {
    public String getHackatonImage(int hackatonID) {
        return "https://pp.userapi.com/c840724/v840724238/104fd/VgVdbyiD2jI.jpg";

    }

    public List<Integer> getHackatonIDs() {
        return Arrays.asList(5, 6, 7, 9);

    }

    public List<HackatonInfoSimple> getHackatonList()  {
        HackatonInfoSimple a = new HackatonInfoSimple("First Line Software", "18.11.2017-19.11.2017", "The best hackaton", getHackatonImage(5), 5);
        HackatonInfoSimple b = new HackatonInfoSimple("Generation H", "27.12.2025", "Future Hackaton", getHackatonImage(5), 5);
        return Arrays.asList(a, b);
    }
}


