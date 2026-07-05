package com.huya.live.utils.cache;

import com.duowan.auk.ArkValue;
import com.jakewharton.disklrucache.DiskLruCache;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DataCache extends FileCacheBase {
    private DiskLruCache mDataDiskLruCache;
    private String mFileDir;
    private int mMaxSize;
    private int mVersion;

    public DataCache() {
        this.mFileDir = "DataCache";
        this.mVersion = 1;
        this.mMaxSize = 1048576;
    }

    public DataCache(String str, int i, int i2) {
        this.mFileDir = "DataCache";
        this.mVersion = 1;
        this.mMaxSize = 1048576;
        this.mFileDir = str;
        this.mVersion = i;
        this.mMaxSize = i2;
    }

    public DiskLruCache getDiskLruCache() {
        if (this.mDataDiskLruCache == null) {
            this.mDataDiskLruCache = openDiskLruCache(ArkValue.gContext, this.mFileDir, this.mVersion, 1, this.mMaxSize);
        }
        return this.mDataDiskLruCache;
    }

    public boolean saveStringToCache(String str, String str2) {
        try {
            DiskLruCache diskLruCache = getDiskLruCache();
            if (diskLruCache == null) {
                return false;
            }
            DiskLruCache.Editor editorEdit = diskLruCache.edit(MD5.getMD5(str.getBytes()));
            editorEdit.newOutputStream(0).write(str2.getBytes());
            editorEdit.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public String readStringToCache(String str) {
        DiskLruCache.Snapshot snapshot;
        try {
            DiskLruCache diskLruCache = getDiskLruCache();
            if (diskLruCache != null && (snapshot = diskLruCache.get(MD5.getMD5(str.getBytes()))) != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                return new String(bArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void remove(String str) {
        if (this.mDataDiskLruCache == null) {
            this.mDataDiskLruCache = getDiskLruCache();
        }
        try {
            this.mDataDiskLruCache.remove(MD5.getMD5(str.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
