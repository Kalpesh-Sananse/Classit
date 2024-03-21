package com.example.dept1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TeacherPanel extends AppCompatActivity {


    TextView textView;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);
        Intent intent = getIntent();
        username =   intent.getStringExtra("name");
         textView = findViewById(R.id.textviewusername);


        textView.setText("Welcome "+username);

        CardView cardView = findViewById(R.id.Notescard);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherPanel.this, notes_activity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        CardView cardView1= findViewById(R.id.addstudent);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1  = new Intent(TeacherPanel.this,Register_activity.class);
                startActivity(intent1);
            }
        });

        CardView cardView2 = findViewById(R.id.allstdcard);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent1 = new Intent(TeacherPanel.this, allstudent.class);
            startActivity(intent1);
            }
        });

    }
}