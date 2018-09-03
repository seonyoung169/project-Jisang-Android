package com.dek.jisangbase.LocationProductList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetCategoryProductData
import com.dek.jisangbase.GET.GetCategoryProductResponse
import com.dek.jisangbase.ItemDetail.ItemDetailActivity
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.tsengvn.typekit.TypekitContextWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProductListActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: ProductListAdapter? = null
    private var requestManager: RequestManager? = null
    private var productListItems: ArrayList<ProductListItem>? = null
    private var networkService : NetworkService? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        networkService = ApplicationController.instance!!.networkService


        recyclerView = findViewById(R.id.apl_recyclerview) as RecyclerView
        recyclerView!!.setHasFixedSize(false)

        requestManager = Glide.with(this)

        val manager = GridLayoutManager(this, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }
        }
        recyclerView!!.layoutManager = manager
        getcategoryproduct()
    }

    fun getcategoryproduct(){
        Log.v("dek0112","start")
        var getCategoryProductResponse = networkService!!.getCategoryProduct("301","201")
        Log.v("dek0112","여까지됨")
        getCategoryProductResponse.enqueue(object : Callback<GetCategoryProductResponse>, View.OnClickListener {

            override fun onClick(v: View?) {
                val idx: Int = recyclerView!!.getChildAdapterPosition(v)
                var pid: Int? = null
                Log.d("ash3734", (idx - 1).toString() + "idx in tab2")
                pid = Common.specificCategoryList!!.get(idx - 1).pid
                Log.d("ash3734", pid.toString() + "pid in tab2")

                val intent = Intent(applicationContext, ItemDetailActivity::class.java)
                intent.putExtra("pid", pid)
                startActivity(intent)

            }

            override fun onResponse(call: Call<GetCategoryProductResponse>?, response: Response<GetCategoryProductResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        Log.v("dek0112","success")
                        if(response!!.body().productArr != null) {

                            Log.v("dek0112", response!!.body().productArr.size.toString())
                            for (i in 0..response!!.body().productArr.size - 1) {

                                Common.specificCategoryList.add(GetCategoryProductData(response!!.body().productArr[i].pid,
                                        response!!.body().productArr[i].name, response!!.body().productArr[i].price,
                                        response!!.body().productArr[i].image, response!!.body().productArr[i].mname))

                                Log.v("dek0112", response!!.body().productArr[i].name)
                            }
                        }
                        adapter = ProductListAdapter(applicationContext, requestManager!!, Common.specificCategoryList)

                        adapter!!.setOnItemOnClickListener(this)

                        recyclerView!!.adapter = adapter

                       // ApplicationController.instance!!.makeToast("카테고리 지역 별 상품 가져오기 성공")
                    }
                    else{
                    //    ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                  //  ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetCategoryProductResponse>?, t: Throwable?) {
               // ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
        return
    }
}