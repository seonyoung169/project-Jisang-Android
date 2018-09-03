package com.dek.jisangbase.LocationProductList
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.R

/**
 * Created by wlgns on 2018-01-11.
 */

class AutoScrollAdapter(var context: Context, var data: ArrayList<Int>, var requestManager: RequestManager) : PagerAdapter() {




    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        Log.d("lsy","어댑터1")

        //뷰페이지 슬라이딩 할 레이아웃 인플레이션
        var inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var v = inflater!!.inflate(R.layout.auto_viewpager, container, false)
        Log.d("lsy","어댑터2")
        //ar image_container = v.findViewById(R.id.image_container) as ImageView
        var image_container : ImageView
        image_container = v.findViewById(R.id.auto_imageview)

        if(context == null) {
            Log.d("debug", "------------")
        }
        requestManager.load(data.get(position)).into(image_container)
//        Log.d("debug", data.get(position))
        container.addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}