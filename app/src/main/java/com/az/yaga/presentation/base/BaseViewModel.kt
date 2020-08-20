package com.az.yaga.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.az.yaga.presentation.common.ResponseState
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router

/**
 *Created by Zorin.A on 20.August.2020.
 */
abstract class BaseViewModel(protected val router: Router) : ViewModel() {
    var disposable: CompositeDisposable = CompositeDisposable()
    private val loadingLiveData = MutableLiveData<ResponseState>()
    fun getLoadingLiveData(): LiveData<ResponseState> = loadingLiveData

    fun onBackPressed() {
        router.exit()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}