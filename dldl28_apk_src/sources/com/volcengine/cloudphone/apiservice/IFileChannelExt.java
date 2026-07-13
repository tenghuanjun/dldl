package com.volcengine.cloudphone.apiservice;

import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface IFileChannelExt {

    public interface IReceiveFileListener {
        void onCancel(File file, Map<String, String> map);

        void onComplete(File file, Map<String, String> map);

        void onError(File file, Map<String, String> map, int i);

        void onProgress(File file, Map<String, String> map, int i);

        void onStart(File file, Map<String, String> map);
    }

    public interface ISendFileListener {
        void onCancel(File file, Map<String, String> map);

        void onComplete(File file, Map<String, String> map);

        void onError(File file, Map<String, String> map, int i);

        void onProgress(File file, Map<String, String> map, int i);

        void onStart(File file, Map<String, String> map);
    }

    void setReceiveFileListener(IReceiveFileListener iReceiveFileListener);

    void startSendFile(File file, Map<String, String> map, ISendFileListener iSendFileListener);

    void stopReceiveFile(File file);

    void stopSendFile(File file);
}
