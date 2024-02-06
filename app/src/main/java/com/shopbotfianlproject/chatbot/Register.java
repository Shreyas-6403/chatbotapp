package com.shopbotfianlproject.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    Button LogBack,reg;
    TextInputLayout name,mobile,email,US,PA,username;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        reg=findViewById(R.id.btnLogin2);
        name=findViewById(R.id.name2233);
        mobile=findViewById(R.id.Phone);
        email=findViewById(R.id.Email);
        US=findViewById(R.id.usernameRE);
        PA=findViewById(R.id.passRe);
        username=findViewById(R.id.usernameRE);






        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NAME = name.getEditText().getText().toString();
                String MOBILE = mobile.getEditText().getText().toString();
                String Email = email.getEditText().getText().toString();
                String User = US.getEditText().getText().toString();
                String Pass = PA.getEditText().getText().toString();
                String unm = username.getEditText().getText().toString();


                if (!NAME.isEmpty()) {
                    name.setError(null);
                    name.setErrorEnabled(false);
                    if (!MOBILE.isEmpty()) {

                        if (MOBILE.length() == 10) {
                            mobile.setError(null);
                            mobile.setErrorEnabled(false);
                            if (unm.isEmpty()) {
                                username.setError("Enter Username No");
                            }
                                if (!Email.isEmpty()) {
                                    email.setError(null);
                                    email.setErrorEnabled(false);
                                    if (!User.isEmpty()) {
                                        US.setError(null);
                                        US.setErrorEnabled(false);
                                        if (!Pass.isEmpty()) {
                                            firebaseDatabase = FirebaseDatabase.getInstance();
                                            reference = firebaseDatabase.getReference("UserInfo");

                                            String NAME_s = name.getEditText().getText().toString();
                                            String MOBILE_s = mobile.getEditText().getText().toString();
                                            String Email_s = email.getEditText().getText().toString();
                                            String User_s = US.getEditText().getText().toString();
                                            String Pass_s = PA.getEditText().getText().toString();

                                            storingdata storingdata = new storingdata(NAME_s, MOBILE_s, Email_s, User_s, Pass_s);

                                            reference.child(User_s).setValue(storingdata);
                                            //Toast.makeText(getApplicationContext(), "Successfully Stored", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), login.class);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            PA.setError("Enter Password");
                                        }
                                    } else {
                                        US.setError("Enter Your UserName");
                                    }

                                } else {
                                    email.setError("Enter  Email");
                                }
                            } else {
                                mobile.setError("Mobile No Must be 10 Digit");
                            }
                        } else {
                            mobile.setError("Enter Mobile No");
                        }
                    } else {
                        name.setError("Enter Name");
                    }
                }

        });



    }
}