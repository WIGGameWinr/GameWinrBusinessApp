package com.webingate.GameWinR.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.webingate.GameWinR.BookingHistoryActivity;
import com.webingate.GameWinR.GameListActivity;
import com.webingate.GameWinR.FeatureProfileGeneralProfile1Activity;
import com.webingate.GameWinR.PaymentsHistoryActivity;
import com.webingate.GameWinR.R;
import com.webingate.GameWinR.TroubleTicketActivity;
import com.webingate.GameWinR.UsersListActivity;
import com.webingate.GameWinR.adapter.ProductsAdapter;
import com.webingate.GameWinR.adapter.PromotionsAdapter;
import com.webingate.GameWinR.object.ProductsVO;
import com.webingate.GameWinR.object.PromotionsVO;
import com.webingate.GameWinR.repository.UserDashboardRepository;

import java.util.List;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class UserDashboardFragment extends Fragment {

    List<ProductsVO> productsList;
//    List<CategoryVO> categoryList;
    List<PromotionsVO> promotionsList;
//    List<PopularVO> popularList;
//    List<FlightsVO> flightsList;

    ProductsAdapter productsAdapter;
   // AppDirectoryHome9CategoryAdapter categoryAdapter;
    PromotionsAdapter promotionsAdapter;
////    PopularAdapter popularAdapter;
////    FlightsAdapter flightsAdapter;

    RecyclerView rvProduct,rvPromotions;
    //rvCategory, rvPromotions, rvPopular, rvFlights;

    //ImageView moreImageView, moreImageView2, profileImageView;

    TextView loginRegisterTextView;

    int noOfProductColumn = 5;
    int noOfPopularColumn = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        initData();

        initUI(view);

        initDataBindings();

        initActions();

        return view;
    }

    private void initData() {
        productsList = UserDashboardRepository.getfleetownerList();
//        categoryList = DashboardRepository.getCategoryList();
        promotionsList = UserDashboardRepository.getPromotionsList();
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
//
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
//
//        Utils.setImageToImageView(getContext(), moreImageView, leftImageId);
//        Utils.setImageToImageView(getContext(), moreImageView2, leftImageId);
      //  Utils.setImageToImageView(getContext(), profileImageView, profileImageId);

       // profileImageView.setOnClickListener(view -> Toast.makeText(getContext(), "Clicked : Profile", Toast.LENGTH_SHORT).show());

       // loginRegisterTextView.setOnClickListener(view -> Toast.makeText(getContext(), "Clicked : Log in and Register", Toast.LENGTH_SHORT).show());
    }

    private void initActions() {
        //productsAdapter.setOnItemClickListener((view, product, position) -> Toast.makeText(getContext(), "Clicked : " + product.getName(), Toast.LENGTH_SHORT).show());
        productsAdapter.setOnItemClickListener((view, promotion, position) -> {

                switch(position){
                    case 0:
                       // ApplicationConstants.marker = R.mipmap.marker_taxi;
                        Intent intent = new Intent(getActivity(),UsersListActivity.class );
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), PaymentsHistoryActivity.class);
                        startActivity(intent1);
//                        AppDirectoryHome1Fragment af1 = new AppDirectoryHome1Fragment();
//
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.home9Frame, af1)
//                                .commitAllowingStateLoss();
                        break;
                    case 2:
                        Intent intent4 = new Intent(getActivity(), TroubleTicketActivity.class);
                        startActivity(intent4);

                        break;
                    case 3:
                        intent = new Intent(getActivity(), GameListActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), FeatureProfileGeneralProfile1Activity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(getActivity(), BookingHistoryActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(getActivity(), GameListActivity.class);
                        startActivity(intent);
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
            //Toast.makeText(getContext(), "Clicked : " + promotion.getName(), Toast.LENGTH_SHORT).show();
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
//
//        popularAdapter.setOnItemClickListener((view, popular, position) -> Toast.makeText(getContext(), "Clicked : " + popular.getName(), Toast.LENGTH_SHORT).show());
//
//        flightsAdapter.setOnItemClickListener((view, flight, position) -> Toast.makeText(getContext(), "Clicked : " + flight.getCountry(), Toast.LENGTH_SHORT).show());

    }


}
