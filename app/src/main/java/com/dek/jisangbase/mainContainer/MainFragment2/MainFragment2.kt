package com.dek.jisangbase.mainContainer.MainFragment2

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Card.CardAdapter
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetWholeBestData
import com.dek.jisangbase.GET.GetWholeBestResponse
import com.dek.jisangbase.GET.GetWholeCategoryData
import com.dek.jisangbase.GET.GetWholeCategoryResponse
import com.dek.jisangbase.ItemDetail.ItemDetailActivity
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.dek.jisangbase.Search.SearchActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment2 : Fragment(), View.OnClickListener {

    private var itemRecycle: RecyclerView? = null
    private var cardlist: ViewPager? = null
    private var itemAdapter: MainFragment2Adapter? = null

    private var gridManager: GridLayoutManager? = null
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null
    private var cardAdapter: CardAdapter? = null

    private var category: String? = null
    private var mylocation : String? = null

    private var searchButton : Button? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.main_fragment2, container, false)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        searchButton = v.findViewById(R.id.button2)

       searchButton!!.setOnClickListener{
            val intent = Intent(activity, SearchActivity::class.java)
            intent.putExtra("title","지상")
            startActivity(intent)
            activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }

        itemRecycle = v.findViewById(R.id.main2_recycler)
        gridManager = GridLayoutManager(context, 2)
        gridManager!!.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }

        }

        itemRecycle!!.layoutManager = gridManager
        //GridLayoutManager(context, )
        //GridLayoutManager()

        //-------------------  CardAdapter ViewPager   --------------------------//
        //viewPagerBestProduct = v.findViewById<View>(R.id.viewPager) as ViewPager

        initFragment(v)

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(v: View?) {

        //ApplicationController.instance!!.makeToast("클릭 성공")

    }


    fun initFragment(v: View) {
        Common.wholeCategoryList.clear()
        getwholebest()
//        childAdapter = MainFragment2ChildAdapter(Common.wholeCategoryList, requestManager!!)
//        itemRecycle!!.adapter = childAdapter
    }

    fun getwholebest() {
        var getWholeBestsResponse = networkService!!.getWholeBest()
        Log.v("hee", "여까지됨")
        getWholeBestsResponse.enqueue(object : Callback<GetWholeBestResponse> {
            override fun onResponse(call: Call<GetWholeBestResponse>?, response: Response<GetWholeBestResponse>?) {
                if (response!!.isSuccessful) {
                    if (response!!.body().status.equals("success")) {
                        for (i in 0..response!!.body().bestArr.size - 1) {
                            Common.wholeBestList.add(GetWholeBestData(response!!.body().bestArr[i].pid,
                                    response!!.body().bestArr[i].pimg, response!!.body().bestArr[i].location))
                        }
                        getwholecategory("301") //TODO 리사이클러 여기서
                        //ApplicationController.instance!!.makeToast("지역 베스트 상품 가져오기 성공")
                    } else {
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                } else {
                    ApplicationController.instance!!.makeToast("실패")
                }

            }

            override fun onFailure(call: Call<GetWholeBestResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

    fun getwholecategory(category: String) {
        var getWholeCategoryResponse = networkService!!.getWholeCategoryProduct(category)

        Log.v("hee", "여까지됨")//
        getWholeCategoryResponse.enqueue(object : Callback<GetWholeCategoryResponse>, View.OnClickListener {

            override fun onClick(v: View?) {
                val idx: Int = itemRecycle!!.getChildAdapterPosition(v)
                var pid: Int? = null
                Log.d("ash3734", (idx - 1).toString() + "idx in tab2")
                pid = Common.wholeCategoryList!!.get(idx - 1).pid
                Log.d("ash3734", pid.toString() + "pid in tab2")

                val intent = Intent(activity, ItemDetailActivity::class.java)
                intent.putExtra("pid", pid)
                startActivity(intent)

            }

            override fun onResponse(call: Call<GetWholeCategoryResponse>?, response: Response<GetWholeCategoryResponse>?) {
                if (response!!.isSuccessful) {
                    Log.d("ash3734", response.message())
                    if (response!!.body().status.equals("success")) {
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
                        itemAdapter = MainFragment2Adapter(Common.wholeCategoryList, requestManager!!)

                        itemAdapter!!.setOnItemClickListener(this)

                        itemRecycle!!.adapter = itemAdapter

                       // ApplicationController.instance!!.makeToast("전체 베스트 상품 가져오기 성공")
                        /*   else{
                               ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                           }*/
/*
                        newItem_adapter = MainFragment2ChildAdapter(Common.wholeCategoryList, requestManager!!)
                        //newItemList!!.setOnClickListener(this)

                        newItemList!!.adapter = newItem_adapter*/
                    }

                } else {
                    ApplicationController.instance!!.makeToast("실패")
                }

            }

            override fun onFailure(call: Call<GetWholeCategoryResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }
}


