package com.shamweel.trimaplus.data.respository

import com.shamweel.trimaplus.data.network.ApiInterface
import com.shamweel.trimaplus.data.network.SafeApiCall

class SplashIntroRepository(
    private val api: ApiInterface
): SafeApiCall {

    suspend fun getIntroData(
        url : String
    ) = safeApiCall {
        api.getSplashIntroById(url)
    }

}