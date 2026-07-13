package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WithdrewBillBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003Jm\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0005HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0005HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013¨\u0006+"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WithdrewBillBean;", "", "dateline", "", "id", "", "payMoney", "status", "statusStr", "tradeMoney", "type", "typeStr", SocialConstants.PARAM_APP_DESC, "uid", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;I)V", "getDateline", "()Ljava/lang/String;", "getDesc", "getId", "()I", "getPayMoney", "getStatus", "getStatusStr", "getTradeMoney", "getType", "getTypeStr", "getUid", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class WithdrewBillBean {
    public static final int $stable = 0;
    private final String dateline;
    private final String desc;
    private final int id;
    private final String payMoney;
    private final String status;
    private final String statusStr;
    private final String tradeMoney;
    private final int type;
    private final String typeStr;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPayMoney() {
        return this.payMoney;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStatusStr() {
        return this.statusStr;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTradeMoney() {
        return this.tradeMoney;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getTypeStr() {
        return this.typeStr;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    public final WithdrewBillBean copy(String dateline, int id, String payMoney, String status, String statusStr, String tradeMoney, int type, String typeStr, String desc, int uid) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(payMoney, "payMoney");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(statusStr, "statusStr");
        Intrinsics.checkNotNullParameter(tradeMoney, "tradeMoney");
        Intrinsics.checkNotNullParameter(typeStr, "typeStr");
        Intrinsics.checkNotNullParameter(desc, "desc");
        return new WithdrewBillBean(dateline, id, payMoney, status, statusStr, tradeMoney, type, typeStr, desc, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WithdrewBillBean)) {
            return false;
        }
        WithdrewBillBean withdrewBillBean = (WithdrewBillBean) other;
        return Intrinsics.areEqual(this.dateline, withdrewBillBean.dateline) && this.id == withdrewBillBean.id && Intrinsics.areEqual(this.payMoney, withdrewBillBean.payMoney) && Intrinsics.areEqual(this.status, withdrewBillBean.status) && Intrinsics.areEqual(this.statusStr, withdrewBillBean.statusStr) && Intrinsics.areEqual(this.tradeMoney, withdrewBillBean.tradeMoney) && this.type == withdrewBillBean.type && Intrinsics.areEqual(this.typeStr, withdrewBillBean.typeStr) && Intrinsics.areEqual(this.desc, withdrewBillBean.desc) && this.uid == withdrewBillBean.uid;
    }

    public int hashCode() {
        return (((((((((((((((((this.dateline.hashCode() * 31) + this.id) * 31) + this.payMoney.hashCode()) * 31) + this.status.hashCode()) * 31) + this.statusStr.hashCode()) * 31) + this.tradeMoney.hashCode()) * 31) + this.type) * 31) + this.typeStr.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.uid;
    }

    public String toString() {
        return "WithdrewBillBean(dateline=" + this.dateline + ", id=" + this.id + ", payMoney=" + this.payMoney + ", status=" + this.status + ", statusStr=" + this.statusStr + ", tradeMoney=" + this.tradeMoney + ", type=" + this.type + ", typeStr=" + this.typeStr + ", desc=" + this.desc + ", uid=" + this.uid + ")";
    }

    public WithdrewBillBean(String dateline, int i, String payMoney, String status, String statusStr, String tradeMoney, int i2, String typeStr, String desc, int i3) {
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(payMoney, "payMoney");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(statusStr, "statusStr");
        Intrinsics.checkNotNullParameter(tradeMoney, "tradeMoney");
        Intrinsics.checkNotNullParameter(typeStr, "typeStr");
        Intrinsics.checkNotNullParameter(desc, "desc");
        this.dateline = dateline;
        this.id = i;
        this.payMoney = payMoney;
        this.status = status;
        this.statusStr = statusStr;
        this.tradeMoney = tradeMoney;
        this.type = i2;
        this.typeStr = typeStr;
        this.desc = desc;
        this.uid = i3;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final int getId() {
        return this.id;
    }

    public final String getPayMoney() {
        return this.payMoney;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getStatusStr() {
        return this.statusStr;
    }

    public final String getTradeMoney() {
        return this.tradeMoney;
    }

    public final int getType() {
        return this.type;
    }

    public final String getTypeStr() {
        return this.typeStr;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final int getUid() {
        return this.uid;
    }
}
