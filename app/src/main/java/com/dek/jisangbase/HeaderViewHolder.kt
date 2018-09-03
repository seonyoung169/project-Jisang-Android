package com.dek.jisangbase

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by SY Lee on 2018-01-02.
 */
class HeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var text : TextView = itemView!!.findViewById(R.id.registerd_item_ment)
    var garbage : ImageView = itemView!!.findViewById(R.id.garbage_bin)

}