package com.example.musicplayer.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicplayer.R

@Composable
fun SplashView(onStartClick: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(R.drawable.intro_pic), contentDescription = null, modifier =
                Modifier
                    .fillMaxSize().blur(5.dp)
                    .align(Alignment.TopCenter), contentScale = ContentScale.Crop
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            Column {
                Text(
                    text = "Player",
                    fontSize = 60.sp,
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.SemiBold
                )
                Row {
                    Text(
                        text = "that",
                        fontSize = 60.sp,
                        color = colorResource(R.color.white),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = " plays",
                        fontSize = 60.sp,
                        color = colorResource(R.color.orange),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Text(
                    text = "All",
                    fontSize = 60.sp,
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.SemiBold
                )
            }

            Button(
                onClick = onStartClick,
                modifier = Modifier
                    .padding(bottom = 64.dp)
                    .align(Alignment.BottomEnd)
                    .size(75.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.orange)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    tint = colorResource(R.color.white),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}