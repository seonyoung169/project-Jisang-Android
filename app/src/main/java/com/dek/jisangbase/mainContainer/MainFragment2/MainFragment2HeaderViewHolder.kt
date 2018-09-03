package com.dek.jisangbase.mainContainer.MainFragment2

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.dek.jisangbase.R

/**
 * Created by 김진석 on 2018-01-10.
 */
class MainFragment2HeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
    var viewPager : ViewPager? = itemView!!.findViewById(R.id.viewPager)
    var all : Button? = itemView!!.findViewById(R.id.item_btn_all)
    var outer : Button? = itemView!!.findViewById(R.id.item_btn_outer)
    var top : Button? = itemView!!.findViewById(R.id.item_btn_top)
    var pants : Button? = itemView!!.findViewById(R.id.item_btn_pants)
    var skirt : Button? = itemView!!.findViewById(R.id.item_btn_skirt)
    var onepiece : Button? = itemView!!.findViewById(R.id.item_btn_onepiece)
    var shoes : Button? = itemView!!.findViewById(R.id.item_btn_shoes)
    var bag : Button? = itemView!!.findViewById(R.id.item_btn_bag)
    var acc : Button? = itemView!!.findViewById(R.id.item_btn_acc)
}