package com.dek.jisangbase.GET

/**
 * Created by 김진석 on 2018-01-11.
 */
data class GetQuestionProductResponse (
        var status : String,
        var msg : String,
        var questionArr : ArrayList<GetQuestionProductData>
)