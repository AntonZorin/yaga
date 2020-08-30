package com.az.yagp.data.api

import com.az.yagp.data.model.UserDetails
import com.az.yagp.data.model.UserRepo
import com.az.yagp.data.model.UserSearchResult
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *Created by Zorin.A on 20.August.2020.
 */
interface GithubApi {
    @GET("search/users")
    fun searchUsers(@Query("q") input: String): Observable<Response<UserSearchResult>>

    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user: String): Single<Response<List<UserRepo>>>

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Single<Response<UserDetails>>
}