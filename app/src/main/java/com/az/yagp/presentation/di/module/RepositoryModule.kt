package com.az.yagp.presentation.di.module

import com.az.yagp.data.repository.GithubDataRepository
import com.az.yagp.data.repository.GithubRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 *Created by Zorin.A on 21.August.2020.
 */
@Module
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun provideGitHubRepository(p: GithubDataRepository): GithubRepository
}