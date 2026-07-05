package com.duowan.auk.http;

import com.duowan.auk.http.HttpClient;
import com.huya.mtp.utils.IOUtils;
import com.huya.mtp.utils.ThreadUtils;
import java.io.File;
import java.util.List;
import java.util.Map;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class FileEasyHandler implements HttpClient.HttpHandler {
    private File mFile;

    public abstract void onFailure(FailReason failReason);

    public abstract void onSuccess(File file);

    public enum FailReason {
        Http(IDataSource.SCHEME_HTTP_TAG),
        FileStore("file_store");

        public final String name;

        FailReason(String str) {
            this.name = str;
        }
    }

    public FileEasyHandler(File file) {
        this.mFile = file;
    }

    @Override // com.duowan.auk.http.HttpClient.HttpHandler
    public void onSuccess(int i, Map<String, List<String>> map, final byte[] bArr) {
        ThreadUtils.runAsync(new Runnable() { // from class: com.duowan.auk.http.FileEasyHandler.1
            @Override // java.lang.Runnable
            public void run() {
                FileEasyHandler.this.writeToFile(bArr);
            }
        });
    }

    @Override // com.duowan.auk.http.HttpClient.HttpHandler
    public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
        onFailure(FailReason.Http);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeToFile(byte[] bArr) {
        if (IOUtils.writeBytes(this.mFile, bArr)) {
            onSuccess(this.mFile);
        } else {
            onFailure(FailReason.FileStore);
        }
    }
}
