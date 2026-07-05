package com.huya.live.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ImageLoaderProxy implements IImageLoader {
    private static volatile ImageLoaderProxy instance;
    private IImageLoader mImageLoader;

    public static ImageLoaderProxy getInstance() {
        if (instance == null) {
            synchronized (ImageLoaderProxy.class) {
                if (instance == null) {
                    instance = new ImageLoaderProxy();
                }
            }
        }
        return instance;
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void init(Context context) {
        this.mImageLoader.init(context);
    }

    public void setImageLoader(IImageLoader iImageLoader) {
        this.mImageLoader = iImageLoader;
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayCircle(Context context, ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener) {
        this.mImageLoader.displayCircle(context, imageView, str, i, imageLoaderListener);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayImage(Context context, ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener) {
        this.mImageLoader.displayImage(context, imageView, str, i, imageLoaderListener);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayEmpty(Context context, ImageView imageView, String str) {
        this.mImageLoader.displayEmpty(context, imageView, str);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayRound(Context context, ImageView imageView, String str, ImageLoaderListener imageLoaderListener, int i, int i2) {
        this.mImageLoader.displayRound(context, imageView, str, imageLoaderListener, i, i2);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void loadImage(Context context, String str, ImageLoaderListener imageLoaderListener) {
        this.mImageLoader.loadImage(context, str, imageLoaderListener);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public Bitmap loadImage(Context context, String str, int i, int i2) {
        return this.mImageLoader.loadImage(context, str, i, i2);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayImageFromRes(Context context, ImageView imageView, int i, int i2, ImageLoaderListener imageLoaderListener) {
        this.mImageLoader.displayImageFromRes(context, imageView, i, i2, imageLoaderListener);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayRound(Context context, ImageView imageView, File file, ImageLoaderListener imageLoaderListener, int i, int i2) {
        this.mImageLoader.displayRound(context, imageView, file, imageLoaderListener, i, i2);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void loadImage(Context context, String str, IconListLoader iconListLoader) {
        this.mImageLoader.loadImage(context, str, iconListLoader);
    }
}
