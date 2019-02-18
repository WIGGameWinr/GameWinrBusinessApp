package com.webingate.GameWinR.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.webingate.GameWinR.R;

import butterknife.BindView;
//import cropper.CropImage;
//import cropper.CropImageView;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class CouponInfoFragment extends Fragment {

    //ImageView profileImageView;
    @BindView(R.id.s_name)
    EditText name;
    @BindView(R.id.s_size)
    EditText size;
    @BindView(R.id.s_colour)
    EditText colour;
    @BindView(R.id.s_pincode)
    EditText pincode;
    @BindView(R.id.s_charge)
    EditText charges;
    @BindView(R.id.s_discount)
    EditText discount;
    @BindView(R.id.s_deliverycharge)
    EditText deliverycharge;
    Toast toast;
//    @BindView(R.id.edituserphoto)
//    ImageView userphoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.newcoupon_infofragment, container, false);

//        initData();
//
        initUI(view);
//
//        initDataBindings();
//
        initActions(view);
//
        return view;
    }
//
//    private void initData() {
//        productsList = DirectoryHome9Repository.getfleetownerList();
//        categoryList = DirectoryHome9Repository.getCategoryList();
//        promotionsList = DirectoryHome9Repository.getPromotionsList();
//        popularList = DirectoryHome9Repository.getPopularList();
//        flightsList = DirectoryHome9Repository.getFlightsList();
//    }
//
    private void initUI(View view) {

//        profileImageView = view.findViewById(R.id.profileImageView);
//        int id = R.drawable.profile2;
//        Utils.setCornerRadiusImageToImageView(view.getContext(), profileImageView, id, 20, 2,  R.color.md_white_1000);
        setName((EditText)view.findViewById(R.id.s_name));

        name = view.findViewById(R.id.s_name);
        size = view.findViewById(R.id.s_size);
        colour = view.findViewById(R.id.s_colour);
        pincode = view.findViewById(R.id.s_pincode);
        charges = view.findViewById(R.id.s_charge);
        discount = view.findViewById(R.id.s_discount);
        deliverycharge = view.findViewById(R.id.s_deliverycharge);
        //userphoto = view.findViewById(R.id.edituserphoto);
    }
    private void initActions(View view) {


//        userphoto.setOnClickListener((View v) -> {
//            Toast.makeText(getActivity(), "Clicked on Pen of Profile", Toast.LENGTH_SHORT).show();
////            startActivity(new Intent(getActivity(), customerappTrainBookingSearchActivity.class));
//            //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
//            CropImage.activity()
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .setActivityTitle("My Crop")
//                    .setCropMenuCropButtonTitle("Done")
//                    .setRequestedSize(400, 800)
//                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
//                    .start(this.getActivity());
//        });
    }
    public EditText getName() {
        return name;
    }

    public void setName(EditText name) {
        this.name = name;
    }



}
