package com.cy.yyjia.zhe28.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReportBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001:\u0001\nB!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ReportBean;", "", "pay", "", "Lcom/cy/yyjia/zhe28/domain/ReportBean$ListBean;", "user", "(Ljava/util/List;Ljava/util/List;)V", "getPay", "()Ljava/util/List;", "getUser", "ListBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ReportBean {
    public static final int $stable = 8;
    private final List<ListBean> pay;
    private final List<ListBean> user;

    public ReportBean(List<ListBean> pay, List<ListBean> user) {
        Intrinsics.checkNotNullParameter(pay, "pay");
        Intrinsics.checkNotNullParameter(user, "user");
        this.pay = pay;
        this.user = user;
    }

    public final List<ListBean> getPay() {
        return this.pay;
    }

    public final List<ListBean> getUser() {
        return this.user;
    }

    /* JADX INFO: compiled from: ReportBean.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ReportBean$ListBean;", "", "uid", "", "money", "", "orderId", "(Ljava/lang/String;ILjava/lang/String;)V", "getMoney", "()I", "getOrderId", "()Ljava/lang/String;", "getUid", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ListBean {
        public static final int $stable = 0;
        private final int money;
        private final String orderId;
        private final String uid;

        public ListBean(String uid, int i, String str) {
            Intrinsics.checkNotNullParameter(uid, "uid");
            this.uid = uid;
            this.money = i;
            this.orderId = str;
        }

        public final String getUid() {
            return this.uid;
        }

        public final int getMoney() {
            return this.money;
        }

        public final String getOrderId() {
            return this.orderId;
        }
    }
}
