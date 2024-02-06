package com.shopbotfianlproject.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class admin extends AppCompatActivity {
    TextInputLayout modelnm,price,imgurl,specs;
    String name,pr,url,spc;
    FirebaseFirestore fStore;
    Button uplode,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);



        fStore = FirebaseFirestore.getInstance();
        uplode=findViewById(R.id.uplodeimg);
        modelnm=findViewById(R.id.modela);
        price=findViewById(R.id.pricea);
        imgurl=findViewById(R.id.imgurl);
        specs=findViewById(R.id.spec);
        back=findViewById(R.id.back);


back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
        Intent is=new Intent(admin.this,login.class);
        startActivity(is);
    }
});

        uplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DocumentReference documentReference = fStore.collection("Data").document(name);




                name=modelnm.getEditText().getText().toString();
                pr=price.getEditText().getText().toString();
                url=imgurl.getEditText().getText().toString();
                spc=specs.getEditText().getText().toString();
                CollectionReference usersCollectionRef = fStore.collection("Data");
                DocumentReference documentReference = fStore.document("Data/"+name);

                Map<String,Object> user = new HashMap<>();
                user.put("imgUrl",url);
                user.put("name",name);
                user.put("price",pr);
                user.put("spec",spc);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(admin.this, "Data Added 👍!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });




    }
}