package com.gh.androidapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.gh.androidapp.MainActivity.hackLogos;

public class ProjectsListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private String[] names;
    private String[] descs;

    public ProjectsListAdapter(Activity context, String[] names, String[] descs){
        super(context, R.layout.list_single_main, names);
        this.context = context;
        this.names = names;
        this.descs = descs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View single = inflater.inflate(R.layout.list_single_project, parent, false);

        TextView name = single.findViewById(R.id.tv_project_name);
        TextView desc = single.findViewById(R.id.tv_project_desc);

        name.setText(names[position]);
        desc.setText(descs[position]);

        return single;
    }
}

