package com.shamweel.trimaplus.ui.splashintro.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.shamweel.trimaplus.R
import com.shamweel.trimaplus.data.network.ApiInterface
import com.shamweel.trimaplus.data.network.RemoteDataSource
import com.shamweel.trimaplus.data.network.Resource
import com.shamweel.trimaplus.data.responses.Data
import com.shamweel.trimaplus.data.respository.SplashIntroRepository
import com.shamweel.trimaplus.databinding.FragmentIntroFirstBinding
import com.shamweel.trimaplus.ui.splashintro.handleAPIError
import com.shamweel.trimaplus.ui.splashintro.viewmodel.IntroFirstViewModel
import com.shamweel.trimaplus.ui.splashintro.visible
import kotlinx.coroutines.launch


class IntroFirstFragment : Fragment(R.layout.fragment_intro_first) {

    private lateinit var binding: FragmentIntroFirstBinding
    private lateinit var viewModel: IntroFirstViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIntroFirstBinding.bind(view)

        val remoteDataSource = RemoteDataSource()
        val api = remoteDataSource.buildApi(ApiInterface::class.java, requireContext())
        val repository = SplashIntroRepository(api)
        viewModel = IntroFirstViewModel(repository)

        binding.layoutIntro.progressbar.visible(false)

        getData()
        viewModel.response.observe(viewLifecycleOwner, {
            binding.layoutIntro.progressbar.visible(it is Resource.Loading)
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
        binding.layoutIntro.animationView.addLottieOnCompositionLoadedListener {
            binding.layoutIntro.progressbar.visible(false)
        }
    }

    private fun getData() {
        viewModel.getIntroData("pg_1")
    }
}