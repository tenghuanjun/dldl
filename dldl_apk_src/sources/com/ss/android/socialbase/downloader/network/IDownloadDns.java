package com.ss.android.socialbase.downloader.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IDownloadDns {
    List<InetAddress> lookup(String str) throws UnknownHostException;
}
