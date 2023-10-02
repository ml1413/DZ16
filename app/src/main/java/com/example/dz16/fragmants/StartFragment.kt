package com.example.dz16.fragmants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dz16.R
import com.example.dz16.adapter.AdapterForAllHolder
import com.example.dz16.adapter.ClickItem
import com.example.dz16.databinding.FragmentStartBinding
import com.example.dz16.models.Character
import com.example.dz16.utils.onOffProgressBar
import com.example.dz16.viewModel.HeroViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment(), ClickItem {
    private lateinit var binding: FragmentStartBinding
    private lateinit var adapter: AdapterForAllHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this)[HeroViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                HeroViewModel.UiState.Empty -> Unit
                is HeroViewModel.UiState.Error -> Unit
                HeroViewModel.UiState.Loading -> onOffProgressBar(shopProgressBar = true)
                is HeroViewModel.UiState.Result -> {
                    setListInAdapter(uiState.list)
                    onOffProgressBar(shopProgressBar = false)
                }
            }
        }
        initField()
        return binding.root
    }

    private fun initField() {
        adapter = AdapterForAllHolder(clickItem = this, itemLayout = R.layout.item_hero)
        binding.recyclerView.adapter = adapter
    }

    private fun setListInAdapter(listItem: List<Character>) {
        adapter.setList(list = listItem)
    }


    override fun getModel(item: Character) {
        openDetailsFragment(item = item)
    }

    private fun openDetailsFragment(item: Character) {
        val detailsFragment = DetailsFragment.newInstance(item = item)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, detailsFragment)
            .addToBackStack("")
            .commit()

    }


}