package com.example.musicplayer.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.musicplayer.R
import com.example.musicplayer.model.data.Music
import com.example.musicplayer.view.PlayerScreen

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mySongList = intent.getParcelableArrayListExtra("songList") ?: emptyList<Music>()
        val initialIndex = intent.getIntExtra("position", 0)
        setContent {
            PlayerScreen(songList = mySongList, initialIndex = initialIndex, onBack = { finish() })
        }
    }
}