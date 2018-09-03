package com.dek.storedetail.item_question

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetQuestionCommentResponse
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.dek.jisangbase.storedetail.ItemQuestionFragment
import com.dek.storedetail.item_question_reply.QuestionReplyAdapter
import com.dek.storedetail.item_question_reply.QuestionReplyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionAdapter(var dataList : ArrayList<QuestionData>?, var myContext : Context) : RecyclerView.Adapter<QuestionViewHolder>() { //생성자

    private var onItemClick : View.OnClickListener? = null
    private var cliked :  Boolean = false
    //선택이 되어 있는가(과거형)

    private var networkService : NetworkService? = null
    private var questionCommentAdapter : QuestionReplyAdapter? = null

    private var customLinear : CustomLinear? = null
    private var question_reply_item : QuestionReplyAdapter? = null

    //함수 오버라이드
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): QuestionViewHolder {
        //adapter가 어떤 viewHolder를 잡을지 결정하는 부분
        val mainView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.question_items,parent,false)
        mainView.setOnClickListener(onItemClick)
        //viewHodler를 쓰겠다고 선언

        networkService = ApplicationController.instance!!.networkService

        return QuestionViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder?, position: Int) {
        //adapter를 이용해서 data를 사용자에게 보여주는 부분
        holder!!.questionWriter.setText(dataList!!.get(position).username)
        holder!!.questionContext.setText(dataList!!.get(position).content)

        holder!!.questionLayout.setOnClickListener {
            if(cliked) {
                holder!!.questionContext.setSingleLine(true)
                holder!!.questReplyFrame.visibility = View.GONE
                getquestioncomment(dataList!![position].qid,holder.questionRelyList)
                holder!!.questionRelyList.layoutManager = LinearLayoutManager(myContext)
                holder!!.questionRelyList.adapter = QuestionReplyAdapter(Common.questionReplyDatas)

                cliked = false
            }else{
                holder!!.questionContext.setSingleLine(false)
                holder!!.questReplyFrame.visibility = View.VISIBLE
                cliked = true
            }
        }
    }

    override fun getItemCount(): Int = dataList!!.size //item에 들어가는 갯수만큼, ArrayList의 길이 반환

    //받아올 함수
    fun setOnItemclickLister(l: ItemQuestionFragment){
        onItemClick = l
    }

    fun getquestioncomment(qid : Int,list : RecyclerView){

        var getQuestionCommentResponse = networkService!!.getQuestionComment(qid)

        Log.d("ash3734",qid.toString()+"qid")
        getQuestionCommentResponse.enqueue(object : Callback<GetQuestionCommentResponse> {
            override fun onResponse(call: Call<GetQuestionCommentResponse>?, response: Response<GetQuestionCommentResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){

                        for (i in 0..response!!.body().commentArr.size - 1) {
                            Common.questioncommentDatas.add(QuestionReplyData(response!!.body().commentArr[i].cid, response!!.body().commentArr[i].username, response!!.body().commentArr[i].comment))

                            Log.v("que",response!!.body().commentArr[i].comment)
                        }
                        questionCommentAdapter = QuestionReplyAdapter(Common.questioncommentDatas)
                        list!!.adapter = questionCommentAdapter


                        ApplicationController.instance!!.makeToast("유저 문의사항 가져오기 성공!!")
                    }
                    else{
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    ApplicationController.instance!!.makeToast(response!!.message())
                }

            }
            override fun onFailure(call: Call<GetQuestionCommentResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

}