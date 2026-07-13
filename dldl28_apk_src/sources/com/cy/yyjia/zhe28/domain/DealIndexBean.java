package com.cy.yyjia.zhe28.domain;

import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DealIndexBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u001dB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003JA\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DealIndexBean;", "", "freeze_money", "", "isClose", "tradeMoney", "usable_money", "list", "", "Lcom/cy/yyjia/zhe28/domain/DealIndexBean$FunBean;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getFreeze_money", "()Ljava/lang/String;", "getList", "()Ljava/util/List;", "getTradeMoney", "getUsable_money", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "FunBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class DealIndexBean {
    public static final int $stable = 8;
    private final String freeze_money;
    private final String isClose;
    private final List<FunBean> list;
    private final String tradeMoney;
    private final String usable_money;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DealIndexBean copy$default(DealIndexBean dealIndexBean, String str, String str2, String str3, String str4, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dealIndexBean.freeze_money;
        }
        if ((i & 2) != 0) {
            str2 = dealIndexBean.isClose;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = dealIndexBean.tradeMoney;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = dealIndexBean.usable_money;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            list = dealIndexBean.list;
        }
        return dealIndexBean.copy(str, str5, str6, str7, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFreeze_money() {
        return this.freeze_money;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getIsClose() {
        return this.isClose;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTradeMoney() {
        return this.tradeMoney;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUsable_money() {
        return this.usable_money;
    }

    public final List<FunBean> component5() {
        return this.list;
    }

    public final DealIndexBean copy(String freeze_money, String isClose, String tradeMoney, String usable_money, List<FunBean> list) {
        Intrinsics.checkNotNullParameter(freeze_money, "freeze_money");
        Intrinsics.checkNotNullParameter(isClose, "isClose");
        Intrinsics.checkNotNullParameter(tradeMoney, "tradeMoney");
        Intrinsics.checkNotNullParameter(usable_money, "usable_money");
        Intrinsics.checkNotNullParameter(list, "list");
        return new DealIndexBean(freeze_money, isClose, tradeMoney, usable_money, list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DealIndexBean)) {
            return false;
        }
        DealIndexBean dealIndexBean = (DealIndexBean) other;
        return Intrinsics.areEqual(this.freeze_money, dealIndexBean.freeze_money) && Intrinsics.areEqual(this.isClose, dealIndexBean.isClose) && Intrinsics.areEqual(this.tradeMoney, dealIndexBean.tradeMoney) && Intrinsics.areEqual(this.usable_money, dealIndexBean.usable_money) && Intrinsics.areEqual(this.list, dealIndexBean.list);
    }

    public int hashCode() {
        return (((((((this.freeze_money.hashCode() * 31) + this.isClose.hashCode()) * 31) + this.tradeMoney.hashCode()) * 31) + this.usable_money.hashCode()) * 31) + this.list.hashCode();
    }

    public String toString() {
        return "DealIndexBean(freeze_money=" + this.freeze_money + ", isClose=" + this.isClose + ", tradeMoney=" + this.tradeMoney + ", usable_money=" + this.usable_money + ", list=" + this.list + ")";
    }

    public DealIndexBean(String freeze_money, String isClose, String tradeMoney, String usable_money, List<FunBean> list) {
        Intrinsics.checkNotNullParameter(freeze_money, "freeze_money");
        Intrinsics.checkNotNullParameter(isClose, "isClose");
        Intrinsics.checkNotNullParameter(tradeMoney, "tradeMoney");
        Intrinsics.checkNotNullParameter(usable_money, "usable_money");
        Intrinsics.checkNotNullParameter(list, "list");
        this.freeze_money = freeze_money;
        this.isClose = isClose;
        this.tradeMoney = tradeMoney;
        this.usable_money = usable_money;
        this.list = list;
    }

    public final String getFreeze_money() {
        return this.freeze_money;
    }

    public final String isClose() {
        return this.isClose;
    }

    public final String getTradeMoney() {
        return this.tradeMoney;
    }

    public final String getUsable_money() {
        return this.usable_money;
    }

    public final List<FunBean> getList() {
        return this.list;
    }

    /* JADX INFO: compiled from: DealIndexBean.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/DealIndexBean$FunBean;", "", "icon", "", "name", SocialConstants.PARAM_APP_DESC, "type", "link", "Lcom/cy/yyjia/zhe28/domain/BtnBean;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/BtnBean;)V", "getDesc", "()Ljava/lang/String;", "getIcon", "getLink", "()Lcom/cy/yyjia/zhe28/domain/BtnBean;", "getName", "getType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FunBean {
        public static final int $stable = 0;
        private final String desc;
        private final String icon;
        private final BtnBean link;
        private final String name;
        private final String type;

        public static /* synthetic */ FunBean copy$default(FunBean funBean, String str, String str2, String str3, String str4, BtnBean btnBean, int i, Object obj) {
            if ((i & 1) != 0) {
                str = funBean.icon;
            }
            if ((i & 2) != 0) {
                str2 = funBean.name;
            }
            String str5 = str2;
            if ((i & 4) != 0) {
                str3 = funBean.desc;
            }
            String str6 = str3;
            if ((i & 8) != 0) {
                str4 = funBean.type;
            }
            String str7 = str4;
            if ((i & 16) != 0) {
                btnBean = funBean.link;
            }
            return funBean.copy(str, str5, str6, str7, btnBean);
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
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final BtnBean getLink() {
            return this.link;
        }

        public final FunBean copy(String icon, String name, String desc, String type, BtnBean link) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(link, "link");
            return new FunBean(icon, name, desc, type, link);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FunBean)) {
                return false;
            }
            FunBean funBean = (FunBean) other;
            return Intrinsics.areEqual(this.icon, funBean.icon) && Intrinsics.areEqual(this.name, funBean.name) && Intrinsics.areEqual(this.desc, funBean.desc) && Intrinsics.areEqual(this.type, funBean.type) && Intrinsics.areEqual(this.link, funBean.link);
        }

        public int hashCode() {
            return (((((((this.icon.hashCode() * 31) + this.name.hashCode()) * 31) + this.desc.hashCode()) * 31) + this.type.hashCode()) * 31) + this.link.hashCode();
        }

        public String toString() {
            return "FunBean(icon=" + this.icon + ", name=" + this.name + ", desc=" + this.desc + ", type=" + this.type + ", link=" + this.link + ")";
        }

        public FunBean(String icon, String name, String desc, String type, BtnBean link) {
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(link, "link");
            this.icon = icon;
            this.name = name;
            this.desc = desc;
            this.type = type;
            this.link = link;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final String getName() {
            return this.name;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getType() {
            return this.type;
        }

        public final BtnBean getLink() {
            return this.link;
        }
    }
}
