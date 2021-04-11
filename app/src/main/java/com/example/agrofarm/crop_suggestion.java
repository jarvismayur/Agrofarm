package com.example.agrofarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class crop_suggestion extends AppCompatActivity {
    public FirebaseDatabase database;
    public String ref, id;
    public DatabaseReference reference;
    public FirebaseAuth mAuth;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_suggestion);
        textView = findViewById(R.id.t2);
        mAuth = FirebaseAuth.getInstance();
        id = mAuth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        ref = id + "/Crop/Crop_curr_sug";
        reference = database.getReference(ref);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Data = dataSnapshot.getValue(String.class);
                Log.w("E_Value", "Data    ="+ Data);
                textView.setText(Data);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(crop_suggestion.this, "Something Went Wrong Try Again", Toast.LENGTH_LONG).show();
            }
        });
    }
}
