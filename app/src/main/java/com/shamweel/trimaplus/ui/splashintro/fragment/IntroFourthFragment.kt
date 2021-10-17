package com.shamweel.trimaplus.ui.splashintro.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shamweel.trimaplus.R
import com.shamweel.trimaplus.data.network.Resource
import com.shamweel.trimaplus.databinding.FragmentIntroContainerBinding
import com.shamweel.trimaplus.ui.splashintro.utils.handleAPIError
import com.shamweel.trimaplus.ui.splashintro.utils.setNoInternet
import com.shamweel.trimaplus.ui.splashintro.utils.setViews
import com.shamweel.trimaplus.ui.splashintro.utils.visible
import com.shamweel.trimaplus.ui.splashintro.viewmodel.IntroFourthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroFourthFragment : Fragment(R.layout.fragment_intro_container) {

    private lateinit var binding: FragmentIntroContainerBinding
    private val viewModel: IntroFourthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIntroContainerBinding.bind(view)
        binding.layoutIntro.progressbar.visible(false)

        getData()
        viewModel.response.observe(viewLifecycleOwner, {
            binding.layoutIntro.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    binding.layoutIntro.setViews(it.value.data)
                }
                is Resource.Failure -> {
                    binding.layoutIntro.setNoInternet()
                    handleAPIError(it) {
                        getData()
                    }
                }
            }
        })
    }

private fun getData() {
    val url: String = resources.getString(R.string.GET_INTRO_PAGE_4)
    viewModel.getIntroData(url)
}

}

