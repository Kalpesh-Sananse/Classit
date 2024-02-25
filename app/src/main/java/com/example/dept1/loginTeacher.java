package com.example.dept1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginTeacher extends AppCompatActivity {


    EditText Username, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.password);
        Button Tlogin = findViewById(R.id.Tloginbutton);

        Tlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TUsername = Username.getText().toString();
                String TPassword = Password.getText().toString();
                if(TextUtils.isEmpty(TUsername)){
                    Toast.makeText(loginTeacher.this, "Username should not be empty", Toast.LENGTH_SHORT).show();
                    Username.setError("Username is required");
                    Username.requestFocus();
                }else if(TextUtils.isEmpty(TPassword)){
                    Toast.makeText(loginTeacher.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    Password.setError("password is required");
                    Password.requestFocus();
                }else{
                    loginUser(TUsername,TPassword);
                }
            }

            private void loginUser(String tUsername, String tPassword) {

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
                });
            }
        });
    }
}