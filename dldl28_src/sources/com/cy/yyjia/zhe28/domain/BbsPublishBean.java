package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BbsPublishBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BbsPublishBean;", "", "awardBg", "", SocialConstants.PARAM_APP_DESC, "isTodayFirst", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getAwardBg", "()Ljava/lang/String;", "getDesc", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class BbsPublishBean {
    public static final int $stable = 0;
    private final String awardBg;
    private final String desc;
    private final boolean isTodayFirst;

    public static /* synthetic */ BbsPublishBean copy$default(BbsPublishBean bbsPublishBean, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bbsPublishBean.awardBg;
        }
        if ((i & 2) != 0) {
            str2 = bbsPublishBean.desc;
        }
        if ((i & 4) != 0) {
            z = bbsPublishBean.isTodayFirst;
        }
        return bbsPublishBean.copy(str, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAwardBg() {
        return this.awardBg;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsTodayFirst() {
        return this.isTodayFirst;
    }

    public final BbsPublishBean copy(String awardBg, String desc, boolean isTodayFirst) {
        Intrinsics.checkNotNullParameter(awardBg, "awardBg");
        Intrinsics.checkNotNullParameter(desc, "desc");
        return new BbsPublishBean(awardBg, desc, isTodayFirst);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BbsPublishBean)) {
            return false;
        }
        BbsPublishBean bbsPublishBean = (BbsPublishBean) other;
        return Intrinsics.areEqual(this.awardBg, bbsPublishBean.awardBg) && Intrinsics.areEqual(this.desc, bbsPublishBean.desc) && this.isTodayFirst == bbsPublishBean.isTodayFirst;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public int hashCode() {
        int iHashCode = ((this.awardBg.hashCode() * 31) + this.desc.hashCode()) * 31;
        boolean z = this.isTodayFirst;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "BbsPublishBean(awardBg=" + this.awardBg + ", desc=" + this.desc + ", isTodayFirst=" + this.isTodayFirst + ")";
    }

    public BbsPublishBean(String awardBg, String desc, boolean z) {
        Intrinsics.checkNotNullParameter(awardBg, "awardBg");
        Intrinsics.checkNotNullParameter(desc, "desc");
        this.awardBg = awardBg;
        this.desc = desc;
        this.isTodayFirst = z;
    }

    public final String getAwardBg() {
        return this.awardBg;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final boolean isTodayFirst() {
        return this.isTodayFirst;
    }
}
