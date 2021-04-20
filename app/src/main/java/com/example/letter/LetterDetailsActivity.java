package com.example.letter;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LetterDetailsActivity extends AppCompatActivity {

    private Button btnCancel;
    private TextView tvContent;
    private Button btnReply;

    public String letterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_letterdetails);
        tvContent = findViewById(R.id.tvContent);
        btnReply = findViewById(R.id.btnReply);

        tvContent.setText(letterId);
    }

    public void passData(String letterId) {
        this.letterId = letterId;
    }
}