package com.example.myvideoplayer.di

import android.content.Context
import com.example.myvideoplayer.repo.VideoFilesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRepo(
        @ApplicationContext context:Context
    ): VideoFilesRepo {
        return VideoFilesRepo(context)
    }

}