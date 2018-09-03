package com.dek.jisangbase.Search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.dek.jisangbase.R

/**
 * Created by SY Lee on 2018-01-06.
 */
class ResultHeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var searchresultText : TextView = itemView!!.findViewById(R.id.search_result_text)
    var allitemText : TextView = itemView!!.findViewById(R.id.allitem_text)
}