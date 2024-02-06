package com.shopbotfianlproject.chatbot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main22Activity extends AppCompatActivity {
 TextView url;
 ImageView imge,wbg;
 ImageButton sr,button1;
 AutoCompleteTextView edtsr;
 ImageButton filter;
 public int flagg23=0;
 public String value,value2;


Spinner price;
String prr[]={"10000-15000","16000-20000","21000-25000","26000-30000","31000-40000","41000-more"};
 String arr[]={"samsung M30","Poco X3"};
 String arr2[]={"dumi value"};
 String arr3[]={"dumi"};


int flag=0;
    GridView coursesGV;
    ArrayList<DataModal> dataModalArrayList;
    ArrayList<DataModal> dataModalArrayList1;
    FirebaseFirestore db;
 FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

 DatabaseReference reference=firebaseDatabase.getReference();
 DatabaseReference childreference=reference.child("link");
 RecyclerView mRecyclearView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        edtsr=findViewById(R.id.editTextTextPersonName);
        coursesGV = findViewById(R.id.idGVCourses);
        filter=findViewById(R.id.filter);

        price=findViewById(R.id.price);

        dataModalArrayList = new ArrayList<>();
        //dataModalArrayList1 = new ArrayList<>();
        //db = FirebaseDatabase.getInstance();
        db = FirebaseFirestore.getInstance();
        loadDatainGridView();
       wbg=findViewById(R.id.imageView2);
        //url=findViewById(R.id.url);
        imge=findViewById(R.id.imageea);
        //CollectionReference citiesRef = db.collection("Data");
        //Query query = citiesRef.whereEqualTo("User", "M30");
        //Log.d("Sample","query is "+query.toString());
        setTitle("Activity 2");
        Intent intent = getIntent();
        final int number1 = intent.getIntExtra("number1", 0);
        final int number2 = intent.getIntExtra("number2", 0);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,arr);
        edtsr.setAdapter(adapter);
        //sr=findViewById(R.id.imageButton2);
        //edtsr=findViewById(R.id.editTextTextPersonName);
        button1=findViewById(R.id.imageButton2);
                //mRecyclearView=findViewById(R.id.recyclerview);

        //citiesRef.whereGreaterThanOrEqualTo("name", "Poco x3").get();



        ArrayAdapter<String> adpt=new ArrayAdapter<String>(Main22Activity.this, android.R.layout.simple_dropdown_item_1line,prr);
        price.setAdapter(adpt);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtsr.setVisibility(View.INVISIBLE);
                flagg23++;
                value2=edtsr.getText().toString();
                price.setVisibility(View.VISIBLE);
                int pos=price.getSelectedItemPosition();
                value=price.getItemAtPosition(pos).toString();

/*
                Query query = db.getInstance()
                        .collection("Data")
                        .orderBy("price", Query.Direction.ASCENDING);

              //  ArrayAdapter<DataModal> list = new ArrayAdapter<DataModal>
                //         .setQuery(query, Main22Activity.class)
                  //      .build();



 */



            }
        });




        edtsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // wbg.setVisibility(View.VISIBLE);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag==0)
                {
                    Log.d("sample","in if");
                    edtsr.setVisibility(View.VISIBLE);
                    String srch=edtsr.getText().toString();
                    Log.d("sample","value of getlib=ne is ");


                    if(edtsr.getLineCount()==-1)
                    {
                        edtsr.setVisibility(View.INVISIBLE);
                    }
                }
                else
                {
                    Log.d("sample","i  ma cjivled");
                    edtsr.setVisibility(View.INVISIBLE);
                    flag=0;
                }





            }
        });









    }

    private void loadDatainGridView() {
        // below line is use to get data from Firebase
        // firestore using collection in android.
        db.collection("Data").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding our
                            // progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {


                                DataModal dataModal = d.toObject(DataModal.class);
                                //DataModal dataModal1 = d.toObject(DataModal.class);
                                //int i=1;


                                dataModalArrayList.add(dataModal);
                                for (int i=0; i<dataModalArrayList.size(); i++) {
                                    Log.d("datamodel",String.valueOf(dataModalArrayList.get(i)));
                                }
                                //dataModalArrayList1.add(dataModal);
                            }

                            CoursesGVAdapter adapter = new CoursesGVAdapter(Main22Activity.this, dataModalArrayList);
                          //  ArrayAdapter<String> adapter1=new ArrayAdapter<String>(Main22Activity.this, android.R.layout.simple_dropdown_item_1line,arr);
                           // edtsr.setAdapter(adapter1);

                            coursesGV.setAdapter(adapter);


                        } else {

                            Toast.makeText(Main22Activity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Main22Activity.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();











        childreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataaSnapshot) {
                String message=dataaSnapshot.getValue(String.class);
                // url.setText(message);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public class CoursesGVAdapter extends ArrayAdapter<DataModal> {
        public String str;
        public String ary2[];
        List<String> arrlist = new ArrayList<String>(Arrays.asList(arr));
        List<String> arrlist2 = new ArrayList<String>(Arrays.asList(arr2));
        List<String> arrlist3 = new ArrayList<String>(Arrays.asList(arr3));
        public CoursesGVAdapter(@NonNull Context context, ArrayList<DataModal> dataModalArrayList) {
            super(context, 0, dataModalArrayList);
        }
        int Flag2=0;
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            // below line is use to inflate the
            // layout for our item of list view.
            View listitemView = convertView;
            if (listitemView == null) {
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.image_gv_item, parent, false);
            }

            // after inflating an item of listview item
            // we are getting data from array list inside
            // our modal class.
            DataModal dataModal = getItem(position);
            System.out.println("data mofuel "+dataModal);



            //Intent intent = new Intent(getContext(), com.tutorial.chatbot.CoursesGVAdapter.class);
            // initializing our UI components of list view item.
            TextView nameTV = listitemView.findViewById(R.id.idTVtext);
            TextView nameTV2 = listitemView.findViewById(R.id.idTVtext2);
            ImageView courseIV = listitemView.findViewById(R.id.idIVimage);

            // after initializing our items we are
            // setting data to our view.
            // below line is use to set data to our text view.
            //String rryr[]={dataModal.getName()};
            //String ary[]={dataModal.getName()+ary};
            //String sum;sum=dataModal.getName();
            arrlist.add(dataModal.getName());
            arr=arrlist.toArray(arr);

            arrlist2.add(dataModal.getImgUrl());
            arr2=arrlist2.toArray(arr2);

            arrlist3.add(dataModal.getprice());
            arr3=arrlist3.toArray(arr3);






            System.out.println("valu of arry ne wjugad===================================================");
            for (int i=0; i<arr.length; i++) {
                System.out.println(arr[i]);
            }

            System.out.println("valu of arry linkssssssssssss ne wjugad===================================================");
            for (int i=0; i<arr2.length; i++) {
                System.out.println(arr2[i]);
            }



            if(flagg23==1)
            {


                    DocumentReference docRef = db.collection("Data").document(value2);
                    // System.out.println("value of chis "+checkss);

                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d("data", "DocumentSnapshot data: " + document.getData());

                                    String out = document.getData().toString();
                                    String ary[] = document.getData().toString().split("=");
                                    Picasso.get().load(ary[1]).into(courseIV);
                                    Toast.makeText(Main22Activity.this, "i got this", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                    });



                nameTV.setText(dataModal.getprice());
            }

            nameTV.setText(dataModal.getName());
            nameTV2.setText(dataModal.getprice());

            // in below line we are using Picasso to load image
            // from URL in our Image VIew.
            Picasso.get().load(dataModal.getImgUrl()).into(courseIV);

            // below line is use to add item
            // click listener for our item of list view.
            listitemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // on the item click on our list view.
                    // we are displaying a toast message.
                    str=dataModal.getName().toString();
                    Log.d("sample",str);
                    String price=dataModal.getprice();


                    Intent intent = new Intent(getContext(),MainActivity.class);
                    Intent intent2 = new Intent(getContext(),MainActivity.class);
                    // now by putExtra method put the value in key, value pair
                    // key is message_key by this key we will receive the value, and put the string

                   // onNewIntent("message_key", str);//
                    /*intent2.putExtra("array",ary);
                    startActivity(intent2);
                    intent.putExtra("message_key", str);
                    startActivity(intent);

                     */
                    System.out.println("valu of ar2 ===================================================");
                    for (int i=0; i<arr.length; i++) {
                        System.out.println(arr[i]);
                    }
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("result",str);
                    resultIntent.putExtra("result2",price);
                    resultIntent.putExtra("result3",arr2);
                    Log.d("sample","reached at 309 in main22activity"+str);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                    //Toast.makeText(getContext(), "Item clicked is : " + dataModal.getName()+"str value is "+str, Toast.LENGTH_SHORT).show();

                    //maycall m1=new maycall();





                }
            });
            return listitemView;


        }


    }

}