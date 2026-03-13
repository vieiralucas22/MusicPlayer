package com.example.musicplayer.view.dialog

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.musicplayer.R
import com.example.musicplayer.viewmodel.dialog.AddMusicDialogViewModel

@Composable
fun AddMusicDialogView(
    addMusicDialogViewModel: AddMusicDialogViewModel
) {
    val context = LocalContext.current
    val musicPikerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {

            context.contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            addMusicDialogViewModel.addAMusicInLibrary(uri)
            addMusicDialogViewModel.closeDialog()
        }
    }

    if (addMusicDialogViewModel.isDialogOpen) {

        Dialog(onDismissRequest = { addMusicDialogViewModel.closeDialog() })
        {
            Column(
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.white),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = addMusicDialogViewModel.name,
                    onValueChange = {
                        addMusicDialogViewModel.name = it
                    },
                    label = { Text(text = "Music name") },
                    isError = addMusicDialogViewModel.name.isEmpty(),
                    colors = TextFieldDefaults.colors(
                        errorIndicatorColor = colorResource(R.color.errorColor),
                        errorContainerColor = colorResource(R.color.white),
                        unfocusedContainerColor = colorResource(R.color.white),
                        focusedContainerColor = colorResource(R.color.white),
                    )
                )

                HorizontalDivider(
                    Modifier.height(16.dp),
                )

                Button(modifier = Modifier.align(Alignment.End),onClick = {
                    if (addMusicDialogViewModel.name.isNotEmpty())
                        musicPikerLauncher.launch(arrayOf("audio/*"))
                }
                ) {
                    Text(text = "Add a music")
                }
            }
        }

    }
}