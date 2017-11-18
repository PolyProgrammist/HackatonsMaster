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

public class MainListAdapter extends ArrayAdapter<String> {
    private final Activity context;

    public MainListAdapter(Activity context, String[] values) {
        super(context, R.layout.list_single_main, values);
        this.context = context;
    }

    // https://pp.userapi.com/c837721/v837721567/5ce2a/oNTd2QBCi5Y.jpg

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View single = inflater.inflate(R.layout.list_single_main, parent, false);

        TextView date = single.findViewById(R.id.tv_date);
        TextView name = single.findViewById(R.id.tv_name);
        ImageView img = single.findViewById(R.id.iv_logo);

        new ImageLoadTask("http://192.168.137.1/storage/steam.png", img).execute();
        name.setText("Just 4 test");
        date.setText("27 Nov");

        return single;
    }
}

class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
    private String url;
    private ImageView imageView;

    public ImageLoadTask(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
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
        imageView.setImageBitmap(result);
    }

}