package com.cy.yyjia.zhe28.domain;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: GMOrderBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b-\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0003¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\u0095\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0003HÆ\u0001J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\u0005HÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014¨\u00067"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GMOrderBean;", "", "actual_price", "", "createtime", "", "gameid", "gmOrderId", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "orderId", "order_price", "role_id", "role_name", "serverid", "tier_id", "tier_name", "uid", HintConstants.AUTOFILL_HINT_USERNAME, "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getActual_price", "()Ljava/lang/String;", "getCreatetime", "()I", "getGameid", "getGmOrderId", "getIp", "getOrderId", "getOrder_price", "getRole_id", "getRole_name", "getServerid", "getTier_id", "getTier_name", "getUid", "getUsername", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GMOrderBean {
    public static final int $stable = 0;
    private final String actual_price;
    private final int createtime;
    private final String gameid;
    private final String gmOrderId;
    private final String ip;
    private final String orderId;
    private final String order_price;
    private final String role_id;
    private final String role_name;
    private final String serverid;
    private final String tier_id;
    private final String tier_name;
    private final int uid;
    private final String username;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getActual_price() {
        return this.actual_price;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getServerid() {
        return this.serverid;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getTier_id() {
        return this.tier_id;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTier_name() {
        return this.tier_name;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getUid() {
        return this.uid;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getCreatetime() {
        return this.createtime;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getGameid() {
        return this.gameid;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getGmOrderId() {
        return this.gmOrderId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getIp() {
        return this.ip;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getOrderId() {
        return this.orderId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getOrder_price() {
        return this.order_price;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getRole_id() {
        return this.role_id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getRole_name() {
        return this.role_name;
    }

    public final GMOrderBean copy(String actual_price, int createtime, String gameid, String gmOrderId, String ip, String orderId, String order_price, String role_id, String role_name, String serverid, String tier_id, String tier_name, int uid, String username) {
        Intrinsics.checkNotNullParameter(actual_price, "actual_price");
        Intrinsics.checkNotNullParameter(gameid, "gameid");
        Intrinsics.checkNotNullParameter(gmOrderId, "gmOrderId");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(order_price, "order_price");
        Intrinsics.checkNotNullParameter(role_id, "role_id");
        Intrinsics.checkNotNullParameter(role_name, "role_name");
        Intrinsics.checkNotNullParameter(serverid, "serverid");
        Intrinsics.checkNotNullParameter(tier_id, "tier_id");
        Intrinsics.checkNotNullParameter(tier_name, "tier_name");
        Intrinsics.checkNotNullParameter(username, "username");
        return new GMOrderBean(actual_price, createtime, gameid, gmOrderId, ip, orderId, order_price, role_id, role_name, serverid, tier_id, tier_name, uid, username);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GMOrderBean)) {
            return false;
        }
        GMOrderBean gMOrderBean = (GMOrderBean) other;
        return Intrinsics.areEqual(this.actual_price, gMOrderBean.actual_price) && this.createtime == gMOrderBean.createtime && Intrinsics.areEqual(this.gameid, gMOrderBean.gameid) && Intrinsics.areEqual(this.gmOrderId, gMOrderBean.gmOrderId) && Intrinsics.areEqual(this.ip, gMOrderBean.ip) && Intrinsics.areEqual(this.orderId, gMOrderBean.orderId) && Intrinsics.areEqual(this.order_price, gMOrderBean.order_price) && Intrinsics.areEqual(this.role_id, gMOrderBean.role_id) && Intrinsics.areEqual(this.role_name, gMOrderBean.role_name) && Intrinsics.areEqual(this.serverid, gMOrderBean.serverid) && Intrinsics.areEqual(this.tier_id, gMOrderBean.tier_id) && Intrinsics.areEqual(this.tier_name, gMOrderBean.tier_name) && this.uid == gMOrderBean.uid && Intrinsics.areEqual(this.username, gMOrderBean.username);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.actual_price.hashCode() * 31) + this.createtime) * 31) + this.gameid.hashCode()) * 31) + this.gmOrderId.hashCode()) * 31) + this.ip.hashCode()) * 31) + this.orderId.hashCode()) * 31) + this.order_price.hashCode()) * 31) + this.role_id.hashCode()) * 31) + this.role_name.hashCode()) * 31) + this.serverid.hashCode()) * 31) + this.tier_id.hashCode()) * 31) + this.tier_name.hashCode()) * 31) + this.uid) * 31) + this.username.hashCode();
    }

    public String toString() {
        return "GMOrderBean(actual_price=" + this.actual_price + ", createtime=" + this.createtime + ", gameid=" + this.gameid + ", gmOrderId=" + this.gmOrderId + ", ip=" + this.ip + ", orderId=" + this.orderId + ", order_price=" + this.order_price + ", role_id=" + this.role_id + ", role_name=" + this.role_name + ", serverid=" + this.serverid + ", tier_id=" + this.tier_id + ", tier_name=" + this.tier_name + ", uid=" + this.uid + ", username=" + this.username + ")";
    }

    public GMOrderBean(String actual_price, int i, String gameid, String gmOrderId, String ip, String orderId, String order_price, String role_id, String role_name, String serverid, String tier_id, String tier_name, int i2, String username) {
        Intrinsics.checkNotNullParameter(actual_price, "actual_price");
        Intrinsics.checkNotNullParameter(gameid, "gameid");
        Intrinsics.checkNotNullParameter(gmOrderId, "gmOrderId");
        Intrinsics.checkNotNullParameter(ip, "ip");
        Intrinsics.checkNotNullParameter(orderId, "orderId");
        Intrinsics.checkNotNullParameter(order_price, "order_price");
        Intrinsics.checkNotNullParameter(role_id, "role_id");
        Intrinsics.checkNotNullParameter(role_name, "role_name");
        Intrinsics.checkNotNullParameter(serverid, "serverid");
        Intrinsics.checkNotNullParameter(tier_id, "tier_id");
        Intrinsics.checkNotNullParameter(tier_name, "tier_name");
        Intrinsics.checkNotNullParameter(username, "username");
        this.actual_price = actual_price;
        this.createtime = i;
        this.gameid = gameid;
        this.gmOrderId = gmOrderId;
        this.ip = ip;
        this.orderId = orderId;
        this.order_price = order_price;
        this.role_id = role_id;
        this.role_name = role_name;
        this.serverid = serverid;
        this.tier_id = tier_id;
        this.tier_name = tier_name;
        this.uid = i2;
        this.username = username;
    }

    public final String getActual_price() {
        return this.actual_price;
    }

    public final int getCreatetime() {
        return this.createtime;
    }

    public final String getGameid() {
        return this.gameid;
    }

    public final String getGmOrderId() {
        return this.gmOrderId;
    }

    public final String getIp() {
        return this.ip;
    }

    public final String getOrderId() {
        return this.orderId;
    }

    public final String getOrder_price() {
        return this.order_price;
    }

    public final String getRole_id() {
        return this.role_id;
    }

    public final String getRole_name() {
        return this.role_name;
    }

    public final String getServerid() {
        return this.serverid;
    }

    public final String getTier_id() {
        return this.tier_id;
    }

    public final String getTier_name() {
        return this.tier_name;
    }

    public final int getUid() {
        return this.uid;
    }

    public final String getUsername() {
        return this.username;
    }
}
