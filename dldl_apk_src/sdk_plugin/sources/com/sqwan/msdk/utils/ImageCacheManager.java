package com.sqwan.msdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sqwan.common.util.LogUtil;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ImageCacheManager {
    private static final String CACHE_DIR = "sq_image_cache";
    private static final int CONNECT_TIMEOUT = 3000;
    private static final int READ_TIMEOUT = 6000;
    private static final String TAG = "ImageCacheManager";
    private static ImageCacheManager sInstance;
    private File mCacheDir;
    private Context mContext;
    private ThreadPoolExecutor mImageThreadPool;
    private Handler mMainHandler;

    public interface ImageDownloadCallback {
        void onAllCompleted();

        void onProgress(int i, int i2);
    }

    public interface ImageLoadCallback {
        void onError(String str);

        void onSuccess(Bitmap bitmap);
    }

    private ImageCacheManager(Context context) {
        this.mContext = context.getApplicationContext();
        initThreadPool();
        initDiskCache();
    }

    private void initThreadPool() {
        this.mImageThreadPool = new ThreadPoolExecutor(2, 4, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(20), new ThreadPoolExecutor.CallerRunsPolicy());
        this.mMainHandler = new Handler(Looper.getMainLooper());
    }

    public static synchronized ImageCacheManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ImageCacheManager(context);
        }
        return sInstance;
    }

    private void initDiskCache() {
        File file = new File(this.mContext.getCacheDir(), CACHE_DIR);
        this.mCacheDir = file;
        if (!file.exists()) {
            LogUtil.i(TAG, "创建缓存目录: " + this.mCacheDir.getAbsolutePath() + ", 结果: " + this.mCacheDir.mkdirs());
            return;
        }
        clearImageCacheFiles();
    }

    private void clearImageCacheFiles() {
        try {
            File[] fileArrListFiles = this.mCacheDir.listFiles();
            if (fileArrListFiles != null) {
                int i = 0;
                for (File file : fileArrListFiles) {
                    if (file.isFile() && file.getName().endsWith(".jpg")) {
                        boolean zDelete = file.delete();
                        if (zDelete) {
                            i++;
                        }
                        LogUtil.d(TAG, "删除图片缓存文件: " + file.getName() + ", 结果: " + zDelete);
                    }
                }
                LogUtil.i(TAG, "初始化清理完成，删除图片文件数量: " + i);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "初始化清理图片缓存失败: " + e.getMessage());
        }
    }

    public void downloadImages(List<String> list, final ImageDownloadCallback imageDownloadCallback) {
        if (list == null || list.isEmpty()) {
            if (imageDownloadCallback != null) {
                imageDownloadCallback.onAllCompleted();
                return;
            }
            return;
        }
        final ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addUrlIfNotEmpty(arrayList, it.next());
        }
        if (!arrayList.isEmpty()) {
            this.mImageThreadPool.execute(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.1
                @Override // java.lang.Runnable
                public void run() {
                    ImageCacheManager.this.downloadImagesSync(arrayList, imageDownloadCallback);
                }
            });
        } else if (imageDownloadCallback != null) {
            imageDownloadCallback.onAllCompleted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadImagesSync(List<String> list, final ImageDownloadCallback imageDownloadCallback) {
        final int size = list.size();
        final int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            String str = list.get(i);
            if (isImageCached(str) || downloadAndSaveToDisk(str)) {
                i2++;
            }
            i++;
            if (imageDownloadCallback != null) {
                this.mMainHandler.post(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        imageDownloadCallback.onProgress(i, size);
                    }
                });
            }
        }
        if (imageDownloadCallback != null) {
            this.mMainHandler.post(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.3
                @Override // java.lang.Runnable
                public void run() {
                    imageDownloadCallback.onAllCompleted();
                }
            });
        }
        LogUtil.i(TAG, "批量下载完成: 成功" + i2 + "/" + size);
    }

    private void addUrlIfNotEmpty(List<String> list, String str) {
        if (TextUtils.isEmpty(str) || list.contains(str)) {
            return;
        }
        list.add(str);
    }

    public void loadImage(final String str, final ImageView imageView, final ImageLoadCallback imageLoadCallback) {
        if (!TextUtils.isEmpty(str)) {
            this.mImageThreadPool.execute(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.5
                @Override // java.lang.Runnable
                public void run() {
                    ImageCacheManager.this.loadImageSync(str, imageView, imageLoadCallback);
                }
            });
        } else if (imageLoadCallback != null) {
            this.mMainHandler.post(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.4
                @Override // java.lang.Runnable
                public void run() {
                    imageLoadCallback.onError("图片URL为空");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadImageSync(String str, final ImageView imageView, final ImageLoadCallback imageLoadCallback) {
        try {
            final Bitmap bitmapFromDiskCache = getBitmapFromDiskCache(str);
            if (bitmapFromDiskCache != null) {
                this.mMainHandler.post(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.6
                    @Override // java.lang.Runnable
                    public void run() {
                        Bitmap bitmap;
                        if (imageView != null && (bitmap = bitmapFromDiskCache) != null && !bitmap.isRecycled()) {
                            imageView.setImageBitmap(bitmapFromDiskCache);
                        }
                        ImageLoadCallback imageLoadCallback2 = imageLoadCallback;
                        if (imageLoadCallback2 != null) {
                            imageLoadCallback2.onSuccess(bitmapFromDiskCache);
                        }
                    }
                });
                return;
            }
            if (downloadAndSaveToDisk(str)) {
                final Bitmap bitmapFromDiskCache2 = getBitmapFromDiskCache(str);
                if (bitmapFromDiskCache2 != null) {
                    this.mMainHandler.post(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.7
                        @Override // java.lang.Runnable
                        public void run() {
                            Bitmap bitmap;
                            if (imageView != null && (bitmap = bitmapFromDiskCache2) != null && !bitmap.isRecycled()) {
                                imageView.setImageBitmap(bitmapFromDiskCache2);
                            }
                            ImageLoadCallback imageLoadCallback2 = imageLoadCallback;
                            if (imageLoadCallback2 != null) {
                                imageLoadCallback2.onSuccess(bitmapFromDiskCache2);
                            }
                        }
                    });
                    return;
                } else {
                    this.mMainHandler.post(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.8
                        @Override // java.lang.Runnable
                        public void run() {
                            ImageLoadCallback imageLoadCallback2 = imageLoadCallback;
                            if (imageLoadCallback2 != null) {
                                imageLoadCallback2.onError("下载成功但无法读取图片");
                            }
                        }
                    });
                    return;
                }
            }
            this.mMainHandler.post(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.9
                @Override // java.lang.Runnable
                public void run() {
                    ImageLoadCallback imageLoadCallback2 = imageLoadCallback;
                    if (imageLoadCallback2 != null) {
                        imageLoadCallback2.onError("下载图片失败");
                    }
                }
            });
        } catch (Exception e) {
            LogUtil.e(TAG, "加载图片异常: " + str + ", " + e.getMessage());
            e.printStackTrace();
            this.mMainHandler.post(new Runnable() { // from class: com.sqwan.msdk.utils.ImageCacheManager.10
                @Override // java.lang.Runnable
                public void run() {
                    ImageLoadCallback imageLoadCallback2 = imageLoadCallback;
                    if (imageLoadCallback2 != null) {
                        imageLoadCallback2.onError("加载图片异常: " + e.getMessage());
                    }
                }
            });
        }
    }

    private Bitmap getBitmapFromDiskCache(String str) {
        Bitmap bitmapDecodeFile;
        try {
            File file = new File(this.mCacheDir, generateCacheKey(str) + ".jpg");
            if (!file.exists() || file.length() <= 0 || (bitmapDecodeFile = BitmapFactory.decodeFile(file.getAbsolutePath())) == null) {
                return null;
            }
            LogUtil.d(TAG, "从磁盘缓存加载图片成功: " + str);
            return bitmapDecodeFile;
        } catch (Exception e) {
            LogUtil.e(TAG, "从磁盘缓存读取图片失败: " + e.getMessage());
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:209:0x050e A[Catch: all -> 0x0612, TRY_LEAVE, TryCatch #45 {all -> 0x0612, blocks: (B:207:0x04ea, B:209:0x050e), top: B:330:0x04ea }] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0573  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x05ee  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0664  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0669  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x06e2  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x05ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0628 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0532 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:316:0x06a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:320:0x05ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0646 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0550 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:336:0x06c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:361:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:366:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean downloadAndSaveToDisk(java.lang.String r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1795
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.msdk.utils.ImageCacheManager.downloadAndSaveToDisk(java.lang.String):boolean");
    }

    private String generateCacheKey(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(String.format("%02x", Byte.valueOf(b)));
            }
            return sb.toString();
        } catch (Exception e) {
            LogUtil.e(TAG, "生成缓存key失败: " + e.getMessage());
            return String.valueOf(str.hashCode());
        }
    }

    public void clearDiskCache() {
        try {
            File[] fileArrListFiles = this.mCacheDir.listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    LogUtil.d(TAG, "删除缓存文件: " + file.getName() + ", 结果: " + file.delete());
                }
            }
            LogUtil.i(TAG, "磁盘缓存已清理");
        } catch (Exception e) {
            LogUtil.e(TAG, "清理磁盘缓存失败: " + e.getMessage());
        }
    }

    public long getCacheSize() {
        long length = 0;
        try {
            File[] fileArrListFiles = this.mCacheDir.listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    length += file.length();
                }
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "计算缓存大小失败: " + e.getMessage());
        }
        return length;
    }

    public boolean isAllImagesCached(List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.w(TAG, "图片URL列表为空");
            return true;
        }
        for (String str : list) {
            if (!isImageCached(str)) {
                LogUtil.d(TAG, "图片未缓存: " + str);
                return false;
            }
        }
        LogUtil.i(TAG, "所有图片都已缓存，总数: " + list.size());
        return true;
    }

    public boolean isImageCached(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            String strGenerateCacheKey = generateCacheKey(str);
            File file = new File(this.mCacheDir, strGenerateCacheKey + ".jpg");
            if (file.exists() && file.length() > 0) {
                LogUtil.d(TAG, "图片在磁盘缓存中: " + str);
                return true;
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "检查磁盘缓存失败: " + e.getMessage());
        }
        LogUtil.d(TAG, "图片未缓存: " + str);
        return false;
    }

    public List<String> getUncachedImageUrls(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                if (!isImageCached(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public String getCacheStatusInfo(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "图片URL列表为空";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("图片缓存状态 [总数: ");
        sb.append(list.size());
        sb.append("]:\n");
        int i = 0;
        while (i < list.size()) {
            String str = list.get(i);
            sb.append("- 图片");
            int i2 = i + 1;
            sb.append(i2);
            sb.append(": ");
            sb.append(isImageCached(str) ? "已缓存" : "未缓存");
            sb.append(" (");
            sb.append(str);
            sb.append(")");
            sb.append(i < list.size() + (-1) ? ShellAdbUtils.COMMAND_LINE_END : "");
            i = i2;
        }
        return sb.toString();
    }

    public void cleanup() {
        ThreadPoolExecutor threadPoolExecutor = this.mImageThreadPool;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
            return;
        }
        this.mImageThreadPool.shutdown();
        try {
            if (this.mImageThreadPool.awaitTermination(5L, TimeUnit.SECONDS)) {
                return;
            }
            this.mImageThreadPool.shutdownNow();
        } catch (InterruptedException unused) {
            this.mImageThreadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
