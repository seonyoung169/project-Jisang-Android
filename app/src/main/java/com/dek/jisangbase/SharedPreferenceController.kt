package com.dek.jisangbase

import android.content.Context

/**
 * Created by 김진석 on 2018-01-10.
 */

class SharedPrefrernceController {
    private val TOKEN = "token"
    private val TOKEN_ID = "tokenId"


    fun setToken(context: Context, token: String) {
        val pref = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(TOKEN_ID, TOKEN)
        editor.commit()
    }

    fun getToken(context: Context): String {
        val pref = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE)
        return pref.getString(TOKEN_ID, "")
    }
}
