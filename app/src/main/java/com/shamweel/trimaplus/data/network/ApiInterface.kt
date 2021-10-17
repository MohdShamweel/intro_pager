package com.shamweel.trimaplus.data.network

import com.shamweel.trimaplus.data.responses.IntroResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET
    suspend fun getSplashIntroById(@Url url: String) : IntroResponse

}