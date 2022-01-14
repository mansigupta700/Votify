package com.example.votify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().setTitle("HomePage");

        Button poll = findViewById(R.id.poll);
        poll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreatePoll.class);
                startActivity(i);
            }
        });

        Button vote = findViewById(R.id.vote);
        vote.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), VotingPage.class);
                startActivity(i);
            }
        });

        Button results = findViewById(R.id.results);
        results.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ResultsPage.class);
                startActivity(i);
            }
        });

        Button about = findViewById(R.id.about);
        about.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), About.class);
                startActivity(i);
            }
        });

    }
}