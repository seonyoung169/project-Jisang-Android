package com.dek.storedetail.item_question_reply

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dek.jisangbase.ItemDetail.ItemDetailActivity
import com.dek.jisangbase.R


class QuestionReplyAdapter(var dataList : ArrayList<QuestionReplyData>?) : RecyclerView.Adapter<QuestionRelyViewHolder>() { //생성자

    private var onItemClick : View.OnClickListener? = null

    //함수 오버라이드
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): QuestionRelyViewHolder {
        //adapter가 어떤 viewHolder를 잡을지 결정하는 부분
        val mainView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.quetion_reply_items,parent,false)
        mainView.setOnClickListener(onItemClick)
        //viewHodler를 쓰겠다고 선언
        return QuestionRelyViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: QuestionRelyViewHolder?, position: Int) {
        //adapter를 이용해서 data를 사용자에게 보여주는 부분
        holder!!.questionReplyWriter.setText(dataList!!.get(position).username)
        holder!!.questionReplyContext.setText(dataList!!.get(position).comment)
    }

    override fun getItemCount(): Int = dataList!!.size //item에 들어가는 갯수만큼, ArrayList의 길이 반환

    //받아올 함수
    fun setOnItemclickLister(l: ItemDetailActivity){
        onItemClick = l
    }

}