package com.az.yagp.presentation.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.az.yagp.presentation.app.AppViewModel
import com.az.yagp.presentation.common.ViewModelFactory
import com.az.yagp.presentation.di.annotation.ViewModelKey
import com.az.yagp.presentation.screens.details.DetailsViewModel
import com.az.yagp.presentation.screens.search.SearchViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}