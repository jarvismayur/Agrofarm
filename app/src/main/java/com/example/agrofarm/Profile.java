package com.example.agrofarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    public EditText fname, lname, date, mobno;
    public Button but;
    public FirebaseDatabase database;
    public DatabaseReference reference;
    public FirebaseAuth mAuth;
    public String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fname = findViewById(R.id.editText);
        lname = findViewById(R.id.editText2);
        date = findViewById(R.id.editText3);
        mobno = findViewById(R.id.editText4);
        but = findViewById(R.id.but1);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 user = mAuth.getCurrentUser().getUid();
                reference = database.getReference().child("AuthDetails").child("Profile").child(user);

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Intent intent = new Intent(Profile.this, Launch.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        reference.child("fname").setValue(fname.getText().toString().trim());
                        reference.child("lname").setValue(lname.getText().toString().trim());
                        reference.child("date").setValue(date.getText().toString().trim());
                        reference.child("mobno").setValue(mobno.getText().toString().trim());
                    }
                });


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        reference = database.getReference().child("AuthDetails").child("Profile").child(user).child("fname");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                if (data == null){
                    startActivity(new Intent(Profile.this, Launch.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
