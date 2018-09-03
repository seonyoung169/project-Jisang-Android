package com.dek.jisangbase.POST

/**
 * Created by 김진석 on 2018-01-12.
 */
data class QuestionRegisterBody (
        var pid : Int,
        var content : String,
        var secret : String
)