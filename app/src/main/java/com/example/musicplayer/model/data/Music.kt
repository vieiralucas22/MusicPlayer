package com.example.musicplayer.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(
    val id : Long,
    val title: String,
    val artist: String,
    val data: String,
    val albumId : Long
) : Parcelable
