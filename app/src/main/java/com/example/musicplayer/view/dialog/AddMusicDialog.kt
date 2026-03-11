package com.example.musicplayer.view.dialog

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.window.Dialog
import com.example.musicplayer.R
import com.example.musicplayer.Routes
import com.example.musicplayer.viewmodel.dialog.AddMusicDialogViewModel

@Composable
fun AddMusicDialogView(addMusicDialogViewModel: AddMusicDialogViewModel) {

    val musicPikerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            addMusicDialogViewModel.addAMusicInLibrary(uri)
            addMusicDialogViewModel.closeDialog()
        }
    }

    var name = addMusicDialogViewModel.name

    if (addMusicDialogViewModel.isDialogOpen) {

        Dialog(onDismissRequest = { addMusicDialogViewModel.closeDialog()} )
        {
            Column {
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

                Button(onClick = {musicPikerLauncher.launch(arrayOf("audio/*"))}) {
                    Text(text = "Add a music")
                }
            }
        }

    }
}