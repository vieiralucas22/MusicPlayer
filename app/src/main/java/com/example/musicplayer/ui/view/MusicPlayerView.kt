package com.example.musicplayer.ui.view

import android.widget.ToggleButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicplayer.R

@Composable
fun MusicPlayerView() {

    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderView()

            Image(
                painter = painterResource(R.drawable.live_music),
                contentDescription = "Music Image",
                modifier = Modifier.size(200.dp)
            )

            Button(onClick = {}) {
                Text(text = "hi")
            }

        }
    }
}

@Composable
fun HeaderView() {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                println("Clicou!")
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