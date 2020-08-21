package com.az.yagp.data.repository

import com.az.yagp.data.api.GithubApi
import com.az.yagp.data.model.User
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 *Created by Zorin.A on 21.August.2020.
 */
class GithubDataRepository @Inject constructor(
    private val githubApi: GithubApi
) :
    GithubRepository {
    override fun searchUsers(input: String): Observable<List<User>> {
        return githubApi.searchUsers(input).map { it.body()?.items ?: listOf() }
    }
}