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
import androidx.recyclerview.widget.RecyclerView;

import com.example.letter.LoginActivity;
import com.example.letter.MainActivity;
import com.example.letter.R;
import com.parse.ParseUser;


public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";
    private Button btnLogout;
    private Button btnSetting;
    private RecyclerView rvReply;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnSetting = view.findViewById(R.id.btnSetting);
        rvReply = view.findViewById(R.id.rvReply);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                goLoginActivity();
            }
        });


        btnSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goSetting();
            }
        });
    }

    private void goLoginActivity() {

        Intent i = new Intent(getContext(), LoginActivity.TAG.getClass());
        startActivity(i);
    }

    private void goSetting() {

    }
}