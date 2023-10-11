package com.example.dz16.adapter

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.dz16.databinding.ItemDetailsBinding
import com.example.dz16.databinding.ItemHeroBinding
import com.example.dz16.databinding.ItemLabelHeroBinding
import com.example.dz16.models.Character
import com.example.dz16.utils.KEY_CATEGORY
import com.example.dz16.utils.K_INFO
import com.example.dz16.utils.K_LABEL
import com.example.dz16.utils.setImage

sealed class AllHolders(view: View) : RecyclerView.ViewHolder(view) {
    //______________________________________________________________________________________________
    class CharacterHolder(private val view: View) : AllHolders(view) {
        private val binding = ItemHeroBinding.bind(view)

        fun initView(item: Any, clickCallBack: (item: Character) -> Unit) = with(binding) {
            val character = item as Character
            tvName.text = character.name
            ivAvatar.setImage(character.images.lg, true, ivBackground)
            itemView.setOnClickListener {
                clickCallBack(character)
            }

            ObjectAnimator.ofFloat(itemView, View.ALPHA, 0f, 1f)
                .setDuration(100)
                .start()

        }
    }

    //______________________________________________________________________________________________
    class CharacterDetails(private val view: View) : AllHolders(view) {
        private val binding = ItemDetailsBinding.bind(view)
        fun initView(item: Any) = with(binding) {
            val info = item as HashMap<String, String>

            tvCategory.isVisible = info[KEY_CATEGORY]?.isNotBlank() == true

            tvCategory.text = info[KEY_CATEGORY]
            tvPower.text = info[K_LABEL]
            tvInfo.text = info[K_INFO]

        }
    }

    //______________________________________________________________________________________________
    class CharacterLabel(private val view: View) : AllHolders(view) {
        private val binding = ItemLabelHeroBinding.bind(view)
        fun initView(item: Any) {
            binding.text1.text = item as String
        }
    }
}