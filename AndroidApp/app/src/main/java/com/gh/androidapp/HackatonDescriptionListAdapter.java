package com.gh.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Aspire on 19.11.2017.
 */

public class HackatonDescriptionListAdapter extends ArrayAdapter<String> {

    private final Activity context;

    public HackatonDescriptionListAdapter(Activity context, String[] values) {
        super(context, R.layout.list_single_projects_history, values);
        this.context = context;
    }
}
