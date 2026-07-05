package com.huya.live.utils.image;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.duowan.auk.ArkValue;
import com.sqwan.liveshow.huya.SqR;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ImageBind {
    private static final int DEFAULT_IMAGE = getDrawableResIDByName(SqR.drawable.base_presenter_avatar_default);
    private static final int DEFAULT_CIRCLE_IMAGE = getDrawableResIDByName(SqR.drawable.default_photo_circle);

    public static int getDrawableResIDByName(String str) {
        return getResIDByName(str, "drawable");
    }

    public static int getResIDByName(String str, String str2) {
        Application application = ArkValue.gContext;
        return application.getResources().getIdentifier(str, str2, application.getPackageName());
    }

    public static void displayCircle(ImageView imageView, String str) {
        displayCircle(imageView.getContext(), imageView, str);
    }

    public static void displayCircle(Context context, ImageView imageView, String str) {
        displayCircle(context, imageView, str, DEFAULT_CIRCLE_IMAGE, null);
    }

    public static void displayCircle(ImageView imageView, String str, int i) {
        displayCircle(imageView.getContext(), imageView, str, i, null);
    }

    public static void displayCircle(ImageView imageView, String str, ImageLoaderListener imageLoaderListener) {
        displayCircle(imageView.getContext(), imageView, str, DEFAULT_CIRCLE_IMAGE, imageLoaderListener);
    }

    public static void displayCircle(Context context, ImageView imageView, String str, int i) {
        displayCircle(context, imageView, str, i, null);
    }

    public static void displayCircle(Context context, ImageView imageView, String str, ImageLoaderListener imageLoaderListener) {
        displayCircle(context, imageView, str, DEFAULT_CIRCLE_IMAGE, imageLoaderListener);
    }

    public static void displayCircle(Context context, ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener) {
        ImageLoaderProxy.getInstance().displayCircle(context, imageView, str, i, imageLoaderListener);
    }

    public static void displayImage(ImageView imageView, String str, int i) {
        displayImage(imageView.getContext(), imageView, str, i, null);
    }

    public static void displayImage(ImageView imageView, String str) {
        displayImage(imageView.getContext(), imageView, str, DEFAULT_IMAGE, null);
    }

    public static void displayImage(Context context, ImageView imageView, String str) {
        displayImage(context, imageView, str, DEFAULT_IMAGE, null);
    }

    public static void displayImage(Context context, ImageView imageView, String str, int i) {
        displayImage(context, imageView, str, i, null);
    }

    public static void displayImage(Context context, ImageView imageView, String str, ImageLoaderListener imageLoaderListener) {
        displayImage(context, imageView, str, DEFAULT_IMAGE, imageLoaderListener);
    }

    public static void displayImage(Context context, ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener) {
        ImageLoaderProxy.getInstance().displayImage(context, imageView, str, i, imageLoaderListener);
    }

    public static void displayEmpty(Context context, ImageView imageView, String str) {
        ImageLoaderProxy.getInstance().displayEmpty(context, imageView, str);
    }

    public static void displayEmpty(ImageView imageView, String str) {
        ImageLoaderProxy.getInstance().displayEmpty(imageView.getContext(), imageView, str);
    }

    public static void displayRound(ImageView imageView, String str, int i) {
        displayRound(imageView.getContext(), imageView, str, (ImageLoaderListener) null, DEFAULT_IMAGE, i);
    }

    public static void displayRound(ImageView imageView, String str, int i, int i2) {
        displayRound(imageView.getContext(), imageView, str, (ImageLoaderListener) null, i, i2);
    }

    public static void displayRound(ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener) {
        displayRound(imageView.getContext(), imageView, str, imageLoaderListener, DEFAULT_IMAGE, i);
    }

    public static void displayRound(Context context, ImageView imageView, String str, ImageLoaderListener imageLoaderListener, int i, int i2) {
        ImageLoaderProxy.getInstance().displayRound(context, imageView, str, imageLoaderListener, i, i2);
    }

    public static void displayImageFromRes(Context context, ImageView imageView, int i, int i2, ImageLoaderListener imageLoaderListener) {
        ImageLoaderProxy.getInstance().displayImageFromRes(context, imageView, i, i2, imageLoaderListener);
    }

    public static void displayRound(Context context, ImageView imageView, File file, ImageLoaderListener imageLoaderListener, int i, int i2) {
        ImageLoaderProxy.getInstance().displayRound(context, imageView, file, imageLoaderListener, i, i2);
    }

    public static void displayImageFromPath(Context context, ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener) {
        ImageLoaderProxy.getInstance().displayImage(context, imageView, str, i, imageLoaderListener);
    }

    public static void loadImage(Context context, String str, ImageLoaderListener imageLoaderListener) {
        ImageLoaderProxy.getInstance().loadImage(context, str, imageLoaderListener);
    }

    public static Bitmap loadImage(Context context, String str) throws Exception {
        return loadImage(context, str, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public static Bitmap loadImage(Context context, String str, int i, int i2) throws Exception {
        return ImageLoaderProxy.getInstance().loadImage(context, str, i, i2);
    }

    public static String getResDrawablePath(int i) {
        return "android.resource://" + ArkValue.gContext.getPackageName() + "/drawable/" + i;
    }
}
