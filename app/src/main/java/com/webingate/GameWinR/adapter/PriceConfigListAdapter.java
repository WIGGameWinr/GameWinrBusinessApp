package com.webingate.GameWinR.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webingate.GameWinR.Deo.GameResponce;
import com.webingate.GameWinR.R;

import java.util.ArrayList;

/**
 * Created by Panacea-Soft on 17/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class PriceConfigListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //private List<ShopItem> shopItemList;
    private OnItemClickListener itemClickListener;
    private ArrayList<GameResponce> placeArrayList;

    public interface OnItemClickListener {
        void onItemClick(View view, GameResponce obj, int position);
        //void onAddToCartClick(View view, ShopItem obj, int position);
        //void onMenuClick(View view, ShopItem obj, int position);
        //void onItemClick(View view, Place obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    public PriceConfigListAdapter(ArrayList<GameResponce> placeArrayList) {
        this.placeArrayList = placeArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list__activity_item, parent, false);

        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof PlaceViewHolder) {

            GameResponce gamelist = placeArrayList.get(position);

            PlaceViewHolder holder = (PlaceViewHolder) viewHolder;
            holder.gametitlename.setText(gamelist.getName());

            Context context = holder.holderCardView.getContext();


            if(gamelist.getGameImage()!=null){
                byte[] decodedString= Base64.decode(gamelist.getGameImage().substring(gamelist.getGameImage().indexOf(",")+1), Base64.DEFAULT);
                Bitmap image1 = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                holder.gameimage.setImageBitmap(image1);
            }
            else{

                holder.gameimage.setImageResource(R.drawable.game1);
            }

//            else{
//                int id = Utils.getDrawableInt(context, shopItem.imageName);
//                Utils.setImageToImageView(context, holder.gameimage, id);            }


            if ( itemClickListener != null ) {
                holder.holderCardView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, placeArrayList.get(position), position));
            }

        }
    }

    @Override
    public int getItemCount() {
        int a ;
        if(placeArrayList != null && !placeArrayList.isEmpty()) {
            a = placeArrayList.size();
        }
        else {
            a = 0;
        }
        return a;
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
