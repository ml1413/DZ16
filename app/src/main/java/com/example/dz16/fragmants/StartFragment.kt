package com.example.dz16.fragmants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dz16.R
import com.example.dz16.adapter.AdapterForAllHolder
import com.example.dz16.adapter.ClickItem
import com.example.dz16.databinding.FragmentStartBinding
import com.example.dz16.models.Character
import com.example.dz16.request.ApiClient
import com.example.dz16.request.ApiInterface
import com.example.dz16.request.disposable
import com.example.dz16.request.requestApi


class StartFragment : Fragment(), ClickItem {
    private lateinit var binding: FragmentStartBinding
    private lateinit var client: ApiInterface
    private lateinit var adapter: AdapterForAllHolder
    private var itemClickListener: (Character) -> Unit = {}
    private var characters: List<Character> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        initField()

        if (characters.isEmpty()) {
            requestApi(single = client.getAllHero()) {
                characters = it as List<Character>
                setListInAdapter(listItem = characters)
            }
        } else {
            setListInAdapter(listItem = characters)
        }


        return binding.root
    }

    private fun initField() {
        client = ApiClient.retrofit.create(ApiInterface::class.java)
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