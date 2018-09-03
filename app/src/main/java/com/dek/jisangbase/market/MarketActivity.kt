package com.dek.jisangbase.market

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.*
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.POST.BookMarkUpdatePost
import com.dek.jisangbase.POST.BookMarkUpdateResponse
import com.dek.jisangbase.R
import com.tsengvn.typekit.TypekitContextWrapper
import kotlinx.android.synthetic.main.market_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by 김진석 on 2018-01-07.
 */
class MarketActivity : AppCompatActivity(), View.OnClickListener {

    private var backBtn : Button? = null
    private var likeBtn : Button? = null
    private var itemList : RecyclerView? = null
    private var adapter : MarketAdapter? = null
    var isBookMark : Boolean ?= null

    private var networkService : NetworkService? = null
    private var bestProduct = ArrayList<GetMarketBestData>()
    private var normalProduct = ArrayList<GetMarketProductData>()
    //normal

    private var requestManager : RequestManager? = null


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.market_fragment)

        networkService = ApplicationController.instance!!.networkService

        likeBtn = findViewById(R.id.market_like_button) as Button?

        itemList = findViewById(R.id.marketmain_recyclerview) as RecyclerView?

        requestManager = Glide.with(this)

        val manager = GridLayoutManager(this, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }
        }

        getmarketinfobymid()
        getisbookmark()

        itemList!!.layoutManager = manager


        Common.itemdata!!.add(MarketItemData(R.drawable.item1, "item1"))
        Common.itemdata!!.add(MarketItemData(R.drawable.item2, "item2"))
        Common.itemdata!!.add(MarketItemData(R.drawable.item1, "item3"))
        Common.itemdata!!.add(MarketItemData(R.drawable.item2, "item4"))
        Common.itemdata!!.add(MarketItemData(R.drawable.item1, "item5"))
        Common.itemdata!!.add(MarketItemData(R.drawable.item2, "iem6"))





        likeBtn!!.setOnClickListener(this)

        getmarketbest()


    }

    override fun onClick(p0: View?) {
        //val idx : Int = itemList!!.getChildAdapterPosition(p0)

        when(p0){
            likeBtn->{
                if(isBookMark == false){ // 북마크에 등록이 안되어있었다면
                    isBookMark = true
                    updatebookmark()
                    likeBtn!!.setBackgroundResource(R.drawable.store_like_click)
                }else if(isBookMark == true){ // 이미 북마크에 등록이 되있었다면
                    isBookMark = false
                    updatebookmark()
                    likeBtn!!.setBackgroundResource(R.drawable.store_like)
                }
            }
        }
    }

    fun getmarketinfobymid(){
        val marketinfobymidResponse = networkService!!.getMarketByMid(getIntent().getIntExtra("mid",0))
        marketinfobymidResponse.enqueue(object : Callback<GetMarketInfoByMidResponse> {
            override fun onResponse(call: Call<GetMarketInfoByMidResponse>?, response: Response<GetMarketInfoByMidResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        market_title_name.setText(response!!.body().market.name)
                        //ApplicationController.instance!!.makeToast("마켓 정보 가져오기 성공")

                    }
                    else{
                        ///ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    //ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetMarketInfoByMidResponse>?, t: Throwable?) {
                //ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

    fun getmarketbest(){
        val marketBestResponse = networkService!!.getMarketBest(getIntent().getIntExtra("mid",0))
        marketBestResponse.enqueue(object : Callback<GetMarketBestResponse> {
            override fun onResponse(call: Call<GetMarketBestResponse>?, response: Response<GetMarketBestResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        if(response!!.body().bestArr != null){
                            for(i in 0..response!!.body().bestArr.size-1){
                                bestProduct.add(GetMarketBestData(response!!.body().bestArr[i].pid,response!!.body().bestArr[i].pimg))
                            }
                        }

                        //ApplicationController.instance!!.makeToast("마켓 베스트 가져오기 성공")
                        getmarketproduct()
                    }
                    else{
                        //ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    //ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetMarketBestResponse>?, t: Throwable?) {
                //ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

    fun getmarketproduct(){
        val getmarketproductResponse = networkService!!.getMarketProduct(getIntent().getIntExtra("mid",0))
        getmarketproductResponse.enqueue(object : Callback<GetMarketProductResponse> {
            override fun onResponse(call: Call<GetMarketProductResponse>?, response: Response<GetMarketProductResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){

                        if(response!!.body().productArr != null){
                            for(i in 0..response!!.body().productArr.size-1){
                                normalProduct.add(GetMarketProductData(response!!.body().productArr[i].pid,
                                        response!!.body().productArr[i].name,response!!.body().productArr[i].price,response!!.body().productArr[i].image))
                            }

                            Log.v("hogugu",response!!.body().productArr[0].image)
                            Log.v("hogugu",response!!.body().productArr[1].image)

                        }


                        //ApplicationController.instance!!.makeToast("마켓 아이템 가져오기 성공")

                        //리사이클러뷰
                        adapter = MarketAdapter(bestProduct!!, normalProduct!!, requestManager!!)


                        itemList!!.adapter = adapter


                    }
                    else{
                        //ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    //ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetMarketProductResponse>?, t: Throwable?) {
                //ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

    fun getisbookmark(){
        val getisbookmarkResponse = networkService!!.getIsBookMark(Common.myToken.toString(),getIntent().getIntExtra("mid",0))
        getisbookmarkResponse.enqueue(object : Callback<GetIsBookMarkResponse> {
            override fun onResponse(call: Call<GetIsBookMarkResponse>?, response: Response<GetIsBookMarkResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){

                        if(response!!.body().bookmarked.equals("0")){
                            isBookMark = false
                            likeBtn!!.setBackgroundResource(R.drawable.store_like)
                        }else if(response!!.body().bookmarked.equals("1")){
                            isBookMark = true
                            likeBtn!!.setBackgroundResource(R.drawable.store_like_click)
                        }


                        //리사이클러뷰
                        adapter = MarketAdapter(bestProduct!!, normalProduct!!, requestManager!!)


                        itemList!!.adapter = adapter


                    }
                    else{
                        //ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    //ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetIsBookMarkResponse>?, t: Throwable?) {
               // ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

    fun updatebookmark(){
        val updatebookmarkPostResponse = networkService!!.bookMarkUpdate(Common.myToken.toString(), BookMarkUpdatePost( getIntent().getIntExtra("mid",0)))
        updatebookmarkPostResponse.enqueue(object : Callback<BookMarkUpdateResponse> {
            override fun onResponse(call: Call<BookMarkUpdateResponse>?, response: Response<BookMarkUpdateResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){

                        //ApplicationController.instance!!.makeToast("북마크 업데이트 성공")

                    }else{
                        //ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }
                else{
                    //ApplicationController.instance!!.makeToast("??")
                }
            }
            override fun onFailure(call: Call<BookMarkUpdateResponse>?, t: Throwable?) {
                //ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

}