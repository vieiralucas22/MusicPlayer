package com.example.musicplayer.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import com.example.musicplayer.R

class MediaPlayerService : Service() {

    private val mBinder = LocalBinder()
    private var mediaPlayer: MediaPlayer? = null

    /*Override methods*/
    override fun onCreate() {
        super.onCreate()
        Log.d("[MusicPlayer]","onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        initForegroundService()

        return START_NOT_STICKY
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

        mediaPlayer?.release()
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

    fun seekTo(millisecond : Int)
    {
        mediaPlayer?.seekTo(millisecond)
    }

    /*Private methods*/
    @SuppressLint("ForegroundServiceType")
    private fun initForegroundService() {
        val channel = NotificationChannel(
            "media_channel",
            "Media Playback",
            NotificationManager.IMPORTANCE_LOW
        )

        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, "media_channel")
            .setContentTitle("Music")
            .setContentText("Sua música está em reprodução")
            .setSmallIcon(R.drawable.ic_play)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()

        ServiceCompat.startForeground(
            this,
            100,
            notification,
            ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
        )
    }

    /*Inner class*/
    inner class LocalBinder() : Binder() {
        fun getMediaPlayerService() : MediaPlayerService = this@MediaPlayerService
    }
}