package com.suneetsrivastava.apifetchtask.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suneetsrivastava.apifetchtask.Data.Comments;
import com.suneetsrivastava.apifetchtask.R;

import java.util.List;

/**
 * Created by suneetsrivastava on 13/01/18.
 */

public class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentsRecyclerAdapter.ViewHolder> {

    Context c;
    List<Comments> comments;

    public CommentsRecyclerAdapter(Context c, List<Comments> comments) {
        this.c = c;
        this.comments = comments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(c).inflate(R.layout.comments, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            Comments c = comments.get(position);
            holder.user_id.setText(c.getId());
            holder.name.setText(c.getName());
            holder.email.setText(c.getEmail());
            holder.body.setText(c.getBody());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;
        private TextView user_id;
        private TextView body;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Name);
            email = (TextView) itemView.findViewById(R.id.email);
            user_id = (TextView) itemView.findViewById(R.id.user_id);
            body = (TextView) itemView.findViewById(R.id.body);

        }
    }
}
