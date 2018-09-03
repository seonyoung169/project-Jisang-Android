package com.dek.jisangbase.GET

/**
 * Created by 김진석 on 2018-01-10.
 */
data class GetWholeCategoryResponse (
        var status : String,
        var msg : String,
        var productArr : ArrayList<GetWholeCategoryData>
)