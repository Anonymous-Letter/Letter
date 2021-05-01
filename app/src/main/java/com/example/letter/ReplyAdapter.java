package com.example.letter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseException;

import java.util.List;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder> {

    Context context;
    List<Reply> replies;

    public ReplyAdapter(Context context, List<Reply> replies) {
        this.context = context;
        this.replies = replies;
    }

    @NonNull
    @Override
    public ReplyAdapter.ReplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_reply, parent, false);
        return new ReplyAdapter.ReplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyAdapter.ReplyViewHolder holder, int position) {
        Reply reply = replies.get(position);
        holder.tvUser.setText(reply.getUser().getUsername());
        holder.tvReply.setText(reply.getContent());
        Log.i("ReplyAdapter", "Position" + position + " " + reply.getContent() +" " + reply.getUser().getUsername());
    }

    @Override
    public int getItemCount() {

        Log.i("ReplyAdapter", "Size" + replies.size());
        return replies.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        replies.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Reply> replyList) {
        replies.addAll(replyList);
        notifyDataSetChanged();
    }
    public class ReplyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUser;
        private TextView tvReply;

        public ReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvReply = itemView.findViewById(R.id.tvReply);

        }
    }


}
