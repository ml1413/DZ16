package com.example.dz16

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.dz16.adapter.ClickItem
import com.example.dz16.adapter.AdapterForAllHolder
import com.example.dz16.databinding.ActivityMainBinding
import com.example.dz16.models.Character
import com.example.dz16.request.ApiClient
import com.example.dz16.request.ApiInterface
import com.example.dz16.utils.CHARACTER_CLASS
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), ClickItem {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var disposable: Disposable
    private lateinit var client: ApiInterface
   private lateinit var adapter: AdapterForAllHolder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        initField()
        requestApi(single = client.getAllHero()) { setListInAdapter(listItem = it as List<Character>) }
        binding.recyclerView.adapter = adapter
    }

    private fun setListInAdapter(listItem: List<Character>) {
        adapter.setList(listItem)
    }

    private fun initField() {
        client = ApiClient.retrofit.create(ApiInterface::class.java)
        adapter = AdapterForAllHolder(clickItem = this,R.layout.item_hero)
    }

    private fun requestApi(
        single: Single<List<Character>>,
        callback: (result: Any) -> Unit
    ) {
        disposable = single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback(it);binding.progressBar.isVisible = false },
                { Log.d("TAG1", "onCreate: ${it.message}") })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun getModel(item: Character) {
        val intent = Intent(this, Details::class.java)
            .putExtra(CHARACTER_CLASS, item)

        startActivity(intent)
    }

}
