package com.gh.androidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.gh.androidapp.MainActivity.SERVER_URL;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Intent intent = getIntent();
        Integer PROJ_ID = intent.getIntExtra("id", 0);
        new AsyncTaskNetwork(this, SERVER_URL + "content/all/" + PROJ_ID,
                new AsyncTaskCallback() {
                    @Override
                    public void run(Context context, String response) {
                        // text photo video git time
                        String text[] = null, photo[] = null, video[] = null, git[] = null, time[] = null, author[] = null;

                        try {
                            JSONArray arr = new JSONArray(response);

                            text = new String[arr.length()];
                            photo = new String[arr.length()];
                            video = new String[arr.length()];
                            git = new String[arr.length()];
                            time = new String[arr.length()];
                            author = new String[arr.length()];

                            for(Integer i = 0; i < arr.length(); i++){
                                JSONObject o = (JSONObject) arr.get(i);

                                text[i] = (String) o.get("text");
                                photo[i] = (String) o.get("photo");
                                video[i] = (String) o.get("video");
                                git[i] = (String) o.get("git");
                                time[i] = (String) o.get("time");
                                author[i] = (String) o.get("author");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        FeedListAdapter adapter = new FeedListAdapter((Activity)context, text, photo, video, git, time, author);
                        ListView listHacks = findViewById(R.id.lv_content);
                        listHacks.setAdapter(adapter);

                        final String[] finalVideo = video;
                        final String[] finalGit = git;
                        listHacks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                if(!finalVideo[i].isEmpty() && finalGit[i].isEmpty()){
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalVideo[i])));
                                }else if(!finalGit[i].isEmpty()){
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalGit[i])));
                                }
                            }
                        });
                    }
                }).execute();

//        new GitApiWorker(GitApiWorker.owner_example, GitApiWorker.repo_example, this).print_commits();
    }
}
