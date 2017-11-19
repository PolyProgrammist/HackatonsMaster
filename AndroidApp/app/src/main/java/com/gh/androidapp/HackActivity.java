package com.gh.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class HackActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hack);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        String date = intent.getStringExtra("date");
        String wlogo = intent.getStringExtra("wlogo");

        TextView tvDesc = findViewById(R.id.tv_hack_desc);
        TextView tvName = findViewById(R.id.tv_hack_name);
        TextView tvDate = findViewById(R.id.tv_hack_date);
        ImageView ivWLogo = findViewById(R.id.iv_hack_wlogo);

        try {
            new OneImageLoadTask(wlogo, ivWLogo).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat back = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date vdate = start.parse(date);
            date = back.format(vdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvName.setText(name);
        tvDesc.setText(desc);
        tvDate.setText("Дата проведения: " + date);
    }

    static class OneImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
        private String url;
        private ImageView iv;

        public OneImageLoadTask(String url, ImageView iv) {
            this.url = "http://192.168.137.1/storage/" + url;
            this.iv = iv;
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
            iv.setImageBitmap(result);
        }
    }
}

