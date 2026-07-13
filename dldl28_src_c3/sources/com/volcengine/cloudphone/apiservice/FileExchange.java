package com.volcengine.cloudphone.apiservice;

import java.io.File;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface FileExchange {

    public interface IPullFileListener {
        void onCancel(File file);

        void onComplete(File file, String str);

        void onError(File file, int i);

        void onProgress(File file, int i);

        void onStart(File file);
    }

    public interface IPushFileListener {
        void onCancel(File file);

        void onComplete(File file);

        void onError(File file, int i);

        void onProgress(File file, int i);

        void onStart(File file);
    }

    void startPullFile(File file, IPullFileListener iPullFileListener);

    void startPullFile(File file, File file2, IPullFileListener iPullFileListener);

    void startPushFile(File file, File file2, IPushFileListener iPushFileListener);

    void stopPullFile(File file);

    void stopPushFile(File file);
}
