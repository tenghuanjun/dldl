package com.bytedance.downloader.core;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadResponse extends DownloadInfo {
    private final Map extras;
    private int progress;
    private DownloadState state;

    public DownloadResponse(DownloadInfo downloadInfo) {
        super(downloadInfo);
        this.progress = 0;
        this.state = DownloadState.NotStart;
        this.extras = new HashMap();
    }

    public DownloadResponse(DownloadResponse downloadResponse) {
        super(downloadResponse);
        this.progress = 0;
        this.state = DownloadState.NotStart;
        HashMap map = new HashMap();
        this.extras = map;
        this.progress = downloadResponse.getProgress();
        this.state = downloadResponse.getState();
        map.putAll(downloadResponse.getExtras());
    }

    public synchronized Map getExtras() {
        return this.extras;
    }

    public int getProgress() {
        return this.progress;
    }

    public DownloadState getState() {
        return this.state;
    }

    public synchronized void putExtra(String str, String str2) {
        if (!q.a(str) && !q.a(str2)) {
            this.extras.put(str, str2);
        }
        if (!q.a(str) && q.a(str2)) {
            this.extras.remove(str);
        }
    }

    public synchronized void putExtras(Map map) {
        this.extras.putAll(map);
    }

    public synchronized void setExtras(Map map) {
        this.extras.clear();
        this.extras.putAll(map);
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public void setState(DownloadState downloadState) {
        this.state = downloadState;
    }
}
