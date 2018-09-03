package com.dek.jisangbase.Search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.GET.GetKeywordData
import com.dek.jisangbase.R
import com.dek.jisangbase.Search.ResultHeaderViewHolder
import com.dek.jisangbase.Search.ResultItemViewHolder
import java.util.*

/**
 * Created by SY Lee on 2018-01-06.
 */


class ResultAdapter(private var context: Context, private val requestManager: RequestManager, private var dataList: ArrayList<GetKeywordData>?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var keyword : String? = null

    private val HEADER_POSITION : Int = 0
    private val TYPE_HEADER = 1
    private val TYPE_ITEM = 2


    fun setContext(context: Context){
        this.context = context
    }

    override fun getItemCount(): Int {
        return (if(dataList != null) dataList!!.size + 1  else 0)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {

        if (viewType == TYPE_HEADER) {
            val v = LayoutInflater.from(context).inflate(R.layout.search_result_header, parent, false)
            return ResultHeaderViewHolder(v)
        } else{
            val v = LayoutInflater.from(context).inflate(R.layout.search_result_item, parent, false)
            return ResultItemViewHolder(v)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) =
            if(holder is ResultHeaderViewHolder) { }
            else{
                var itemViewHolder = holder as ResultItemViewHolder

                requestManager.load(dataList!!.get(position-1).productimg).into(itemViewHolder!!.itemImage)
                itemViewHolder.itempriceText!!.text = dataList!!.get(position-1).price
                itemViewHolder.itemnameText!!.text = dataList!!.get(position-1).name
                itemViewHolder.marketlocaText!!.text = dataList!!.get(position-1).marketname

            }

    override fun getItemViewType(position: Int): Int {
        if(position ==HEADER_POSITION){
            return TYPE_HEADER
        }
        else{
            return TYPE_ITEM
        }
    }

}