package com.dek.jisangbase

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

/**
 * Created by 김진석 on 2018-01-07.
 */
class CustomLinear(context: Context?) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean {
        return false
    }
}