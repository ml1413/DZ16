package com.example.dz16.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dz16.R

class AdapterForAllHolder(val clickItem: ClickItem, private val itemLayout: Int) :
    RecyclerView.Adapter<AllHolders>() {
    private var listItem: List<Any> = emptyList()
    fun setList(list: List<Any>) {
        listItem = list; notifyDataSetChanged()
    }

    override fun getItemCount() = listItem.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHolders {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(itemLayout, parent, false)

      return  when (itemLayout) {
            R.layout.item_hero -> AllHolders.CharacterHolder(view = view)
            R.layout.item_details -> AllHolders.CharacterDetails(view = view)
            else -> throw Exception("Error")
        }
    }

    override fun onBindViewHolder(holder: AllHolders, position: Int) {
        when (holder) {
            is AllHolders.CharacterHolder -> holder.initView(listItem[position], clickItem)
            is AllHolders.CharacterDetails -> holder.initView(listItem[position], clickItem)
        }
    }


}





