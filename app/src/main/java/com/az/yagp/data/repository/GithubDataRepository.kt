package com.az.yagp.data.repository

import com.az.yagp.data.api.GithubApi
import com.az.yagp.data.model.User
import com.az.yagp.data.model.UserDetails
import com.az.yagp.data.model.UserRepo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 *Created by Zorin.A on 21.August.2020.
 */
class GithubDataRepository @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository {
    override fun searchUsers(input: String): Observable<List<User>> {
        return githubApi.searchUsers(input).map { it.body()?.items ?: listOf() }
    }

    override fun getUser(userName: String): Single<UserDetails> {
        return githubApi.getUser(userName).map { it.body() }
    }

    override fun getUserRepos(userName: String): Single<List<UserRepo>> {
        return githubApi.getUserRepos(userName).map { it.body() }
    }
}