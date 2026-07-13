package com.cy.yyjia.zhe28.domain;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VipFlbBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/VipFlbBean;", "", "id", "", "isReceive", "money", "", "name", "upgradeYhqId", "(IILjava/lang/String;Ljava/lang/String;I)V", "getId", "()I", "getMoney", "()Ljava/lang/String;", "getName", "getUpgradeYhqId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class VipFlbBean {
    public static final int $stable = 0;
    private final int id;
    private final int isReceive;

    @SerializedName(alternate = {"welfare"}, value = "money")
    private final String money;
    private final String name;
    private final int upgradeYhqId;

    public static /* synthetic */ VipFlbBean copy$default(VipFlbBean vipFlbBean, int i, int i2, String str, String str2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = vipFlbBean.id;
        }
        if ((i4 & 2) != 0) {
            i2 = vipFlbBean.isReceive;
        }
        int i5 = i2;
        if ((i4 & 4) != 0) {
            str = vipFlbBean.money;
        }
        String str3 = str;
        if ((i4 & 8) != 0) {
            str2 = vipFlbBean.name;
        }
        String str4 = str2;
        if ((i4 & 16) != 0) {
            i3 = vipFlbBean.upgradeYhqId;
        }
        return vipFlbBean.copy(i, i5, str3, str4, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getIsReceive() {
        return this.isReceive;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMoney() {
        return this.money;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getUpgradeYhqId() {
        return this.upgradeYhqId;
    }

    public final VipFlbBean copy(int id, int isReceive, String money, String name, int upgradeYhqId) {
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(name, "name");
        return new VipFlbBean(id, isReceive, money, name, upgradeYhqId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VipFlbBean)) {
            return false;
        }
        VipFlbBean vipFlbBean = (VipFlbBean) other;
        return this.id == vipFlbBean.id && this.isReceive == vipFlbBean.isReceive && Intrinsics.areEqual(this.money, vipFlbBean.money) && Intrinsics.areEqual(this.name, vipFlbBean.name) && this.upgradeYhqId == vipFlbBean.upgradeYhqId;
    }

    public int hashCode() {
        return (((((((this.id * 31) + this.isReceive) * 31) + this.money.hashCode()) * 31) + this.name.hashCode()) * 31) + this.upgradeYhqId;
    }

    public String toString() {
        return "VipFlbBean(id=" + this.id + ", isReceive=" + this.isReceive + ", money=" + this.money + ", name=" + this.name + ", upgradeYhqId=" + this.upgradeYhqId + ")";
    }

    public VipFlbBean(int i, int i2, String money, String name, int i3) {
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = i;
        this.isReceive = i2;
        this.money = money;
        this.name = name;
        this.upgradeYhqId = i3;
    }

    public final int getId() {
        return this.id;
    }

    public final int isReceive() {
        return this.isReceive;
    }

    public final String getMoney() {
        return this.money;
    }

    public final String getName() {
        return this.name;
    }

    public final int getUpgradeYhqId() {
        return this.upgradeYhqId;
    }
}
