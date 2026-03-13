package com.example.musicplayer.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.musicplayer.R
import com.example.musicplayer.Routes
import com.example.musicplayer.view.dialog.AddMusicDialogView
import com.example.musicplayer.viewmodel.SongLibraryViewModel
import com.example.musicplayer.viewmodel.dialog.AddMusicDialogViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SongLibraryView(
    navController: NavHostController,
    addMusicDialogViewModel: AddMusicDialogViewModel,
    songLibraryViewModel: SongLibraryViewModel
) {
    val allMusicsInLibrary by songLibraryViewModel
        .allMusics
        .collectAsStateWithLifecycle(initialValue = emptyList())

    Scaffold(
        floatingActionButton =
            {
                FloatingActionButton(
                    onClick = {
                        addMusicDialogViewModel.openDialog()
                    },
                    containerColor = colorResource(R.color.orange)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_music),
                        contentDescription = "Icon",
                        tint = colorResource(R.color.white)
                    )
                }
            }) {

        Box {

            Image(
                painter = painterResource(R.drawable.intro_pic),
                contentDescription = null,
                modifier = Modifier
                        .fillMaxSize()
                        .blur(10.dp)
                        .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(24.dp, 48.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = "Library musics! Choose one.",
                    color = colorResource(R.color.white),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                VerticalDivider(Modifier.height(16.dp), color = colorResource(R.color.transparent))

                allMusicsInLibrary.let { musics ->
                    if (musics.isNotEmpty()) {
                        LazyColumn(content = {
                            itemsIndexed(musics) { index, music ->
                                MusicItem(
                                    music.name, onClick =
                                        {
                                            navController.navigate(Routes.MusicPlayerView + "/" + music.uri)
                                        })
                            }
                        })
                    } else {
                        EmptyLibrary()
                    }
                }
            }

            AddMusicDialogView(addMusicDialogViewModel)
        }
    }
}

@Composable
fun MusicItem(musicName: String = "", onClick: (Int) -> Unit = {}, index: Int = 0) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(index) })
            .background(colorResource(R.color.white_80), shape = RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_music),
            contentDescription = null,
            tint = colorResource(R.color.orange)
        )

        HorizontalDivider(Modifier.width(8.dp), color = colorResource(R.color.transparent))

        Text(
            text = musicName,
            fontSize = 18.sp,
            color = colorResource(R.color.orange),
            fontWeight = FontWeight.SemiBold
        )
    }

    Divider(Modifier.height(8.dp), color = colorResource(R.color.transparent))
}

@Composable
fun EmptyLibrary() {
    Text(
        text = "No musics in your library yet! \n Click on the below button to add new musics!",
        fontSize = 16.sp,
        color = colorResource(R.color.white),
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )
}