package com.example.musicplayer.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.musicplayer.model.entity.MusicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Insert
    suspend fun insertMusic(entity: MusicEntity)

    @Query("Select * from Music")
    fun getAllMusic() : Flow<List<MusicEntity>>

    @Query("Select * from Music where id = :id")
    suspend fun getMusicById(id : Int) : MusicEntity
}