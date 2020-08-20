package com.az.yagp.presentation.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton


@Module
internal object AppModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    @JvmStatic
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
        val holder by lazy {
            cicerone.navigatorHolder
        }
        return holder
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideCicerone(): Cicerone<Router> {
        val cicerone by lazy {
            Cicerone.create()
        }
        return cicerone
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideRouter(cicerone: Cicerone<Router>): Router {
        val router by lazy {
            cicerone.router
        }
        return router
    }

    @Singleton
    @Provides
    @JvmStatic
    fun providePrefs(context: Context): SharedPreferences {
        val prefs by lazy {
            PreferenceManager.getDefaultSharedPreferences(context)
        }
        return prefs
    }
}