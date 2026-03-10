package com.example.musicplayer.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicplayer.model.dao.MusicDao
import com.example.musicplayer.model.entity.MusicEntity

@Database(entities=[MusicEntity::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun getMusicDao(): MusicDao

}