package com.example.dz16

import android.os.Build
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.dz16.adapter.AdapterForAllHolder
import com.example.dz16.adapter.ClickItem
import com.example.dz16.databinding.ActivityDetailsBinding
import com.example.dz16.models.Character
import com.example.dz16.utils.CHARACTER_CLASS
import com.example.dz16.utils.KEY_CATEGORY
import com.example.dz16.utils.K_INFO
import com.example.dz16.utils.K_LABEL
import com.example.dz16.utils.setImage

class Details : AppCompatActivity(), ClickItem {
    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    private var character: Character? = null
    private lateinit var adapter: AdapterForAllHolder
    private lateinit var listInfo: List<HashMap<String, String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initField()

        binding.recyclerView.adapter = adapter


        character?.let {
            binding.ivDetails.setImage(it.images.md, false, binding.ivBackground)
            binding.ivDetails.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha))
            binding.tvNameCardView.text = it.name
        }


    }


    private fun initField() {


        //вытягиваем из intents character
        intent?.let {
            character = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializableExtra(CHARACTER_CLASS, Character::class.java)
            } else {
                it.getSerializableExtra(CHARACTER_CLASS) as Character
            }
        }
        // получаем список всей онформации про героя
        getListInfo()

        //иницыализируем адаптэр передаем в конструктор лист
        adapter = AdapterForAllHolder(this@Details, R.layout.item_details)
        adapter.setList(listInfo)
    }


    override fun getModel(item: Character) {

    }

    private fun getListInfo() {
        character?.let {

            listInfo = listOf(
                hashMapOf(KEY_CATEGORY to "статистика"),
                hashMapOf(K_LABEL to "Интеллект", K_INFO to it.powerstats.intelligence),
                hashMapOf(K_LABEL to "Сила", K_INFO to it.powerstats.strength),
                hashMapOf(K_LABEL to "Скорость", K_INFO to it.powerstats.speed),
                hashMapOf(K_LABEL to "Долговечность", K_INFO to it.powerstats.durability),
                hashMapOf(K_LABEL to "Сила", K_INFO to it.powerstats.power),
                hashMapOf(K_LABEL to "Бой", K_INFO to it.powerstats.combat),
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
}