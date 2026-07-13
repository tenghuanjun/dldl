package com.cy.yyjia.zhe28.domain;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InviteListBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/InviteListBean;", "", "dateline", "", "id", "", "rewardMoney", HintConstants.AUTOFILL_HINT_USERNAME, "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getDateline", "()Ljava/lang/String;", "getId", "()I", "getRewardMoney", "getUsername", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class InviteListBean {
    public static final int $stable = 0;
    private final String dateline;
    private final int id;
    private final String rewardMoney;
    private final String username;

    public static /* synthetic */ InviteListBean copy$default(InviteListBean inviteListBean, String str, int i, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = inviteListBean.dateline;
        }
        if ((i2 & 2) != 0) {
            i = inviteListBean.id;
        }
        if ((i2 & 4) != 0) {
            str2 = inviteListBean.rewardMoney;
        }
        if ((i2 & 8) != 0) {
            str3 = inviteListBean.username;
        }
        return inviteListBean.copy(str, i, str2, str3);
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
    public final String getRewardMoney() {
        return this.rewardMoney;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    public final InviteListBean copy(String dateline, int id, String rewardMoney, String username) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(rewardMoney, "rewardMoney");
        Intrinsics.checkNotNullParameter(username, "username");
        return new InviteListBean(dateline, id, rewardMoney, username);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InviteListBean)) {
            return false;
        }
        InviteListBean inviteListBean = (InviteListBean) other;
        return Intrinsics.areEqual(this.dateline, inviteListBean.dateline) && this.id == inviteListBean.id && Intrinsics.areEqual(this.rewardMoney, inviteListBean.rewardMoney) && Intrinsics.areEqual(this.username, inviteListBean.username);
    }

    public int hashCode() {
        return (((((this.dateline.hashCode() * 31) + this.id) * 31) + this.rewardMoney.hashCode()) * 31) + this.username.hashCode();
    }

    public String toString() {
        return "InviteListBean(dateline=" + this.dateline + ", id=" + this.id + ", rewardMoney=" + this.rewardMoney + ", username=" + this.username + ")";
    }

    public InviteListBean(String dateline, int i, String rewardMoney, String username) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(rewardMoney, "rewardMoney");
        Intrinsics.checkNotNullParameter(username, "username");
        this.dateline = dateline;
        this.id = i;
        this.rewardMoney = rewardMoney;
        this.username = username;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final int getId() {
        return this.id;
    }

    public final String getRewardMoney() {
        return this.rewardMoney;
    }

    public final String getUsername() {
        return this.username;
    }
}
