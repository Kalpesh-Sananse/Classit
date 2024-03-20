package com.example.dept1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create_subject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);
        Intent intent = getIntent();

        String createdby = intent.getStringExtra("username");
         TextView textViewhead = findViewById(R.id.textviewusernamesub);

        EditText subname, tname;
        subname  = findViewById(R.id.sname);
        tname = findViewById(R.id.Tname);
        Button cSub = findViewById(R.id.csub);
        textViewhead.setText(createdby);

        cSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subnameU =  subname.getText().toString().trim();
                String tnameU = tname.getText().toString().trim();
                if(TextUtils.isEmpty(subnameU)){
                    Toast.makeText(create_subject.this, "Subject Name is required", Toast.LENGTH_SHORT).show();
                    subname.setError("Subject name is Required");
                    subname.requestFocus();
                } else if (TextUtils.isEmpty(tnameU)) {
                    Toast.makeText(create_subject.this,"Teacher Name is required",Toast.LENGTH_LONG).show();
                    tname.setError("Teacher name is required");
                    tname.requestFocus();


                }else{
                    createSubject(subnameU,tnameU,createdby);
                }
            }

            private void createSubject(String subnameU, String tnameU,String createdby) {
                ReadWriteSubDetails readWriteSubDetails = new ReadWriteSubDetails(subnameU,tnameU,createdby);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Subject");
                reference.child(subnameU).setValue(readWriteSubDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(create_subject.this, "subject created successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(create_subject.this, "Subject not created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}