package com.dek.jisangbase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by SY Lee on 2018-01-02.
 */
class ActivityRegisterdItem : AppCompatActivity(), View.OnClickListener {
    private var networkService : NetworkService? = null
    private var requestManager : RequestManager? = null

    private var itemList: RecyclerView? = null
    private var itemdata: ArrayList<ProductArr>? = null
    private var adapter: ItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registered_item)

        networkService = ApplicationController.instance!!.networkService

        requestManager = Glide.with(this)

        itemList = findViewById(R.id.item_recyclerview) as RecyclerView
        Common.bottomBar = findViewById(R.id.bottom_bar) as LinearLayout

        val manager = GridLayoutManager(this, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }
        }

        itemList!!.layoutManager = manager


        var getRegisteredListFrom = networkService!!.registeredList(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MzksImFkbWluIjoiMTAyIiwibWlkIjoyMywiaWF0IjoxNTE1NjA5ODQ3LCJleHAiOjE1MTYyMTQ2NDd9.uYGd3a7bu8KD7C5StshcVA2cBW6eT4MKIuLU2YGmkqE"
                ,"2018 년 01 월 11 일")

        getRegisteredListFrom.enqueue(object  : Callback<RegisteredListFromData> {
            override fun onResponse(call: Call<RegisteredListFromData>?, response: Response<RegisteredListFromData>?) {
                Log.d("lsy", "여기? 1")
                if(response!!.isSuccessful) {
                    Log.d("lsy", "여기? 2")
                    if(response.body().status.equals("success")){
                        Log.d("lsy", "여기? 3")
                        ApplicationController.instance!!.makeToast("날짜별 리스트 받기 성공")
                        Log.d("lsy", response.body().productArr.size.toString())
                        itemdata = response.body().productArr
                        Log.d("lsy", itemdata!!.size.toString())

                        adapter = ItemAdapter(itemdata,requestManager)
                        itemList!!.adapter = adapter
                    }
                }
                else{
                    Log.d("lsy", response!!.message())
                }
            }

            override fun onFailure(call: Call<RegisteredListFromData>?, t: Throwable?) {
               // ApplicationController.instance!!.makeToast("실패 띠용!")
            }
        })
    }


    override fun onClick(v: View?) {

    }


}