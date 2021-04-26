package com.example.letter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RepliesContentAdapter extends RecyclerView.Adapter<RepliesContentAdapter.ViewHolder> {
    Context context;
    List<Reply> replies;

    public RepliesContentAdapter(Context context, List<Reply> replies) {
        this.context = context;
        this.replies = replies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_repliescontent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepliesContentAdapter.ViewHolder holder, int position) {
        Reply reply = replies.get(position);
        holder.bind(reply);
    }

    @Override
    public int getItemCount() {
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRplContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRplContent = itemView.findViewById(R.id.tvRplContent);
        }

        public void bind(Reply reply) {
            // Bind the post data to the view elements
            tvRplContent.setText(reply.getContent());
        }
    }
}
