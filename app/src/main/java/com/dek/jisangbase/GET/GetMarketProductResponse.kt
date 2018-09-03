package com.dek.jisangbase.GET

/**
 * Created by 김진석 on 2018-01-12.
 */
data class GetMarketProductResponse (
        var status : String,
        var msg : String,
        var productArr : ArrayList<GetMarketProductData>
)