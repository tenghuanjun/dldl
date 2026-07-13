package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.tencent.open.SocialConstants;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: YunPrice.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\u000e\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0005J\u0006\u0010\u001f\u001a\u00020\u0003J\t\u0010 \u001a\u00020\u0005HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/cy/yyjia/zhe28/domain/YunPrice;", "Landroidx/databinding/BaseObservable;", SocialConstants.PARAM_APP_DESC, "", "id", "", "name", "price", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getId", "()I", "getName", "getPrice", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "getPayAmount", "num", "getPriceStr", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class YunPrice extends BaseObservable {
    public static final int $stable = 8;
    private final String desc;
    private final int id;
    private final String name;
    private final String price;
    private boolean selected;

    public static /* synthetic */ YunPrice copy$default(YunPrice yunPrice, String str, int i, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = yunPrice.desc;
        }
        if ((i2 & 2) != 0) {
            i = yunPrice.id;
        }
        if ((i2 & 4) != 0) {
            str2 = yunPrice.name;
        }
        if ((i2 & 8) != 0) {
            str3 = yunPrice.price;
        }
        return yunPrice.copy(str, i, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    public final YunPrice copy(String desc, int id, String name, String price) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(price, "price");
        return new YunPrice(desc, id, name, price);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof YunPrice)) {
            return false;
        }
        YunPrice yunPrice = (YunPrice) other;
        return Intrinsics.areEqual(this.desc, yunPrice.desc) && this.id == yunPrice.id && Intrinsics.areEqual(this.name, yunPrice.name) && Intrinsics.areEqual(this.price, yunPrice.price);
    }

    public int hashCode() {
        return (((((this.desc.hashCode() * 31) + this.id) * 31) + this.name.hashCode()) * 31) + this.price.hashCode();
    }

    public String toString() {
        return "YunPrice(desc=" + this.desc + ", id=" + this.id + ", name=" + this.name + ", price=" + this.price + ")";
    }

    public final String getDesc() {
        return this.desc;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPrice() {
        return this.price;
    }

    public YunPrice(String desc, int i, String name, String price) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(price, "price");
        this.desc = desc;
        this.id = i;
        this.name = name;
        this.price = price;
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }

    public final String getPriceStr() {
        return String.valueOf(GMTitleBean$$ExternalSyntheticBackport0.m(new BigDecimal(this.price)));
    }

    public final String getPayAmount(int num) {
        return String.valueOf(new BigDecimal(this.price).multiply(new BigDecimal(num)));
    }
}
