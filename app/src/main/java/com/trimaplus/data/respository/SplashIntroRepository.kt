package com.trimaplus.data.respository

import com.trimaplus.data.network.ApiInterface
import com.trimaplus.data.network.SafeApiCall
import javax.inject.Inject

class SplashIntroRepository @Inject constructor(private val api: ApiInterface): SafeApiCall {

    suspend fun getIntroData(url : String) = safeApiCall {
        api.getSplashIntroById(url)
    }

}