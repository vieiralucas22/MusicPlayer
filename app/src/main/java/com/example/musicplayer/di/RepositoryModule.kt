package com.example.musicplayer.di

import com.example.musicplayer.model.repository.IMusicRepository
import com.example.musicplayer.model.repository.MusicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsMusicRepository(impl: MusicRepositoryImpl) : IMusicRepository

}