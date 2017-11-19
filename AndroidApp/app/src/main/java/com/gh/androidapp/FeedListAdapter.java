package com.gh.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class FeedListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private String[] text;
    private String[] photo;
    private String[] video;
    private String[] git;
    private String[] time;
    private String[] author;

    public FeedListAdapter(Activity context, String[] text, String[] photo, String[] video, String[] git, String[] time, String[] author){
        super(context, R.layout.list_single_main, text);
        this.context = context;
        this.text = text;
        this.photo = photo;
        this.video = video;
        this.git = git;
        this.time = time;
        this.author = author;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View single = inflater.inflate(R.layout.list_single_content, parent, false);

        TextView tvText = single.findViewById(R.id.tv_content_text);
        TextView tvVideo = single.findViewById(R.id.tv_content_video);
        TextView tvDate = single.findViewById(R.id.tv_content_date);
        ImageView ivPhoto = single.findViewById(R.id.tv_content_photo);

        String datestr = time[position];
        SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat back = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date vdate = start.parse(datestr);
            datestr = back.format(vdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(!photo[position].isEmpty()){
            try {
                new HackActivity.OneImageLoadTask(photo[position], ivPhoto).execute().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        String bytext = "";
        if(!video[position].isEmpty() || !git[position].isEmpty()){
            if(git[position].isEmpty())
                tvVideo.setText(video[position]);
            else {
                tvVideo.setText(git[position]);
                bytext = ", by " + author[position];
            }
        }else{
            RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
            tvVideo.setLayoutParams(params);

            RelativeLayout.LayoutParams params2= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params2.addRule(RelativeLayout.BELOW, R.id.tv_content_text);
            params2.setMargins(0, Algo.dpToPx(context, 16), 0, 0);
            tvDate.setLayoutParams(params2);
        }

        tvText.setText(text[position]);
        tvDate.setText(datestr + bytext);

        return single;
    }
}

