package com.dek.jisangbase.GET

/**
 * Created by 김진석 on 2018-01-13.
 */
data class RegisteredItemListData (
        var status : String,
        var msg : String,
        var objArr : ArrayList<RegisteredItemListDataSub>
)