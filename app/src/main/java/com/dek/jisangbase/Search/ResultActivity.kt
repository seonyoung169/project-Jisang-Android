package com.dek.jisangbase.Search

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetKeywordData
import com.dek.jisangbase.GET.GetKeywordResponse
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.tsengvn.typekit.TypekitContextWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultActivity  : AppCompatActivity() {

    private var resultList : RecyclerView? = null
    private var adapter : ResultAdapter? = null
    private var keyword : String? = null
    private var networkService : NetworkService? = null
    private var requestManager : RequestManager? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        networkService = ApplicationController.instance!!.networkService
        keyword = getIntent().getStringExtra("keyword") //TODO 받아왔다 INTENT로!!!

        Log.v("keyword",keyword)

        resultList = findViewById(R.id.search_result_recyclerview) as RecyclerView

        val manager = GridLayoutManager(this, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }
        }
        resultList!!.layoutManager = manager
        requestManager = Glide.with(this)
        getcategoryproduct()
    }
    fun getcategoryproduct(){
        Log.v("dek0112","start")
        var getCategoryProductResponse = networkService!!.getKeyword(keyword!!)
        Log.v("dek0112","여까지됨")
        getCategoryProductResponse.enqueue(object : Callback<GetKeywordResponse>{

            override fun onResponse(call: Call<GetKeywordResponse>?, response: Response<GetKeywordResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        Log.v("dek0112","success")
                        if(response!!.body().productArr != null) {

                            Log.v("dek0112", response!!.body().productArr.size.toString())
                            for (i in 0..response!!.body().productArr.size - 1) {

                                Common.keywordData.add(GetKeywordData(response!!.body().productArr[i].productid,
                                        response!!.body().productArr[i].name, response!!.body().productArr[i].price,
                                        response!!.body().productArr[i].marketname,response!!.body().productArr[i].productimg))

                                Log.v("dek0112", response!!.body().productArr[i].name)
                            }
                        }
                        adapter = ResultAdapter(applicationContext, requestManager!!, Common.keywordData)

                        resultList!!.adapter = adapter

                        //ApplicationController.instance!!.makeToast("키워드 상품 가져오기 성공")
                    }
                    else{
                        //ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetKeywordResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
        return
    }
}
