package com.shamweel.trimaplus.ui.splashintro.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shamweel.trimaplus.data.network.Resource
import com.shamweel.trimaplus.data.responses.IntroResponse
import com.shamweel.trimaplus.data.respository.SplashIntroRepository
import com.shamweel.trimaplus.ui.splashintro.viewstate.IntroViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroFirstViewModel @Inject constructor(private val repository : SplashIntroRepository): BaseViewModel(repository) {

    private val _response: MutableLiveData<Resource<IntroResponse>> = MutableLiveData()
    val response: LiveData<Resource<IntroResponse>>
        get() = _response
    val viewState = IntroViewState()

    fun getIntroData(url: String) = viewModelScope.launch {
        //_response.value = Resource.Loading
        //_response.value = repository.getIntroData(url)
        val rs = repository.getIntroData(url)
        if (rs is Resource.Success<IntroResponse>) {
            viewState.progressVisibility = View.GONE
            viewState.animUrl = rs.value.data.pic
            viewState.title = rs.value.data.title
            viewState.desc = rs.value.data.desc
        } else {

        }
    }

}