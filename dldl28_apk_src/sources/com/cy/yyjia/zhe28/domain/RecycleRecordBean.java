package com.cy.yyjia.zhe28.domain;

import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecycleRecordBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0095\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0016HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J½\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u0016HÆ\u0001J\u0013\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010C\u001a\u00020\u0003HÖ\u0001J\t\u0010D\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001bR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0019R\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001bR\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0019¨\u0006E"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/RecycleRecordBean;", "", CommonConstants.key_accountId, "", "accountName", "", "auditTime", "createTime", "effectiveMoney", CommonConstants.key_gameId, "id", "planId", "rate", "recycleMoney", "refuseRemark", "returnType", "roleName", "service", "status", "totalMoney", "uid", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;ILcom/cy/yyjia/zhe28/domain/GameBean;)V", "getAccountId", "()I", "getAccountName", "()Ljava/lang/String;", "getAuditTime", "getCreateTime", "getEffectiveMoney", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getGameId", "getId", "getPlanId", "getRate", "getRecycleMoney", "getRefuseRemark", "getReturnType", "getRoleName", "getService", "getStatus", "getTotalMoney", "getUid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class RecycleRecordBean {
    public static final int $stable = 8;
    private final int accountId;
    private final String accountName;
    private final String auditTime;
    private final int createTime;
    private final String effectiveMoney;
    private final GameBean game;
    private final int gameId;
    private final int id;
    private final int planId;
    private final String rate;
    private final String recycleMoney;
    private final String refuseRemark;
    private final int returnType;
    private final String roleName;
    private final String service;
    private final int status;
    private final String totalMoney;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getAccountId() {
        return this.accountId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getRecycleMoney() {
        return this.recycleMoney;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getRefuseRemark() {
        return this.refuseRemark;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getReturnType() {
        return this.returnType;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getRoleName() {
        return this.roleName;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getService() {
        return this.service;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getTotalMoney() {
        return this.totalMoney;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccountName() {
        return this.accountName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAuditTime() {
        return this.auditTime;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getCreateTime() {
        return this.createTime;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getEffectiveMoney() {
        return this.effectiveMoney;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getPlanId() {
        return this.planId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getRate() {
        return this.rate;
    }

    public final RecycleRecordBean copy(int accountId, String accountName, String auditTime, int createTime, String effectiveMoney, int gameId, int id, int planId, String rate, String recycleMoney, String refuseRemark, int returnType, String roleName, String service, int status, String totalMoney, int uid, GameBean game) {
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(auditTime, "auditTime");
        Intrinsics.checkNotNullParameter(effectiveMoney, "effectiveMoney");
        Intrinsics.checkNotNullParameter(rate, "rate");
        Intrinsics.checkNotNullParameter(recycleMoney, "recycleMoney");
        Intrinsics.checkNotNullParameter(refuseRemark, "refuseRemark");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        Intrinsics.checkNotNullParameter(game, "game");
        return new RecycleRecordBean(accountId, accountName, auditTime, createTime, effectiveMoney, gameId, id, planId, rate, recycleMoney, refuseRemark, returnType, roleName, service, status, totalMoney, uid, game);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RecycleRecordBean)) {
            return false;
        }
        RecycleRecordBean recycleRecordBean = (RecycleRecordBean) other;
        return this.accountId == recycleRecordBean.accountId && Intrinsics.areEqual(this.accountName, recycleRecordBean.accountName) && Intrinsics.areEqual(this.auditTime, recycleRecordBean.auditTime) && this.createTime == recycleRecordBean.createTime && Intrinsics.areEqual(this.effectiveMoney, recycleRecordBean.effectiveMoney) && this.gameId == recycleRecordBean.gameId && this.id == recycleRecordBean.id && this.planId == recycleRecordBean.planId && Intrinsics.areEqual(this.rate, recycleRecordBean.rate) && Intrinsics.areEqual(this.recycleMoney, recycleRecordBean.recycleMoney) && Intrinsics.areEqual(this.refuseRemark, recycleRecordBean.refuseRemark) && this.returnType == recycleRecordBean.returnType && Intrinsics.areEqual(this.roleName, recycleRecordBean.roleName) && Intrinsics.areEqual(this.service, recycleRecordBean.service) && this.status == recycleRecordBean.status && Intrinsics.areEqual(this.totalMoney, recycleRecordBean.totalMoney) && this.uid == recycleRecordBean.uid && Intrinsics.areEqual(this.game, recycleRecordBean.game);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((this.accountId * 31) + this.accountName.hashCode()) * 31) + this.auditTime.hashCode()) * 31) + this.createTime) * 31) + this.effectiveMoney.hashCode()) * 31) + this.gameId) * 31) + this.id) * 31) + this.planId) * 31) + this.rate.hashCode()) * 31) + this.recycleMoney.hashCode()) * 31) + this.refuseRemark.hashCode()) * 31) + this.returnType) * 31) + this.roleName.hashCode()) * 31) + this.service.hashCode()) * 31) + this.status) * 31) + this.totalMoney.hashCode()) * 31) + this.uid) * 31) + this.game.hashCode();
    }

    public String toString() {
        return "RecycleRecordBean(accountId=" + this.accountId + ", accountName=" + this.accountName + ", auditTime=" + this.auditTime + ", createTime=" + this.createTime + ", effectiveMoney=" + this.effectiveMoney + ", gameId=" + this.gameId + ", id=" + this.id + ", planId=" + this.planId + ", rate=" + this.rate + ", recycleMoney=" + this.recycleMoney + ", refuseRemark=" + this.refuseRemark + ", returnType=" + this.returnType + ", roleName=" + this.roleName + ", service=" + this.service + ", status=" + this.status + ", totalMoney=" + this.totalMoney + ", uid=" + this.uid + ", game=" + this.game + ")";
    }

    public RecycleRecordBean(int i, String accountName, String auditTime, int i2, String effectiveMoney, int i3, int i4, int i5, String rate, String recycleMoney, String refuseRemark, int i6, String roleName, String service, int i7, String totalMoney, int i8, GameBean game) {
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(auditTime, "auditTime");
        Intrinsics.checkNotNullParameter(effectiveMoney, "effectiveMoney");
        Intrinsics.checkNotNullParameter(rate, "rate");
        Intrinsics.checkNotNullParameter(recycleMoney, "recycleMoney");
        Intrinsics.checkNotNullParameter(refuseRemark, "refuseRemark");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        Intrinsics.checkNotNullParameter(game, "game");
        this.accountId = i;
        this.accountName = accountName;
        this.auditTime = auditTime;
        this.createTime = i2;
        this.effectiveMoney = effectiveMoney;
        this.gameId = i3;
        this.id = i4;
        this.planId = i5;
        this.rate = rate;
        this.recycleMoney = recycleMoney;
        this.refuseRemark = refuseRemark;
        this.returnType = i6;
        this.roleName = roleName;
        this.service = service;
        this.status = i7;
        this.totalMoney = totalMoney;
        this.uid = i8;
        this.game = game;
    }

    public final int getAccountId() {
        return this.accountId;
    }

    public final String getAccountName() {
        return this.accountName;
    }

    public final String getAuditTime() {
        return this.auditTime;
    }

    public final int getCreateTime() {
        return this.createTime;
    }

    public final String getEffectiveMoney() {
        return this.effectiveMoney;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final int getId() {
        return this.id;
    }

    public final int getPlanId() {
        return this.planId;
    }

    public final String getRate() {
        return this.rate;
    }

    public final String getRecycleMoney() {
        return this.recycleMoney;
    }

    public final String getRefuseRemark() {
        return this.refuseRemark;
    }

    public final int getReturnType() {
        return this.returnType;
    }

    public final String getRoleName() {
        return this.roleName;
    }

    public final String getService() {
        return this.service;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getTotalMoney() {
        return this.totalMoney;
    }

    public final int getUid() {
        return this.uid;
    }

    public final GameBean getGame() {
        return this.game;
    }
}
