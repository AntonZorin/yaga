package com.az.yagp.presentation.di.module

import com.az.yagp.presentation.screens.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {
    @ContributesAndroidInjector
    fun contributeSearchFragment(): SearchFragment
}
