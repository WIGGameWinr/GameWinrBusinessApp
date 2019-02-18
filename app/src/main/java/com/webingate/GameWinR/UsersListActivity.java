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
import android.view.View;
import android.widget.Toast;

import com.webingate.GameWinR.Deo.UsersResponse;
import com.webingate.GameWinR.adapter.UserListAdapter;
import com.webingate.GameWinR.object.Place;
import com.webingate.GameWinR.repository.UsersListRepository;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UsersListActivity extends AppCompatActivity {

    // data and adapter
    ArrayList<Place> placeArrayList;
    UserListAdapter adapter;

    // RecyclerView
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_activity);

        initData();

        initUI();

        initDataBindings();

        initActions();
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(

                v ->
                {
                    Toast.makeText(getApplicationContext(), "Clicked New User", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, UserListActivityNew.class);
                    startActivity(intent);
                }

                //Toast.makeText(getApplicationContext(), "Open Video clicked", Toast.LENGTH_SHORT).show()

        );
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
        placeArrayList = UsersListRepository.getPlaceList();
    }

    private void initUI()
    {
        initToolbar();

        // get list adapter
        adapter = new UserListAdapter(placeArrayList);

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

        adapter.setOnItemClickListener(new UserListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Place obj, int position) {
                //ApplicationConstants.userimg = obj.imageName;
                Toast.makeText(getApplicationContext(), "Selected " + obj.name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UsersListActivity.this,FeatureProfileGeneralProfile1Activity.class);
                startActivity(intent);
            }
        });
        Getuserlist();
    }

    //region Init Toolbar
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Users List");

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


    public void Getuserlist(){
        com.webingate.GameWinR.utils.DataPrepare1.get(this).getrestadapter()
                .GetUserslist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UsersResponse>>() {
                    @Override
                    public void onCompleted() {
                        //    DisplayToast("Successfully LoggedIn");
                        //   StopDialogue();
                    }
                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("OnError ", e.getLocalizedMessage());
                         //   DisplayToast("Unable to Login");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(List<UsersResponse> responce) {
                        UsersResponse credentialsResponse= (UsersResponse) responce;
                    }
                });
    }
}
