package com.example.dept1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {

    TextView signup;
    EditText username ,pass;


    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_loginactivity);

        btn = findViewById(R.id.loginbutton);
        username = findViewById(R.id.susername);
        pass = findViewById(R.id.spass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usern =  username.getText().toString();
                String userp = pass.getText().toString();

                if(TextUtils.isEmpty(usern)){
                    Toast.makeText(loginactivity.this, "Username can not be empty", Toast.LENGTH_SHORT).show();
                    username.setError("please enter username");
                    username.requestFocus();
                }else if(TextUtils.isEmpty(userp)){
                    Toast.makeText(loginactivity.this, "Enter a password", Toast.LENGTH_SHORT).show();
                    pass.setError("please enter a password");
                    pass.requestFocus();
                }else{
                    loginUser(usern,userp);
                }
            }

            private void loginUser(String usern, String userp) {

                FirebaseAuth authProfile = FirebaseAuth.getInstance();
                authProfile.signInWithEmailAndPassword(usern,userp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent intent = new Intent(loginactivity.this,studentPanel.class);
                            intent.putExtra("username",usern);
                            startActivity(intent);
                            username.setText("");
                            pass.setText("");
                            Toast.makeText(loginactivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(loginactivity.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}