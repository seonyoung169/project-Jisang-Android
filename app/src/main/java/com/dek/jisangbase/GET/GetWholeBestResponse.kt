package com.dek.jisangbase.GET

/**
 * Created by 김진석 on 2018-01-10.
 */
data class GetWholeBestResponse (
        var status : String,
        var msg : String,
        var bestArr : ArrayList<GetWholeBestData>
)