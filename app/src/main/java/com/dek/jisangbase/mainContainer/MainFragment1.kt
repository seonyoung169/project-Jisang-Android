package com.dek.jisangbase.mainContainer

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.LocationProductList.ProductListActivity
import com.dek.jisangbase.R

/**
 * Created by 김진석 on 2018-01-06.
 */
class MainFragment1  : Fragment(), View.OnClickListener {
    private var location_ground: TextView? = null
    private var location_click: TextView? = null

    private var locate_btn1: Button? = null
    private var locate_btn2: Button? = null
    private var locate_btn3: Button? = null
    private var locate_btn4: Button? = null
    private var locate_btn5: Button? = null
    private var meta: ImageView? = null

    private var location : Int? = null

    private var locate_go_btn : Button? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.main_fragment1, container, false)
        //위 함수의 argument -> xml을 fragment에서 사용할 수 있게 (= activity의 setconentview)

        meta = v.findViewById(R.id.imageView2)
        val locationview_Deco2 = GlideDrawableImageViewTarget(meta)
        Glide.with(this).load(R.drawable.locationview_deco2).into(locationview_Deco2)
        location_ground= v.findViewById(R.id.location_ground)
        location_click = v.findViewById(R.id.location_click)

        locate_btn1 = v.findViewById(R.id.location_btn1)
        locate_btn2 = v.findViewById(R.id.location_btn2)
        locate_btn3 = v.findViewById(R.id.location_btn3)
        locate_btn4 = v.findViewById(R.id.location_btn4)
        locate_btn5 = v.findViewById(R.id.location_btn5)

        locate_go_btn = v.findViewById(R.id.locateGo_btn)

        locate_btn1!!.isSelected = false
        locate_btn2!!.isSelected = false
        locate_btn3!!.isSelected = false
        locate_btn4!!.isSelected = false
        locate_btn5!!.isSelected = false

        locate_btn1!!.setOnClickListener(this)
        locate_btn2!!.setOnClickListener(this)
        locate_btn3!!.setOnClickListener(this)
        locate_btn4!!.setOnClickListener(this)
        locate_btn5!!.setOnClickListener(this)

        locate_go_btn!!.setOnClickListener(this)

        return v
    }

    override fun onClick(v: View?) {
        when(v) {
            locate_btn1->{
                locate_btn1!!.isSelected = true
                locate_btn2!!.isSelected = false
                locate_btn3!!.isSelected = false
                locate_btn4!!.isSelected = false
                locate_btn5!!.isSelected = false
                location_ground!!.setText("강남")
                location_click!!.setText("바로가기")
            }
            locate_btn2->{
                locate_btn1!!.isSelected = false
                locate_btn2!!.isSelected = true
                locate_btn3!!.isSelected = false
                locate_btn4!!.isSelected = false
                locate_btn5!!.isSelected = false
                location_ground!!.setText("고속터미널")
                location_click!!.setText("바로가기")
            }
            locate_btn3->{
                locate_btn1!!.isSelected = false
                locate_btn2!!.isSelected = false
                locate_btn3!!.isSelected = true
                locate_btn4!!.isSelected = false
                locate_btn5!!.isSelected = false

                location_ground!!.setText("영등포")
                location_click!!.setText("바로가기")
            }
            locate_btn4->{
                locate_btn1!!.isSelected = false
                locate_btn2!!.isSelected = false
                locate_btn3!!.isSelected = false
                locate_btn4!!.isSelected = true
                locate_btn5!!.isSelected = false

                location_ground!!.setText("부평")
                location_click!!.setText("바로가기")
            }
            locate_btn5->{
                locate_btn1!!.isSelected = false
                locate_btn2!!.isSelected = false
                locate_btn3!!.isSelected = false
                locate_btn4!!.isSelected = false
                locate_btn5!!.isSelected = true

                location_ground!!.setText("의정부")
                location_click!!.setText("바로가기")
            }
            locate_go_btn->{
                if(location_ground!!.text == "강남"){
                    val intent = Intent(context, ProductListActivity::class.java)
                    intent.putExtra("locName",location_ground.toString())
                    startActivity(intent)
                }
                else if(location_ground!!.text == "고속터미널"){
                    ApplicationController.instance!!.makeToast("서비스 준비중입니다.")
                }
                else if(location_ground!!.text == "영등포"){
                    ApplicationController.instance!!.makeToast("서비스 준비중입니다.")
                }
                else if(location_ground!!.text == "부평"){
                    ApplicationController.instance!!.makeToast("서비스 준비중입니다.")
                }
                else if(location_ground!!.text == "의정부"){
                    ApplicationController.instance!!.makeToast("서비스 준비중입니다.")
                }
                else{
                    ApplicationController.instance!!.makeToast("지역을 선택해주세요")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}