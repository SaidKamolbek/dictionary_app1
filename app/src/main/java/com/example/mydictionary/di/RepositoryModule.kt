package com.example.mydictionary.di

import com.example.mydictionary.domain.DashboardRepository
import com.example.mydictionary.domain.impl.DashboardRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDashboardRepository(impl: DashboardRepositoryImpl): DashboardRepository

}