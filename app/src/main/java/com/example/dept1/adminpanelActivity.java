package com.example.dept1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class adminpanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        CardView addfacultyCard = findViewById(R.id.addfac);

        TextView tv = findViewById(R.id.textviewhead);


            tv.setText(name);
        addfacultyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminpanelActivity.this, addFaculty.class);
                startActivity(intent);
            }
        });
    }
}