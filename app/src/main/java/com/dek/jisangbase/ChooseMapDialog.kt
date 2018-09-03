package com.dek.jisangbase

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_choosemap.*

/**
 * Created by hansol on 2018. 1. 12..
 */
class ChooseMapDialog (context: Context, private var gangnam : View.OnClickListener?,
                       private var gobus : View.OnClickListener?, view : View) : Dialog(context, R.style.ThemeDialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_choosemap)

        getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));


        item1.setOnClickListener(gangnam)

        item2.setOnClickListener(View.OnClickListener {
            Toast.makeText(context.applicationContext, "준비 중입니다.", Toast.LENGTH_SHORT).show()
        })
        item3.setOnClickListener(View.OnClickListener {
            Toast.makeText(context.applicationContext, "준비 중입니다.", Toast.LENGTH_SHORT).show()
        })
        item4.setOnClickListener(View.OnClickListener {
            Toast.makeText(context.applicationContext, "준비 중입니다.", Toast.LENGTH_SHORT).show()
        })

        item5.setOnClickListener(View.OnClickListener {
            Toast.makeText(context.applicationContext, "준비 중입니다.", Toast.LENGTH_SHORT).show()
        })
    }
    init{



    }

}