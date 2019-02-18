package com.webingate.GameWinR;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class PriceConfigListDetailsActivity extends AppCompatActivity {

    ImageView gameimage,videplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_list_details);
        initData();
        initUI();
        initDataBindings();
        initActions();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(

                v ->
                {
                    Toast.makeText(getApplicationContext(), "Edit Details", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(this, GameListNewActivity.class));
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData()
    {

    }

    private void initUI()
    {
        initToolbar();
        gameimage=(ImageView)findViewById(R.id.gameimage);
        videplay=(ImageView)findViewById(R.id.videplay);
        gameimage.setImageResource(R.drawable.game1);
        videplay.setImageResource(R.drawable.videoplay);
    }

    private void initDataBindings()
    {
    }

    private void initActions()
    {
    }


    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Price Config Details");

        try {
            toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        }catch (Exception e){
            Log.e("TEAMPS","Can't set color.");
        }

        try {
            setSupportActionBar(toolbar);
        }catch (Exception e) {
            Log.e("TEAMPS","Error in set support action bar.");
        }

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }catch (Exception e) {
            Log.e("TEAMPS","Error in set display home as up enabled.");
        }

    }
}
