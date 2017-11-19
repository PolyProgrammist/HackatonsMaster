package com.gh.androidapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TabHost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import okhttp3.Response;

/**
 * Created by Aspire on 19.11.2017.
 */

public class GitApiWorker {
    final static String branches_example = "https://api.github.com/repos/PolyProgrammist/HackatonsMaster/branches";
//    final static String commits_example = "https://api.github.com/repos/PolyProgrammist/HackatonsMaster/..";
    final static String owner_example = "PolyProgrammist";
    final static String repo_example = "HackatonsMaster";

    final static String apilink = "https://api.github.com/repos/";
    final static String branches_adding = "branches";
    final static int magic_const = 10;
    final static String commits_adding = "commits?per_page=" + magic_const + "&sha=";

    private String prelink;
    private Set<String> commit_shas;
    private Set<String> branches_shas;
    private Context context;

    public GitApiWorker(String owner, String repo, Context context) {
        this.prelink = this.apilink + owner + '/' + repo + '/';
        this.commit_shas = new HashSet<>();
        this.branches_shas = new HashSet<>();
        this.context = context;
    }

    List<GitCommit> get_new_commits() {
        try {
            List<String> shas = _get_all_new_shas();
            List<JSONObject> commits = new ArrayList<>();
            for (String s : shas)
                if (!branches_shas.contains(s))
                    commits.addAll(_get_new_commits_from_branch(s));
            branches_shas.addAll(shas);
            for (JSONObject commit : commits)
                commit_shas.add((String) commit.get("sha"));

            List<GitCommit> res = new ArrayList<>();
            for (JSONObject commit : commits)
                res.add(new GitCommit(
                        (String) commit.getJSONObject("commit").getJSONObject("author").get("date"),
                        (String) commit.getJSONObject("commit").getJSONObject("author").get("name"),
                        (String) commit.getJSONObject("commit").get("message"),
                        (String) commit.get("html_url")
                ));
            return res;
        } catch (Exception e) {

        }
        return null;
    }

    public void print_commits() {
        List<GitCommit> t = get_new_commits();

        for (GitCommit commit : t)
            System.out.println(String.format("date: %s, author: %s, message: %s, url: %s", commit.date, commit.author, commit.message, commit.url));
    }

    private List<String> _get_all_new_shas() throws ExecutionException, InterruptedException {
        System.out.println("starting querying");
        final List<String> res = new ArrayList<>();
        new AsyncTaskNetwork(context, prelink + branches_adding,
                new AsyncTaskCallback(){
                    @Override
                    public void run(Context context, String response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < jsonArray.length(); i++)
                            try {
                                res.add((String) jsonArray.getJSONObject(i).getJSONObject("commit").get("sha"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                }).execute().get();

        return res;
    }

    private List<JSONObject> _get_new_commits_from_branch(String sha) throws ExecutionException, InterruptedException {
        final List<JSONObject> res = new ArrayList<>();
        new AsyncTaskNetwork(context, prelink + commits_adding + sha,
                new AsyncTaskCallback(){
                    @Override
                    public void run(Context context, String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++)
                                if (!commit_shas.contains(jsonArray.getJSONObject(i).get("sha")))
                                    res.add(jsonArray.getJSONObject(i));
                        } catch
                                (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).execute().get();
        return res;
    }
}
//new GitCommitsGetter(GitCommitsGetter.owner_example, GitCommitsGetter.repo_example, TabActivity.this).print_commits();