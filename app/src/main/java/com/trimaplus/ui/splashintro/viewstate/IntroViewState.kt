package com.trimaplus.ui.splashintro.viewstate

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.trimaplus.BR
import com.trimaplus.helper.bind

class IntroViewState(initProgressVisibility: Int = View.VISIBLE,
                     initTitle: String? = null,
                     initAnimUrl: String? = null,
                     initDesc: String? = null): BaseObservable() {

    @get:Bindable
    var progressVisibility by bind(BR.progressVisibility, initProgressVisibility)

    @get:Bindable
    var animUrl by bind(BR.animUrl, initAnimUrl)

    @get:Bindable
    var title by bind(BR.title, initTitle)

    @get:Bindable
    var desc by bind(BR.desc, initDesc)


}