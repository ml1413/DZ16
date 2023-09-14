package com.example.dz16.request

import android.util.Log
import com.example.dz16.models.Character
import com.example.dz16.utils.onOffProgressBar
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

lateinit var disposable: Disposable


fun requestApi(
    single: Single<List<Character>>,
    callback: (result: Any) -> Unit
) {
    onOffProgressBar()
    disposable = single
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ callback(it);onOffProgressBar() },
            { Log.d("TAG1", "onCreate: ${it.message}") })
}
