package com.dek.storedetail.item_detail_fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.R
import kotlinx.android.synthetic.main.fragment_image.view.*

class ImageFragment() : Fragment() {
    var image : String? = null
    var requstManager : RequestManager? = null

    companion object {
        fun create(image : String, position : Int, requestManager: RequestManager) : ImageFragment {
            var imageFragement = ImageFragment(image, requestManager)
            return imageFragement
        }
    }
    constructor(image : String, requestManager: RequestManager) : this() {
        this.image = image
        this.requstManager = requestManager
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_image,container,false)
        requstManager!!.load(image).into(v.viewpager_image);
        //v.viewpager_image.setImageResource(image) //서버에서 받고 나면 달라짐.

        return v
    }
}