package com.example.letter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class SettingsActivity extends AppCompatActivity{
    public static final String TAG = "SettingsActivity";
    private TextView uNameDisp;
    private TextView emailDisp;
    private Button logoutBtn;
    private Button resetPassBtn;
    ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_settings);

        setContentView(R.layout.activity_settings);

        uNameDisp = findViewById(R.id.uNameDisp);
        emailDisp = findViewById(R.id.emailDisp);
        logoutBtn = findViewById(R.id.logoutBtn);
        resetPassBtn = findViewById(R.id.resetPassBtn);

        uNameDisp.setText(currentUser.getUsername());
        emailDisp.setText(currentUser.getEmail());

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Logout button");
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                goLoginActivity();
            }
        });
        resetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Reset Password 1 button");
                goResetPasswordActivity();
            }
        });
    }

    private void goLoginActivity() {

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private void goResetPasswordActivity() {

        Intent i = new Intent(this, ResetPasswordActivity.class);
        startActivity(i);
    }
}
