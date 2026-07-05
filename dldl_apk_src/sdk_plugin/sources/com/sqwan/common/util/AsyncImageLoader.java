package com.sqwan.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;
import com.sqwan.bugless.util.FileUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AsyncImageLoader {
    public static final String BITMAP_CACHE = "/fdk_bitmap/";
    public static final int POLL_MAX_LOADIMAGE_NUM = 8;
    public static ExecutorService pool = Executors.newFixedThreadPool(8);
    private Context context;
    private HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<>();

    public interface ImageCallback {
        void imageLoaded(Bitmap bitmap, ImageView imageView, String str);
    }

    public AsyncImageLoader(Context context) {
        this.context = context;
    }

    public void loadDrawable(final String str, final ImageView imageView, final ImageCallback imageCallback) {
        final Handler handler = new Handler() { // from class: com.sqwan.common.util.AsyncImageLoader.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                imageCallback.imageLoaded((Bitmap) message.obj, imageView, str);
            }
        };
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            handler.sendMessage(handler.obtainMessage(0, null));
            return;
        }
        if (this.imageCache.containsKey(str)) {
            Bitmap bitmap = this.imageCache.get(str).get();
            if (bitmap != null) {
                imageCallback.imageLoaded(bitmap, imageView, str);
                return;
            }
        } else if (EnvironmentUtils.checkSdCardPermission(this.context)) {
            if (str.lastIndexOf("/") == -1 || str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR) == -1) {
                handler.sendMessage(handler.obtainMessage(0, null));
                return;
            }
            String strSubstring = str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
            File file = new File(EnvironmentUtils.getCommonDirPath(this.context) + BITMAP_CACHE);
            if (!file.exists()) {
                file.mkdirs();
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return;
            }
            while (i < fileArrListFiles.length && !strSubstring.equals(fileArrListFiles[i].getName())) {
                i++;
            }
            if (i < fileArrListFiles.length) {
                imageCallback.imageLoaded(BitmapFactory.decodeFile(EnvironmentUtils.getCommonDirPath(this.context) + BITMAP_CACHE + strSubstring), imageView, str);
                return;
            }
        }
        pool.execute(new Thread() { // from class: com.sqwan.common.util.AsyncImageLoader.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                File file2;
                Bitmap bitmapDecodeStream;
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setConnectTimeout(8000);
                    if (httpURLConnection.getResponseCode() != 200) {
                        handler.sendMessage(handler.obtainMessage(0, null));
                        return;
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    if (EnvironmentUtils.checkSdCardPermission(AsyncImageLoader.this.context)) {
                        File file3 = new File(EnvironmentUtils.getCommonDirPath(AsyncImageLoader.this.context) + AsyncImageLoader.BITMAP_CACHE);
                        if (!file3.exists()) {
                            file3.mkdirs();
                        }
                        String strSubstring2 = str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
                        file2 = new File(EnvironmentUtils.getCommonDirPath(AsyncImageLoader.this.context) + AsyncImageLoader.BITMAP_CACHE + strSubstring2);
                        try {
                            if (!file2.exists()) {
                                file2.createNewFile();
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i2 = inputStream.read(bArr);
                                if (i2 == -1) {
                                    break;
                                } else {
                                    fileOutputStream.write(bArr, 0, i2);
                                }
                            }
                            fileOutputStream.close();
                            inputStream.close();
                            bitmapDecodeStream = BitmapFactory.decodeFile(EnvironmentUtils.getCommonDirPath(AsyncImageLoader.this.context) + AsyncImageLoader.BITMAP_CACHE + strSubstring2);
                            if (bitmapDecodeStream == null) {
                                LogUtil.e("bitmap 网络加载失败");
                                if (file2.exists()) {
                                    file2.delete();
                                }
                            }
                        } catch (Exception unused) {
                            handler.sendMessage(handler.obtainMessage(0, null));
                            if (file2 == null || !file2.exists()) {
                                return;
                            }
                            file2.delete();
                            return;
                        }
                    } else {
                        bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
                        file2 = null;
                    }
                    handler.sendMessage(handler.obtainMessage(0, bitmapDecodeStream));
                    AsyncImageLoader.this.imageCache.put(str, new SoftReference(bitmapDecodeStream));
                } catch (Exception unused2) {
                    file2 = null;
                }
            }
        });
    }
}
