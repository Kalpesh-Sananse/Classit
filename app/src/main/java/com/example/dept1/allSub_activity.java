package com.example.dept1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class allSub_activity extends AppCompatActivity {

    DatabaseReference databaseReference;
    Context context;
    ArrayList<ReadWriteSubDetails> list;
    RecyclerView recyclerView;
    mySubAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sub);
        context = this;

        recyclerView = findViewById(R.id.allsubrecycler);
        databaseReference = FirebaseDatabase.getInstance().getReference("Subject");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new mySubAdapter(this,list);
        recyclerView.setAdapter(adapter);

    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                ReadWriteSubDetails readWriteSubDetails = dataSnapshot.getValue(ReadWriteSubDetails.class);
                list.add(readWriteSubDetails);
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

    }


}