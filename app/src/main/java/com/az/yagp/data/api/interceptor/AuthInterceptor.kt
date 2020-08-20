package com.az.yagp.data.api.interceptor

import android.content.SharedPreferences
import com.az.yagp.presentation.common.ACCESS_TOKEN
import com.az.yagp.presentation.common.string
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by zorin.a on 01.04.2018.
 */
class AuthInterceptor @Inject constructor(prefs: SharedPreferences) : Interceptor {
    private val token: String? by prefs.string(key = ACCESS_TOKEN)
    override fun intercept(chain: Interceptor.Chain): Response? {

        val original = chain.request()
        val request = original.newBuilder()
            .header("Authorization", "Bearer $token")
            .addHeader("Accept", "application/vnd.github.v3+json")
            .build()
        return chain.proceed(request)
    }
}