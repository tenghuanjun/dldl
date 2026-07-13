package com.shuyu.gsyvideoplayer.cache;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.danikula.videocache.CacheListener;
import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.StorageUtils;
import com.danikula.videocache.file.FileNameGenerator;
import com.danikula.videocache.file.Md5FileNameGenerator;
import com.shuyu.gsyvideoplayer.cache.ICacheManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.TrustManager;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: classes3.dex */
public class ProxyCacheManager implements ICacheManager, CacheListener {
    public static int DEFAULT_MAX_COUNT = -1;
    public static int DEFAULT_MAX_SIZE = 536870912;
    private static FileNameGenerator fileNameGenerator;
    private static ProxyCacheManager proxyCacheManager;
    private ICacheManager.ICacheAvailableListener cacheAvailableListener;
    protected File mCacheDir;
    protected boolean mCacheFile;
    protected HttpProxyCacheServer proxy;
    private TrustManager[] trustAllCerts;
    protected ProxyCacheUserAgentHeadersInjector userAgentHeadersInjector = new ProxyCacheUserAgentHeadersInjector();
    private HostnameVerifier v;

    public static synchronized ProxyCacheManager instance() {
        if (proxyCacheManager == null) {
            proxyCacheManager = new ProxyCacheManager();
        }
        return proxyCacheManager;
    }

    @Override // com.danikula.videocache.CacheListener
    public void onCacheAvailable(File file, String str, int i) {
        ICacheManager.ICacheAvailableListener iCacheAvailableListener = this.cacheAvailableListener;
        if (iCacheAvailableListener != null) {
            iCacheAvailableListener.onCacheAvailable(file, str, i);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.cache.ICacheManager
    public void doCacheLogic(Context context, IMediaPlayer iMediaPlayer, String str, Map<String, String> map, File file) {
        ProxyCacheUserAgentHeadersInjector.mMapHeadData.clear();
        if (map != null) {
            ProxyCacheUserAgentHeadersInjector.mMapHeadData.putAll(map);
        }
        if (str.startsWith("http") && !str.contains("127.0.0.1") && !str.contains(".m3u8")) {
            HttpProxyCacheServer proxy = getProxy(context.getApplicationContext(), file);
            if (proxy != null) {
                String proxyUrl = proxy.getProxyUrl(str);
                boolean zStartsWith = proxyUrl.startsWith("http");
                this.mCacheFile = !zStartsWith;
                if (zStartsWith) {
                    proxy.registerCacheListener(this, str);
                }
                str = proxyUrl;
            }
        } else if (!str.startsWith("http") && !str.startsWith("rtmp") && !str.startsWith("rtsp") && !str.contains(".m3u8")) {
            this.mCacheFile = true;
        }
        try {
            iMediaPlayer.setDataSource(context, Uri.parse(str), map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.cache.ICacheManager
    public void clearCache(Context context, File file, String str) {
        if (TextUtils.isEmpty(str)) {
            if (file == null) {
                FileUtils.deleteFiles(new File(StorageUtils.getIndividualCacheDirectory(context.getApplicationContext()).getAbsolutePath()));
                return;
            } else {
                FileUtils.deleteFiles(file);
                return;
            }
        }
        FileNameGenerator md5FileNameGenerator = new Md5FileNameGenerator();
        FileNameGenerator fileNameGenerator2 = fileNameGenerator;
        if (fileNameGenerator2 != null) {
            md5FileNameGenerator = fileNameGenerator2;
        }
        String strGenerate = md5FileNameGenerator.generate(str);
        if (file != null) {
            String str2 = file.getAbsolutePath() + File.separator + strGenerate + ".download";
            String str3 = file.getAbsolutePath() + File.separator + strGenerate;
            CommonUtil.deleteFile(str2);
            CommonUtil.deleteFile(str3);
            return;
        }
        String str4 = StorageUtils.getIndividualCacheDirectory(context.getApplicationContext()).getAbsolutePath() + File.separator + strGenerate + ".download";
        String str5 = StorageUtils.getIndividualCacheDirectory(context.getApplicationContext()).getAbsolutePath() + File.separator + strGenerate;
        CommonUtil.deleteFile(str4);
        CommonUtil.deleteFile(str5);
    }

    @Override // com.shuyu.gsyvideoplayer.cache.ICacheManager
    public void release() {
        HttpProxyCacheServer httpProxyCacheServer = this.proxy;
        if (httpProxyCacheServer != null) {
            try {
                httpProxyCacheServer.unregisterCacheListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.shuyu.gsyvideoplayer.cache.ICacheManager
    public boolean cachePreview(Context context, File file, String str) {
        HttpProxyCacheServer proxy = getProxy(context.getApplicationContext(), file);
        if (proxy != null) {
            str = proxy.getProxyUrl(str);
        }
        return !str.startsWith("http");
    }

    @Override // com.shuyu.gsyvideoplayer.cache.ICacheManager
    public boolean hadCached() {
        return this.mCacheFile;
    }

    @Override // com.shuyu.gsyvideoplayer.cache.ICacheManager
    public void setCacheAvailableListener(ICacheManager.ICacheAvailableListener iCacheAvailableListener) {
        this.cacheAvailableListener = iCacheAvailableListener;
    }

    public HttpProxyCacheServer newProxy(Context context, File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        HttpProxyCacheServer.Builder builder = new HttpProxyCacheServer.Builder(context);
        builder.cacheDirectory(file);
        int i = DEFAULT_MAX_COUNT;
        if (i > 0) {
            builder.maxCacheFilesCount(i);
        } else {
            builder.maxCacheSize(DEFAULT_MAX_SIZE);
        }
        builder.headerInjector(this.userAgentHeadersInjector);
        builder.hostnameVerifier(this.v);
        builder.trustAllCerts(this.trustAllCerts);
        FileNameGenerator fileNameGenerator2 = fileNameGenerator;
        if (fileNameGenerator2 != null) {
            builder.fileNameGenerator(fileNameGenerator2);
        }
        this.mCacheDir = file;
        return builder.build();
    }

    public void setProxy(HttpProxyCacheServer httpProxyCacheServer) {
        this.proxy = httpProxyCacheServer;
    }

    public HttpProxyCacheServer newProxy(Context context) {
        HttpProxyCacheServer.Builder builderHeaderInjector = new HttpProxyCacheServer.Builder(context.getApplicationContext()).headerInjector(this.userAgentHeadersInjector);
        int i = DEFAULT_MAX_COUNT;
        if (i > 0) {
            builderHeaderInjector.maxCacheFilesCount(i);
        } else {
            builderHeaderInjector.maxCacheSize(DEFAULT_MAX_SIZE);
        }
        builderHeaderInjector.hostnameVerifier(this.v);
        builderHeaderInjector.trustAllCerts(this.trustAllCerts);
        return builderHeaderInjector.build();
    }

    protected static HttpProxyCacheServer getProxy(Context context) {
        HttpProxyCacheServer httpProxyCacheServer = instance().proxy;
        if (httpProxyCacheServer != null) {
            return httpProxyCacheServer;
        }
        ProxyCacheManager proxyCacheManagerInstance = instance();
        HttpProxyCacheServer httpProxyCacheServerNewProxy = instance().newProxy(context);
        proxyCacheManagerInstance.proxy = httpProxyCacheServerNewProxy;
        return httpProxyCacheServerNewProxy;
    }

    public static HttpProxyCacheServer getProxy(Context context, File file) {
        if (file == null) {
            return getProxy(context);
        }
        if (instance().mCacheDir != null && !instance().mCacheDir.getAbsolutePath().equals(file.getAbsolutePath())) {
            HttpProxyCacheServer httpProxyCacheServer = instance().proxy;
            if (httpProxyCacheServer != null) {
                httpProxyCacheServer.shutdown();
            }
            ProxyCacheManager proxyCacheManagerInstance = instance();
            HttpProxyCacheServer httpProxyCacheServerNewProxy = instance().newProxy(context, file);
            proxyCacheManagerInstance.proxy = httpProxyCacheServerNewProxy;
            return httpProxyCacheServerNewProxy;
        }
        HttpProxyCacheServer httpProxyCacheServer2 = instance().proxy;
        if (httpProxyCacheServer2 != null) {
            return httpProxyCacheServer2;
        }
        ProxyCacheManager proxyCacheManagerInstance2 = instance();
        HttpProxyCacheServer httpProxyCacheServerNewProxy2 = instance().newProxy(context, file);
        proxyCacheManagerInstance2.proxy = httpProxyCacheServerNewProxy2;
        return httpProxyCacheServerNewProxy2;
    }

    public static void setFileNameGenerator(FileNameGenerator fileNameGenerator2) {
        fileNameGenerator = fileNameGenerator2;
    }

    public static void clearFileNameGenerator() {
        fileNameGenerator = null;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.v;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.v = hostnameVerifier;
    }

    public TrustManager[] getTrustAllCerts() {
        return this.trustAllCerts;
    }

    public void setTrustAllCerts(TrustManager[] trustManagerArr) {
        this.trustAllCerts = trustManagerArr;
    }
}
