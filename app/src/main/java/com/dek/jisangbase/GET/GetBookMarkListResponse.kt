package com.dek.jisangbase.GET

/**
 * Created by 김진석 on 2018-01-08.
 */
data class GetBookMarkListResponse (
        var status : String,
        var msg : String,
        var list : ArrayList<GetBookMarkListData>
)