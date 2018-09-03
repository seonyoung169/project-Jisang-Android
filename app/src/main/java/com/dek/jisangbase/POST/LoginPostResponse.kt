package com.dek.signup.POST

import com.dek.jisangbase.POST.LoginData

/**
 * Created by 김진석 on 2018-01-05.
 */
data class LoginPostResponse(
        var status : String,
        var msg : String,
        var data : LoginData
)