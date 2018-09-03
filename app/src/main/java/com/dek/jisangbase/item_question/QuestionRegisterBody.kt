package com.dek.jisangbase.item_question

data class QuestionRegisterBody(
        var pid : Int,
        var content : String,
        var secret : String
)