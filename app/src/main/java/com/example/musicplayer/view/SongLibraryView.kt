package com.example.musicplayer.view

import android.Manifest
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.musicplayer.R
import com.example.musicplayer.Routes
import com.example.musicplayer.model.data.Music
import com.example.musicplayer.util.SongUtils
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun SongLibraryView(navController: NavHostController) {
    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val musicPikerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenDocument()
            ) { uri ->
                uri?.let {
                    navController.navigate(Routes.MusicPlayerView + "/" + Uri.encode(uri.toString()))
                }
            }

            Button(onClick = {
                musicPikerLauncher.launch(arrayOf("audio/*"))
            }) {
                Text(text = "Select a music")
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SongLibraryView2(onSongClick: (songs: List<Music>, position: Int) -> Unit) {

    val context = LocalContext.current
    val songState = remember { mutableStateOf<List<Music>>(emptyList()) }
    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val permissionState = rememberPermissionState(permission)

    LaunchedEffect(permissionState.status) {
        if (permissionState.status.isGranted) {
            songState.value = SongUtils.getSongs(context)
        }
    }

    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Explore Artist",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 44.dp, end = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            if (!permissionState.status.isGranted) {
                Button(
                    onClick = { permissionState.launchPermissionRequest() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                {
                    Text(text = "Request permission to Musics")
                }
            }

            SongList(
                songs = songState.value,
                onSongClick = { pos -> onSongClick(songState.value, pos) },
                modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun SongList(songs: List<Music>, onSongClick: (Int) -> Unit, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        itemsIndexed(songs)
        { index, song ->
            SongItem(song, onClick = { onSongClick(index) })
        }
    }

}

@Composable
fun SongItem(song: Music, onClick: () -> Unit) {

    val albumArtUri = ContentUris.withAppendedId(
        Uri.parse("content://media/external/audio/albumart"),
        song.albumId
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
            .height(72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = albumArtUri,
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0x33000000)),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_music),
            placeholder = painterResource(R.drawable.ic_music),
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = song.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = song.artist,
                color = Color(0xffbbbbbb),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

