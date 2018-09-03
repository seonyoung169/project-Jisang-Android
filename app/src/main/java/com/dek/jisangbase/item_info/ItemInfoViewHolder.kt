package com.dek.jisangbase.item_info

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.dek.jisangbase.R

/**
 * Created by 김진석 on 2018-01-11.
 */
class ItemInfoViewHolder (itemView : View?) : RecyclerView.ViewHolder(itemView){

    var itemInfoImage : ImageView = itemView!!.findViewById(R.id.item_info_imageview)
}
