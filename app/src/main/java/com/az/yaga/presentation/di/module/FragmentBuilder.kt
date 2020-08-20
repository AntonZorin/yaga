package com.az.yaga.presentation.di.module

import com.az.yaga.presentation.screens.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {
    @ContributesAndroidInjector
    fun contributeSearchFragment(): SearchFragment
}
