package com.az.yagp.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *Created by Zorin.A on 20.August.2020.
 */
interface Api {
    @GET("search/users")
    fun searchUsers(@Query("q") input: String)

    @GET("{user}/repos")
    fun getUserRepos(@Path("user") user: String)
}