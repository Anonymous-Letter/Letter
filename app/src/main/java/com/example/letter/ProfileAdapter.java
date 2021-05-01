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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder>  {

    public static final String TAG = "ProfileAdapter";
    Context context;
    List<Letter> letters;
    protected ReplyAdapter adapter;
    protected List<Reply> allReplies;

    public ProfileAdapter(Context context, List<Letter> letters) {
        this.context = context;
        this.letters = letters;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_letter, parent, false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        Letter letter = letters.get(position);
        holder.bind(letter);
    }

    @Override
    public int getItemCount() {
        return letters.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        letters.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Letter> letterList) {
        letters.addAll(letterList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvCategory;
        private RecyclerView rvReplies;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            rvReplies =itemView.findViewById(R.id.rvReplies);
        }

        public void bind(Letter letter) {
            // Bind the post data to the view elements
            tvTitle.setText(letter.getTitle());
            tvCategory.setText(letter.getCategory());

            queryReplies(letter);
        }

        protected void queryReplies(Letter letter) {
            ParseObject.registerSubclass(Reply.class);
            ParseQuery<Reply> query = ParseQuery.getQuery(Reply.class);
            query.whereEqualTo("letter", letter);

            query.findInBackground(new FindCallback<Reply>() {
                @Override
                public void done(List<Reply> replies, ParseException e) {
                    if(e != null){
                        Log.e(TAG, "Issue with getting replies", e);
                        return;
                    }
                    for(Reply reply: replies){
                        try {
                            Log.i(TAG, "Reply: " + reply.getContent() + ", username: " + reply.getUser().fetchIfNeeded().getUsername() + reply.getObjectId());
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                    }

                    adapter = new ReplyAdapter(context, replies);
                    adapter.notifyDataSetChanged();
                    rvReplies.setAdapter(adapter);
                    rvReplies.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                }
            });
        }
    }
}

