package com.dek.storedetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetIlocationData
import com.dek.jisangbase.GET.GetProductDetailData
import com.dek.jisangbase.GET.GetProductDetailResponse
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.dek.jisangbase.item_info.ItemInfoAdapter
import com.dek.jisangbase.item_info.ItemInfoData
import com.dek.storedetail.item_question.CustomLinear
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemInfoFragment(var pid : Int) : Fragment() {

    private var itemList : RecyclerView? = null
    private var customLinear : CustomLinear? = null
    private var info_adapter : ItemInfoAdapter? = null
    private var requestManager : RequestManager? = null
    var networkService : NetworkService ?= null
    var mypid : Int? = null

    var itemNameTextView : TextView? = null
    var itemPriceTextView : TextView? = null
    var itemInfoTextView : TextView? = null

    var detailList2 : ArrayList<GetProductDetailData> ?= null
    var ilocationlist : ArrayList<GetIlocationData> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_item_info,container,false)
        requestManager = Glide.with(context)
        networkService = ApplicationController.instance!!.networkService

        mypid = pid

        Log.v("hello","1")
        customLinear = CustomLinear(context)
        itemList = v.findViewById<RecyclerView>(R.id.item_recycler)//main에서 만들엇던 RecyclerView 연결
        itemNameTextView = v.findViewById<TextView>(R.id.item_name_text)
        itemPriceTextView = v.findViewById<TextView>(R.id.item_price_text)
        itemInfoTextView = v.findViewById<TextView>(R.id.item_detail_text)

        customLinear!!.orientation = LinearLayout.VERTICAL
        itemList!!.layoutManager = customLinear//recycler view의 배치 방식

        Common.itemdetail.clear()
        getproductdetail()

        return v
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun getproductdetail(){
        Log.v("hodu",mypid.toString())
        var getProductDetailResponse = networkService!!.getProductDetail( mypid!!)
        //var getProductDetailResponse = networkService!!.getProductDetail((Common.myToken).toString(), 64)

        Log.v("hodu","여까지됨")
        getProductDetailResponse.enqueue(object : Callback<GetProductDetailResponse> {
            override fun onResponse(call: Call<GetProductDetailResponse>?, response: Response<GetProductDetailResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        Log.v("size",response!!.body().product.imageArr.size.toString())
                        if(response!!.body().product.imageArr != null){
                            for(i in 0..response!!.body().product.imageArr.size - 1){
                                ilocationlist!!.add(GetIlocationData(response!!.body().product.imageArr[i].ilocation))
                            }

                            Log.v("hodu2",ilocationlist[0].toString())

                            for (i in 0..response!!.body().product.imageArr.size - 1) {
                                Common.itemdetail!!.add(ItemInfoData(response!!.body().product.name, response!!.body().product.imageArr[i].ilocation,
                                        response!!.body().product.detail,response!!.body().product.mid) )
                            }
                            itemNameTextView!!.setText(response!!.body().product.name)
                            itemPriceTextView!!.setText(response!!.body().product.price)
                            itemInfoTextView!!.setText(response!!.body().product.detail)
                        }

                        info_adapter = ItemInfoAdapter(Common.itemdetail!!, requestManager!!)
                        itemList!!.adapter = info_adapter

                        //ApplicationController.instance!!.makeToast("상품 상세 정보 가져오기 성공")
                    }
                    else{
                        //ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    //ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetProductDetailResponse>?, t: Throwable?) {
                //ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }
}