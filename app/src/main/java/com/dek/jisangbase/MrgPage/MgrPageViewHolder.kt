package com.dek.jisangbase.MrgPage

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.dek.jisangbase.R

/**
 * Created by 김진석 on 2018-01-08.
 */
class MgrPageViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var itemNumber : TextView? = itemView!!.findViewById(R.id.item_number)
    var itemDate : TextView? = itemView!!.findViewById(R.id.item_date)

    var myPageItem1 : ImageView? = itemView!!.findViewById(R.id.my_page_item1)
    var myPageItem2 : ImageView? = itemView!!.findViewById(R.id.my_page_item2)
    var myPageItem3 : ImageView? = itemView!!.findViewById(R.id.my_page_item3)
    var nextButton : Button? = itemView!!.findViewById(R.id.mgr_page_storego_btn)
}