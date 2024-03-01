package com.example.dept1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_activity extends AppCompatActivity {

    EditText name, phone, email, password,cnfpassword,regno;

    ProgressBar progressBar;

    private static final String TAG="Register_activity";
    @SuppressLint("MissingInflatedId")

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phoneno);
        email   = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cnfpassword =findViewById(R.id.confirmpassword);
        regno = findViewById(R.id.regnumber);
        Button btn = findViewById(R.id.regbtn);
        progressBar = findViewById(R.id.progressbar);
        db = new DBHelper(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stdname = name.getText().toString();
                String stdphone = phone.getText().toString();
                String stdemail = email.getText().toString();
                String stdpass = password.getText().toString();
                String cnfpass = cnfpassword.getText().toString();
                String regino = regno.getText().toString();


             /*   String mobileRegex =" [6-9][0-9]{9}";
                Matcher mobileMatcher ;
                Pattern mobilePattern = Pattern.compile(mobileRegex);
                mobileMatcher =  mobilePattern.matcher(stdphone);
*/

                if(TextUtils.isEmpty(stdname)){
                    Toast.makeText(Register_activity.this, "pleease enter a full name", Toast.LENGTH_LONG).show();
                    name.setError("Full name is required");
                    name.requestFocus();
                }else if(TextUtils.isEmpty(stdemail)){
                    Toast.makeText(Register_activity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    email.setError("email is required");
                    email.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(stdemail).matches()){
                    Toast.makeText(Register_activity.this, "Please re-enter  your email", Toast.LENGTH_SHORT).show();
                    email.setError("valid email is required");
                    email.requestFocus();
                }else if (TextUtils.isEmpty(stdphone)){
                    Toast.makeText(Register_activity.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
                    phone.setError("phone number is required");
                    phone.requestFocus();
                }else if(stdphone.length() !=10){
                    Toast.makeText(Register_activity.this, "Please re-enter your mobile no.", Toast.LENGTH_SHORT).show();
                    phone.setError("Mobile number should be 10 digit");
                    phone.requestFocus();
                }
                /*else if (!mobileMatcher.find()){
                    Toast.makeText(Register_activity.this, "Please re-enter your mobile no.", Toast.LENGTH_SHORT).show();
                    phone.setError("Mobile number should be valid");
                    phone.requestFocus();
                }*/

                else if(TextUtils.isEmpty(stdpass)){
                    Toast.makeText(Register_activity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    password.setError("password is required");
                    password.requestFocus();
                }else if (stdpass.length() < 6){
                    Toast.makeText(Register_activity.this, "Password should be at least 6 digit", Toast.LENGTH_SHORT).show();
                    password.setError("password to weak");
                    password.requestFocus();
                }else if(TextUtils.isEmpty(cnfpass)){
                    Toast.makeText(Register_activity.this, "Please confirm your password", Toast.LENGTH_SHORT).show();
                    cnfpassword.setError("password confirmation required");
                    cnfpassword.requestFocus();
                }else if(!stdpass.equals(cnfpass)){
                    Toast.makeText(Register_activity.this, "Please enter same password", Toast.LENGTH_SHORT).show();
                    cnfpassword.setError("password confirmation is requird");
                    cnfpassword.requestFocus();

                    //clear the entered password
                    cnfpassword.clearComposingText();
                    password.clearComposingText();
                }else if(TextUtils.isEmpty(regino)){
                    Toast.makeText(Register_activity.this, "please enter registration number", Toast.LENGTH_SHORT).show();
                    regno.setError("please enter reg no");
                    regno.requestFocus();
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);


                   registerUser(stdname, stdemail,stdphone,stdpass,regino);
                }
        }



          public void registerUser(String stdname, String stdemail, String stdphone, String stdpass,  String regino) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(stdemail, stdpass).addOnCompleteListener(Register_activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(stdname, stdphone, regino);

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Registered Users");
                            reference.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        firebaseUser.sendEmailVerification();
                                        Toast.makeText(Register_activity.this, "User Registered Successfully, please verify your email", Toast.LENGTH_SHORT).show();
                /*
                                   Intent intent = new Intent(Register_activity.this, UserProfileActivity.class);
                                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                   |Intent.FLAG_ACTIVITY_NEW_TASK;
                                   startActivity(intent);
                                   finish();*/
                                   } else {
                                        Toast.makeText(Register_activity.this, "User Registered failed , please try again", Toast.LENGTH_SHORT).show();

                                    }
                                    progressBar.setVisibility(View.GONE);

                                }
                            });

                            //sendverfication email

                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                password.setError("your password is too weak");
                                password.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                email.setError("your email is invalid or already in use, kindly re-enter");
                                email.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e) {
                                email.setError("user already registered with this email.use another email");
                                email.requestFocus();

                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(Register_activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }

    });
}
}
  /* if(stdpass.equals(cnfpass)){
                        Boolean checkuser = db.checkusername(regino);
                        if(checkuser == false){
                            Boolean insert  = db.insertData(stdname,stdpass,regino,stdphone,stdemail);
                            if(insert == true){
                                Toast.makeText(Register_activity.this, "Registration Sucessfull", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(Register_activity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Register_activity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register_activity.this, "passwords are not matching", Toast.LENGTH_SHORT).show();
                    }*/