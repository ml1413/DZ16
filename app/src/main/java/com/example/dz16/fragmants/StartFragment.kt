package com.example.dz16.fragmants

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dz16.R
import com.example.dz16.adapter.AdapterForAllHolder
import com.example.dz16.databinding.FragmentStartBinding
import com.example.dz16.models.Character
import com.example.dz16.utils.onOffProgressBar
import com.example.dz16.viewModel.AllHeroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding
    private lateinit var adapter: AdapterForAllHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this)[AllHeroViewModel::class.java]
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                AllHeroViewModel.UiState.Empty -> Unit
                is AllHeroViewModel.UiState.Error -> Unit
                AllHeroViewModel.UiState.Loading -> onOffProgressBar(shopProgressBar = true)
                is AllHeroViewModel.UiState.Result -> {
                    setListInAdapter(uiState.list)
                    onOffProgressBar(shopProgressBar = false)
                }
            }
        }
        initField()
        return binding.root
    }


    private fun initField() {
        adapter = AdapterForAllHolder(itemLayout = R.layout.item_hero) {character,imageView->
                openDetailsFragment(item = character,imageVIew = imageView)
            }
        binding.recyclerView.adapter = adapter
    }

    private fun setListInAdapter(listItem: List<Character>) {
        adapter.setList(list = listItem)
    }

    private fun openDetailsFragment(item: Character,imageVIew:ImageView) {
        val detailsFragment = DetailsFragment.newInstance(item = item)
        parentFragmentManager
            .beginTransaction()
            .addSharedElement(imageVIew, item.name)
            .addToBackStack("")
            .replace(R.id.activity_container, detailsFragment)
            .commit()

    }


}