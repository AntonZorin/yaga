package com.az.yagp.presentation.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.az.yagp.data.model.UserDetails
import com.az.yagp.data.repository.GithubRepository
import com.az.yagp.data.transformer.SingleAsyncTransformer
import com.az.yagp.presentation.base.BaseViewModel
import com.az.yagp.presentation.common.ViewState
import com.az.yagp.presentation.common.ViewState.State.*
import io.reactivex.rxkotlin.plusAssign
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Zorin.A on 25.August.2020.
 */
class DetailsViewModel @Inject constructor(
    private val repository: GithubRepository,
    router: Router
) : BaseViewModel(router) {
    private val _resultLiveData = MutableLiveData<UserDetails>()
    val resultLiveData: LiveData<UserDetails> = _resultLiveData

    var userName: String? = null

    fun queryUser() {
        loadingLiveData.value = ViewState(LOADING)
        disposable += repository.getUser(userName ?: "")
            .compose(SingleAsyncTransformer())
            .subscribe(
                {
                    loadingLiveData.value = ViewState(IDLE)
                    _resultLiveData.value = it
                    Timber.d("USER DETAILS: $it")
                },
                {
                    loadingLiveData.value = ViewState(ERROR)
                }
            )
    }
}