package com.az.yagp.presentation.di.module

import android.content.Context
import com.az.yagp.data.api.GithubApi
import com.az.yagp.data.api.interceptor.AuthInterceptor
import com.az.yagp.presentation.common.BASE_URL
import com.az.yagp.presentation.common.DISK_CACHE_SIZE
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import javax.inject.Singleton


@Module
object ApiModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(
        context: Context,
        authInterceptor: AuthInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .retryOnConnectionFailure(true)
            .cache(Cache(File(context.cacheDir, "http_cache"), DISK_CACHE_SIZE))
            .build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { log ->
            Timber.tag("OK_HTTP").d(log)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideApiService(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideAuthInterceptor(): AuthInterceptor {

        return AuthInterceptor()
    }
}

