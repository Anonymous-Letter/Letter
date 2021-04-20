package com.example.letter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LettersAdapter extends RecyclerView.Adapter<LettersAdapter.ViewHolder> {

    Context context;
    List<Letter> letters;

    public LettersAdapter(Context context, List<Letter> letters) {
        this.context = context;
        this.letters = letters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_letter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        private Button btnOpen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            btnOpen = itemView.findViewById(R.id.btnOpen);
        }

        public void bind(Letter letter) {
            // Bind the post data to the view elements
            tvTitle.setText(letter.getTitle());
            tvCategory.setText(letter.getCategory());

            btnOpen.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, LetterDetailsActivity.class);
                    i.putExtra("LETTER_ID", letter.getObjectId());
                    context.startActivity(i);
                }
            });
        }

    }
}
