package com.az.yaga.presentation.di.module

import android.content.Context
import android.content.SharedPreferences
import com.az.yaga.data.api.Api
import com.az.yaga.data.api.interceptor.AuthInterceptor
import com.az.yaga.presentation.common.BASE_URL
import com.az.yaga.presentation.common.DISK_CACHE_SIZE
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
internal object NetworkModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideOkHttpClient(context: Context,
                            authInterceptor: AuthInterceptor,
                            httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client by lazy {
            OkHttpClient
                    .Builder()
                    .addInterceptor(authInterceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .retryOnConnectionFailure(true)
                    .cache(Cache(File(context.cacheDir, "fishing_http_cache"), DISK_CACHE_SIZE))
                    .build()
        }
        return client
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor by lazy {
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { log ->
                Timber.tag("OK_HTTP").d(log)
            })
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideApiService(retrofit: Retrofit): Api {
        val service by lazy { retrofit.create(Api::class.java) }
        return service
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideAuthInterceptor(prefs: SharedPreferences): AuthInterceptor {
        val interceptor by lazy {
            AuthInterceptor(prefs)
        }
        return interceptor
    }
}


