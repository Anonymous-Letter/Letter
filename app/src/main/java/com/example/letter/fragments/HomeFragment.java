package com.example.letter.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.letter.Letter;
import com.example.letter.LettersAdapter;
import com.example.letter.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private SwipeRefreshLayout swipeContainer;

    public static final String TAG = "HomeFragment";
    private Spinner spCategories;
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
        spCategories = view.findViewById(R.id.spCategories);
        ParseObject.registerSubclass(Letter.class);
        //create a list of items for the spinner.
        String[] items = new String[]{"All", "Friendship", "Love", "Study", "Work", "Other"};
        //adapter created to describe how the items are displayed. 'this' is used instead of getContext() in some samples
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        spCategories.setAdapter(catAdapter);

        String selected = spCategories.getSelectedItem().toString();
        queryLetters(selected);

        rvLetters = view.findViewById(R.id.rvLetters);
        allLetters = new ArrayList<>();
        adapter = new LettersAdapter(getContext(), allLetters);
        rvLetters.setAdapter(adapter);
        rvLetters.setLayoutManager(new LinearLayoutManager(getContext()));

        //swipe to refresh features
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                queryLetters(selected);
                swipeContainer.setRefreshing(false);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    protected void queryLetters(String category) {
        ParseQuery<Letter> query = ParseQuery.getQuery(Letter.class);
        if ( category == "All" ) {}
        else {
            query.whereContains("category", category);
        }
        query.findInBackground(new FindCallback<Letter>() {
            @Override
            public void done(List<Letter> letters, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting letters", e);
                    return;
                }
                for (Letter letter : letters) {
                    try {
                        Log.i(TAG, "Letters: " + letter.getTitle() + ", username: " + letter.getUser().fetchIfNeeded().getUsername());
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }

                allLetters.addAll(letters);
                adapter.notifyDataSetChanged();
            }
        });
    }
}