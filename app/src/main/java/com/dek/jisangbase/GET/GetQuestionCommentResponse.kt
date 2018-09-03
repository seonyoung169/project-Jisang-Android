package com.dek.jisangbase.GET

/**
 * Created by 김진석 on 2018-01-11.
 */
data class GetQuestionCommentResponse (
        var status : String,
        var msg : String,
        var commentArr : ArrayList<GetQuestionCommentData>
)