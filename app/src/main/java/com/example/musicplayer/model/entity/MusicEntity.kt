package com.example.musicplayer.model.entity

import android.net.Uri
import androidx.room.Entity

@Entity(tableName = "Music")
data class MusicEntity(
    val id : Int,
    val name: String,
    val uri: Uri
)
