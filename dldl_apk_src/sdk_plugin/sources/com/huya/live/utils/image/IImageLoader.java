package com.huya.live.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IImageLoader {
    void displayCircle(Context context, ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener);

    void displayEmpty(Context context, ImageView imageView, String str);

    void displayImage(Context context, ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener);

    void displayImageFromRes(Context context, ImageView imageView, int i, int i2, ImageLoaderListener imageLoaderListener);

    void displayRound(Context context, ImageView imageView, File file, ImageLoaderListener imageLoaderListener, int i, int i2);

    void displayRound(Context context, ImageView imageView, String str, ImageLoaderListener imageLoaderListener, int i, int i2);

    void init(Context context);

    Bitmap loadImage(Context context, String str, int i, int i2);

    void loadImage(Context context, String str, IconListLoader iconListLoader);

    void loadImage(Context context, String str, ImageLoaderListener imageLoaderListener);
}
