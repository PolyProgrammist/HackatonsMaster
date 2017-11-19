package com.gh.androidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    final static String SERVER_URL = "http://192.168.137.1/api/";
    public static Bitmap[] hackLogos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AsyncTaskNetwork(this, SERVER_URL + "hackathons",
                new AsyncTaskCallback() {
                    @Override
                    public void run(Context context, String response) {
                        Integer ids[] = null;
                        String names[] = null, dates[] = null;

                        try {
                            JSONArray arr = new JSONArray(response);

                            ids = new Integer[arr.length()];
                            names = new String[arr.length()];
                            dates = new String[arr.length()];
                            hackLogos = new Bitmap[arr.length()];

                            for(Integer i = 0; i < arr.length(); i++){
                                JSONObject o = (JSONObject) arr.get(i);

                                ids[i] = (Integer) o.get("id");
                                names[i] = (String) o.get("name");
                                dates[i] = (String) o.get("date");

                                // download photos
                                ImageLoadTask imtsk = new ImageLoadTask((String) o.get("logo"), i);
                                imtsk.execute().get();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // nice photo fix
                        synchronized (this){
                            try {
                                wait(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        MainListAdapter adapter = new MainListAdapter((Activity)context, names, dates);
                        ListView listHacks = findViewById(R.id.main_list_hacks);
                        listHacks.setAdapter(adapter);

                        final Integer[] finalIds = ids;
                        listHacks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                                intent.putExtra("id", finalIds[position]);
                                MainActivity.this.startActivity(intent);
                            }
                        });
                    }
                }).execute();

        setContentView(R.layout.activity_main);
    }
}