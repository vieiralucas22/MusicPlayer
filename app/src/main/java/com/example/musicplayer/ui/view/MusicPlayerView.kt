package com.example.musicplayer.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.musicplayer.R
import com.example.musicplayer.ui.theme.LightGray
import com.example.musicplayer.ui.viewmodel.MusicPlayerViewModel

@Composable
fun MusicPlayerView(navController: NavHostController, musicPlayerViewModel: MusicPlayerViewModel) {

    LaunchedEffect(Unit) {
        musicPlayerViewModel.loadMusicToPlay()
    }

    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderView(navController, musicPlayerViewModel)

            Image(
                painter = painterResource(R.drawable.live_music),
                contentDescription = "Music Image",
                modifier = Modifier.size(200.dp)
            )

            MediaPlayerControlView(musicPlayerViewModel)
        }
    }
}

@Composable
fun HeaderView(navController: NavHostController, musicPlayerViewModel: MusicPlayerViewModel) {
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

        IconButton(
            onClick = { musicPlayerViewModel.handleWithToggleMuteAction() },
            Modifier.background(
                if (musicPlayerViewModel.mIsMute)
                    LightGray
                else
                    Color.Transparent, shape = RoundedCornerShape(99.dp)
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mute_music),
                contentDescription = "Mute music button"
            )
        }
    }
}

@Composable
fun TransportBarView(musicPlayerViewModel: MusicPlayerViewModel) {
    Column(
        modifier = Modifier
            .padding(12.dp, 0.dp)
            .fillMaxWidth()
    ) {

        Slider(
            value = musicPlayerViewModel.mCurrentSliderPosition.toFloat(),
            onValueChange = {
                musicPlayerViewModel.pauseMusic()
                musicPlayerViewModel.updateCurrentPosition(it.toInt())
            },
            onValueChangeFinished = {
                musicPlayerViewModel.seek(musicPlayerViewModel.mCurrentSliderPosition)
                musicPlayerViewModel.playMusic()
            },
            valueRange = 0f..musicPlayerViewModel.getDuration().toFloat(),
            modifier = Modifier.height(2.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 12.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(text = musicPlayerViewModel.mCurrentPositionFormatted)

            Text(text = musicPlayerViewModel.mDuration, textAlign = TextAlign.End)
        }

    }
}

@Composable
fun MediaPlayerControlView(musicPlayerViewModel: MusicPlayerViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 36.dp)
    ) {
        TransportBarView(musicPlayerViewModel)

        if (musicPlayerViewModel.mIsPlaying) {
            PlayPauseButton(
                onClick = { musicPlayerViewModel.pauseMusic() },
                resourceId = R.drawable.ic_pause,
                "Pause Button"
            )
        } else {
            PlayPauseButton(
                onClick = { musicPlayerViewModel.playMusic() },
                resourceId = R.drawable.ic_play,
                "Play Button"
            )
        }
    }
}

@Composable
fun PlayPauseButton(onClick: () -> Unit, resourceId: Int, description: String) {
    IconButton(onClick = onClick, modifier = Modifier.width(64.dp))
    {
        Icon(
            painter = painterResource(resourceId),
            contentDescription = description,
            modifier = Modifier.size(48.dp)
        )
    }
}