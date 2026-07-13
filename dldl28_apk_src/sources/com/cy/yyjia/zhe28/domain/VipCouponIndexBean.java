package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.tencent.open.SocialConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VipCouponIndexBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001!B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\b\u0010\u001d\u001a\u0004\u0018\u00010\nJ\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\""}, d2 = {"Lcom/cy/yyjia/zhe28/domain/VipCouponIndexBean;", "", "coupon", "", SocialConstants.PARAM_APP_DESC, "rule", "user", "Lcom/cy/yyjia/zhe28/domain/UserBean;", "vipList", "", "Lcom/cy/yyjia/zhe28/domain/VipCouponIndexBean$Vip;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cy/yyjia/zhe28/domain/UserBean;Ljava/util/List;)V", "getCoupon", "()Ljava/lang/String;", "getDesc", "getRule", "getUser", "()Lcom/cy/yyjia/zhe28/domain/UserBean;", "getVipList", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "getVip", "hashCode", "", "toString", "Vip", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class VipCouponIndexBean {
    public static final int $stable = 8;
    private final String coupon;
    private final String desc;
    private final String rule;
    private final UserBean user;
    private final List<Vip> vipList;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VipCouponIndexBean copy$default(VipCouponIndexBean vipCouponIndexBean, String str, String str2, String str3, UserBean userBean, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = vipCouponIndexBean.coupon;
        }
        if ((i & 2) != 0) {
            str2 = vipCouponIndexBean.desc;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = vipCouponIndexBean.rule;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            userBean = vipCouponIndexBean.user;
        }
        UserBean userBean2 = userBean;
        if ((i & 16) != 0) {
            list = vipCouponIndexBean.vipList;
        }
        return vipCouponIndexBean.copy(str, str4, str5, userBean2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCoupon() {
        return this.coupon;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRule() {
        return this.rule;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final UserBean getUser() {
        return this.user;
    }

    public final List<Vip> component5() {
        return this.vipList;
    }

    public final VipCouponIndexBean copy(String coupon, String desc, String rule, UserBean user, List<Vip> vipList) {
        Intrinsics.checkNotNullParameter(coupon, "coupon");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(rule, "rule");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(vipList, "vipList");
        return new VipCouponIndexBean(coupon, desc, rule, user, vipList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VipCouponIndexBean)) {
            return false;
        }
        VipCouponIndexBean vipCouponIndexBean = (VipCouponIndexBean) other;
        return Intrinsics.areEqual(this.coupon, vipCouponIndexBean.coupon) && Intrinsics.areEqual(this.desc, vipCouponIndexBean.desc) && Intrinsics.areEqual(this.rule, vipCouponIndexBean.rule) && Intrinsics.areEqual(this.user, vipCouponIndexBean.user) && Intrinsics.areEqual(this.vipList, vipCouponIndexBean.vipList);
    }

    public int hashCode() {
        return (((((((this.coupon.hashCode() * 31) + this.desc.hashCode()) * 31) + this.rule.hashCode()) * 31) + this.user.hashCode()) * 31) + this.vipList.hashCode();
    }

    public String toString() {
        return "VipCouponIndexBean(coupon=" + this.coupon + ", desc=" + this.desc + ", rule=" + this.rule + ", user=" + this.user + ", vipList=" + this.vipList + ")";
    }

    public VipCouponIndexBean(String coupon, String desc, String rule, UserBean user, List<Vip> vipList) {
        Intrinsics.checkNotNullParameter(coupon, "coupon");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(rule, "rule");
        Intrinsics.checkNotNullParameter(user, "user");
        Intrinsics.checkNotNullParameter(vipList, "vipList");
        this.coupon = coupon;
        this.desc = desc;
        this.rule = rule;
        this.user = user;
        this.vipList = vipList;
    }

    public final String getCoupon() {
        return this.coupon;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getRule() {
        return this.rule;
    }

    public final UserBean getUser() {
        return this.user;
    }

    public final List<Vip> getVipList() {
        return this.vipList;
    }

    public final Vip getVip() {
        for (Vip vip : this.vipList) {
            if (vip.getSelected()) {
                return vip;
            }
        }
        return null;
    }

    /* JADX INFO: compiled from: VipCouponIndexBean.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J;\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR&\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u0004\b\u0017\u0010\u0012¨\u0006#"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/VipCouponIndexBean$Vip;", "Landroidx/databinding/BaseObservable;", "growth_alue", "", "id", "", "name", "num", "my", "", "(ILjava/lang/String;Ljava/lang/String;IZ)V", "getGrowth_alue", "()I", "getId", "()Ljava/lang/String;", "getMy", "()Z", "setMy", "(Z)V", "getName", "getNum", "selected", "getSelected", "setSelected", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Vip extends BaseObservable {
        public static final int $stable = 8;
        private final int growth_alue;
        private final String id;
        private boolean my;
        private final String name;
        private final int num;
        private boolean selected;

        public static /* synthetic */ Vip copy$default(Vip vip, int i, String str, String str2, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = vip.growth_alue;
            }
            if ((i3 & 2) != 0) {
                str = vip.id;
            }
            String str3 = str;
            if ((i3 & 4) != 0) {
                str2 = vip.name;
            }
            String str4 = str2;
            if ((i3 & 8) != 0) {
                i2 = vip.num;
            }
            int i4 = i2;
            if ((i3 & 16) != 0) {
                z = vip.my;
            }
            return vip.copy(i, str3, str4, i4, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getGrowth_alue() {
            return this.growth_alue;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getNum() {
            return this.num;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getMy() {
            return this.my;
        }

        public final Vip copy(int growth_alue, String id, String name, int num, boolean my) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            return new Vip(growth_alue, id, name, num, my);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Vip)) {
                return false;
            }
            Vip vip = (Vip) other;
            return this.growth_alue == vip.growth_alue && Intrinsics.areEqual(this.id, vip.id) && Intrinsics.areEqual(this.name, vip.name) && this.num == vip.num && this.my == vip.my;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v6, types: [int] */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r1v8 */
        public int hashCode() {
            int iHashCode = ((((((this.growth_alue * 31) + this.id.hashCode()) * 31) + this.name.hashCode()) * 31) + this.num) * 31;
            boolean z = this.my;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return iHashCode + r1;
        }

        public String toString() {
            return "Vip(growth_alue=" + this.growth_alue + ", id=" + this.id + ", name=" + this.name + ", num=" + this.num + ", my=" + this.my + ")";
        }

        public final int getGrowth_alue() {
            return this.growth_alue;
        }

        public final String getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final int getNum() {
            return this.num;
        }

        public final boolean getMy() {
            return this.my;
        }

        public final void setMy(boolean z) {
            this.my = z;
        }

        public Vip(int i, String id, String name, int i2, boolean z) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            this.growth_alue = i;
            this.id = id;
            this.name = name;
            this.num = i2;
            this.my = z;
        }

        @Bindable
        public final boolean getSelected() {
            return this.selected;
        }

        public final void setSelected(boolean z) {
            this.selected = z;
            notifyPropertyChanged(94);
        }
    }
}
