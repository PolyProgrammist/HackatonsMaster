package com.gh.androidapp;

/**
 * Created by Asus on 19.11.2017.
 */

public class GitCommit {
    String date, author, message, url;
    GitCommit(String date, String author, String message, String url){
        this.date = date;
        this.author = author;
        this.message = message;
        this.url = url;
    }
}
