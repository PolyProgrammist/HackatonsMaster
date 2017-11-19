package com.gh.androidapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.gh.androidapp.MainActivity.SERVER_URL;
import static com.gh.androidapp.MainActivity.hackLogos;


public class MainListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private String[] names;
    private String[] dates;

    public MainListAdapter(Activity context, String[] names, String[] dates){
        super(context, R.layout.list_single_main, names);
        this.context = context;
        this.names = names;
        this.dates = dates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View single = inflater.inflate(R.layout.list_single_main, parent, false);

        TextView date = single.findViewById(R.id.tv_date);
        TextView name = single.findViewById(R.id.tv_name);
        ImageView img = single.findViewById(R.id.iv_logo);

        String datestr = dates[position];
        SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat back = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date vdate = start.parse(datestr);
            datestr = back.format(vdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        img.setImageBitmap(hackLogos[position]);
        name.setText(names[position]);
        date.setText(datestr);

        return single;
    }
}

class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
    private String url;
    private Integer pos;

    public ImageLoadTask(String url, Integer pos) {
        this.url = "http://192.168.137.1/storage/" + url;
        this.pos = pos;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        hackLogos[pos] = result;
    }
}