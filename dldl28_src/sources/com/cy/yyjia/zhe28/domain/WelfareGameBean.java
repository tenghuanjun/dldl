package com.cy.yyjia.zhe28.domain;

import androidx.databinding.Bindable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WelfareGameBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR&\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WelfareGameBean;", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "gift", "", "Lcom/cy/yyjia/zhe28/domain/TypeBean;", "news", "coupon_num", "", "(Ljava/util/List;Ljava/util/List;I)V", "getCoupon_num", "()I", "getGift", "()Ljava/util/List;", "getNews", "selected", "Lcom/cy/yyjia/zhe28/domain/RoleBean;", "userRole", "getUserRole", "()Lcom/cy/yyjia/zhe28/domain/RoleBean;", "setUserRole", "(Lcom/cy/yyjia/zhe28/domain/RoleBean;)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WelfareGameBean extends GameBean {
    public static final int $stable = 8;
    private final int coupon_num;
    private final List<TypeBean> gift;
    private final List<TypeBean> news;
    private RoleBean userRole;

    public final List<TypeBean> getGift() {
        return this.gift;
    }

    public final List<TypeBean> getNews() {
        return this.news;
    }

    public final int getCoupon_num() {
        return this.coupon_num;
    }

    public WelfareGameBean(List<TypeBean> gift, List<TypeBean> news, int i) {
        Intrinsics.checkNotNullParameter(gift, "gift");
        Intrinsics.checkNotNullParameter(news, "news");
        this.gift = gift;
        this.news = news;
        this.coupon_num = i;
        this.userRole = new RoleBean(0, "", "", "");
    }

    @Bindable
    public final RoleBean getUserRole() {
        return this.userRole;
    }

    public final void setUserRole(RoleBean selected) {
        Intrinsics.checkNotNullParameter(selected, "selected");
        this.userRole = selected;
        notifyPropertyChanged(120);
    }
}
