package com.example.musicplayer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.musicplayer.model.repository.IMusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongLibraryViewModel @Inject constructor(
    application: Application,
    private val mMusicRepository: IMusicRepository
) : AndroidViewModel(application) {

    val allMusics = mMusicRepository.getAllMusics()

}