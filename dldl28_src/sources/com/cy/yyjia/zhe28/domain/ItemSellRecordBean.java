package com.cy.yyjia.zhe28.domain;

import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ItemSellRecordBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b3\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0007¢\u0006\u0002\u0010\u0015J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0007HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\u0007HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0007HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J©\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u0007HÆ\u0001J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u0007HÖ\u0001J\t\u0010>\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0011\u0010\u0012\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0017R\u0011\u0010\u0014\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001b¨\u0006?"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ItemSellRecordBean;", "", "createTime", "", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", CommonConstants.key_gameId, "", "id", "money", "num", "name", "price", "pic", "roleId", "roleName", "serviceCode", "serviceId", "status", "status_txt", "uid", "(Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/GameBean;IILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;I)V", "getCreateTime", "()Ljava/lang/String;", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getGameId", "()I", "getId", "getMoney", "getName", "getNum", "getPic", "getPrice", "getRoleId", "getRoleName", "getServiceCode", "getServiceId", "getStatus", "getStatus_txt", "getUid", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ItemSellRecordBean {
    public static final int $stable = 8;
    private final String createTime;
    private final GameBean game;
    private final int gameId;
    private final int id;
    private final String money;
    private final String name;
    private final int num;
    private final String pic;
    private final String price;
    private final String roleId;
    private final String roleName;
    private final String serviceCode;
    private final String serviceId;
    private final int status;
    private final String status_txt;
    private final int uid;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCreateTime() {
        return this.createTime;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getRoleId() {
        return this.roleId;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getRoleName() {
        return this.roleName;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getServiceCode() {
        return this.serviceCode;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getServiceId() {
        return this.serviceId;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getStatus_txt() {
        return this.status_txt;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getMoney() {
        return this.money;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getNum() {
        return this.num;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPic() {
        return this.pic;
    }

    public final ItemSellRecordBean copy(String createTime, GameBean game, int gameId, int id, String money, int num, String name, String price, String pic, String roleId, String roleName, String serviceCode, String serviceId, int status, String status_txt, int uid) {
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(roleId, "roleId");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        Intrinsics.checkNotNullParameter(serviceId, "serviceId");
        Intrinsics.checkNotNullParameter(status_txt, "status_txt");
        return new ItemSellRecordBean(createTime, game, gameId, id, money, num, name, price, pic, roleId, roleName, serviceCode, serviceId, status, status_txt, uid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ItemSellRecordBean)) {
            return false;
        }
        ItemSellRecordBean itemSellRecordBean = (ItemSellRecordBean) other;
        return Intrinsics.areEqual(this.createTime, itemSellRecordBean.createTime) && Intrinsics.areEqual(this.game, itemSellRecordBean.game) && this.gameId == itemSellRecordBean.gameId && this.id == itemSellRecordBean.id && Intrinsics.areEqual(this.money, itemSellRecordBean.money) && this.num == itemSellRecordBean.num && Intrinsics.areEqual(this.name, itemSellRecordBean.name) && Intrinsics.areEqual(this.price, itemSellRecordBean.price) && Intrinsics.areEqual(this.pic, itemSellRecordBean.pic) && Intrinsics.areEqual(this.roleId, itemSellRecordBean.roleId) && Intrinsics.areEqual(this.roleName, itemSellRecordBean.roleName) && Intrinsics.areEqual(this.serviceCode, itemSellRecordBean.serviceCode) && Intrinsics.areEqual(this.serviceId, itemSellRecordBean.serviceId) && this.status == itemSellRecordBean.status && Intrinsics.areEqual(this.status_txt, itemSellRecordBean.status_txt) && this.uid == itemSellRecordBean.uid;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.createTime.hashCode() * 31) + this.game.hashCode()) * 31) + this.gameId) * 31) + this.id) * 31) + this.money.hashCode()) * 31) + this.num) * 31) + this.name.hashCode()) * 31) + this.price.hashCode()) * 31) + this.pic.hashCode()) * 31) + this.roleId.hashCode()) * 31) + this.roleName.hashCode()) * 31) + this.serviceCode.hashCode()) * 31) + this.serviceId.hashCode()) * 31) + this.status) * 31) + this.status_txt.hashCode()) * 31) + this.uid;
    }

    public String toString() {
        return "ItemSellRecordBean(createTime=" + this.createTime + ", game=" + this.game + ", gameId=" + this.gameId + ", id=" + this.id + ", money=" + this.money + ", num=" + this.num + ", name=" + this.name + ", price=" + this.price + ", pic=" + this.pic + ", roleId=" + this.roleId + ", roleName=" + this.roleName + ", serviceCode=" + this.serviceCode + ", serviceId=" + this.serviceId + ", status=" + this.status + ", status_txt=" + this.status_txt + ", uid=" + this.uid + ")";
    }

    public ItemSellRecordBean(String createTime, GameBean game, int i, int i2, String money, int i3, String name, String price, String pic, String roleId, String roleName, String serviceCode, String serviceId, int i4, String status_txt, int i5) {
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(game, "game");
        Intrinsics.checkNotNullParameter(money, "money");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(roleId, "roleId");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        Intrinsics.checkNotNullParameter(serviceId, "serviceId");
        Intrinsics.checkNotNullParameter(status_txt, "status_txt");
        this.createTime = createTime;
        this.game = game;
        this.gameId = i;
        this.id = i2;
        this.money = money;
        this.num = i3;
        this.name = name;
        this.price = price;
        this.pic = pic;
        this.roleId = roleId;
        this.roleName = roleName;
        this.serviceCode = serviceCode;
        this.serviceId = serviceId;
        this.status = i4;
        this.status_txt = status_txt;
        this.uid = i5;
    }

    public final String getCreateTime() {
        return this.createTime;
    }

    public final GameBean getGame() {
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

    public final String getName() {
        return this.name;
    }

    public final String getPrice() {
        return this.price;
    }

    public final String getPic() {
        return this.pic;
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

    public final String getStatus_txt() {
        return this.status_txt;
    }

    public final int getUid() {
        return this.uid;
    }
}
