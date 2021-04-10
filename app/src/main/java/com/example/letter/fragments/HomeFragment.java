package com.example.letter.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letter.Letter;
import com.example.letter.LettersAdapter;
import com.example.letter.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    private RecyclerView rvCategories;
    private RecyclerView rvLetters;
    protected LettersAdapter adapter;
    protected List<Letter> allLetters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCategories = view.findViewById(R.id.rvCategories);
        rvLetters = view.findViewById(R.id.rvLetters);

        allLetters = new ArrayList<>();
        adapter = new LettersAdapter(getContext(), allLetters);
        rvLetters.setAdapter(adapter);
        rvLetters.setLayoutManager(new LinearLayoutManager(getContext()));

        queryLetters();
    }

    protected void queryLetters() {
        ParseQuery<Letter> query = ParseQuery.getQuery(Letter.class);
        query.include(Letter.KEY_AUTHOR);
        query.addDescendingOrder(Letter.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Letter>() {
            @Override
            public void done(List<Letter> letters, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting letters", e);
                    return;
                }
                for (Letter letter : letters) {
                    Log.i(TAG, "Letters: " + letter.getTitle() + ", username: " + letter.getUser().getUsername());
                }

                allLetters.addAll(letters);
                adapter.notifyDataSetChanged();
            }
        });
    }
}