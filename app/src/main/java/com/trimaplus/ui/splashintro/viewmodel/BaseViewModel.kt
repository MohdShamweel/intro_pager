package com.trimaplus.ui.splashintro.viewmodel

import androidx.lifecycle.ViewModel
import com.trimaplus.data.respository.SplashIntroRepository

abstract class BaseViewModel(
    private val repository: SplashIntroRepository
): ViewModel() {
}