package com.dek.jisangbase.Search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dek.jisangbase.R

/**
 * Created by SY Lee on 2018-01-06.
 */
class ResultItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var itemImage : ImageView = itemView!!.findViewById(R.id.item_image)
    var itemnameText : TextView = itemView!!.findViewById(R.id.item_name_text)
    var itempriceText : TextView = itemView!!.findViewById(R.id.item_price_text)
    var marketlocaText : TextView = itemView!!.findViewById(R.id.market_loca_text)

}