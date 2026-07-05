package com.bytedance.sdk.openadsdk.api.a;

import com.bykv.vk.openvk.api.proto.Bridge;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.sdk.openadsdk.downloadnew.core.ValueSetConstants;
import com.ss.android.download.api.model.DownloadShortInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class d implements Bridge {
    private DownloadShortInfo a;

    public d(DownloadShortInfo downloadShortInfo) {
        this.a = downloadShortInfo;
    }

    @Override // com.bykv.vk.openvk.api.proto.Bridge
    public ValueSet values() {
        return com.bykv.a.a.a.a.b.a().a(ValueSetConstants.VALUE_DOWNLOAD_SHORT_GET_ID, a()).a(ValueSetConstants.VALUE_DOWNLOAD_SHORT_GET_STATUS, b()).a(ValueSetConstants.VALUE_DOWNLOAD_SHORT_GET_TOTAL_BYTES, c()).a(ValueSetConstants.VALUE_DOWNLOAD_SHORT_GET_CURRENT_BYTES, d()).a(ValueSetConstants.VALUE_DOWNLOAD_SHORT_GET_FILE_NAME, e()).a(ValueSetConstants.VALUE_DOWNLOAD_SHORT_GET_FAIL_STATUS, f()).a(ValueSetConstants.VALUE_DOWNLOAD_SHORT_GET_ONLY_WIFI, g()).b();
    }

    @Override // com.bykv.vk.openvk.api.proto.Caller
    public <T> T call(int i, ValueSet valueSet, Class<T> cls) {
        switch (i) {
            case ValueSetConstants.VALUE_DOWNLOAD_SHORT_EQUALS /* 223700 */:
                return (T) Boolean.valueOf(equals(valueSet.objectValue(ValueSetConstants.VALUE_DOWNLOAD_SHORT_EQUALS_PARAMETER, Object.class)));
            case ValueSetConstants.VALUE_DOWNLOAD_SHORT_HASH_CODE /* 223701 */:
                hashCode();
                return null;
            default:
                return null;
        }
    }

    public boolean equals(Object obj) {
        DownloadShortInfo downloadShortInfo = this.a;
        if (downloadShortInfo != null) {
            return downloadShortInfo.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        DownloadShortInfo downloadShortInfo = this.a;
        if (downloadShortInfo != null) {
            return downloadShortInfo.hashCode();
        }
        return 0;
    }

    public long a() {
        DownloadShortInfo downloadShortInfo = this.a;
        if (downloadShortInfo != null) {
            return downloadShortInfo.id;
        }
        return -1L;
    }

    public int b() {
        DownloadShortInfo downloadShortInfo = this.a;
        if (downloadShortInfo != null) {
            return downloadShortInfo.status;
        }
        return -1;
    }

    public long c() {
        DownloadShortInfo downloadShortInfo = this.a;
        if (downloadShortInfo != null) {
            return downloadShortInfo.totalBytes;
        }
        return -1L;
    }

    public long d() {
        DownloadShortInfo downloadShortInfo = this.a;
        if (downloadShortInfo != null) {
            return downloadShortInfo.currentBytes;
        }
        return -1L;
    }

    public String e() {
        DownloadShortInfo downloadShortInfo = this.a;
        return downloadShortInfo != null ? downloadShortInfo.fileName : "";
    }

    public int f() {
        DownloadShortInfo downloadShortInfo = this.a;
        if (downloadShortInfo != null) {
            return downloadShortInfo.failStatus;
        }
        return 0;
    }

    public boolean g() {
        DownloadShortInfo downloadShortInfo = this.a;
        if (downloadShortInfo != null) {
            return downloadShortInfo.onlyWifi;
        }
        return false;
    }
}
