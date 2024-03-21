package com.example.dept1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class myNotesActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    Context context;
    myPdfadapter adapter;
    ArrayList<ReadWritePdfDetails> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_notes);
        Intent intent =  getIntent();
        String subName = intent.getStringExtra("subname");

        context = this;
        recyclerView = findViewById(R.id.myreyclerview);
        databaseReference = FirebaseDatabase.getInstance().getReference("pdf");

        list =new ArrayList<>();
        Query query  = databaseReference.orderByChild("subjectName").equalTo(subName);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new myPdfadapter(this,list);
        recyclerView.setAdapter(adapter);


       query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ReadWritePdfDetails readWritePdfDetails = dataSnapshot.getValue(ReadWritePdfDetails.class);
                    list.add(readWritePdfDetails);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        TextView textView = findViewById(R.id.textviewhead);
        textView.setText(subName);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myNotesActivity.this, UploadPdf.class);
                intent.putExtra("subname",subName);
                startActivity(intent);
            }
        });

    }
}