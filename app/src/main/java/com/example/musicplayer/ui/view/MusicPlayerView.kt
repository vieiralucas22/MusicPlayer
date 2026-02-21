package com.example.musicplayer.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.musicplayer.R
import com.example.musicplayer.ui.Routes

@Composable
fun MusicPlayerView(navController: NavHostController) {

    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderView(navController)

            Image(
                painter = painterResource(R.drawable.live_music),
                contentDescription = "Music Image",
                modifier = Modifier.size(200.dp)
            )

            MediaPlayerControlView()
        }
    }
}

@Composable
fun HeaderView(navController: NavHostController) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_left),
                contentDescription = "Back button"
            )
        }

        Text(text = "Music name")

        IconButton(
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mute_music),
                contentDescription = "Mute music button"
            )
        }
    }
}

@Composable
fun TransportBarView() {
    Column(
        modifier = Modifier
            .padding(12.dp, 0.dp)
            .fillMaxWidth()
    ) {

        Slider(
            value = 50f,
            onValueChange = { },
            valueRange = 0f..100f,
            modifier = Modifier.height(2.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(0.dp, 12.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(text = "00:53")

            Text(text = "04:00", textAlign = TextAlign.End)
        }

    }
}

@Composable
fun MediaPlayerControlView()
{
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 36.dp)){
        TransportBarView()

        IconButton(onClick = {}, modifier = Modifier.width(64.dp))
        {
            Icon(painter = painterResource(R.drawable.ic_play), contentDescription = "Play button", modifier = Modifier.size(48.dp))
        }
    }
}