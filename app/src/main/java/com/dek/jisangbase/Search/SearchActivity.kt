package com.dek.jisangbase.Search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetHashTagData
import com.dek.jisangbase.GET.GetHashTagResponse
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.tsengvn.typekit.TypekitContextWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private var networkService : NetworkService? = null

    private var itemsearchEdittext : EditText? = null

    private var searchiconBtn : Button? = null

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null
    private var button4 : Button? = null
    private var button5 : Button? = null
    private var button6 : Button? = null
    private var button7 : Button? = null
    private var button8 : Button? = null
    private var button9 : Button? = null
    private var button10 : Button? = null
    private var button11 : Button? = null
    private var button12 : Button? = null
    private var button13 : Button? = null
    private var button14 : Button? = null
    private var button15 : Button? = null
    private var button16 : Button? = null
    private var button17 : Button? = null

    private var titleText : TextView? = null

    private var item1 : String? = null
    private var item2 : String? = null
    private var item3 : String? = null
    private var item4 : String? = null
    private var item5 : String? = null
    private var item6 : String? = null
    private var item7 : String? = null
    private var item8 : String? = null
    private var item9 : String? = null
    private var item10 : String? = null
    private var item11 : String? = null
    private var item12 : String? = null
    private var item13 : String? = null
    private var item14 : String? = null
    private var item15 : String? = null
    private var item16 : String? = null
    private var item17 : String? = null

    private var search_keyword : String? = null

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        networkService = ApplicationController.instance!!.networkService
        titleText = findViewById(R.id.title_text) as TextView

        titleText!!.setText(getIntent().getStringExtra("title"))
        itemsearchEdittext = findViewById(R.id.search_edittext) as EditText

        searchiconBtn = findViewById(R.id.button_in_search_edittext) as Button

        button1 = findViewById(R.id.tag_button1)  as Button
        button2 = findViewById(R.id.tag_button2)   as Button
        button3 = findViewById(R.id.tag_button3)   as Button
        button4 = findViewById(R.id.tag_button4)   as Button
        button5 = findViewById(R.id.tag_button5)   as Button
        button6 = findViewById(R.id.tag_button6)  as Button
        button7 = findViewById(R.id.tag_button7)   as Button
        button8 = findViewById(R.id.tag_button8)  as Button
        button9 = findViewById(R.id.tag_button9)  as Button
        button10 = findViewById(R.id.tag_button10) as Button
        button11 = findViewById(R.id.tag_button11)  as Button
        button12 = findViewById(R.id.tag_button12)  as Button
        button13 = findViewById(R.id.tag_button13)  as Button
        button14 = findViewById(R.id.tag_button14)  as Button
        button15 = findViewById(R.id.tag_button15)  as Button
        button16 = findViewById(R.id.tag_button16)  as Button
        button17 = findViewById(R.id.tag_button17)   as Button

        Common.hashTagDatas.clear()
        getproductquestion()

        //--------------------해시태그 버튼 글씨에 따라서 유동적으로 움직이게 한 것!-------------------

        //------------------------------------------------------------------------------
        button1!!.setOnClickListener(this)
        button2!!.setOnClickListener(this)
        button3!!.setOnClickListener(this)
        button4!!.setOnClickListener(this)
        button5!!.setOnClickListener(this)
        button6!!.setOnClickListener(this)
        button7!!.setOnClickListener(this)
        button8!!.setOnClickListener(this)
        button9!!.setOnClickListener(this)
        button10!!.setOnClickListener(this)
        button11!!.setOnClickListener(this)
        button12!!.setOnClickListener(this)
        button13!!.setOnClickListener(this)
        button14!!.setOnClickListener(this)
        button15!!.setOnClickListener(this)
        button16!!.setOnClickListener(this)
        button17!!.setOnClickListener(this)

        searchiconBtn!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            searchiconBtn -> {
                Log.v("hash","돋보기 버튼 눌리니?")
                search_keyword = itemsearchEdittext!!.text.toString()
                val intent = Intent(applicationContext, ResultActivity::class.java)
                intent.putExtra("keyword",search_keyword)
                startActivity(intent)
            }
            button1-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[0].keyword)
            }
            button2-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[1].keyword)
            }
            button3-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[2].keyword)
            }
            button4-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[3].keyword)
            }
            button5-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[4].keyword)
            }
            button6-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[5].keyword)
            }
            button7-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[6].keyword)
            }
            button8-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[7].keyword)
            }
            button9-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[8].keyword)
            }
            button10-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[9].keyword)
            }
            button11-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[10].keyword)
            }
            button12-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[11].keyword)
            }
            button13-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[12].keyword)
            }
            button14-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[13].keyword)
            }
            button15-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[14].keyword)
            }
            button16-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[15].keyword)
            }
            button17-> {
                itemsearchEdittext!!.setText(Common.hashTagDatas[16].keyword)
            }
        }

        // TODO search_keyword 인텐트로 넘기기 -> 다음 activity로 검색해서 list를 넘겨온다.
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }
    fun getproductquestion(){
        var gethashtagResponse = networkService!!.getHashTag()

        Log.v("hash","여긴 되니....")
        gethashtagResponse.enqueue(object : Callback<GetHashTagResponse> {

            override fun onResponse(call: Call<GetHashTagResponse>?, response: Response<GetHashTagResponse>?) {
                Log.v("hash",response!!.message())
                if(response!!.isSuccessful){
                    Log.v("hash","isSuccessful")
                    if (response!!.body().status.equals("success")){
                        Log.v("hash",response!!.body().status)

                        if(response!!.body().hashtagArr != null) {
                            for (i in 0..response!!.body().hashtagArr.size - 1) {
                                Common.hashTagDatas.add(GetHashTagData(response!!.body().hashtagArr[i].keyword))
                                Log.v("hash",response!!.body().hashtagArr[i].keyword)
                            }
                        }

                        if(Common.hashTagDatas != null) {
                            val params_button1 = button1!!.layoutParams
                            item1 = "#" + Common.hashTagDatas[0].keyword
                            params_button1.width = item1!!.length * 60
                            button1!!.layoutParams = params_button1
                            button1!!.setText(item1)

                            val params_button2 = button2!!.layoutParams
                            item2 = "#" + Common.hashTagDatas[1].keyword
                            params_button2.width = item2!!.length * 60
                            button2!!.layoutParams = params_button2
                            button2!!.setText(item2)

                            val params_button3 = button3!!.layoutParams
                            item3 = "#" + Common.hashTagDatas[2].keyword
                            params_button3.width = item3!!.length * 60
                            button3!!.layoutParams = params_button3
                            button3!!.setText(item3)

                            val params_button4 = button4!!.layoutParams
                            item4 = "#" + Common.hashTagDatas[3].keyword
                            params_button4.width = item4!!.length * 60
                            button4!!.layoutParams = params_button4
                            button4!!.setText(item4)

                            val params_button5 = button5!!.layoutParams
                            item5 = "#" + Common.hashTagDatas[4].keyword
                            params_button5.width = item5!!.length * 60
                            button5!!.layoutParams = params_button5
                            button5!!.setText(item5)

                            val params_button6 = button6!!.layoutParams
                            item6 = "#" + Common.hashTagDatas[5].keyword
                            params_button6.width = item6!!.length * 60
                            button6!!.layoutParams = params_button6
                            button6!!.setText(item6)

                            val params_button7 = button7!!.layoutParams
                            item7 = "#" + Common.hashTagDatas[6].keyword
                            params_button7.width = item7!!.length * 60
                            button7!!.layoutParams = params_button7
                            button7!!.setText(item7)

                            val params_button8 = button8!!.layoutParams
                            item8 = "#" + Common.hashTagDatas[7].keyword
                            params_button8.width = item8!!.length * 60
                            button8!!.layoutParams = params_button8
                            button8!!.setText(item8)

                            val params_button9 = button9!!.layoutParams
                            item9 = "#" + Common.hashTagDatas[8].keyword
                            params_button9.width = item9!!.length * 60
                            button9!!.layoutParams = params_button9
                            button9!!.setText(item9)

                            val params_button10 = button10!!.layoutParams
                            item10 = "#" + Common.hashTagDatas[9].keyword
                            params_button10.width = item10!!.length * 60
                            button10!!.layoutParams = params_button10
                            button10!!.setText(item10)

                            val params_button11 = button11!!.layoutParams
                            item11 = "#" + Common.hashTagDatas[10].keyword
                            params_button11.width = item11!!.length * 60
                            button11!!.layoutParams = params_button11
                            button11!!.setText(item11)

                            val params_button12 = button12!!.layoutParams
                            item12 = "#" + Common.hashTagDatas[11].keyword
                            params_button12.width = item12!!.length * 60
                            button12!!.layoutParams = params_button12
                            button12!!.setText(item12)

                            val params_button13 = button13!!.layoutParams
                            item13 = "#" + Common.hashTagDatas[12].keyword
                            params_button13.width = item13!!.length * 60
                            button13!!.layoutParams = params_button13
                            button13!!.setText(item13)

                            val params_button14 = button14!!.layoutParams
                            item14 = "#" + Common.hashTagDatas[13].keyword
                            params_button14.width = item14!!.length * 60
                            button14!!.layoutParams = params_button14
                            button14!!.setText(item14)

                            val params_button15 = button15!!.layoutParams
                            item15 = "#" + Common.hashTagDatas[14].keyword
                            params_button15.width = item15!!.length * 60
                            button15!!.layoutParams = params_button15
                            button15!!.setText(item15)

                            val params_button16 = button16!!.layoutParams
                            item16 = "#" + Common.hashTagDatas[15].keyword
                            params_button16.width = item16!!.length * 60
                            button16!!.layoutParams = params_button16
                            button16!!.setText(item16)

                            val params_button17 = button17!!.layoutParams
                            item17 = "#" + Common.hashTagDatas[16].keyword
                            params_button17.width = item17!!.length * 60
                            button17!!.layoutParams = params_button17
                            button17!!.setText(item17)
                        }

                        //ApplicationController.instance!!.makeToast("해시태그 가져오기 성공")
                    }
                    else{
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetHashTagResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
                Log.v("hash",t.toString())
            }
        })
    }


}