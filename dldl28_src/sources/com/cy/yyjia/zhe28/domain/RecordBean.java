package com.cy.yyjia.zhe28.domain;

import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecordBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b4\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0007¢\u0006\u0002\u0010\u0015J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J³\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u0007HÆ\u0001J\u0013\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010>\u001a\u00020\u0003J\u0006\u0010?\u001a\u00020\u0003J\t\u0010@\u001a\u00020\u0007HÖ\u0001J\t\u0010A\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0011\u0010\u0014\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0017¨\u0006B"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/RecordBean;", "", MetricsSQLiteCacheKt.METRICS_END_TIME, "", "create_time", "dateline", "id", "", "signedMoney", "unsignedMoney", "money", "orderId", "payName", "payType", "title", "type", "gameName", "moduleName", "num", "unit", "uid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getCreate_time", "()Ljava/lang/String;", "getDateline", "getEnd_time", "getGameName", "getId", "()I", "getModuleName", "getMoney", "getNum", "getOrderId", "getPayName", "getPayType", "getSignedMoney", "getTitle", "getType", "getUid", "getUnit", "getUnsignedMoney", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getBtnText", "getShowMoneyText", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class RecordBean {
    public static final int $stable = 0;
    private final String create_time;
    private final String dateline;
    private final String end_time;
    private final String gameName;
    private final int id;
    private final String moduleName;
    private final String money;
    private final String num;
    private final String orderId;
    private final String payName;
    private final String payType;
    private final String signedMoney;
    private final String title;
    private final String type;
    private final int uid;
    private final String unit;
    private final String unsignedMoney;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getEnd_time() {
        return this.end_time;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getPayType() {
        return this.payType;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getGameName() {
        return this.gameName;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getModuleName() {
        return this.moduleName;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getNum() {
        return this.num;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getUnit() {
        return this.unit;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCreate_time() {
        return this.create_time;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSignedMoney() {
        return this.signedMoney;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getUnsignedMoney() {
        return this.unsignedMoney;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getMoney() {
        return this.money;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getOrderId() {
        return this.orderId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPayName() {
        return this.payName;
    }

    public final RecordBean copy(String end_time, String create_time, String dateline, int id, String signedMoney, String unsignedMoney, String money, String orderId, String payName, String payType, String title, String type, String gameName, String moduleName, String num, String unit, int uid) {
        Intrinsics.checkNotNullParameter(end_time, "end_time");
        Intrinsics.checkNotNullParameter(create_time, "create_time");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(signedMoney, "signedMoney");
        Intrinsics.checkNotNullParameter(unsignedMoney, "unsignedMoney");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(payName, "payName");
        Intrinsics.checkNotNullParameter(payType, "payType");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(gameName, "gameName");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(num, "num");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return new RecordBean(end_time, create_time, dateline, id, signedMoney, unsignedMoney, money, orderId, payName, payType, title, type, gameName, moduleName, num, unit, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RecordBean)) {
            return false;
        }
        RecordBean recordBean = (RecordBean) other;
        return Intrinsics.areEqual(this.end_time, recordBean.end_time) && Intrinsics.areEqual(this.create_time, recordBean.create_time) && Intrinsics.areEqual(this.dateline, recordBean.dateline) && this.id == recordBean.id && Intrinsics.areEqual(this.signedMoney, recordBean.signedMoney) && Intrinsics.areEqual(this.unsignedMoney, recordBean.unsignedMoney) && Intrinsics.areEqual(this.money, recordBean.money) && Intrinsics.areEqual(this.orderId, recordBean.orderId) && Intrinsics.areEqual(this.payName, recordBean.payName) && Intrinsics.areEqual(this.payType, recordBean.payType) && Intrinsics.areEqual(this.title, recordBean.title) && Intrinsics.areEqual(this.type, recordBean.type) && Intrinsics.areEqual(this.gameName, recordBean.gameName) && Intrinsics.areEqual(this.moduleName, recordBean.moduleName) && Intrinsics.areEqual(this.num, recordBean.num) && Intrinsics.areEqual(this.unit, recordBean.unit) && this.uid == recordBean.uid;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((this.end_time.hashCode() * 31) + this.create_time.hashCode()) * 31) + this.dateline.hashCode()) * 31) + this.id) * 31) + this.signedMoney.hashCode()) * 31) + this.unsignedMoney.hashCode()) * 31) + this.money.hashCode()) * 31) + this.orderId.hashCode()) * 31) + this.payName.hashCode()) * 31) + this.payType.hashCode()) * 31) + this.title.hashCode()) * 31) + this.type.hashCode()) * 31) + this.gameName.hashCode()) * 31) + this.moduleName.hashCode()) * 31) + this.num.hashCode()) * 31) + this.unit.hashCode()) * 31) + this.uid;
    }

    public String toString() {
        return "RecordBean(end_time=" + this.end_time + ", create_time=" + this.create_time + ", dateline=" + this.dateline + ", id=" + this.id + ", signedMoney=" + this.signedMoney + ", unsignedMoney=" + this.unsignedMoney + ", money=" + this.money + ", orderId=" + this.orderId + ", payName=" + this.payName + ", payType=" + this.payType + ", title=" + this.title + ", type=" + this.type + ", gameName=" + this.gameName + ", moduleName=" + this.moduleName + ", num=" + this.num + ", unit=" + this.unit + ", uid=" + this.uid + ")";
    }

    public RecordBean(String end_time, String create_time, String dateline, int i, String signedMoney, String unsignedMoney, String money, String orderId, String payName, String payType, String title, String type, String gameName, String moduleName, String num, String unit, int i2) {
        Intrinsics.checkNotNullParameter(end_time, "end_time");
        Intrinsics.checkNotNullParameter(create_time, "create_time");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(signedMoney, "signedMoney");
        Intrinsics.checkNotNullParameter(unsignedMoney, "unsignedMoney");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(payName, "payName");
        Intrinsics.checkNotNullParameter(payType, "payType");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(gameName, "gameName");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(num, "num");
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.end_time = end_time;
        this.create_time = create_time;
        this.dateline = dateline;
        this.id = i;
        this.signedMoney = signedMoney;
        this.unsignedMoney = unsignedMoney;
        this.money = money;
        this.orderId = orderId;
        this.payName = payName;
        this.payType = payType;
        this.title = title;
        this.type = type;
        this.gameName = gameName;
        this.moduleName = moduleName;
        this.num = num;
        this.unit = unit;
        this.uid = i2;
    }

    public final String getEnd_time() {
        return this.end_time;
    }

    public final String getCreate_time() {
        return this.create_time;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final int getId() {
        return this.id;
    }

    public final String getSignedMoney() {
        return this.signedMoney;
    }

    public final String getUnsignedMoney() {
        return this.unsignedMoney;
    }

    public final String getMoney() {
        return this.money;
    }

    public final String getOrderId() {
        return this.orderId;
    }

    public final String getPayName() {
        return this.payName;
    }

    public final String getPayType() {
        return this.payType;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getType() {
        return this.type;
    }

    public final String getGameName() {
        return this.gameName;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final String getNum() {
        return this.num;
    }

    public final String getUnit() {
        return this.unit;
    }

    public final int getUid() {
        return this.uid;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003a A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getBtnText() {
        /*
            r2 = this;
            java.lang.String r0 = r2.type
            int r1 = r0.hashCode()
            switch(r1) {
                case -1354573786: goto L2e;
                case -1177318867: goto L22;
                case 3165170: goto L16;
                case 3530567: goto La;
                default: goto L9;
            }
        L9:
            goto L3a
        La:
            java.lang.String r1 = "site"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L13
            goto L3a
        L13:
            java.lang.String r0 = "平台币"
            goto L3c
        L16:
            java.lang.String r1 = "game"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L1f
            goto L3a
        L1f:
            java.lang.String r0 = "游戏消费"
            goto L3c
        L22:
            java.lang.String r1 = "account"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L2b
            goto L3a
        L2b:
            java.lang.String r0 = "账号交易"
            goto L3c
        L2e:
            java.lang.String r1 = "coupon"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L37
            goto L3a
        L37:
            java.lang.String r0 = "代金券"
            goto L3c
        L3a:
            java.lang.String r0 = "全部"
        L3c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.domain.RecordBean.getBtnText():java.lang.String");
    }

    public final String getShowMoneyText() {
        if (Intrinsics.areEqual(this.type, "game")) {
            return "-" + this.unsignedMoney;
        }
        return "+" + this.unsignedMoney;
    }
}
