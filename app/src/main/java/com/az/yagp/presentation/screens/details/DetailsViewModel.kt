package com.az.yagp.presentation.screens.details

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.az.yagp.data.model.UserDetails
import com.az.yagp.data.model.UserRepo
import com.az.yagp.data.repository.GithubRepository
import com.az.yagp.data.transformer.SingleAsyncTransformer
import com.az.yagp.presentation.base.BaseViewModel
import com.az.yagp.presentation.common.ViewState
import com.az.yagp.presentation.common.ViewState.State.ERROR
import com.az.yagp.presentation.common.ViewState.State.LOADING
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.plusAssign
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 *Created by Zorin.A on 25.August.2020.
 */
class DetailsViewModel @Inject constructor(
    private val repository: GithubRepository,
    router: Router
) : BaseViewModel(router) {
    private var userRepos = mutableListOf<UserRepo>()

    private val _resultLiveData = MutableLiveData<UserDetails>()
    val resultLiveData: LiveData<UserDetails> = _resultLiveData

    private val _reposLiveData = MutableLiveData<List<UserRepo>>()
    val reposLiveData: LiveData<List<UserRepo>> = _reposLiveData

    var userName: String? = null

    @SuppressLint("CheckResult")
    fun queryUser() {
        loadingLiveData.value = ViewState(LOADING)
        disposable += Single.zip(
            repository.getUser(userName ?: ""),
            repository.getUserRepos(userName ?: ""),
            BiFunction<UserDetails, List<UserRepo>, Pair<UserDetails, List<UserRepo>>> { t1, t2 ->
                Pair(t1, t2)
            }
        )
            .compose(SingleAsyncTransformer())
            .subscribe(
                {
                    loadingLiveData.value = ViewState(ViewState.State.IDLE)
                    _resultLiveData.value = it.first
                    userRepos.addAll(it.second)
                    _reposLiveData.value = userRepos
                },
                {
                    loadingLiveData.value = ViewState(ERROR)
                }
            )
    }

    fun filterRepos(input: String) {
        if (input.isEmpty()) {
            _reposLiveData.value = userRepos
        } else {
            val result = userRepos.filter { it.name.contains(input, ignoreCase = true) }
            _reposLiveData.value = result
        }
    }
}