package com.webingate.GameWinR.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.webingate.GameWinR.R;
import com.webingate.GameWinR.utils.ApplicationConstants;

import cropper.CropImage;
import cropper.CropImageView;

//import com.webingate.paysmartbusinessapp.businessapp.ApplicationConstants;
//import com.webingate.paysmartbusinessapp.businessapp.GetaLyft;

public class ItemImagesInfoFragment extends Fragment {

    //ImageView Firstimage;
    ImageView SecondImage;
    ImageView FirstImage;
    ImageView ThirdImage;
    ImageView FourthImage;
    ImageView fifthImage;
    ImageView sixthImage;
    Toast toast;
//    @BindView(R.id.edituserphoto)
//    ImageView userphoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.newitem_imagesfragment, container, false);


        initUI(view);

        initActions(view);

        return view;
    }

    private void initUI(View view) {
        FirstImage=(ImageView) view.findViewById(R.id.Firstimage);
         SecondImage=(ImageView) view.findViewById(R.id.Secondimage);
        ThirdImage=(ImageView) view.findViewById(R.id.Thirdimage);
        FourthImage=(ImageView) view.findViewById(R.id.Fourthimage);
        fifthImage=(ImageView) view.findViewById(R.id.fifthimage);
        sixthImage=(ImageView) view.findViewById(R.id.sixthimage);
//        Firstimage = view.findViewById(R.id.Firstimage);
//        int id = R.drawable.lighthouse;
//        Utils.setCornerRadiusImageToImageView(view.getContext(), Firstimage, id, 20, 2,  R.color.md_white_1000);
//
//        SecondImage = view.findViewById(R.id.Secondimage);
//        int id1 = R.drawable.penguins;
//        Utils.setCornerRadiusImageToImageView(view.getContext(), SecondImage, id1, 20, 2,  R.color.md_white_1000);
    }
    private void initActions(View view) {

        FirstImage.setOnClickListener((View v) -> {
            Toast.makeText(getActivity(), "Clicked on Image", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getActivity(), customerappTrainBookingSearchActivity.class));
            //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            ApplicationConstants.Itemimage = 1;
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("My Crop")
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(400, 800)
                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                    .start(this.getActivity());
        });
        SecondImage.setOnClickListener((View v) -> {
            Toast.makeText(getActivity(), "Clicked on Image", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getActivity(), customerappTrainBookingSearchActivity.class));
            //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            ApplicationConstants.Itemimage = 2;
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("My Crop")
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(400, 800)
                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                    .start(this.getActivity());
        });
        ThirdImage.setOnClickListener((View v) -> {
            Toast.makeText(getActivity(), "Clicked on Image", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getActivity(), customerappTrainBookingSearchActivity.class));
            //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            ApplicationConstants.Itemimage = 3;
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("My Crop")
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(400, 800)
                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                    .start(this.getActivity());
        });
        FourthImage.setOnClickListener((View v) -> {
            Toast.makeText(getActivity(), "Clicked on Image", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getActivity(), customerappTrainBookingSearchActivity.class));
            //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            ApplicationConstants.Itemimage = 4;
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("My Crop")
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(400, 800)
                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                    .start(this.getActivity());
        });
        fifthImage.setOnClickListener((View v) -> {
            Toast.makeText(getActivity(), "Clicked on Image", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getActivity(), customerappTrainBookingSearchActivity.class));
            //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            ApplicationConstants.Itemimage = 5;
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("My Crop")
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(400, 800)
                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                    .start(this.getActivity());
        });
        sixthImage.setOnClickListener((View v) -> {
            Toast.makeText(getActivity(), "Clicked on Image", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getActivity(), customerappTrainBookingSearchActivity.class));
            //startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            ApplicationConstants.Itemimage = 6;
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("My Crop")
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(400, 800)
                    .setCropMenuCropButtonIcon(R.drawable.badge_menu)
                    .start(this.getActivity());
        });
    }
//    public EditText getName() {
//        return name;
//    }
//
//    public void setName(EditText name) {
//        this.name = name;
//    }



}
