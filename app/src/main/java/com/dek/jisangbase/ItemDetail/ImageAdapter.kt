package com.dek.storedetail.item_detail_fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.bumptech.glide.RequestManager

class ImageAdapter(fm: FragmentManager?, var tabCount : Int, var dataList : ArrayList<String>, var requestManager: RequestManager) : FragmentStatePagerAdapter(fm) {

        //requestManager!!.load(dataList.get(position)).into(image_container)

    var tab : ImageFragment? = null
    //기본생성자를 상속받는 새로운 생성자
//    constructor(fm : FragmentManager, tabCount : Int) : this(fm) {
//        this.tabCount = tabCount
//        this.tab = ImageFragment()
//    }

    override fun getItem(position: Int): Fragment? {
/*
        for(i in 0..(tabCount-1)){
            when(i){
                i -> return tab
            }
        }*/

        return ImageFragment.create(dataList.get(position), position, requestManager)
    }

    override fun getCount(): Int = tabCount



}