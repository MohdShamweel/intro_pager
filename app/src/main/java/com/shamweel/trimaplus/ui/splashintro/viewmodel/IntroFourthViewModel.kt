package com.shamweel.trimaplus.ui.splashintro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shamweel.trimaplus.data.network.Resource
import com.shamweel.trimaplus.data.responses.IntroResponse
import com.shamweel.trimaplus.data.respository.SplashIntroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroFourthViewModel @Inject constructor(
    private val repository : SplashIntroRepository
): BaseViewModel(repository) {

    private val _response: MutableLiveData<Resource<IntroResponse>> = MutableLiveData()
    val response: LiveData<Resource<IntroResponse>>
        get() = _response

    fun getIntroData(
        url : String
    ) = viewModelScope.launch {
        _response.value = Resource.Loading
        _response.value = repository.getIntroData(url)
    }

}