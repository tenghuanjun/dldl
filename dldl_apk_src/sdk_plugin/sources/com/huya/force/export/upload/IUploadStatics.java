package com.huya.force.export.upload;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IUploadStatics {
    public static final int kNetStateBad = 1;
    public static final int kNetStateGood = 0;

    int getNetState();

    long getTotalSendAudioBytes();

    int getTotalSendAudioFrames();

    int getTotalSendBlockCount();

    int getTotalSendBytes();

    int getTotalSendTime();

    long getTotalSendVideoBytes();

    int getTotalSendVideoFrames();
}
