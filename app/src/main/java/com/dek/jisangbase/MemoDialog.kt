package com.dek.jisangbase

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.dialog_memo.*

/**
 * Created by hansol on 2018. 1. 12..
 */
class MemoDialog (context: Context, market : String) : Dialog(context, R.style.ThemeDialog) {

    var mymarket = market
    var pref : SharedPreferences?= null


    init{
        setContentView(R.layout.dialog_memo)

        getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("debug", "oncrate")

        pref = context.getSharedPreferences("mymemo", Context.MODE_PRIVATE)

        Log.d("mypr",pref.toString())

        Log.v("debug1",pref!!.getString(mymarket, ""))

        memoEdit.setText(pref!!.getString(mymarket, ""))

        memoButton.setOnClickListener(View.OnClickListener {
            val editor = pref!!.edit()
            editor.putString(mymarket,memoEdit.text.toString())
            editor.commit()
            Log.v("debug2",pref!!.getString(mymarket, ""))

            dismiss()
        })

    }


}