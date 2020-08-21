package com.az.yagp.data.repository

import com.az.yagp.data.model.User
import io.reactivex.Observable
import retrofit2.http.Query

/**
 *Created by Zorin.A on 21.August.2020.
 */
interface GithubRepository {
    fun searchUsers(
        @Query("q") input: String
    ): Observable<List<User>>
}