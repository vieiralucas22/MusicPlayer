package com.example.musicplayer.model.repository

import com.example.musicplayer.model.entity.MusicEntity
import kotlinx.coroutines.flow.Flow

interface IMusicRepository {

    suspend fun insertMusic(entity: MusicEntity)

    fun getAllMusics() : Flow<List<MusicEntity>>

    suspend fun getMusicById(id : Int) : MusicEntity

}