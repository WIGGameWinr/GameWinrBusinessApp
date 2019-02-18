package com.webingate.GameWinR;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.GameWinR.adapter.CustomSpinnerAdapter;
import com.webingate.GameWinR.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class AppUserLogin4Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button loginButton;
    TextView forgotTextView;
    Spinner spinner;
    ImageView bgImageView;
    int loginasOption = -1;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    String[] fruits = {"Admin", "User"};
    int[] icons = {R.drawable.baseline_person_outline_black_24, R.drawable.baseline_person_outline_black_24};

    //region override methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_user_login_4_activity);

        initUI();

        initDataBindings();

        initActions();

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        list = new ArrayList<>(Arrays.asList(fruits));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        CustomSpinnerAdapter uiloginasCustomSpinnerAdapter =new CustomSpinnerAdapter(getApplicationContext(),icons,fruits);
        spinner.setAdapter(uiloginasCustomSpinnerAdapter);
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
    //endregion


    //region Init Functions
    private void initUI() {
        //initToolbar();
        loginButton = findViewById(R.id.loginButton);
        //signUpCardView = findViewById(R.id.signUpCardView);
        forgotTextView = findViewById(R.id.forgotTextView);
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void initDataBindings() {
        int id = R.drawable.lgback;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }

    private void initActions() {
        loginButton.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Login", Toast.LENGTH_SHORT).show();
            GoToDashboard();
        });

//        signUpCardView.setOnClickListener(view -> {
//            Toast.makeText(getApplicationContext(), "Clicked Sign Up", Toast.LENGTH_SHORT).show();
//        });

        forgotTextView.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Clicked Forgot Password", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }

    private void GoToDashboard(){
        switch (this.loginasOption){
            case 0:
                Toast.makeText(getApplicationContext(), "Clicked option 0.", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(this, DashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

                break;
            case 1:
                //Toast.makeText(getApplicationContext(), "Clicked option 1.", Toast.LENGTH_SHORT).show();

                intent = new Intent(this,UserDashboardActivity.class);
//                intent.putExtra("UserAccountNo",UserAccountNumber);
//                intent.putExtra("usertypeid",usertypeid);
                startActivity(intent);
                //intent = new Intent(this, businessappFleetownerDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                //startActivity(intent);
                break;
            default:
                //intent = new Intent(this, customerDashboardActivity.class);
                // EditText editText = (EditText) findViewById(R.id.editText);
                // String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                //startActivity(intent);
                break;
        }

    }

//    private void initToolbar() {
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//
//        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);
//
//        if(toolbar.getNavigationIcon() != null) {
//            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
//        }
//
//        toolbar.setTitle("Login 4");
//
//        try {
//            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
//        }catch (Exception e){
//            Log.e("TEAMPS","Can't set color.");
//        }
//
//        try {
//            setSupportActionBar(toolbar);
//        }catch (Exception e) {
//            Log.e("TEAMPS","Error in set support action bar.");
//        }
//
//        try {
//            if (getSupportActionBar() != null) {
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            }
//        }catch (Exception e) {
//            Log.e("TEAMPS","Error in set display home as up enabled.");
//        }
//
//    }

    private int selectype(){
        if(loginasOption==0){
            return 109;
        }else if(loginasOption==1){
            return 110;
        }
        else {
            return 200;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loginasOption = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        loginasOption = -1;
    }

    //endregion
}
