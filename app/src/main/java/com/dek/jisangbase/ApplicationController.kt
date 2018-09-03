package com.dek.jisangbase

import android.app.Application
import android.content.Context
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationController : Application() {
    var networkService: NetworkService? = null
        private set
    val baseUrl = "http://13.125.29.25/"
    var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        instance = this

        buildNetwork()

/*        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this,"NotoSansCJKkr-Regular.otf"))
                .addBold(Typekit.createFromAsset(this,"NotoSansCJKkr-Bold.otf"))
                .addCustom1(Typekit.createFromAsset(this,"NotoSansCJKkr-DemiLight.otf"))
                .addCustom2(Typekit.createFromAsset(this,"NotoSansCJKkr-Light.otf"))
                .addCustom3(Typekit.createFromAsset(this,"NotoSansCJKkr-Medium.otf"))*/
    }


    fun buildNetwork() {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        networkService = retrofit.create(NetworkService::class.java)
    }

    fun makeToast(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        var instance: ApplicationController? = null
    }
}