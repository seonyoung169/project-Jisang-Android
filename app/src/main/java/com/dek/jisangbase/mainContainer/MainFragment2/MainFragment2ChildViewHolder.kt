package com.dek.jisangbase.mainContainer.MainFragment2

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dek.jisangbase.R

class MainFragment2ChildViewHolder (itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var newItemImage : ImageView? = itemView!!.findViewById(R.id.item_image2)
    var newItemLoc : TextView? = itemView!!.findViewById(R.id.item_name_location2)
    var newItemName : TextView? = itemView!!.findViewById(R.id.item_name_text2)
    var newItemPrice : TextView? = itemView!!.findViewById(R.id.item_price_text2)
    //var newItemStore : TextView? = itemView!!.findViewById(R.id.market_loca_text2)

}