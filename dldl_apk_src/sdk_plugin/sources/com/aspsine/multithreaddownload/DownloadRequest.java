package com.aspsine.multithreaddownload;

import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DownloadRequest {
    private String fileMd5;
    private CharSequence mDescription;
    private File mFolder;
    private boolean mScannable;
    private CharSequence mTitle;
    private String mUri;

    private DownloadRequest() {
    }

    private DownloadRequest(String str, String str2, File file, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        this.mUri = str;
        this.fileMd5 = str2;
        this.mFolder = file;
        this.mTitle = charSequence;
        this.mDescription = charSequence2;
        this.mScannable = z;
    }

    public String getFileMd5() {
        return this.fileMd5;
    }

    public String getUri() {
        return this.mUri;
    }

    public File getFolder() {
        return this.mFolder;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    public boolean isScannable() {
        return this.mScannable;
    }

    public static class Builder {
        private String fileMd5;
        private CharSequence mDescription;
        private File mFolder;
        private boolean mScannable;
        private CharSequence mTitle;
        private String mUri;

        public Builder setUri(String str) {
            this.mUri = str;
            return this;
        }

        public Builder setFileMd5(String str) {
            this.fileMd5 = str;
            return this;
        }

        public Builder setFolder(File file) {
            this.mFolder = file;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }

        public Builder setDescription(CharSequence charSequence) {
            this.mDescription = charSequence;
            return this;
        }

        public Builder setScannable(boolean z) {
            this.mScannable = z;
            return this;
        }

        public DownloadRequest build() {
            return new DownloadRequest(this.mUri, this.fileMd5, this.mFolder, this.mTitle, this.mDescription, this.mScannable);
        }
    }
}
