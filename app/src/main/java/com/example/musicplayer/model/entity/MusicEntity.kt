package com.example.musicplayer.model.entity

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Music")
data class MusicEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id : Long = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val uri: String
)
