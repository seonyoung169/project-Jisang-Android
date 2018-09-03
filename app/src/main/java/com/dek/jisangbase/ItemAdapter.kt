package com.dek.jisangbase

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by SY Lee on 2018-01-02.
 */

class ItemAdapter(var dataList: ArrayList<ProductArr>?,
                  var requestmanager : RequestManager? )
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var networkService : NetworkService? = null

    private val HEADER_POSITION : Int = 0
    private val TYPE_HEADER = 1
    private val TYPE_ITEM = 2
    private var select : Boolean = false
    private var checkedList : ArrayList<Int> = ArrayList()
    private var productArr : ProductArrList = ProductArrList(checkedList)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if (holder is HeaderViewHolder) {
            holder.garbage.setOnClickListener{
                //var itemViewHolder = holder as ItemViewHolder
                Log.d("lsy","휴지통 클릭 ㅇ")

                //itemViewHolder.checkBox!!.visibility = View.GONE
                select = !select
                notifyDataSetChanged()
            }
        }
        else {
            var itemViewHolder = holder as ItemViewHolder

            requestmanager!!.load(dataList!!.get(position-1).pimg).into(itemViewHolder.itemImage)

            if(select){
                Common.bottomBar!!.visibility = View.VISIBLE
                itemViewHolder.checkBox.visibility = View.VISIBLE

                var buttonCancel = Common.bottomBar!!.findViewById<Button>(R.id.cancel_button)
                buttonCancel.setOnClickListener {
                    select = !select
                    notifyDataSetChanged()
                }


                //체크박스 체크 되어있으면 삭제 전송 보낼 배열에서 빼기
                if(itemViewHolder.checkBox.isChecked){
                    itemViewHolder.checkBox.setOnClickListener {
                        checkedList!!.remove(dataList!![position-1].pid)
                        notifyDataSetChanged()
                        Log.v("lsy", dataList!![position-1].pid.toString())
                    }

                }

                //체크박스 체크 안되어 있으면 삭제 전송 보낼 배열에 추가
                else{
                    itemViewHolder.checkBox.setOnClickListener {
                        checkedList!!.add(dataList!![position - 1].pid)
                        notifyDataSetChanged()
                        Log.v("lsy", dataList!![position - 1].pid.toString())
                    }
                }


                //-------------------------삭제 처리-------------------------
                var buttonDelete = Common.bottomBar!!.findViewById<Button>(R.id.delete_button)
                buttonDelete.setOnClickListener {
                    Log.v("lsy","삭제 버튼 클릭")
                    Log.v("lsy", checkedList!!.size.toString()+"size??")

                    Log.v("lsy","삭제할 이미지 아이디 출력 ")
                    for(i in 0..checkedList!!.size-1){
                        Log.v("lsy", checkedList!![i].toString())
                    }


                    //삭제할 이미지 아이디 보낸다
                    networkService = ApplicationController.instance!!.networkService
                    /* var productArrList : ProductArrList = ProductArrList()
                     productArrList.productArr = checkedList*/
                    productArr!!.productArr = checkedList


                    var registeredDeletefun = networkService!!.registeredDelete(
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MzksImFkbWluIjoiMTAyIiwibWlkIjoyMywiaWF0IjoxNTE1NjA5ODQ3LCJleHAiOjE1MTYyMTQ2NDd9.uYGd3a7bu8KD7C5StshcVA2cBW6eT4MKIuLU2YGmkqE",
                            productArr!!
                    )
                    registeredDeletefun.enqueue(object : Callback<ItemDeleteData>{
                        override fun onResponse(call: Call<ItemDeleteData>?, response: Response<ItemDeleteData>?) {
                            Log.d("lsy", "여기? 1")
                            if(response!!.isSuccessful) {
                                Log.d("lsy", "여기? 2")
                                if(response.body().status.equals("success")){
                                    Log.d("lsy", "여기? 3")

                                    // ActivityRegistered 재시작

                                    //deleteArr = int 형 배열 (이미지 pid 들어가 있음)
                                    var deleteArr : ArrayList<Int> = productArr.productArr

                                    for(i in 0..deleteArr.size-1){
                                        if (dataList!![i].pid == deleteArr!![i]){
                                            dataList!!.removeAt(i)
                                        }
                                    }
                                    notifyDataSetChanged()

                                }
                            }
                            else{
                                Log.d("lsy", response!!.message())
                            }
                        }

                        override fun onFailure(call: Call<ItemDeleteData>?, t: Throwable?) {
                            //ApplicationController.instance!!.makeToast("실패 띠용!")
                        }

                    })
                }
            }
            else {
                Common.bottomBar!!.visibility = View.GONE
                itemViewHolder.checkBox.visibility = View.GONE
            }

            //item 체크박스 클릭 시 그 이미지 position 받아서 저장 후 서버에 넘기기
            //ItemViewHolder.checkBox.setOnClickListener {
            //}

        }
    }

    override fun getItemCount(): Int {
        return (if (dataList != null) dataList!!.size + 1 else 0)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val v = LayoutInflater.from(parent!!.context).inflate(R.layout.registered_header, parent, false)
            return HeaderViewHolder(v)
        } else{
            val v = LayoutInflater.from(parent!!.context).inflate(R.layout.registered_items, parent, false)
            //v.setOnClickListener(onClickListener)
            return ItemViewHolder(v)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == HEADER_POSITION) {
            return TYPE_HEADER
        }
        else {
            return TYPE_ITEM
        }
    }
}