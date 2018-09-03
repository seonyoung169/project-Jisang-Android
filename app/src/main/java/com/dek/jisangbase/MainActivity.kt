package com.dek.jisangbase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.login.LoginedMgr
import com.dek.jisangbase.login.LoginedUser
import com.dek.jisangbase.mainContainer.MainFragment1
import com.dek.jisangbase.mainContainer.MainFragment2.MainFragment2
import com.dek.jisangbase.mainContainer.MainFragment3
import com.dek.jisangbase.mainContainer.MainFragment4
import com.dek.jisangbase.mainContainer.MainFragment5
import com.tsengvn.typekit.TypekitContextWrapper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    private var networkService : NetworkService? = null
    private var requestManager : RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        if(savedInstanceState == null){
            val bundle =  Bundle()
            AddFragment(MainFragment1(),bundle,"frag1") //가장 처음 프래그먼트를 붙임 (first는 tag)
        }
        frag1.isSelected = true
        frag2.isSelected = false
        frag3.isSelected = false
        frag4.isSelected = false
        frag5.isSelected = false

        //button click listener
        frag1.setOnClickListener(this)
        frag2.setOnClickListener(this)
        frag3.setOnClickListener(this)
        frag4.setOnClickListener(this)
        frag5.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        when(p0){
            frag1->{
                frag1.isSelected = true
                frag2.isSelected = false
                frag3.isSelected = false
                frag4.isSelected = false
                frag5.isSelected = false
                val bundle = Bundle()
                ReplaceFragment(MainFragment1(), bundle, "frag1")
            }
            frag2->{
                frag1.isSelected = false
                frag2.isSelected = true
                frag3.isSelected = false
                frag4.isSelected = false
                frag5.isSelected = false
                val bundle = Bundle()
                ReplaceFragment(MainFragment2(), bundle, "frag2")
            }
            frag3->{
                frag1.isSelected = false
                frag2.isSelected = false
                frag3.isSelected = true
                frag4.isSelected = false
                frag5.isSelected = false
                val bundle = Bundle()
                ReplaceFragment(MainFragment3(), bundle, "frag3")
            }
            frag4->{
                frag1.isSelected = false
                frag2.isSelected = false
                frag3.isSelected = false
                frag4.isSelected = true
                frag5.isSelected = false

                val bundle = Bundle()
                ReplaceFragment(MainFragment4(), bundle, "frag4")
            }
            frag5->{
                frag1.isSelected = false
                frag2.isSelected = false
                frag3.isSelected = false
                frag4.isSelected = false
                frag5.isSelected = true

                if(Common.myAdmin == "102") {
                    replaceFragment(LoginedMgr())
                } else if(Common.myAdmin == "101"){
                    replaceFragment(LoginedUser())
                } else {
                    replaceFragment(MainFragment5())
                }
            }
        }
    }

    //requestCode가 102면 관리자
    //requestCode가 101이면 사용자
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.v("requestCode",requestCode.toString())

        val bundle = Bundle()
        if(Common.myAdmin == "102") {
            replaceFragment(LoginedMgr())
        } else if(Common.myAdmin == "101"){
            replaceFragment(LoginedUser())
        } else {
            replaceFragment(MainFragment5())
        }
        /*
        if(requestCode == 101){
            val bundle =  Bundle()
            ApplicationController.instance!!.makeToast("MY 사용자 프래그먼트 넘기기 성공")
            //ReplaceFragment(LoginedMgr(),bundle,"fragMgr")
        }
        else if(requestCode == 102){
            val bundle =  Bundle()
            ApplicationController.instance!!.makeToast("MY 관리자 프래그먼트 넘기기 성공")
            //ReplaceFragment(LoginedUser(),bundle,"frag5")
        }*/

    }

    //붙이기만 하고 끝낼 함수 , 지우지 않음
    fun AddFragment(fragment : Fragment, bundle : Bundle, tag : String){

        val fm = supportFragmentManager //fragment 종합적관리
        val transaction = fm.beginTransaction() //삽입,삭제 ..
        fragment.arguments = bundle
        transaction.add(R.id.main_container,fragment,tag) //main에서 정의한 프레임 레이아웃에 프래그먼트를 올린다.
        transaction.commit()
    }
    fun ReplaceFragment(fragment: Fragment, bundle: Bundle, tag: String){
        val fm = supportFragmentManager //fragment 종합적관리
        val transaction = fm.beginTransaction() //삽입,삭제 ..
        fragment.arguments = bundle

        transaction.replace(R.id.main_container,fragment,tag) //replace할 때 fragment에 tag가 붙음
        transaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }






}
