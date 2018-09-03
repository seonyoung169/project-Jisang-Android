package com.dek.jisangbase.Like

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetBookMarkListData
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.POST.BookMarkUpdatePost
import com.dek.jisangbase.POST.BookMarkUpdateResponse
import com.dek.jisangbase.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikeAdapter(var likeDataList: ArrayList<GetBookMarkListData>, var requestManager: RequestManager) : RecyclerView.Adapter<LikeViewHolder>() {

    private var onItemClick : View.OnClickListener? = null
    private var networkService : NetworkService? = null
    var deleteMid : Int ?= null
    var myPosition : Int ?= null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LikeViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.main_fragment4_items,parent,false)

        networkService = ApplicationController.instance!!.networkService
        mainView.setOnClickListener(onItemClick)


        return LikeViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: LikeViewHolder?, position: Int) {
        //holder!!.marketImage!!.setImageResource(likeDataList!!.get(position).imgData)
        requestManager.load(likeDataList!!.get(position).mimage).into(holder!!.marketImage)
        holder!!.marketName!!.text = likeDataList!!.get(position).mname
        holder!!.marketLocation!!.text = likeDataList!!.get(position).location
        holder!!.cancleButton!!.setOnClickListener{
            myPosition = position
            deleteMid = likeDataList!!.get(position).mid
            updatebookmark(deleteMid!!)

        }

    }



    override fun getItemCount(): Int = likeDataList!!.size

    fun setOnItemclickLister(l: View.OnClickListener){
        onItemClick = l
    }

    fun updatebookmark(mid : Int){

        var updatebookmarkResponse = networkService!!.bookMarkUpdate((Common.myToken).toString(), BookMarkUpdatePost(mid))
        updatebookmarkResponse.enqueue(object : Callback<BookMarkUpdateResponse> {
            override fun onResponse(call: Call<BookMarkUpdateResponse>?, response: Response<BookMarkUpdateResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){

                        ApplicationController.instance!!.makeToast("북마크 해제")
                        likeDataList.removeAt(myPosition!!)
                        notifyDataSetChanged()

                    }
                    else{
                      //  ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                   // ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<BookMarkUpdateResponse>?, t: Throwable?) {
               // ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }


}

/*
class LikeAdapter(var likeDataList: ArrayList<GetBookMarkListData>, var requestManager: RequestManager) : RecyclerView.Adapter<LikeViewHolder>() {

    private var onItemClick : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LikeViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.main_fragment4_items,parent,false)

        return LikeViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: LikeViewHolder?, position: Int) {
        //holder!!.marketImage!!.setImageResource(likeDataList!!.get(position).imgData)
        requestManager.load(likeDataList!!.get(position).mimage).into(holder!!.marketImage)
        holder!!.marketName!!.text = likeDataList!!.get(position).mname
        holder!!.marketLocation!!.text = likeDataList!!.get(position).location
    }
    override fun getItemCount(): Int = likeDataList!!.size

    fun setOnItemclickLister(l: MainFragment4){
        onItemClick = l
    }

}*/
