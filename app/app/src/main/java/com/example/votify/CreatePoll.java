package com.example.votify;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Random;

public class CreatePoll extends AppCompatActivity {
    Button generatecode;
    Button createpoll;
    Button shareCode;
    Button copytext;
    Button clear;
    EditText person1;
    EditText person2;
    EditText person3;
    EditText person4;
    EditText pollcode;

    DatabaseReference votify;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);
        getSupportActionBar().setTitle("Create Poll");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        person1 = findViewById(R.id.Person1);
        person2 = findViewById(R.id.Person2);
        person3 = findViewById(R.id.Person3);
        person4 = findViewById(R.id.Person4);
        pollcode = findViewById(R.id.code);
        if(person1.getText().toString().length()<1){
            person1.setFocusableInTouchMode(true);
            person1.requestFocus();
        }
        votify = FirebaseDatabase.getInstance().getReference().child("Candidates");
        reference = FirebaseDatabase.getInstance().getReference().child("Results");

        createpoll=findViewById(R.id.createpoll);
        createpoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!TextUtils.isEmpty(person1.getText().toString())) &&
                        (!TextUtils.isEmpty(person2.getText().toString())) &&
                        (!TextUtils.isEmpty(pollcode.getText().toString())) &&
                        (!TextUtils.isEmpty(person3.getText().toString())) &&
                        (!TextUtils.isEmpty(person4.getText().toString())) &&
                                (!TextUtils.isEmpty(pollcode.getText().toString())))
                {
                    insertCandidateData();
                    createpoll.setEnabled(false);
                }
                else{
                    Toast.makeText(CreatePoll.this, "No complete data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        copytext = findViewById(R.id.copytext);
        copytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!TextUtils.isEmpty(pollcode.getText().toString()))) {
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("CopyText", pollcode.getText().toString());
                    clipboardManager.setPrimaryClip(clip);
                    Toast.makeText(CreatePoll.this, "Copied", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CreatePoll.this, "No code to copy", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shareCode = findViewById(R.id.sharecode);
        shareCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "";
                title += "Here's your code- " + pollcode.getText();
                if((!TextUtils.isEmpty(pollcode.getText().toString()))) {
                    Intent shareintent = new Intent();
                    shareintent.setAction(Intent.ACTION_SEND);
                    shareintent.putExtra(Intent.EXTRA_TEXT, title );
                    shareintent.setType("text/plain");
                    startActivity(shareintent);
                }else{
                    Toast.makeText(CreatePoll.this, "No code to share", Toast.LENGTH_SHORT).show();
                }
            }

            });

        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person1.getText().clear();
                person2.getText().clear();
                person3.getText().clear();
                person4.getText().clear();
            }
        });

        pollcode = findViewById(R.id.code);
        generatecode=findViewById(R.id.gencode);
        generatecode.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Random random = new Random();
                                                int val = random.nextInt(999999);
                                                pollcode.setText(Integer.toString(val));
                                            }
                                        }
        );
    }
    private void insertCandidateData(){
        String P1 = person1.getText().toString();
        String P2 = person2.getText().toString();
        String P3 = person3.getText().toString();
        String P4 = person4.getText().toString();
        String CODE = pollcode.getText().toString();
        Candidates candidates = new Candidates(P1,P2,P3,P4,CODE);
        Results results = new Results(P1,P2,P3,P4,CODE);
        votify.child(CODE).setValue(candidates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pollcode.setText("");
                person1.setText("");
                person2.setText("");
                person3.setText("");
                person4.setText("");
            }
        });
        reference.child(CODE).setValue(results).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pollcode.setText("");
                person1.setText("");
                person2.setText("");
                person3.setText("");
                person4.setText("");
            }
        });
        Toast.makeText(CreatePoll.this, "Data inserted", Toast.LENGTH_SHORT).show();

    }
}
