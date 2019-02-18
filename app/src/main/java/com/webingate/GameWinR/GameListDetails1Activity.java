package com.webingate.GameWinR;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.GameWinR.Deo.GameResponce;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GameListDetails1Activity extends AppCompatActivity {

    ImageView gameimage,videplay;
    Toast toast;
    TextView gametitlename,gametimelist,entryfee,gametype1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_list_details1);
        Intent i = getIntent();
        int gameId=i.getIntExtra("Id",0);
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

        GotoGameDetails(gameId);
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
        gametitlename=findViewById(R.id.gametitlename);
        gametimelist=findViewById(R.id.gametimelist);
        entryfee=findViewById(R.id.entryfee);
        gametype1=findViewById(R.id.gametype1);
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

        toolbar.setTitle("Game Details");

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
    public void DisplayToast(String text){
        if(toast!=null){
            toast.cancel();
            toast=null;
        }
        toast= Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
    }
    public void GotoGameDetails(int Id){
        com.webingate.GameWinR.utils.DataPrepare.get(this).getrestadapter()
                .GetGamesById(Id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GameResponce>>() {

                    @Override
                    public void onCompleted() {
                        DisplayToast("Successfully Get Game Details");
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

                        GameResponce res=responselist.get(0);
                        gametitlename.setText(res.getName());
                        gametype1.setText(res.getTypeName());
                        gametimelist.setText(res.getDate()+" at "+res.getTime());
                        entryfee.setText(res.getEntryFee().toString());



                        if(res.getGameImage()!=null){

                            byte[] decodedString= Base64.decode(res.getGameImage().substring(res.getGameImage().indexOf(",")+1), Base64.DEFAULT);
                            Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            gameimage.setImageBitmap(image1);
                        }
                        else{
                            gameimage.setImageResource(R.drawable.game1);

                        }

                        //gamelist= (ArrayList<GameResponce>) responselist;
                      //  adapter = new GameItemListAdapter1(gamelist);



                    }
                });


    }
}
