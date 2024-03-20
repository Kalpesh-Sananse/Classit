package com.example.dept1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class loginTeacher extends AppCompatActivity {


    EditText Username, Password;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.password);
        Button Tlogin = findViewById(R.id.Tloginbutton);
        db = new DBHelper(this);

        Tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String TUsername = Username.getText().toString().trim();
                String TPassword = Password.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Faculty");
                if(TextUtils.isEmpty(TUsername)){
                    Toast.makeText(loginTeacher.this, "Username should not be empty", Toast.LENGTH_SHORT).show();
                    Username.setError("Username is required");
                    Username.requestFocus();
                }else if(TextUtils.isEmpty(TPassword)){
                    Toast.makeText(loginTeacher.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    Password.setError("password is required");
                    Password.requestFocus();
                }else{

                    Query checkUserDatabase = reference.orderByChild("regno").equalTo(TUsername);

                    checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                 String passwordfromDb = snapshot.child(TUsername).child("pass").getValue(String.class);
                                 if(passwordfromDb.equals(TPassword)){
                                     Intent intent = new Intent(loginTeacher.this, TeacherPanel.class);
                                     intent.putExtra("name",TUsername);
                                     startActivity(intent);
                                 }else{
                                     Toast.makeText(loginTeacher.this, "invalid ", Toast.LENGTH_SHORT).show();
                                 }
                            }else {
                                Toast.makeText(loginTeacher.this, "User doesnt exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            }

                /*    private void loginUser(String tUsername, String tPassword) {

                FirebaseAuth authProfile = FirebaseAuth.getInstance();
                authProfile.signInWithEmailAndPassword(tUsername,tPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(loginTeacher.this, "You are logged in", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(loginTeacher.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
            }
        });
    }
}