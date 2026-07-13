package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SdkJumpBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SdkJumpBean;", "", "actionid", "", "gid", "url", "", "(IILjava/lang/String;)V", "getActionid", "()I", "getGid", "getUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class SdkJumpBean {
    public static final int $stable = 0;
    private final int actionid;
    private final int gid;
    private final String url;

    public static /* synthetic */ SdkJumpBean copy$default(SdkJumpBean sdkJumpBean, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = sdkJumpBean.actionid;
        }
        if ((i3 & 2) != 0) {
            i2 = sdkJumpBean.gid;
        }
        if ((i3 & 4) != 0) {
            str = sdkJumpBean.url;
        }
        return sdkJumpBean.copy(i, i2, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getActionid() {
        return this.actionid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getGid() {
        return this.gid;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final SdkJumpBean copy(int actionid, int gid, String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new SdkJumpBean(actionid, gid, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SdkJumpBean)) {
            return false;
        }
        SdkJumpBean sdkJumpBean = (SdkJumpBean) other;
        return this.actionid == sdkJumpBean.actionid && this.gid == sdkJumpBean.gid && Intrinsics.areEqual(this.url, sdkJumpBean.url);
    }

    public int hashCode() {
        return (((this.actionid * 31) + this.gid) * 31) + this.url.hashCode();
    }

    public String toString() {
        return "SdkJumpBean(actionid=" + this.actionid + ", gid=" + this.gid + ", url=" + this.url + ")";
    }

    public SdkJumpBean(int i, int i2, String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.actionid = i;
        this.gid = i2;
        this.url = url;
    }

    public final int getActionid() {
        return this.actionid;
    }

    public final int getGid() {
        return this.gid;
    }

    public final String getUrl() {
        return this.url;
    }
}
