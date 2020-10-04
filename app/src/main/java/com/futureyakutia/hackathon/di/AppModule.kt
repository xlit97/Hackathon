package com.futureyakutia.hackathon.di

import android.content.Context
import com.futureyakutia.hackathon.analytics.AnalyticsManagerImpl
import com.futureyakutia.hackathon.analytics.AnalyticsManager
import com.futureyakutia.hackathon.appeal.Appeal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppeal(): Appeal = Appeal()

    @Provides
    @Singleton
    fun provideAnalyticsManager(@ApplicationContext context: Context): AnalyticsManager = AnalyticsManagerImpl(context)
}