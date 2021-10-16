package com.shamweel.trimaplus.ui.splashintro.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shamweel.trimaplus.R
import com.shamweel.trimaplus.data.network.ApiInterface
import com.shamweel.trimaplus.data.network.RemoteDataSource
import com.shamweel.trimaplus.data.network.Resource
import com.shamweel.trimaplus.data.responses.Data
import com.shamweel.trimaplus.data.respository.SplashIntroRepository
import com.shamweel.trimaplus.databinding.FragmentIntroThirdBinding
import com.shamweel.trimaplus.ui.splashintro.handleAPIError
import com.shamweel.trimaplus.ui.splashintro.viewmodel.IntroFirstViewModel
import com.shamweel.trimaplus.ui.splashintro.viewmodel.IntroThirdViewModel

class IntroThirdFragment : Fragment(R.layout.fragment_intro_third) {

    private lateinit var binding: FragmentIntroThirdBinding
    private lateinit var viewModel: IntroThirdViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIntroThirdBinding.bind(view)

        val remoteDataSource = RemoteDataSource()
        val api = remoteDataSource.buildApi(ApiInterface::class.java, requireContext())
        val repository = SplashIntroRepository(api)
        viewModel = IntroThirdViewModel(repository)


        getData()
        viewModel.response.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    setViews(it.value.data)
                }
                is Resource.Failure -> handleAPIError(it) {
                    getData()
                }
            }
        })
    }

    private fun setViews(data: Data) {
        binding.layoutIntro.txtTitle.text = data.title
        binding.layoutIntro.txtDesc.text = data.desc
        binding.layoutIntro.animationView.setAnimationFromUrl(data.pic)
    }

    private fun getData() {
        viewModel.getIntroData("pg_3")
    }

}