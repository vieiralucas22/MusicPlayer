package com.example.musicplayer.view

import android.window.SplashScreenView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicplayer.R

@Composable
fun SplashScreenView(onStartClick: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(R.drawable.intro_pic), contentDescription = null, modifier =
                Modifier.fillMaxSize().align(Alignment.TopCenter), contentScale = ContentScale.Crop
        )

        Button(
            onClick = onStartClick,
            modifier = Modifier
                .padding(bottom = 48.dp, start = 24.dp, end = 24.dp)
                .align(Alignment.BottomCenter)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.orange)
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(
                text = stringResource(R.string.get_started),
                fontSize = 20.sp,
                color = colorResource(R.color.white),
                fontWeight = FontWeight.Bold
            )
        }
    }
}