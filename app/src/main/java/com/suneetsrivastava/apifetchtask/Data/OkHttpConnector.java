package com.suneetsrivastava.apifetchtask.Data;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by suneetsrivastava on 13/01/18.
 */

public class OkHttpConnector {

    OkHttpClient okHttpClient;
    Request request;
    public OkHttpConnector(){
        okHttpClient = new OkHttpClient();
    }

    public String run(String url) throws IOException {
        request = new Request.Builder().url(url).build();
        try(Response response = okHttpClient.newCall(request).execute()){
            return  response.body().string();
        }


    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
