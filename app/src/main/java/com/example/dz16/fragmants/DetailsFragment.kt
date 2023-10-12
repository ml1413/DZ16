package com.example.dz16.fragmants

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.dz16.R
import com.example.dz16.adapter.AdapterForAllHolder
import com.example.dz16.databinding.FragmentDetailsBinding
import com.example.dz16.models.Character
import com.example.dz16.utils.KEY_CATEGORY
import com.example.dz16.utils.K_INFO
import com.example.dz16.utils.K_LABEL
import com.example.dz16.utils.setImage


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private var item: Character? = null
    private lateinit var adapter: AdapterForAllHolder
    private lateinit var listInfo: List<HashMap<String, String>>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        init()
        setImageAndTitle()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivDetails.transitionName = item?.name
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

    private fun init() {
        item = getCharacterArgument()
        getListInfo()//запотняем listInfo
        adapter = AdapterForAllHolder(itemLayout = R.layout.item_details)
        adapter.setList(list = listInfo)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.smoothScrollToPosition(0)
    }

    private fun setImageAndTitle() {
        item?.let {
            binding.ivDetails.setImage(it.images.md, false, binding.ivBackground)
            binding.ivDetails.startAnimation(
                AnimationUtils.loadAnimation(
                    requireContext(),
                    R.anim.alpha
                )
            )
            binding.tvNameCardView.text = it.name
        }
    }


    private fun getListInfo() {
        item?.let {
            listInfo = listOf(
                hashMapOf(KEY_CATEGORY to "статистика"),
                hashMapOf(K_LABEL to "Интеллект", K_INFO to it.powerstats.intelligence),
                hashMapOf(K_LABEL to "Сила", K_INFO to it.powerstats.strength),
                hashMapOf(K_LABEL to "Скорость", K_INFO to it.powerstats.speed),
                hashMapOf(K_LABEL to "Долговечность", K_INFO to it.powerstats.durability),
                hashMapOf(K_LABEL to "Мощность", K_INFO to it.powerstats.power),
                hashMapOf(K_LABEL to "Бой", K_INFO to it.powerstats.combat),
                //__________________________________________________
                hashMapOf(KEY_CATEGORY to "появление"),
                hashMapOf(K_LABEL to "Пол", K_INFO to it.appearance.gender),
                hashMapOf(K_LABEL to "Расса", K_INFO to it.appearance.race),
                hashMapOf(K_LABEL to "Рост", K_INFO to it.appearance.height[1]),
                hashMapOf(K_LABEL to "Вес", K_INFO to it.appearance.weight[1]),
                hashMapOf(K_LABEL to "Цвет злаз", K_INFO to it.appearance.eyeColor),
                hashMapOf(K_LABEL to "Цвет волос", K_INFO to it.appearance.hairColor),
                //__________________________________________________
                hashMapOf(KEY_CATEGORY to "биография"),
                hashMapOf(K_LABEL to "Полное имя", K_INFO to it.biography.fullName),
                hashMapOf(K_LABEL to "Альтер эго", K_INFO to it.biography.alterEgos),
                hashMapOf(
                    K_LABEL to "Псевдонимы",
                    K_INFO to it.biography.aliases.toString().replace("[\\[\\]]".toRegex(), "")
                ),
                hashMapOf(K_LABEL to "Место рождения", K_INFO to it.biography.placeOfBirth),
                hashMapOf(K_LABEL to "Первое появление", K_INFO to it.biography.firstAppearance),
                hashMapOf(K_LABEL to "Издатель", K_INFO to it.biography.publisher),
                hashMapOf(K_LABEL to "Персонаж", K_INFO to it.biography.alignment),
                //__________________________________________________
                hashMapOf(KEY_CATEGORY to "Работа"),
                hashMapOf(K_LABEL to "Род занятий", K_INFO to it.work.occupation),
                hashMapOf(K_LABEL to "База", K_INFO to it.work.base),
                //__________________________________________________
                hashMapOf(KEY_CATEGORY to "связи"),
                hashMapOf(K_LABEL to "Группа", K_INFO to it.connections.groupAffiliation),
                hashMapOf(K_LABEL to "Родственники", K_INFO to it.connections.relatives),

                )
        }
    }

    companion object {
        private const val KEY_CHARACTER = "KEY"
        fun newInstance(item: Character): DetailsFragment {
            val args = Bundle()
            args.putSerializable(KEY_CHARACTER, item)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }


    private fun getCharacterArgument(): Character? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(
                KEY_CHARACTER,
                Character::class.java
            )
        } else {
            requireArguments().getSerializable(KEY_CHARACTER) as Character
        }
    }

}