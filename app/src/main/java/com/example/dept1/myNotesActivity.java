package com.example.dept1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class myNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent =  getIntent();
        String subName = intent.getStringExtra("subname");
        setContentView(R.layout.activity_my_notes);

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