package com.example.dz16.fragmants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dz16.App
import com.example.dz16.R
import com.example.dz16.adapter.AdapterForAllHolder
import com.example.dz16.adapter.ClickItem
import com.example.dz16.databinding.FragmentStartBinding
import com.example.dz16.models.Character
import com.example.dz16.request.disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StartFragment : Fragment(), ClickItem {
    private lateinit var binding: FragmentStartBinding
    private val scope  = CoroutineScope(Dispatchers.IO)
    private lateinit var adapter: AdapterForAllHolder
    private var itemClickListener: (Character) -> Unit = {}
    private var characters: List<Character> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        initField()

        scope.launch {
            val list = App.getApp().repository.getAllHero()
            characters = list
            withContext(Dispatchers.Main){
                setListInAdapter(list)
            }

        }

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
        itemClickListener(item)
    }

    fun setOnItemClickListener(lyambda: (Character) -> Unit) {
        itemClickListener = lyambda
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}