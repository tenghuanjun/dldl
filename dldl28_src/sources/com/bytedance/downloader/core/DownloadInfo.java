package com.bytedance.downloader.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadInfo implements Comparable {
    protected String fileName;
    protected long fileSize;
    protected List hostIpList;
    protected String md5;
    protected String savePath;
    protected long timestamp;
    protected String url;

    protected DownloadInfo() {
        this.url = "";
        this.fileName = "";
        this.savePath = "";
        this.md5 = "";
        this.timestamp = 0L;
        this.fileSize = -1L;
        this.hostIpList = new ArrayList();
    }

    protected DownloadInfo(DownloadInfo downloadInfo) {
        this.url = "";
        this.fileName = "";
        this.savePath = "";
        this.md5 = "";
        this.timestamp = 0L;
        this.fileSize = -1L;
        ArrayList arrayList = new ArrayList();
        this.hostIpList = arrayList;
        this.url = downloadInfo.url;
        this.fileName = downloadInfo.fileName;
        this.savePath = downloadInfo.savePath;
        this.md5 = downloadInfo.md5;
        this.timestamp = downloadInfo.timestamp;
        this.fileSize = downloadInfo.fileSize;
        arrayList.addAll(downloadInfo.getHostIpList());
    }

    public boolean checkValidity() {
        return (q.a(this.url) || q.a(this.savePath)) ? false : true;
    }

    @Override // java.lang.Comparable
    public int compareTo(DownloadInfo downloadInfo) {
        return Long.compare(this.timestamp, downloadInfo.getTimestamp());
    }

    public boolean equals(Object obj) {
        if (obj instanceof DownloadInfo) {
            return toString().equals(((DownloadInfo) obj).toString());
        }
        return false;
    }

    public File getFile() {
        return new File(this.savePath, this.fileName);
    }

    public String getFileName() {
        return this.fileName;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public List getHostIpList() {
        return this.hostIpList;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getUrl() {
        return this.url;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setHostIpList(List list) {
        this.hostIpList.addAll(list);
    }

    public String toString() {
        return String.format("%s%s", this.url, this.savePath);
    }
}
