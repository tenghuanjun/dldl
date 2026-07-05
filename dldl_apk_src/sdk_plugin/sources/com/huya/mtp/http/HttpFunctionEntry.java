package com.huya.mtp.http;

import android.text.TextUtils;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.http.Cache;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpFunctionEntry {
    private static final String DEFAULT_CACHE_DIR = "mtp_http";
    private static final String TAG = "Http";
    private static Cache sDefaultCache;
    public static final HandlerExecutor sCacheHandlerExecutor = new HandlerExecutor("HttpCacheThread");
    public static final HandlerExecutor sDispatchHandlerExecutor = new HandlerExecutor("HttpDispatcherThread");
    private static Map<String, Cache> sCacheMap = new HashMap();

    public static Cache.Entry getCache(String str) {
        return getCache(null, str);
    }

    private static Cache getCacheManager(String str) {
        if (TextUtils.isEmpty(str)) {
            return getDefaultCache();
        }
        Cache cacheForDir = getCacheForDir(str);
        return cacheForDir == null ? getDefaultCache() : cacheForDir;
    }

    private static Cache getDefaultCache() {
        if (sDefaultCache == null) {
            DiskBasedCache diskBasedCache = new DiskBasedCache(new File(MTPApi.CONTEXT.getApplication().getCacheDir(), DEFAULT_CACHE_DIR));
            sDefaultCache = diskBasedCache;
            diskBasedCache.initialize();
        }
        return sDefaultCache;
    }

    private static Cache getCacheForDir(String str) {
        Cache diskBasedCache = sCacheMap.get(str);
        if (diskBasedCache != null) {
            return diskBasedCache;
        }
        File file = new File(str);
        if ((file.exists() || file.mkdirs()) && file.isDirectory()) {
            diskBasedCache = new DiskBasedCache(file);
            try {
                MTPApi.LOGGER.info(TAG, "initialize cache for %s", str);
                diskBasedCache.initialize();
                sCacheMap.put(str, diskBasedCache);
            } catch (Throwable th) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                    MTPApi.LOGGER.info(TAG, "initialize cache for %s failed, child list as below", str);
                    for (File file2 : fileArrListFiles) {
                        MTPApi.LOGGER.info(TAG, "fileName %s, fileSize %d", file2.getName(), Long.valueOf(file.length()));
                        file2.delete();
                    }
                    file.delete();
                }
                throw th;
            }
        } else {
            MTPApi.LOGGER.warn(TAG, "file is not exist or is ");
        }
        return diskBasedCache;
    }

    public static Cache.Entry getCache(String str, String str2) {
        try {
            return getCacheManager(str).get(str2);
        } catch (Exception e) {
            MTPApi.LOGGER.debug("error when getCache for key %s", str2);
            MTPApi.LOGGER.error("stack when getCache ", e);
            return null;
        }
    }

    public static void setCache(String str, Cache.Entry entry) {
        setCache(null, str, entry);
    }

    public static void setCache(String str, String str2, Cache.Entry entry) {
        Cache cacheManager = getCacheManager(str);
        if (entry != null) {
            cacheManager.put(str2, entry);
        } else {
            cacheManager.remove(str2);
        }
    }

    public static void setDiscCacheAsync(String str, Cache.Entry entry) {
        setDiscCacheAsync(null, str, entry);
    }

    public static void setDiscCacheAsync(final String str, final String str2, final Cache.Entry entry) {
        sCacheHandlerExecutor.execute(new Runnable() { // from class: com.huya.mtp.http.HttpFunctionEntry.1
            @Override // java.lang.Runnable
            public void run() {
                HttpFunctionEntry.setCache(str, str2, entry);
            }
        });
    }
}
