package com.dek.jisangbase.Like

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.dek.jisangbase.R


class LikeViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var marketImage : ImageView? = itemView!!.findViewById(R.id.market_image)
    var marketName : TextView? = itemView!!.findViewById(R.id.market_name)
    var marketLocation : TextView? = itemView!!.findViewById(R.id.market_location)
    var cancleButton : Button? = itemView!!.findViewById(R.id.like_list_cancel)

}

/*
class LikeViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var marketImage : ImageView? = itemView!!.findViewById(R.id.market_image)
    var marketName : TextView? = itemView!!.findViewById(R.id.market_name)
    var marketLocation : TextView? = itemView!!.findViewById(R.id.market_location)

}*/
