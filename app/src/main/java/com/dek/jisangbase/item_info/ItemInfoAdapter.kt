package com.dek.jisangbase.item_info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.R

/**
 * Created by 김진석 on 2018-01-11.
 */
class ItemInfoAdapter (var ItemInfoList: ArrayList<ItemInfoData>, var requestManager: RequestManager) : RecyclerView.Adapter<ItemInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemInfoViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.fragment_item_info_items,parent,false)

        return ItemInfoViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: ItemInfoViewHolder?, position: Int) {
        requestManager.load(ItemInfoList!!.get(position).img).into(holder!!.itemInfoImage)
    }
    override fun getItemCount(): Int = ItemInfoList!!.size

}