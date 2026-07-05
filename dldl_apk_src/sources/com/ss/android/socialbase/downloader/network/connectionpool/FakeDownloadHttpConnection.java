package com.ss.android.socialbase.downloader.network.connectionpool;

import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpService;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class FakeDownloadHttpConnection implements IDownloadHttpConnection, IFakeDownloadHttpConnection {
    private boolean isRequesting;
    private IDownloadHttpConnection mConnection;
    private long mCreateTime;
    private InputStream mInputStream;
    protected final Object mJoinLock = new Object();
    private final int mMaxLength;
    private final List<HttpHeader> mRequestHeaders;
    private final String mUrl;

    public boolean isSuccessful(int i) {
        return i >= 200 && i < 300;
    }

    public FakeDownloadHttpConnection(int i, String str, List<HttpHeader> list, long j) {
        this.mMaxLength = i;
        this.mUrl = str;
        this.mRequestHeaders = list;
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public void execute() throws IOException, BaseException {
        if (this.mConnection != null) {
            return;
        }
        synchronized (this.mJoinLock) {
            try {
                this.isRequesting = true;
                IDownloadHttpConnection iDownloadHttpConnectionDoExecute = doExecute();
                this.mConnection = iDownloadHttpConnectionDoExecute;
                if (iDownloadHttpConnectionDoExecute != null) {
                    this.mCreateTime = System.currentTimeMillis();
                    this.mInputStream = this.mConnection.getInputStream();
                }
            } finally {
                this.isRequesting = false;
                this.mJoinLock.notifyAll();
            }
        }
    }

    private IDownloadHttpConnection doExecute() throws IOException, BaseException {
        IDownloadHttpService defaultHttpService = DownloadComponentManager.getDefaultHttpService();
        if (defaultHttpService != null) {
            return defaultHttpService.downloadWithConnection(this.mMaxLength, this.mUrl, this.mRequestHeaders);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public void joinExecute() throws InterruptedException {
        synchronized (this.mJoinLock) {
            if (this.isRequesting && this.mConnection == null) {
                this.mJoinLock.wait();
            }
        }
    }

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.mInputStream;
        if (inputStream != null) {
            return inputStream;
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHttpConnection
    public void end() {
        IDownloadHttpConnection iDownloadHttpConnection = this.mConnection;
        if (iDownloadHttpConnection != null) {
            iDownloadHttpConnection.end();
        }
    }

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
    public String getResponseHeaderField(String str) {
        IDownloadHttpConnection iDownloadHttpConnection = this.mConnection;
        if (iDownloadHttpConnection != null) {
            return iDownloadHttpConnection.getResponseHeaderField(str);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
    public int getResponseCode() throws IOException {
        IDownloadHttpConnection iDownloadHttpConnection = this.mConnection;
        if (iDownloadHttpConnection != null) {
            return iDownloadHttpConnection.getResponseCode();
        }
        return 0;
    }

    public List<HttpHeader> getRequestHeaders() {
        return this.mRequestHeaders;
    }

    @Override // com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
    public void cancel() {
        IDownloadHttpConnection iDownloadHttpConnection = this.mConnection;
        if (iDownloadHttpConnection != null) {
            iDownloadHttpConnection.cancel();
        }
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public boolean isSuccessful() {
        try {
            if (this.mConnection != null) {
                return isSuccessful(this.mConnection.getResponseCode());
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public boolean isValid() {
        return System.currentTimeMillis() - this.mCreateTime < DownloadPreconnecter.sConnectionOutdatedTime;
    }

    @Override // com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection
    public boolean isRequesting() {
        return this.isRequesting;
    }
}
