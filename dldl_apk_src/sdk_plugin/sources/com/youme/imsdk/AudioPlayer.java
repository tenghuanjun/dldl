package com.youme.imsdk;

import android.media.MediaPlayer;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AudioPlayer {
    private static AudioPlayer _instance;
    private MediaPlayer.OnCompletionListener completeListener;
    private boolean isPaused;
    private boolean lastStateIsStop;
    private MediaPlayer mediaPlayer;
    MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.youme.imsdk.AudioPlayer.1
        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            if (AudioPlayer.this.lastStateIsStop) {
                return;
            }
            AudioPlayer.this.mediaPlayer.start();
        }
    };
    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() { // from class: com.youme.imsdk.AudioPlayer.2
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (AudioPlayer.this.completeListener != null) {
                AudioPlayer.this.isPaused = false;
                AudioPlayer.this.completeListener.onCompletion(mediaPlayer);
            }
        }
    };

    public static AudioPlayer GetInstance() {
        if (_instance == null) {
            _instance = new AudioPlayer();
        }
        return _instance;
    }

    public AudioPlayer() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mediaPlayer = mediaPlayer;
        mediaPlayer.setOnPreparedListener(this.preparedListener);
        this.mediaPlayer.setOnCompletionListener(this.completionListener);
    }

    public boolean IsPlaying() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public void SetOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.completeListener = onCompletionListener;
    }

    public boolean Play(String str) {
        this.isPaused = false;
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.stop();
        }
        try {
            this.mediaPlayer.reset();
            this.mediaPlayer.setDataSource(str);
            this.lastStateIsStop = false;
            this.mediaPlayer.prepareAsync();
            return true;
        } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Pause() {
        if (!this.mediaPlayer.isPlaying()) {
            return false;
        }
        try {
            this.mediaPlayer.pause();
            this.isPaused = true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            this.isPaused = false;
        }
        return this.isPaused;
    }

    public boolean ResumePlay() {
        if (!this.isPaused || this.mediaPlayer.isPlaying()) {
            return false;
        }
        this.mediaPlayer.start();
        return true;
    }

    public boolean IsPaused() {
        return this.isPaused;
    }

    public boolean Stop() {
        this.isPaused = false;
        this.lastStateIsStop = true;
        try {
            this.mediaPlayer.stop();
            return true;
        } catch (IllegalStateException unused) {
            return false;
        }
    }
}
