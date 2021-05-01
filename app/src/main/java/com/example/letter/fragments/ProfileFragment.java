package com.example.letter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letter.Letter;
import com.example.letter.LettersAdapter;
import com.example.letter.ProfileAdapter;
import com.example.letter.R;
import com.example.letter.SettingsActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";
    private Button btnSetting;
    private RecyclerView rvProfLetters;
    protected ProfileAdapter adapter;
    protected List<Letter> allLetters;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ParseObject.registerSubclass(Letter.class);
        btnSetting = view.findViewById(R.id.btnSetting);
        rvProfLetters = view.findViewById(R.id.rvProfLetters);

        allLetters = new ArrayList<>();
        adapter = new ProfileAdapter(getContext(), allLetters);
        rvProfLetters.setAdapter(adapter);
        rvProfLetters.setLayoutManager(new LinearLayoutManager(getContext()));

        queryLetters();

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSetting();
            }
        });
    }

    private void goSetting() {
        Intent i = new Intent(getActivity(), SettingsActivity.class);
        startActivity(i);
    }

    protected void queryLetters() {
        ParseQuery<Letter> query = ParseQuery.getQuery(Letter.class);
        query.include(Letter.KEY_AUTHOR);
        query.whereEqualTo(Letter.KEY_AUTHOR, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Letter.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Letter>() {
            @Override
            public void done(List<Letter> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting letters", e);
                    return;
                }
                for(Letter letter: allLetters){
                    try {
                        Log.i(TAG, "Post: " +letter.getContent() + ", username: " + letter.getUser().fetchIfNeeded().getUsername());
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
                allLetters.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

}