package com.example.musicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicplayer.ui.Routes
import com.example.musicplayer.ui.view.MusicPlayerView
import com.example.musicplayer.ui.view.SongLibraryView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Routes.SongLibraryView, builder = {

                composable (Routes.SongLibraryView)
                {
                    SongLibraryView(navController)
                }

                composable (Routes.MusicPlayerView + "/{musicName}")
                {
                    val musicName = it.arguments?.getString("musicName")

                    if (musicName != null)
                    {
                        MusicPlayerView(navController)
                    }
                }
            })
        }
    }
}