package com.shopbotfianlproject.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class recipt extends AppCompatActivity {
    String modelnm,name,price,username,nameofpic,paymod,st,unm,model;
    public String passcheck;
    TextView buynm,price2,nampic,tot,mod;
    String olddata;
    ImageView img;
    FirebaseDatabase firebaseDatabase, firebaseDatabase2;
    DatabaseReference reference,ref2,databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipt);
        buynm=findViewById(R.id.hi);
        img=findViewById(R.id.imageView);
        price2=findViewById(R.id.price);
        nampic=findViewById(R.id.mobile);
        mod=findViewById(R.id.Mode);
        tot=findViewById(R.id.tot);

        Intent i=getIntent();
        nameofpic=i.getStringExtra("nameofpic");
        modelnm=i.getStringExtra("Model");
        name=i.getStringExtra("name");
        price=i.getStringExtra("price");
        username=i.getStringExtra("username");
        paymod=i.getStringExtra("paymod");
        unm=i.getStringExtra("unm");
        Log.d("demo",""+modelnm);
        buynm.setText("Hi, "+username);
        nampic.setText(nameofpic);
        tot.setText(price);
        price2.setText(price);
       st=mod.getText().toString();
        mod.setText(st+"\n"+paymod);
        Picasso.get().load(modelnm).into(img);




        firebaseDatabase2 = FirebaseDatabase.getInstance();
       databaseReference = firebaseDatabase2.getReference("UserInfo");
        Query check_username = databaseReference.orderByChild("usern").equalTo(unm);
        //check_username.addListenerForSingleValueEvent(new ValueEventListener()
        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                passcheck = snapshot.child(unm).child("orders").getValue(String.class);
                Log.d("order2","the password oredrs are in varianle is "+passcheck+"username is "+unm);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("UserInfo");
                Log.d("order2","the password oredrs are in varianle is "+passcheck+"username is "+unm);
                if(passcheck==null)
                {
                    model ="\n"+nameofpic+"  "+price;
                    reference.child(unm).child("orders").setValue(model);
                }
                else
                {
                    model =" "+passcheck+"    "+nameofpic+"  "+price;
                    Log.d("order2","model anme is "+model+"pass check is "+passcheck);
                    reference.child(unm).child("orders").setValue(model);
                }

            }
        }, 5000);










    }
}