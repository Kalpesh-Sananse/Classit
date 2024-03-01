package com.example.dept1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TeacherPanel extends AppCompatActivity {


    TextView textView;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);
        Intent intent = getIntent();
        username =   intent.getStringExtra("Username");
         textView = findViewById(R.id.textviewusername);


        textView.setText("Welcome "+username);


    }
}