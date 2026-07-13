package com.bytedance.downloader.core;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadConfig {
    private final Builder mBuilder;

    public static class Builder {
        private File cacheDirectory;
        private ExecutorService executor;
        private int maxTask = 1;
        private int maxChunk = 3;
        private int retryCount = 5;
        private int retryMode = 0;
        private int retryInterval = 500;
        private int cacheExpiredTime = 900000;
        private int connectTimeout = 10000;
        private int readTimeout = 20000;
        private int bandwidthLimit = 0;
        private int dnsSelectStrategy = 0;
        private boolean supportBreakpointResume = true;
        private boolean supportFileVerification = false;
        private boolean overwriteExistTask = false;
        private boolean disappearWhenTaskIsDone = false;
        private boolean forceFlushWhenWriteFile = false;
        private boolean useFileStreamWriteFile = false;
        private boolean ignoreCertificateVerify = false;
        private final Map dnsResolver = new ConcurrentHashMap();
        private boolean logger = false;

        public Builder addDnsResolver(String str, String str2) {
            Set set;
            if (this.dnsResolver.containsKey(str)) {
                set = (Set) this.dnsResolver.get(str);
            } else {
                HashSet hashSet = new HashSet();
                this.dnsResolver.put(str, hashSet);
                set = hashSet;
            }
            set.add(str2);
            return this;
        }

        public Builder bandwidthLimit(int i) {
            this.bandwidthLimit = Math.max(0, i);
            return this;
        }

        public DownloadConfig build() {
            h.a(this.logger);
            h.a("== DOWNLOAD SDK CONFIGURATION ==");
            h.a(String.format(Locale.getDefault(), "max task: %d", Integer.valueOf(this.maxTask)));
            h.a(String.format(Locale.getDefault(), "max chunk: %d", Integer.valueOf(this.maxChunk)));
            h.a(String.format(Locale.getDefault(), "retry count: %d", Integer.valueOf(this.retryCount)));
            h.a(String.format(Locale.getDefault(), "retry mode: %d", Integer.valueOf(this.retryMode)));
            h.a(String.format(Locale.getDefault(), "retry interval: %d", Integer.valueOf(this.retryInterval)));
            h.a(String.format(Locale.getDefault(), "cache expired time: %d", Integer.valueOf(this.cacheExpiredTime)));
            h.a(String.format(Locale.getDefault(), "connect timeout: %d", Integer.valueOf(this.connectTimeout)));
            h.a(String.format(Locale.getDefault(), "read timeout: %d", Integer.valueOf(this.readTimeout)));
            h.a(String.format(Locale.getDefault(), "bandwidth limit: %d", Integer.valueOf(this.bandwidthLimit)));
            h.a(String.format(Locale.getDefault(), "breakpoint resume: %b", Boolean.valueOf(this.supportBreakpointResume)));
            h.a(String.format(Locale.getDefault(), "file verification: %b", Boolean.valueOf(this.supportFileVerification)));
            h.a(String.format(Locale.getDefault(), "overwrite exist task: %b", Boolean.valueOf(this.overwriteExistTask)));
            h.a(String.format(Locale.getDefault(), "disappear when task is done: %b", Boolean.valueOf(this.disappearWhenTaskIsDone)));
            h.a(String.format(Locale.getDefault(), "dns select strategy: %d", Integer.valueOf(this.dnsSelectStrategy)));
            h.a(String.format(Locale.getDefault(), "force flush when writing file: %b", Boolean.valueOf(this.forceFlushWhenWriteFile)));
            h.a(String.format(Locale.getDefault(), "ignore CA certificate verify: %b", Boolean.valueOf(this.ignoreCertificateVerify)));
            for (String str : this.dnsResolver.keySet()) {
                h.a(String.format(Locale.getDefault(), str + ": " + Arrays.toString(((Set) this.dnsResolver.get(str)).toArray()), new Object[0]));
            }
            h.a("== END ==");
            return new DownloadConfig(this);
        }

        public Builder cacheDirectory(File file) {
            this.cacheDirectory = file;
            return this;
        }

        public Builder cacheExpiredTime(int i) {
            this.cacheExpiredTime = Math.max(0, i);
            return this;
        }

        public Builder connectTimeout(int i) {
            this.connectTimeout = Math.max(1, i);
            return this;
        }

        public Builder disappearWhenTaskIsDone(boolean z) {
            this.disappearWhenTaskIsDone = z;
            return this;
        }

        public Builder dnsSelectStrategy(int i) {
            this.dnsSelectStrategy = i;
            return this;
        }

        public Builder executor(ExecutorService executorService) {
            this.executor = executorService;
            return this;
        }

        public Builder forceFlushWhenWriteFile(boolean z) {
            this.forceFlushWhenWriteFile = z;
            return this;
        }

        public Builder ignoreCertificateVerify(boolean z) {
            this.ignoreCertificateVerify = z;
            return this;
        }

        public Builder logger(boolean z) {
            this.logger = z;
            return this;
        }

        public Builder maxChunk(int i) {
            this.maxChunk = Math.max(1, i);
            return this;
        }

        public Builder maxTask(int i) {
            this.maxTask = Math.max(1, i);
            return this;
        }

        public Builder overwriteExistTask(boolean z) {
            this.overwriteExistTask = z;
            return this;
        }

        public Builder readTimeout(int i) {
            this.readTimeout = Math.max(1, i);
            return this;
        }

        public Builder retryCount(int i) {
            this.retryCount = Math.max(0, i);
            return this;
        }

        public Builder retryInterval(int i) {
            this.retryInterval = Math.max(0, i);
            return this;
        }

        public Builder retryMode(int i) {
            this.retryMode = Math.max(0, i);
            return this;
        }

        public Builder supportBreakpointResume(boolean z) {
            this.supportBreakpointResume = z;
            return this;
        }

        public Builder supportFileVerification(boolean z) {
            this.supportFileVerification = z;
            return this;
        }

        public Builder useFileStreamWriteFile(boolean z) {
            this.useFileStreamWriteFile = z;
            return this;
        }
    }

    private DownloadConfig(Builder builder) {
        this.mBuilder = builder;
    }

    public File cacheDirectory() {
        return this.mBuilder.cacheDirectory;
    }

    public boolean disappearWhenTaskIsDone() {
        return this.mBuilder.disappearWhenTaskIsDone;
    }

    public Map dnsResolver() {
        return this.mBuilder.dnsResolver;
    }

    public int dnsSelectStrategy() {
        return this.mBuilder.dnsSelectStrategy;
    }

    public ExecutorService executor() {
        return this.mBuilder.executor;
    }

    public boolean forceFlushWhenWriteFile() {
        return this.mBuilder.forceFlushWhenWriteFile;
    }

    public int getBandwidthLimit() {
        return this.mBuilder.bandwidthLimit;
    }

    public int getCacheExpiredTime() {
        return this.mBuilder.cacheExpiredTime;
    }

    public int getConnectTimeout() {
        return this.mBuilder.connectTimeout;
    }

    public int getMaxChunk() {
        return this.mBuilder.maxChunk;
    }

    public int getMaxTask() {
        return this.mBuilder.maxTask;
    }

    public int getReadTimeout() {
        return this.mBuilder.readTimeout;
    }

    public int getRetryCount() {
        return this.mBuilder.retryCount;
    }

    public int getRetryInterval() {
        return this.mBuilder.retryInterval;
    }

    public int getRetryMode() {
        return this.mBuilder.retryMode;
    }

    public boolean ignoreCertificateVerify() {
        return this.mBuilder.ignoreCertificateVerify;
    }

    public boolean isOverwriteExistTask() {
        return this.mBuilder.overwriteExistTask;
    }

    public boolean isSupportBreakpointResume() {
        return this.mBuilder.supportBreakpointResume;
    }

    public boolean isSupportFileVerification() {
        return this.mBuilder.supportFileVerification;
    }

    public boolean logger() {
        return this.mBuilder.logger;
    }

    public boolean useFileStreamWriteFile() {
        return this.mBuilder.useFileStreamWriteFile;
    }
}
