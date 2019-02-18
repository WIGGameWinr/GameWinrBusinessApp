package com.webingate.GameWinR;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.webingate.GameWinR.utils.Utils;
//import com.webingate.giftsolution.activity.businessapp.Deo.MOTPVerificationResponse;
//import com.webingate.giftsolution.driverapplication.ApplicationConstants;

//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;

public class EwalletVerificationActivity extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String ID = "idKey";
    public static final String Email = "emailKey";
    public static final String Emailotp = "emailotpkey";
    public static final String UserAccountNumber = "UserAccountNo";
    public static final String usertypeid = "usertypeid";

String id,email,useracntno;

    Toast toast;
//
//    @BindView(R.id.s_email)
//    EditText etop;
//
//    @BindView(R.id.submitOTPButton)
//    Button sbtn;

    ImageView bgImageView;
    Button changeButton, resendButton, submitOTPButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewallet_verification_homepage_activity);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        useracntno= prefs.getString(UserAccountNumber, null);
        initUI();
        initActions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.menu_true, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        initToolbar();

        bgImageView = findViewById(R.id.bgImageView);
        int id = Utils.getDrawableInt(getApplicationContext(), "verification3");
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);

        changeButton = findViewById(R.id.changeButton);

        resendButton = findViewById(R.id.resendButton);
        //etop=findViewById(R.id.s_email);
        submitOTPButton = findViewById(R.id.submitOTPButton);
    }

    private void initActions(){
        changeButton.setOnClickListener((View v) ->{
           Toast.makeText(getApplicationContext(),"Clicked Change Email.",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:"+"email_to"));
//            intent.putExtra(Intent.EXTRA_SUBJECT, "email_subject");
//            intent.putExtra(Intent.EXTRA_TEXT, "email_body");
//            startActivity(intent);
        });

        resendButton.setOnClickListener((View v) ->{
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("UserAccountNo",useracntno);
//            jsonObject.addProperty("change","3");
//           //ResendOTP(jsonObject);
            Toast.makeText(getApplicationContext(),"OTP is Resent.",Toast.LENGTH_SHORT).show();
        });

//        submitOTPButton.setOnClickListener((View v) ->{
//            //Toast.makeText(getApplicationContext(),"OTP is Resent.",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, customerMOTPVerificationActivity.class);
//            startActivity(intent);
//        });
        submitOTPButton.setOnClickListener((View v) ->{
            Toast.makeText(getApplicationContext(),"Clicked on Submit",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,EwalletMOTPVerificationActivity.class));
//            Intent intent = new Intent(this, DashboardEWallet.class);
//            startActivity(intent);
//            if(etop.getText().toString().matches("")){
//                Toast.makeText(getApplicationContext(),"Please Enter OTP",Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                JsonObject jsonObject = new JsonObject();
//                jsonObject.addProperty("UserAccountNo",ApplicationConstants.userAccountNo);
//                jsonObject.addProperty("Mobilenumber",etop.getText().toString());
//                MtopVerification1(jsonObject);
//            }
        });
    }


    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Mobile No Verification ");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        } catch (Exception e) {
            Log.e("TEAMPS", "Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } catch (Exception e) {
            Log.e("TEAMPS", "Error in set display home as up enabled.");
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }




    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;

        }
        toast= Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

    }
}
