package com.suneetsrivastava.apifetchtask.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.suneetsrivastava.apifetchtask.Adapter.CommentsRecyclerAdapter;
import com.suneetsrivastava.apifetchtask.Application;
import com.suneetsrivastava.apifetchtask.Data.Comments;
import com.suneetsrivastava.apifetchtask.R;

import org.w3c.dom.Comment;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Comments_Activity extends AppCompatActivity{
    List<Comments> comments = new ArrayList<>();
    List<Comments> fetchedComments;
    private CommentsRecyclerAdapter commentsRecyclerAdapter;
    private static String result;
    private static String postId;
    private static String title;
    private static String body;
    private String adapterPosition;
    private RecyclerView commentRecyclerView;
    private TextView post_idtextView;
    private TextView titletextView;
    private TextView bodytextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        commentRecyclerView = (RecyclerView) findViewById(R.id.comments_recyclerview);
        post_idtextView = (TextView) findViewById(R.id.comments_postId);
        titletextView = (TextView) findViewById(R.id.comments_title);
        bodytextView = (TextView) findViewById(R.id.comments_body);
        FetchAPI fetchAPI  = new FetchAPI();
        fetchAPI.execute(Application.BASE_COMMENTS_URL);
        Intent i = getIntent();
        postId = i.getStringExtra("post_id");
        title = i.getStringExtra("title");
        body = i.getStringExtra("body");
        adapterPosition = i.getStringExtra("position");
        post_idtextView.setText(postId);
        titletextView.setText(title);
        bodytextView.setText(body);



    }



    private class FetchAPI extends AsyncTask<String, Void, Void> {
        private OkHttpClient client = new OkHttpClient();
        private Response response;
        private Request request;



        @Override
        protected Void doInBackground(String... strings) {
            request = new Request.Builder().url(strings[0]).build();
            try {
                response = client.newCall(request).execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Comments>>(){}.getType();
            comments = gson.fromJson(result, type);
            Log.e("TAG", "onPostExecute: "+comments.get(1).getName() );

        }
    }

    private List<Comments> getFetchedComments(){
        fetchedComments = new ArrayList<>();

        return fetchedComments;
    }
}
