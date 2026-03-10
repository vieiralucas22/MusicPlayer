package com.example.musicplayer.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicplayer.model.ApplicationDatabase
import com.example.musicplayer.model.dao.MusicDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) : ApplicationDatabase
    {
        return Room.databaseBuilder(
            context,
            ApplicationDatabase::class.java,
            "MusicPlayerDatabase"
        ).build()
    }

    @Provides
    fun providesMusicDao(db: ApplicationDatabase) : MusicDao
    {
        return db.getMusicDao()
    }

}