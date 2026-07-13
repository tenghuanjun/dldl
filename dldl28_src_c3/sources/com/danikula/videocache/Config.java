package com.danikula.videocache;

import com.danikula.videocache.file.DiskUsage;
import com.danikula.videocache.file.FileNameGenerator;
import com.danikula.videocache.headers.HeaderInjector;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import java.io.File;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
class Config {
    public final File cacheRoot;
    public final DiskUsage diskUsage;
    public final FileNameGenerator fileNameGenerator;
    public final HeaderInjector headerInjector;
    public final SourceInfoStorage sourceInfoStorage;
    public final TrustManager[] trustAllCerts;
    public final HostnameVerifier v;

    Config(File file, FileNameGenerator fileNameGenerator, DiskUsage diskUsage, SourceInfoStorage sourceInfoStorage, HeaderInjector headerInjector, HostnameVerifier hostnameVerifier, TrustManager[] trustManagerArr) {
        this.cacheRoot = file;
        this.fileNameGenerator = fileNameGenerator;
        this.diskUsage = diskUsage;
        this.sourceInfoStorage = sourceInfoStorage;
        this.headerInjector = headerInjector;
        this.v = hostnameVerifier;
        this.trustAllCerts = trustManagerArr;
    }

    File generateCacheFile(String str) {
        return new File(this.cacheRoot, this.fileNameGenerator.generate(str));
    }
}
