package com.trimaplus.data.network

import com.trimaplus.data.responses.IntroResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    @GET
    suspend fun getSplashIntroById(@Url url: String) : IntroResponse

}