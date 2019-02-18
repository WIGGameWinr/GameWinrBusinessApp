package com.webingate.GameWinR;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.webingate.GameWinR.fragment.PaymentsHistoryFragment;
import com.webingate.GameWinR.utils.common_adapter.ViewPagerAdapter;

public class TroubleTicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payments_activity);

        initUI();
        //initActions();

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
        initToolbar();

        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }



    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if(toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Trouble Ticket");

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

        PaymentsHistoryFragment pendingFragment = new PaymentsHistoryFragment("Pending");
        adapter.addFragment(pendingFragment, "CURRENT");

        PaymentsHistoryFragment deliveryFragment = new PaymentsHistoryFragment("Completed");
        adapter.addFragment(deliveryFragment, "COMPLETED");

        PaymentsHistoryFragment cenceledFragment = new PaymentsHistoryFragment("Cancelled");
        adapter.addFragment(cenceledFragment, "CANCELLED");

        viewPager.setAdapter(adapter);

    }

//    private void initActions(){
//
//        adapter.setOnClickListener(view -> {
//
//        });
//
//    }
    //endregion
}
