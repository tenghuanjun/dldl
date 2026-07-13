package com.cy.yyjia.zhe28.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ItemTradePayTypeBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0011B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ItemTradePayTypeBean;", "", "payList", "", "Lcom/cy/yyjia/zhe28/domain/ItemTradePayTypeBean$Pay;", "(Ljava/util/List;)V", "getPayList", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Pay", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ItemTradePayTypeBean {
    public static final int $stable = 8;
    private final List<Pay> payList;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ItemTradePayTypeBean copy$default(ItemTradePayTypeBean itemTradePayTypeBean, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = itemTradePayTypeBean.payList;
        }
        return itemTradePayTypeBean.copy(list);
    }

    public final List<Pay> component1() {
        return this.payList;
    }

    public final ItemTradePayTypeBean copy(List<Pay> payList) {
        Intrinsics.checkNotNullParameter(payList, "payList");
        return new ItemTradePayTypeBean(payList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ItemTradePayTypeBean) && Intrinsics.areEqual(this.payList, ((ItemTradePayTypeBean) other).payList);
    }

    public int hashCode() {
        return this.payList.hashCode();
    }

    public String toString() {
        return "ItemTradePayTypeBean(payList=" + this.payList + ")";
    }

    public ItemTradePayTypeBean(List<Pay> payList) {
        Intrinsics.checkNotNullParameter(payList, "payList");
        this.payList = payList;
    }

    public final List<Pay> getPayList() {
        return this.payList;
    }

    /* JADX INFO: compiled from: ItemTradePayTypeBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ItemTradePayTypeBean$Pay;", "", "icon", "", "name", "payType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getIcon", "()Ljava/lang/String;", "getName", "getPayType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Pay {
        public static final int $stable = 0;
        private final String icon;
        private final String name;
        private final String payType;

        public static /* synthetic */ Pay copy$default(Pay pay, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = pay.icon;
            }
            if ((i & 2) != 0) {
                str2 = pay.name;
            }
            if ((i & 4) != 0) {
                str3 = pay.payType;
            }
            return pay.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getIcon() {
            return this.icon;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPayType() {
            return this.payType;
        }

        public final Pay copy(String icon, String name, String payType) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(payType, "payType");
            return new Pay(icon, name, payType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Pay)) {
                return false;
            }
            Pay pay = (Pay) other;
            return Intrinsics.areEqual(this.icon, pay.icon) && Intrinsics.areEqual(this.name, pay.name) && Intrinsics.areEqual(this.payType, pay.payType);
        }

        public int hashCode() {
            return (((this.icon.hashCode() * 31) + this.name.hashCode()) * 31) + this.payType.hashCode();
        }

        public String toString() {
            return "Pay(icon=" + this.icon + ", name=" + this.name + ", payType=" + this.payType + ")";
        }

        public Pay(String icon, String name, String payType) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(payType, "payType");
            this.icon = icon;
            this.name = name;
            this.payType = payType;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPayType() {
            return this.payType;
        }
    }
}
