package com.example.votify;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getSupportActionBar().setTitle("Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
}
