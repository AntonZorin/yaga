package com.az.yagp.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.az.yagp.presentation.common.ViewState
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router

/**
 *Created by Zorin.A on 20.August.2020.
 */
abstract class BaseViewModel(protected val router: Router) : ViewModel() {
    var disposable: CompositeDisposable = CompositeDisposable()
    protected val loadingLiveData = MutableLiveData<ViewState>()
    fun getLoadingLiveData(): LiveData<ViewState> = loadingLiveData

    fun onBackPressed() {
        router.exit()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}