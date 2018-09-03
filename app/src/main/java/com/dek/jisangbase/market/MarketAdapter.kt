package com.dek.jisangbase.market

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.GET.GetMarketBestData
import com.dek.jisangbase.GET.GetMarketProductData
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R

/**
 * Created by 김진석 on 2018-01-07.
 */
class MarketAdapter(var dataList : ArrayList<GetMarketBestData>, var dataList2 : ArrayList<GetMarketProductData>,var requestManager : RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val HEADER_POSITION : Int = 0
    private val TYPE_HEADER = 1
    private val TYPE_ITEM = 2
    private var context : Context? = null

    private var networkService : NetworkService? = null


    fun setContext(context: Context){
        this.context = context
    }

    override fun getItemCount(): Int {
        return (if(dataList2 != null) dataList2!!.size + 1 else 0)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(holder is MarketHeaderViewHolder) {
            val bestViewHolder = holder as MarketHeaderViewHolder
            if(dataList!= null){
                if(dataList.size == 1){
                    requestManager.load(dataList[0].pimg).into(bestViewHolder!!.itemImage1)
                }else if(dataList.size == 2){
                    requestManager.load(dataList[0].pimg).into(bestViewHolder!!.itemImage1)
                    requestManager.load(dataList[1].pimg).into(bestViewHolder!!.itemImage2)
                }else if(dataList.size == 3){
                    requestManager.load(dataList[0].pimg).into(bestViewHolder!!.itemImage1)
                    requestManager.load(dataList[1].pimg).into(bestViewHolder!!.itemImage2)
                    requestManager.load(dataList[2].pimg).into(bestViewHolder!!.itemImage3)
                }else if(dataList.size == 4){
                    requestManager.load(dataList[0].pimg).into(bestViewHolder!!.itemImage1)
                    requestManager.load(dataList[1].pimg).into(bestViewHolder!!.itemImage2)
                    requestManager.load(dataList[2].pimg).into(bestViewHolder!!.itemImage3)
                    requestManager.load(dataList[3].pimg).into(bestViewHolder!!.itemImage4)
                }else if(dataList.size == 5){
                    requestManager.load(dataList[0].pimg).into(bestViewHolder!!.itemImage1)
                    requestManager.load(dataList[1].pimg).into(bestViewHolder!!.itemImage2)
                    requestManager.load(dataList[2].pimg).into(bestViewHolder!!.itemImage3)
                    requestManager.load(dataList[3].pimg).into(bestViewHolder!!.itemImage4)
                    requestManager.load(dataList[4].pimg).into(bestViewHolder!!.itemImage5)
                }else if(dataList.size == 6){
                    requestManager.load(dataList[0].pimg).into(bestViewHolder!!.itemImage1)
                    requestManager.load(dataList[1].pimg).into(bestViewHolder!!.itemImage2)
                    requestManager.load(dataList[2].pimg).into(bestViewHolder!!.itemImage3)
                    requestManager.load(dataList[3].pimg).into(bestViewHolder!!.itemImage4)
                    requestManager.load(dataList[4].pimg).into(bestViewHolder!!.itemImage5)
                    requestManager.load(dataList[5].pimg).into(bestViewHolder!!.itemImage6)
                }
            }

        }
        else{
            //val currentItem = dataList!!.get(position-1).itemImage
            val normalViewHolder = holder as MarketItemViewHolder
            if(dataList2 != null){
                Log.v("hogu",dataList2.size.toString())

                requestManager!!.load(dataList2!!.get(position-1).image).into(normalViewHolder!!.normalitemImg)
                normalViewHolder!!.normalitemName.setText(dataList2!!.get(position-1).name)
                normalViewHolder!!.normalitemPrice.setText(dataList2!!.get(position-1).price)
            }

            //requestManager.load(dataList2).into(normalViewHolder!!.viewPager)
            //ItemViewHolder.itemImage.setImageResource(currentItem)
            //requestManager.load(dataList2)!!.into(normalViewHolder.viewPager)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        networkService = ApplicationController.instance!!.networkService
//        requestManager = Glide.with(context)

        if(viewType == TYPE_HEADER) {
            val v = LayoutInflater.from(parent!!.context).inflate(R.layout.market_fragment_header, parent, false)
            return MarketHeaderViewHolder(v)
        }
        else{
            val v = LayoutInflater.from(parent!!.context).inflate(R.layout.market_fragment_items, parent, false)
            return MarketItemViewHolder(v)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == HEADER_POSITION) {
            return TYPE_HEADER
        }
        else {
            return TYPE_ITEM
        }
    }

}