package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlideBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012¨\u0006\""}, d2 = {"Lcom/cy/yyjia/zhe28/domain/SlideBean;", "", "actionid", "", "gid", "openTimes", "id", SocialConstants.PARAM_IMG_URL, "", "url", "countdown", "(IIIILjava/lang/String;Ljava/lang/String;I)V", "getActionid", "()I", "getCountdown", "getGid", "getId", "getImg", "()Ljava/lang/String;", "getOpenTimes", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class SlideBean {
    public static final int $stable = 0;
    private final int actionid;
    private final int countdown;
    private final int gid;
    private final int id;
    private final String img;
    private final int openTimes;
    private final String url;

    public static /* synthetic */ SlideBean copy$default(SlideBean slideBean, int i, int i2, int i3, int i4, String str, String str2, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i = slideBean.actionid;
        }
        if ((i6 & 2) != 0) {
            i2 = slideBean.gid;
        }
        int i7 = i2;
        if ((i6 & 4) != 0) {
            i3 = slideBean.openTimes;
        }
        int i8 = i3;
        if ((i6 & 8) != 0) {
            i4 = slideBean.id;
        }
        int i9 = i4;
        if ((i6 & 16) != 0) {
            str = slideBean.img;
        }
        String str3 = str;
        if ((i6 & 32) != 0) {
            str2 = slideBean.url;
        }
        String str4 = str2;
        if ((i6 & 64) != 0) {
            i5 = slideBean.countdown;
        }
        return slideBean.copy(i, i7, i8, i9, str3, str4, i5);
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
    public final int getOpenTimes() {
        return this.openTimes;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getImg() {
        return this.img;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getCountdown() {
        return this.countdown;
    }

    public final SlideBean copy(int actionid, int gid, int openTimes, int id, String img, String url, int countdown) {
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(url, "url");
        return new SlideBean(actionid, gid, openTimes, id, img, url, countdown);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SlideBean)) {
            return false;
        }
        SlideBean slideBean = (SlideBean) other;
        return this.actionid == slideBean.actionid && this.gid == slideBean.gid && this.openTimes == slideBean.openTimes && this.id == slideBean.id && Intrinsics.areEqual(this.img, slideBean.img) && Intrinsics.areEqual(this.url, slideBean.url) && this.countdown == slideBean.countdown;
    }

    public int hashCode() {
        return (((((((((((this.actionid * 31) + this.gid) * 31) + this.openTimes) * 31) + this.id) * 31) + this.img.hashCode()) * 31) + this.url.hashCode()) * 31) + this.countdown;
    }

    public String toString() {
        return "SlideBean(actionid=" + this.actionid + ", gid=" + this.gid + ", openTimes=" + this.openTimes + ", id=" + this.id + ", img=" + this.img + ", url=" + this.url + ", countdown=" + this.countdown + ")";
    }

    public SlideBean(int i, int i2, int i3, int i4, String img, String url, int i5) {
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(url, "url");
        this.actionid = i;
        this.gid = i2;
        this.openTimes = i3;
        this.id = i4;
        this.img = img;
        this.url = url;
        this.countdown = i5;
    }

    public final int getActionid() {
        return this.actionid;
    }

    public final int getGid() {
        return this.gid;
    }

    public final int getOpenTimes() {
        return this.openTimes;
    }

    public final int getId() {
        return this.id;
    }

    public final String getImg() {
        return this.img;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getCountdown() {
        return this.countdown;
    }
}
