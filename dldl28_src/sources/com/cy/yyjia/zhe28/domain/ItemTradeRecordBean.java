package com.cy.yyjia.zhe28.domain;

import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ItemTradeRecordBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b=\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001:\u0002MNB¥\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\b\u0012\u0006\u0010\u0013\u001a\u00020\b\u0012\u0006\u0010\u0014\u001a\u00020\b\u0012\u0006\u0010\u0015\u001a\u00020\b\u0012\u0006\u0010\u0016\u001a\u00020\b\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\b\u0012\u0006\u0010\u0019\u001a\u00020\u0003¢\u0006\u0002\u0010\u001aJ\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\bHÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\bHÆ\u0003J\t\u00108\u001a\u00020\bHÆ\u0003J\t\u00109\u001a\u00020\bHÆ\u0003J\t\u0010:\u001a\u00020\bHÆ\u0003J\t\u0010;\u001a\u00020\bHÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\bHÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\bHÆ\u0003J\t\u0010B\u001a\u00020\bHÆ\u0003J\t\u0010C\u001a\u00020\u000bHÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\bHÆ\u0003JÑ\u0001\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\b2\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\u0003HÆ\u0001J\u0013\u0010H\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010K\u001a\u00020\u0003HÖ\u0001J\t\u0010L\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010!R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001cR\u0011\u0010\u0012\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010!R\u0011\u0010\u0013\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b,\u0010!R\u0011\u0010\u0014\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b-\u0010!R\u0011\u0010\u0015\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b.\u0010!R\u0011\u0010\u0016\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b/\u0010!R\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001cR\u0011\u0010\u0018\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b1\u0010!R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001c¨\u0006O"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean;", "", CommonConstants.key_accountId, "", "asset", "Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean$Asset;", "assetId", "createTime", "", "deliverTime", "game", "Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean$Game;", CommonConstants.key_gameId, "id", "money", "num", "payTime", "payType", "price", "roleId", "roleName", "serviceCode", "serviceId", "status", "statusName", "uid", "(ILcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean$Asset;ILjava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean$Game;IILjava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;I)V", "getAccountId", "()I", "getAsset", "()Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean$Asset;", "getAssetId", "getCreateTime", "()Ljava/lang/String;", "getDeliverTime", "getGame", "()Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean$Game;", "getGameId", "getId", "getMoney", "getNum", "getPayTime", "getPayType", "getPrice", "getRoleId", "getRoleName", "getServiceCode", "getServiceId", "getStatus", "getStatusName", "getUid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "Asset", "Game", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ItemTradeRecordBean {
    public static final int $stable = 0;
    private final int accountId;
    private final Asset asset;
    private final int assetId;
    private final String createTime;
    private final String deliverTime;
    private final Game game;
    private final int gameId;
    private final int id;
    private final String money;
    private final int num;
    private final String payTime;
    private final int payType;
    private final String price;
    private final String roleId;
    private final String roleName;
    private final String serviceCode;
    private final String serviceId;
    private final int status;
    private final String statusName;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getAccountId() {
        return this.accountId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getNum() {
        return this.num;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPayTime() {
        return this.payTime;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getPayType() {
        return this.payType;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getRoleId() {
        return this.roleId;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getRoleName() {
        return this.roleName;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getServiceCode() {
        return this.serviceCode;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getServiceId() {
        return this.serviceId;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getStatusName() {
        return this.statusName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Asset getAsset() {
        return this.asset;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getAssetId() {
        return this.assetId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCreateTime() {
        return this.createTime;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDeliverTime() {
        return this.deliverTime;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Game getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getMoney() {
        return this.money;
    }

    public final ItemTradeRecordBean copy(int accountId, Asset asset, int assetId, String createTime, String deliverTime, Game game, int gameId, int id, String money, int num, String payTime, int payType, String price, String roleId, String roleName, String serviceCode, String serviceId, int status, String statusName, int uid) {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(deliverTime, "deliverTime");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(payTime, "payTime");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(roleId, "roleId");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        Intrinsics.checkNotNullParameter(serviceId, "serviceId");
        Intrinsics.checkNotNullParameter(statusName, "statusName");
        return new ItemTradeRecordBean(accountId, asset, assetId, createTime, deliverTime, game, gameId, id, money, num, payTime, payType, price, roleId, roleName, serviceCode, serviceId, status, statusName, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ItemTradeRecordBean)) {
            return false;
        }
        ItemTradeRecordBean itemTradeRecordBean = (ItemTradeRecordBean) other;
        return this.accountId == itemTradeRecordBean.accountId && Intrinsics.areEqual(this.asset, itemTradeRecordBean.asset) && this.assetId == itemTradeRecordBean.assetId && Intrinsics.areEqual(this.createTime, itemTradeRecordBean.createTime) && Intrinsics.areEqual(this.deliverTime, itemTradeRecordBean.deliverTime) && Intrinsics.areEqual(this.game, itemTradeRecordBean.game) && this.gameId == itemTradeRecordBean.gameId && this.id == itemTradeRecordBean.id && Intrinsics.areEqual(this.money, itemTradeRecordBean.money) && this.num == itemTradeRecordBean.num && Intrinsics.areEqual(this.payTime, itemTradeRecordBean.payTime) && this.payType == itemTradeRecordBean.payType && Intrinsics.areEqual(this.price, itemTradeRecordBean.price) && Intrinsics.areEqual(this.roleId, itemTradeRecordBean.roleId) && Intrinsics.areEqual(this.roleName, itemTradeRecordBean.roleName) && Intrinsics.areEqual(this.serviceCode, itemTradeRecordBean.serviceCode) && Intrinsics.areEqual(this.serviceId, itemTradeRecordBean.serviceId) && this.status == itemTradeRecordBean.status && Intrinsics.areEqual(this.statusName, itemTradeRecordBean.statusName) && this.uid == itemTradeRecordBean.uid;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((this.accountId * 31) + this.asset.hashCode()) * 31) + this.assetId) * 31) + this.createTime.hashCode()) * 31) + this.deliverTime.hashCode()) * 31) + this.game.hashCode()) * 31) + this.gameId) * 31) + this.id) * 31) + this.money.hashCode()) * 31) + this.num) * 31) + this.payTime.hashCode()) * 31) + this.payType) * 31) + this.price.hashCode()) * 31) + this.roleId.hashCode()) * 31) + this.roleName.hashCode()) * 31) + this.serviceCode.hashCode()) * 31) + this.serviceId.hashCode()) * 31) + this.status) * 31) + this.statusName.hashCode()) * 31) + this.uid;
    }

    public String toString() {
        return "ItemTradeRecordBean(accountId=" + this.accountId + ", asset=" + this.asset + ", assetId=" + this.assetId + ", createTime=" + this.createTime + ", deliverTime=" + this.deliverTime + ", game=" + this.game + ", gameId=" + this.gameId + ", id=" + this.id + ", money=" + this.money + ", num=" + this.num + ", payTime=" + this.payTime + ", payType=" + this.payType + ", price=" + this.price + ", roleId=" + this.roleId + ", roleName=" + this.roleName + ", serviceCode=" + this.serviceCode + ", serviceId=" + this.serviceId + ", status=" + this.status + ", statusName=" + this.statusName + ", uid=" + this.uid + ")";
    }

    public ItemTradeRecordBean(int i, Asset asset, int i2, String createTime, String deliverTime, Game game, int i3, int i4, String money, int i5, String payTime, int i6, String price, String roleId, String roleName, String serviceCode, String serviceId, int i7, String statusName, int i8) {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(deliverTime, "deliverTime");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(payTime, "payTime");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(roleId, "roleId");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        Intrinsics.checkNotNullParameter(serviceId, "serviceId");
        Intrinsics.checkNotNullParameter(statusName, "statusName");
        this.accountId = i;
        this.asset = asset;
        this.assetId = i2;
        this.createTime = createTime;
        this.deliverTime = deliverTime;
        this.game = game;
        this.gameId = i3;
        this.id = i4;
        this.money = money;
        this.num = i5;
        this.payTime = payTime;
        this.payType = i6;
        this.price = price;
        this.roleId = roleId;
        this.roleName = roleName;
        this.serviceCode = serviceCode;
        this.serviceId = serviceId;
        this.status = i7;
        this.statusName = statusName;
        this.uid = i8;
    }

    public final int getAccountId() {
        return this.accountId;
    }

    public final Asset getAsset() {
        return this.asset;
    }

    public final int getAssetId() {
        return this.assetId;
    }

    public final String getCreateTime() {
        return this.createTime;
    }

    public final String getDeliverTime() {
        return this.deliverTime;
    }

    public final Game getGame() {
        return this.game;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final int getId() {
        return this.id;
    }

    public final String getMoney() {
        return this.money;
    }

    public final int getNum() {
        return this.num;
    }

    public final String getPayTime() {
        return this.payTime;
    }

    public final int getPayType() {
        return this.payType;
    }

    public final String getPrice() {
        return this.price;
    }

    public final String getRoleId() {
        return this.roleId;
    }

    public final String getRoleName() {
        return this.roleName;
    }

    public final String getServiceCode() {
        return this.serviceCode;
    }

    public final String getServiceId() {
        return this.serviceId;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getStatusName() {
        return this.statusName;
    }

    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: compiled from: ItemTradeRecordBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean$Asset;", "", "id", "", "name", "", "pic", "(ILjava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getPic", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Asset {
        public static final int $stable = 0;
        private final int id;
        private final String name;
        private final String pic;

        public static /* synthetic */ Asset copy$default(Asset asset, int i, String str, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = asset.id;
            }
            if ((i2 & 2) != 0) {
                str = asset.name;
            }
            if ((i2 & 4) != 0) {
                str2 = asset.pic;
            }
            return asset.copy(i, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPic() {
            return this.pic;
        }

        public final Asset copy(int id, String name, String pic) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(pic, "pic");
            return new Asset(id, name, pic);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Asset)) {
                return false;
            }
            Asset asset = (Asset) other;
            return this.id == asset.id && Intrinsics.areEqual(this.name, asset.name) && Intrinsics.areEqual(this.pic, asset.pic);
        }

        public int hashCode() {
            return (((this.id * 31) + this.name.hashCode()) * 31) + this.pic.hashCode();
        }

        public String toString() {
            return "Asset(id=" + this.id + ", name=" + this.name + ", pic=" + this.pic + ")";
        }

        public Asset(int i, String name, String pic) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(pic, "pic");
            this.id = i;
            this.name = name;
            this.pic = pic;
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPic() {
            return this.pic;
        }
    }

    /* JADX INFO: compiled from: ItemTradeRecordBean.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ItemTradeRecordBean$Game;", "", "icon", "", "id", "", "name", "score", "type", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getIcon", "()Ljava/lang/String;", "getId", "()I", "getName", "getScore", "getType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Game {
        public static final int $stable = 0;
        private final String icon;
        private final int id;
        private final String name;
        private final String score;
        private final String type;

        public static /* synthetic */ Game copy$default(Game game, String str, int i, String str2, String str3, String str4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = game.icon;
            }
            if ((i2 & 2) != 0) {
                i = game.id;
            }
            int i3 = i;
            if ((i2 & 4) != 0) {
                str2 = game.name;
            }
            String str5 = str2;
            if ((i2 & 8) != 0) {
                str3 = game.score;
            }
            String str6 = str3;
            if ((i2 & 16) != 0) {
                str4 = game.type;
            }
            return game.copy(str, i3, str5, str6, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getIcon() {
            return this.icon;
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
        public final String getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final Game copy(String icon, int id, String name, String score, String type) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(score, "score");
            Intrinsics.checkNotNullParameter(type, "type");
            return new Game(icon, id, name, score, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Game)) {
                return false;
            }
            Game game = (Game) other;
            return Intrinsics.areEqual(this.icon, game.icon) && this.id == game.id && Intrinsics.areEqual(this.name, game.name) && Intrinsics.areEqual(this.score, game.score) && Intrinsics.areEqual(this.type, game.type);
        }

        public int hashCode() {
            return (((((((this.icon.hashCode() * 31) + this.id) * 31) + this.name.hashCode()) * 31) + this.score.hashCode()) * 31) + this.type.hashCode();
        }

        public String toString() {
            return "Game(icon=" + this.icon + ", id=" + this.id + ", name=" + this.name + ", score=" + this.score + ", type=" + this.type + ")";
        }

        public Game(String icon, int i, String name, String score, String type) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(score, "score");
            Intrinsics.checkNotNullParameter(type, "type");
            this.icon = icon;
            this.id = i;
            this.name = name;
            this.score = score;
            this.type = type;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getScore() {
            return this.score;
        }

        public final String getType() {
            return this.type;
        }
    }
}
