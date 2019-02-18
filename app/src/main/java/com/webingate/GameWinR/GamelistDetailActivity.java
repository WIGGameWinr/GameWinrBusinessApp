package com.webingate.GameWinR;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.GameWinR.adapter.FeatureDetailECommerceItemDetail1PagerAdapter;
import com.webingate.GameWinR.adapter.FeatureDetailECommerceItemDetail1ReviewAdapter;
import com.webingate.GameWinR.object.ShopItem;
import com.webingate.GameWinR.object.UserReview;
import com.webingate.GameWinR.repository.ShopItemRepository;
import com.webingate.GameWinR.repository.UserReviewRepository;
import com.webingate.GameWinR.utils.Utils;

import java.util.List;

public class GamelistDetailActivity extends AppCompatActivity {

    // data variables
    private ShopItem shopItem;
    private List<UserReview> userReviewList;
    private int dotsCount;

    private Boolean size1Status = false;
    private Boolean size2Status = false;
    private Boolean size3Status = false;
    private Boolean size4Status = false;
    private Boolean size5Status = false;

    private Boolean color1Status = true;
    private Boolean color2Status = false;
    private Boolean color3Status = false;
    private Boolean color4Status = false;
    private Boolean color5Status = false;
    private Boolean color6Status = false;
    private Boolean color7Status = false;

    // ui variables
    private FeatureDetailECommerceItemDetail1PagerAdapter FeatureDetailECommerceItemDetail1PagerAdapter;
    private FeatureDetailECommerceItemDetail1ReviewAdapter adapter;

    private RecyclerView recyclerView;

    private ViewPager imageViewPager;
    private LinearLayout pager_indicator;
    private ImageView[] dots;

    private TextView addressTextView;
    private TextView phoneTextView;
    private TextView websiteTextView;
    private TextView emailTextView;

    private TextView descTextView;
    private TextView nameTextView;
    private TextView reviewCountTextView;
    private TextView priceTextView;
    private TextView originalPriceTextView;
    private RatingBar itemRatingBar;

    private TextView viewAllCommentTextView;
//    private Button addToBasketButton;
////    private Button addToFavouriteButton;

    private ImageView size1BgImageView;
    private ImageView size2BgImageView;
    private ImageView size3BgImageView;
    private ImageView size4BgImageView;
    private ImageView size5BgImageView;

    private TextView size1TextView;
    private TextView size2TextView;
    private TextView size3TextView;
    private TextView size4TextView;
    private TextView size5TextView;

    private ImageView color1ImageView;
    private ImageView color2ImageView;
    private ImageView color3ImageView;
    private ImageView color4ImageView;
    private ImageView color5ImageView;
    private ImageView color6ImageView;
    private ImageView color7ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_detail_ecommerce_item_detail_1_activity);

        initData();

        initUI();

        initDataBindings();

        initActions();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else if(item.getItemId() == R.id.action_share) {
            Toast.makeText(this, "Clicked Share.", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.action_basket) {
            Toast.makeText(this, "Clicked Edit.", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share_basket,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initData() {

        // get shopItem detail
        shopItem = ShopItemRepository.getWomenShopItem();

        FeatureDetailECommerceItemDetail1PagerAdapter = new FeatureDetailECommerceItemDetail1PagerAdapter(this, shopItem);

        // get place list
        userReviewList = UserReviewRepository.getUserReviewList();

    }

    private void initUI() {

        initToolbar();

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        imageViewPager = findViewById(R.id.imageViewPager);
        pager_indicator = findViewById(R.id.viewPagerCountDots);

        addressTextView = findViewById(R.id.addressTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        websiteTextView = findViewById(R.id.websiteTextView);
        emailTextView = findViewById(R.id.emailTextView);

        descTextView = findViewById(R.id.descTextView);
        nameTextView = findViewById(R.id.nameTextView);
        reviewCountTextView = findViewById(R.id.reviewCountTextView);
        priceTextView = findViewById(R.id.priceTextView);
        originalPriceTextView = findViewById(R.id.originalPriceTextView);
        itemRatingBar = findViewById(R.id.itemRatingBar);

        viewAllCommentTextView = findViewById(R.id.viewAllCommentTextView);
//        addToBasketButton = findViewById(R.id.addToBasketButton);
//        addToFavouriteButton = findViewById(R.id.addToFavouriteButton);

        ImageView color1BgImageView = findViewById(R.id.color1BgImageView);
        ImageView color2BgImageView = findViewById(R.id.color2BgImageView);
        ImageView color3BgImageView = findViewById(R.id.color3BgImageView);
        ImageView color4BgImageView = findViewById(R.id.color4BgImageView);
        ImageView color5BgImageView = findViewById(R.id.color5BgImageView);
        ImageView color6BgImageView = findViewById(R.id.color6BgImageView);
        ImageView color7BgImageView = findViewById(R.id.color7BgImageView);

        setDefaultCircleImage(color1BgImageView, R.color.md_white_1000);
        setDefaultCircleImage(color2BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(color3BgImageView, R.color.md_yellow_400);
        setDefaultCircleImage(color4BgImageView, R.color.md_green_500);
        setDefaultCircleImage(color5BgImageView, R.color.md_green_900);
        setDefaultCircleImage(color6BgImageView, R.color.md_blue_500);
        setDefaultCircleImage(color7BgImageView, R.color.md_black_1000);

        color1ImageView = findViewById(R.id.color1ImageView);
        color2ImageView = findViewById(R.id.color2ImageView);
        color3ImageView = findViewById(R.id.color3ImageView);
        color4ImageView = findViewById(R.id.color4ImageView);
        color5ImageView = findViewById(R.id.color5ImageView);
        color6ImageView = findViewById(R.id.color6ImageView);
        color7ImageView = findViewById(R.id.color7ImageView);


        size1BgImageView = findViewById(R.id.size1BgImageView);
        size2BgImageView = findViewById(R.id.size2BgImageView);
        size3BgImageView = findViewById(R.id.size3BgImageView);
        size4BgImageView = findViewById(R.id.size4BgImageView);
        size5BgImageView = findViewById(R.id.size5BgImageView);

        setDefaultCircleImage(size1BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(size2BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(size3BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(size4BgImageView, R.color.md_grey_400);
        setDefaultCircleImage(size5BgImageView, R.color.md_grey_400);

        size1TextView = findViewById(R.id.size1TextView);
        size2TextView = findViewById(R.id.size2TextView);
        size3TextView = findViewById(R.id.size3TextView);
        size4TextView = findViewById(R.id.size4TextView);
        size5TextView = findViewById(R.id.size5TextView);

        // Set Color Default
        color1ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
        color1Status = true;


        // Set Size Default
        setSelectUnSelectSizeFilter(size3BgImageView, R.color.colorPrimary, size3TextView, R.color.md_white_1000);
        size3Status = true;


        // get list adapter
        adapter = new FeatureDetailECommerceItemDetail1ReviewAdapter(userReviewList);

        // get recycler view
        recyclerView = findViewById(R.id.reviewRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void setDefaultCircleImage(ImageView imageView, int color) {
        Utils.setCircleImageToImageView(getApplicationContext(), imageView, R.drawable.white_background, color, 10, R.color.colorLine);
    }

    private void setSelectUnSelectSizeFilter(ImageView imageView, int bgColor, TextView textView, int color) {
        imageView.setColorFilter(getResources().getColor(bgColor), PorterDuff.Mode.SRC_IN);
        textView.setTextColor(getResources().getColor(color));
    }

    private void initDataBindings() {

        imageViewPager.setAdapter(FeatureDetailECommerceItemDetail1PagerAdapter);
        setupSliderPagination();

        addressTextView.setText(shopItem.shop.shopAddress);
        phoneTextView.setText(shopItem.shop.shopPhone);
        websiteTextView.setText(shopItem.shop.shopWebsite);
        emailTextView.setText(shopItem.shop.shopEmail);

        descTextView.setText(shopItem.description);
        nameTextView.setText(shopItem.name);
        reviewCountTextView.setText(shopItem.ratingCount);
        itemRatingBar.setRating(Float.parseFloat(shopItem.totalRating));

        String priceStr = "$ " + shopItem.price;
        String originalPriceStr = "$ " + shopItem.originalPrice;
        priceTextView.setText(priceStr);
        originalPriceTextView.setText(originalPriceStr);
        originalPriceTextView.setPaintFlags(originalPriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        // bind adapter to recycler
        recyclerView.setAdapter(adapter);
    }

    private void initActions() {
        imageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (pager_indicator != null) {//&& dots == null) {
                    setupSliderPagination();
                }

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }

                dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //region Size
        size1TextView.setOnClickListener((View v) -> {
            if (size1Status) {
                setSelectUnSelectSizeFilter(size1BgImageView, R.color.md_grey_400, size1TextView, R.color.md_grey_800);
                size1Status = false;
            } else {
                setSelectUnSelectSizeFilter(size1BgImageView, R.color.colorPrimary, size1TextView, R.color.md_white_1000);
                size1Status = true;
            }

        });

        size2TextView.setOnClickListener((View v) -> {
            if (size2Status) {
                setSelectUnSelectSizeFilter(size2BgImageView, R.color.md_grey_400, size2TextView, R.color.md_grey_800);
                size2Status = false;
            } else {
                setSelectUnSelectSizeFilter(size2BgImageView, R.color.colorPrimary, size2TextView, R.color.md_white_1000);
                size2Status = true;
            }

        });

        size3TextView.setOnClickListener((View v) -> {
            if (size3Status) {
                setSelectUnSelectSizeFilter(size3BgImageView, R.color.md_grey_400, size3TextView, R.color.md_grey_800);
                size3Status = false;
            } else {
                setSelectUnSelectSizeFilter(size3BgImageView, R.color.colorPrimary, size3TextView, R.color.md_white_1000);
                size3Status = true;
            }

        });

        size4TextView.setOnClickListener((View v) -> {
            if (size4Status) {
                setSelectUnSelectSizeFilter(size4BgImageView, R.color.md_grey_400, size4TextView, R.color.md_grey_800);
                size4Status = false;
            } else {
                setSelectUnSelectSizeFilter(size4BgImageView, R.color.colorPrimary, size4TextView, R.color.md_white_1000);
                size4Status = true;
            }

        });

        size5TextView.setOnClickListener((View v) -> {
            if (size5Status) {
                setSelectUnSelectSizeFilter(size5BgImageView, R.color.md_grey_400, size5TextView, R.color.md_grey_800);
                size5Status = false;
            } else {
                setSelectUnSelectSizeFilter(size5BgImageView, R.color.colorPrimary, size5TextView, R.color.md_white_1000);
                size5Status = true;
            }

        });

        //endregion

        //region Color

        color1ImageView.setOnClickListener((View v) -> {
            if (color1Status) {
                color1ImageView.setImageDrawable(null);
                color1Status = false;
            } else {
                color1ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color1Status = true;
            }

        });

        color2ImageView.setOnClickListener((View v) -> {
            if (color2Status) {
                color2ImageView.setImageDrawable(null);
                color2Status = false;
            } else {
                color2ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color2Status = true;
            }

        });

        color3ImageView.setOnClickListener((View v) -> {
            if (color3Status) {
                color3ImageView.setImageDrawable(null);
                color3Status = false;
            } else {
                color3ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color3Status = true;
            }

        });

        color4ImageView.setOnClickListener((View v) -> {
            if (color4Status) {
                color4ImageView.setImageDrawable(null);
                color4Status = false;
            } else {
                color4ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color4Status = true;
            }

        });

        color5ImageView.setOnClickListener((View v) -> {
            if (color5Status) {
                color5ImageView.setImageDrawable(null);
                color5Status = false;
            } else {
                color5ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color5Status = true;
            }

        });

        color6ImageView.setOnClickListener((View v) -> {
            if (color6Status) {
                color6ImageView.setImageDrawable(null);
                color6Status = false;
            } else {
                color6ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color6Status = true;
            }

        });

        color7ImageView.setOnClickListener((View v) -> {
            if (color7Status) {
                color7ImageView.setImageDrawable(null);
                color7Status = false;
            } else {
                color7ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24);
                color7Status = true;
            }

        });

        //endregion

        adapter.setOnItemClickListener((view, obj, position) -> Toast.makeText(this, "Selected : " + obj.userName, Toast.LENGTH_LONG).show());

        FeatureDetailECommerceItemDetail1PagerAdapter.setOnItemClickListener((view, obj, position) -> Toast.makeText(this, "Selected : " + obj.imageList.get(position).imageName, Toast.LENGTH_SHORT).show());

        viewAllCommentTextView.setOnClickListener((View v) -> Toast.makeText(this, "Clicked View All Reviews.", Toast.LENGTH_SHORT).show());
//        addToBasketButton.setOnClickListener((View v) -> Toast.makeText(this, "Clicked add to basket..", Toast.LENGTH_SHORT).show());
//        addToFavouriteButton.setOnClickListener((View v) -> Toast.makeText(this, "Clicked add to favourite.", Toast.LENGTH_SHORT).show());

        phoneTextView.setOnClickListener(v -> Toast.makeText(this, "Clicked phone.", Toast.LENGTH_SHORT).show());
        emailTextView.setOnClickListener(v -> Toast.makeText(this, "Clicked email.", Toast.LENGTH_SHORT).show());
        websiteTextView.setOnClickListener(v -> Toast.makeText(this, "Clicked website.", Toast.LENGTH_SHORT).show());
    }

    public void setupSliderPagination() {

        dotsCount = FeatureDetailECommerceItemDetail1PagerAdapter.getCount();

        if (dotsCount > 0 && dots == null) {

            dots = new ImageView[dotsCount];

            if (pager_indicator != null) {
                if (pager_indicator.getChildCount() > 0) {
                    pager_indicator.removeAllViewsInLayout();
                }
            }

            for (int i = 0; i < dotsCount; i++) {
                dots[i] = new ImageView(this);
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.setMargins(4, 0, 4, 0);

                pager_indicator.addView(dots[i], params);
            }

            dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

        }

    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24);

        if (toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        }

        toolbar.setTitle("Item Details");

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

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);

        if (Utils.isRTL()) {
            collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.END);
        } else {
            collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.START);
        }
    }
}
