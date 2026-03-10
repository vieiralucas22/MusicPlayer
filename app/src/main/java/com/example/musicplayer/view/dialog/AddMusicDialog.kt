package com.example.musicplayer.view.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.window.Dialog
import com.example.musicplayer.R

@Composable
fun AddMusicDialogView() {
    Dialog(onDismissRequest = { })
    {
        Column {

            var name by remember { mutableStateOf("") }

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = { Text(text = "Music name") },
                isError = name.isEmpty(),
                colors = TextFieldDefaults.colors(
                    errorIndicatorColor = colorResource(R.color.errorColor),
                    errorContainerColor = colorResource(R.color.white),
                    unfocusedContainerColor = colorResource(R.color.white),
                    focusedContainerColor = colorResource(R.color.white),
                )
            )
        }

    }
}