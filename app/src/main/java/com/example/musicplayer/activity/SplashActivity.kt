package com.example.musicplayer.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.musicplayer.view.SplashScreenView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreenView(onStartClick = {
                startActivity(Intent(this, MusicLibraryActivity::class.java))
                finish()
            })
        }
    }
}