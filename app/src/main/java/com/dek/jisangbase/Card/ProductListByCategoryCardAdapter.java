package com.dek.jisangbase.Card;

import android.support.v7.widget.CardView;

/**
 * Created by 김진석 on 2018-01-07.
 */

public interface ProductListByCategoryCardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
