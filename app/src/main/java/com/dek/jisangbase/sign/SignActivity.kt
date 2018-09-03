package com.dek.jisangbase.sign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dek.jisangbase.R
import com.tsengvn.typekit.TypekitContextWrapper
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)


        signup_storemgr_btn.setOnClickListener{
            val intent = Intent(applicationContext, SignStoreMgr::class.java)
            startActivity(intent)
        }
        signup_user_btn.setOnClickListener{
            val intent = Intent(applicationContext, SignUser::class.java)

            //signUser() //for networking

            startActivity(intent)
        }
    }
}
