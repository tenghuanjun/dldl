package com.huya.berry;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.duowan.auk.ArkValue;
import com.huya.live.utils.image.IImageLoader;
import com.huya.live.utils.image.IconListLoader;
import com.huya.live.utils.image.ImageLoaderListener;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GlideImageLoader implements IImageLoader {
    private Context mContext;
    private IconListLoader mIconListLoader;
    private ImageLoaderListener mImageLoaderListener;
    private RequestListener mRequestListener = new RequestListener() { // from class: com.huya.berry.GlideImageLoader.2
        @Override // com.bumptech.glide.request.RequestListener
        public boolean onLoadFailed(GlideException glideException, Object obj, Target target, boolean z) {
            if (GlideImageLoader.this.mImageLoaderListener == null) {
                return false;
            }
            GlideImageLoader.this.mImageLoaderListener.onFail();
            return false;
        }

        @Override // com.bumptech.glide.request.RequestListener
        public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z) {
            if (GlideImageLoader.this.mImageLoaderListener == null) {
                return false;
            }
            GlideImageLoader.this.mImageLoaderListener.onLoadSuccess((Bitmap) obj);
            return false;
        }
    };
    private SimpleTarget<Bitmap> target;

    public GlideImageLoader() {
        int i = 80;
        this.target = new SimpleTarget<Bitmap>(i, i) { // from class: com.huya.berry.GlideImageLoader.1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                if (GlideImageLoader.this.mIconListLoader != null) {
                    GlideImageLoader.this.mIconListLoader.loadNext(bitmap);
                }
            }
        };
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void init(Context context) {
        this.mContext = context;
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayCircle(Context context, ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener) {
        this.mImageLoaderListener = imageLoaderListener;
        Glide.with(context).asBitmap().load(str).apply(new RequestOptions().placeholder(i).error(i).transform(new GlideCircleTransform(context))).listener(this.mRequestListener).into(imageView);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayImage(Context context, ImageView imageView, String str, int i, ImageLoaderListener imageLoaderListener) {
        this.mImageLoaderListener = imageLoaderListener;
        Glide.with(context).asBitmap().apply(new RequestOptions().placeholder(i).error(i).dontAnimate()).load(str).listener(this.mRequestListener).into(imageView);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayEmpty(Context context, ImageView imageView, String str) {
        Glide.with(context).load(str).apply(new RequestOptions().dontAnimate()).into(imageView);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayRound(Context context, ImageView imageView, String str, ImageLoaderListener imageLoaderListener, int i, int i2) {
        this.mImageLoaderListener = imageLoaderListener;
        Glide.with(context).asBitmap().load(str).apply(new RequestOptions().transform(new GlideRoundTransform(context, i2)).placeholder(i).error(i).dontAnimate()).listener(this.mRequestListener).into(imageView);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void loadImage(Context context, String str, ImageLoaderListener imageLoaderListener) {
        this.mImageLoaderListener = imageLoaderListener;
        Glide.with(context).asBitmap().load(str).listener(this.mRequestListener).into(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public Bitmap loadImage(Context context, String str, int i, int i2) {
        try {
            return Glide.with(context).asBitmap().load(str).into(i, i2).get();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayImageFromRes(Context context, ImageView imageView, int i, int i2, ImageLoaderListener imageLoaderListener) {
        this.mImageLoaderListener = imageLoaderListener;
        Glide.with(context).asBitmap().load(Integer.valueOf(i)).apply(new RequestOptions().error(i2).dontAnimate()).listener(this.mRequestListener).into(imageView);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void displayRound(Context context, ImageView imageView, File file, ImageLoaderListener imageLoaderListener, int i, int i2) {
        this.mImageLoaderListener = imageLoaderListener;
        Glide.with(context).asBitmap().load(file).apply(new RequestOptions().transform(new GlideRoundTransform(context, i2)).placeholder(i).error(i).dontAnimate()).listener(this.mRequestListener).into(imageView);
    }

    @Override // com.huya.live.utils.image.IImageLoader
    public void loadImage(Context context, String str, IconListLoader iconListLoader) {
        this.mIconListLoader = iconListLoader;
        Glide.with(ArkValue.gContext).asBitmap().load(str).into(this.target);
    }
}
