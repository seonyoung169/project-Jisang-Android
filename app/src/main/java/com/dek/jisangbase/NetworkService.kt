package com.dek.jisangbase

import com.dek.jisangbase.GET.*
import com.dek.jisangbase.MrgPage.UploadData
import com.dek.jisangbase.POST.*
import com.dek.signup.POST.LoginPost
import com.dek.signup.POST.LoginPostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    //실제로 post를 작성하기 위해 함수 작성
    //@Header("token")

    //로그인
    @POST("signin")
    fun login(@Body loginPost: LoginPost): Call<LoginPostResponse> //LoginPost를 보내서 Response로 받는다.

    //회원가입
    @POST("signup")
    fun signup(@Body signPost: SignPost): Call<SignPostResponse>

    //관리자 회원가입
    @POST("signup")
    fun signupMgr(@Body hostsignupPost: SignMgrPost): Call<SignMgrPostResponse>

    //북마크 리스트 불러오기
    @GET("bookmark")
    fun getBookMarkList(@Header("token") token: String) : Call<GetBookMarkListResponse>

    //매장 베스트아이템 불러오기
    @GET("product/market/best")
    fun getMarketBest(@Query("mid") mid: Int) : Call<GetMarketBestResponse>

    //전체지역 베스트 상품 가져오기
    @GET("product/whole/best")
    fun getWholeBest() : Call<GetWholeBestResponse>

    //젠체&카테고리 별 상품 가져오기
    @GET("product/whole/category")
    fun getWholeCategoryProduct(@Query("category") category: String) : Call<GetWholeCategoryResponse>

    //물품 상세보기 설명 및 가격 등
    @GET("product")
    fun getProductDetail( @Query("pid") pid: Int) : Call<GetProductDetailResponse>

    @GET("product/mall/category")
    fun getCategoryProduct( @Query("category") category : String, @Query("location") location : String) : Call<GetCategoryProductResponse>

    //특정상품 문의사항 확인
    @GET("question/product")
    fun getProductQuestion( @Query("pid") pid: Int) : Call<GetQuestionProductResponse>

    //대댓글 가져오기
    @GET("comment")
    fun getQuestionComment(@Query("qid") qid: Int) : Call<GetQuestionCommentResponse>

    //북마크 등록&해제
    @POST("bookmark/market")
    fun bookMarkUpdate(@Header("token") token: String, @Body bookmarkupdatePost : BookMarkUpdatePost) : Call<BookMarkUpdateResponse>

    //mid로 매장정보 가져오기
    @GET("market/info")
    fun getMarketByMid(@Query("mid") mid : Int) : Call<GetMarketInfoByMidResponse>

    //매장 아이템 불러오기
    @GET("product/market")
    fun getMarketProduct(@Query("mid") mid: Int) : Call<GetMarketProductResponse>

    //유저 문의사항 불러오기
    @GET("question/user")
    fun getUserQuestion(@Header("token") token : String, @Query("pid") pid: Int) : Call<GetUserQuestionResponse>

    //북마크인지 아닌지
    @GET("bookmark/market")
    fun getIsBookMark(@Header("token") token : String, @Query("mid") mid: Int) : Call<GetIsBookMarkResponse>

    //상품상세 - 상품 문의 - QnA 등록하기
    @POST("question/product")
    fun registerQuestion (
            @Header("token") token : String,
            @Body  bodyData : QuestionRegisterBody
    ) : Call<QuestionRegisterData>

    //검색창 주요 키워드 가져오기
    @GET("hashtag/list")
    fun getHashTag() : Call<GetHashTagResponse>

    //검색창 키워드 검색결과 가져오기
    @GET("hashtag")
    fun getKeyword(@Query("hashname") hashname : String) : Call<GetKeywordResponse>

    //매장 대표이미지 가져오기
    @GET("market/mainimg")
    fun getMarketMainImg(@Query("mid") mid: Int) : Call<GetMarketImageResponse>

    //매장 베스트상품(관리자)
    @GET("market/auth/best")
    fun getMarketAuthBest(@Header("token") token: String) : Call<GetMarketAuthBestResponse>

    //관리자페이지 - 등록된 상품 리스트 받아오기
    @GET("market/auth/registered")
    fun getRegisteredItemList(@Header("token") token : String) : Call<RegisteredItemListData>

    @Multipart
    @POST("market/auth/mainimg")
    fun setMarketAuthMainImg(@Header("token") token: String,
                             @Part marketid: MultipartBody.Part,
                             @Part("test") test : RequestBody) : Call<SetMarketAuthMainImgResponse>

    //관리자 페이지 - 관리자가 상품을 등록한다
    @Multipart
    @POST("market/auth/product")
    fun uploadItem(
            @Header("token") token : String,
            @Part images : ArrayList<MultipartBody.Part>,
            @Part("category") category: RequestBody,
            @Part("price") price: RequestBody,
            @Part("hashtags") hashtags: RequestBody,
            @Part("detail") detail: RequestBody,
            @Part("best") best: RequestBody,
            @Part("name") name: RequestBody
    ): Call<UploadData>

    //등록된 상품 받아오기 - 내화면
    @GET("market/auth/date")
    fun registeredList(
            @Header("token") token : String,
            @Query("date") date : String ) : retrofit2.Call<RegisteredListFromData>

    @POST("market/auth/deleted")
    fun registeredDelete(
            @Header("token") token : String,
            @Body productArr : ProductArrList
    ) : retrofit2.Call<ItemDeleteData>

}

