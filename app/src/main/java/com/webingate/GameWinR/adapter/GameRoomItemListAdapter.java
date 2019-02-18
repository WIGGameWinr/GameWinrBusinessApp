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
import com.webingate.GameWinR.object.ShopItem;
import com.webingate.GameWinR.utils.Utils;

import java.util.List;

/**
 * Created by Panacea-Soft on 17/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class GameRoomItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ShopItem> shopItemList;
    private OnItemClickListener itemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, ShopItem obj, int position);
        //void onAddToCartClick(View view, ShopItem obj, int position);
        //void onMenuClick(View view, ShopItem obj, int position);
          //void onItemClick(View view, Place obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public GameRoomItemListAdapter(List<ShopItem> shopItemList) {
        this.shopItemList = shopItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_room_list__activity_item, parent, false);

        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof PlaceViewHolder) {

            ShopItem shopItem = shopItemList.get(position);

            PlaceViewHolder holder = (PlaceViewHolder) viewHolder;
            holder.gametitlename.setText(shopItem.name);

            Context context = holder.holderCardView.getContext();

            int id = Utils.getDrawableInt(context, shopItem.imageName);
            Utils.setImageToImageView(context, holder.gameimage, id);



            if ( itemClickListener != null ) {
                holder.holderCardView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, shopItemList.get(position), position));
            }

        }
    }

    @Override
    public int getItemCount() {
        return shopItemList.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        ImageView gameimage;
        TextView gametitlename;
        CardView holderCardView;


        PlaceViewHolder(View view) {
            super(view);
               gameimage=view.findViewById(R.id.gameimage);
               holderCardView = view.findViewById(R.id.holderCardView);
            gametitlename=view.findViewById(R.id.gametitlename);

        }
    }
}
