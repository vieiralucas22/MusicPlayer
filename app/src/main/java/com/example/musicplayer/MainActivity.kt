package com.example.musicplayer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

        requestNotificationPermission()

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

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    100
                )
            }
        }
    }
}