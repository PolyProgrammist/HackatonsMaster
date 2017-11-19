package com.gh.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UsersListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private String[] names;
    private String[] descs;
    private String[] gits;

    public UsersListAdapter(Activity context, String[] names, String[] descs, String[] git){
        super(context, R.layout.list_single_main, names);
        this.context = context;
        this.names = names;
        this.descs = descs;
        this.gits = git;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View single = inflater.inflate(R.layout.list_single_user, parent, false);

        TextView name = single.findViewById(R.id.tv_user_name);
        TextView desc = single.findViewById(R.id.tv_user_desc);
        TextView git = single.findViewById(R.id.tv_user_git);

        name.setText(names[position]);
        desc.setText(descs[position]);
        git.setText(gits[position]);

//        git.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(gits[position])));
//            }
//        });

        return single;
    }
}

