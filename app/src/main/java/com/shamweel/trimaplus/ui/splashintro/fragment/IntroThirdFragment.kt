package com.shamweel.trimaplus.ui.splashintro.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.shamweel.trimaplus.R
import com.shamweel.trimaplus.data.network.ApiInterface
import com.shamweel.trimaplus.data.network.RemoteDataSource
import com.shamweel.trimaplus.data.network.Resource
import com.shamweel.trimaplus.data.respository.SplashIntroRepository
import com.shamweel.trimaplus.databinding.FragmentIntroContainerBinding
import com.shamweel.trimaplus.ui.extensions.handleAPIError
import com.shamweel.trimaplus.ui.extensions.setViews
import com.shamweel.trimaplus.ui.splashintro.viewmodel.IntroThirdViewModel

class IntroThirdFragment : Fragment(R.layout.fragment_intro_container) {

    private lateinit var binding: FragmentIntroContainerBinding
    private lateinit var viewModel: IntroThirdViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIntroContainerBinding.bind(view)

        val remoteDataSource = RemoteDataSource()
        val api = remoteDataSource.buildApi(ApiInterface::class.java, requireContext())
        val repository = SplashIntroRepository(api)
        viewModel = IntroThirdViewModel(repository)


        getData()
        viewModel.response.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    binding.layoutIntro.setViews(it.value.data)
                }
                is Resource.Failure -> handleAPIError(it) {
                    getData()
                }
            }
        })
    }

    private fun getData() {
        val url: String = resources.getString(R.string.GET_INTRO_PAGE_3)
        viewModel.getIntroData(url)
    }

}