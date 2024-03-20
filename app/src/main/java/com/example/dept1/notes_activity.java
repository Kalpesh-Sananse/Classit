package com.example.dept1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class notes_activity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    ArrayList<ReadWriteSubDetails> list;
    DatabaseReference   databaseReference;
    mySubAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        context = this  ;
        Intent intent = getIntent();
       String username =   intent.getStringExtra("username");

        recyclerView = findViewById(R.id.recyclerviewnotes);
        databaseReference = FirebaseDatabase.getInstance().getReference("Subject");
        list = new ArrayList<>();
        Query query = databaseReference.orderByChild("createdby").equalTo(username);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new mySubAdapter(this,list);
        recyclerView.setAdapter(adapter);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    ReadWriteSubDetails readWriteSubDetails = dataSnapshot.getValue(ReadWriteSubDetails.class);
                    list.add(readWriteSubDetails);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setup the alert builder

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                String items [] ={"Create Subject","Upload in Existing"};
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(notes_activity.this, create_subject.class);
                                intent.putExtra("username",username);
                                startActivity(intent);
                                break;
                            case 1:
                                Toast.makeText(notes_activity.this, "ok", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                AlertDialog dialog1 = dialog.create();
                dialog1.show();

            }
        });
    }
}