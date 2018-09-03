package com.dek.jisangbase.GET

/**
 * Created by 김진석 on 2018-01-11.
 */
data class GetProductDetailData (
        var name : String,
        var imageArr : ArrayList<GetIlocationData>,
        var price : String,
        var detail : String,
        var mid : Int
)