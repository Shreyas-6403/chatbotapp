package com.shopbotfianlproject.chatbot;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    int flag =0;
    int Flag2=0;
    int flag22=0;
    int flggt=0;
    int flagg=0;
    int i45=0;
    public static int i23=0;
    LinearLayout lv;
    RecyclerView rv,rv2;
    ImageView chatmod;
    public int i=0;
    int flagg34=0;
    int flaggbt=0;
    int flagm=0;
    String prices;
    String name22;
    String namemn;
    public Spinner price,model10,model20;
    public MediaPlayer mp,mp2;
    public ImageView filter;
    int flagg2=0;
    int flagg33=0;
    ImageView send;
    public String his[]={"dummy"};
   public java.util.List<String> list = new ArrayList<String>();
    public java.util.List<String> list2 = new ArrayList<String>();
    public java.util.List<String> list3 = new ArrayList<String>();
    int index=0;
    public CardView cv,cv1,cv2,cv3,cv5,cv4,cv6,cv7;
    public ImageView img23,img24,img25,img26,img27,img233;
    int ref=1;
    int Flag33;
    public ImageView img22;
    public String[] ary;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    String checkss;
    String result;
    int Flagg45=1;
    String Username;
    public String aryfi[];
    public String arlink[];
    DatabaseReference databaseReference;
    ArrayList<String> List;
    public String s;
    public String nameusr;
    String arrayyy[]={"arry"};
    RecyclerView recyclerView;
    public EditText editText;
    ImageView imageView;
    String arrayy[]={"njd"};
    ArrayList<Chatsmodal> chatsmodalArrayList;
    ChatAdapter chatAdapter;
    Button buy,more,okp,okm;
    ImageView sbtn;
    String sg;
    String m10[]={"Select price range","10,000-15,999","16,000-20,999","21,000-25,999","26,000-30,999","31,000-more"};
    String m11[]={"select model","Realme Narzo","Samsung m30",};
    String m20[]={"select model","Samsung F23","Samsung M32","Samsung a51","Poco x3","Redmi note 10 pro max"};
    String m30[]={"Select model","Oppo F21 Pro","Redmi Note 11 pro","Vivo V21e 5G"};
    String m40[]={"Select model","MOTOROLA Edge 20"};
    String m50[]={"Select Model","Iphone 13 pro max"," Oneplus 10 pro","Rog 5","iQOO 9 5G"};
    private  final String USER_KEY = "user";
    private  final String BOT_KEY = "bot";
    private  final String BOT_imgs = "bot2";
    private static final String AS="My";
    private static final String Name="name";
    private static final String EmailPass="pass";
    SharedPreferences sharedPreferences;
    //public Object List;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shopandsr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = new Intent();
        switch (item.getItemId()) {
            case R.id.shopemenu:
                Toast.makeText(MainActivity.this, "Entered in Shop Menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.thememenu:
                Toast.makeText(MainActivity.this, "Theme changed", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.chat_recycler);
        editText = findViewById(R.id.edt_msg);
        mp = MediaPlayer.create(this, R.raw.repl);
        mp2 = MediaPlayer.create(this, R.raw.sample2);
        imageView = findViewById(R.id.send_btn);
        chatsmodalArrayList = new ArrayList<>();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample2);
        chatAdapter = new ChatAdapter(chatsmodalArrayList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        sbtn=findViewById(R.id.shop_btn);
        databaseReference= FirebaseDatabase.getInstance().getReference("");
        filter=findViewById(R.id.filter);
        price=findViewById(R.id.price2);
        model10=findViewById(R.id.model10);
        okp=findViewById(R.id.okp);
        okm=findViewById(R.id.okm);
        model20=findViewById(R.id.model20);
        chatmod=findViewById(R.id.retrn);




        lv=findViewById(R.id.botreply);








                Intent i =getIntent();
                Username=i.getStringExtra("username");
                sg=i.getStringExtra("shop");
                Log.d("userame","data got in intent is "+Username+"sg value is "+sg);
                 Log.d("user","valu of sg is "+sg);


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sg.equals("shop"))
                {

                    Log.d("user","in 197");

                    editText.setText("shop");
                    imageView.performClick();
                }
            }
        }, 500);










        DatabaseReference dbf=FirebaseDatabase.getInstance().getReference("UserInfo");
        dbf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nameusr=snapshot.child(Username).child("name").getValue(String.class);


                Log.d("userame"," username is "+nameusr);
                chatsmodalArrayList.add(new Chatsmodal("Hi, "+nameusr+"\n i am in chatmode now..... \n to switch me into shoping mode pls type shop....  ", BOT_KEY,null));
                chatAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);
                mp2.start();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                chatsmodalArrayList.add(new Chatsmodal("pls check your Internet Connection", BOT_KEY,null));
                chatAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);
            }
        });




        //Log.d("userame",""+nameusr);
        sharedPreferences=getSharedPreferences(AS,MODE_PRIVATE);

        //when open an activity check sharedprefrence
            ArrayAdapter<String> adpt10=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,m10);
            price.setAdapter(adpt10);

            ArrayAdapter<String> adpt11=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,m11);
            model10.setAdapter(adpt11);

            ArrayAdapter<String> adpt20=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,m20);
            model20.setAdapter(adpt20);

        if(Flagg45==0) {
            /*
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "clicked buy", Toast.LENGTH_SHORT).show();
                }
            });

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "clicked more", Toast.LENGTH_SHORT).show();
                }
            });

             */
        }
        recyclerView.setAdapter(chatAdapter);

      //  chatsmodalArrayList.add(new Chatsmodal("Hi, "+name22+"\n i am in chatmode now..... \n to switch me into shoping mode pls type shop....  ", BOT_KEY,null));
       // chatAdapter.notifyDataSetChanged();
       // recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);
        //Intent intent2 = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        //String str = intent2.getStringExtra("message_key");
        //editText.setText(str);
        //Intent intent = new Intent(getApplicationContext(),CoursesGVAdapter.class);
        Intent intent = getIntent();
        s = intent.getStringExtra("message_key");
        Log.d("sample","valu od s is "+s);
        editText.setText(s);
        Intent intent2 = getIntent();

        arrayyy= intent2.getStringArrayExtra("array");

        Flag2=2;
        Log.d("sample","valu of falg 2 is "+Flag2);
        System.out.println("the arrya is ___________________________");
       /* if(Flag2==2) {
            System.out.println("the arrya is ___________________________");
            for (int i = 0; i < arrayyy.length; i++) {
                System.out.println(arrayyy[i]);
            }

        }

        */
        //editText.setText("shop");
        // display the string into textView


            chatmod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.setText("normal");
                    imageView.performClick();

                }
            });

        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flaggbt==0)
                {   finish();
                    Intent t=new Intent(MainActivity.this,MainActivity.class);
                    String ss="shop";
                    t.putExtra("username",Username);
                    t.putExtra("shop",ss);
                    Log.d("user","valu of ss is "+ss);
                    startActivity(t);
                    i45=1;

                }


                if(flaggbt==1)
                {


                }

            }
        });


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setVisibility(View.VISIBLE);
                editText.setVisibility(View.INVISIBLE);
                okp.setVisibility(View.VISIBLE);
                //price.setSelected();
               // int pos=price.getSelectedItemPosition();
               // String Item =m10[pos];


            }
        });

        okp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=price.getSelectedItemPosition();
                String Item =m10[pos];
                okp.setVisibility(View.INVISIBLE);
                okm.setVisibility(View.VISIBLE);


                if(Item.equals("10,000-15,999"))
                {
                    price.setVisibility(View.INVISIBLE);
                    model10.setVisibility(View.VISIBLE);
                    flagm=1;


                }
                if(Item.equals("16,000-20,999"))
                {
                    price.setVisibility(View.INVISIBLE);
                    model20.setVisibility(View.VISIBLE);
                    flagm=2;


                }


            }
        });

        okm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1)
                {
                    int posm=model10.getSelectedItemPosition();
                    String model=m11[posm];
                    editText.setText(model);
                    editText.setVisibility(View.VISIBLE);
                    model10.setVisibility(View.INVISIBLE);
                    okm.setVisibility(View.INVISIBLE);
                }

                if(flag==2)
                {
                    int posm=model20.getSelectedItemPosition();
                    String model2=m20[posm];
                    editText.setText(model2);
                    editText.setVisibility(View.VISIBLE);
                    model20.setVisibility(View.INVISIBLE);
                    okm.setVisibility(View.INVISIBLE);
                }

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*flag++;
                flag=2;
                flag++;
                Log.d("sample","valu of flag is "+flag);
                if(flag>2) {

                    Log.d("sample","reached here============== ");
                    if ((editText.getText().toString()).equals(marr)) {
                        Log.d("sample","Done!!!!");
                        Toast.makeText(MainActivity.this, "Done !!!!!!!!!", Toast.LENGTH_SHORT).show();
                    }
                }

                 */
                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter your message",Toast.LENGTH_SHORT).show();
                    return;
                }
                checkss=editText.getText().toString();
                Log.d("sample"," valu id od checsss "+checkss);
                if (editText.getText().toString().equals("shop") == true) {
                    Toast.makeText(MainActivity.this, "Opning shoping mode", Toast.LENGTH_SHORT).show();
                    i45=1;



                    //editText.setText("shop");
                    //imageView.performClick();
                    chatmod.setVisibility(View.VISIBLE);
                    sbtn.setVisibility(View.INVISIBLE);
                    filter.setVisibility(View.VISIBLE);
                    flaggbt=1;
                    flag = 1;
                    flag22=0;
                }
                if (editText.getText().toString().equals("normal") == true) {
                   // Toast.makeText(MainActivity.this, "Opning shoping mode", Toast.LENGTH_SHORT).show();
                    filter.setVisibility(View.INVISIBLE);

                    chatmod.setVisibility(View.INVISIBLE);
                    sbtn.setVisibility(View.VISIBLE);
                    Intent t=new Intent(MainActivity.this,MainActivity.class);
                    t.putExtra("username",Username);
                    t.putExtra("shop","hi");


                    startActivity(t);

                    finish();



                   flag = 0;
                }

                if (editText.getText().toString().equals("log out") == true) {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    Toast.makeText(getApplicationContext(),"Logout successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,login.class);
                    startActivity(intent);
                    finish();
                    sharedPreferences= getSharedPreferences(AS, MODE_PRIVATE);


                    flag = 0;
                }

                if (editText.getText().toString().equals("My Orders") == true) {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("UserInfo");
                    Query check_username = databaseReference.orderByChild("usern").equalTo(Username);
                    //check_username.addListenerForSingleValueEvent(new ValueEventListener()
                     check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                             String passcheck = snapshot.child(Username).child("orders").getValue(String.class);
                             Log.d("order","the password oredrs are in varianle is "+passcheck+"username is "+Username);
                             chatsmodalArrayList.add(new Chatsmodal("Oredred phones are \n"+passcheck, BOT_KEY,null));
                             chatAdapter.notifyDataSetChanged();
                             recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);

                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {

                         }
                     });

                    //flaggbt=1;
                   //flag = 1;
                    flggt=1;
                    flag22=1;

                }


                if (editText.getText().toString().equals("10,000-15,000") == true) {
                    //Toast.makeText(MainActivity.this, "Opning shoping mode", Toast.LENGTH_SHORT).show();
                    //ref = 0;

                    editText.setVisibility(View.INVISIBLE);
                    //hereevhvbvbh

                }
                getResponse(editText.getText().toString());
               // databaseReference.setValue(editText.getText().toString());
                HashMap<String,Object>map=new HashMap<>();
                map.put("data",editText.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("all info").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("tag","oncomplet") ;
                            }
                        });


                //new jugad

                if (flag == 0) {
                    if (ref == 0)
                    {
                        // Toast.makeText(MainActivity.this, "Opning shoping mode", Toast.LENGTH_SHORT).show();

                        chatsmodalArrayList.add(new Chatsmodal("back to normal", BOT_KEY,null));
                        chatAdapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);
                        ref++;
                    }
                    else
                    {


                    }
                } else {


                    //chatsmodalArrayList.add(new Chatsmodal("i am in shoping mode", BOT_KEY,null));
                    //chatAdapter.notifyDataSetChanged();
                    //recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);

                    //Intent activity2Intent = new Intent(getApplicationContext(), Main22Activity.class);
                    //startActivity(activity2Intent);

                }






                    editText.setText("");
                //editText.setText(str);
            }
        });





    }



    //http://api.brainshop.ai/get?bid=164165&key=mrUquOej5HM6zSQc&uid=[uid]&msg=

    private void getResponse(String message) {
        chatsmodalArrayList.add(new Chatsmodal(message,USER_KEY,null));
        chatAdapter.notifyDataSetChanged();
        mp2.start();
        String url = "\n" +
                "http://api.brainshop.ai/get?bid=164165&key=mrUquOej5HM6zSQc&uid=[uid]&msg="+message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFitApi retroFitApi = retrofit.create(RetroFitApi.class);
        Call<MsgModal> call = retroFitApi.getMessage(url);
        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if(response.isSuccessful()) {
                    String checks=editText.getText().toString();
                    if (editText.getText().toString().equals("normal") == true) {
                        Toast.makeText(MainActivity.this, "back to bot", Toast.LENGTH_SHORT).show();
                        flag = 0;
                    }

                    if (flag == 0) {

                        if (ref == 0)
                        {

                            // Toast.makeText(MainActivity.this, "Opning shoping mode", Toast.LENGTH_SHORT).show();
                            //MsgModal msgModal = response.body();
                            chatsmodalArrayList.add(new Chatsmodal("back to normal", BOT_KEY,null));
                            chatAdapter.notifyDataSetChanged();
                            recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);
                            ref++;
                        }
                        else {


                            if(flggt==1)
                            {
                                flggt=0;
                            }
                            else
                            {
                                MsgModal msgModal = response.body();
                                mp.start();
                                chatsmodalArrayList.add(new Chatsmodal(msgModal.getCnt(), BOT_KEY, null));
                                chatAdapter.notifyDataSetChanged();
                                recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);

                            }






                        }
                    } else
                        {

                        //MsgModal msgModal = response.body();
                           // if (editText.)
                            //{
                             //   flag22=0;
                           // }
                           if(flag22==0) {
                              // chatsmodalArrayList.add(new Chatsmodal("i am in shoping mode", BOT_KEY,null));
                               //chatAdapter.notifyDataSetChanged();
                               //recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);
                               Intent intent34 = new Intent(MainActivity.this, Main22Activity.class);
                               intent34.putExtra("number1", 23);
                               intent34.putExtra("number2", 34);
                               flag22++;
                               startActivityForResult(intent34, 1);

                           }
                           else {
                               Log.d("sample","reached at if 275 line");


                               DocumentReference docRef = db.collection("Data").document(checkss);
                              // System.out.println("value of chis "+checkss);

                               docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                   @Override
                                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                       if (task.isSuccessful()) {
                                           DocumentSnapshot document = task.getResult();
                                           if (document.exists()) {
                                               Log.d("data", "DocumentSnapshot data: " + document.getData());

                                               String out=document.getData().toString();
                                               ary = document.getData().toString().split("=");



                                               if(Flag33==0) {
                                                   Flagg45=0;
                                                   LinearLayout layout = findViewById(R.id.lin);
                                                 //  LinearLayout layout22 = findViewById(R.id.lin2);
                                                  // ViewGroup.LayoutParams params = layout.getLayoutParams();

                                                 //  params.height = 400;
                                                  /// params.width = 400;
                                                  // layout.setLayoutParams(params);


                                                  // LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(400, 400);

                                                  // if(flagg==0) {
                                                       img22 = findViewById(R.id.img2);
                                                    //   flagg++;
                                                   //}
                                                      // img22.setLayoutParams(parms);
                                                    /*
                                                   System.out.println(img22.getId());
                                                    int img22id=img22.getId();
                                                   img22id++;
                                                   img22.setId(img22id);
                                                   System.out.println(img22.getId());
                                                   if(flagg==1)
                                                   {
                                                       System.out.println("here in line 334");
                                                       img22.setId(R.id.my_edit_text_1);
                                                       img22=findViewById(R.id.my_edit_text_1);
                                                       flagg++;
                                                       flagg2++;
                                                   }
                                                   if(flagg==2)
                                                   {
                                                       System.out.println("here in line 341");
                                                       img22.setId(R.id.my_button_1);
                                                       img22=findViewById(R.id.my_button_1);
                                                       flagg++;
                                                   }
                                                   if(flagg==3)
                                                   {
                                                       System.out.println("here in line 341");
                                                       img22.setId(R.id.my_time_picker_1);
                                                       img22=findViewById(R.id.my_time_picker_1);
                                                       flagg=1;
                                                   }
                                                   if(flagg==1)
                                                   {
                                                       if(flagg2==0) {
                                                           System.out.println("here in line 334");
                                                           img22.setId(R.id.my_edit_text_1);
                                                           img22 = findViewById(R.id.my_edit_text_1);
                                                           flagg++;
                                                       }
                                                   }


                                                     */

                                                   //Log.d("data","Output of to srting is "+out);
                                                   // Log.d("data","Output of to srting2 is "+out1);

                                                  // img22 = findViewById(R.id.(id));


                                                   Log.d("sample", "reached at if 336  line");





                                                   ImageView img33 = new ImageView(MainActivity.this);
                                                   TableLayout.LayoutParams params233 = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                                                   img33.setLayoutParams(params233);

// Which value am I supposed to use here?
                                                   img33.setId(R.id.my_edit_text_1);

                                                  // layout22.addView(img33);


                                                    /*if(flagg33==0)
                                                    {
                                                        Picasso.get().load(ary[1]).into(img22);
                                                        chatsmodalArrayList.add(new Chatsmodal("", BOT_KEY, img22));

                                                        //ImageView img2=findViewById()
                                                        chatAdapter.notifyDataSetChanged();
                                                        recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);
                                                        flagg33++;
                                                    }
                                                  // Picasso.get().load(ary[1]).into(img22);
                                                   if(flagg33==1)
                                                   {
                                                       Picasso.get().load(ary[1]).into(img33);
                                                       chatsmodalArrayList.add(new Chatsmodal("", BOT_KEY,null));

                                                       //ImageView img2=findViewById()
                                                       chatAdapter.notifyDataSetChanged();
                                                       recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);
                                                       flagg33=0;
                                                   }

                                                     */


                                                   if(flagg34==0)
                                                   {
                                                      cv = findViewById(R.id.cv1);
                                                       img233 = findViewById(R.id.img23);
                                                       Picasso.get().load(ary[1]).into(img233);
                                                       cv.setVisibility(View.VISIBLE);
                                                       Log.d("New","at 424");


                                                       img233.setVisibility(View.VISIBLE);


                                                   }
                                                   cv2 = findViewById(R.id.cv2);
                                                   img24 = findViewById(R.id.img24);


                                                   if(flagg34==2)
                                                   {
                                                       cv.setVisibility(View.GONE);
                                                       img233.setVisibility(View.GONE);
                                                       System.out.print("at 436");

                                                       Picasso.get().load(ary[1]).into(img24);
                                                       cv2.setVisibility(View.VISIBLE);
                                                       img24.setVisibility(View.VISIBLE);
                                                       Log.d("New","at 442");
                                                       //flagg34=0;
                                                      // flagg34++;


                                                   }

                                                   cv3 = findViewById(R.id.cv3);
                                                   img25 = findViewById(R.id.img25);
                                                  // flagg34++;
                                                   if(flagg34==4)
                                                   {

                                                       Log.d("flag34","i am at flg value 4");

                                                       cv2.setVisibility(View.GONE);
                                                       img24.setVisibility(View.GONE);
                                                       System.out.print("at 436");

                                                       cv3.setVisibility(View.VISIBLE);


                                                       Picasso.get().load(ary[1]).into(img25);

                                                       img25.setVisibility(View.VISIBLE);
                                                       Log.d("New","at 442");
                                                       //flagg34++;
                                                      // flagg34=0;

                                                   }
                                                   //flagg34++;
                                                   cv4 = findViewById(R.id.cv4);
                                                   img26 = findViewById(R.id.img25);
                                                   if(flagg34==6)
                                                   {
                                                       cv3.setVisibility(View.GONE);
                                                       Log.d("flag34","i am at flg value 4");
                                                       img25.setVisibility(View.GONE);




                                                       Picasso.get().load(ary[1]).into(img26);

                                                       Log.d("New","at 669");
                                                       cv4.setVisibility(View.VISIBLE);
                                                       img26.setVisibility(View.VISIBLE);
                                                       //flagg34++;
                                                       // flagg34=0;

                                                   }

                                                   cv5 = findViewById(R.id.cv5);
                                                   img27 = findViewById(R.id.img247);
                                                   if(flagg34==8)
                                                   {

                                                       Log.d("flag34","i am at flg value 4");

                                                       cv4.setVisibility(View.GONE);
                                                       img26.setVisibility(View.GONE);
                                                       System.out.print("at 436");

                                                       cv5.setVisibility(View.VISIBLE);


                                                       Picasso.get().load(ary[1]).into(img27);

                                                       img27.setVisibility(View.VISIBLE);


                                                   }



                                                   if(flagg34==0)
                                                   {
                                                       flagg34=2;
                                                   }
                                                   else
                                                   {
                                                       if(flagg34==2)
                                                       {
                                                           flagg34=4;
                                                       }
                                                       else
                                                       {
                                                           if(flagg34==4)
                                                           {
                                                               flagg34=6;
                                                           }
                                                           else
                                                           {
                                                               if(flagg34==6)
                                                               {
                                                                   flagg34=8;
                                                               }
                                                               else
                                                               {

                                                               }
                                                           }
                                                       }
                                                   }




                                                   String spcc=ary[3];
                                                   String spc2=ary[4];
                                                   String spc3=ary[5];
                                                   String spc4=ary[6];
                                                    Log.d("samples",spcc);
                                                   chatsmodalArrayList.add(new Chatsmodal("\n"+"📣"+spcc+"\n"+"\n"+"📣"+spc2+"\n"+"\n"+"📣"+spc3+"\n"+"\n"+"📣"+spc4+"", BOT_KEY,null));
                                                   mp.start();
                                                   //ImageView img2=findViewById()
                                                   chatAdapter.notifyDataSetChanged();
                                                   recyclerView.scrollToPosition(chatsmodalArrayList.size() - 1);
                                                   flagg33++;
                                                   //LinearLayout layout2 = findViewById(R.id.lin);

                                                  // ViewGroup.LayoutParams params222 = layout.getLayoutParams();
                                                  // LinearLayout.LayoutParams layoutParams;
                                                   //layoutParams =setLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                                                  // layout.setLayoutParams(params222);





                                               }


                                           } else {
                                               Log.d("data", "No such document");
                                           }
                                       } else {
                                           Log.d("data", "get failed with ", task.getException());
                                       }
                                   }
                               });


                           }
                          // Intent activity2Intent = new Intent(getApplicationContext(), Main22Activity.class);
                           // startActivity(activity2Intent);

                            //Intent activity2Intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                            //startActivity(activity2Intent);

                            // Read from the database
                            List = new ArrayList<String>();
                            //=new ArrayList<>();
                            //List.clear();
                            FirebaseDatabase.getInstance().getReference().child("User").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // This method is called once with the initial value and again
                                    // whenever data at this location is updated.
                                    // String value =dataSnapshot.getChildren(String.class);
                                    // String value = dataSnapshot.getValue(String.class);
                                    //Log.d(TAG, "Value is: " + value);
                                    // System.out.println("the value is "+value);

                                    for (DataSnapshot Snapshot : dataSnapshot.getChildren())
                                    {

                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Log.w("tag", "Failed to read value.", error.toException());
                                }
                            });
                    }
                }
            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {
                chatsmodalArrayList.add(new Chatsmodal("Pls Check Internet Connection",BOT_KEY,null));
                chatAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("sample","reached at method onactivity 328");
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                result = data.getStringExtra("result");
                prices=data.getStringExtra("result2");
                namemn=data.getStringExtra("result3");

                arlink=data.getStringArrayExtra("result3");
                System.out.println("valu of arry ===================================================");

                }
                Log.d("sample","reached at method onactivity 332");
                editText.setText(result);
                imageView.performClick();

                //his[index]=result;
                list.add(result);
                list2.add(prices);



                Log.d("list","value of list in first item is "+list);



                for(int i55=0;i55<his.length;i55++)
                {
                    Log.d("his","\n"+his[i55]);
                }

            }
            if (resultCode == RESULT_CANCELED) {
                Log.d("sample","reached at method onactivity 336");
                editText.setText("normal");
            }
        }



/*
    public void onResume()
    {
        super.onResume();
        Intent intent = getIntent();
        s = intent.getStringExtra("message_key");
        Log.d("sample","valu od s is "+s);








    }
*/

    public void buy1(View v)
    {
       // Toast.makeText(MainActivity.this, "clicked buy", Toast.LENGTH_LONG).show();
        Intent buy=new Intent(MainActivity.this,buyactivity.class);
        buy.putExtra("mob",""+list.get(i));
        buy.putExtra("price",""+list2.get(i));
        buy.putExtra("unm",Username);


        startActivity(buy);
    }

    public void more1(View v)
    {
        // Toast.makeText(MainActivity.this, "clicked more", Toast.LENGTH_LONG).show();
        Intent more=new Intent(MainActivity.this,Main22Activity.class);
        Intent intent34 = new Intent(MainActivity.this, Main22Activity.class);
        intent34.putExtra("number1", 23);
        intent34.putExtra("number2", 34);
        flag22++;
        startActivityForResult(intent34, 1);
        i++;
        // startActivity(more);
    }

    //for mobile 2
    public void buy2(View v)
    {
       // Toast.makeText(MainActivity.this, "clicked buy", Toast.LENGTH_LONG).show();
        Intent buy=new Intent(MainActivity.this,buyactivity.class);
        buy.putExtra("mob",""+list.get(i));
        //ImageView img=findViewById(R.id.img24);
        buy.putExtra("price",""+list2.get(i));
        buy.putExtra("unm",Username);
        startActivity(buy);
    }
    public void more2(View v)
    {
       // Toast.makeText(MainActivity.this, "clicked more", Toast.LENGTH_LONG).show();
        Intent more=new Intent(MainActivity.this,Main22Activity.class);
        Intent intent34 = new Intent(MainActivity.this, Main22Activity.class);
        intent34.putExtra("number1", 23);
        intent34.putExtra("number2", 34);

        flag22++;
        i++;
        startActivityForResult(intent34, 1);
       // startActivity(more);
    }
    //for model 3

    public void buy3(View v)
    {
        // Toast.makeText(MainActivity.this, "clicked buy", Toast.LENGTH_LONG).show();
        Intent buy=new Intent(MainActivity.this,buyactivity.class);
        buy.putExtra("mob",""+list.get(i));
        buy.putExtra("unm",Username);
        ImageView img=findViewById(R.id.img25);
        buy.putExtra("price",""+list2.get(i));

        startActivity(buy);
    }
    public void more3(View v)
    {
        // Toast.makeText(MainActivity.this, "clicked more", Toast.LENGTH_LONG).show();
        Intent more=new Intent(MainActivity.this,Main22Activity.class);
        Intent intent34 = new Intent(MainActivity.this, Main22Activity.class);
        intent34.putExtra("number1", 23);
        intent34.putExtra("number2", 34);
        flag22++;
        i++;
        startActivityForResult(intent34, 1);
        // startActivity(more);
    }

    public void buy4(View v)
    {
        // Toast.makeText(MainActivity.this, "clicked buy", Toast.LENGTH_LONG).show();
        Intent buy=new Intent(MainActivity.this,buyactivity.class);
        buy.putExtra("mob",""+list.get(i));
        ImageView img=findViewById(R.id.img25);
        buy.putExtra("price",""+list2.get(i));
        buy.putExtra("unm",Username);
        startActivity(buy);
    }
    public void more4(View v)
    {
        // Toast.makeText(MainActivity.this, "clicked more", Toast.LENGTH_LONG).show();
        Intent more=new Intent(MainActivity.this,Main22Activity.class);
        Intent intent34 = new Intent(MainActivity.this, Main22Activity.class);
        intent34.putExtra("number1", 23);
        intent34.putExtra("number2", 34);
        flag22++;
        i++;
        startActivityForResult(intent34, 1);
        // startActivity(more);
    }

    public void buy5(View v)
    {
        // Toast.makeText(MainActivity.this, "clicked buy", Toast.LENGTH_LONG).show();
        Intent buy=new Intent(MainActivity.this,buyactivity.class);
        buy.putExtra("mob",""+list.get(i));
        ImageView img=findViewById(R.id.img25);
        buy.putExtra("price",""+list2.get(i));
        buy.putExtra("unm",Username);
        startActivity(buy);
    }
    public void more5(View v)
    {
        // Toast.makeText(MainActivity.this, "clicked more", Toast.LENGTH_LONG).show();
        Intent more=new Intent(MainActivity.this,Main22Activity.class);
        Intent intent34 = new Intent(MainActivity.this, Main22Activity.class);
        intent34.putExtra("number1", 23);
        intent34.putExtra("number2", 34);
        flag22++;
        i++;
        startActivityForResult(intent34, 1);
        // startActivity(more);
    }

    public void shopp()
    {
        Intent t=new Intent(MainActivity.this,MainActivity.class);
        t.putExtra("username",Username);


        startActivity(t);

    }

    @Override
    protected void onResume() {
        super.onResume();




    }
}




