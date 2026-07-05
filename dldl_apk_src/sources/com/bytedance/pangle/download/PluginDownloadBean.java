package com.bytedance.pangle.download;

import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PluginDownloadBean {
    public List<String> mBackupUrlList;
    public int mFlag;
    public long mFollowId;
    public String mMd5;
    public String mPackageName;
    public String mUrl;
    public int mVersionCode;
    public int mApiVersionMin = 0;
    public int mApiVersionMax = Integer.MAX_VALUE;

    public boolean isUnInstall() {
        return this.mFlag == 1;
    }

    public boolean isRevert() {
        return this.mFlag == 3;
    }
}
