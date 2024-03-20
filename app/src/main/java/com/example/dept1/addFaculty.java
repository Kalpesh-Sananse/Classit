package com.example.dept1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addFaculty extends AppCompatActivity {

    EditText name , email , pass, cnfpass,regno,phone;
    DBHelper db;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        name = findViewById(R.id.facname);
        phone = findViewById(R.id.facphoneno);
        email = findViewById(R.id.facemail);
        pass = findViewById(R.id.facpassword);
        cnfpass = findViewById(R.id.facconfirmpassword);
        regno = findViewById(R.id.facregnumber);

        db = new DBHelper(this);
        ProgressBar progressBar = findViewById(R.id.progressbar);


        Button facreg = findViewById(R.id.facregbtn);

        facreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facname = name.getText().toString().trim();
                String facphone = phone.getText().toString().trim();
                String facemail = email.getText().toString().trim();
                String facpass = pass.getText().toString().trim();
                String faccnfpass = cnfpass.getText().toString().trim();
                String facregno = regno.getText().toString().trim();


                /*String mobileRegex =" [6-9][0-9]{9}";
                Matcher mobileMatcher ;
                Pattern mobilePattern = Pattern.compile(mobileRegex);
                mobileMatcher =  mobilePattern.matcher(facphone);

*/

                if (TextUtils.isEmpty(facname)) {
                    Toast.makeText(addFaculty.this, "pleease enter a full name", Toast.LENGTH_LONG).show();
                    name.setError("Full name is required");
                    name.requestFocus();
                } else if (TextUtils.isEmpty(facemail)) {
                    Toast.makeText(addFaculty.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    email.setError("email is required");
                    email.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(facemail).matches()) {
                    Toast.makeText(addFaculty.this, "Please re-enter  your email", Toast.LENGTH_SHORT).show();
                    email.setError("valid email is required");
                    email.requestFocus();
                } else if (TextUtils.isEmpty(facphone)) {
                    Toast.makeText(addFaculty.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
                    phone.setError("phone number is required");
                    phone.requestFocus();
                } else if (facphone.length() != 10) {
                    Toast.makeText(addFaculty.this, "Please re-enter your mobile no.", Toast.LENGTH_SHORT).show();
                    phone.setError("Mobile number should be 10 digit");
                    phone.requestFocus();
                }
                /*else if (!mobileMatcher.find()){
                    Toast.makeText(addFaculty.this, "Please re-enter your mobile no.", Toast.LENGTH_SHORT).show();
                    phone.setError("Mobile number should be valid");
                    phone.requestFocus();
                }*/

                else if (TextUtils.isEmpty(facpass)) {
                    Toast.makeText(addFaculty.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    pass.setError("password is required");
                    pass.requestFocus();
                } else if (facpass.length() < 6) {
                    Toast.makeText(addFaculty.this, "Password should be at least 6 digit", Toast.LENGTH_SHORT).show();
                    pass.setError("password to weak");
                    pass.requestFocus();
                } else if (TextUtils.isEmpty(faccnfpass)) {
                    Toast.makeText(addFaculty.this, "Please confirm your password", Toast.LENGTH_SHORT).show();
                    cnfpass.setError("password confirmation required");
                    cnfpass.requestFocus();
                } else if (!facpass.equals(faccnfpass)) {
                    Toast.makeText(addFaculty.this, "Please enter same password", Toast.LENGTH_SHORT).show();
                    cnfpass.setError("password confirmation is requird");
                    cnfpass.requestFocus();

                    //clear the entered password
                    cnfpass.clearComposingText();
                    pass.clearComposingText();
                } else if (TextUtils.isEmpty(facregno)) {
                    Toast.makeText(addFaculty.this, "please enter registration number", Toast.LENGTH_SHORT).show();
                    regno.setError("please enter reg no");
                    regno.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    registerUser(facname, facemail, facphone, facpass, facregno);
                }

            }


            private void registerUser(String facname, String facemail, String facphone, String facpass, String facregno) {
                FirebaseAuth auth = FirebaseAuth.getInstance();

                ReadWriteFacDetails facwriteUserDetails = new ReadWriteFacDetails(facname, facphone, facregno, facpass);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Faculty");
                reference.child(facregno).setValue(facwriteUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(addFaculty.this, "User Registred Successfully", Toast.LENGTH_SHORT).show();
                            name.setText("");
                            email.setText("");
                            pass.setText("");
                            cnfpass.setText("");
                            phone.setText("");
                            regno.setText("");
                        } else {
                            Toast.makeText(addFaculty.this, "User Registration failed,please try again", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });


            }
        });
    }
}





