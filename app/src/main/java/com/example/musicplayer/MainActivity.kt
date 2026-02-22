package com.example.musicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicplayer.ui.Routes
import com.example.musicplayer.ui.view.MusicPlayerView
import com.example.musicplayer.ui.view.SongLibraryView
import com.example.musicplayer.ui.viewmodel.MusicPlayerViewModel
import com.example.musicplayer.ui.viewmodel.SongLibraryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val musicPlayerViewModel : MusicPlayerViewModel = viewModel()
            val songLibraryViewModel : SongLibraryViewModel = viewModel()

            NavHost(navController = navController, startDestination = Routes.SongLibraryView, builder = {

                composable (Routes.SongLibraryView)
                {
                    SongLibraryView(navController)
                }

                composable (Routes.MusicPlayerView + "/{uri}")
                {
                    val uri = it.arguments?.getString("uri")

                    if (uri != null)
                    {
                        musicPlayerViewModel.setUri(uri)
                        MusicPlayerView(navController, musicPlayerViewModel)
                    }
                }
            })
        }
    }
}