package com.shopbotfianlproject.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    //Button Signup,Login,ADmin;
    TextInputLayout username,password;
    SharedPreferences sharedPreferences;
    TextView forgotPassword,txt;
    ProgressBar p1;
    String sffg="hi";
    FrameLayout f1;
    private static final String AS="My";
    private static final String Name="name";
    private static final String EmailPass="pass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences= getSharedPreferences(AS, MODE_PRIVATE);
        String name=sharedPreferences.getString(Name,null);
        sffg="hi";
        if(name!=null)
        {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("username",name);
            intent.putExtra("shop","hi");
            startActivity(intent);
            finish();
        }
       // Login=findViewById(R.id.btnLogin);
        username=findViewById(R.id.inputEmail);
        f1=findViewById(R.id.frame);
        p1=findViewById(R.id.pgbar);
        password=findViewById(R.id.inputPassword);
        forgotPassword=findViewById(R.id.forgotPassword);
        txt=findViewById(R.id.logtxt);

        Log.d("check","reached at line 40 in login");

        Log.d("check","reached at line 42 in login");
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity2Intent = new Intent(getApplicationContext(),Register.class);
                startActivity(activity2Intent);

            }
        });

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                String shopp="hi";
                Log.d("userame","shopp value is "+shopp);
                Log.d("login",""+user);
                Log.d("login",""+pass);

                if (user.equals("Admin") && pass.equals("Admin2214"))
                {

                    Intent ii=new Intent(login.this,admin.class);
                    startActivity(ii);
                    finish();


                }
                else {



                if (!user.isEmpty()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    if (!pass.isEmpty()) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        final String username_data = username.getEditText().getText().toString();
                        final String Password_data = password.getEditText().getText().toString();



                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("UserInfo");

                        // FirebaseFirestore db=FirebaseFirestore.getInstance();
                        //DatabaseReference databaseReference1=FirebaseFirestore.






                        Query check_username = databaseReference.orderByChild("usern").equalTo(username_data);
                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //Query check_pass=databaseReference.orderByChild("passwo").equalTo(Password_data);
                                if (snapshot.exists()) {
                                    username.setError(null);
                                    username.setErrorEnabled(false);
                                    String passcheck = snapshot.child(username_data).child("passwo").getValue(String.class);
                                    Log.d("sample", "reacged here 78");
                                    //firebase



                                    if (passcheck.equals(Password_data)) {
                                        Log.d("sample", "reacged here 83");
                                        password.setError(null);
                                        password.setErrorEnabled(false);
                                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                        //
                                        Log.d("check", "reached at line 95 in login");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        Log.d("check", "reached at line 97 in login");
                                        String a = username.getEditText().getText().toString();
                                        Log.d("check", "reached at line 99 in login");
                                        //String b=password.getEditText().getText().toString();
                                        Log.d("check", "reached at line 100 in login");

                                        editor.putString(Name, a);
                                        Log.d("check", "reached at line 104 in login");
                                        // editor.putString(EmailPass,b);
                                        editor.apply();

                                        p1.setVisibility(View.VISIBLE);
                                        txt.setVisibility(View.GONE);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                p1.setVisibility(View.INVISIBLE);
                                                txt.setVisibility(View.VISIBLE);

                                            }
                                        }, 500);


                                       // Intent intent345 = new Intent(getApplicationContext(), MainActivity.class);
                                        //startActivity(intent345);
                                        //finish();

                                        Intent intent=new Intent(login.this,MainActivity.class);
                                        intent.putExtra("username",user);
                                        //sf="hi";
                                        intent.putExtra("shop","hi");
                                       // Log.d("userame", "data sending now is "+user+"sf value ids"+user);
                                        Log.d("userame", "data sending now is "+user+"sf value ids"+sffg);

                                        startActivity(intent);
                                        finish();

                                    } else {
                                        password.setError("Wrong Password");
                                    }
                                } else {
                                    username.setError("Wrong Username");
                                }


                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        password.setError("Enter Password");
                    }
                } else {
                    username.setError("Enter UserName");
                }

            }
            }
        });
    }


}