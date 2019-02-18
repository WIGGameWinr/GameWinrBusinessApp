package com.webingate.GameWinR.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.webingate.GameWinR.R;
import com.webingate.GameWinR.object.UserReview;

import java.util.List;

/**
 * Created by Panacea-Soft on 19/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class FeatureDetailECommerceItemDetail1ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserReview> userReviewList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, UserReview obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public FeatureDetailECommerceItemDetail1ReviewAdapter(List<UserReview> userReviewList) {
        this.userReviewList = userReviewList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_detail_ecommerce_item_detail_1_review_item, parent, false);

        return new UserReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof UserReviewViewHolder) {
            UserReview userReview = userReviewList.get(position);

            UserReviewViewHolder holder = (UserReviewViewHolder) viewHolder;
            String name = "By " + userReview.userName;
            holder.userNameTextView.setText(name);

            holder.dateTextView.setText(userReview.added);
            holder.reviewDetailTextView.setText(userReview.comment);

            holder.ratingBar.setRating(Float.parseFloat(userReview.totalRating));

            if (itemClickListener != null) {
                holder.holderCardView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, userReviewList.get(position), position));
            }
        }
    }

    @Override
    public int getItemCount() {

        if (userReviewList.size() > 3) {
            return 3;
        } else {
            return userReviewList.size();
        }
    }

    public class UserReviewViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTextView;
        TextView dateTextView;
        TextView reviewDetailTextView;
        RatingBar ratingBar;
        CardView holderCardView;

        UserReviewViewHolder(View view) {
            super(view);

            userNameTextView = view.findViewById(R.id.userNameTextView);
            dateTextView = view.findViewById(R.id.dateTextView);
            reviewDetailTextView = view.findViewById(R.id.reviewDetailTextView);
            ratingBar = view.findViewById(R.id.ratingBar);
            holderCardView = view.findViewById(R.id.holderCardView);
        }
    }
}
