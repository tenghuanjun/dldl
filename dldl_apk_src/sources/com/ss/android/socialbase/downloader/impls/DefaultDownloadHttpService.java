package com.ss.android.socialbase.downloader.impls;

import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.network.IDefaultDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpService;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.android.socialbase.downloader.utils.LruCache;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import okhttp3.Call;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DefaultDownloadHttpService implements IDownloadHttpService {
    private final LruCache<String, OkHttpClient> hostIpClientCache = new LruCache<>(4, 8);

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpService
    public IDownloadHttpConnection downloadWithConnection(int i, String str, List<HttpHeader> list) throws IOException {
        String value;
        OkHttpClient downloadClient;
        Request.Builder builderUrl = new Request.Builder().url(str);
        if (list == null || list.size() <= 0) {
            value = null;
        } else {
            value = null;
            for (HttpHeader httpHeader : list) {
                String name = httpHeader.getName();
                if (value == null && DownloadConstants.EXTRA_REQUEST_HOST_IP.equals(name)) {
                    value = httpHeader.getValue();
                } else {
                    builderUrl.addHeader(name, DownloadUtils.getEncodedStr(httpHeader.getValue()));
                }
            }
        }
        if (!TextUtils.isEmpty(value)) {
            downloadClient = createSpecialHostIpClient(str, value);
        } else {
            downloadClient = DownloadComponentManager.getDownloadClient();
        }
        if (downloadClient == null) {
            throw new IOException("can't get httpClient");
        }
        final Call callNewCall = downloadClient.newCall(builderUrl.build());
        final Response responseExecute = callNewCall.execute();
        if (responseExecute == null) {
            throw new IOException("can't get response");
        }
        final ResponseBody responseBodyBody = responseExecute.body();
        if (responseBodyBody == null) {
            return null;
        }
        InputStream inputStreamByteStream = responseBodyBody.byteStream();
        String strHeader = responseExecute.header(HttpHeaders.CONTENT_ENCODING);
        final InputStream gZIPInputStream = (strHeader == null || !"gzip".equalsIgnoreCase(strHeader) || (inputStreamByteStream instanceof GZIPInputStream)) ? inputStreamByteStream : new GZIPInputStream(inputStreamByteStream);
        return new IDefaultDownloadHttpConnection() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadHttpService.1
            @Override // com.ss.android.socialbase.downloader.network.AbsDownloadHttpConnection
            public String getHostIp() {
                return "";
            }

            @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
            public InputStream getInputStream() throws IOException {
                return gZIPInputStream;
            }

            @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
            public String getResponseHeaderField(String str2) {
                return responseExecute.header(str2);
            }

            @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
            public int getResponseCode() throws IOException {
                return responseExecute.code();
            }

            @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
            public void cancel() {
                Call call = callNewCall;
                if (call == null || call.isCanceled()) {
                    return;
                }
                callNewCall.cancel();
            }

            @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
            public void end() {
                try {
                    if (responseBodyBody != null) {
                        responseBodyBody.close();
                    }
                    if (callNewCall == null || callNewCall.isCanceled()) {
                        return;
                    }
                    callNewCall.cancel();
                } catch (Throwable unused) {
                }
            }
        };
    }

    private OkHttpClient createSpecialHostIpClient(String str, final String str2) {
        try {
            final String host = Uri.parse(str).getHost();
            if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(str2)) {
                String str3 = host + "_" + str2;
                synchronized (this.hostIpClientCache) {
                    OkHttpClient okHttpClient = this.hostIpClientCache.get(str3);
                    if (okHttpClient != null) {
                        return okHttpClient;
                    }
                    OkHttpClient.Builder builderCreateDownloadClientBuilder = DownloadComponentManager.createDownloadClientBuilder();
                    builderCreateDownloadClientBuilder.dns(new Dns() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadHttpService.2
                        @Override // okhttp3.Dns
                        public List<InetAddress> lookup(String str4) throws UnknownHostException {
                            if (TextUtils.equals(host, str4)) {
                                return Collections.singletonList(InetAddress.getByName(str2));
                            }
                            return Dns.SYSTEM.lookup(str4);
                        }
                    });
                    OkHttpClient okHttpClientBuild = builderCreateDownloadClientBuilder.build();
                    synchronized (this.hostIpClientCache) {
                        this.hostIpClientCache.put(str3, okHttpClientBuild);
                    }
                    return okHttpClientBuild;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return DownloadComponentManager.getDownloadClient();
    }
}
