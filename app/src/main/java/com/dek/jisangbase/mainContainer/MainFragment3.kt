package com.dek.jisangbase.mainContainer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dek.jisangbase.*
import com.dek.jisangbase.market.MarketActivity
import pl.polidea.view.ZoomView

/**
 * Created by 김진석 on 2018-01-06.
 */
class MainFragment3 : Fragment() {

    var choose : ChooseMapDialog?= null
    var v : View ?= null
    var current : String ?= null
    var zoomView : ZoomView?= null
    var pref : SharedPreferences?= null
    var isPinReady = false



    private var networkService : NetworkService? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater!!.inflate(R.layout.main_fragment3, container, false)

        isPinReady = false // 핀 확인



        networkService = ApplicationController.instance!!.networkService
        pref = context.getSharedPreferences("myPin", Context.MODE_PRIVATE)
        val editor = pref!!.edit()

        var whatmarketname = v!!.findViewById<TextView>(R.id.whatmarket)

        val view = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.main_fragment3_zoomview, null, false)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        zoomView = ZoomView(context)
        zoomView!!.addView(view)

        zoomView!!.layoutParams = layoutParams
        //zoomView.isMiniMapEnabled = true // 좌측 상단 검은색 미니맵 설정
        zoomView!!.maxZoom = 4f // 줌 Max 배율 설정  1f 로 설정하면 줌 안됩니다.
        zoomView!!.miniMapCaption = "Mini Map Test" //미니 맵 내용
        zoomView!!.miniMapCaptionSize = 20f // 미니 맵 내용 글씨 크기 설정

        val container = v!!.findViewById<RelativeLayout>(R.id.mapContainer)

        container.addView(zoomView)

        //zz

        var north1 = view!!.findViewById<Button>(R.id.north1)
        var north2 = view!!.findViewById<Button>(R.id.north2)
        var north6 = view!!.findViewById<Button>(R.id.north6)
        var north8 = view!!.findViewById<Button>(R.id.north8)

        var south1 = view!!.findViewById<Button>(R.id.south1)
        var south4 = view!!.findViewById<Button>(R.id.south4)
        var south5 = view!!.findViewById<Button>(R.id.south5)

        var a13 = view!!.findViewById<Button>(R.id.a13)
        var a14 = view!!.findViewById<Button>(R.id.a14)

        var d12 = view!!.findViewById<Button>(R.id.d12)
        var d13 = view!!.findViewById<Button>(R.id.d13)
        var d19 = view!!.findViewById<Button>(R.id.d19)

        // 핀 //

        Log.v("pfpf",pref!!.getBoolean("north1pin",false).toString())

        if (pref!!.getBoolean("north1pin",false) == true) north1!!.setBackgroundResource(R.drawable.map_pick)
        else north1!!.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("north2pin",false) == true) north2.setBackgroundResource(R.drawable.map_pick)
        else north2.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("north6pin",false) == true) north6.setBackgroundResource(R.drawable.map_pick)
        else north6.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("north8pin",false) == true) north8.setBackgroundResource(R.drawable.map_pick)
        else north8.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("south1pin",false) == true) south1.setBackgroundResource(R.drawable.map_pick)
        else south1.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("south4pin",false) == true) south4.setBackgroundResource(R.drawable.map_pick)
        else south4.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("south5pin",false) == true) south5.setBackgroundResource(R.drawable.map_pick)
        else south5.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("a13pin",false) == true) a13.setBackgroundResource(R.drawable.map_pick)
        else a13.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("a14pin",false) == true) a14.setBackgroundResource(R.drawable.map_pick)
        else a14.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("d12pin",false) == true) d12.setBackgroundResource(R.drawable.map_pick)
        else d12.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("d13pin",false) == true) d13.setBackgroundResource(R.drawable.map_pick)
        else d13.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("d19pin",false) == true) d19.setBackgroundResource(R.drawable.map_pick)
        else d19.setBackgroundResource(R.drawable.map_drop_go)




        // 맵 확대 //




        var map_pin_button = v!!.findViewById<Button>(R.id.map_pin_button)
        var map = v!!.findViewById<ImageView>(R.id.map)
        var map_menu = v!!.findViewById<Button>(R.id.map_menu)


        map_pin_button.setOnClickListener(View.OnClickListener {
            if(isPinReady == false){
                map_pin_button.setBackgroundResource(R.drawable.map_pin_click)
                map_pin_button.isEnabled == false
                isPinReady = true
            }else if(isPinReady == true){
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
                map_pin_button.isEnabled == true
                isPinReady = false
            }

        })


        map.setOnClickListener(View.OnClickListener {
            if(isPinReady == true){
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
                isPinReady = false

            }
        })

        // 매장 OnCLick


        north1!!.setOnClickListener { v ->

            current = "북-1"

            if(isPinReady == true) {
                north1.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("north1pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",65)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("north1pin",false)
                            editor.commit()
                            north1.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        north2!!.setOnClickListener { v ->

            current = "북-2"

            if(isPinReady == true) {
                north2.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("north2pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",64)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("north2pin",false)
                            editor.commit()
                            north2.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        north6!!.setOnClickListener { v ->

            current = "북-6"

            if(isPinReady == true) {
                north6.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("north6pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",67)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("north6pin",false)
                            editor.commit()
                            north6.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        north8!!.setOnClickListener { v ->

            current = "북-8"

            if(isPinReady == true) {
                north8.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("north8pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",66)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("north8pin",false)
                            editor.commit()
                            north8.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        south1!!.setOnClickListener { v ->

            current = "남-1"

            if(isPinReady == true) {
                south1.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("south1pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",65)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("south1pin",false)
                            editor.commit()
                            south1.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        south4!!.setOnClickListener { v ->

            current = "남-4"

            if(isPinReady == true) {
                south4.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("south4pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",64)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("south4pin",true)
                            editor.commit()
                            south4.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        south5!!.setOnClickListener { v ->

            current = "남-5"

            if(isPinReady == true) {
                south5.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("south5pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",63)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("south5pin",false)
                            editor.commit()
                            south5.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        a13!!.setOnClickListener { v ->

            current = "A-13"

            if(isPinReady == true) {
                a13.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("a13pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",62)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("a13pin",false)
                            editor.commit()
                            a13.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        a14!!.setOnClickListener { v ->

            current = "A-14"

            if(isPinReady == true) {
                a14.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("a14pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",69)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("a14pin",false)
                            editor.commit()
                            a14.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        d12!!.setOnClickListener { v ->

            current = "D-12"

            if(isPinReady == true) {
                d12.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("d12pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",68)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("d12pin",false)
                            editor.commit()
                            d12.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        d13!!.setOnClickListener { v ->

            current = "D-13"

            if(isPinReady == true) {
                d13.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("d13pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",65)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("d13pin",false)
                            editor.commit()
                            d13.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        d19!!.setOnClickListener { v ->

            current = "D-19"

            if(isPinReady == true) {
                d19.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("d19pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",64)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("d19pin",false)
                            editor.commit()
                            d19.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        map_menu!!.setOnClickListener(View.OnClickListener {

            choose = ChooseMapDialog(context, gangnam, gobus, view)
            choose!!.show()
        })



        return v
    }



    // 지도 바꾸기


    private val gangnam : View.OnClickListener? = View.OnClickListener {
        val view = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.main_fragment3_zoomview, null, false)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        zoomView = ZoomView(context)
        zoomView!!.addView(view)

        zoomView!!.layoutParams = layoutParams
        //zoomView.isMiniMapEnabled = true // 좌측 상단 검은색 미니맵 설정
        zoomView!!.maxZoom = 4f // 줌 Max 배율 설정  1f 로 설정하면 줌 안됩니다.
        zoomView!!.miniMapCaption = "Mini Map Test" //미니 맵 내용
        zoomView!!.miniMapCaptionSize = 20f // 미니 맵 내용 글씨 크기 설정

        val container = v!!.findViewById<RelativeLayout>(R.id.mapContainer)

        container.addView(zoomView)

        val editor = pref!!.edit()

        //zz

        var north1 = view!!.findViewById<Button>(R.id.north1)
        var north2 = view!!.findViewById<Button>(R.id.north2)
        var north6 = view!!.findViewById<Button>(R.id.north6)
        var north8 = view!!.findViewById<Button>(R.id.north8)

        var south1 = view!!.findViewById<Button>(R.id.south1)
        var south4 = view!!.findViewById<Button>(R.id.south4)
        var south5 = view!!.findViewById<Button>(R.id.south5)

        var a13 = view!!.findViewById<Button>(R.id.a13)
        var a14 = view!!.findViewById<Button>(R.id.a14)

        var d12 = view!!.findViewById<Button>(R.id.d12)
        var d13 = view!!.findViewById<Button>(R.id.d13)
        var d19 = view!!.findViewById<Button>(R.id.d19)

        // 핀 //

        Log.v("pfpf",pref!!.getBoolean("north1pin",false).toString())

        if (pref!!.getBoolean("north1pin",false) == true) north1!!.setBackgroundResource(R.drawable.map_pick)
        else north1!!.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("north2pin",false) == true) north2.setBackgroundResource(R.drawable.map_pick)
        else north2.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("north6pin",false) == true) north6.setBackgroundResource(R.drawable.map_pick)
        else north6.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("north8pin",false) == true) north8.setBackgroundResource(R.drawable.map_pick)
        else north8.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("south1pin",false) == true) south1.setBackgroundResource(R.drawable.map_pick)
        else south1.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("south4pin",false) == true) south4.setBackgroundResource(R.drawable.map_pick)
        else south4.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("south5pin",false) == true) south5.setBackgroundResource(R.drawable.map_pick)
        else south5.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("a13pin",false) == true) a13.setBackgroundResource(R.drawable.map_pick)
        else a13.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("a14pin",false) == true) a14.setBackgroundResource(R.drawable.map_pick)
        else a14.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("d12pin",false) == true) d12.setBackgroundResource(R.drawable.map_pick)
        else d12.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("d13pin",false) == true) d13.setBackgroundResource(R.drawable.map_pick)
        else d13.setBackgroundResource(R.drawable.map_drop_go)

        if (pref!!.getBoolean("d19pin",false) == true) d19.setBackgroundResource(R.drawable.map_pick)
        else d19.setBackgroundResource(R.drawable.map_drop_go)




        // 맵 확대 //




        var map_pin_button = v!!.findViewById<Button>(R.id.map_pin_button)
        var map = v!!.findViewById<ImageView>(R.id.map)
        var map_menu = v!!.findViewById<Button>(R.id.map_menu)


        map_pin_button.setOnClickListener(View.OnClickListener {
            if(isPinReady == false){
                map_pin_button.setBackgroundResource(R.drawable.map_pin_click)
                map_pin_button.isEnabled == false
                isPinReady = true
            }else if(isPinReady == true){
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
                map_pin_button.isEnabled == true
                isPinReady = false
            }

        })


        map.setOnClickListener(View.OnClickListener {
            if(isPinReady == true){
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
                isPinReady = false

            }
        })

        // 매장 OnCLick


        north1!!.setOnClickListener { v ->

            current = "북-1"

            if(isPinReady == true) {
                north1.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("north1pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",69)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("north1pin",false)
                            editor.commit()
                            north1.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        north2!!.setOnClickListener { v ->

            current = "북-2"

            if(isPinReady == true) {
                north2.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("north2pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",68)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("north2pin",false)
                            editor.commit()
                            north2.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        north6!!.setOnClickListener { v ->

            current = "북-6"

            if(isPinReady == true) {
                north6.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("north6pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",67)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("north6pin",false)
                            editor.commit()
                            north6.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        north8!!.setOnClickListener { v ->

            current = "북-8"

            if(isPinReady == true) {
                north8.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("north8pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",66)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("north8pin",false)
                            editor.commit()
                            north8.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        south1!!.setOnClickListener { v ->

            current = "남-1"

            if(isPinReady == true) {
                south1.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("south1pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",65)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("south1pin",false)
                            editor.commit()
                            south1.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        south4!!.setOnClickListener { v ->

            current = "남-4"

            if(isPinReady == true) {
                south4.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("south4pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",64)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("south4pin",true)
                            editor.commit()
                            south4.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        south5!!.setOnClickListener { v ->

            current = "남-5"

            if(isPinReady == true) {
                south5.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("south5pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",63)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("south5pin",false)
                            editor.commit()
                            south5.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        a13!!.setOnClickListener { v ->

            current = "A-13"

            if(isPinReady == true) {
                a13.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("a13pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",62)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("a13pin",false)
                            editor.commit()
                            a13.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        a14!!.setOnClickListener { v ->

            current = "A-14"

            if(isPinReady == true) {
                a14.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("a14pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",69)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("a14pin",false)
                            editor.commit()
                            a14.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        d12!!.setOnClickListener { v ->

            current = "D-12"

            if(isPinReady == true) {
                d12.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("d12pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",68)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("d12pin",false)
                            editor.commit()
                            d12.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        d13!!.setOnClickListener { v ->

            current = "D-13"

            if(isPinReady == true) {
                d13.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("d13pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",67)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("d13pin",false)
                            editor.commit()
                            d13.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        d19!!.setOnClickListener { v ->

            current = "D-19"

            if(isPinReady == true) {
                d19.setBackgroundResource(R.drawable.map_pick)
                isPinReady = false
                editor.putBoolean("d19pin",true)
                editor.commit()
                map_pin_button.setBackgroundResource(R.drawable.map_pin)
            }

            val popup = PopupMenu(context.applicationContext, v)//v는 클릭된 뷰를 의미

            activity.menuInflater.inflate(R.menu.map_menu, popup.menu)
            popup.menu.findItem(R.id.whatmarket).setTitle(current)


            popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {

                override fun onMenuItemClick(item: MenuItem): Boolean {

                    when (item.getItemId()) {
                        R.id.m1 -> {
                            Toast.makeText(context.applicationContext, "매장 가기", Toast.LENGTH_SHORT).show()
                            var intent = Intent(context,MarketActivity::class.java)
                            intent.putExtra("mid",66)
                            startActivity(intent)
                        }
                        R.id.m2 -> {
                            MemoDialog(context,current!!).show()
                        }
                        R.id.m3 -> {
                            Toast.makeText(context.applicationContext, "핀 지우기", Toast.LENGTH_SHORT).show()
                            editor.putBoolean("d19pin",false)
                            editor.commit()
                            d19.setBackgroundResource(R.drawable.map_drop_go)
                        }
                        else -> {
                        }
                    }
                    return false
                }
            })

            popup.show()//Popup Menu 보이기
        }

        choose!!.dismiss()
    }

    private val gobus : View.OnClickListener? = View.OnClickListener {
        val view = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.main_fragment3_zoomview2, null, false)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val zoomView = ZoomView(context)
        zoomView.addView(view)

        zoomView.layoutParams = layoutParams
        //zoomView.isMiniMapEnabled = true // 좌측 상단 검은색 미니맵 설정
        zoomView.maxZoom = 2f // 줌 Max 배율 설정  1f 로 설정하면 줌 안됩니다.
        zoomView.miniMapCaption = "Mini Map Test" //미니 맵 내용
        zoomView.miniMapCaptionSize = 20f // 미니 맵 내용 글씨 크기 설정

        val container = v!!.findViewById<RelativeLayout>(R.id.mapContainer)

        container.addView(zoomView)

        choose!!.dismiss()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



}