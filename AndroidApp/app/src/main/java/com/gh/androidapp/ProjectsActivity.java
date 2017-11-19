package com.gh.androidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.gh.androidapp.MainActivity.SERVER_URL;

public class ProjectsActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        Intent intent = getIntent();
        Integer HACK_ID = intent.getIntExtra("id", 0);
        new AsyncTaskNetwork(this, SERVER_URL + "projects/" + HACK_ID,
                new AsyncTaskCallback() {
                    @Override
                    public void run(Context context, String response) {
                        String names[] = null, descs[] = null;
                        Integer ids[] = null;

                        try {
                            JSONArray arr = new JSONArray(response);

                            ids = new Integer[arr.length()];
                            names = new String[arr.length()];
                            descs = new String[arr.length()];

                            for(Integer i = 0; i < arr.length(); i++){
                                JSONObject o = (JSONObject) arr.get(i);

                                ids[i] = (Integer) o.get("id");
                                names[i] = (String) o.get("name");
                                descs[i] = (String) o.get("desc");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        ProjectsListAdapter adapter = new ProjectsListAdapter((Activity)context, names, descs);
                        ListView listHacks = findViewById(R.id.lv_projects);
                        listHacks.setAdapter(adapter);

                        final Integer[] finalIds = ids;
                        listHacks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ProjectsActivity.this, FeedActivity.class);
                                intent.putExtra("id", finalIds[position]);
                                ProjectsActivity.this.startActivity(intent);
                            }
                        });
                    }
                }).execute();
    }
}
