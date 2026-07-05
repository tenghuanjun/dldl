package com.parameters.share;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface IShareInfo {

    public static class ShareClassify {
        public static final int SHARE_DEFAULT = 0;
        public static final int SHARE_IMG = 1;
        public static final int SHARE_TEXT = 3;
        public static final int SHARE_WEB = 2;
    }

    int classify();
}
