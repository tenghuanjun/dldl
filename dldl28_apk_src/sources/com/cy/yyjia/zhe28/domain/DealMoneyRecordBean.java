package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DealMoneyRecordBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\t¢\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\tHÆ\u0003J\u008b\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\tHÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\tHÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019¨\u00064"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DealMoneyRecordBean;", "", "after_money", "", "before_money", "create_time", SocialConstants.PARAM_APP_DESC, "explain", "id", "", "module", "money", "operade", "operateUid", "orderId", "title", "uid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;IILjava/lang/String;Ljava/lang/String;I)V", "getAfter_money", "()Ljava/lang/String;", "getBefore_money", "getCreate_time", "getDesc", "getExplain", "getId", "()I", "getModule", "getMoney", "getOperade", "getOperateUid", "getOrderId", "getTitle", "getUid", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class DealMoneyRecordBean {
    public static final int $stable = 0;
    private final String after_money;
    private final String before_money;
    private final String create_time;
    private final String desc;
    private final String explain;
    private final int id;
    private final int module;
    private final String money;
    private final int operade;
    private final int operateUid;
    private final String orderId;
    private final String title;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAfter_money() {
        return this.after_money;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getOperateUid() {
        return this.operateUid;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getOrderId() {
        return this.orderId;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getBefore_money() {
        return this.before_money;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCreate_time() {
        return this.create_time;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getExplain() {
        return this.explain;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getModule() {
        return this.module;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getMoney() {
        return this.money;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getOperade() {
        return this.operade;
    }

    public final DealMoneyRecordBean copy(String after_money, String before_money, String create_time, String desc, String explain, int id, int module, String money, int operade, int operateUid, String orderId, String title, int uid) {
        Intrinsics.checkNotNullParameter(after_money, "after_money");
        Intrinsics.checkNotNullParameter(before_money, "before_money");
        Intrinsics.checkNotNullParameter(create_time, "create_time");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(explain, "explain");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(title, "title");
        return new DealMoneyRecordBean(after_money, before_money, create_time, desc, explain, id, module, money, operade, operateUid, orderId, title, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DealMoneyRecordBean)) {
            return false;
        }
        DealMoneyRecordBean dealMoneyRecordBean = (DealMoneyRecordBean) other;
        return Intrinsics.areEqual(this.after_money, dealMoneyRecordBean.after_money) && Intrinsics.areEqual(this.before_money, dealMoneyRecordBean.before_money) && Intrinsics.areEqual(this.create_time, dealMoneyRecordBean.create_time) && Intrinsics.areEqual(this.desc, dealMoneyRecordBean.desc) && Intrinsics.areEqual(this.explain, dealMoneyRecordBean.explain) && this.id == dealMoneyRecordBean.id && this.module == dealMoneyRecordBean.module && Intrinsics.areEqual(this.money, dealMoneyRecordBean.money) && this.operade == dealMoneyRecordBean.operade && this.operateUid == dealMoneyRecordBean.operateUid && Intrinsics.areEqual(this.orderId, dealMoneyRecordBean.orderId) && Intrinsics.areEqual(this.title, dealMoneyRecordBean.title) && this.uid == dealMoneyRecordBean.uid;
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.after_money.hashCode() * 31) + this.before_money.hashCode()) * 31) + this.create_time.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.explain.hashCode()) * 31) + this.id) * 31) + this.module) * 31) + this.money.hashCode()) * 31) + this.operade) * 31) + this.operateUid) * 31) + this.orderId.hashCode()) * 31) + this.title.hashCode()) * 31) + this.uid;
    }

    public String toString() {
        return "DealMoneyRecordBean(after_money=" + this.after_money + ", before_money=" + this.before_money + ", create_time=" + this.create_time + ", desc=" + this.desc + ", explain=" + this.explain + ", id=" + this.id + ", module=" + this.module + ", money=" + this.money + ", operade=" + this.operade + ", operateUid=" + this.operateUid + ", orderId=" + this.orderId + ", title=" + this.title + ", uid=" + this.uid + ")";
    }

    public DealMoneyRecordBean(String after_money, String before_money, String create_time, String desc, String explain, int i, int i2, String money, int i3, int i4, String orderId, String title, int i5) {
        Intrinsics.checkNotNullParameter(after_money, "after_money");
        Intrinsics.checkNotNullParameter(before_money, "before_money");
        Intrinsics.checkNotNullParameter(create_time, "create_time");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(explain, "explain");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(title, "title");
        this.after_money = after_money;
        this.before_money = before_money;
        this.create_time = create_time;
        this.desc = desc;
        this.explain = explain;
        this.id = i;
        this.module = i2;
        this.money = money;
        this.operade = i3;
        this.operateUid = i4;
        this.orderId = orderId;
        this.title = title;
        this.uid = i5;
    }

    public final String getAfter_money() {
        return this.after_money;
    }

    public final String getBefore_money() {
        return this.before_money;
    }

    public final String getCreate_time() {
        return this.create_time;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getExplain() {
        return this.explain;
    }

    public final int getId() {
        return this.id;
    }

    public final int getModule() {
        return this.module;
    }

    public final String getMoney() {
        return this.money;
    }

    public final int getOperade() {
        return this.operade;
    }

    public final int getOperateUid() {
        return this.operateUid;
    }

    public final String getOrderId() {
        return this.orderId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getUid() {
        return this.uid;
    }
}
