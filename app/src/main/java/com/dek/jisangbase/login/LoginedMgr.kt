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
import android.widget.Button
import com.dek.jisangbase.Common
import com.dek.jisangbase.MainActivity
import com.dek.jisangbase.MrgPage.MgrPageActivity
import com.dek.jisangbase.R
import kotlinx.android.synthetic.main.logined_mgr.*

class LoginedMgr : Fragment(), View.OnClickListener {

    private var mypage_managerpage_btn: Button? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.logined_mgr, container, false)

        mypage_managerpage_btn = v.findViewById<Button>(R.id.mypage_managerpage_btn)
        mypage_managerpage_btn!!.setOnClickListener(this)

        return v
    }

    override fun onStart() {
        super.onStart()
        mypage_logout_btn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
        //로그인화면이므로 replacefragment가 아니고,
            mypage_managerpage_btn -> {
                val intent = Intent(context, MgrPageActivity::class.java)
                startActivity(intent)
            }
            mypage_logout_btn ->{
                signOut()
            }

        }
    }

    fun signOut() {
        Log.d("TEST","SignOut1")
        var pref: SharedPreferences = context.getSharedPreferences("autologin", Context.MODE_PRIVATE)
        val editor = pref!!.edit()
        editor.putString("myid", "")
        editor.putString("mypwd", "")
        editor.commit()
        val intent = Intent(context, MainActivity::class.java)
        Common.myToken = ""
        Common.myAdmin = "103"
        activity.startActivityForResult(intent, 0)
    }
}