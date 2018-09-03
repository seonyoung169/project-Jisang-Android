package com.dek.jisangbase.mainContainer

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.dek.jisangbase.Common
import com.dek.jisangbase.R
import com.dek.jisangbase.login.LoginActivity

class MainFragment5: Fragment() {

    private var mypageLoginBtn : Button? = null
    private var v: View? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater!!.inflate(R.layout.main_fragment5,container,false)
        //위 함수의 argument -> xml을 fragment에서 사용할 수 있게 (= activity의 setconentview)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mypageLoginBtn = v!!.findViewById<Button>(R.id.mypage_login_btn)
        mypageLoginBtn!!.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            if(Common.myAdmin == "102") {
                //ApplicationController.instance!!.makeToast("관리자")
                activity.startActivityForResult(intent, 102)
            }
            else if(Common.myAdmin == "101"){
                //ApplicationController.instance!!.makeToast("사용자")
                activity.startActivityForResult(intent, 101)
            }
            else{
                //ApplicationController.instance!!.makeToast("너는 누구냐?")
                activity.startActivityForResult(intent, 103)
            }
        }
    }



}