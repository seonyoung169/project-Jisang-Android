package com.dek.jisangbase.mainContainer.MainFragment2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Card.CardAdapter
import com.dek.jisangbase.Card.CardTransformer
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetWholeCategoryData
import com.dek.jisangbase.GET.GetWholeCategoryResponse
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by 김진석 on 2018-01-10.
 */
class MainFragment2Adapter (var dataList: ArrayList<GetWholeCategoryData>, var requestManager: RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER_POSITION : Int = 0
    private val TYPE_HEADER = 1
    private val TYPE_ITEM = 2
    private val TYPE_EMPTY = 3
    private var context : Context? = null
    private var select : Boolean = false
    private var cardAdapter: CardAdapter? = null
    private var cardTransformer: CardTransformer? = null
    private var networkService : NetworkService?=null


    private var onItemClick : View.OnClickListener? = null

    var mylocation : String ?= null

    fun setContext(context: Context) {
        this.context = context
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(holder is MainFragment2HeaderViewHolder){


            var headViewHolder = holder as MainFragment2HeaderViewHolder
            headViewHolder.viewPager
            cardAdapter = CardAdapter(Common.wholeBestList, requestManager!!)
            Log.v("ash3734",Common.wholeBestList.size.toString())

            for(i in 0..(Common.wholeBestList!!.size-1)){
                cardAdapter!!.addCardItem(Common.wholeBestList!![i])
            }
            Log.v("ash3734",Common.wholeBestList.size.toString())
            cardTransformer = CardTransformer(headViewHolder.viewPager, cardAdapter)
            cardTransformer!!.enableScaling(true)

            holder.viewPager!!.adapter = cardAdapter

            //todo 여기 다세요
            holder.all!!.setOnClickListener {
                holder.all!!.isSelected = true
                holder.outer!!.isSelected = false
                holder.top!!.isSelected = false
                holder.pants!!.isSelected = false
                holder.skirt!!.isSelected = false
                holder.onepiece!!.isSelected = false
                holder.shoes!!.isSelected = false
                holder.bag!!.isSelected = false
                holder.acc!!.isSelected = false

                Common.wholeCategoryList.clear()
                getwholecategory("301")
            }
            holder.outer!!.setOnClickListener {
                holder.all!!.isSelected = false
                holder.outer!!.isSelected = true
                holder.top!!.isSelected = false
                holder.pants!!.isSelected = false
                holder.skirt!!.isSelected = false
                holder.onepiece!!.isSelected = false
                holder.shoes!!.isSelected = false
                holder.bag!!.isSelected = false
                holder.acc!!.isSelected = false

                Common.wholeCategoryList.clear()
                getwholecategory("302")
            }
            holder.top!!.setOnClickListener {
                holder.all!!.isSelected =false
                holder.outer!!.isSelected = false
                holder.top!!.isSelected = true
                holder.pants!!.isSelected = false
                holder.skirt!!.isSelected = false
                holder.onepiece!!.isSelected = false
                holder.shoes!!.isSelected = false
                holder.bag!!.isSelected = false
                holder.acc!!.isSelected = false

                Common.wholeCategoryList.clear()
                getwholecategory("303")
            }
            holder.pants!!.setOnClickListener {
                holder.all!!.isSelected = false
                holder.outer!!.isSelected = false
                holder.top!!.isSelected = false
                holder.pants!!.isSelected = true
                holder.skirt!!.isSelected = false
                holder.onepiece!!.isSelected = false
                holder.shoes!!.isSelected = false
                holder.bag!!.isSelected = false
                holder.acc!!.isSelected = false

                Common.wholeCategoryList.clear()
                getwholecategory("304")
            }
            holder.skirt!!.setOnClickListener {
                holder.all!!.isSelected = false
                holder.outer!!.isSelected = false
                holder.top!!.isSelected = false
                holder.pants!!.isSelected = false
                holder.skirt!!.isSelected = true
                holder.onepiece!!.isSelected = false
                holder.shoes!!.isSelected = false
                holder.bag!!.isSelected = false
                holder.acc!!.isSelected = false

                Common.wholeCategoryList.clear()
                getwholecategory("305")
            }
            holder.onepiece!!.setOnClickListener {
                holder.all!!.isSelected = false
                holder.outer!!.isSelected = false
                holder.top!!.isSelected = false
                holder.pants!!.isSelected = false
                holder.skirt!!.isSelected = false
                holder.onepiece!!.isSelected = true
                holder.shoes!!.isSelected = false
                holder.bag!!.isSelected = false
                holder.acc!!.isSelected = false

                Common.wholeCategoryList.clear()
                getwholecategory("306")
            }
            holder.shoes!!.setOnClickListener {
                holder.all!!.isSelected = false
                holder.outer!!.isSelected = false
                holder.top!!.isSelected = false
                holder.pants!!.isSelected = false
                holder.skirt!!.isSelected = false
                holder.onepiece!!.isSelected = false
                holder.shoes!!.isSelected = true
                holder.bag!!.isSelected = false
                holder.acc!!.isSelected = false

                Common.wholeCategoryList.clear()
                getwholecategory("307")
            }
            holder.bag!!.setOnClickListener {
                holder.all!!.isSelected = false
                holder.outer!!.isSelected = false
                holder.top!!.isSelected = false
                holder.pants!!.isSelected = false
                holder.skirt!!.isSelected = false
                holder.onepiece!!.isSelected = false
                holder.shoes!!.isSelected = false
                holder.bag!!.isSelected = true
                holder.acc!!.isSelected = false

                Common.wholeCategoryList.clear()
                getwholecategory("308")
            }
            holder.acc!!.setOnClickListener {
                holder.all!!.isSelected = false
                holder.outer!!.isSelected = false
                holder.top!!.isSelected = false
                holder.pants!!.isSelected = false
                holder.skirt!!.isSelected = false
                holder.onepiece!!.isSelected = false
                holder.shoes!!.isSelected = false
                holder.bag!!.isSelected = false
                holder.acc!!.isSelected = true

                Common.wholeCategoryList.clear()
                getwholecategory("309")
            }
            //todo 나중에 노가다
        } else{

            var itemViewHolder = holder as MainFragment2ChildViewHolder

            requestManager.load(dataList!!.get(position-1).image).into(itemViewHolder!!.newItemImage)

            itemViewHolder!!.newItemLoc!!.text =  dataList!!.get(position-1).location

            itemViewHolder.newItemName!!.text = dataList!!.get(position-1).name

            itemViewHolder.newItemPrice!!.text = dataList!!.get(position-1).price


/*
            var currentStoreName = dataList!!.get(position-1).storeName
            itemViewHolder.newItemStore!!.setText(currentStoreName)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        networkService = ApplicationController.instance!!.networkService
        if (viewType == TYPE_HEADER) {
            val headView : View = LayoutInflater.from(parent!!.context)
                    .inflate(R.layout.main_fragment2_header,parent,false)
            return MainFragment2HeaderViewHolder(headView)
        } else{
            val bodyView : View = LayoutInflater.from(parent!!.context)
                    .inflate(R.layout.main_fragment2_child_item,parent,false)
            bodyView.setOnClickListener(onItemClick)
            return MainFragment2ChildViewHolder(bodyView)
        }
    }

    override fun getItemCount() : Int {
        return (if (dataList != null) dataList!!.size + 1 else 0)
    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0)
            return TYPE_HEADER
        else
            return TYPE_ITEM
    }

    fun setOnItemClickListener(l:View.OnClickListener){
        onItemClick = l
    }


    fun getwholecategory(category : String){
        var getWholeCategoryResponse = networkService!!.getWholeCategoryProduct(category)
        Log.v("hee","여까지됨")
        getWholeCategoryResponse.enqueue(object : Callback<GetWholeCategoryResponse> {
            override fun onResponse(call: Call<GetWholeCategoryResponse>?, response: Response<GetWholeCategoryResponse>?) {
                if(response!!.isSuccessful){
                    Log.d("ash3734",response.message())
                    if (response!!.body().status.equals("success")) {
                        dataList.clear()
                        if (response!!.body().productArr != null){
                            for (i in 0..response!!.body().productArr.size - 1) {

                                if((response!!.body().productArr[i].location).equals("201")) mylocation = "강남"
                                else if((response!!.body().productArr[i].location).equals("202")) mylocation = "고속터미널"
                                else if((response!!.body().productArr[i].location).equals("203")) mylocation = "부평"
                                else mylocation = "알수없음"

                                Common.wholeCategoryList.add(GetWholeCategoryData(response!!.body().productArr[i].pid,
                                        response!!.body().productArr[i].name, response!!.body().productArr[i].price,
                                        response!!.body().productArr[i].image, mylocation!!))

                                Log.v("check", response!!.body().productArr[i].name)
                            }
                    }
                        dataList = Common.wholeCategoryList
                        Log.d("ash3734",dataList.size.toString()+" 데이타 사이즈")
                        notifyDataSetChanged()
                        ApplicationController.instance!!.makeToast("전체 베스트 상품 가져오기 성공")
                        /*   else{
                                   ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                               }*/
/*
                        newItem_adapter = MainFragment2ChildAdapter(Common.wholeCategoryList, requestManager!!)
                        //newItemList!!.setOnClickListener(this)

                        newItemList!!.adapter = newItem_adapter*/
                    }

                }else{
                    ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetWholeCategoryResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }
}