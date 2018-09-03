package com.dek.jisangbase.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dek.jisangbase.Common
import com.dek.jisangbase.MainActivity
import com.dek.jisangbase.R
import kotlinx.android.synthetic.main.logined_user.*

/**
 * Created by 김진석 on 2018-01-07.
 */
class LoginedUser : Fragment() , View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0){
            mypage_login_btn -> signOut()
        }


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.logined_user,container,false)

        return v
    }


    override fun onStart() {
        super.onStart()
        mypage_login_btn.setOnClickListener(this)
    }

    fun signOut(){
        Log.d("TEST","SignOut2")
        var pref : SharedPreferences = context.getSharedPreferences("autologin", Context.MODE_PRIVATE)
        val editor = pref!!.edit()
        editor.putString("myid","")
        editor.putString("mypwd","")
        editor.commit()
        val intent = Intent(context, MainActivity::class.java)
        Common.myToken = ""
        Common.myAdmin = "103"
        activity.startActivityForResult(intent, 103)

    }

}