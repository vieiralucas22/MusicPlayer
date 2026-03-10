package com.example.musicplayer.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.musicplayer.R
import com.example.musicplayer.Routes

@Composable
fun SongLibraryView(navController: NavHostController) {

    val musicPikerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            navController.navigate(Routes.MusicPlayerView + "/" + Uri.encode(uri.toString()))
        }
    }

    Scaffold(
        floatingActionButton =
            {
                FloatingActionButton(
                    onClick = { musicPikerLauncher.launch(arrayOf("audio/*")) },
                    containerColor = colorResource(R.color.orange)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_music),
                        contentDescription = "Icon",
                        tint = colorResource(R.color.white)
                    )
                }
            }) { paddingValues ->

        Box {

            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "No musics in your library yet! \n Click on the below button to add new musics!",
                    fontSize = 16.sp,
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}