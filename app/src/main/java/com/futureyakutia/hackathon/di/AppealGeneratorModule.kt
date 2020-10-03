package com.futureyakutia.hackathon.di

import android.content.Context
import com.futureyakutia.hackathon.appeal.Appeal
import com.futureyakutia.hackathon.appeal.generator.AppealGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(FragmentComponent::class)
object AppealGeneratorModule {

    @Provides
    fun provideAppealGenerator(appeal: Appeal, @ApplicationContext context: Context) = AppealGenerator(appeal, context)
}