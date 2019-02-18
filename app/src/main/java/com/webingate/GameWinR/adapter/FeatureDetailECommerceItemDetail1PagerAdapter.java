package com.webingate.GameWinR.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.webingate.GameWinR.R;
import com.webingate.GameWinR.object.ShopItem;
import com.webingate.GameWinR.utils.Utils;

/**
 * Created by Panacea-Soft on 19/7/18.
 * Contact Email : teamps.is.cool@gmail.com
 * Website : http://www.panacea-soft.com
 */
public class FeatureDetailECommerceItemDetail1PagerAdapter extends PagerAdapter {

    private Context context;
    private ShopItem shopItem;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, ShopItem obj, int position);
    }

    public FeatureDetailECommerceItemDetail1PagerAdapter(Context context, ShopItem shopItem) {
        this.context = context;
        this.shopItem = shopItem;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.itemClickListener = mItemClickListener;
    }

    @Override
    public int getCount() {
        return shopItem.imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            View view = layoutInflater.inflate(R.layout.feature_detail_ecommerce_item_detail_1_pager_item, container, false);
            ImageView pagerItemImageView = view.findViewById(R.id.pagerItemImageView);
            ImageView pagerItemBgImageView = view.findViewById(R.id.pagerItemBgImageView);


            Context context = container.getContext();

            int id = Utils.getDrawableInt(context, shopItem.imageList.get(position).imageName);
            Utils.setImageToImageView(context, pagerItemImageView, id);

            Utils.setImageToImageView(context, pagerItemBgImageView, R.drawable.black_top_bottom_alpha_70);

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);

            // Listeners
            if (itemClickListener != null) {
                pagerItemImageView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, shopItem, position));
                pagerItemBgImageView.setOnClickListener((View v) -> itemClickListener.onItemClick(v, shopItem, position));
            }

            return view;
        }

        return container.getRootView();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}

