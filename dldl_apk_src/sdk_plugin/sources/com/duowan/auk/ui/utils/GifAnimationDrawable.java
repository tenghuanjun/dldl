package com.duowan.auk.ui.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import com.huya.mtp.utils.ThreadUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GifAnimationDrawable extends AnimationDrawable {
    private boolean decoded;
    private int height;
    private Runnable loader;
    private GifDecoder mGifDecoder;
    private Bitmap mTmpBitmap;
    private int width;

    public GifAnimationDrawable(File file) throws IOException {
        this(file, false);
    }

    public GifAnimationDrawable(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public GifAnimationDrawable(File file, boolean z) throws IOException {
        this(new BufferedInputStream(new FileInputStream(file), 32768), z);
    }

    public GifAnimationDrawable(InputStream inputStream, boolean z) throws IOException {
        this.loader = new Runnable() { // from class: com.duowan.auk.ui.utils.GifAnimationDrawable.1
            @Override // java.lang.Runnable
            public void run() {
                GifAnimationDrawable.this.mGifDecoder.complete();
                int frameCount = GifAnimationDrawable.this.mGifDecoder.getFrameCount();
                for (int i = 1; i < frameCount; i++) {
                    GifAnimationDrawable gifAnimationDrawable = GifAnimationDrawable.this;
                    gifAnimationDrawable.mTmpBitmap = gifAnimationDrawable.mGifDecoder.getFrame(i);
                    final int delay = GifAnimationDrawable.this.mGifDecoder.getDelay(i);
                    Log.v("GifAnimationDrawable", "===>Frame " + i + ": " + delay + "]");
                    ThreadUtils.runOnMainThread(new Runnable() { // from class: com.duowan.auk.ui.utils.GifAnimationDrawable.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GifAnimationDrawable.this.addFrame(new BitmapDrawable(GifAnimationDrawable.this.mTmpBitmap), delay);
                        }
                    });
                }
                GifAnimationDrawable.this.decoded = true;
                GifAnimationDrawable.this.mGifDecoder = null;
            }
        };
        inputStream = BufferedInputStream.class.isInstance(inputStream) ? inputStream : new BufferedInputStream(inputStream, 32768);
        this.decoded = false;
        GifDecoder gifDecoder = new GifDecoder();
        this.mGifDecoder = gifDecoder;
        gifDecoder.read(inputStream);
        this.mTmpBitmap = this.mGifDecoder.getFrame(0);
        Log.v("GifAnimationDrawable", "===>Lead frame: [" + this.width + "x" + this.height + "; " + this.mGifDecoder.getDelay(0) + ";" + this.mGifDecoder.getLoopCount() + "]");
        this.height = this.mTmpBitmap.getHeight();
        this.width = this.mTmpBitmap.getWidth();
        addFrame(new BitmapDrawable(this.mTmpBitmap), this.mGifDecoder.getDelay(0));
        setOneShot(this.mGifDecoder.getLoopCount() != 0);
        setVisible(true, true);
        if (z) {
            this.loader.run();
        } else {
            new Thread(this.loader).start();
        }
    }

    public boolean isDecoded() {
        return this.decoded;
    }

    @Override // android.graphics.drawable.AnimationDrawable, android.graphics.drawable.Animatable
    public void start() {
        super.start();
        setVisible(true, true);
    }

    @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.height;
    }

    @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.width;
    }

    @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.height;
    }

    @Override // android.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.width;
    }
}
