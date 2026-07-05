package com.sq.sdk.tool.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.LruCache;
import android.widget.ImageView;
import com.sq.sdk.tool.observer.Observable;
import com.sq.sdk.tool.observer.Observer;
import com.sq.sdk.tool.observer.ThreadModel;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqImageLoader {
    private static SqImageLoader sInstance;
    private LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>(((int) (Runtime.getRuntime().totalMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 8) { // from class: com.sq.sdk.tool.util.SqImageLoader.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public int sizeOf(String str, Bitmap bitmap) {
            return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
        }
    };

    private SqImageLoader() {
    }

    public static SqImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (SqImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new SqImageLoader();
                }
            }
        }
        return sInstance;
    }

    public void showImage(ImageView imageView, String str) {
        showImage(imageView, str, 0);
    }

    public void showImage(final ImageView imageView, String str, final int i) {
        SqLogUtil.i("load img url = " + str);
        final String strEncode = MD5Util.encode(str);
        Bitmap bitmap = this.mLruCache.get(strEncode);
        if (bitmap != null) {
            SqLogUtil.i("get the img from cache!");
            imageView.setImageBitmap(bitmap);
        } else {
            DownloadImage.downloadImage(str).observerOn(ThreadModel.MAIN_THREAD).registerObserver(new Observer<Bitmap, String, String>() { // from class: com.sq.sdk.tool.util.SqImageLoader.2
                @Override // com.sq.sdk.tool.observer.Observer
                public void onComplete(String str2) {
                }

                @Override // com.sq.sdk.tool.observer.Observer
                public void onSuccess(Bitmap bitmap2) {
                    imageView.setImageBitmap(bitmap2);
                    SqImageLoader.this.mLruCache.put(strEncode, bitmap2);
                }

                @Override // com.sq.sdk.tool.observer.Observer
                public void onFail(String str2) {
                    SqLogUtil.e("Load bitmap from net fail: " + str2);
                    if (i != 0) {
                        SqLogUtil.i("load errorLoadRes!");
                        imageView.setImageResource(i);
                    } else {
                        SqLogUtil.e("errorLoadRes is null!");
                    }
                }
            }).execute();
        }
    }

    private static class DownloadImage extends Observable<Bitmap, String, String> {
        private String mImgUrl;

        private DownloadImage() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static DownloadImage downloadImage(String str) {
            return new DownloadImage().setImgUrl(str);
        }

        private DownloadImage setImgUrl(String str) {
            this.mImgUrl = str;
            return this;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bitmap bitmapLoadBitmap = loadBitmap(this.mImgUrl);
            if (bitmapLoadBitmap == null) {
                handleFail("下载图片失败！");
            } else {
                handleSuccess(bitmapLoadBitmap);
            }
        }

        private Bitmap loadBitmap(String str) {
            Bitmap bitmapDecodeStream = null;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return bitmapDecodeStream;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return bitmapDecodeStream;
            } catch (IOException e2) {
                e2.printStackTrace();
                return bitmapDecodeStream;
            }
        }
    }
}
