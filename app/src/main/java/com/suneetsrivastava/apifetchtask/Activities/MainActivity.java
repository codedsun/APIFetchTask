package com.suneetsrivastava.apifetchtask.Activities;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.suneetsrivastava.apifetchtask.Adapter.PostRecyclerAdapter;
import com.suneetsrivastava.apifetchtask.Application;
import com.suneetsrivastava.apifetchtask.Data.Post;
import com.suneetsrivastava.apifetchtask.R;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    public  List<Post> postList;
    private OkHttpClient client = new OkHttpClient();
    public static final String BASE_URL = Application.BASE_POST_URL;
    private RecyclerView postRecyclerView;
    private int permission;
    private PostRecyclerAdapter postRecyclerAdapter;
    private String result;
    View v;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postList = new ArrayList<>();
        postRecyclerView = (RecyclerView) findViewById(R.id.post_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        postRecyclerView.setLayoutManager(layoutManager);
        v = this.findViewById(R.id.activity_main);
        if(!checkPermission()){
            requestPermissions(new String []{Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE},1);
        }

        if(!isOnline()) {

            Snackbar snackbar = Snackbar.make(v,"Turn on Your Internet",Snackbar.LENGTH_SHORT);
            snackbar.show();

        }
        else{
                v.setBackground(null);
                fetchData();

        }


    }

    private boolean checkPermission(){
        permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        return (permission ==1);
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void fetchData() {
        NetworkFetch networkFetch = new NetworkFetch();
        networkFetch.execute();

    }

    private class NetworkFetch extends android.os.AsyncTask<Void, Void, Void> {
        private Request request;
        private Response response;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                request = new Request.Builder().url(BASE_URL).build();
                response = client.newCall(request).execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setData();

        }
    }

    public void setData(){

        Gson gson = new Gson();
        Type type = new TypeToken<List<Post>>(){}.getType();
        postList = gson.fromJson(result,type);
        postRecyclerAdapter = new PostRecyclerAdapter(this,postList);
        postRecyclerView.setAdapter(postRecyclerAdapter);

    }
}
