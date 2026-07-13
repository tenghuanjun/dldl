package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InviteBillBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003Jc\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0007HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012¨\u0006*"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/InviteBillBean;", "", "dateline", "", "dateline_txt", "", "id", "", "payMoney", "payType", "status", "status_txt", "uid", "userName", "(JLjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getDateline", "()J", "getDateline_txt", "()Ljava/lang/String;", "getId", "()I", "getPayMoney", "getPayType", "getStatus", "getStatus_txt", "getUid", "getUserName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class InviteBillBean {
    public static final int $stable = 0;
    private final long dateline;
    private final String dateline_txt;
    private final int id;
    private final String payMoney;
    private final String payType;
    private final String status;
    private final String status_txt;
    private final int uid;
    private final String userName;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDateline_txt() {
        return this.dateline_txt;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPayMoney() {
        return this.payMoney;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPayType() {
        return this.payType;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getStatus_txt() {
        return this.status_txt;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getUserName() {
        return this.userName;
    }

    public final InviteBillBean copy(long dateline, String dateline_txt, int id, String payMoney, String payType, String status, String status_txt, int uid, String userName) {
        Intrinsics.checkNotNullParameter(dateline_txt, "dateline_txt");
        Intrinsics.checkNotNullParameter(payMoney, "payMoney");
        Intrinsics.checkNotNullParameter(payType, "payType");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(status_txt, "status_txt");
        Intrinsics.checkNotNullParameter(userName, "userName");
        return new InviteBillBean(dateline, dateline_txt, id, payMoney, payType, status, status_txt, uid, userName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InviteBillBean)) {
            return false;
        }
        InviteBillBean inviteBillBean = (InviteBillBean) other;
        return this.dateline == inviteBillBean.dateline && Intrinsics.areEqual(this.dateline_txt, inviteBillBean.dateline_txt) && this.id == inviteBillBean.id && Intrinsics.areEqual(this.payMoney, inviteBillBean.payMoney) && Intrinsics.areEqual(this.payType, inviteBillBean.payType) && Intrinsics.areEqual(this.status, inviteBillBean.status) && Intrinsics.areEqual(this.status_txt, inviteBillBean.status_txt) && this.uid == inviteBillBean.uid && Intrinsics.areEqual(this.userName, inviteBillBean.userName);
    }

    public int hashCode() {
        return (((((((((((((((GMTitleBean$$ExternalSyntheticBackport0.m(this.dateline) * 31) + this.dateline_txt.hashCode()) * 31) + this.id) * 31) + this.payMoney.hashCode()) * 31) + this.payType.hashCode()) * 31) + this.status.hashCode()) * 31) + this.status_txt.hashCode()) * 31) + this.uid) * 31) + this.userName.hashCode();
    }

    public String toString() {
        return "InviteBillBean(dateline=" + this.dateline + ", dateline_txt=" + this.dateline_txt + ", id=" + this.id + ", payMoney=" + this.payMoney + ", payType=" + this.payType + ", status=" + this.status + ", status_txt=" + this.status_txt + ", uid=" + this.uid + ", userName=" + this.userName + ")";
    }

    public InviteBillBean(long j, String dateline_txt, int i, String payMoney, String payType, String status, String status_txt, int i2, String userName) {
        Intrinsics.checkNotNullParameter(dateline_txt, "dateline_txt");
        Intrinsics.checkNotNullParameter(payMoney, "payMoney");
        Intrinsics.checkNotNullParameter(payType, "payType");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(status_txt, "status_txt");
        Intrinsics.checkNotNullParameter(userName, "userName");
        this.dateline = j;
        this.dateline_txt = dateline_txt;
        this.id = i;
        this.payMoney = payMoney;
        this.payType = payType;
        this.status = status;
        this.status_txt = status_txt;
        this.uid = i2;
        this.userName = userName;
    }

    public final long getDateline() {
        return this.dateline;
    }

    public final String getDateline_txt() {
        return this.dateline_txt;
    }

    public final int getId() {
        return this.id;
    }

    public final String getPayMoney() {
        return this.payMoney;
    }

    public final String getPayType() {
        return this.payType;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getStatus_txt() {
        return this.status_txt;
    }

    public final int getUid() {
        return this.uid;
    }

    public final String getUserName() {
        return this.userName;
    }
}
