package com.webingate.GameWinR.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.webingate.GameWinR.R;
//import com.webingate.giftsolution.driverapplication.Deo.DrivermasterResponse;
import com.webingate.GameWinR.object.Place;
import com.webingate.GameWinR.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Panacea-Soft on 17/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Place> placeArrayList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Place obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public UserListAdapter(ArrayList<Place> placeArrayList) {
        this.placeArrayList = placeArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userslist_item, parent, false);

        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof PlaceViewHolder) {
            Place place = placeArrayList.get(position);

            PlaceViewHolder holder = (PlaceViewHolder) viewHolder;
            holder.placeNameTextView.setText(place.getName());

            Context context = holder.placeHolderCardView.getContext();
            // int id = Utils.getDrawableInt(context, place.getPhoto());

//            int id = Utils.getDrawableInt(context, "user_round_button");
                int id = Utils.getDrawableInt(context, place.imageName);
            Utils.setImageToImageView(context, holder.placeImageView, id);

            holder.typeTextView.setText("12");
            holder.cityTextView.setText("");
            holder.placeRatingBar.setRating(Float.parseFloat("4"));
            holder.totalRatingTextView.setText("4");
            holder.ratingCountTextView.setText("4");

//            if (place.discount != null && Integer.parseInt(place.discount) > 0) {
//                holder.promoCardView.setVisibility(View.VISIBLE);
//                String discount = place.discount + " %";
//                holder.promoAmtTextView.setText(discount);
//            } else {
//                holder.promoCardView.setVisibility(View.GONE);
//            }

            if ( itemClickListener != null ) {
                holder.placeHolderCardView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, placeArrayList.get(position), position));
            }
        }
    }

//    @Override
//    public int getItemCount() {
//        int a ;
//        if(placeArrayList != null && !placeArrayList.isEmpty()) {
//            a = placeArrayList.size();
//        }
//        else {
//            a = 0;
//        }
//        return a;
//    }

    @Override
    public int getItemCount() {
        return placeArrayList.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
         ImageView placeImageView;
         TextView placeNameTextView;
         TextView typeTextView;
         TextView cityTextView;
         TextView totalRatingTextView;
         TextView ratingCountTextView;
         RatingBar placeRatingBar;
      //  public TextView promoAmtTextView;
         //CardView promoCardView;
         CardView placeHolderCardView;

         PlaceViewHolder(View view) {
            super(view);

            placeImageView = view.findViewById(R.id.placeImageView);
            placeNameTextView = view.findViewById(R.id.placeNameTextView);
            typeTextView = view.findViewById(R.id.distanceTextView);
            cityTextView = view.findViewById(R.id.cityTextView);
            totalRatingTextView = view.findViewById(R.id.totalRatingTextView);
            ratingCountTextView = view.findViewById(R.id.ratingCountTextView);
            placeRatingBar = view.findViewById(R.id.placeRatingBar);
         //   promoAmtTextView = view.findViewById(R.id.promoAmtTextView);
            //promoCardView = view.findViewById(R.id.promoCardView);
            placeHolderCardView = view.findViewById(R.id.placeHolderCardView);
        }


    }


}
