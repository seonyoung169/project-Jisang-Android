package com.dek.jisangbase.market

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.dek.jisangbase.R
import android.view.View


class MarketHeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var bestitemText : TextView = itemView!!.findViewById(R.id.bestitem_text)

    var itemImage1 : ImageView = itemView!!.findViewById(R.id.best_item1)
    var itemImage2 : ImageView = itemView!!.findViewById(R.id.best_item2)
    var itemImage3 : ImageView = itemView!!.findViewById(R.id.best_item3)
    var itemImage4 : ImageView = itemView!!.findViewById(R.id.best_item4)
    var itemImage5 : ImageView = itemView!!.findViewById(R.id.best_item5)
    var itemImage6 : ImageView = itemView!!.findViewById(R.id.best_item6)

    var allitemText : TextView = itemView!!.findViewById(R.id.allitem_text)

}