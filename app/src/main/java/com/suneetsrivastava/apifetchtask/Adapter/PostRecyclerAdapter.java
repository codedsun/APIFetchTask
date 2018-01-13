package com.suneetsrivastava.apifetchtask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suneetsrivastava.apifetchtask.Activities.Comments_Activity;
import com.suneetsrivastava.apifetchtask.Data.Post;
import com.suneetsrivastava.apifetchtask.R;

import java.util.List;

/**
 * Created by suneetsrivastava on 12/01/18.
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {
    Context c;
    private List<Post> postList;


    public PostRecyclerAdapter(Context c, List<Post> postList) {
        this.c = c;
        this.postList = postList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(c).inflate(R.layout.post_view,parent,false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("TAG", "onCreateViewHolder: "+postList.size() +postList.get(2).getTitle());
        holder.id.setText(""+ postList.get(position).getId());
        holder.body.setText(postList.get(position).getBody());
        holder.user_id.setText(""+postList.get(position).getUserId());
        holder.title.setText(postList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView user_id;
        private TextView id;
        private TextView title;
        private TextView body;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("SUNEET", "onClick: "+getAdapterPosition());
                    Intent i = new Intent(c, Comments_Activity.class);
                    i.putExtra("position",getAdapterPosition());
                    i.putExtra("post_id",id.getText().toString());
                    i.putExtra("body",body.getText().toString());
                    i.putExtra("title",title.getText().toString());
                    c.startActivity(i);
                }
            });
            user_id = (TextView) itemView.findViewById(R.id.post_userid);
            id = (TextView) itemView.findViewById(R.id.post_id);
            title = (TextView) itemView.findViewById(R.id.post_title);
            body = (TextView) itemView.findViewById(R.id.post_body);
        }
    }
}
