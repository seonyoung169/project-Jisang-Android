package com.dek.jisangbase.mainContainer

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetBookMarkListData
import com.dek.jisangbase.GET.GetBookMarkListResponse
import com.dek.jisangbase.Like.LikeAdapter
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.dek.jisangbase.market.MarketActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment4 : Fragment() , View.OnClickListener {
    private var likeList: RecyclerView? = null
    private var likeadapter: LikeAdapter? = null
    private var requestManager : RequestManager? = null
    private var networkService : NetworkService? = null
    private var unloginedTextview : TextView? = null

    var deleteButton : Button? = null

    var deleteMid : Int ?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.main_fragment4, container, false)

        networkService = ApplicationController.instance!!.networkService

        deleteButton = v!!.findViewById(R.id.like_list_cancel)

        //unloginedTextview = v.findViewById<TextView>(R.id.unLogined_textview)

        likeList = v.findViewById<RecyclerView>(R.id.recycler_like)
        likeList!!.layoutManager = LinearLayoutManager(context)
        requestManager = Glide.with(context)



        return v


    }

    override fun onResume() {
        super.onResume()

        if(Common.myToken == null){

        }else{
            Common.bookMarkList.clear()
            getbookmark()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onClick(v: View?) {
        val idx : Int = likeList!!.getChildAdapterPosition(v) //recyclerview에서 몇번째 인가


        deleteMid = Common.bookMarkList[idx].mid

        var intent = Intent(context, MarketActivity::class.java)
        intent.putExtra("mid",deleteMid!!)
        startActivity(intent)

        Log.v("hogu",deleteMid.toString())



        //val image : String? = Common.arrayLike!!.get(idx).questionWriter //그 몇번째에서 name이 뭔가
        //val type : String? = Common.arrayLike!!.get(idx).questionContext
    }
    fun getbookmark(){

        var getbookmarklistResponse = networkService!!.getBookMarkList((Common.myToken).toString())
        getbookmarklistResponse.enqueue(object : Callback<GetBookMarkListResponse> {
            override fun onResponse(call: Call<GetBookMarkListResponse>?, response: Response<GetBookMarkListResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        var bookMarkLength = response!!.body().list.size
                        Log.v("length",bookMarkLength.toString())
                        for(i in 0..bookMarkLength-1){
                            Common.bookMarkList.add(GetBookMarkListData(response!!.body().list[i].mid,
                                    response!!.body().list[i].mimage,response!!.body().list[i].mname,response!!.body().list[i].location))

                            Log.v(i.toString(),response!!.body().list[i].mname)
                            Log.v(i.toString(),Common.bookMarkList[i].mname)
                        }

                        //이 코드 넣으면 터짐 ... 내생각엔 requestManager가 NULL 포인트여서 NULL 포인트 Exception 방생
                        likeadapter = LikeAdapter(Common.bookMarkList, requestManager!!)
                        likeadapter!!.setOnItemclickLister(this@MainFragment4)
                        likeList!!.adapter = likeadapter
                        likeadapter!!.notifyDataSetChanged()
                        //ApplicationController.instance!!.makeToast("북마크 리스트 가져오기 성공")

                    }
                    else{
                        Common.bookMarkList.clear()
                        likeadapter = LikeAdapter(Common.bookMarkList, requestManager!!)
                        likeadapter!!.setOnItemclickLister(this@MainFragment4)
                        likeList!!.adapter = likeadapter
                        likeadapter!!.notifyDataSetChanged()
                        //ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    //ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetBookMarkListResponse>?, t: Throwable?) {
               // ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }


}