package com.example.musicplayer.viewmodel.dialog

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayer.model.entity.MusicEntity
import com.example.musicplayer.model.repository.IMusicRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddMusicDialogViewModel @Inject constructor(application: Application, private val mMusicRepository: IMusicRepository) :
    AndroidViewModel(application) {

    var isDialogOpen by mutableStateOf(false)
    var name by mutableStateOf("")

    fun openDialog() {
        isDialogOpen = true
    }

    fun closeDialog() {
        isDialogOpen = false
        name = ""
    }

    fun addAMusicInLibrary(uri : Uri)
    {
        viewModelScope.launch {
            val entity = MusicEntity(name = name, uri = uri)
            mMusicRepository.insertMusic(entity)
        }
    }

}