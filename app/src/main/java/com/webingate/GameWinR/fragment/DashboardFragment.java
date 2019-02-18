package com.webingate.GameWinR.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.webingate.GameWinR.GameRoomConfigListActivity;
import com.webingate.GameWinR.PricingConfigListActivity;
import com.webingate.GameWinR.R;
import com.webingate.GameWinR.GameListActivity;
import com.webingate.GameWinR.RoomConfigListActivity;
import com.webingate.GameWinR.TroubleTicketActivity;
import com.webingate.GameWinR.UsersListActivity;
import com.webingate.GameWinR.WinnersListActivity;
import com.webingate.GameWinR.adapter.ProductsAdapter;
import com.webingate.GameWinR.adapter.PromotionsAdapter;
import com.webingate.GameWinR.object.Image;
import com.webingate.GameWinR.object.ProductsVO;
import com.webingate.GameWinR.object.PromotionsVO;
import com.webingate.GameWinR.repository.DashboardRepository;
import com.webingate.GameWinR.utils.Tools;

import java.util.ArrayList;
import java.util.List;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class DashboardFragment extends Fragment {

    List<ProductsVO> productsList;
//    List<CategoryVO> categoryList;
    List<PromotionsVO> promotionsList;
//    List<PopularVO> popularList;
//    List<FlightsVO> flightsList;

    ProductsAdapter productsAdapter;
   // AppDirectoryHome9CategoryAdapter categoryAdapter;
   PromotionsAdapter promotionsAdapter;
//    PopularAdapter popularAdapter;
//    FlightsAdapter flightsAdapter;

    RecyclerView rvProduct, rvCategory, rvPromotions, rvPopular, rvFlights;

   // ImageView moreImageView, moreImageView2, profileImageView;

    TextView loginRegisterTextView;

    int noOfProductColumn = 5;
    int noOfPopularColumn = 2;
    private Runnable runnable = null;
    private Handler handler = new Handler();
    private LinearLayout layout_dots;
    private ViewPager viewPager;
    private AdapterImageSlider adapterImageSlider;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initData();
        initUI(view);
        initDataBindings();
        initActions();
        initComponent(view);
        return view;
    }

    private void initData() {
        productsList = DashboardRepository.getfleetownerList();
//        categoryList = DashboardRepository.getCategoryList();
        promotionsList = DashboardRepository.getPromotionsList();
//        popularList = DashboardRepository.getPopularList();
//        flightsList = DashboardRepository.getFlightsList();
    }

    private void initUI(View view) {

        productsAdapter = new ProductsAdapter(productsList);
       // categoryAdapter = new AppDirectoryHome9CategoryAdapter(categoryList);
        promotionsAdapter = new PromotionsAdapter(promotionsList);
//        popularAdapter = new PopularAdapter(popularList);
//        flightsAdapter = new FlightsAdapter(flightsList);

        if (getActivity() != null) {

            rvProduct = view.findViewById(R.id.rvProducts);
           // rvCategory = view.findViewById(R.id.rvCategory);
            rvPromotions = view.findViewById(R.id.rvPromotions);
//            rvPopular = view.findViewById(R.id.rvPopular);
//            rvFlights = view.findViewById(R.id.rvFlights);

            RecyclerView.LayoutManager productLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), noOfProductColumn);
            rvProduct.setLayoutManager(productLayoutManager);
            rvProduct.setAdapter(productsAdapter);
    //        rvProduct.setOnClickListener();

           // RecyclerView.LayoutManager categoryLayoutManger = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
           // rvCategory.setLayoutManager(categoryLayoutManger);
           // rvCategory.setAdapter(categoryAdapter);

            RecyclerView.LayoutManager promotionsLayoutManger = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            rvPromotions.setLayoutManager(promotionsLayoutManger);
            rvPromotions.setAdapter(promotionsAdapter);

//            RecyclerView.LayoutManager popularLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), noOfPopularColumn);
//            rvPopular.setLayoutManager(popularLayoutManager);
//            rvPopular.setAdapter(popularAdapter);
//
//            RecyclerView.LayoutManager flightsLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//            rvFlights.setLayoutManager(flightsLayoutManager);
//            rvFlights.setAdapter(flightsAdapter);
        }

//        moreImageView = view.findViewById(R.id.moreImageView);
//        moreImageView2 = view.findViewById(R.id.moreImageView2);
        //profileImageView = view.findViewById(R.id.home9ProfileImageView);
        //loginRegisterTextView = view.findViewById(R.id.loginRegisterTextView);
    }

    private void initDataBindings() {
//        int leftImageId = R.drawable.baseline_arrow_right_24;
//        int profileImageId = R.drawable.home9_profile;

//        Utils.setImageToImageView(getContext(), moreImageView, leftImageId);
//        Utils.setImageToImageView(getContext(), moreImageView2, leftImageId);
      //  Utils.setImageToImageView(getContext(), profileImageView, profileImageId);

       // profileImageView.setOnClickListener(view -> Toast.makeText(getContext(), "Clicked : Profile", Toast.LENGTH_SHORT).show());

       // loginRegisterTextView.setOnClickListener(view -> Toast.makeText(getContext(), "Clicked : Log in and Register", Toast.LENGTH_SHORT).show());
    }

    private void initActions() {
        productsAdapter.setOnItemClickListener((view, promotion, position) -> {
                switch(position){
                    case 0:
                        startActivity(new Intent(getActivity(),GameListActivity.class ));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(),RoomConfigListActivity.class ));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(),GameRoomConfigListActivity.class ));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(),PricingConfigListActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(),UsersListActivity.class ));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(),WinnersListActivity.class ));
                        break;
                    case 6:
                        startActivity(new Intent(getActivity(),TroubleTicketActivity.class ));
                        break;
                    default:
                        break;
                }
            Toast.makeText(getContext(), "Clicked : " + promotion.getName(), Toast.LENGTH_SHORT).show();
        });


        //    categoryAdapter.setOnItemClickListener((view, category, position) -> Toast.makeText(getContext(), "Clicked : " + category.getName(), Toast.LENGTH_SHORT).show());

        promotionsAdapter.setOnItemClickListener((view, promotion, position) -> {
            if (position == 0)
                Toast.makeText(getContext(), "Clicked : See All Promos", Toast.LENGTH_SHORT).show();
            else
            {
                switch(position){
                    case 1:
//                        AppDirectoryHome1Fragment af = new AppDirectoryHome1Fragment();
//
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.home9Frame, af)
//                                .commitAllowingStateLoss();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        break;
                }

            }
                //Toast.makeText(getContext(), "Clicked : " + promotion.getName(), Toast.LENGTH_SHORT).show();
        });

//        popularAdapter.setOnItemClickListener((view, popular, position) -> Toast.makeText(getContext(), "Clicked : " + popular.getName(), Toast.LENGTH_SHORT).show());
//
//        flightsAdapter.setOnItemClickListener((view, flight, position) -> Toast.makeText(getContext(), "Clicked : " + flight.getCountry(), Toast.LENGTH_SHORT).show());

    }
    @Override
    public void onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable);
        super.onDestroy();
    }
    public static class AdapterImageSlider extends PagerAdapter {

        private Activity act;
        private List<Image> items;

        private AdapterImageSlider.OnItemClickListener onItemClickListener;

        private interface OnItemClickListener {
            void onItemClick(View view, Image obj);
        }

        public void setOnItemClickListener(AdapterImageSlider.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        // constructor
        public AdapterImageSlider(Activity activity, List<Image> items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        public Image getItem(int pos) {
            return items.get(pos);
        }

        public void setItems(List<Image> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final Image o = items.get(position);
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_slider_image, container, false);

            ImageView image = (ImageView) v.findViewById(R.id.image);
            MaterialRippleLayout lyt_parent = (MaterialRippleLayout) v.findViewById(R.id.lyt_parent);
            Tools.displayImageOriginal(act, image, o.image);
            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, o);
                    }
                }
            });

            ((ViewPager) container).addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((RelativeLayout) object);

        }

    }
    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getContext());
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle_outline);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setImageResource(R.drawable.shape_circle);
        }
    }

    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }
    public void initComponent(View view) {
        layout_dots = view.findViewById(R.id.layout_dots);
        viewPager = view.findViewById(R.id.pager);
        adapterImageSlider = new AdapterImageSlider(getActivity(), new ArrayList<Image>());

        final List<Image> items = new ArrayList<>();
        for (int i = 0; i < array_image_place.length; i++) {
            Image obj = new Image();
            obj.image = array_image_place[i];
            obj.imageDrw = getResources().getDrawable(obj.image);
            // obj.name = array_title_place[i];

            items.add(obj);
        }

        adapterImageSlider.setItems(items);
        viewPager.setAdapter(adapterImageSlider);

        // displaying selected image first
        viewPager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        // ((TextView) findViewById(R.id.title)).setText(items.get(0).name);
        //((TextView) findViewById(R.id.brief)).setText(items.get(0).brief);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                // ((TextView) findViewById(R.id.title)).setText(items.get(pos).name);
                //((TextView) findViewById(R.id.brief)).setText(items.get(pos).brief);
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        startAutoSlider(adapterImageSlider.getCount());
    }

}
