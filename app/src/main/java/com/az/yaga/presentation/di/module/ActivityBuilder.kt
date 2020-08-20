package com.az.yaga.presentation.di.module

import com.az.yaga.presentation.app.AppActivity
import com.az.yaga.presentation.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    fun contributeMainActivity(): AppActivity
}