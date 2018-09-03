package com.dek.storedetail.item_question

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

class CustomLinear(context: Context?) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean {
        return false
    }
}