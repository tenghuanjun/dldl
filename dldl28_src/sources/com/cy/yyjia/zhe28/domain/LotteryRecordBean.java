package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LotteryRecordBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LotteryRecordBean;", "", "dateline", "", "id", "", "name", "status", "uid", "(Ljava/lang/String;ILjava/lang/String;II)V", "getDateline", "()Ljava/lang/String;", "getId", "()I", "getName", "getStatus", "getUid", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class LotteryRecordBean {
    public static final int $stable = 0;
    private final String dateline;
    private final int id;
    private final String name;
    private final int status;
    private final int uid;

    public static /* synthetic */ LotteryRecordBean copy$default(LotteryRecordBean lotteryRecordBean, String str, int i, String str2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = lotteryRecordBean.dateline;
        }
        if ((i4 & 2) != 0) {
            i = lotteryRecordBean.id;
        }
        int i5 = i;
        if ((i4 & 4) != 0) {
            str2 = lotteryRecordBean.name;
        }
        String str3 = str2;
        if ((i4 & 8) != 0) {
            i2 = lotteryRecordBean.status;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            i3 = lotteryRecordBean.uid;
        }
        return lotteryRecordBean.copy(str, i5, str3, i6, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    public final LotteryRecordBean copy(String dateline, int id, String name, int status, int uid) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(name, "name");
        return new LotteryRecordBean(dateline, id, name, status, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LotteryRecordBean)) {
            return false;
        }
        LotteryRecordBean lotteryRecordBean = (LotteryRecordBean) other;
        return Intrinsics.areEqual(this.dateline, lotteryRecordBean.dateline) && this.id == lotteryRecordBean.id && Intrinsics.areEqual(this.name, lotteryRecordBean.name) && this.status == lotteryRecordBean.status && this.uid == lotteryRecordBean.uid;
    }

    public int hashCode() {
        return (((((((this.dateline.hashCode() * 31) + this.id) * 31) + this.name.hashCode()) * 31) + this.status) * 31) + this.uid;
    }

    public String toString() {
        return "LotteryRecordBean(dateline=" + this.dateline + ", id=" + this.id + ", name=" + this.name + ", status=" + this.status + ", uid=" + this.uid + ")";
    }

    public LotteryRecordBean(String dateline, int i, String name, int i2, int i3) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(name, "name");
        this.dateline = dateline;
        this.id = i;
        this.name = name;
        this.status = i2;
        this.uid = i3;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final int getStatus() {
        return this.status;
    }

    public final int getUid() {
        return this.uid;
    }
}
