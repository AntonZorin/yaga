package com.az.yagp.presentation.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.az.yagp.data.model.User
import com.az.yagp.data.repository.GithubRepository
import com.az.yagp.data.transformer.ObservableAsyncTransformer
import com.az.yagp.presentation.base.BaseViewModel
import com.az.yagp.presentation.common.ViewState
import com.az.yagp.presentation.screens.Screens
import io.reactivex.rxkotlin.plusAssign
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 *Created by Zorin.A on 20.August.2020.
 */
class SearchViewModel @Inject constructor(
    router: Router,
    private val repository: GithubRepository
) : BaseViewModel(router) {
    private var inputCache: String = ""

    private val _resultLiveData = MutableLiveData<List<User>>()
    val resultLiveData: LiveData<List<User>> = _resultLiveData

    fun performSearch(input: String) {
        inputCache = input
        loadingLiveData.value = ViewState(ViewState.State.LOADING)
        disposable += repository.searchUsers(input)
            .compose(ObservableAsyncTransformer())
            .subscribe(
                {
                    loadingLiveData.value = ViewState(ViewState.State.IDLE)
                    _resultLiveData.value = it
                },
                {
                    loadingLiveData.value = ViewState(ViewState.State.ERROR)
                }
            )
    }

    fun retrySearch() {
        performSearch(inputCache)
    }

    fun onUserSelected(it: User) {
        router.navigateTo(Screens.DetailsScreen(it.login))
    }
}