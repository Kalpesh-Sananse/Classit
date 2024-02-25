package com.example.dept1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class loginoptionactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_loginoptionactivity);

        Button  studentlogin = findViewById(R.id.loginasstudent);
        Button adminlogin = findViewById(R.id.loginasadmin);
        Button teacheradmin = findViewById(R.id.loginasteacher);

        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginoptionactivity.this, loginactivity.class);
                startActivity(intent);
            }
        });

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginoptionactivity.this, loginadminactivity.class);
                startActivity(intent);
            }
        });

        teacheradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginoptionactivity.this,loginTeacher.class);
                startActivity(intent);
            }
        });
    }
}