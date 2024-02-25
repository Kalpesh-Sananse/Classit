package com.example.dept1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.PasswordAuthentication;

public class loginadminactivity extends AppCompatActivity {


    EditText Username, password;

    Button btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_loginadminactivity);

        Username = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        btn = findViewById(R.id.loginbutton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textUsername = Username.getText().toString();
                String textpass = password.getText().toString();

                if(TextUtils.isEmpty(textUsername)){
                    Toast.makeText(loginadminactivity.this, "Username should not be empty", Toast.LENGTH_SHORT).show();
                    Username.setError("Username is required");
                    Username.requestFocus();
                }else if(TextUtils.isEmpty(textpass)){
                    Toast.makeText(loginadminactivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    password.setError("password is required");
                    password.requestFocus();
                }else {


                    if (textUsername.equals("admin") && textpass.equals("admin")) {
                        Intent intent = new Intent(loginadminactivity.this, adminpanelActivity.class);
                        startActivity(intent);
                    }

                    else{

                        Toast.makeText(loginadminactivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}