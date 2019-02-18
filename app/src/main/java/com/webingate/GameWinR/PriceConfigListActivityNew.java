package com.webingate.GameWinR;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.webingate.GameWinR.Deo.GameResponce;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import com.webingate.giftsolution.activity.businessapp.Deo.RegisterBusinessUsers;
//import com.webingate.giftsolution.driverapplication.ApplicationConstants;
//import com.webingate.giftsolution.fragment.businessAppDriverUserInfoFragment;
//import com.webingate.giftsolution.fragment.businessAppUploadDocsFragment;
//import cropper.CropImage;
//import com.webingate.paysmartbusinessapp.pa;

public class PriceConfigListActivityNew extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    Spinner gametitle1;
    List<GameResponce> tt;
    ArrayList<GameResponce> gamelist ;
    ArrayAdapter arlist;
    Toast toast;
    int pos;
    Button btnsubmit;
    EditText editviewwinprize,editviewperkil,editviewentryfee;
    List<String> listgamelist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_price_config);
        gametitle1=findViewById(R.id.gametitle1);
        btnsubmit=findViewById(R.id.btnsubmit);
        gametitle1.setOnItemSelectedListener(this);
        editviewwinprize=findViewById(R.id.editviewwinprize);
        editviewperkil=findViewById(R.id.editviewperkil);
        editviewentryfee=findViewById(R.id.editviewentryfee);
        GetGamelist();
        initData();
        initUI();
        initDataBinding();
        initActions();
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
    private void initData() {

    }
    private void initUI() {

        initToolbar();


    }
    private void initDataBinding() {

    }

    private void initActions() {
//        btnsubmit.setOnClickListener(view -> {

//        });


        btnsubmit.setOnClickListener(view ->{
            //Toast.makeText(getApplicationContext(), "Successfully Added New Game", Toast.LENGTH_SHORT).show();
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("WinPrize",Integer.parseInt(editviewwinprize.getText().toString()));
            jsonObject.addProperty("PerKill",Integer.parseInt(editviewperkil.getText().toString()));
            jsonObject.addProperty("EntryFee",Integer.parseInt(editviewentryfee.getText().toString()));
            jsonObject.addProperty("GameName",tt.get(pos).getName());
            jsonObject.addProperty("insupdflag","I");

            saveNewPrice(jsonObject);
        });
//
//        uploadbtn.setOnClickListener(view ->{
//            Toast.makeText(getApplicationContext(), "Clicked Upload Image", Toast.LENGTH_SHORT).show();
//        });
    }
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Price Config New");

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
    public void GetGamelist( ){
        com.webingate.GameWinR.utils.DataPrepare.get(this).getrestadapter()
                .GetGamelist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GameResponce>>() {

                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Get Game list");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            //Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<GameResponce> responselist) {
                       // gamelist= (ArrayList <GameResponce>) responselist;
                          tt=responselist;
                        // Adding the responce list to spinner  inthe dropdownlist

                        for (int i = 0; i < tt.size(); i++) {
                            listgamelist.add(tt.get(i).getName());
                        }

                        arlist=new ArrayAdapter(PriceConfigListActivityNew.this,android.R.layout.simple_spinner_item,listgamelist);
                        arlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        gametitle1.setAdapter(arlist);
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
    public void saveNewPrice( JsonObject jsonObject){
        com.webingate.GameWinR.utils.DataPrepare.get(this).getrestadapter()
                .savenewprice(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GameResponce>>() {

                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Get Price list");
                        //StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            //Log.d("OnError ", e.getMessage());
                            DisplayToast("Error");
                            //StopDialogue();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(List<GameResponce> responselist) {
                       tt=responselist;
                       startActivity(new Intent(PriceConfigListActivityNew.this,PricingConfigListActivity.class));
                    }
                });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      // loginasOption = position;
        pos=position;
        DisplayToast("selected"+tt.get(position).getName());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
       // loginasOption = -1;
    }
}
