package com.gh.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ProjectHistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_activity);

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };

        ProjectsHistoryListAdapter adapter = new ProjectsHistoryListAdapter(this, values);
        ListView listHacks = findViewById(R.id.single_list_view);
        listHacks.setAdapter(adapter);

        listHacks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));

//                Intent intent = new Intent(ProjectHistoryActivity.this, TabActivity.class);
//                ProjectHistoryActivity.this.startActivity(intent);
            }
        });
    }
}
