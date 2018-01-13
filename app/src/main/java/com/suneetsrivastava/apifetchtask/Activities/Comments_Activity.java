package com.suneetsrivastava.apifetchtask.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.suneetsrivastava.apifetchtask.Adapter.CommentsRecyclerAdapter;
import com.suneetsrivastava.apifetchtask.Data.Comments;
import com.suneetsrivastava.apifetchtask.R;


import java.util.List;

public class Comments_Activity extends AppCompatActivity {
    List<Comments> comments;
    List<Comments> fetchedComments;
    private CommentsRecyclerAdapter commentsRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
    }
}
