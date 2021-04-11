package com.example.agrofarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class current_reading extends AppCompatActivity {
    public TextView spin;
    public FirebaseDatabase database;
    public String ref;
    public DatabaseReference reference;
    public FirebaseAuth mAuth;
    public ArrayList<String> list;
    public ListView l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_reading);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        l = findViewById(R.id.list);
        list = new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        l.setAdapter(arrayAdapter);
        spin =(TextView)findViewById(R.id.t1);



        database  = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Current_reading");
        final String user = mAuth.getCurrentUser().getUid();

        ref  = "Current_reading";
        reference = database.getReference().child(ref);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.w("E_value", "onDataChange: DAta"+  dataSnapshot.getValue());
                String Data = dataSnapshot.getValue(String.class);
                list.add(Data);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}



