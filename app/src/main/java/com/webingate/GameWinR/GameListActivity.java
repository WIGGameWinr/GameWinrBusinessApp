package com.webingate.GameWinR;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.webingate.GameWinR.Deo.GameResponce;
import com.webingate.GameWinR.adapter.GameItemListAdapter1;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GameListActivity extends AppCompatActivity {

    // data and adapter
   // List<ShopItem> shopItemList;
    GameItemListAdapter1 adapter;
    ArrayList<GameResponce> gamelist;
   Toast toast;
    // RecyclerView
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_list_activity);

        initData();

        initUI();

        initDataBindings();

        initActions();
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(

                v ->
                {
                    Toast.makeText(getApplicationContext(), "Add Item", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, GameListNewActivity.class));
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );
        GetGamelist();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search_basket,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

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
        // get place list
      //  shopItemList = ShopItemRepository.getWomenShopItemList();
    }

    private void initUI()
    {
        initToolbar();

        // get list adapter
       // adapter = new GameItemListAdapter(shopItemList);
        adapter = new GameItemListAdapter1(null);

        // get recycler view
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initDataBindings()
    {
        // bind adapter to recycler
        recyclerView.setAdapter(adapter);
    }

    private void initActions()
    {

//        adapter.setOnItemClickListener(new GameItemListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, ShopItem obj, int position) {
//                Toast.makeText(getApplicationContext(), "Selected " + obj.name, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(GameListActivity.this,GameListDetails1Activity.class);
//            startActivity(intent);
//            }
//
//        });
    }

    //region Init Toolbar
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Games List");

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
                        gamelist= (ArrayList <GameResponce>) responselist;
                        adapter = new GameItemListAdapter1(gamelist);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener((view, obj, position) ->
                                {
                                    Toast.makeText(GameListActivity.this, "Selected : " +obj.getName(), Toast.LENGTH_LONG).show();

                                    Intent intent=new Intent(GameListActivity.this,GameListDetails1Activity.class);
                                    intent.putExtra("Id",obj.getId());
                                    startActivity(intent);

                                }
                        );

                    }
                });


    }

}
