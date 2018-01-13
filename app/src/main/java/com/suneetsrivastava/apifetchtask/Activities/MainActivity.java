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
import android.view.View;

import com.suneetsrivastava.apifetchtask.Adapter.PostRecyclerAdapter;
import com.suneetsrivastava.apifetchtask.Application;
import com.suneetsrivastava.apifetchtask.Data.OkHttpConnector;
import com.suneetsrivastava.apifetchtask.Data.Post;
import com.suneetsrivastava.apifetchtask.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  List<Post> postList;
    private OkHttpConnector connector;
    public static final String BASE_URL = Application.BASE_POST_URL;
    private RecyclerView postRecyclerView;
    private int permission;
    private PostRecyclerAdapter postRecyclerAdapter;
    View v;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connector = new OkHttpConnector();
        postList = new ArrayList<>();
        postRecyclerView = (RecyclerView) findViewById(R.id.post_recyclerView);
        postRecyclerAdapter = new PostRecyclerAdapter(this,postList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        postRecyclerView.setLayoutManager(layoutManager);
        postRecyclerView.setAdapter(postRecyclerAdapter);
        v = this.findViewById(R.id.activity_main);
        if(!checkPermission()){
            requestPermissions(new String []{Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE},1);
        }

        if(!isOnline()) {

            Snackbar snackbar = Snackbar.make(v,"Turn on Your Internet",Snackbar.LENGTH_SHORT);
            snackbar.show();

        }
        else{



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

    private void FetchData(){

    }
}
