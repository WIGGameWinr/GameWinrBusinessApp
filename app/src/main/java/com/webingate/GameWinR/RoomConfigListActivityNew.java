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

public class RoomConfigListActivityNew extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

     EditText gametitle1,roompassword,roomid;
     Spinner spinnerGameTitle;
    List<GameResponce> tt;
    List<String> listgamelist = new ArrayList<String>();
    ArrayAdapter arlist;
    Toast toast;
    int pos;
    Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_room_config);
        gametitle1=findViewById(R.id.gametitle1);
        roompassword=findViewById(R.id.roompassword);
        roomid=findViewById(R.id.roomid);

        spinnerGameTitle=findViewById(R.id.gametitle);
        spinnerGameTitle.setOnItemSelectedListener(this);
        btnsubmit=findViewById(R.id.btnsubmit);
        initData();
        initUI();
        initDataBinding();
        initActions();
        GetGamelist();
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
        btnsubmit.setOnClickListener(view ->{
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("RoomName",gametitle1.getText().toString());
            jsonObject.addProperty("RoomPassword",roompassword.getText().toString());
            jsonObject.addProperty("RoomId",roomid.getText().toString());
            jsonObject.addProperty("GameName",tt.get(pos).getName());
            jsonObject.addProperty("insupdflag","I");
            savenewroom(jsonObject);
        });
//
//        uploadbtn.setOnClickListener(view ->{
//            Toast.makeText(getApplicationContext(), "Clicked Upload Image", Toast.LENGTH_SHORT).show();
//        });
    }
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;
        }
        toast= Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
    }
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Add New Game");

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

                        arlist=new ArrayAdapter(RoomConfigListActivityNew.this,android.R.layout.simple_spinner_item,listgamelist);
                        arlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerGameTitle.setAdapter(arlist);
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
    public void savenewroom( JsonObject jsonObject){
        com.webingate.GameWinR.utils.DataPrepare.get(this).getrestadapter()
                .saveroom(jsonObject)
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
                        tt=responselist;
                        startActivity(new Intent(RoomConfigListActivityNew.this,RoomConfigListActivity.class));
                    }
                });


    }
}
