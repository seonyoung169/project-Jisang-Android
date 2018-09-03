package com.dek.jisangbase.MrgPage

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.R
import com.tsengvn.typekit.TypekitContextWrapper
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

/**
 * Created by 김진석 on 2018-01-13.
 */
class MgrPageUpload : AppCompatActivity(), View.OnClickListener {

    private var outerBtn: ImageView? = null
    private var topBtn: ImageView? = null
    private var pantsBtn: ImageView? = null
    private var skirtBtn: ImageView? = null
    private var onepeiceBtn: ImageView? = null
    private var shoesBtn: ImageView? = null
    private var bagBtn: ImageView? = null
    private var accBtn: ImageView? = null

    private var nameText: EditText? = null
    private var priceText: EditText? = null
    private var hashtagText: EditText? = null
    private var detailText: EditText? = null

    private val REQ_CODE_SELECT_IMAGE = 100
    private var data: Uri? = null
    private var image1: MultipartBody.Part? = null
    private var image2: MultipartBody.Part? = null
    private var image3: MultipartBody.Part? = null

    private var networkService: NetworkService? = null
    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null
    private var imageView3: ImageView? = null
    private var imagePosition: Int =0

    private var bestCheck : CheckBox?=null

    private var buttonUpload: Button? = null
    private var type: String? = null


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mgr_page_upload)
        type = "302"


        outerBtn = findViewById(R.id.cate_outer_button) as ImageView?
        topBtn = findViewById(R.id.cate_top_button) as ImageView?
        pantsBtn = findViewById(R.id.cate_pants_button) as ImageView?
        skirtBtn = findViewById(R.id.cate_skirt_button) as ImageView?
        onepeiceBtn = findViewById(R.id.cate_onepiece_button) as ImageView?
        shoesBtn = findViewById(R.id.cate_shoes_button) as ImageView?
        bagBtn = findViewById(R.id.cate_bag_button) as ImageView?
        accBtn = findViewById(R.id.cate_acc_button) as ImageView?

        nameText = findViewById(R.id.upload_edittext1) as EditText?
        priceText = findViewById(R.id.upload_edittext2) as EditText?
        hashtagText = findViewById(R.id.upload_edittext3) as EditText?
        detailText = findViewById(R.id.upload_edittext4) as EditText?

        imageView1 = findViewById(R.id.upload_pic1) as ImageView?
        imageView2 = findViewById(R.id.upload_pic2) as ImageView?
        imageView3 = findViewById(R.id.upload_pic3) as ImageView?

        bestCheck = findViewById(R.id.upload_checkbox) as CheckBox?

        buttonUpload = findViewById(R.id.upload_ok_button) as Button?

        networkService = ApplicationController.instance!!.networkService
        imageView1!!.setOnClickListener(this)
        imageView2!!.setOnClickListener(this)
        imageView3!!.setOnClickListener(this)
        buttonUpload!!.setOnClickListener(this)

        outerBtn!!.setOnClickListener(this)
        topBtn!!.setOnClickListener(this)
        pantsBtn!!.setOnClickListener(this)
        skirtBtn!!.setOnClickListener(this)
        onepeiceBtn!!.setOnClickListener(this)
        shoesBtn!!.setOnClickListener(this)
        bagBtn!!.setOnClickListener(this)
        accBtn!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            imageView1 -> {
                imagePosition = 1;
                changeImage()

            }
            imageView2 -> {
                imagePosition = 2;
                changeImage()
            }
            imageView3 -> {
                imagePosition = 3;
                changeImage()
            }
            buttonUpload -> {
                register();
            }
            outerBtn -> {
                if(type.equals("302")){
                    //아우터 를 안눌린 상태로 바꾼다.
                    outerBtn!!.setImageResource(R.drawable.outer)
                    type = ""
                }else{
                    // 아우터를 눌린 상태로 바꾸고 나머지를 안눌린 상태로 바꾸기
                    outerBtn!!.setImageResource(R.drawable.outer_click)
                    topBtn!!.setImageResource(R.drawable.top)
                    pantsBtn!!.setImageResource(R.drawable.pants)
                    skirtBtn!!.setImageResource(R.drawable.skirt)
                    onepeiceBtn!!.setImageResource(R.drawable.onepiece)
                    shoesBtn!!.setImageResource(R.drawable.shoes)
                    bagBtn!!.setImageResource(R.drawable.bag)
                    accBtn!!.setImageResource(R.drawable.acc)
                    type = "302"
                }

            }
            topBtn -> {
                if(type.equals("303")){
                    //아우터 를 안눌린 상태로 바꾼다.
                    topBtn!!.setImageResource(R.drawable.top)
                    type = ""
                }else{
                    // 아우터를 눌린 상태로 바꾸고 나머지를 안눌린 상태로 바꾸기
                    outerBtn!!.setImageResource(R.drawable.outer)
                    topBtn!!.setImageResource(R.drawable.top_click)
                    pantsBtn!!.setImageResource(R.drawable.pants)
                    skirtBtn!!.setImageResource(R.drawable.skirt)
                    onepeiceBtn!!.setImageResource(R.drawable.onepiece)
                    shoesBtn!!.setImageResource(R.drawable.shoes)
                    bagBtn!!.setImageResource(R.drawable.bag)
                    accBtn!!.setImageResource(R.drawable.acc)
                    type = "303"
                }

            }
            pantsBtn -> {
                if(type.equals("304")){
                    //아우터 를 안눌린 상태로 바꾼다.
                    pantsBtn!!.setImageResource(R.drawable.pants)
                    type = ""
                }else{
                    // 아우터를 눌린 상태로 바꾸고 나머지를 안눌린 상태로 바꾸기
                    outerBtn!!.setImageResource(R.drawable.outer)
                    topBtn!!.setImageResource(R.drawable.top)
                    pantsBtn!!.setImageResource(R.drawable.pants_click)
                    skirtBtn!!.setImageResource(R.drawable.skirt)
                    onepeiceBtn!!.setImageResource(R.drawable.onepiece)
                    shoesBtn!!.setImageResource(R.drawable.shoes)
                    bagBtn!!.setImageResource(R.drawable.bag)
                    accBtn!!.setImageResource(R.drawable.acc)
                    type = "304"
                }

            }
            skirtBtn -> {
                if(type.equals("305")){
                    //아우터 를 안눌린 상태로 바꾼다.
                    skirtBtn!!.setImageResource(R.drawable.skirt)
                    type = ""
                }else{
                    // 아우터를 눌린 상태로 바꾸고 나머지를 안눌린 상태로 바꾸기
                    outerBtn!!.setImageResource(R.drawable.outer)
                    topBtn!!.setImageResource(R.drawable.top)
                    pantsBtn!!.setImageResource(R.drawable.pants)
                    skirtBtn!!.setImageResource(R.drawable.skirt_click)
                    onepeiceBtn!!.setImageResource(R.drawable.onepiece)
                    shoesBtn!!.setImageResource(R.drawable.shoes)
                    bagBtn!!.setImageResource(R.drawable.bag)
                    accBtn!!.setImageResource(R.drawable.acc)
                    type = "305"
                }

            }
            onepeiceBtn -> {
                if(type.equals("306")){
                    //아우터 를 안눌린 상태로 바꾼다.
                    onepeiceBtn!!.setImageResource(R.drawable.onepiece)
                    type = ""
                }else{
                    // 아우터를 눌린 상태로 바꾸고 나머지를 안눌린 상태로 바꾸기
                    outerBtn!!.setImageResource(R.drawable.outer)
                    topBtn!!.setImageResource(R.drawable.top)
                    pantsBtn!!.setImageResource(R.drawable.pants)
                    skirtBtn!!.setImageResource(R.drawable.skirt)
                    onepeiceBtn!!.setImageResource(R.drawable.onepiece_click)
                    shoesBtn!!.setImageResource(R.drawable.shoes)
                    bagBtn!!.setImageResource(R.drawable.bag)
                    accBtn!!.setImageResource(R.drawable.acc)
                    type = "306"
                }

            }
            shoesBtn -> {
                if(type.equals("307")){
                    //아우터 를 안눌린 상태로 바꾼다.
                    shoesBtn!!.setImageResource(R.drawable.shoes)
                    type = ""
                }else{
                    // 아우터를 눌린 상태로 바꾸고 나머지를 안눌린 상태로 바꾸기
                    outerBtn!!.setImageResource(R.drawable.outer)
                    topBtn!!.setImageResource(R.drawable.top)
                    pantsBtn!!.setImageResource(R.drawable.pants)
                    skirtBtn!!.setImageResource(R.drawable.skirt)
                    onepeiceBtn!!.setImageResource(R.drawable.onepiece)
                    shoesBtn!!.setImageResource(R.drawable.shoes_click)
                    bagBtn!!.setImageResource(R.drawable.bag)
                    accBtn!!.setImageResource(R.drawable.acc)
                    type = "307"
                }

            }
            bagBtn -> {
                if(type.equals("308")){
                    //아우터 를 안눌린 상태로 바꾼다.
                    bagBtn!!.setImageResource(R.drawable.bag)
                    type = ""
                }else{
                    // 아우터를 눌린 상태로 바꾸고 나머지를 안눌린 상태로 바꾸기
                    outerBtn!!.setImageResource(R.drawable.outer)
                    topBtn!!.setImageResource(R.drawable.top)
                    pantsBtn!!.setImageResource(R.drawable.pants)
                    skirtBtn!!.setImageResource(R.drawable.skirt)
                    onepeiceBtn!!.setImageResource(R.drawable.onepiece)
                    shoesBtn!!.setImageResource(R.drawable.shoes)
                    bagBtn!!.setImageResource(R.drawable.bag_click)
                    accBtn!!.setImageResource(R.drawable.acc)
                    type = "308"
                }

            }
            accBtn -> {
                if(type.equals("309")){
                    //아우터 를 안눌린 상태로 바꾼다.
                    accBtn!!.setImageResource(R.drawable.acc)
                    type = ""
                }else{
                    // 아우터를 눌린 상태로 바꾸고 나머지를 안눌린 상태로 바꾸기
                    outerBtn!!.setImageResource(R.drawable.outer)
                    topBtn!!.setImageResource(R.drawable.top)
                    pantsBtn!!.setImageResource(R.drawable.pants)
                    skirtBtn!!.setImageResource(R.drawable.skirt)
                    onepeiceBtn!!.setImageResource(R.drawable.onepiece)
                    shoesBtn!!.setImageResource(R.drawable.shoes)
                    bagBtn!!.setImageResource(R.drawable.bag)
                    accBtn!!.setImageResource(R.drawable.acc_click)
                    type = "309"
                }

            }
        }
    }

    fun changeImage() {
        val intent = Intent(Intent.ACTION_PICK)
        //intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    //if(ApplicationController.getInstance().is)

                    /////Log.v("Uri1", data.clipData.getItemAt(0).uri.toString())

                    this.data = data!!.data
                    Log.v("이미지", this.data.toString())

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 5, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다
                    Log.d("ash3734","imageName"+photo.name)

                    if (imagePosition == 1) {
                        image1 = MultipartBody.Part.createFormData("images", photo.name, photoBody)
                    } else if (imagePosition == 2) {
                        image2 = MultipartBody.Part.createFormData("images", photo.name, photoBody)
                    } else {
                        image3 = MultipartBody.Part.createFormData("images", photo.name, photoBody)
                    }

                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    if (imagePosition == 1) {
                        Glide.with(this).load(data.data).centerCrop().into(imageView1)
                        imageView2!!.visibility = View.VISIBLE
                    } else if (imagePosition == 2) {
                        Glide.with(this).load(data.data).centerCrop().into(imageView2)
                        imageView3!!.visibility = View.VISIBLE
                    } else {
                        Glide.with(this).load(data.data).centerCrop().into(imageView3)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    fun register() {
        networkService = ApplicationController.instance!!.networkService

        var images = ArrayList<MultipartBody.Part>()
        if (imagePosition == 0) {
            ApplicationController.instance!!.makeToast("이미지를 등록해 주세요")
            return
        } else if (imagePosition == 1) {
            images.add(image1!!)
        } else if (imagePosition == 2) {
            images.add(image1!!)
            images.add(image2!!)
        } else {
            images.add(image1!!)
            images.add(image2!!)
            images.add(image3!!)
        }
        //todo 302 나중에 바꾸기
        /*nameText = f
        priceText =
                hashtagText
        detailText =*/
        val category = RequestBody.create(MediaType.parse("text/pain"), type)
        val price = RequestBody.create(MediaType.parse("text/pain"), priceText!!.text.toString())
        /*var hashTagss = hashtagText!!.text.toString().split('#')
        var hashTags : ArrayList<String> = ArrayList<String>()
        for (i in 0..hashTagss.size-1){
            Log.d("ash3734",hashTagss[i])
            hashTags.add("#"+hashTagss[i])
        }*/
        val hashTag = RequestBody.create(MediaType.parse("text/pain"), hashtagText!!.text.toString())
        val detail = RequestBody.create(MediaType.parse("text/pain"), detailText!!.text.toString())

        var check : Int

        if(bestCheck!!.isChecked)
            check = 1
        else
            check = 0
        var best = RequestBody.create(MediaType.parse("text/pain"), check.toString())
        val name = RequestBody.create(MediaType.parse("text/pain"), nameText!!.text.toString())

        if(type.equals("")){
            ApplicationController.instance!!.makeToast("카테고리를 선택해 주세요")
            return
        }
        Log.d("ash3734",images.size.toString()+"이미지 개수")
        var upload = networkService!!.uploadItem("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTUwLCJhZG1pbiI6IjEwMiIsIm1pZCI6NjMsImlhdCI6MTUxNTc3MjY3MCwiZXhwIjoxNTE2Mzc3NDcwfQ.T8T5NDJaH1w1iVuAsTB7TUsjZ2rV5QjmO6-GmSLN_W4",
                images,category,price,hashTag,detail,best,name)
        upload.enqueue(object  : Callback<UploadData> {
            override fun onResponse(call: Call<UploadData>?, response: Response<UploadData>?) {
                Log.v("ash3734", "업로드 onResponse")
                if(response!!.isSuccessful){
                    Log.v("ash3734", "업로드 isSuccessful")
                    if(response.body().status.equals("success")){
                        Log.v("ash3734", "업로드 success")
                        ApplicationController.instance!!.makeToast("완료 되었습니다.")
                        finish()
                    }
                }else{
                    Log.v("ash3734", response.message()+"in 업로드")
                }
            }

            override fun onFailure(call: Call<UploadData>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("실패 띠용!")
            }
        })

    }

    fun getToString(arrayData: ArrayList<String>): String {
        val stringBuilder = StringBuilder()
        for (i in arrayData.indices) {
            stringBuilder.append(arrayData[i])
            if (i < arrayData.size - 1) {
                stringBuilder.append(",")
            }
        }
        Log.d("ash3734",stringBuilder.toString()+"in getToString")
        return stringBuilder.toString()
    }


}