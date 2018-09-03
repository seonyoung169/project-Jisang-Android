package com.dek.jisangbase.storedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.*
import com.dek.jisangbase.GET.GetQuestionProductResponse
import com.dek.jisangbase.POST.QuestionRegisterBody
import com.dek.jisangbase.POST.QuestionRegisterData
import com.dek.storedetail.item_question.QuestionAdapter
import com.dek.storedetail.item_question.QuestionData
import com.dek.storedetail.item_question_reply.QuestionReplyAdapter
import kotlinx.android.synthetic.main.fragment_item_question.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("ValidFragment")
class ItemQuestionFragment(var pid:Int) : Fragment(), View.OnClickListener {

    private var questionList : RecyclerView? = null
    private var question_adapter : QuestionAdapter? = null
    private var customLinear : CustomLinear? = null
    private var networkService : NetworkService? = null
    private var requestManager : RequestManager? = null
    private var productQuestionAdapter : QuestionAdapter? = null

    //private var bodyData : QuestionRegisterBody? = null

    private var questionContext : EditText? = null
    private var registerBtn : Button? = null
    private var secretBtn : Button? =null

    var qid : Int ?= null

    /*
    private var questionRelyList : RecyclerView? = null*/
    private var question_reply_item : QuestionReplyAdapter? = null

    @SuppressLint("WrongViewCast")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_item_question,container,false)
        networkService = ApplicationController.instance!!.networkService
        getproductquestion()


        //---------------------------RECYCLERVIEW Question---------------------------------//
        //recyclerview 생성
        customLinear = CustomLinear(context)
        questionList = v.findViewById<RecyclerView>(R.id.item_question_recyclerview)//main에서 만들엇던 RecyclerView 연결

        customLinear!!.orientation = LinearLayout.VERTICAL
        questionList!!.layoutManager = customLinear//recycler view의 배치 방식

        //data -> adapter
        question_adapter = QuestionAdapter(Common.questionDatas, context)
        question_adapter!!.setOnItemclickLister(this)

        //reyclerview 가 adapter를 사용하도록
        questionList!!.adapter = question_adapter


        registerBtn = v!!.findViewById(R.id.register_btn)
        registerBtn!!.setOnClickListener(this)

        questionContext = v.findViewById(R.id.QnA_register_edit)

        secretBtn = v.findViewById(R.id.question_secret_btn)


        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onClick(v: View?) {
        //val idx : Int = questionList!!.getChildAdapterPosition(v) //recyclerview에서 몇번째 인가
/*
        val image : String? = Common.questionDatas!!.get(idx).questionWriter //그 몇번째에서 name이 뭔가
        val type : String? = Common.questionDatas!!.get(idx).questionContext*/

        when(v){
            registerBtn ->{
                //등록버튼 클릭 시 서버에 문의 내용 전송 후 팝업창
                registerQuestion()
            }
        }
    }

    fun getproductquestion(){
        Log.v("que",(Common.myToken).toString())

        var getProductQuestionResponse = networkService!!.getProductQuestion(pid!!)

        getProductQuestionResponse.enqueue(object : Callback<GetQuestionProductResponse> {
            override fun onResponse(call: Call<GetQuestionProductResponse>?, response: Response<GetQuestionProductResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        //if(response!!.body().questionArr.size != 0){
                        if(response!!.body().questionArr != null) {
                            for (i in 0..response!!.body().questionArr.size - 1) {
                                Common.questionDatas.add(QuestionData(response!!.body().questionArr[i].show, response!!.body().questionArr[i].qid,
                                        response!!.body().questionArr[i].content, response!!.body().questionArr[i].username))
                            }
                        }
                        //}
                        productQuestionAdapter = QuestionAdapter(Common.questionDatas, context)
                        item_question_recyclerview!!.adapter = productQuestionAdapter
                       //ApplicationController.instance!!.makeToast("유저 문의사항 가져오기 성공")
                    }
                    else{
                       // ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    //ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetQuestionProductResponse>?, t: Throwable?) {
                //ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

    fun registerQuestion(){

        var secretText : String

        if(secretBtn!!.isPressed){
            secretText = "402"
        } else{
            secretText = "401"
        }

        var bodyData = QuestionRegisterBody(pid,  questionContext!!.text.toString(), secretText)

        var registerQuestion = networkService!!.registerQuestion((Common.myToken).toString(), bodyData!!)

        registerQuestion.enqueue(object  : Callback<QuestionRegisterData>{

            override fun onResponse(call: Call<QuestionRegisterData>?, response: Response<QuestionRegisterData>?) {
                Log.v("lee", "response 받음")
                if(response!!.isSuccessful){
                    Log.v("lee", "isSuccessful 받음")
                    if(response.body().status.equals("success")){
                        Log.v("lee", "success 들어옴")
                        ApplicationController.instance!!.makeToast("문의글이 등록되었습니다!")
                        questionContext!!.setText("")
                        getproductquestion()
                    }
                }
            }

            override fun onFailure(call: Call<QuestionRegisterData>?, t: Throwable?) {
                //ApplicationController.instance!!.makeToast("실패 띠용!")
            }
        })
    }
}




/*
class ItemQuestionFragment(var pid:Int) : Fragment(), View.OnClickListener {

    private var questionList : RecyclerView? = null
    private var question_adapter : QuestionAdapter? = null
    private var customLinear : CustomLinear? = null
    private var networkService : NetworkService? = null
    private var productQuestionAdapter : QuestionAdapter? = null

    var qid : Int ?= null

    private var question_reply_item : QuestionReplyAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_item_question,container,false)
        networkService = ApplicationController.instance!!.networkService
        Common.questionDatas.clear()
        getproductquestion()

        //위 함수의 argument -> xml을 fragment에서 사용할 수 있게 (= activity의 setconentview)

        //---------------------------RECYCLERVIEW Question---------------------------------//
        //recyclerview 생성
        customLinear = CustomLinear(context)
        questionList = v.findViewById<RecyclerView>(R.id.item_question_recyclerview)//main에서 만들엇던 RecyclerView 연결

        customLinear!!.orientation = LinearLayout.VERTICAL
        questionList!!.layoutManager = customLinear//recycler view의 배치 방식

        //data -> adapter
        question_adapter = QuestionAdapter(Common.questionDatas, context)
        question_adapter!!.setOnItemclickLister(this)

        //reyclerview 가 adapter를 사용하도록
        questionList!!.adapter = question_adapter

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onClick(v: View?) {
        val idx : Int = questionList!!.getChildAdapterPosition(v) //recyclerview에서 몇번째 인가
*/
/*
        val image : String? = Common.questionDatas!!.get(idx).questionWriter //그 몇번째에서 name이 뭔가
        val type : String? = Common.questionDatas!!.get(idx).questionContext*//*

    }

    fun getproductquestion(){
        Log.v("que",(Common.myToken).toString())

        var getProductQuestionResponse = networkService!!.getProductQuestion(pid!!)

        getProductQuestionResponse.enqueue(object : Callback<GetQuestionProductResponse> {
            override fun onResponse(call: Call<GetQuestionProductResponse>?, response: Response<GetQuestionProductResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        //if(response!!.body().questionArr.size != 0){
                        if(response!!.body().questionArr != null) {
                            for (i in 0..response!!.body().questionArr.size - 1) {
                                Common.questionDatas.add(QuestionData(response!!.body().questionArr[i].show, response!!.body().questionArr[i].qid,
                                        response!!.body().questionArr[i].content, response!!.body().questionArr[i].username))
                            }
                        }
                        //}
                        productQuestionAdapter = QuestionAdapter(Common.questionDatas, context)
                        item_question_recyclerview!!.adapter = productQuestionAdapter
                        ApplicationController.instance!!.makeToast("유저 문의사항 가져오기 성공")
                    }
                    else{
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetQuestionProductResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }



}*/
