package com.webingate.GameWinR.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webingate.GameWinR.R;
import com.webingate.GameWinR.object.PopularVO;
import com.webingate.GameWinR.utils.Utils;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PopularVO> popularList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, PopularVO popular, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public PopularAdapter(List<PopularVO> popularList) {
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View popularView = LayoutInflater.from(parent.getContext()).inflate(R.layout.popularitem, parent, false);
        return new PopularViewHolder(popularView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PopularViewHolder) {
            PopularVO popularVO = popularList.get(position);
            PopularViewHolder popularViewHolder = (PopularViewHolder) holder;
            Context context = popularViewHolder.popularImageView.getContext();

            popularViewHolder.popularTitleTextView.setText(popularVO.getName());
            popularViewHolder.popularCityTextView.setText(popularVO.getPlace());

            int popularImageId = Utils.getDrawableInt(context, popularVO.getImage());
            Utils.setImageToImageView(context, popularViewHolder.popularImageView, popularImageId);

            if (itemClickListener != null) {
                ((PopularViewHolder) holder).popularCardView.setOnClickListener(view -> itemClickListener.onItemClick(view, popularList.get(position), position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        CardView popularCardView;
        ImageView popularImageView;
        TextView popularTitleTextView;
        TextView popularCityTextView;

        public PopularViewHolder(View itemView) {
            super(itemView);

            popularCardView = itemView.findViewById(R.id.popularCardView);
            popularImageView = itemView.findViewById(R.id.popularImageView);
            popularTitleTextView = itemView.findViewById(R.id.popularTitleTextView);
            popularCityTextView = itemView.findViewById(R.id.popularCityTextView);
        }
    }
}
