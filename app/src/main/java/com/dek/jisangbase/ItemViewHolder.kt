package com.dek.jisangbase
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by 김진석 on 2018-01-13.
 */
class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var itemImage : ImageView = itemView!!.findViewById(R.id.registerd_list_image)
    var checkBox : CheckBox = itemView!!.findViewById(R.id.item_checkbox)
}