package com.cy.yyjia.zhe28.domain;

import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GameCouponBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006¢\u0006\u0002\u0010\rR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0011R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameCouponBean;", "", "current_page", "", "last_page", "list", "", "Lcom/cy/yyjia/zhe28/domain/CouponBean;", "total_num", "isShowMonth", "isShowSaving", "cardList", "Lcom/cy/yyjia/zhe28/domain/TabBean;", "(IILjava/util/List;IIILjava/util/List;)V", "getCardList", "()Ljava/util/List;", "getCurrent_page", "()I", "getLast_page", "getList", "getTotal_num", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameCouponBean {
    public static final int $stable = 8;
    private final List<TabBean> cardList;

    @SerializedName(alternate = {"current"}, value = "current_page")
    private final int current_page;
    private final int isShowMonth;
    private final int isShowSaving;

    @SerializedName(alternate = {"pageCount"}, value = "last_page")
    private final int last_page;

    @SerializedName(alternate = {"data"}, value = "list")
    private final List<CouponBean> list;

    @SerializedName(alternate = {MetricsSQLiteCacheKt.METRICS_COUNT, "total"}, value = "total_num")
    private final int total_num;

    public GameCouponBean(int i, int i2, List<CouponBean> list, int i3, int i4, int i5, List<TabBean> cardList) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(cardList, "cardList");
        this.current_page = i;
        this.last_page = i2;
        this.list = list;
        this.total_num = i3;
        this.isShowMonth = i4;
        this.isShowSaving = i5;
        this.cardList = cardList;
    }

    public final int getCurrent_page() {
        return this.current_page;
    }

    public final int getLast_page() {
        return this.last_page;
    }

    public final List<CouponBean> getList() {
        return this.list;
    }

    public final int getTotal_num() {
        return this.total_num;
    }

    /* JADX INFO: renamed from: isShowMonth, reason: from getter */
    public final int getIsShowMonth() {
        return this.isShowMonth;
    }

    /* JADX INFO: renamed from: isShowSaving, reason: from getter */
    public final int getIsShowSaving() {
        return this.isShowSaving;
    }

    public final List<TabBean> getCardList() {
        return this.cardList;
    }
}
