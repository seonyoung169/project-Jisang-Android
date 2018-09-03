package com.dek.jisangbase.sign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.dek.jisangbase.*
import com.dek.jisangbase.POST.SignMgrPost
import com.dek.jisangbase.POST.SignMgrPostResponse
import com.tsengvn.typekit.TypekitContextWrapper
import kotlinx.android.synthetic.main.sign_store_mgr.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignStoreMgr : AppCompatActivity() {

    private var shoplocaSpinner : Spinner? = null
    private var networkService : NetworkService? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_store_mgr)

        networkService = ApplicationController.instance!!.networkService

        //spinner
        shoplocaSpinner = findViewById(R.id.shoploca_spinner) as Spinner?
        val adapter = ArrayAdapter.createFromResource(this, R.array.shop_loca, android.R.layout.simple_spinner_item)
        shoplocaSpinner!!.adapter = adapter

        mgr_signup_button.setOnClickListener{
            signMgr()
        }
    }
    fun signMgr(){
        val signMgrPostResponse = networkService!!.signupMgr(SignMgrPost(email_edittext.text.toString(), pwd_edittext.text.toString(),
                name_edittext.text.toString(),
                phone_edittext.text.toString(),"102",shopname_edittext.text.toString(), shoplocaSpinner.toString(),shopaddress_edittext.text.toString()))
        signMgrPostResponse.enqueue(object : Callback<SignMgrPostResponse> {
            override fun onResponse(call: Call<SignMgrPostResponse>?, response: Response<SignMgrPostResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        Common.signMgrPostResponse = response!!.body()
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
            override fun onFailure(call: Call<SignMgrPostResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    }

}