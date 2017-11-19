package com.gh.androidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.gh.androidapp.MainActivity.SERVER_URL;

public class UsersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Intent intent = getIntent();
        Integer PROJ_ID = intent.getIntExtra("id", 0);
        new AsyncTaskNetwork(this, SERVER_URL + "users/hack/" + PROJ_ID,
                new AsyncTaskCallback() {
                    @Override
                    public void run(Context context, String response) {
                        String names[] = null, descs[] = null, git[] = null;
                        Integer ids[] = null;

                        try {
                            JSONArray arr = new JSONArray(response);

                            ids = new Integer[arr.length()];
                            names = new String[arr.length()];
                            descs = new String[arr.length()];
                            git = new String[arr.length()];

                            for(Integer i = 0; i < arr.length(); i++){
                                JSONObject o = (JSONObject) arr.get(i);

                                ids[i] = (Integer) o.get("id");
                                names[i] = (String) o.get("name");
                                descs[i] = (String) o.get("desc");
                                git[i] = (String) o.get("git");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        UsersListAdapter adapter = new UsersListAdapter((Activity)context, names, descs, git);
                        ListView listHacks = findViewById(R.id.lv_users);
                        listHacks.setAdapter(adapter);
                        final String[] finalGit = git;
                        listHacks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalGit[i])));
                            }
                        });
                    }
                }).execute();
    }
}
