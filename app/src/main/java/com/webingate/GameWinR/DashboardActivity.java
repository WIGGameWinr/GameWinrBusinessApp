package com.webingate.GameWinR;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.GameWinR.fragment.DashboardFragment;
import com.webingate.GameWinR.utils.Utils;

public class DashboardActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;
    private Runnable runnable = null;
    private Handler handler = new Handler();
    private LinearLayout layout_dots;
    private ViewPager viewPager;
    private DashboardFragment.AdapterImageSlider adapterImageSlider;
    ImageView userImageView;
    TextView UserMobileNumber;
    private static int[] array_image_place = {
            R.drawable.game1,
            R.drawable.game2,
            R.drawable.game3,
            R.drawable.game4,
            R.drawable.game5,
    };

    private static String[] array_title_place = {
            "Dui fringilla ornare finibus, orci odio",
            "Mauris sagittis non elit quis fermentum",
            "Mauris ultricies augue sit amet est sollicitudin",
            "Suspendisse ornare est ac auctor pulvinar",
            "Vivamus laoreet aliquam ipsum eget pretium",
    };

    private static String[] array_brief_place = {
            "Foggy Hill",
            "The Backpacker",
            "River Forest",
            "Mist Mountain",
            "Side Park",
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerapp_userprofile_activity);
        initData();
        initUI();
        initDataBinding();
        initAction();
        //initComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else{
                drawer.openDrawer(GravityCompat.START);
            }
            //startActivity(new Intent(this,customerappUserprofileActivity.class));
        } else if (item.getItemId()== R.id.nav_profile) {
            Toast.makeText(this, "Clicked nav_profile.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FeatureProfileGeneralProfile1Activity.class);
            startActivity(intent);
        }  else if (item.getItemId() == R.id.nav_coupons) {
            Toast.makeText(this, "Clicked nav_coupons.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DashboardActivity.this, CouponsListActivity.class));

        }else if (item.getItemId() == R.id.nav_notification) {
             Toast.makeText(this, "Clicked nav_notification.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DashboardActivity.this, NotificationsListActivity.class));
        }else if (item.getItemId() == R.id.nav_logout) {
            Toast.makeText(this, "Clicked Logout.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DashboardActivity.this, LoginGameActivity.class));
        }else if (item.getItemId() == R.id.nav_tticket) {
            Toast.makeText(this, "Clicked nav_tticket.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DashboardActivity.this, TroubleTicketActivity.class));
       }else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
   private void initData() {

    }

    private void initUI() {
        initToolbar();

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);
        View headerLayout = navigationView.getHeaderView(0);

        userImageView = headerLayout.findViewById(R.id.userImageView);
        UserMobileNumber=headerLayout.findViewById(R.id.UserMobileNumber);
        UserMobileNumber.setText(ApplicationConstants.Mobilenumber);

        if(ApplicationConstants.UserPhoto==null){
     // userImageView = headerLayout.findViewById(R.id.userImageView);
            Utils.setCircleImageToImageView(this, userImageView, R.drawable.profile1, 0, 0);
        }
        else{
            byte[] decodedString= Base64.decode( ApplicationConstants.UserPhoto.substring( ApplicationConstants.UserPhoto.indexOf(",")+1), Base64.DEFAULT);
            Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            userImageView.setImageBitmap(image1);
            userImageView.setImageBitmap(Utils.getCircularBitmapWithBorder(image1,0,0));
        }

        // Start Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.home9BottomNavigation);
        Utils.removeShiftMode(bottomNavigationView);

        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;

        View badge = LayoutInflater.from(this)
                .inflate(R.layout.notificationcount_item, bottomNavigationMenuView, false);
        TextView tv = badge.findViewById(R.id.notification_badge);
        tv.setText("8+");
        itemView.addView(badge);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
//                case R.id.searchMenu:
//                    loadFragment(new businessAppDashboardFragment());
//                    break;
                case R.id.wallet:
                    Intent intent2 = new Intent(this, EwalletVerificationActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.inboxMenu:
                    Intent intent1 = new Intent(this, NotificationsListActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.profileMenu:
                    //loadFragment(new AppDirectoryHome4Fragment());
                    drawer.openDrawer(GravityCompat.START);
//                    Intent intent = new Intent(this, FeatureProfileGeneralProfile1Activity.class);
//                    startActivity(intent);
                    break;
                case R.id.promocode:

                        startActivity(new Intent(this, PromoCodeListActivity.class));
                        break;
                default:
                    loadFragment(new DashboardFragment());
                    break;
            }
            Toast.makeText(getApplicationContext(), "Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
            return false;
        });

        loadFragment(new DashboardFragment());

    }

    private void initDataBinding() {

    }

    private void initAction() {
    }

    private void initToolbar() {

      toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Dashboard");

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

    private void loadFragment(Fragment fragment) {
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.home9Frame, fragment)
                .commitAllowingStateLoss();
    }

}
