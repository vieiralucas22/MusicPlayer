package com.example.musicplayer.ui.viewmodel

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.example.musicplayer.service.MediaPlayerService
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MusicPlayerViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var mMediaPlayerService: MediaPlayerService
    private var mBounded: Boolean = false
    var mUri by mutableStateOf("")
    var mDuration by mutableStateOf("")
    var mCurrentPosition by mutableStateOf("")
    var mIsPlaying by mutableStateOf(true)
    var mIsMute by mutableStateOf(false)

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            var binder = service as MediaPlayerService.LocalBinder
            mMediaPlayerService = binder.getMediaPlayerService()
            mBounded = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBounded = false
        }
    }

    init {
        val intent = Intent(application.applicationContext, MediaPlayerService::class.java)
        application.bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    fun loadMusicToPlay() {
        if (mBounded) {
            mMediaPlayerService.play(application, mUri.toUri())
            mDuration = getFormattedDuration()

            updateElapseTime()
        }
    }

    fun setUri(encodedUri: String) {
        mUri = encodedUri
    }

    fun getFormattedDuration(): String {
        return formatMillisecondsToTime(mMediaPlayerService.getDuration())
    }

    fun getDuration(): Int {
        return mMediaPlayerService.getDuration() ?: 0
    }

    fun updateCurrentPosition()  {
        mCurrentPosition = formatMillisecondsToTime(mMediaPlayerService.getCurrentPosition())
    }

    fun getCurrentPosition() : Int {
        return mMediaPlayerService.getCurrentPosition() ?: 0
    }

    fun playOrPauseMusic(isPlaying: Boolean) {
        mIsPlaying = !isPlaying

        if (isPlaying)
            mMediaPlayerService.pauseMusic()
        else {
            mMediaPlayerService.startOrResumeMusic()
            updateElapseTime()
        }
    }

    fun handleWithToggleMuteAction()
    {
        if (mIsMute)
            mMediaPlayerService.unmutePlayer()
        else
            mMediaPlayerService.mutePlayer()

        mIsMute = !mIsMute
    }

    private fun formatMillisecondsToTime(timeInMilliseconds : Int?) : String
    {
        val totalSeconds = timeInMilliseconds?.div(1000)
        val minutes = totalSeconds?.div(60)
        val seconds = totalSeconds?.rem(60)
        return "%02d:%02d".format(minutes, seconds)
    }

    private fun updateElapseTime()
    {
        viewModelScope.launch {
            while (mIsPlaying)
            {
                updateCurrentPosition()
                delay(500)
            }
        }
    }
}