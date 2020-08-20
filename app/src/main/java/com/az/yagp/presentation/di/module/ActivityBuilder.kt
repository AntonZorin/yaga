package com.az.yagp.presentation.di.module

import com.az.yagp.presentation.app.AppActivity
import com.az.yagp.presentation.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    fun contributeMainActivity(): AppActivity
}