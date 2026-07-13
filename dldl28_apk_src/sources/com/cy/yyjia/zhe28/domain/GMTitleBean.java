package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.bytedance.framwork.core.sdklib.DBHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GMTitleBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010 \u001a\u00020\u0004HÆ\u0003J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J1\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010%\u001a\u00020\u00192\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\b\u0010(\u001a\u0004\u0018\u00010\u0011J\t\u0010)\u001a\u00020\tHÖ\u0001J\t\u0010*\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R&\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00198G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000e¨\u0006+"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GMTitleBean;", "Landroidx/databinding/BaseObservable;", "Ljava/io/Serializable;", "id", "", "tier_name", "price", "", "has_access", "", "(Ljava/lang/String;Ljava/lang/String;DI)V", "getHas_access", "()I", "getId", "()Ljava/lang/String;", "items", "", "Lcom/cy/yyjia/zhe28/domain/GMItemBean;", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "getPrice", "()D", DBHelper.COL_VALUE, "", "selected", "getSelected", "()Z", "setSelected", "(Z)V", "getTier_name", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "getItem", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GMTitleBean extends BaseObservable implements Serializable {
    public static final int $stable = 8;
    private final int has_access;
    private final String id;
    private List<GMItemBean> items;
    private final double price;
    private boolean selected;
    private final String tier_name;

    public static /* synthetic */ GMTitleBean copy$default(GMTitleBean gMTitleBean, String str, String str2, double d, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gMTitleBean.id;
        }
        if ((i2 & 2) != 0) {
            str2 = gMTitleBean.tier_name;
        }
        String str3 = str2;
        if ((i2 & 4) != 0) {
            d = gMTitleBean.price;
        }
        double d2 = d;
        if ((i2 & 8) != 0) {
            i = gMTitleBean.has_access;
        }
        return gMTitleBean.copy(str, str3, d2, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTier_name() {
        return this.tier_name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final double getPrice() {
        return this.price;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getHas_access() {
        return this.has_access;
    }

    public final GMTitleBean copy(String id, String tier_name, double price, int has_access) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(tier_name, "tier_name");
        return new GMTitleBean(id, tier_name, price, has_access);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GMTitleBean)) {
            return false;
        }
        GMTitleBean gMTitleBean = (GMTitleBean) other;
        return Intrinsics.areEqual(this.id, gMTitleBean.id) && Intrinsics.areEqual(this.tier_name, gMTitleBean.tier_name) && Double.compare(this.price, gMTitleBean.price) == 0 && this.has_access == gMTitleBean.has_access;
    }

    public int hashCode() {
        return (((((this.id.hashCode() * 31) + this.tier_name.hashCode()) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.price)) * 31) + this.has_access;
    }

    public String toString() {
        return "GMTitleBean(id=" + this.id + ", tier_name=" + this.tier_name + ", price=" + this.price + ", has_access=" + this.has_access + ")";
    }

    public final String getId() {
        return this.id;
    }

    public final String getTier_name() {
        return this.tier_name;
    }

    public final double getPrice() {
        return this.price;
    }

    public final int getHas_access() {
        return this.has_access;
    }

    public GMTitleBean(String id, String tier_name, double d, int i) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(tier_name, "tier_name");
        this.id = id;
        this.tier_name = tier_name;
        this.price = d;
        this.has_access = i;
        this.items = new ArrayList();
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }

    public final List<GMItemBean> getItems() {
        return this.items;
    }

    public final void setItems(List<GMItemBean> list) {
        this.items = list;
    }

    public final GMItemBean getItem() {
        List<GMItemBean> list = this.items;
        Intrinsics.checkNotNull(list);
        for (GMItemBean gMItemBean : list) {
            if (gMItemBean.getSelected()) {
                return gMItemBean;
            }
        }
        return null;
    }
}
