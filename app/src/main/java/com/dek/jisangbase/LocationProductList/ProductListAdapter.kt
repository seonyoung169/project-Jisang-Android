package com.dek.jisangbase.LocationProductList
import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetCategoryProductData
import com.dek.jisangbase.GET.GetCategoryProductResponse
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by ksj on 2018. 1. 11..
 */

class ProductListAdapter(private var mcontext: Context, private val requestManager: RequestManager, private var productListItems: ArrayList<GetCategoryProductData>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
    private var productBestItems: ArrayList<Int>? = null
    private var networkService : NetworkService? = null
    private val productListAdpater: ProductListAdapter? = null
    var category : String ?= null
    var activity : Activity?= null



    private var onItemClick : View.OnClickListener? = null


    fun setOnItemOnClickListener(l:View.OnClickListener){
        onItemClick = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        networkService = ApplicationController.instance!!.networkService


        if (viewType == TYPE_HEADER) {
            //헤더인 경우
            val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_auto_scroll, parent, false)
            return HeaderViewHolder(v)
        } else if (viewType == TYPE_ITEM) {
            //아이템인
            val v = LayoutInflater.from(parent.context).inflate(R.layout.items_recycler_row, parent, false)
            v.setOnClickListener(onItemClick)
            return ViewHolder(v)
        }
        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            //뷰타입이 헤더 생성하기 버튼일 경우

            productBestItems = ArrayList()
            productBestItems!!.add(R.drawable.banner1)
            productBestItems!!.add(R.drawable.banner2)
            productBestItems!!.add(R.drawable.banner3)
            productBestItems!!.add(R.drawable.banner4)
            productBestItems!!.add(R.drawable.banner5)

            //TODO autoScroll view
            val autoScrollViewPager = holder.viewPagerBestProduct
            val autoScrollAdapter = AutoScrollAdapter(mcontext, productBestItems!!, requestManager)
            autoScrollViewPager.adapter = autoScrollAdapter

            autoScrollViewPager.interval = 2000
            autoScrollViewPager.startAutoScroll()

            holder.btnAll!!.setOnClickListener{
                category = "301"
                Common.specificCategoryList.clear()
                getcategoryproduct(category!!)
            }
            holder.btnOuter!!.setOnClickListener{
                category = "302"
                Common.specificCategoryList.clear()
                getcategoryproduct(category!!)
            }
            holder.btnTop!!.setOnClickListener{
                category = "303"
                Common.specificCategoryList.clear()
                getcategoryproduct(category!!)
            }
            holder.btnPants!!.setOnClickListener{
                category = "304"
                Common.specificCategoryList.clear()
                getcategoryproduct(category!!)
            }
            holder.btnSkirt!!.setOnClickListener{
                category = "305"
                Common.specificCategoryList.clear()
                getcategoryproduct(category!!)
            }
            holder.btnOnepiece!!.setOnClickListener{
                category = "306"
                Common.specificCategoryList.clear()
                getcategoryproduct(category!!)
            }
            holder.btnShoes!!.setOnClickListener{
                category = "307"
                Common.specificCategoryList.clear()
                getcategoryproduct(category!!)
            }
            holder.btnBag!!.setOnClickListener{
                category = "308"
                Common.specificCategoryList.clear()
                getcategoryproduct(category!!)
            }
            holder.btnAcc!!.setOnClickListener{
                category = "309"
                Common.specificCategoryList.clear()
                getcategoryproduct(category!!)
            }
           holder.sButton!!.setOnClickListener{
                   /* Log.v("click","클릭됨")
                    val intent = Intent(mcontext, SearchActivity::class.java)
                    intent.putExtra("title","강남 지하상가")
                    mcontext.startActivity(intent)
                    activity = mcontext as Activity
                    activity!!.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)*/

            }

        } else{
            var itemViewHolder = holder as ViewHolder

            requestManager.load(productListItems!!.get(position-1).image).into(itemViewHolder!!.imageProduct)
            itemViewHolder.productPrice!!.text = productListItems!!.get(position-1).price
            itemViewHolder.mName!!.text = productListItems!!.get(position-1).mname
            itemViewHolder.productName!!.text = productListItems!!.get(position-1).name
        }

    }

    override fun getItemCount(): Int {
        return productListItems!!.size + 1
    }

    //뷰타입 정하기
    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) {
            TYPE_HEADER
        } else TYPE_ITEM
    }

    //true가 반환되면 리스트의 끝임을 알수있다.
    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    private fun dataChange(productListItems: ArrayList<GetCategoryProductData>) {

        this.productListItems = productListItems
        notifyDataSetChanged()

    }

    //헤더 뷰 홀더
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewPagerBestProduct: AutoScrollViewPager
        var btnAll: Button
        var btnOuter: Button
        var btnTop : Button
        var btnPants : Button
        var btnSkirt : Button
        var btnOnepiece : Button
        var btnShoes : Button
        var btnBag : Button
        var btnAcc : Button
        var sButton : Button

        init {
            viewPagerBestProduct = itemView.findViewById(R.id.autoViewPager)

            btnAll = itemView.findViewById(R.id.item_btn_all)
            btnOuter = itemView.findViewById(R.id.item_btn_outer)
            btnTop = itemView.findViewById(R.id.item_btn_top)
            btnPants = itemView.findViewById(R.id.item_btn_pants)
            btnSkirt = itemView.findViewById(R.id.item_btn_skirt)
            btnOnepiece = itemView.findViewById(R.id.item_btn_onepiece)
            btnShoes = itemView.findViewById(R.id.item_btn_shoes)
            btnBag = itemView.findViewById(R.id.item_btn_bag)
            btnAcc = itemView.findViewById(R.id.item_btn_acc)

            sButton = itemView.findViewById(R.id.button2)

        }
    }

    //아이템 뷰 홀더
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageProduct: ImageView? = null
        var productName: TextView? = null
        var productPrice: TextView? = null
        var mName : TextView? = null

        init {
            imageProduct = itemView.findViewById(R.id.cycel_item)
            productName = itemView.findViewById(R.id.cycel_itemname)
            productPrice = itemView.findViewById(R.id.cycel_price)
            mName = itemView.findViewById(R.id.cycel_storename)
        }
    }

    override fun onClick(v: View) {
        var category : String?= null
        when (v.id) {
            R.id.item_btn_all -> {
                category = "301"
                Common.specificCategoryList.clear()
                getcategoryproduct(category)
            }
            R.id.item_btn_outer -> {
                category = "302"
                Common.specificCategoryList.clear()
                getcategoryproduct(category)
            }
            R.id.item_btn_top -> {
                category = "303"
                Common.specificCategoryList.clear()
                getcategoryproduct(category)
            }
            R.id.item_btn_pants -> {
                category = "304"
                Common.specificCategoryList.clear()
                getcategoryproduct(category)
            }
            R.id.item_btn_skirt -> {
                category = "305"
                Common.specificCategoryList.clear()
                getcategoryproduct(category)
            }
            R.id.item_btn_onepiece -> {
                category = "306"
                Common.specificCategoryList.clear()
                getcategoryproduct(category)
            }
            R.id.item_btn_shoes -> {
                category = "307"
                Common.specificCategoryList.clear()
                getcategoryproduct(category)
            }
            R.id.item_btn_bag -> {
                category = "308"
                Common.specificCategoryList.clear()
                getcategoryproduct(category)
            }
            R.id.item_btn_acc -> {
                category = "309"
                Common.specificCategoryList.clear()
                getcategoryproduct(category)
            }
        }


    }

    companion object {

        //1은 아이템 2는 푸터
        private val TYPE_ITEM = 1
        private val TYPE_HEADER = 2
    }

    fun getcategoryproduct(category : String){
        Log.v("dek0112","start")
        var getCategoryProductResponse = networkService!!.getCategoryProduct(category,"201")
        Log.v("dek0112","여까지됨")
        getCategoryProductResponse.enqueue(object : Callback<GetCategoryProductResponse> {
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
                        productListItems = Common.specificCategoryList
                        notifyDataSetChanged()
                        ApplicationController.instance!!.makeToast("카테고리 지역 별 상품 가져오기 성공")
                    }
                    else{
                     //   ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    //ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetCategoryProductResponse>?, t: Throwable?) {
               // ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
        return
    }
}