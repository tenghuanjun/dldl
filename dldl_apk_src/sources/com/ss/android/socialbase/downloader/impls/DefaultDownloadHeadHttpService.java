package com.ss.android.socialbase.downloader.impls;

import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHeadHttpService;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DefaultDownloadHeadHttpService implements IDownloadHeadHttpService {
    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpService
    public IDownloadHeadHttpConnection downloadWithConnection(String str, List<HttpHeader> list) throws IOException {
        OkHttpClient downloadClient = DownloadComponentManager.getDownloadClient();
        if (downloadClient == null) {
            throw new IOException("can't get httpClient");
        }
        Request.Builder builderHead = new Request.Builder().url(str).head();
        if (list != null && list.size() > 0) {
            for (HttpHeader httpHeader : list) {
                builderHead.addHeader(httpHeader.getName(), DownloadUtils.getEncodedStr(httpHeader.getValue()));
            }
        }
        final Call callNewCall = downloadClient.newCall(builderHead.build());
        final Response responseExecute = callNewCall.execute();
        if (responseExecute == null) {
            throw new IOException("can't get response");
        }
        if (DownloadExpSwitchCode.isSwitchEnable(2097152)) {
            responseExecute.close();
        }
        return new IDownloadHeadHttpConnection() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadHeadHttpService.1
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
        };
    }
}
