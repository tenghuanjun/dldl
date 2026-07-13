package com.bytedance.downloader.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadRequest extends DownloadInfo {

    public static class Builder {
        private String url = "";
        private String fileName = "";
        private String savePath = "";
        private String md5 = "";
        private final List hostIpList = new ArrayList();

        public DownloadRequest build() {
            h.a("== DOWNLOAD REQUEST ==");
            h.a(String.format(Locale.getDefault(), "url: %s", this.url));
            h.a(String.format(Locale.getDefault(), "file name: %s", this.fileName));
            h.a(String.format(Locale.getDefault(), "save path: %s", this.savePath));
            h.a(String.format(Locale.getDefault(), "md5: %s", this.md5));
            Iterator it = this.hostIpList.iterator();
            while (it.hasNext()) {
                h.a(String.format(Locale.getDefault(), "ip: %s", (String) it.next()));
            }
            h.a("== END ==");
            return new DownloadRequest(this);
        }

        public Builder fileName(String str) {
            this.fileName = str;
            return this;
        }

        public Builder hostIpList(List list) {
            this.hostIpList.addAll(list);
            return this;
        }

        public Builder md5(String str) {
            this.md5 = str;
            return this;
        }

        public Builder savePath(String str) {
            this.savePath = str;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }
    }

    private DownloadRequest(Builder builder) {
        if (builder == null) {
            return;
        }
        this.url = builder.url;
        this.fileName = builder.fileName;
        this.savePath = builder.savePath;
        this.md5 = builder.md5;
        this.timestamp = System.currentTimeMillis();
        this.hostIpList.addAll(builder.hostIpList);
    }
}
