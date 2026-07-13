package com.cy.yyjia.zhe28.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MonthlyTaskBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/MonthlyTaskBean;", "", "list", "", "Lcom/cy/yyjia/zhe28/domain/TaskBean;", "statMonth", "", "totalMoney", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getList", "()Ljava/util/List;", "getStatMonth", "()Ljava/lang/String;", "getTotalMoney", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class MonthlyTaskBean {
    public static final int $stable = 8;
    private final List<TaskBean> list;
    private final String statMonth;
    private final String totalMoney;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MonthlyTaskBean copy$default(MonthlyTaskBean monthlyTaskBean, List list, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = monthlyTaskBean.list;
        }
        if ((i & 2) != 0) {
            str = monthlyTaskBean.statMonth;
        }
        if ((i & 4) != 0) {
            str2 = monthlyTaskBean.totalMoney;
        }
        return monthlyTaskBean.copy(list, str, str2);
    }

    public final List<TaskBean> component1() {
        return this.list;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getStatMonth() {
        return this.statMonth;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTotalMoney() {
        return this.totalMoney;
    }

    public final MonthlyTaskBean copy(List<TaskBean> list, String statMonth, String totalMoney) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(statMonth, "statMonth");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        return new MonthlyTaskBean(list, statMonth, totalMoney);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MonthlyTaskBean)) {
            return false;
        }
        MonthlyTaskBean monthlyTaskBean = (MonthlyTaskBean) other;
        return Intrinsics.areEqual(this.list, monthlyTaskBean.list) && Intrinsics.areEqual(this.statMonth, monthlyTaskBean.statMonth) && Intrinsics.areEqual(this.totalMoney, monthlyTaskBean.totalMoney);
    }

    public int hashCode() {
        return (((this.list.hashCode() * 31) + this.statMonth.hashCode()) * 31) + this.totalMoney.hashCode();
    }

    public String toString() {
        return "MonthlyTaskBean(list=" + this.list + ", statMonth=" + this.statMonth + ", totalMoney=" + this.totalMoney + ")";
    }

    public MonthlyTaskBean(List<TaskBean> list, String statMonth, String totalMoney) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(statMonth, "statMonth");
        Intrinsics.checkNotNullParameter(totalMoney, "totalMoney");
        this.list = list;
        this.statMonth = statMonth;
        this.totalMoney = totalMoney;
    }

    public final List<TaskBean> getList() {
        return this.list;
    }

    public final String getStatMonth() {
        return this.statMonth;
    }

    public final String getTotalMoney() {
        return this.totalMoney;
    }
}
