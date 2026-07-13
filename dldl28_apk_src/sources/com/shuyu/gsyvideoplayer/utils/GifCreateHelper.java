package com.shuyu.gsyvideoplayer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import com.shuyu.gsyvideoplayer.listener.GSYVideoGifSaveListener;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotSaveListener;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes3.dex */
public class GifCreateHelper {
    private int mDelay;
    private int mFrequencyCount;
    private GSYVideoGifSaveListener mGSYVideoGifSaveListener;
    private List<String> mPicList;
    private StandardGSYVideoPlayer mPlayer;
    private int mSampleSize;
    private boolean mSaveShotBitmapSuccess;
    private int mScaleSize;
    private Timer mTimer;
    private TaskLocal mTimerTask;
    private File mTmpPath;

    public GifCreateHelper(StandardGSYVideoPlayer standardGSYVideoPlayer, GSYVideoGifSaveListener gSYVideoGifSaveListener) {
        this(standardGSYVideoPlayer, gSYVideoGifSaveListener, 0, 1, 5, 50);
    }

    public GifCreateHelper(StandardGSYVideoPlayer standardGSYVideoPlayer, GSYVideoGifSaveListener gSYVideoGifSaveListener, int i, int i2, int i3, int i4) {
        this.mSaveShotBitmapSuccess = true;
        this.mTimer = new Timer();
        this.mPicList = new ArrayList();
        this.mPlayer = standardGSYVideoPlayer;
        this.mGSYVideoGifSaveListener = gSYVideoGifSaveListener;
        this.mDelay = i;
        this.mSampleSize = i2;
        this.mScaleSize = i3;
        this.mFrequencyCount = i4;
    }

    public void startGif(File file) {
        this.mTmpPath = file;
        cancelTask();
        this.mPicList.clear();
        TaskLocal taskLocal = new TaskLocal();
        this.mTimerTask = taskLocal;
        this.mTimer.schedule(taskLocal, 0L, this.mFrequencyCount);
    }

    public void stopGif(final File file) {
        cancelTask();
        this.mSaveShotBitmapSuccess = true;
        new Thread(new Runnable() { // from class: com.shuyu.gsyvideoplayer.utils.GifCreateHelper.1
            @Override // java.lang.Runnable
            public void run() {
                if (GifCreateHelper.this.mPicList.size() <= 2) {
                    GifCreateHelper.this.mGSYVideoGifSaveListener.result(false, null);
                } else {
                    GifCreateHelper gifCreateHelper = GifCreateHelper.this;
                    gifCreateHelper.createGif(file, gifCreateHelper.mPicList, GifCreateHelper.this.mDelay, GifCreateHelper.this.mSampleSize, GifCreateHelper.this.mScaleSize, GifCreateHelper.this.mGSYVideoGifSaveListener);
                }
            }
        }).start();
    }

    public void cancelTask() {
        TaskLocal taskLocal = this.mTimerTask;
        if (taskLocal != null) {
            taskLocal.cancel();
            this.mTimerTask = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSaveBitmap() {
        this.mPlayer.saveFrame(new File(this.mTmpPath, "GSY-TMP-FRAME" + System.currentTimeMillis() + ".tmp"), new GSYVideoShotSaveListener() { // from class: com.shuyu.gsyvideoplayer.utils.GifCreateHelper.2
            @Override // com.shuyu.gsyvideoplayer.listener.GSYVideoShotSaveListener
            public void result(boolean z, File file) {
                GifCreateHelper.this.mSaveShotBitmapSuccess = true;
                if (z) {
                    Debuger.printfError(" SUCCESS CREATE FILE " + file.getAbsolutePath());
                    GifCreateHelper.this.mPicList.add(file.getAbsolutePath());
                }
            }
        });
    }

    public void createGif(File file, List<String> list, int i, int i2, int i3, GSYVideoGifSaveListener gSYVideoGifSaveListener) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        AnimatedGifEncoder animatedGifEncoder = new AnimatedGifEncoder();
        animatedGifEncoder.start(byteArrayOutputStream);
        animatedGifEncoder.setRepeat(0);
        animatedGifEncoder.setDelay(i);
        int i4 = 0;
        while (i4 < list.size()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = i2;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(list.get(i4), options);
            double d = i3;
            double d2 = ((double) options.outWidth) / d;
            double d3 = ((double) options.outHeight) / d;
            options.inJustDecodeBounds = false;
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(list.get(i4), options);
            Bitmap bitmapExtractThumbnail = ThumbnailUtils.extractThumbnail(bitmapDecodeFile, (int) d2, (int) d3);
            animatedGifEncoder.addFrame(bitmapExtractThumbnail);
            bitmapDecodeFile.recycle();
            bitmapExtractThumbnail.recycle();
            i4++;
            gSYVideoGifSaveListener.process(i4, list.size());
        }
        animatedGifEncoder.finish();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
            byteArrayOutputStream.writeTo(fileOutputStream);
            byteArrayOutputStream.flush();
            fileOutputStream.flush();
            byteArrayOutputStream.close();
            fileOutputStream.close();
            gSYVideoGifSaveListener.result(true, file);
        } catch (IOException e) {
            e.printStackTrace();
            gSYVideoGifSaveListener.result(false, file);
        }
    }

    private class TaskLocal extends TimerTask {
        private TaskLocal() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (GifCreateHelper.this.mSaveShotBitmapSuccess) {
                GifCreateHelper.this.mSaveShotBitmapSuccess = false;
                GifCreateHelper.this.startSaveBitmap();
            }
        }
    }
}
