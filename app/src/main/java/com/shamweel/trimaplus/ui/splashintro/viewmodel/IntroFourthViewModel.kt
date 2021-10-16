package com.shamweel.trimaplus.ui.splashintro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shamweel.trimaplus.data.network.Resource
import com.shamweel.trimaplus.data.responses.IntroResponse
import com.shamweel.trimaplus.data.respository.SplashIntroRepository
import kotlinx.coroutines.launch

class IntroFourthViewModel(
    private val repository : SplashIntroRepository
): BaseViewModel(repository) {

    private val _response: MutableLiveData<Resource<IntroResponse>> = MutableLiveData()
    val response: LiveData<Resource<IntroResponse>>
        get() = _response

    fun getIntroData(
        page_id : String
    ) = viewModelScope.launch {
        _response.value = Resource.Loading
        _response.value = repository.getIntroData(page_id)
    }

}