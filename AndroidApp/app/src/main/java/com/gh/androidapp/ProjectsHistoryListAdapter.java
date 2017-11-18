package com.gh.androidapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Aspire on 19.11.2017.
 */

public class ProjectsHistoryListAdapter extends ArrayAdapter<String> {
    private final Activity context;

    public ProjectsHistoryListAdapter(Activity context, String[] values) {
        super(context, R.layout.list_single_projects_history, values);
        this.context = context;
    }

    // https://pp.userapi.com/c837721/v837721567/5ce2a/oNTd2QBCi5Y.jpg

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View single = inflater.inflate(R.layout.list_single_projects_history, parent, false);

        TextView header = single.findViewById(R.id.project_history_header);
        TextView text = single.findViewById(R.id.project_history_item_text);
        ImageView img = single.findViewById(R.id.project_history_item_image);

        header.setText("Lorem Ipsum");
        text.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin mauris et vulputate sodales. Integer eleifend in neque non commodo. Aenean ac ullamcorper lacus. Fusce porttitor malesuada arcu, vitae lacinia quam rhoncus id. Donec pharetra volutpat velit, et ornare turpis. In molestie feugiat lacus et semper. In libero tellus, porta at sapien convallis, vehicula gravida urna.");

        new ImageLoadTask("http://192.168.137.1/storage/steam.png", img).execute();

        return single;
    }
}