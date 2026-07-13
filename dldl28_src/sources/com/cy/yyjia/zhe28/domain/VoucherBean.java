package com.cy.yyjia.zhe28.domain;

import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VoucherBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0003HÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006'"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/VoucherBean;", "", CommonConstants.key_accountId, "", "coupon", "", "couponAmount", "dateline", CommonConstants.key_gameId, "id", "status", "game", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "(ILjava/lang/String;Ljava/lang/String;IIIILcom/cy/yyjia/zhe28/domain/GameBean;)V", "getAccountId", "()I", "getCoupon", "()Ljava/lang/String;", "getCouponAmount", "getDateline", "getGame", "()Lcom/cy/yyjia/zhe28/domain/GameBean;", "getGameId", "getId", "getStatus", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class VoucherBean {
    public static final int $stable = 8;
    private final int accountId;
    private final String coupon;
    private final String couponAmount;
    private final int dateline;
    private final GameBean game;
    private final int gameId;
    private final int id;
    private final int status;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getAccountId() {
        return this.accountId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCoupon() {
        return this.coupon;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCouponAmount() {
        return this.couponAmount;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getDateline() {
        return this.dateline;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final GameBean getGame() {
        return this.game;
    }

    public final VoucherBean copy(int accountId, String coupon, String couponAmount, int dateline, int gameId, int id, int status, GameBean game) {
        Intrinsics.checkNotNullParameter(coupon, "coupon");
        Intrinsics.checkNotNullParameter(couponAmount, "couponAmount");
        Intrinsics.checkNotNullParameter(game, "game");
        return new VoucherBean(accountId, coupon, couponAmount, dateline, gameId, id, status, game);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VoucherBean)) {
            return false;
        }
        VoucherBean voucherBean = (VoucherBean) other;
        return this.accountId == voucherBean.accountId && Intrinsics.areEqual(this.coupon, voucherBean.coupon) && Intrinsics.areEqual(this.couponAmount, voucherBean.couponAmount) && this.dateline == voucherBean.dateline && this.gameId == voucherBean.gameId && this.id == voucherBean.id && this.status == voucherBean.status && Intrinsics.areEqual(this.game, voucherBean.game);
    }

    public int hashCode() {
        return (((((((((((((this.accountId * 31) + this.coupon.hashCode()) * 31) + this.couponAmount.hashCode()) * 31) + this.dateline) * 31) + this.gameId) * 31) + this.id) * 31) + this.status) * 31) + this.game.hashCode();
    }

    public String toString() {
        return "VoucherBean(accountId=" + this.accountId + ", coupon=" + this.coupon + ", couponAmount=" + this.couponAmount + ", dateline=" + this.dateline + ", gameId=" + this.gameId + ", id=" + this.id + ", status=" + this.status + ", game=" + this.game + ")";
    }

    public VoucherBean(int i, String coupon, String couponAmount, int i2, int i3, int i4, int i5, GameBean game) {
        Intrinsics.checkNotNullParameter(coupon, "coupon");
        Intrinsics.checkNotNullParameter(couponAmount, "couponAmount");
        Intrinsics.checkNotNullParameter(game, "game");
        this.accountId = i;
        this.coupon = coupon;
        this.couponAmount = couponAmount;
        this.dateline = i2;
        this.gameId = i3;
        this.id = i4;
        this.status = i5;
        this.game = game;
    }

    public final int getAccountId() {
        return this.accountId;
    }

    public final String getCoupon() {
        return this.coupon;
    }

    public final String getCouponAmount() {
        return this.couponAmount;
    }

    public final int getDateline() {
        return this.dateline;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final int getId() {
        return this.id;
    }

    public final int getStatus() {
        return this.status;
    }

    public final GameBean getGame() {
        return this.game;
    }
}
