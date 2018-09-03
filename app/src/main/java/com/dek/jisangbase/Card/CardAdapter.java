package com.dek.jisangbase.Card;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.dek.jisangbase.GET.GetWholeBestData;
import com.dek.jisangbase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 김진석 on 2018-01-07.
 */

//TODO 5개가 10개로. 10개가 20개로 두번 들어감
public class CardAdapter extends PagerAdapter implements ProductListByCategoryCardAdapter {

    private List<CardView> mViews;
    private ArrayList<GetWholeBestData> mData;
    private float mBaseElevation;
    private RequestManager requestManager;
    private Context context;

    public CardAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public CardAdapter(Context context) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        this.context = context;

    }

    public CardAdapter(ArrayList<GetWholeBestData> mData, RequestManager requestManager) {
        this.mViews = new ArrayList<>();
        this.mData = new ArrayList<>();
        this.mData = mData;
        this.requestManager = requestManager;
        this.mViews = new ArrayList<>();
        this.context = context;
        for(int i = 0; i < mData.size(); i++) {
            mViews.add(null);
        }

    }


    public void addCardItem(GetWholeBestData item) {
        mViews.add(null);
        mData.add(item);
    }


    public float getBaseElevation() {
        return mBaseElevation;
    }


    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.best_item_row, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardview);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(GetWholeBestData item, View view) {

        ImageView imageView = (ImageView)view.findViewById(R.id.card_image);
        requestManager.load(item.getPimg()).into(imageView);

    }
}
