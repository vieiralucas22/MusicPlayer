package com.example.musicplayer.model.repository

import com.example.musicplayer.model.dao.MusicDao
import com.example.musicplayer.model.entity.MusicEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(private val musicDao: MusicDao) : IMusicRepository {
    override suspend fun insertMusic(entity: MusicEntity) {
        musicDao.insertMusic(entity)
    }

    override fun getAllMusics(): Flow<List<MusicEntity>> {
        return musicDao.getAllMusic()
    }

    override suspend fun getMusicById(id: Int): MusicEntity {
        return musicDao.getMusicById(id)
    }
}