package com.cy.yyjia.zhe28.domain;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FunBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/FunBean;", "", "name", "", "picUrl", "appUrl", "linkUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAppUrl", "()Ljava/lang/String;", "getLinkUrl", "getName", "getPicUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class FunBean {
    public static final int $stable = 0;
    private final String appUrl;
    private final String linkUrl;
    private final String name;

    @SerializedName(alternate = {"pic"}, value = "picUrl")
    private final String picUrl;

    public static /* synthetic */ FunBean copy$default(FunBean funBean, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = funBean.name;
        }
        if ((i & 2) != 0) {
            str2 = funBean.picUrl;
        }
        if ((i & 4) != 0) {
            str3 = funBean.appUrl;
        }
        if ((i & 8) != 0) {
            str4 = funBean.linkUrl;
        }
        return funBean.copy(str, str2, str3, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPicUrl() {
        return this.picUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAppUrl() {
        return this.appUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLinkUrl() {
        return this.linkUrl;
    }

    public final FunBean copy(String name, String picUrl, String appUrl, String linkUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(picUrl, "picUrl");
        Intrinsics.checkNotNullParameter(appUrl, "appUrl");
        Intrinsics.checkNotNullParameter(linkUrl, "linkUrl");
        return new FunBean(name, picUrl, appUrl, linkUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunBean)) {
            return false;
        }
        FunBean funBean = (FunBean) other;
        return Intrinsics.areEqual(this.name, funBean.name) && Intrinsics.areEqual(this.picUrl, funBean.picUrl) && Intrinsics.areEqual(this.appUrl, funBean.appUrl) && Intrinsics.areEqual(this.linkUrl, funBean.linkUrl);
    }

    public int hashCode() {
        return (((((this.name.hashCode() * 31) + this.picUrl.hashCode()) * 31) + this.appUrl.hashCode()) * 31) + this.linkUrl.hashCode();
    }

    public String toString() {
        return "FunBean(name=" + this.name + ", picUrl=" + this.picUrl + ", appUrl=" + this.appUrl + ", linkUrl=" + this.linkUrl + ")";
    }

    public FunBean(String name, String picUrl, String appUrl, String linkUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(picUrl, "picUrl");
        Intrinsics.checkNotNullParameter(appUrl, "appUrl");
        Intrinsics.checkNotNullParameter(linkUrl, "linkUrl");
        this.name = name;
        this.picUrl = picUrl;
        this.appUrl = appUrl;
        this.linkUrl = linkUrl;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPicUrl() {
        return this.picUrl;
    }

    public final String getAppUrl() {
        return this.appUrl;
    }

    public final String getLinkUrl() {
        return this.linkUrl;
    }
}
