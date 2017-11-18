package com.example.polyprogrammist.androidjava;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Aspire on 18.11.2017.
 */

public class StableArrayAdapter extends ArrayAdapter<HackatonInfoSimple> {
    Activity contextmy;

    HashMap<HackatonInfoSimple, Integer> mIdMap = new HashMap<HackatonInfoSimple, Integer>();
    HashMap<Integer, HackatonInfoSimple> mIDMap = new HashMap<>();

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<HackatonInfoSimple> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
            mIDMap.put(i, objects.get(i));
        }
        this.contextmy = (Activity) context;
    }

    @Override
    public long getItemId(int position) {
        HackatonInfoSimple item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = contextmy.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.one_hackaton_in_list, null, true);

        HackatonInfoSimple his = mIDMap.get(position);

        TextView name = rowView.findViewById(R.id.name);
        TextView date = rowView.findViewById(R.id.date);
        TextView extraInfo = rowView.findViewById(R.id.extra_info);

        ImageView logo = rowView.findViewById(R.id.img);
        name.setText(his.name);
        date.setText(his.date);
        extraInfo.setText(his.extraInfo);
        logo.setImageBitmap(his.logo);
        return rowView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
