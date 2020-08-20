package com.az.yaga.presentation.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.az.yaga.presentation.app.AppViewModel
import com.az.yaga.presentation.common.ViewModelFactory
import com.az.yaga.presentation.di.annotation.ViewModelKey
import com.az.yaga.presentation.screens.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AppViewModel::class)
    fun bindAppViewModel(viewModel: AppViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel


}