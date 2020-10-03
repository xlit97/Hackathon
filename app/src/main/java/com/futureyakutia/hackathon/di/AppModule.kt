package com.futureyakutia.hackathon.di

import com.futureyakutia.hackathon.appeal.Appeal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppeal(): Appeal = Appeal()
}