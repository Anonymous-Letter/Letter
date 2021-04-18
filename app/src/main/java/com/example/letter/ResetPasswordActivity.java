package com.example.letter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class ResetPasswordActivity extends AppCompatActivity {

    public static final String TAG = "CreateActivity";
    private EditText etUserPass;
    private EditText etNewPass;
    private Button resetPass2Btn;
    ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);

        etUserPass = findViewById(R.id.etUserPass);
        etNewPass = findViewById(R.id.etNewPass);
        resetPass2Btn = findViewById(R.id.resetPass2Btn);
        resetPass2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Sign Up button");
                String currPass = etUserPass.getText().toString();
                String newPass = etNewPass.getText().toString();

                ParseUser.logInInBackground(currentUser.getUsername(), currPass, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null) {
                            Log.e(TAG, "Wrong Password", e);
                            Toast.makeText(com.example.letter.ResetPasswordActivity.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        goResetPassword(newPass);
                        Toast.makeText(com.example.letter.ResetPasswordActivity.this, "Password Successful Changed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    void goResetPassword(String newPass){
        currentUser.setPassword(newPass);
        currentUser.saveInBackground();
        finish();
    }
}
