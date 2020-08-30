package com.az.yagp.data.repository

import com.az.yagp.data.model.User
import com.az.yagp.data.model.UserDetails
import com.az.yagp.data.model.UserRepo
import io.reactivex.Observable
import io.reactivex.Single

/**
 *Created by Zorin.A on 21.August.2020.
 */
interface GithubRepository {
    fun searchUsers(
        input: String
    ): Observable<List<User>>

    fun getUser(userName: String): Single<UserDetails>
    fun getUserRepos(userName: String): Single<List<UserRepo>>
}