package com.dek.jisangbase.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.dek.jisangbase.sign.SignActivity
import com.dek.signup.POST.LoginPost
import com.dek.signup.POST.LoginPostResponse
import com.dek.signup.login.ForgotPwd
import com.tsengvn.typekit.TypekitContextWrapper
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity() {

    private var networkService : NetworkService? = null
    private var email : EditText? = null
    var pref : SharedPreferences?= null
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        networkService = ApplicationController.instance!!.networkService

        pref = this.getSharedPreferences("autologin", Context.MODE_PRIVATE)


        email = findViewById(R.id.login_ID) as EditText?
        //email!!.setText(Integer.getInteger("email"))

        signup_btn.setOnClickListener{
            val intent = Intent(applicationContext, SignActivity::class.java)
            startActivity(intent)
        }
        forgot_pwd_btn.setOnClickListener{
            val intent = Intent(applicationContext, ForgotPwd::class.java)
            startActivity(intent)
        }

        //----꼭지워야할 부분 ----------FOR TEST-----------------------//
        facebook.setOnClickListener{
            // val intent = Intent(applicationContext, MainActivity::class.java)
            // startActivity(intent)
        }

        //---------------------------NETWORKING-------------------------//
        login_button.setOnClickListener{
            login()
        }


    }

    fun login(){
        login_ID.isEnabled = false
        login_PW.isEnabled = false
        val loginPostResponse = networkService!!.login(LoginPost(login_ID.text.toString(), login_PW.text.toString()))
        loginPostResponse.enqueue(object : Callback<LoginPostResponse> {
            override fun onResponse(call: Call<LoginPostResponse>?, response: Response<LoginPostResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){

                        Common.loginPostResponse = response!!.body()
                        Common.myToken = response!!.body().data.token
                        Log.v("ismytokenok?",Common.myToken.toString())
                        Common.myAdmin = (response!!.body().data.admin)
                        //ApplicationController.instance!!.makeToast("로그인 성공")

                        val editor = pref!!.edit()
                        editor.putString("myid",login_ID.text.toString())
                        editor.putString("mypwd",login_PW.text.toString())
                        editor.commit()

                    }else{
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }
                else{
                    ApplicationController.instance!!.makeToast("내 잘못이야")
                }

                login_ID.isEnabled = true
                login_PW.isEnabled = true
                finish()
            }
            override fun onFailure(call: Call<LoginPostResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

}