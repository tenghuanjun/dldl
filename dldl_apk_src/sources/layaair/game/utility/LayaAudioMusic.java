package layaair.game.utility;

import android.media.MediaPlayer;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class LayaAudioMusic {
    private static MediaPlayer mBackgroundMediaPlayer = null;
    private static String mCurrentPath = null;
    private static float mLeftVolume = 0.5f;
    private static boolean mManualPaused = false;
    private static boolean mPaused = false;
    private static float mRightVolume = 0.5f;
    private static Object s_lock = new Object();

    public static float getCurrentTime() {
        if (mBackgroundMediaPlayer != null) {
            return r0.getCurrentPosition() * 0.001f;
        }
        return 0.0f;
    }

    public static float getDuration() {
        if (mBackgroundMediaPlayer != null) {
            return r0.getDuration() * 0.001f;
        }
        return 0.0f;
    }

    public static void pauseBackgroundMusic() {
        MediaPlayer mediaPlayer = mBackgroundMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        mBackgroundMediaPlayer.pause();
        mPaused = true;
        mManualPaused = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003b A[Catch: all -> 0x00be, TryCatch #2 {, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x0012, B:10:0x0014, B:11:0x001b, B:18:0x0037, B:20:0x003b, B:22:0x0043, B:23:0x0048, B:24:0x004d, B:26:0x0055, B:27:0x0066, B:32:0x0076, B:33:0x0083, B:36:0x00a0, B:37:0x00ba, B:39:0x00bc, B:12:0x001e, B:14:0x0026, B:16:0x002a, B:17:0x002f), top: B:49:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void playBackgroundMusic(java.lang.String r3, int r4, int r5) {
        /*
            java.lang.Object r0 = layaair.game.utility.LayaAudioMusic.s_lock
            monitor-enter(r0)
            java.lang.String r1 = layaair.game.utility.LayaAudioMusic.mCurrentPath     // Catch: java.lang.Throwable -> Lbe
            if (r1 != 0) goto L1e
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> Lbe
            r1.<init>(r3)     // Catch: java.lang.Throwable -> Lbe
            boolean r1 = r1.exists()     // Catch: java.lang.Throwable -> Lbe
            if (r1 != 0) goto L14
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lbe
            return
        L14:
            android.media.MediaPlayer r1 = new android.media.MediaPlayer     // Catch: java.lang.Throwable -> Lbe
            r1.<init>()     // Catch: java.lang.Throwable -> Lbe
            layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer = r1     // Catch: java.lang.Throwable -> Lbe
        L1b:
            layaair.game.utility.LayaAudioMusic.mCurrentPath = r3     // Catch: java.lang.Throwable -> Lbe
            goto L37
        L1e:
            java.lang.String r1 = layaair.game.utility.LayaAudioMusic.mCurrentPath     // Catch: java.lang.Throwable -> Lbe
            boolean r1 = r1.equals(r3)     // Catch: java.lang.Throwable -> Lbe
            if (r1 != 0) goto L37
            android.media.MediaPlayer r1 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Throwable -> Lbe
            if (r1 == 0) goto L2f
            android.media.MediaPlayer r1 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Throwable -> Lbe
            r1.release()     // Catch: java.lang.Throwable -> Lbe
        L2f:
            android.media.MediaPlayer r1 = new android.media.MediaPlayer     // Catch: java.lang.Throwable -> Lbe
            r1.<init>()     // Catch: java.lang.Throwable -> Lbe
            layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer = r1     // Catch: java.lang.Throwable -> Lbe
            goto L1b
        L37:
            android.media.MediaPlayer r1 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Throwable -> Lbe
            if (r1 == 0) goto Lbc
            android.media.MediaPlayer r1 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Throwable -> Lbe
            boolean r1 = r1.isPlaying()     // Catch: java.lang.Throwable -> Lbe
            if (r1 == 0) goto L48
            android.media.MediaPlayer r1 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Throwable -> Lbe
            r1.stop()     // Catch: java.lang.Throwable -> Lbe
        L48:
            android.media.MediaPlayer r1 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Throwable -> Lbe
            r1.reset()     // Catch: java.lang.Throwable -> Lbe
            java.lang.String r1 = "/"
            boolean r1 = r3.startsWith(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            if (r1 == 0) goto L66
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            r1.<init>(r3)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            android.media.MediaPlayer r3 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            java.io.FileDescriptor r2 = r1.getFD()     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            r3.setDataSource(r2)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            r1.close()     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
        L66:
            android.media.MediaPlayer r3 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            float r1 = layaair.game.utility.LayaAudioMusic.mLeftVolume     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            float r2 = layaair.game.utility.LayaAudioMusic.mRightVolume     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            r3.setVolume(r1, r2)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lbe
            r3 = -1
            r1 = 0
            if (r4 != r3) goto L75
            r3 = 1
            goto L76
        L75:
            r3 = 0
        L76:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.Throwable -> Lbe
            android.media.MediaPlayer r4 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Throwable -> Lbe
            boolean r3 = r3.booleanValue()     // Catch: java.lang.Throwable -> Lbe
            r4.setLooping(r3)     // Catch: java.lang.Throwable -> Lbe
            android.media.MediaPlayer r3 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            r3.prepare()     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            android.media.MediaPlayer r3 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            r3.seekTo(r5)     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            android.media.MediaPlayer r3 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            r3.start()     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            layaair.game.utility.LayaAudioMusic.mPaused = r1     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            android.media.MediaPlayer r3 = layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            layaair.game.utility.a r4 = new layaair.game.utility.a     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            r4.<init>()     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            r3.setOnCompletionListener(r4)     // Catch: java.lang.Exception -> Lbc java.lang.Throwable -> Lbe
            goto Lbc
        L9f:
            r3 = move-exception
            java.lang.String r4 = "Audio"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbe
            java.lang.String r1 = ">>>>>>"
            r5.<init>(r1)     // Catch: java.lang.Throwable -> Lbe
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lbe
            r5.append(r3)     // Catch: java.lang.Throwable -> Lbe
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Throwable -> Lbe
            android.util.Log.e(r4, r3)     // Catch: java.lang.Throwable -> Lbe
            r3 = 0
            layaair.game.utility.LayaAudioMusic.mBackgroundMediaPlayer = r3     // Catch: java.lang.Throwable -> Lbe
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lbe
            return
        Lbc:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lbe
            return
        Lbe:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lbe
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: layaair.game.utility.LayaAudioMusic.playBackgroundMusic(java.lang.String, int, int):void");
    }

    public static void resumeBackgroundMusic() {
        MediaPlayer mediaPlayer = mBackgroundMediaPlayer;
        if (mediaPlayer == null || !mPaused) {
            return;
        }
        mediaPlayer.start();
        mPaused = false;
        mManualPaused = false;
    }

    public static void setBackgroundMusicVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        mRightVolume = f;
        mLeftVolume = f;
        MediaPlayer mediaPlayer = mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(mLeftVolume, mRightVolume);
        }
    }

    public static void setCurrentTime(float f) {
        MediaPlayer mediaPlayer = mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo((int) (f * 1000.0f));
        }
    }

    public static void stopBackgroundMusic() {
        MediaPlayer mediaPlayer = mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mPaused = false;
        }
    }

    public static void uninit() {
        synchronized (s_lock) {
            if (mBackgroundMediaPlayer != null) {
                mBackgroundMediaPlayer.stop();
                mBackgroundMediaPlayer = null;
            }
            mCurrentPath = null;
            mPaused = false;
            mManualPaused = false;
        }
    }
}
