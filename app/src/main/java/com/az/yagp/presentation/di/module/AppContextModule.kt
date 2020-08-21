package com.az.yagp.presentation.di.module

import android.content.Context
import com.az.yagp.presentation.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *Created by Zorin.A on 21.August.2020.
 */
@Module
object AppContextModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(app: App): Context = app
}