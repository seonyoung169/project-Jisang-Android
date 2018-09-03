package com.dek.jisangbase.ItemDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetProductDetailResponse
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.dek.jisangbase.market.MarketActivity
import com.dek.jisangbase.storedetail.ItemQuestionFragment
import com.dek.storedetail.ItemInfoFragment
import com.dek.storedetail.item_detail_fragment.ImageAdapter
import com.dek.storedetail.item_question_reply.QuestionReplyAdapter
import com.dek.storedetail.item_question_reply.QuestionReplyData
import com.tsengvn.typekit.TypekitContextWrapper
import kotlinx.android.synthetic.main.activity_item_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDetailActivity : AppCompatActivity(), View.OnClickListener {

    //private var itemRecycle: ViewPager? = null
    private var questionRelyList : RecyclerView? = null
    private var questionReplyDatas : ArrayList<QuestionReplyData>? = null
    private var question_reply_adapter : QuestionReplyAdapter? = null
    private var networkService : NetworkService? = null
    private var itemAdapter : ImageAdapter? = null
    private var requestManager: RequestManager? = null
    var detailList = ArrayList<String>()
    var pid : Int? = null
    var gogoButton : Button? = null
    var mymid : Int? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        pid = intent.getIntExtra("pid",0)
        Log.d("ash3734", pid.toString() + "pid in detail")

        getproductdetail()

        gogoButton = findViewById(R.id.gogogo) as Button

        gogoButton!!.setOnClickListener(View.OnClickListener {
            Log.v("oreo","im clicked")
            var intent = Intent(applicationContext, MarketActivity::class.java)
            intent.putExtra("mid",mymid!!)
            startActivity(intent)
        })

        if(savedInstanceState == null){
            val bundle =  Bundle()
            AddFragment(ItemInfoFragment(pid!!),bundle,"first") //가장 처음 프래그먼트를 붙임 (first는 tag)
        }

        //----------------------------VIEWPAGER------------------------------------//
        /* 서버에서 이렇게 받아오면 되요!
        for(i in 0 ... (size-1)){
            Common.imageList.image(이미지[i])
        }*/

        //var tabAdapter = ImageAdapter(supportFragmentManager,response!!.body().product.imageArr.size,detailList, requestManager!!)

        Log.v("size", Common.imageList.size.toString())
        detail_viewpager.clipToPadding = false

        //detail_viewpager.adapter = tabAdapter

        detail_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(main2_tab))

        main2_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                detail_viewpager.currentItem = tab!!.position
            }
        })


        item_info_btn.setOnClickListener(this)
        item_question_btn.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0){
            item_info_btn->{

                val bundle = Bundle()
                ReplaceFragment(ItemInfoFragment(pid!!), bundle, "info")
            }
            item_question_btn->{
                val bundle = Bundle()
                ReplaceFragment(ItemQuestionFragment(pid!!), bundle, "question")
            }
        }
    }

    //붙이기만 하고 끝낼 함수 , 지우지 않음
    fun AddFragment(fragment : Fragment, bundle : Bundle, tag : String){

        val fm = supportFragmentManager //fragment 종합적관리
        val transaction = fm.beginTransaction() //삽입,삭제 ..
        fragment.arguments = bundle
        transaction.add(R.id.question_container,fragment,tag) //main에서 정의한 프레임 레이아웃에 프래그먼트를 올린다.
        transaction.commit()
    }
    fun ReplaceFragment(fragment : Fragment, bundle : Bundle, tag : String){
        val fm = supportFragmentManager //fragment 종합적관리
        val transaction = fm.beginTransaction() //삽입,삭제 ..
        fragment.arguments = bundle

        transaction.replace(R.id.question_container,fragment,tag) //replace할 때 fragment에 tag가 붙음
        //transaction.addToBackStack(null) //fragment를 쌓아 가기 때문에 뒤로가기를 하면 stack에 있는 fragment가 pop된다.
        transaction.commit()
    }

    fun getproductdetail(){
        var getProductDetailResponse = networkService!!.getProductDetail(( pid!!))
        //var getProductDetailResponse = networkService!!.getProductDetail((Common.myToken).toString(), 64)


        getProductDetailResponse.enqueue(object : Callback<GetProductDetailResponse> {
            override fun onResponse(call: Call<GetProductDetailResponse>?, response: Response<GetProductDetailResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        for (i in 0..response!!.body().product.imageArr.size - 1) {
                            detailList.add(response!!.body().product.imageArr[i].ilocation)
                            Log.v("check", response!!.body().product.imageArr[i].ilocation)
                        }
                        mymid = response!!.body().product.mid
                        itemAdapter = ImageAdapter(supportFragmentManager,response!!.body().product.imageArr.size,detailList, requestManager!!)
                        detail_viewpager!!.adapter = itemAdapter

                        //ApplicationController.instance!!.makeToast("상품 상세 가져오기 성공")
                    }
                    else{
                       // ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    //ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetProductDetailResponse>?, t: Throwable?) {
               // ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

}
