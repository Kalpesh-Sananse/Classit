package com.example.dept1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class studentPanel extends AppCompatActivity {

    CardView snotescard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);

        snotescard = findViewById(R.id.Snotescard);

        snotescard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studentPanel.this, allSub_activity.class);
                startActivity(intent);

            }
        });

    }
}