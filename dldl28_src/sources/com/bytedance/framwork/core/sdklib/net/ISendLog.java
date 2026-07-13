package com.bytedance.framwork.core.sdklib.net;

/* JADX INFO: loaded from: classes2.dex */
public interface ISendLog {
    public static final int COMPRESS_TYPE_GZIP = 1;

    NetResponse sendLog(long j, String str, byte[] bArr, int i, String str2);
}
