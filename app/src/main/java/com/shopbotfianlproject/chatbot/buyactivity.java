package com.shopbotfianlproject.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shopbotfianlproject.chatbot.databinding.ActivityBuyactivityBinding;
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import kotlin.collections.IntIterator;

public class buyactivity extends AppCompatActivity {
    public String model,price;
    ImageView img;
    TextView pricetxt;
    String fullname,emaill,phonn,addresss,unm;
    TextView modelnm;
    public String[] ary;
    TextInputLayout nameinput,email,phno,add,pincode;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    Button cashondelivery;
    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    int GOOGLE_PAY_REQUEST_CODE = 123;

    //public static final String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    //int GOOGLE_PAY_REQUEST_CODE = 2;
    String amount="0";
    String name = "deepa";
    String upiId = "7276914243@ybl";
    String transactionNote = "";
    String status;
    String TAG ="main";
    final int UPI_PAYMENT = 0;

    Uri uri;
    private ActivityBuyactivityBinding binding;
    private ImageView googlePayButton;

    private static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private static Uri getUpiPaymentUri(String name, String upiId, String transactionNote, String amount) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", transactionNote)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyactivity);
        binding = ActivityBuyactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       // Log.d("val",ary[2]);
        nameinput=findViewById(R.id.inputName2);
        email=findViewById(R.id.inputEmail2);
        phno=findViewById(R.id.inputPassword2);
        add=findViewById(R.id.inputAddress2);
        cashondelivery=findViewById(R.id.cashondelivery);


        googlePayButton=findViewById(R.id.googlePayButton);
        Intent buy=getIntent();
        modelnm=findViewById(R.id.modelname);
        model=buy.getStringExtra("mob");
        price=buy.getStringExtra("price");
        unm=buy.getStringExtra("unm");

        img=findViewById(R.id.model);
        pricetxt=findViewById(R.id.price);


        cashondelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model_name = model;
                fullname = nameinput.getEditText().getText().toString();
                emaill = email.getEditText().getText().toString();
                phonn = phno.getEditText().getText().toString();
                addresss = add.getEditText().getText().toString();
                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {



                    if (!fullname.isEmpty() && !emaill.isEmpty() && !phonn.isEmpty() && !addresss.isEmpty()) {

                        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            sendSMS();
                            Intent ibook = new Intent(buyactivity.this, recipt.class);
                            ibook.putExtra("nameofpic", ary[3]);
                            ibook.putExtra("Model", ary[1]);
                            ibook.putExtra("name", model);
                            ibook.putExtra("price", pricetxt.getText().toString());
                            ibook.putExtra("username", fullname);
                            ibook.putExtra("paymod", "Cash On delivery");
                            ibook.putExtra("unm",unm);
                            startActivity(ibook);


                        } else {
                            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                        }

                    } else {
                        if (fullname.isEmpty()) {
                            nameinput.setError("Enter Full name");
                        }
                        if (emaill.isEmpty()) {
                            email.setError("Enter Email");
                        }
                        if (phonn.isEmpty()) {
                            phno.setError("Enter Phone number");
                        }
                        if (addresss.isEmpty()) {
                            add.setError("Enter Address");
                        }


                    }
                }
                else
                {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                }
            }

        });



        googlePayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = pricetxt.getText().toString();
                fullname = nameinput.getEditText().getText().toString();
                emaill = email.getEditText().getText().toString();
                phonn = phno.getEditText().getText().toString();
                addresss = add.getEditText().getText().toString();

                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

                    if (!fullname.isEmpty() && !emaill.isEmpty() && !phonn.isEmpty() && !addresss.isEmpty()) {
                        transactionNote = "Name is " + fullname;

                        if (!amount.isEmpty()) {
                            Log.d("gpay", "value of amount is" + amount);
                            // uri = getUpiPaymentUri(name, upiId, transactionNote, amount);
                            Log.d("amount", "value of amount is" + amount);


                            payUsingUpi(name, upiId, transactionNote, amount);
                           // String amount, String upi, String name, String desc, String transactionId) {
                          //  int aa= Integer.parseInt(amount);
                           // String sm=Integer.toString(aa);
                            //makePayment(amount,upiId,name,transactionNote,"87yu67ui");
                          //  easyUpiPayment.startPayment();
                        /*   Uri uri =
                                    new Uri.Builder()
                                            .scheme("upi")
                                            .authority("pay")
                                            .appendQueryParameter("pa",upiId)
                                            .appendQueryParameter("pn", name)
                                            .appendQueryParameter("mc", "2214")
                                            .appendQueryParameter("tr", "chatbot3")
                                            .appendQueryParameter("tn", "your-transaction-note")
                                            .appendQueryParameter("am", pricetxt.getText().toString())
                                            .appendQueryParameter("cu", "INR")
                                            .appendQueryParameter("url", "http://damjisb.blogspot.com/")
                                            .build();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
                            startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);

                         */




                        } else {
                            Toast.makeText(buyactivity.this, "Transaction failed  ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (fullname.isEmpty()) {
                            nameinput.setError("Enter Full name");
                        }
                        if (emaill.isEmpty()) {
                            email.setError("Enter Email");
                        }
                        if (phonn.isEmpty()) {
                            phno.setError("Enter Phone number");
                        }
                        if (addresss.isEmpty()) {
                            add.setError("Enter Address");
                        }


                    }


                }
                else
                {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                }
            }
        });
        DocumentReference docRef = db.collection("Data").document(model);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("data", "DocumentSnapshot data: " + document.getData());

                        String out=document.getData().toString();
                        ary = document.getData().toString().split("=");
                        Picasso.get().load(ary[1]).into(img);
                        pricetxt.setText(price);
                        modelnm.setText(model);

                    }
                }}
        });
    }

    private void makePayment(String amount, String upi, String name, String desc, String transactionId) {
        // on below line we are calling an easy payment method and passing
        // all parameters to it such as upi id,name, description and others.


        BigDecimal bg=new BigDecimal(amount);
        String st=bg.setScale(2, RoundingMode.HALF_EVEN).toString();
        Log.d("ss","ampunt in int is "+st);

        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(this)
                // on below line we are adding upi id.
                .setPayeeVpa(upi)
                // on below line we are setting name to which we are making oayment.
                .setPayeeName(name)
                // on below line we are passing transaction id.
                .setTransactionId(transactionId)
                // on below line we are passing transaction ref id.
                .setTransactionRefId(transactionId)
                // on below line we are adding description to payment.
                .setDescription(desc)
                // on below line we are passing amount which is being paid.
                .setAmount(st)
                // on below line we are calling a build method to build this ui.
                .build();
        // on below line we are calling a start
        // payment method to start a payment.
        easyUpiPayment.startPayment();
        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        //easyUpiPayment.setPaymentStatusListener(this);
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        startActivityForResult(chooser, UPI_PAYMENT);

    }






    void payUsingUpi(  String name,String upiId, String note, String amount) {
        Log.e("main ", "name "+name +"--up--"+upiId+"--"+ note+"--"+amount);
        
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("mc", "5065")
                //.appendQueryParameter("tid", "02125412")
                .appendQueryParameter("url", "http://damjisb.blogspot.com/")
                .appendQueryParameter("tr", "25gTfg37")
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
           startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(buyactivity.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response "+resultCode );
        /*
       E/main: response -1
       E/UPI: onActivityResult: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPIPAY: upiPaymentDataOperation: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPI: payment successfull: 922118921612
         */
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    //when user simply back without payment
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }
    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(buyactivity.this)) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(buyactivity.this, "Transaction successful.", Toast.LENGTH_SHORT).show();


                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        sendSMS();
                        Intent ibook=new Intent(buyactivity.this,recipt.class);
                        ibook.putExtra("nameofpic",ary[2]);
                        ibook.putExtra("Model",ary[1]);
                        ibook.putExtra("name",model);
                        ibook.putExtra("price",pricetxt.getText().toString());
                        ibook.putExtra("username",fullname);
                        ibook.putExtra("paymod","UPI Payment");
                        ibook.putExtra("unm",unm);
                        startActivity(ibook);


                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }




                Log.e("UPI", "payment successfull: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(buyactivity.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Cancelled by user: "+approvalRefNo);
            }
            else {
                Toast.makeText(buyactivity.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "failed payment: "+approvalRefNo);
            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(buyactivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void sendMsg()
    {
        String phoneNo ="8856887702";
        String msg = "Odrer has booked \n"+"madel name :- "+modelnm.getText().toString()+"Name of user is "+nameinput.getEditText().toString().trim()+"\nEmail id is "+email.getEditText().toString()+"\nPhone number is "+phno.getEditText().toString()+"\nDilivary address is "+add.getEditText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(this, "Message Sent!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
        }
    }


    public void sendSMS(){
        String model_name=model;
        String fullname=nameinput.getEditText().getText().toString();
        String emaill=email.getEditText().getText().toString();
         String phonn=phno.getEditText().getText().toString();
         String addresss=add .getEditText().getText().toString();
        // "+email.getEditText().toString()+"\nPhone number is "+phno.getEditText().toString()+"\nDilivary address is "+add.getEditText().toString();

        String message =""+model_name+"Booked by user "+fullname+"\n user email id is "+emaill+"\nHis Phone number is "+phonn+"\nDilivery address is "+addresss;
        String number ="9421076463";

        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage("8856887702",null, message, null, null);

        Toast.makeText(buyactivity.this, "Item Booked ", Toast.LENGTH_SHORT).show();
        finish();

    }

}