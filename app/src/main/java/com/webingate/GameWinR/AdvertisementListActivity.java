package com.webingate.GameWinR;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.webingate.GameWinR.fragment.AdvertisementHistoryFragment;
import com.webingate.GameWinR.utils.common_adapter.ViewPagerAdapter;

public class AdvertisementListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertisement_activity);

        initData();

        initUI();

        initDataBindings();

        initActions();
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(

                v ->
                {
                    Toast.makeText(getApplicationContext(), "Add Item", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, NewItemActivity.class);
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

    }

    private void initUI()
    {
        initToolbar();
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void initDataBindings()
    {

    }

    private void initActions()
    {

//        adapter.setOnItemClickListener(new FeatureListECommerceItemList1Adapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, ShopItem obj, int position) {
//                Toast.makeText(getApplicationContext(), "Selected " + obj.name, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(AdvertisementListActivity.this,GamelistDetailActivity.class);
//            startActivity(intent);
//            }
//
////            @Override
////            public void onAddToCartClick(View view, ShopItem obj, int position) {
////                Toast.makeText(getApplicationContext(), "Clicked add to cart.", Toast.LENGTH_SHORT).show();
////            }
//
////            @Override
////            public void onMenuClick(View view, ShopItem obj, int position) {
////                Toast.makeText(getApplicationContext(), "Clicked menu.", Toast.LENGTH_SHORT).show();
////            }
//        });
    }

    //region Init Toolbar
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Boquest List");

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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        AdvertisementHistoryFragment pendingFragment = new AdvertisementHistoryFragment();
        adapter.addFragment(pendingFragment, "CURRENT");

        AdvertisementHistoryFragment deliveryFragment = new AdvertisementHistoryFragment();
        adapter.addFragment(deliveryFragment, "COMPLETED");

        AdvertisementHistoryFragment cenceledFragment = new AdvertisementHistoryFragment();
        adapter.addFragment(cenceledFragment, "CANCELLED");

        viewPager.setAdapter(adapter);

    }
}
