package com.webingate.GameWinR;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.webingate.GameWinR.Deo.UserValidateResp;

import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginGameActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView forgotTextView, newusersignup;
    Button loginButton;
//    Button facebookCardView, twitterCardView;
    ImageView bgImageView;
    Toast toast;
    int loginasOption = -1;
    EditText cpassword,password;
    //region override functions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        initUI();

        initDataBindings();

        initActions();
    }
    //endregion

    //region Init Functions
    private void initUI() {
          forgotTextView = findViewById(R.id.forgotTextView);
          newusersignup = findViewById(R.id.newusersignup);
          loginButton = findViewById(R.id.loginButton);
//          facebookCardView = findViewById(R.id.facebookCardView);
//          twitterCardView = findViewById(R.id.twitterCardView);
          bgImageView = findViewById(R.id.bgImageView);
          bgImageView.setImageResource(R.drawable.lg1);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.cpassword);
    }

    private void initDataBindings() {

    }

    private void initActions() {
        forgotTextView.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Forgot Password.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ForgotPasswordActivity.class));
        });

        newusersignup.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Sign Up.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, SignUpActivity.class));
        });

        loginButton.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Login.", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(this,DashboardActivity.class));

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("Username", cpassword.getText().toString());
            jsonObject.addProperty("Password", password.getText().toString());
            LoginVerification(jsonObject);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loginasOption = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        loginasOption = -1;
    }
    public void LoginVerification(JsonObject jsonObject){
        com.webingate.GameWinR.utils.DataPrepare1.get(this).getrestadapter()
                .ValidateCred(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UserValidateResp>>() {
                    @Override
                    public void onCompleted() {
                        //DisplayToast("Successfully LoggedIn");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                            DisplayToast("Unable to Login");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<UserValidateResp> responce) {
                        UserValidateResp credentialsResponse= responce.get(0);
                          if(credentialsResponse.getCode()!=null) {
                              DisplayToast(credentialsResponse.getDescription());
                          }else {
                              startActivity(new Intent(LoginGameActivity.this,DashboardActivity.class));
                              ApplicationConstants.UserPhoto=credentialsResponse.getUserPhoto();
                              ApplicationConstants.Mobilenumber=credentialsResponse.getMobilenumber();
                              ApplicationConstants.Username=credentialsResponse.getusernamae();
                          }

                    }
                });
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
