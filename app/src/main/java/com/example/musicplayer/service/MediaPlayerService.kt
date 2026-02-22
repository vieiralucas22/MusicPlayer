package com.example.musicplayer.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MediaPlayerService : Service() {

    private val mBinder = LocalBinder()
    private var mediaPlayer: MediaPlayer? = null

    /*Override methods*/
    override fun onCreate() {
        super.onCreate()
        Log.d("[MusicPlayer]","onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("[MusicPlayer]","onBind")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("[MusicPlayer]","onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d("[MusicPlayer]","onDestroy")
        super.onDestroy()
    }

    /*Public methods*/
    fun play(context: Context, uri: Uri) {
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer().apply {
            setDataSource(context, uri)
            prepare()
            start()
        }
    }

    fun startOrResumeMusic()
    {
        mediaPlayer?.start()
    }

    fun pauseMusic()
    {
        mediaPlayer?.pause()
    }

    fun isPlaying() : Boolean?
    {
        return mediaPlayer?.isPlaying
    }

    fun getDuration() : Int?
    {
        return mediaPlayer?.duration
    }

    fun getCurrentPosition() : Int?
    {
        return mediaPlayer?.currentPosition
    }

    fun mutePlayer()
    {
        mediaPlayer?.setVolume(0f, 0f)
    }

    fun unmutePlayer()
    {
        mediaPlayer?.setVolume(1f, 1f)
    }

    /*Inner class*/
    inner class LocalBinder() : Binder() {
        fun getMediaPlayerService() : MediaPlayerService = this@MediaPlayerService
    }
}