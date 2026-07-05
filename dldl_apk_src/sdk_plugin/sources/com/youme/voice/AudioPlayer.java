package com.youme.voice;

import android.media.MediaPlayer;
import android.util.Log;
import com.youme.im.CommonConst;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AudioPlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    private MediaPlayer m_mediaPlayer;
    private boolean m_isPaused = false;
    private boolean m_lastStateIsStop = true;
    private IAudioPlayListener m_playerListener = null;
    private String m_audioPath = "";
    private float m_playVolume = 1.0f;

    public AudioPlayer() {
        this.m_mediaPlayer = null;
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.m_mediaPlayer = mediaPlayer;
        mediaPlayer.setOnPreparedListener(this);
        this.m_mediaPlayer.setOnCompletionListener(this);
        this.m_mediaPlayer.setOnErrorListener(this);
    }

    public void SetPlayListener(IAudioPlayListener iAudioPlayListener) {
        this.m_playerListener = iAudioPlayListener;
    }

    public void SetPlayVolume(float f) {
        Log.d(CommonConst.LOG_TAG, "SetVolume: " + f);
        this.m_playVolume = f;
    }

    public boolean IsPlaying() {
        MediaPlayer mediaPlayer = this.m_mediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public AudioErrorCode Play(String str) {
        if (!new File(str).exists()) {
            return AudioErrorCode.AUDIOERROR_FILE_NOT_EXIT;
        }
        if (this.m_mediaPlayer == null) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.m_mediaPlayer = mediaPlayer;
            mediaPlayer.setOnPreparedListener(this);
            this.m_mediaPlayer.setOnCompletionListener(this);
            this.m_mediaPlayer.setOnErrorListener(this);
        }
        if (this.m_mediaPlayer == null) {
            return AudioErrorCode.AUDIOERROR_START_PLAY_FAILED;
        }
        this.m_isPaused = false;
        AudioErrorCode audioErrorCode = AudioErrorCode.AUDIOERROR_SUCCESS;
        if (this.m_mediaPlayer.isPlaying()) {
            this.m_mediaPlayer.stop();
            audioErrorCode = AudioErrorCode.AUDIOERROR_PLAYING;
        }
        this.m_audioPath = str;
        try {
            this.m_mediaPlayer.reset();
            this.m_mediaPlayer.setOnPreparedListener(this);
            this.m_mediaPlayer.setOnCompletionListener(this);
            this.m_mediaPlayer.setOnErrorListener(this);
            this.m_mediaPlayer.setAudioStreamType(3);
            this.m_mediaPlayer.setDataSource(str);
            this.m_lastStateIsStop = false;
            this.m_mediaPlayer.setVolume(this.m_playVolume, this.m_playVolume);
            this.m_mediaPlayer.prepareAsync();
            Log.d(CommonConst.LOG_TAG, "prepareAsync4");
            return audioErrorCode;
        } catch (Exception e) {
            this.m_lastStateIsStop = true;
            e.printStackTrace();
            return AudioErrorCode.AUDIOERROR_START_PLAY_FAILED;
        }
    }

    public boolean Pause() {
        MediaPlayer mediaPlayer = this.m_mediaPlayer;
        if (mediaPlayer == null) {
            return true;
        }
        if (!mediaPlayer.isPlaying()) {
            return false;
        }
        try {
            this.m_mediaPlayer.pause();
            this.m_isPaused = true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            this.m_isPaused = false;
        }
        return this.m_isPaused;
    }

    public boolean ResumePlay() {
        MediaPlayer mediaPlayer = this.m_mediaPlayer;
        if (mediaPlayer == null || !this.m_isPaused || mediaPlayer.isPlaying()) {
            return false;
        }
        this.m_mediaPlayer.start();
        return true;
    }

    public boolean IsPaused() {
        return this.m_isPaused;
    }

    public AudioErrorCode Stop() {
        MediaPlayer mediaPlayer = this.m_mediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return AudioErrorCode.AUDIOERROR_NOT_START_PLAY;
        }
        this.m_isPaused = false;
        this.m_lastStateIsStop = true;
        try {
            this.m_mediaPlayer.stop();
            return AudioErrorCode.AUDIOERROR_SUCCESS;
        } catch (IllegalStateException unused) {
            return AudioErrorCode.AUDIOERROR_STOP_PLAY_FAILED;
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d(CommonConst.LOG_TAG, "onCompletion");
        this.m_mediaPlayer.reset();
        IAudioPlayListener iAudioPlayListener = this.m_playerListener;
        if (iAudioPlayListener != null) {
            iAudioPlayListener.OnPlayFinish(AudioErrorCode.AUDIOERROR_SUCCESS, this.m_audioPath);
        }
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d(CommonConst.LOG_TAG, "onPrepared");
        if (this.m_lastStateIsStop) {
            return;
        }
        Log.d(CommonConst.LOG_TAG, "start play");
        this.m_mediaPlayer.start();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Log.d(CommonConst.LOG_TAG, "what:" + i + " extra:" + i2);
        MediaPlayer mediaPlayer2 = this.m_mediaPlayer;
        if (mediaPlayer2 != null) {
            mediaPlayer2.reset();
        }
        AudioErrorCode audioErrorCode = AudioErrorCode.AUDIOERROR_OTHREERROR;
        if (100 == i) {
            MediaPlayer mediaPlayer3 = this.m_mediaPlayer;
            if (mediaPlayer3 != null) {
                mediaPlayer3.release();
            }
            MediaPlayer mediaPlayer4 = new MediaPlayer();
            this.m_mediaPlayer = mediaPlayer4;
            mediaPlayer4.setOnPreparedListener(this);
            this.m_mediaPlayer.setOnCompletionListener(this);
            this.m_mediaPlayer.setOnErrorListener(this);
        }
        if (-1010 == i2) {
            audioErrorCode = AudioErrorCode.AUDIOERROR_UNSUPPORT_FORMAT;
        } else if (-110 == i2) {
            audioErrorCode = AudioErrorCode.AUDIOERROR_PLAY_TIMEOUT;
        }
        IAudioPlayListener iAudioPlayListener = this.m_playerListener;
        if (iAudioPlayListener == null) {
            return true;
        }
        iAudioPlayListener.OnPlayFinish(audioErrorCode, this.m_audioPath);
        return true;
    }
}
