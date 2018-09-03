package com.dek.jisangbase.LocationProductList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.R

/**
 * Created by wlgns on 2018-01-11.
 */
class AutoScroll : AppCompatActivity() {

    var autoViewPager : AutoScrollViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_scroll)



        var data : ArrayList<String> = ArrayList()  //이미지 url를 저장하는 arraylist
        data.add("https://upload.wikimedia.org/wikipedia/en/thumb/2/24/SpongeBob_SquarePants_logo.svg/1200px-SpongeBob_SquarePants_logo.svg.png")
        data.add("http://nick.mtvnimages.com/nick/promos-thumbs/videos/spongebob-squarepants/rainbow-meme-video/spongebob-rainbow-meme-video-16x9.jpg?quality=0.60")
        data.add("http://nick.mtvnimages.com/nick/video/images/nick/sb-053-16x9.jpg?maxdimension=&quality=0.60")
        data.add("https://www.gannett-cdn.com/-mm-/60f7e37cc9fdd931c890c156949aafce3b65fd8c/c=243-0-1437-898&r=x408&c=540x405/local/-/media/2017/03/14/USATODAY/USATODAY/636250854246773757-XXX-IMG-WTW-SPONGEBOB01-0105-1-1-NC9J38E8.JPG")
        Log.d("lsy","데이터 들어가니?")

        var data2 : ArrayList<Int> = ArrayList()
        data2.add(R.drawable.acc)
        data2.add(R.drawable.acc)
        data2.add(R.drawable.acc)
        data2.add(R.drawable.acc)
        data2.add(R.drawable.acc)

        autoViewPager = findViewById(R.id.autoViewPager) as AutoScrollViewPager
        val requestManager : RequestManager = Glide.with(this)
        val scrollAdapter = AutoScrollAdapter(this, data2, requestManager)
        autoViewPager!!.adapter = scrollAdapter //Auto Viewpager에 Adapter 장착

        Log.d("lsy","어댑터 장착 하니")
        autoViewPager!!.interval = 2000 // 페이지 넘어갈 시간 간격 설정
        autoViewPager!!.startAutoScroll() //Auto Scroll 시작
        Log.d("lsy","스타트 오토 스크롤")

    }
}