package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DailyCouponResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DailyCouponResult;", "", "code", "", "data", "Lcom/cy/yyjia/zhe28/domain/DailyCouponResult$Data;", "msg", "", "time", "(ILcom/cy/yyjia/zhe28/domain/DailyCouponResult$Data;Ljava/lang/String;I)V", "getCode", "()I", "getData", "()Lcom/cy/yyjia/zhe28/domain/DailyCouponResult$Data;", "getMsg", "()Ljava/lang/String;", "getTime", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "Coupon", "Data", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class DailyCouponResult {
    public static final int $stable = 0;
    private final int code;
    private final Data data;
    private final String msg;
    private final int time;

    public static /* synthetic */ DailyCouponResult copy$default(DailyCouponResult dailyCouponResult, int i, Data data, String str, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = dailyCouponResult.code;
        }
        if ((i3 & 2) != 0) {
            data = dailyCouponResult.data;
        }
        if ((i3 & 4) != 0) {
            str = dailyCouponResult.msg;
        }
        if ((i3 & 8) != 0) {
            i2 = dailyCouponResult.time;
        }
        return dailyCouponResult.copy(i, data, str, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Data getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getTime() {
        return this.time;
    }

    public final DailyCouponResult copy(int code, Data data, String msg, int time) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(msg, "msg");
        return new DailyCouponResult(code, data, msg, time);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DailyCouponResult)) {
            return false;
        }
        DailyCouponResult dailyCouponResult = (DailyCouponResult) other;
        return this.code == dailyCouponResult.code && Intrinsics.areEqual(this.data, dailyCouponResult.data) && Intrinsics.areEqual(this.msg, dailyCouponResult.msg) && this.time == dailyCouponResult.time;
    }

    public int hashCode() {
        return (((((this.code * 31) + this.data.hashCode()) * 31) + this.msg.hashCode()) * 31) + this.time;
    }

    public String toString() {
        return "DailyCouponResult(code=" + this.code + ", data=" + this.data + ", msg=" + this.msg + ", time=" + this.time + ")";
    }

    public DailyCouponResult(int i, Data data, String msg, int i2) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.code = i;
        this.data = data;
        this.msg = msg;
        this.time = i2;
    }

    public final int getCode() {
        return this.code;
    }

    public final Data getData() {
        return this.data;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final int getTime() {
        return this.time;
    }

    /* JADX INFO: compiled from: DailyCouponResult.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DailyCouponResult$Data;", "", "coupon", "Lcom/cy/yyjia/zhe28/domain/DailyCouponResult$Coupon;", "popup_bg", "", "(Lcom/cy/yyjia/zhe28/domain/DailyCouponResult$Coupon;Ljava/lang/String;)V", "getCoupon", "()Lcom/cy/yyjia/zhe28/domain/DailyCouponResult$Coupon;", "getPopup_bg", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Data {
        public static final int $stable = 0;
        private final Coupon coupon;
        private final String popup_bg;

        public static /* synthetic */ Data copy$default(Data data, Coupon coupon, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                coupon = data.coupon;
            }
            if ((i & 2) != 0) {
                str = data.popup_bg;
            }
            return data.copy(coupon, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Coupon getCoupon() {
            return this.coupon;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPopup_bg() {
            return this.popup_bg;
        }

        public final Data copy(Coupon coupon, String popup_bg) {
            Intrinsics.checkNotNullParameter(coupon, "coupon");
            Intrinsics.checkNotNullParameter(popup_bg, "popup_bg");
            return new Data(coupon, popup_bg);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Data)) {
                return false;
            }
            Data data = (Data) other;
            return Intrinsics.areEqual(this.coupon, data.coupon) && Intrinsics.areEqual(this.popup_bg, data.popup_bg);
        }

        public int hashCode() {
            return (this.coupon.hashCode() * 31) + this.popup_bg.hashCode();
        }

        public String toString() {
            return "Data(coupon=" + this.coupon + ", popup_bg=" + this.popup_bg + ")";
        }

        public Data(Coupon coupon, String popup_bg) {
            Intrinsics.checkNotNullParameter(coupon, "coupon");
            Intrinsics.checkNotNullParameter(popup_bg, "popup_bg");
            this.coupon = coupon;
            this.popup_bg = popup_bg;
        }

        public final Coupon getCoupon() {
            return this.coupon;
        }

        public final String getPopup_bg() {
            return this.popup_bg;
        }
    }

    /* JADX INFO: compiled from: DailyCouponResult.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DailyCouponResult$Coupon;", "", "amount", "", "coupon_id", "(II)V", "getAmount", "()I", "getCoupon_id", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Coupon {
        public static final int $stable = 0;
        private final int amount;
        private final int coupon_id;

        public static /* synthetic */ Coupon copy$default(Coupon coupon, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = coupon.amount;
            }
            if ((i3 & 2) != 0) {
                i2 = coupon.coupon_id;
            }
            return coupon.copy(i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getAmount() {
            return this.amount;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getCoupon_id() {
            return this.coupon_id;
        }

        public final Coupon copy(int amount, int coupon_id) {
            return new Coupon(amount, coupon_id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Coupon)) {
                return false;
            }
            Coupon coupon = (Coupon) other;
            return this.amount == coupon.amount && this.coupon_id == coupon.coupon_id;
        }

        public int hashCode() {
            return (this.amount * 31) + this.coupon_id;
        }

        public String toString() {
            return "Coupon(amount=" + this.amount + ", coupon_id=" + this.coupon_id + ")";
        }

        public Coupon(int i, int i2) {
            this.amount = i;
            this.coupon_id = i2;
        }

        public final int getAmount() {
            return this.amount;
        }

        public final int getCoupon_id() {
            return this.coupon_id;
        }
    }
}
