package com.suneetsrivastava.apifetchtask.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        Post p = postList.get(position);
        holder.user_id.setText(p.getUserId());
        holder.id.setText(p.getId());
        holder.title.setText(p.getTitle());
        holder.body.setText(p.getBody());
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

        public ViewHolder(View itemView) {
            super(itemView);
            user_id = (TextView) itemView.findViewById(R.id.user_id);
            id = (TextView) itemView.findViewById(R.id.post_id);
            title = (TextView) itemView.findViewById(R.id.post_title);
            body = (TextView) itemView.findViewById(R.id.post_body);
        }
    }
}
