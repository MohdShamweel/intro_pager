package com.trimaplus.ui.splashintro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.trimaplus.R
import com.trimaplus.databinding.FragmentIntroContainerBinding
import com.trimaplus.ui.splashintro.viewmodel.IntroFirstViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IntroFirstFragment : Fragment(R.layout.fragment_intro_container) {

    private lateinit var binding: FragmentIntroContainerBinding
    private val vm: IntroFirstViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentIntroContainerBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = vm
            viewState = vm.viewState
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        /*binding.layoutIntro.progressbar.visible(false)

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
        })*/
    }


    private fun getData() {
        val url: String = resources.getString(R.string.GET_INTRO_PAGE_1)
        vm.getIntroData(url)
    }
}