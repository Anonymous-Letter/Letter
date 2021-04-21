package com.example.letter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LetterDetailsActivity extends AppCompatActivity {

    private TextView tvHead;
    private TextView tvContent;
    private Button btnReply;

    private String letterId;
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Letter");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_letterdetails);
        letterId = getIntent().getStringExtra("LETTER_ID");
        tvHead = findViewById(R.id.tvHead);
        tvContent = findViewById(R.id.tvContent);
        btnReply = findViewById(R.id.btnReply);

        query.getInBackground(letterId, (letter, error) -> {
            if (error == null) {
                String content = letter.getString("content");
                String head = letter.getString("title");
                tvHead.setText(head);
                tvContent.setText(content);
            }
            else {
                // something went wrong
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnReply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goReply();
            }
        });
    }

    private void goReply() {
        Intent i = new Intent(this, ReplyActivity.class);
        i.putExtra("REPLY_LETTER_ID", letterId);
        startActivity(i);
    }
}