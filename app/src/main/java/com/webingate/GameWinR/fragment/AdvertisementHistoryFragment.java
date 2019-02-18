package com.webingate.GameWinR.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.webingate.GameWinR.GamelistDetailActivity;
import com.webingate.GameWinR.R;
import com.webingate.GameWinR.adapter.FeatureListECommerceItemList1Adapter;
import com.webingate.GameWinR.object.ShopItem;

import java.util.List;


public class AdvertisementHistoryFragment extends Fragment {

    // data and adapter
    List<ShopItem> shopItemList;
    FeatureListECommerceItemList1Adapter adapter;

    // RecyclerView
    RecyclerView recyclerView;

    public AdvertisementHistoryFragment() {
        // Required empty public constructor
    }

//    @SuppressLint("ValidFragment")
//    public AdvertisementHistoryFragment(String status) {
//        // Required empty public constructor
//        this.status = status;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.feature_items_activity, container, false);
        initUI(view);
        initDataBindings();
        initActions();
        return view;
    }

    private void initUI(View view) {
//        statusTextView1 = view.findViewById(R.id.statusTextView1);
//        statusTextView1.setText(this.status);
//
//        statusTextView2 = view.findViewById(R.id.statusTextView2);
//        statusTextView2.setText(this.status);
//
//        statusTextView3 = view.findViewById(R.id.statusTextView3);
//        statusTextView3.setText(this.status);
//
////        copyImageView1 = view.findViewById(R.id.copyImageView1);
////        copyImageView2 = view.findViewById(R.id.copyImageView2);
////        copyImageView3 = view.findViewById(R.id.copyImageView3);
//
//        transNoTextView1 = view.findViewById(R.id.transNoTextView1);
//        transNoTextView2 = view.findViewById(R.id.transNoTextView2);
//        transNoTextView3 = view.findViewById(R.id.transNoTextView3);
        // get list adapter
        adapter = new FeatureListECommerceItemList1Adapter(shopItemList);

        // get recycler view
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    private void initDataBindings()
    {
        // bind adapter to recycler
        recyclerView.setAdapter(adapter);
    }

    private void initActions() {
        adapter.setOnItemClickListener(new FeatureListECommerceItemList1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ShopItem obj, int position) {
                Toast.makeText(getContext().getApplicationContext(), "Selected " + obj.name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),GamelistDetailActivity.class);
                startActivity(intent);
            }

//            @Override
//            public void onAddToCartClick(View view, ShopItem obj, int position) {
//                Toast.makeText(getApplicationContext(), "Clicked add to cart.", Toast.LENGTH_SHORT).show();
//            }

//            @Override
//            public void onMenuClick(View view, ShopItem obj, int position) {
//                Toast.makeText(getApplicationContext(), "Clicked menu.", Toast.LENGTH_SHORT).show();
//            }
        });
    }


}
