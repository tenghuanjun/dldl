package com.huya.live.utils.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.duowan.auk.ArkValue;
import com.jakewharton.disklrucache.DiskLruCache;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BitmapCache extends FileCacheBase {
    private static BitmapCache sBitmapCache;
    private DiskLruCache mDataDiskLruCache;
    private String mFileDir = "BitmapCache";
    private int mVersion = 1;
    private int mMaxSize = 10485760;

    public static BitmapCache getInstance() {
        if (sBitmapCache == null) {
            sBitmapCache = new BitmapCache();
        }
        return sBitmapCache;
    }

    public DiskLruCache getDiskLruCache() {
        if (this.mDataDiskLruCache == null) {
            this.mDataDiskLruCache = openDiskLruCache(ArkValue.gContext, this.mFileDir, this.mVersion, 1, this.mMaxSize);
        }
        return this.mDataDiskLruCache;
    }

    public boolean saveBitmapToCache(String str, Bitmap bitmap) {
        try {
            DiskLruCache diskLruCache = getDiskLruCache();
            if (diskLruCache == null) {
                return false;
            }
            DiskLruCache.Editor editorEdit = diskLruCache.edit(MD5.getMD5(str.getBytes()));
            OutputStream outputStreamNewOutputStream = editorEdit.newOutputStream(0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            outputStreamNewOutputStream.write(byteArrayOutputStream.toByteArray());
            editorEdit.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public Bitmap readBitmapToCache(String str) {
        DiskLruCache.Snapshot snapshot;
        try {
            DiskLruCache diskLruCache = getDiskLruCache();
            if (diskLruCache != null && (snapshot = diskLruCache.get(MD5.getMD5(str.getBytes()))) != null) {
                return BitmapFactory.decodeStream(snapshot.getInputStream(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
