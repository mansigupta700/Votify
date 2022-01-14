package com.example.votify;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VotingPage extends AppCompatActivity {
    RadioButton person1;
    RadioButton person2;
    RadioButton person3;
    RadioButton person4;
    RadioGroup radioGroup;
    EditText votecode, name;
    Button showCand;
    Button submitvote;
    DatabaseReference reference;
    FirebaseDatabase database;
    Voting voting;
    TextView countVotes;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_page);
        getSupportActionBar().setTitle("Voting Page");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        submitvote = findViewById(R.id.vote);
        votecode = findViewById(R.id.vote_code);
        showCand = findViewById(R.id.show_cand);
        person1 = findViewById(R.id.radio1);
        person2 = findViewById(R.id.radio2);
        person3 = findViewById(R.id.radio3);
        person4 = findViewById(R.id.radio4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        name = findViewById(R.id.name);
        voting = new Voting();

        reference = FirebaseDatabase.getInstance().getReference("Voting");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(VotingPage.this, "Select an option", Toast.LENGTH_SHORT).show();
            }
        });


        showCand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vote_code = votecode.getText().toString();
                if (!vote_code.isEmpty()) {
                    readData(vote_code);
                    showCand.setEnabled(false);
                } else {
                    Toast.makeText(VotingPage.this, "Entercode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submitvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !(radioGroup.getCheckedRadioButtonId() == -1) &&
                                (!TextUtils.isEmpty(name.getText().toString())))
                {
                    submitVote();
                    submitvote.setEnabled(false);

                }
                else {
                    Toast.makeText(VotingPage.this, "Vote not submitted", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void readData(String vote_code) {
        reference = FirebaseDatabase.getInstance().getReference("Candidates");
        reference.child(vote_code).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String Person1 = String.valueOf(dataSnapshot.child("person1").getValue());
                        String Person2 = String.valueOf(dataSnapshot.child("person2").getValue());
                        String Person3 = String.valueOf(dataSnapshot.child("person3").getValue());
                        String Person4 = String.valueOf(dataSnapshot.child("person4").getValue());
                        person1.setText(Person1);
                        person2.setText(Person2);
                        person3.setText(Person3);
                        person4.setText(Person4);
                    } else {
                        Toast.makeText(VotingPage.this, "No such code", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(VotingPage.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void submitVote(){
        reference = FirebaseDatabase.getInstance().getReference("Voting");
        String P1 = person1.getText().toString();
        String P2 = person2.getText().toString();
        String P3 = person3.getText().toString();
        String P4 = person4.getText().toString();
        String CODE = votecode.getText().toString();
        String Name = name.getText().toString();
        voting.setCode(votecode.getText().toString());
        reference.child(String.valueOf(i + 1)).setValue(voting);
        voting.setName(name.getText().toString());
        reference.child(String.valueOf(i + 1)).setValue(voting);
        if (person1.isChecked()) {
            voting.setSelectedCand(P1);
            reference.child(String.valueOf(i + 1)).setValue(voting);
        } else if (person2.isChecked()) {
            voting.setSelectedCand(P2);
            reference.child(String.valueOf(i + 1)).setValue(voting);
        } else if (person3.isChecked()) {
            voting.setSelectedCand(P3);
            reference.child(String.valueOf(i + 1)).setValue(voting);
        } else {
            voting.setSelectedCand(P4);
            reference.child(String.valueOf(i + 1)).setValue(voting);
        }
        Voting voting  = new Voting();
       reference.child(CODE).setValue(voting).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                person1.setChecked(false);
                person2.setChecked(false);
                person3.setChecked(false);
                person4.setChecked(false);
                name.setText("");
            }
        });
        Toast.makeText(VotingPage.this, "Voted", Toast.LENGTH_SHORT).show();
    }
}