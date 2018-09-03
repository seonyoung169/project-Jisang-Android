package com.dek.jisangbase.market

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dek.jisangbase.R

class MarketItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var normalitemImg : ImageView = itemView!!.findViewById(R.id.marketmain_item_image)
    var normalitemName : TextView = itemView!!.findViewById(R.id.marketmain_item_text)
    var normalitemPrice : TextView = itemView!!.findViewById(R.id.marketmain_item_price_text)

    //var slidingLayout : LinearLayout = itemView!!.findViewById(R.id.sliding_layout)
}
