package com.shamweel.trimaplus.data.network

import com.shamweel.trimaplus.data.responses.IntroResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("intros/{id}")
    suspend fun getSplashIntroById(@Path("id") pageId : String) : IntroResponse

}