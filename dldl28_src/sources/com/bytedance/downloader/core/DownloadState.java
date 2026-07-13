package com.bytedance.downloader.core;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadState {
    NotStart,
    Prepare,
    Downloading,
    Downloaded,
    DownloadFailed,
    Cancelled,
    Verifying,
    VerifyFailed
}
