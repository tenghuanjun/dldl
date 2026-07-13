package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LotteryGiftBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LotteryGiftBean;", "", "credit", "", "id", "lottery_id", "name", "", "pic", "(IIILjava/lang/String;Ljava/lang/String;)V", "getCredit", "()I", "getId", "getLottery_id", "getName", "()Ljava/lang/String;", "getPic", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class LotteryGiftBean {
    public static final int $stable = 0;
    private final int credit;
    private final int id;
    private final int lottery_id;
    private final String name;
    private final String pic;

    public static /* synthetic */ LotteryGiftBean copy$default(LotteryGiftBean lotteryGiftBean, int i, int i2, int i3, String str, String str2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = lotteryGiftBean.credit;
        }
        if ((i4 & 2) != 0) {
            i2 = lotteryGiftBean.id;
        }
        int i5 = i2;
        if ((i4 & 4) != 0) {
            i3 = lotteryGiftBean.lottery_id;
        }
        int i6 = i3;
        if ((i4 & 8) != 0) {
            str = lotteryGiftBean.name;
        }
        String str3 = str;
        if ((i4 & 16) != 0) {
            str2 = lotteryGiftBean.pic;
        }
        return lotteryGiftBean.copy(i, i5, i6, str3, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCredit() {
        return this.credit;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getLottery_id() {
        return this.lottery_id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPic() {
        return this.pic;
    }

    public final LotteryGiftBean copy(int credit, int id, int lottery_id, String name, String pic) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(pic, "pic");
        return new LotteryGiftBean(credit, id, lottery_id, name, pic);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LotteryGiftBean)) {
            return false;
        }
        LotteryGiftBean lotteryGiftBean = (LotteryGiftBean) other;
        return this.credit == lotteryGiftBean.credit && this.id == lotteryGiftBean.id && this.lottery_id == lotteryGiftBean.lottery_id && Intrinsics.areEqual(this.name, lotteryGiftBean.name) && Intrinsics.areEqual(this.pic, lotteryGiftBean.pic);
    }

    public int hashCode() {
        return (((((((this.credit * 31) + this.id) * 31) + this.lottery_id) * 31) + this.name.hashCode()) * 31) + this.pic.hashCode();
    }

    public String toString() {
        return "LotteryGiftBean(credit=" + this.credit + ", id=" + this.id + ", lottery_id=" + this.lottery_id + ", name=" + this.name + ", pic=" + this.pic + ")";
    }

    public LotteryGiftBean(int i, int i2, int i3, String name, String pic) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(pic, "pic");
        this.credit = i;
        this.id = i2;
        this.lottery_id = i3;
        this.name = name;
        this.pic = pic;
    }

    public final int getCredit() {
        return this.credit;
    }

    public final int getId() {
        return this.id;
    }

    public final int getLottery_id() {
        return this.lottery_id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPic() {
        return this.pic;
    }
}
