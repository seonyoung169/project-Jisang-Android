package com.dek.storedetail.item_question_reply

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.dek.jisangbase.R

class QuestionRelyViewHolder (itemView : View?) : RecyclerView.ViewHolder(itemView){

    var questionReplyWriter : TextView = itemView!!.findViewById(R.id.question_reply_writer)

    //var questionlayout : TextView = itemView!!.findViewById(R.id.item_question_recyclerview_layout) //리사이클러뷰 효과를 위해 임시 추가

    var questionReplyContext : TextView = itemView!!.findViewById(R.id.question_reply_contet)


}