package com.az.yagp.presentation.di.component

import com.az.yagp.presentation.app.App
import com.az.yagp.presentation.di.module.*
import com.az.yagp.presentation.di.module.AppModule
import com.az.yagp.presentation.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 *Created by Zorin.A on 20.August.2020.
 */
@Singleton
@Component(
    modules =
    [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    abstract class AppComponentFactory : AndroidInjector.Factory<App>
}