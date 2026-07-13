package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InviteRankBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/InviteRankBean;", "", "index", "", "nums", "", "rewardProfit", "uid", "userName", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getIndex", "()I", "getNums", "()Ljava/lang/String;", "getRewardProfit", "getUid", "getUserName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class InviteRankBean {
    public static final int $stable = 0;
    private final int index;
    private final String nums;
    private final String rewardProfit;
    private final String uid;
    private final String userName;

    public static /* synthetic */ InviteRankBean copy$default(InviteRankBean inviteRankBean, int i, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = inviteRankBean.index;
        }
        if ((i2 & 2) != 0) {
            str = inviteRankBean.nums;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = inviteRankBean.rewardProfit;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            str3 = inviteRankBean.uid;
        }
        String str7 = str3;
        if ((i2 & 16) != 0) {
            str4 = inviteRankBean.userName;
        }
        return inviteRankBean.copy(i, str5, str6, str7, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNums() {
        return this.nums;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRewardProfit() {
        return this.rewardProfit;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getUserName() {
        return this.userName;
    }

    public final InviteRankBean copy(int index, String nums, String rewardProfit, String uid, String userName) {
        Intrinsics.checkNotNullParameter(nums, "nums");
        Intrinsics.checkNotNullParameter(rewardProfit, "rewardProfit");
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(userName, "userName");
        return new InviteRankBean(index, nums, rewardProfit, uid, userName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InviteRankBean)) {
            return false;
        }
        InviteRankBean inviteRankBean = (InviteRankBean) other;
        return this.index == inviteRankBean.index && Intrinsics.areEqual(this.nums, inviteRankBean.nums) && Intrinsics.areEqual(this.rewardProfit, inviteRankBean.rewardProfit) && Intrinsics.areEqual(this.uid, inviteRankBean.uid) && Intrinsics.areEqual(this.userName, inviteRankBean.userName);
    }

    public int hashCode() {
        return (((((((this.index * 31) + this.nums.hashCode()) * 31) + this.rewardProfit.hashCode()) * 31) + this.uid.hashCode()) * 31) + this.userName.hashCode();
    }

    public String toString() {
        return "InviteRankBean(index=" + this.index + ", nums=" + this.nums + ", rewardProfit=" + this.rewardProfit + ", uid=" + this.uid + ", userName=" + this.userName + ")";
    }

    public InviteRankBean(int i, String nums, String rewardProfit, String uid, String userName) {
        Intrinsics.checkNotNullParameter(nums, "nums");
        Intrinsics.checkNotNullParameter(rewardProfit, "rewardProfit");
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(userName, "userName");
        this.index = i;
        this.nums = nums;
        this.rewardProfit = rewardProfit;
        this.uid = uid;
        this.userName = userName;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getNums() {
        return this.nums;
    }

    public final String getRewardProfit() {
        return this.rewardProfit;
    }

    public final String getUid() {
        return this.uid;
    }

    public final String getUserName() {
        return this.userName;
    }
}
