package com.dek.jisangbase.GET

/**
 * Created by 김진석 on 2018-01-09.
 */
data class GetMarketBestResponse (
        var status : String,
        var msg : String,
        var bestArr : ArrayList<GetMarketBestData>
)