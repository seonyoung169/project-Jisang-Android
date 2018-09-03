package com.dek.storedetail.item_question

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.dek.jisangbase.R

//연결하기 id //list에 보여줄 정보... !!!!!!

class QuestionViewHolder (itemView : View?) : RecyclerView.ViewHolder(itemView){

    var questionWriter : TextView = itemView!!.findViewById(R.id.question_writer)

    var questionLayout : LinearLayout = itemView!!.findViewById(R.id.item_question_recyclerview_layout) //리사이클러뷰 효과를 위해 임시 추가

    var questionContext : TextView = itemView!!.findViewById(R.id.question_context)

    var questReplyFrame : RelativeLayout = itemView!!.findViewById(R.id.question_reply_frame)

    var questionRelyList : RecyclerView = itemView!!.findViewById(R.id.question_reply_recycler)

}
