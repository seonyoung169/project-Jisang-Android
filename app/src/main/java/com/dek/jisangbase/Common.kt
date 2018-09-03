package com.dek.jisangbase

import android.widget.LinearLayout
import com.dek.jisangbase.GET.*
import com.dek.jisangbase.POST.SignMgrPostResponse
import com.dek.jisangbase.POST.SignPostResponse
import com.dek.jisangbase.item_info.ItemInfoData
import com.dek.jisangbase.mainContainer.MainFragment2.MainFragment2ChildData
import com.dek.jisangbase.market.MarketItemData
import com.dek.signup.POST.LoginPostResponse
import com.dek.storedetail.item_question.QuestionData
import com.dek.storedetail.item_question_reply.QuestionReplyData

/**
 * Created by 김진석 on 2018-01-06.
 */
object Common {
    var bottomBar: LinearLayout? = null
    var imageList : ArrayList<Int> = ArrayList()
    var questionDatas : ArrayList<QuestionData> = ArrayList()
    var hashTagDatas : ArrayList<GetHashTagData> = ArrayList()
    var keywordData : ArrayList<GetKeywordData> = ArrayList()
    var questioncommentDatas : ArrayList<QuestionReplyData> = ArrayList()
    var questionReplyDatas : ArrayList<QuestionReplyData> = ArrayList()
    var itemdata : ArrayList<MarketItemData> = ArrayList()
    var mgrPageDatas : ArrayList<RegisteredItemListDataSub> = ArrayList()
    var newItemDatas : ArrayList<MainFragment2ChildData> = ArrayList()
    var itemInfoImage : ArrayList<ItemInfoData> = ArrayList()
    var itemdetail : ArrayList<ItemInfoData> = ArrayList()

    var currentStorename : String ?= null

    //Networking
    var myToken : String? = null
    var myAdmin : String ?= null

    var bookMarkList : ArrayList<GetBookMarkListData> = ArrayList()
    var wholeBestList : ArrayList<GetWholeBestData> = ArrayList()
    var wholeCategoryList : ArrayList<GetWholeCategoryData> = ArrayList()
    var specificCategoryList : ArrayList<GetCategoryProductData> = ArrayList()
    var loginPostResponse : LoginPostResponse?  = null
    var signPostResponse : SignPostResponse? = null
    var signMgrPostResponse : SignMgrPostResponse? = null
    var marketBestResponse : GetMarketBestResponse? = null

    var currentPage : Int? = null

}