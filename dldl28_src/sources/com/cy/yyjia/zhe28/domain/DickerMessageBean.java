package com.cy.yyjia.zhe28.domain;

import android.content.Intent;
import android.view.View;
import com.cy.yyjia.zhe28.ui.activity.DealDetailActivity;
import com.volcengine.common.contant.CommonConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DickerMessageBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\bF\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BË\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0005\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003¢\u0006\u0002\u0010\u001eJ\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0005HÆ\u0003J\u000f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010HÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0005HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u0005HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0005HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0005HÆ\u0003J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\tHÆ\u0003J\t\u0010Q\u001a\u00020\u0003HÆ\u0003J\t\u0010R\u001a\u00020\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0003HÆ\u0003J\t\u0010T\u001a\u00020\u0003HÆ\u0003Jÿ\u0001\u0010U\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00052\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u0003HÆ\u0001J\u0013\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010Y\u001a\u00020\u0005J\u0006\u0010Z\u001a\u00020\u0005J\u0006\u0010[\u001a\u00020\u0005J\t\u0010\\\u001a\u00020\u0003HÖ\u0001J\u000e\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020`J\t\u0010a\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010 R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010 \"\u0004\b+\u0010,R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\"R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\"R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\"R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\"R\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\"R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\"R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\"R\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010 R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\"R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010 R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\"R\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010 R\u0011\u0010\u001c\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b;\u0010 R\u0011\u0010\u001d\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b<\u0010 ¨\u0006b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DickerMessageBean;", "", "accountTreadId", "", "account_name", "", "createTime", "dateline", "endTime", "", CommonConstants.key_gameId, "game_icon", "id", "isread", "orderStatus", "pic", "", "price", "productName", "roleName", "sellMoney", "service", "status", "status_str", "toUid", "totalMoney", CommonConstants.KEY_MESSAGE, "type", "uid", "updateTime", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JILjava/lang/String;IILjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;III)V", "getAccountTreadId", "()I", "getAccount_name", "()Ljava/lang/String;", "getCreateTime", "getDateline", "getEndTime", "()J", "getGameId", "getGame_icon", "getId", "getIsread", "setIsread", "(I)V", "getMessage", "getOrderStatus", "getPic", "()Ljava/util/List;", "getPrice", "getProductName", "getRoleName", "getSellMoney", "getService", "getStatus", "getStatus_str", "getToUid", "getTotalMoney", "getType", "getUid", "getUpdateTime", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getDescStr", "getPayTimeLimit", "getStatusText", "hashCode", "toDetail", "", "v", "Landroid/view/View;", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class DickerMessageBean {
    public static final int $stable = 8;
    private final int accountTreadId;
    private final String account_name;
    private final String createTime;
    private final String dateline;
    private final long endTime;
    private final int gameId;
    private final String game_icon;
    private final int id;
    private int isread;
    private final String message;
    private final String orderStatus;
    private final List<String> pic;
    private final String price;
    private final String productName;
    private final String roleName;
    private final String sellMoney;
    private final String service;
    private final int status;
    private final String status_str;
    private final int toUid;
    private final String totalMoney;
    private final int type;
    private final int uid;
    private final int updateTime;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getAccountTreadId() {
        return this.accountTreadId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getOrderStatus() {
        return this.orderStatus;
    }

    public final List<String> component11() {
        return this.pic;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getRoleName() {
        return this.roleName;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getSellMoney() {
        return this.sellMoney;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getService() {
        return this.service;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getStatus_str() {
        return this.status_str;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final int getToUid() {
        return this.toUid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccount_name() {
        return this.account_name;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getTotalMoney() {
        return this.totalMoney;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final int getUpdateTime() {
        return this.updateTime;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCreateTime() {
        return this.createTime;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getEndTime() {
        return this.endTime;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getGame_icon() {
        return this.game_icon;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getIsread() {
        return this.isread;
    }

    public final DickerMessageBean copy(int accountTreadId, String account_name, String createTime, String dateline, long endTime, int gameId, String game_icon, int id, int isread, String orderStatus, List<String> pic, String price, String productName, String roleName, String sellMoney, String service, int status, String status_str, int toUid, String totalMoney, String message, int type, int uid, int updateTime) {
        Intrinsics.checkNotNullParameter(account_name, "account_name");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(game_icon, "game_icon");
        Intrinsics.checkNotNullParameter(orderStatus, "orderStatus");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(sellMoney, "sellMoney");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(status_str, "status_str");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        Intrinsics.checkNotNullParameter(message, "message");
        return new DickerMessageBean(accountTreadId, account_name, createTime, dateline, endTime, gameId, game_icon, id, isread, orderStatus, pic, price, productName, roleName, sellMoney, service, status, status_str, toUid, totalMoney, message, type, uid, updateTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DickerMessageBean)) {
            return false;
        }
        DickerMessageBean dickerMessageBean = (DickerMessageBean) other;
        return this.accountTreadId == dickerMessageBean.accountTreadId && Intrinsics.areEqual(this.account_name, dickerMessageBean.account_name) && Intrinsics.areEqual(this.createTime, dickerMessageBean.createTime) && Intrinsics.areEqual(this.dateline, dickerMessageBean.dateline) && this.endTime == dickerMessageBean.endTime && this.gameId == dickerMessageBean.gameId && Intrinsics.areEqual(this.game_icon, dickerMessageBean.game_icon) && this.id == dickerMessageBean.id && this.isread == dickerMessageBean.isread && Intrinsics.areEqual(this.orderStatus, dickerMessageBean.orderStatus) && Intrinsics.areEqual(this.pic, dickerMessageBean.pic) && Intrinsics.areEqual(this.price, dickerMessageBean.price) && Intrinsics.areEqual(this.productName, dickerMessageBean.productName) && Intrinsics.areEqual(this.roleName, dickerMessageBean.roleName) && Intrinsics.areEqual(this.sellMoney, dickerMessageBean.sellMoney) && Intrinsics.areEqual(this.service, dickerMessageBean.service) && this.status == dickerMessageBean.status && Intrinsics.areEqual(this.status_str, dickerMessageBean.status_str) && this.toUid == dickerMessageBean.toUid && Intrinsics.areEqual(this.totalMoney, dickerMessageBean.totalMoney) && Intrinsics.areEqual(this.message, dickerMessageBean.message) && this.type == dickerMessageBean.type && this.uid == dickerMessageBean.uid && this.updateTime == dickerMessageBean.updateTime;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((this.accountTreadId * 31) + this.account_name.hashCode()) * 31) + this.createTime.hashCode()) * 31) + this.dateline.hashCode()) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.endTime)) * 31) + this.gameId) * 31) + this.game_icon.hashCode()) * 31) + this.id) * 31) + this.isread) * 31) + this.orderStatus.hashCode()) * 31) + this.pic.hashCode()) * 31) + this.price.hashCode()) * 31) + this.productName.hashCode()) * 31) + this.roleName.hashCode()) * 31) + this.sellMoney.hashCode()) * 31) + this.service.hashCode()) * 31) + this.status) * 31) + this.status_str.hashCode()) * 31) + this.toUid) * 31) + this.totalMoney.hashCode()) * 31) + this.message.hashCode()) * 31) + this.type) * 31) + this.uid) * 31) + this.updateTime;
    }

    public String toString() {
        return "DickerMessageBean(accountTreadId=" + this.accountTreadId + ", account_name=" + this.account_name + ", createTime=" + this.createTime + ", dateline=" + this.dateline + ", endTime=" + this.endTime + ", gameId=" + this.gameId + ", game_icon=" + this.game_icon + ", id=" + this.id + ", isread=" + this.isread + ", orderStatus=" + this.orderStatus + ", pic=" + this.pic + ", price=" + this.price + ", productName=" + this.productName + ", roleName=" + this.roleName + ", sellMoney=" + this.sellMoney + ", service=" + this.service + ", status=" + this.status + ", status_str=" + this.status_str + ", toUid=" + this.toUid + ", totalMoney=" + this.totalMoney + ", message=" + this.message + ", type=" + this.type + ", uid=" + this.uid + ", updateTime=" + this.updateTime + ")";
    }

    public DickerMessageBean(int i, String account_name, String createTime, String dateline, long j, int i2, String game_icon, int i3, int i4, String orderStatus, List<String> pic, String price, String productName, String roleName, String sellMoney, String service, int i5, String status_str, int i6, String totalMoney, String message, int i7, int i8, int i9) {
        Intrinsics.checkNotNullParameter(account_name, "account_name");
        Intrinsics.checkNotNullParameter(createTime, "createTime");
        Intrinsics.checkNotNullParameter(dateline, "dateline");
        Intrinsics.checkNotNullParameter(game_icon, "game_icon");
        Intrinsics.checkNotNullParameter(orderStatus, "orderStatus");
        Intrinsics.checkNotNullParameter(pic, "pic");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(sellMoney, "sellMoney");
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(status_str, "status_str");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        Intrinsics.checkNotNullParameter(message, "message");
        this.accountTreadId = i;
        this.account_name = account_name;
        this.createTime = createTime;
        this.dateline = dateline;
        this.endTime = j;
        this.gameId = i2;
        this.game_icon = game_icon;
        this.id = i3;
        this.isread = i4;
        this.orderStatus = orderStatus;
        this.pic = pic;
        this.price = price;
        this.productName = productName;
        this.roleName = roleName;
        this.sellMoney = sellMoney;
        this.service = service;
        this.status = i5;
        this.status_str = status_str;
        this.toUid = i6;
        this.totalMoney = totalMoney;
        this.message = message;
        this.type = i7;
        this.uid = i8;
        this.updateTime = i9;
    }

    public final int getAccountTreadId() {
        return this.accountTreadId;
    }

    public final String getAccount_name() {
        return this.account_name;
    }

    public final String getCreateTime() {
        return this.createTime;
    }

    public final String getDateline() {
        return this.dateline;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final String getGame_icon() {
        return this.game_icon;
    }

    public final int getId() {
        return this.id;
    }

    public final int getIsread() {
        return this.isread;
    }

    public final void setIsread(int i) {
        this.isread = i;
    }

    public final String getOrderStatus() {
        return this.orderStatus;
    }

    public final List<String> getPic() {
        return this.pic;
    }

    public final String getPrice() {
        return this.price;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final String getRoleName() {
        return this.roleName;
    }

    public final String getSellMoney() {
        return this.sellMoney;
    }

    public final String getService() {
        return this.service;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getStatus_str() {
        return this.status_str;
    }

    public final int getToUid() {
        return this.toUid;
    }

    public final String getTotalMoney() {
        return this.totalMoney;
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getType() {
        return this.type;
    }

    public final int getUid() {
        return this.uid;
    }

    public final int getUpdateTime() {
        return this.updateTime;
    }

    public final String getDescStr() {
        return this.service + " 丨 游戏充值：" + this.totalMoney;
    }

    public final void toDetail(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intent intent = new Intent(v.getContext(), (Class<?>) DealDetailActivity.class);
        intent.putExtra("id", this.accountTreadId);
        intent.putExtra("dickerMoney", this.price);
        intent.putExtra("customer", this.type == 0);
        intent.putExtra("dickerPrice", Intrinsics.areEqual(getStatusText(), "同意"));
        v.getContext().startActivity(intent);
    }

    public final String getStatusText() {
        if (Intrinsics.areEqual(this.orderStatus, "selled")) {
            return this.status_str;
        }
        int i = this.status;
        if (i == 0) {
            return "申请";
        }
        if (i == 1) {
            if ((this.endTime * ((long) 1000)) - System.currentTimeMillis() > 0) {
                return "同意";
            }
            return "已超时";
        }
        if (i == 2) {
            return "已完成";
        }
        if (i == 3) {
            return "已拒绝";
        }
        if (i == 4) {
            return "已取消";
        }
        return "";
    }

    public final String getPayTimeLimit() {
        long jCurrentTimeMillis = (this.endTime * ((long) 1000)) - System.currentTimeMillis();
        if (jCurrentTimeMillis < 0) {
            return "";
        }
        return "待付款时间：" + (jCurrentTimeMillis / ((long) 86400000)) + "天" + ((jCurrentTimeMillis / ((long) 3600000)) % ((long) 24)) + "小时";
    }
}
