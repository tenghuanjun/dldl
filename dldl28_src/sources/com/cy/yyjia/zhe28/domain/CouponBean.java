package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.lzy.okgo.model.Progress;
import com.tencent.open.SocialConstants;
import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CouponBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\bL\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001BÓ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\n\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0005¢\u0006\u0002\u0010\u001fJ\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0010HÆ\u0003J\t\u0010C\u001a\u00020\u0010HÆ\u0003J\t\u0010D\u001a\u00020\nHÆ\u0003J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0005HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010L\u001a\u00020\u0005HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\t\u0010P\u001a\u00020\u0003HÆ\u0003J\t\u0010Q\u001a\u00020\u0005HÆ\u0003J\t\u0010R\u001a\u00020\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0005HÆ\u0003J\t\u0010T\u001a\u00020\u0005HÆ\u0003J\t\u0010U\u001a\u00020\nHÆ\u0003J\u0010\u0010V\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010+J\u000b\u0010W\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010X\u001a\u00020\u0005HÆ\u0003J\u008e\u0002\u0010Y\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010ZJ\u0013\u0010[\u001a\u00020\u00102\b\u0010\\\u001a\u0004\u0018\u00010]HÖ\u0003J\u0006\u0010^\u001a\u00020\u0005J\u000e\u0010_\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0003J\u0006\u0010`\u001a\u00020\u0005J\u0006\u0010a\u001a\u00020\u0005J\t\u0010b\u001a\u00020\u0003HÖ\u0001J\t\u0010c\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0011\u0010\u001c\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010!R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010!R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010!R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010!R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010$R\u0011\u0010\u0011\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u00102R\u0011\u0010\u001d\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010$R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u00102R\u0011\u0010\u001a\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010$R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010!R&\u00105\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00108G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00102\"\u0004\b7\u00108R\u0011\u0010\u0012\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b9\u0010'R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010!R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010!R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010!R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010!R\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b>\u0010$R\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010!¨\u0006d"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/CouponBean;", "Landroidx/databinding/BaseObservable;", "id", "", "account_name", "", "amount", SocialConstants.PARAM_APP_DESC, "endTime", "end", "", CommonConstants.key_gameId, "game_name", "game_sub_name", "gettime", "isUse", "", "isGet", "start", "useCondition", "start_text", "end_text", "title", "getStatus", Progress.TAG, "name", "leftPercent", "type", "couponId", "isReceive", "times", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;)V", "getAccount_name", "()Ljava/lang/String;", "getAmount", "getCouponId", "()I", "getDesc", "getEnd", "()J", "getEndTime", "getEnd_text", "getGameId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getGame_name", "getGame_sub_name", "getGetStatus", "getGettime", "getId", "()Z", "getLeftPercent", "getName", "selected", "getSelected", "setSelected", "(Z)V", "getStart", "getStart_text", "getTag", "getTimes", "getTitle", "getType", "getUseCondition", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;)Lcom/cy/yyjia/zhe28/domain/CouponBean;", "equals", "other", "", "getAccountLimitStr", "getBtnText", "getConditionStr", "getGameLimitStr", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class CouponBean extends BaseObservable {
    public static final int $stable = 8;
    private final String account_name;
    private final String amount;
    private final int couponId;
    private final String desc;
    private final long end;
    private final String endTime;
    private final String end_text;
    private final Integer gameId;
    private final String game_name;
    private final String game_sub_name;
    private final String getStatus;
    private final String gettime;
    private final int id;
    private final boolean isGet;
    private final int isReceive;
    private final boolean isUse;
    private final int leftPercent;
    private final String name;
    private boolean selected;
    private final long start;
    private final String start_text;
    private final String tag;
    private final String times;
    private final String title;
    private final int type;
    private final String useCondition;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getGettime() {
        return this.gettime;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getIsUse() {
        return this.isUse;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getIsGet() {
        return this.isGet;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getStart() {
        return this.start;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getUseCondition() {
        return this.useCondition;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getStart_text() {
        return this.start_text;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getEnd_text() {
        return this.end_text;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getGetStatus() {
        return this.getStatus;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getTag() {
        return this.tag;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccount_name() {
        return this.account_name;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final int getLeftPercent() {
        return this.leftPercent;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final int getCouponId() {
        return this.couponId;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final int getIsReceive() {
        return this.isReceive;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getTimes() {
        return this.times;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAmount() {
        return this.amount;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getEndTime() {
        return this.endTime;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getEnd() {
        return this.end;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getGame_name() {
        return this.game_name;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getGame_sub_name() {
        return this.game_sub_name;
    }

    public final CouponBean copy(int id, String account_name, String amount, String desc, String endTime, long end, Integer gameId, String game_name, String game_sub_name, String gettime, boolean isUse, boolean isGet, long start, String useCondition, String start_text, String end_text, String title, String getStatus, String tag, String name, int leftPercent, int type, int couponId, int isReceive, String times) {
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(game_sub_name, "game_sub_name");
        Intrinsics.checkNotNullParameter(gettime, "gettime");
        Intrinsics.checkNotNullParameter(useCondition, "useCondition");
        Intrinsics.checkNotNullParameter(start_text, "start_text");
        Intrinsics.checkNotNullParameter(end_text, "end_text");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(getStatus, "getStatus");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(times, "times");
        return new CouponBean(id, account_name, amount, desc, endTime, end, gameId, game_name, game_sub_name, gettime, isUse, isGet, start, useCondition, start_text, end_text, title, getStatus, tag, name, leftPercent, type, couponId, isReceive, times);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CouponBean)) {
            return false;
        }
        CouponBean couponBean = (CouponBean) other;
        return this.id == couponBean.id && Intrinsics.areEqual(this.account_name, couponBean.account_name) && Intrinsics.areEqual(this.amount, couponBean.amount) && Intrinsics.areEqual(this.desc, couponBean.desc) && Intrinsics.areEqual(this.endTime, couponBean.endTime) && this.end == couponBean.end && Intrinsics.areEqual(this.gameId, couponBean.gameId) && Intrinsics.areEqual(this.game_name, couponBean.game_name) && Intrinsics.areEqual(this.game_sub_name, couponBean.game_sub_name) && Intrinsics.areEqual(this.gettime, couponBean.gettime) && this.isUse == couponBean.isUse && this.isGet == couponBean.isGet && this.start == couponBean.start && Intrinsics.areEqual(this.useCondition, couponBean.useCondition) && Intrinsics.areEqual(this.start_text, couponBean.start_text) && Intrinsics.areEqual(this.end_text, couponBean.end_text) && Intrinsics.areEqual(this.title, couponBean.title) && Intrinsics.areEqual(this.getStatus, couponBean.getStatus) && Intrinsics.areEqual(this.tag, couponBean.tag) && Intrinsics.areEqual(this.name, couponBean.name) && this.leftPercent == couponBean.leftPercent && this.type == couponBean.type && this.couponId == couponBean.couponId && this.isReceive == couponBean.isReceive && Intrinsics.areEqual(this.times, couponBean.times);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v19, types: [int] */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v46 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4 */
    public int hashCode() {
        int i = this.id * 31;
        String str = this.account_name;
        int iHashCode = (((((((((i + (str == null ? 0 : str.hashCode())) * 31) + this.amount.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.endTime.hashCode()) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.end)) * 31;
        Integer num = this.gameId;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.game_name;
        int iHashCode3 = (((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.game_sub_name.hashCode()) * 31) + this.gettime.hashCode()) * 31;
        boolean z = this.isUse;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i2 = (iHashCode3 + r1) * 31;
        boolean z2 = this.isGet;
        return ((((((((((((((((((((((((((i2 + (z2 ? 1 : z2)) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.start)) * 31) + this.useCondition.hashCode()) * 31) + this.start_text.hashCode()) * 31) + this.end_text.hashCode()) * 31) + this.title.hashCode()) * 31) + this.getStatus.hashCode()) * 31) + this.tag.hashCode()) * 31) + this.name.hashCode()) * 31) + this.leftPercent) * 31) + this.type) * 31) + this.couponId) * 31) + this.isReceive) * 31) + this.times.hashCode();
    }

    public String toString() {
        return "CouponBean(id=" + this.id + ", account_name=" + this.account_name + ", amount=" + this.amount + ", desc=" + this.desc + ", endTime=" + this.endTime + ", end=" + this.end + ", gameId=" + this.gameId + ", game_name=" + this.game_name + ", game_sub_name=" + this.game_sub_name + ", gettime=" + this.gettime + ", isUse=" + this.isUse + ", isGet=" + this.isGet + ", start=" + this.start + ", useCondition=" + this.useCondition + ", start_text=" + this.start_text + ", end_text=" + this.end_text + ", title=" + this.title + ", getStatus=" + this.getStatus + ", tag=" + this.tag + ", name=" + this.name + ", leftPercent=" + this.leftPercent + ", type=" + this.type + ", couponId=" + this.couponId + ", isReceive=" + this.isReceive + ", times=" + this.times + ")";
    }

    public final int getId() {
        return this.id;
    }

    public final String getAccount_name() {
        return this.account_name;
    }

    public final String getAmount() {
        return this.amount;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getEndTime() {
        return this.endTime;
    }

    public final long getEnd() {
        return this.end;
    }

    public final Integer getGameId() {
        return this.gameId;
    }

    public final String getGame_name() {
        return this.game_name;
    }

    public final String getGame_sub_name() {
        return this.game_sub_name;
    }

    public final String getGettime() {
        return this.gettime;
    }

    public final boolean isUse() {
        return this.isUse;
    }

    public final boolean isGet() {
        return this.isGet;
    }

    public final long getStart() {
        return this.start;
    }

    public final String getUseCondition() {
        return this.useCondition;
    }

    public final String getStart_text() {
        return this.start_text;
    }

    public final String getEnd_text() {
        return this.end_text;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getGetStatus() {
        return this.getStatus;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getName() {
        return this.name;
    }

    public final int getLeftPercent() {
        return this.leftPercent;
    }

    public final int getType() {
        return this.type;
    }

    public final int getCouponId() {
        return this.couponId;
    }

    public final int isReceive() {
        return this.isReceive;
    }

    public final String getTimes() {
        return this.times;
    }

    public CouponBean(int i, String str, String amount, String desc, String endTime, long j, Integer num, String str2, String game_sub_name, String gettime, boolean z, boolean z2, long j2, String useCondition, String start_text, String end_text, String title, String getStatus, String tag, String name, int i2, int i3, int i4, int i5, String times) {
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        Intrinsics.checkNotNullParameter(game_sub_name, "game_sub_name");
        Intrinsics.checkNotNullParameter(gettime, "gettime");
        Intrinsics.checkNotNullParameter(useCondition, "useCondition");
        Intrinsics.checkNotNullParameter(start_text, "start_text");
        Intrinsics.checkNotNullParameter(end_text, "end_text");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(getStatus, "getStatus");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(times, "times");
        this.id = i;
        this.account_name = str;
        this.amount = amount;
        this.desc = desc;
        this.endTime = endTime;
        this.end = j;
        this.gameId = num;
        this.game_name = str2;
        this.game_sub_name = game_sub_name;
        this.gettime = gettime;
        this.isUse = z;
        this.isGet = z2;
        this.start = j2;
        this.useCondition = useCondition;
        this.start_text = start_text;
        this.end_text = end_text;
        this.title = title;
        this.getStatus = getStatus;
        this.tag = tag;
        this.name = name;
        this.leftPercent = i2;
        this.type = i3;
        this.couponId = i4;
        this.isReceive = i5;
        this.times = times;
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }

    public final String getBtnText(int type) {
        if (type == 1) {
            return "已使用";
        }
        if (type == 2) {
            return "已过期";
        }
        return "立即使用";
    }

    public final String getConditionStr() {
        if (Intrinsics.areEqual(this.useCondition, "0")) {
            return "无门槛";
        }
        return "满" + this.useCondition + "可用";
    }

    public final String getGameLimitStr() {
        return "仅限【" + this.game_name + "】使用";
    }

    public final String getAccountLimitStr() {
        return "仅限小号：" + this.account_name + "可用";
    }
}
