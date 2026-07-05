package com.alibaba.sdk.android.oss.model;

import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public enum CannedAccessControlList {
    Private("private"),
    PublicRead("public-read"),
    PublicReadWrite("public-read-write"),
    Default(DownloadSettingKeys.BugFix.DEFAULT);

    private String ACLString;

    CannedAccessControlList(String str) {
        this.ACLString = str;
    }

    public static CannedAccessControlList parseACL(String str) {
        for (CannedAccessControlList cannedAccessControlList : values()) {
            if (cannedAccessControlList.toString().equals(str)) {
                return cannedAccessControlList;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.ACLString;
    }
}
