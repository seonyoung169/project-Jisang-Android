package com.dek.jisangbase.sign
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dek.jisangbase.*
import com.dek.jisangbase.POST.SignPost
import com.dek.jisangbase.POST.SignPostResponse
import com.tsengvn.typekit.TypekitContextWrapper
import kotlinx.android.synthetic.main.sign_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUser : AppCompatActivity() {

    private var networkService : NetworkService? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_user)

        networkService = ApplicationController.instance!!.networkService

        user_signup_button.setOnClickListener{
            signUser()
        }
    }
    fun signUser(){
        val signPostResponse = networkService!!.signup(SignPost(user_email_edit.text.toString(), user_pw_edit.text.toString(), user_name_edit.text.toString(),
                user_phone_edit.text.toString(), "101"))
        signPostResponse.enqueue(object : Callback<SignPostResponse> {
            override fun onResponse(call: Call<SignPostResponse>?, response: Response<SignPostResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        Common.signPostResponse = response!!.body()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        ApplicationController.instance!!.makeToast("회원가입 성공")
                    }
                    else{
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    ApplicationController.instance!!.makeToast("중복된 아이디입니다")
                }

            }
            override fun onFailure(call: Call<SignPostResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }
}