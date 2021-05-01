package com.example.letter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import static com.example.letter.fragments.ProfileFragment.TAG;

public class RepliesAdapter extends RecyclerView.Adapter<RepliesAdapter.ViewHolder> {

    // An object of RecyclerView.RecycledViewPool
    // is created to share the Views
    // between the child and
    // the parent RecyclerViews
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<Letter> letters;

    public RepliesAdapter(Context context, List<Letter> letters) {
        this.context = context;
        this.letters = letters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Letter letter = letters.get(position);
        holder.bind(letter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.rvReplies.getContext());
        holder.rvReplies.setLayoutManager(layoutManager);
        holder.rvReplies.setAdapter(holder.adapter);
        holder.rvReplies.setRecycledViewPool(viewPool);
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

        private TextView tvLetterHd;
        private RecyclerView rvReplies;
        private List<Reply> allReplies;
        private RepliesContentAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLetterHd = itemView.findViewById(R.id.tvLetterHd);
            rvReplies = itemView.findViewById(R.id.rvReplies);
        }

        public void bind(Letter letter) {
            // Bind the post data to the view elements
            tvLetterHd.setText(letter.getTitle());

            allReplies = new ArrayList<>();;
            adapter = new RepliesContentAdapter(allReplies);

            queryReply(letter);
        }

        protected void queryReply(Letter letter) {
            ParseQuery<Reply> replyParseQuery = ParseQuery.getQuery(Reply.class);
            replyParseQuery.addDescendingOrder(Letter.KEY_CREATED_AT);
            replyParseQuery.whereEqualTo("letter", letter);
            replyParseQuery.findInBackground(new FindCallback<Reply>() {

                @Override
                public void done(List<Reply> replies, ParseException e) {
                    if (e != null) {
                        Log.e(TAG, "Issue with getting replies", e);
                        return;
                    }
                    for (Reply reply : replies) {
                        try {
                            Log.i(TAG, "Replies: " + reply.getContent() + ", username: " + reply.getUser().fetchIfNeeded().getUsername());
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                        }
                    }

                    allReplies.addAll(replies);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
