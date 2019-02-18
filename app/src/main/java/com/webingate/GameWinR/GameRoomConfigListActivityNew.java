package com.webingate.GameWinR;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

//import com.webingate.giftsolution.activity.businessapp.Deo.RegisterBusinessUsers;
//import com.webingate.giftsolution.driverapplication.ApplicationConstants;
//import com.webingate.giftsolution.fragment.businessAppDriverUserInfoFragment;
//import com.webingate.giftsolution.fragment.businessAppUploadDocsFragment;
//import cropper.CropImage;
//import com.webingate.paysmartbusinessapp.pa;

public class GameRoomConfigListActivityNew extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game_room_config);
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
//        btnsubmit.setOnClickListener(view ->{
//            Toast.makeText(getApplicationContext(), "Successfully Added New Game", Toast.LENGTH_SHORT).show();
//        });
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

        toolbar.setTitle("Game Room New");

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
}
