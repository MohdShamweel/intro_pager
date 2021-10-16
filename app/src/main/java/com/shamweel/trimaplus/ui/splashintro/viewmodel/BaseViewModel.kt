package com.shamweel.trimaplus.ui.splashintro.viewmodel

import androidx.lifecycle.ViewModel
import com.shamweel.trimaplus.data.respository.SplashIntroRepository

abstract class BaseViewModel(
    private val repository: SplashIntroRepository
): ViewModel() {
}