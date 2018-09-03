package com.dek.jisangbase.MrgPage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.dek.jisangbase.ApplicationController
import com.dek.jisangbase.Common
import com.dek.jisangbase.GET.GetMarketAuthBestResponse
import com.dek.jisangbase.GET.GetMarketImageResponse
import com.dek.jisangbase.GET.RegisteredItemListData
import com.dek.jisangbase.NetworkService
import com.dek.jisangbase.POST.SetMarketAuthMainImgResponse
import com.dek.jisangbase.R
import com.mancj.slideup.SlideUp
import kotlinx.android.synthetic.main.activity_mgr_page.*
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


class MgrPageActivity: AppCompatActivity(), View.OnClickListener, SlideUp.SlideListener {

    var slideUp : SlideUp? = null
    private var networkService: NetworkService? = null
    private var requestManager : RequestManager? = null

    private var mgrPageList : RecyclerView? = null
    //private var mgrPageAdapter : MgrPageAdapter? = null

    private val REQ_CODE_SELECT_IMAGE = 100
    private var data : Uri? = null
    private var image : MultipartBody.Part? = null

    private var uploadBtn : Button? = null


    private var gridimg1 : ImageView? = null
    private var gridimg2 : ImageView? = null
    private var gridimg3 : ImageView? = null
    private var gridimg4 : ImageView? = null
    private var gridimg5 : ImageView? = null
    private var gridimg6 : ImageView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mgr_page)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        gridimg1 = findViewById(R.id.grid1) as ImageView
        gridimg2 = findViewById(R.id.grid2) as ImageView
        gridimg3 = findViewById(R.id.grid3) as ImageView
        gridimg4 = findViewById(R.id.grid4) as ImageView
        gridimg5 = findViewById(R.id.grid5) as ImageView
        gridimg6 = findViewById(R.id.grid6) as ImageView


        slideUp = SlideUp(store_manager_recycler_slide)
        slideUp!!.hideImmediately()

        set_profileimage.setOnClickListener(this)

        getmarketimg()
        getmarketauthbest()

        sliding_btn.setOnClickListener(){
            sliding_btn.visibility = View.GONE
            slideUp!!.animateIn()
        }

        slideUp!!.setSlideListener(this)

        mgrPageList = findViewById(R.id.login_store_mgr_recycler) as RecyclerView
        mgrPageList!!.layoutManager = LinearLayoutManager(this)

        //todo 서버에서 받아서 이미지 리스트 넣어두기
        var getRegisteredListFrom = networkService!!.getRegisteredItemList(Common.myToken.toString())

        getRegisteredListFrom.enqueue(object : Callback<RegisteredItemListData>, View.OnClickListener{

            override fun onClick(v: View?) {


            }
            override fun onResponse(call: Call<RegisteredItemListData>?, response: Response<RegisteredItemListData>?) {
                if(response!!.isSuccessful){
                    Log.v("lee","isSuccess 안")
                    if(response.body().status.equals("success")) {
                        Log.v("lee","success 받음")
                        Common.mgrPageDatas = response.body().objArr
                        Log.v("lee","objArr 받아옴")
                        Log.v("lee",response.message())

                        var mgrPageAdapter = MgrPageAdapter(Common.mgrPageDatas, requestManager )
                        mgrPageAdapter!!.setOnItemClickListener(this)
                        mgrPageList!!.adapter = mgrPageAdapter
                    }
                }
            }

            override fun onFailure(call: Call<RegisteredItemListData>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("실패 띠용!")
            }

        })

        Log.v("hell","daa")



        Log.v("hell","add")

        uploadBtn = findViewById(R.id.upload_btn) as Button
        uploadBtn!!.setOnClickListener(this)


    }

    override fun onVisibilityChanged(p0: Int) {
        if(p0 == View.GONE){
            sliding_btn.visibility = View.VISIBLE
        }
    }

    override fun onSlideDown(p0: Float) {
        dim.alpha = (1-(p0/300))
    }

    override fun onClick(v: View?) {
        when(v){
            set_profileimage->{
                changeImage()
            }
            uploadBtn -> {
                val intent = Intent(applicationContext, MgrPageUpload::class.java)
                startActivity(intent)
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

    fun getmarketimg(){
        val getmarketimgResponse = networkService!!.getMarketMainImg(64)
        getmarketimgResponse.enqueue(object : Callback<GetMarketImageResponse> {
            override fun onResponse(call: Call<GetMarketImageResponse>?, response: Response<GetMarketImageResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        Log.v("hihihi",response!!.body().img.marketimg)
                        requestManager!!.load(response!!.body().img.marketimg).into(profile_image)

                        //ApplicationController.instance!!.makeToast("매장 대표 이미지 가져오기 성공")
                    }
                    else{
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetMarketImageResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    } // get market profile image

    fun getmarketauthbest(){
        val marketAuthBestResponse = networkService!!.getMarketAuthBest(Common.myToken.toString())
        marketAuthBestResponse.enqueue(object : Callback<GetMarketAuthBestResponse> {
            override fun onResponse(call: Call<GetMarketAuthBestResponse>?, response: Response<GetMarketAuthBestResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){
                        Log.v("please",response!!.body().bestArr[0].pimg)
                        Log.v("please",response!!.body().bestArr.size.toString())
                        //ApplicationController.instance!!.makeToast("매장 베스트 가져오기 성공")
                        if(response!!.body().bestArr != null){
                            if(response!!.body().bestArr.size == 1){
                                requestManager!!.load(response!!.body().bestArr[0].pimg).into(gridimg1)
                            }else if(response!!.body().bestArr.size == 2){
                                requestManager!!.load(response!!.body().bestArr[0].pimg).into(gridimg1)
                                requestManager!!.load(response!!.body().bestArr[1].pimg).into(gridimg2)
                            }else if(response!!.body().bestArr.size == 3){
                                requestManager!!.load(response!!.body().bestArr[0].pimg).into(gridimg1)
                                requestManager!!.load(response!!.body().bestArr[1].pimg).into(gridimg2)
                                requestManager!!.load(response!!.body().bestArr[2].pimg).into(gridimg3)
                            }else if(response!!.body().bestArr.size == 4){
                                requestManager!!.load(response!!.body().bestArr[0].pimg).into(gridimg1)
                                requestManager!!.load(response!!.body().bestArr[1].pimg).into(gridimg2)
                                requestManager!!.load(response!!.body().bestArr[2].pimg).into(gridimg3)
                                requestManager!!.load(response!!.body().bestArr[3].pimg).into(gridimg4)
                            }else if(response!!.body().bestArr.size == 5){
                                requestManager!!.load(response!!.body().bestArr[0].pimg).into(gridimg1)
                                requestManager!!.load(response!!.body().bestArr[1].pimg).into(gridimg2)
                                requestManager!!.load(response!!.body().bestArr[2].pimg).into(gridimg3)
                                requestManager!!.load(response!!.body().bestArr[3].pimg).into(gridimg4)
                                requestManager!!.load(response!!.body().bestArr[4].pimg).into(gridimg5)
                            }else if(response!!.body().bestArr.size >= 6){
                                Log.v("please","this line is working")
                                requestManager!!.load(response!!.body().bestArr[0].pimg).into(gridimg1)
                                requestManager!!.load(response!!.body().bestArr[1].pimg).into(gridimg2)
                                requestManager!!.load(response!!.body().bestArr[2].pimg).into(gridimg3)
                                requestManager!!.load(response!!.body().bestArr[3].pimg).into(gridimg4)
                                requestManager!!.load(response!!.body().bestArr[4].pimg).into(gridimg5)
                                requestManager!!.load(response!!.body().bestArr[5].pimg).into(gridimg6)
                            }
                        }
                    }

                }else{
                    ApplicationController.instance!!.makeToast("실패")
                }

            }
            override fun onFailure(call: Call<GetMarketAuthBestResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    } // get market best item image

    fun setprofileimg(){
        val setprofileimgResponse = networkService!!.setMarketAuthMainImg(Common.myToken.toString(),image!!
                ,  RequestBody.create(MediaType.parse("text/pain"), "test"))
        Log.v("couldnt",image.toString())
        setprofileimgResponse.enqueue(object : Callback<SetMarketAuthMainImgResponse> {
            override fun onResponse(call: Call<SetMarketAuthMainImgResponse>?, response: Response<SetMarketAuthMainImgResponse>?) {
                if(response!!.isSuccessful){
                    if (response!!.body().status.equals("success")){

                        //ApplicationController.instance!!.makeToast("congra")
                    }
                    else{
                        ApplicationController.instance!!.makeToast("정보를 확인해주세요")
                    }
                }else{
                    ApplicationController.instance!!.makeToast(response!!.message())
                }

            }
            override fun onFailure(call: Call<SetMarketAuthMainImgResponse>?, t: Throwable?) {
                ApplicationController.instance!!.makeToast("통신 상태를 확인해주세요")
            }
        })
    } // get market profile image



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
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    ///RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
                    // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!
                    image = MultipartBody.Part.createFormData("marketid", photo.name, photoBody)
                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    Glide.with(this)
                            .load(data.data)
                            .centerCrop()
                            .into(profile_image)

                    setprofileimg()

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}