package com.sergiodev.android.timetonictest.data

import com.sergiodev.android.timetonictest.data.model.AppKeyResponse
import com.sergiodev.android.timetonictest.data.model.OAuthkeyResponse
import com.sergiodev.android.timetonictest.data.model.SesskeyResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface TimetonicService {
    @POST("?req=createAppKey&appname=sergioordonez&version=1.47")
    suspend fun createAppkey() : AppKeyResponse

    @POST("?req=createOauthkey&version=6.49q/6.49")
    suspend fun createOauthkey(@Query("appkey") appkey : String,
                               @Query("login") login : String,
                               @Query("pwd") pwd : String) : OAuthkeyResponse
    @POST("?req=createSesskey&version=6.49q/6.49&")
    suspend fun createSesskey(@Query("oauthkey") oauthkey: String,
                              @Query("o_u") o_u: String,
                              @Query("u_c") u_c: String) : SesskeyResponse

}