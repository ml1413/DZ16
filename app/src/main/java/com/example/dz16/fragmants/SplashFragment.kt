package com.example.dz16.fragmants

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.dz16.R
import com.example.dz16.adapter.AdapterForAllHolder
import com.example.dz16.databinding.FragmentSplashBinding
import com.example.dz16.models.Character
import com.example.dz16.utils.onOffProgressBar
import com.example.dz16.utils.setImage
import com.example.dz16.viewModel.OneHeroViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var adapter: AdapterForAllHolder
    private lateinit var viewModel: OneHeroViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[OneHeroViewModel::class.java]
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                OneHeroViewModel.UiState.Empty -> Unit
                is OneHeroViewModel.UiState.Error -> Unit
                OneHeroViewModel.UiState.Loading -> onOffProgressBar(true)
                is OneHeroViewModel.UiState.Success -> {
                    onOffProgressBar(false)
                    setImageOnSplash(character = uiState.character)
                    setLabelBottomImage(character = uiState.character)
                    openStartFragment()
                }
            }
        }
        initView()
        return binding.root
    }

    private fun initView() {
        adapter = AdapterForAllHolder(R.layout.item_label_hero)
        binding.recyclerView.adapter = adapter
    }

    private fun setLabelBottomImage(character: Character) {
        val stringArray = character.name.split("")
        requireActivity().runOnUiThread {
            adapter.setList(stringArray)
        }
    }


    private fun openStartFragment() {
        lifecycleScope.launch {
            delay(4000)
            parentFragmentManager.beginTransaction()
                .replace(R.id.activity_container, StartFragment())
                .commit()

        }

    }


    private fun setImageOnSplash(character: Character) {
        binding.ivDetails.setImage(character.images.md, false, binding.ivBackground)
        binding.tvNameCardView.text = "-=Hero=-"
        startImageAnimation()
    }

    private fun startImageAnimation() {
        ObjectAnimator.ofFloat(binding.cardViewSplash, View.ALPHA, 0f, 1f).apply {
            this.startDelay = 1000
            this.duration = 500
            this.start()
        }
    }

}

