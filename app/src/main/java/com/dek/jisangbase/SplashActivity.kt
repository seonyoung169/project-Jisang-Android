package com.dek.jisangbase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dek.signup.POST.LoginPost
import com.dek.signup.POST.LoginPostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    var networkService : NetworkService ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        networkService = ApplicationController.instance!!.networkService
        Log.v("hello", "aaaa")
        login()

        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, 2000)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    fun login(){

        val pref = this.getSharedPreferences("autologin", Context.MODE_PRIVATE)

        if (pref!!.getString("myid","").length == 0) return

        val loginPostResponse = networkService!!.login(LoginPost(pref!!.getString("myid",""), pref!!.getString("mypwd","")))
        loginPostResponse.enqueue(object : Callback<LoginPostResponse> {
            override fun onResponse(call: Call<LoginPostResponse>?, response: Response<LoginPostResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){

                        Common.loginPostResponse = response!!.body()
                        Common.myToken = response!!.body().data.token
                        Log.v("ismytokenok?",Common.myToken.toString())
                        Common.myAdmin = (response!!.body().data.admin)
                        //ApplicationController.instance!!.makeToast("자동 로그인 성공")

                    }else{
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }
                else{
                    ApplicationController.instance!!.makeToast("내 잘못이야")
                }

            }
            override fun onFailure(call: Call<LoginPostResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

}