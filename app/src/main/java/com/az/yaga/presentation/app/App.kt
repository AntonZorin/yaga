package com.az.yaga.presentation.app

import android.app.Application
import com.az.yaga.BuildConfig
import com.az.yaga.presentation.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Zorin.A on 20.August.2020.
 */
class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    //region override
    override fun onCreate() {
        super.onCreate()
        initTimber()
        DaggerAppComponent.factory()
            .create(this)
            .inject(this)
    }
    //endregion

    //region fun
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
    //endregion
}