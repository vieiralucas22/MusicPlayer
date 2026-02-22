package com.example.musicplayer.ui.viewmodel

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
    var mCurrentPositionFormatted by mutableStateOf("")
    var mCurrentSliderPosition by mutableIntStateOf(0)
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
            loadMusicToPlay()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBounded = false
        }
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
        return formatMillisecondsToTime(getDuration())
    }

    fun getDuration(): Int {
        if (!mBounded)
            return 0

        return mMediaPlayerService.getDuration() ?: 0
    }

    fun updateCurrentPosition(newPosition: Int) {
        mCurrentPositionFormatted = formatMillisecondsToTime(newPosition)
        mCurrentSliderPosition = newPosition
    }

    fun getCurrentPosition(): Int {
        if (!mBounded)
            return 0

        return mMediaPlayerService.getCurrentPosition() ?: 0
    }

    fun playMusic() {
        if (!mBounded)
            return

        mIsPlaying = true
        mMediaPlayerService.startOrResumeMusic()
        updateElapseTime()
    }

    fun pauseMusic() {
        if (!mBounded)
            return

        mIsPlaying = false
        mMediaPlayerService.pauseMusic()
    }

    fun handleWithToggleMuteAction() {
        if (!mBounded)
            return

        if (mIsMute)
            mMediaPlayerService.unmutePlayer()
        else
            mMediaPlayerService.mutePlayer()

        mIsMute = !mIsMute
    }

    private fun formatMillisecondsToTime(timeInMilliseconds: Int): String {
        val totalSeconds = timeInMilliseconds.div(1000)
        val minutes = totalSeconds.div(60)
        val seconds = totalSeconds.rem(60)
        return "%02d:%02d".format(minutes, seconds)
    }

    private fun updateElapseTime() {
        viewModelScope.launch {
            while (mIsPlaying && mBounded) {
                updateCurrentPosition(getCurrentPosition())
                delay(500)
            }
        }
    }

    fun seek(newPosition: Int) {
        if (!mBounded)
            return

        mMediaPlayerService.seekTo(newPosition)
    }

    fun unBindMediaPlayerService() {
        application.unbindService(mConnection)
    }

    fun bindMediaPlayerService() {
        val intent = Intent(application.applicationContext, MediaPlayerService::class.java)
        application.startForegroundService(intent)
        application.bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    fun clearValues() {
        mUri = ""
        mDuration = ""
        mCurrentPositionFormatted = ""
        mCurrentSliderPosition = 0
        mIsPlaying = true
        mIsMute = false
        mBounded = false
    }
}