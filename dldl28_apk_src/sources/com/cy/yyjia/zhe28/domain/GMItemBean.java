package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.bytedance.framwork.core.sdklib.DBHelper;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GMItemBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002BM\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0002\u0010\u000eJ\t\u0010!\u001a\u00020\u0004HÆ\u0003J\t\u0010\"\u001a\u00020\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J\t\u0010$\u001a\u00020\bHÆ\u0003J\t\u0010%\u001a\u00020\u0004HÆ\u0003J\t\u0010&\u001a\u00020\bHÆ\u0003J\t\u0010'\u001a\u00020\u0004HÆ\u0003J\t\u0010(\u001a\u00020\u0004HÆ\u0003J\t\u0010)\u001a\u00020\u0004HÆ\u0003Jc\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004HÆ\u0001J\u0013\u0010+\u001a\u00020\u001a2\b\u0010,\u001a\u0004\u0018\u00010-HÖ\u0003J\t\u0010.\u001a\u00020\u0004HÖ\u0001J\t\u0010/\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R&\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0010¨\u00060"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GMItemBean;", "Landroidx/databinding/BaseObservable;", "Ljava/io/Serializable;", "api_type", "", "day_quantity", "id", "item_description", "", "item_id", "item_name", "items_type", "max_quantity", "tier_id", "(IIILjava/lang/String;ILjava/lang/String;III)V", "getApi_type", "()I", "getDay_quantity", "getId", "getItem_description", "()Ljava/lang/String;", "getItem_id", "getItem_name", "getItems_type", "getMax_quantity", DBHelper.COL_VALUE, "", "selected", "getSelected", "()Z", "setSelected", "(Z)V", "getTier_id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GMItemBean extends BaseObservable implements Serializable {
    public static final int $stable = 8;
    private final int api_type;
    private final int day_quantity;
    private final int id;
    private final String item_description;
    private final int item_id;
    private final String item_name;
    private final int items_type;
    private final int max_quantity;
    private boolean selected;
    private final int tier_id;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getApi_type() {
        return this.api_type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getDay_quantity() {
        return this.day_quantity;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getItem_description() {
        return this.item_description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getItem_id() {
        return this.item_id;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getItem_name() {
        return this.item_name;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getItems_type() {
        return this.items_type;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getMax_quantity() {
        return this.max_quantity;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getTier_id() {
        return this.tier_id;
    }

    public final GMItemBean copy(int api_type, int day_quantity, int id, String item_description, int item_id, String item_name, int items_type, int max_quantity, int tier_id) {
        Intrinsics.checkNotNullParameter(item_description, "item_description");
        Intrinsics.checkNotNullParameter(item_name, "item_name");
        return new GMItemBean(api_type, day_quantity, id, item_description, item_id, item_name, items_type, max_quantity, tier_id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GMItemBean)) {
            return false;
        }
        GMItemBean gMItemBean = (GMItemBean) other;
        return this.api_type == gMItemBean.api_type && this.day_quantity == gMItemBean.day_quantity && this.id == gMItemBean.id && Intrinsics.areEqual(this.item_description, gMItemBean.item_description) && this.item_id == gMItemBean.item_id && Intrinsics.areEqual(this.item_name, gMItemBean.item_name) && this.items_type == gMItemBean.items_type && this.max_quantity == gMItemBean.max_quantity && this.tier_id == gMItemBean.tier_id;
    }

    public int hashCode() {
        return (((((((((((((((this.api_type * 31) + this.day_quantity) * 31) + this.id) * 31) + this.item_description.hashCode()) * 31) + this.item_id) * 31) + this.item_name.hashCode()) * 31) + this.items_type) * 31) + this.max_quantity) * 31) + this.tier_id;
    }

    public String toString() {
        return "GMItemBean(api_type=" + this.api_type + ", day_quantity=" + this.day_quantity + ", id=" + this.id + ", item_description=" + this.item_description + ", item_id=" + this.item_id + ", item_name=" + this.item_name + ", items_type=" + this.items_type + ", max_quantity=" + this.max_quantity + ", tier_id=" + this.tier_id + ")";
    }

    public final int getApi_type() {
        return this.api_type;
    }

    public final int getDay_quantity() {
        return this.day_quantity;
    }

    public final int getId() {
        return this.id;
    }

    public final String getItem_description() {
        return this.item_description;
    }

    public final int getItem_id() {
        return this.item_id;
    }

    public final String getItem_name() {
        return this.item_name;
    }

    public final int getItems_type() {
        return this.items_type;
    }

    public final int getMax_quantity() {
        return this.max_quantity;
    }

    public final int getTier_id() {
        return this.tier_id;
    }

    public GMItemBean(int i, int i2, int i3, String item_description, int i4, String item_name, int i5, int i6, int i7) {
        Intrinsics.checkNotNullParameter(item_description, "item_description");
        Intrinsics.checkNotNullParameter(item_name, "item_name");
        this.api_type = i;
        this.day_quantity = i2;
        this.id = i3;
        this.item_description = item_description;
        this.item_id = i4;
        this.item_name = item_name;
        this.items_type = i5;
        this.max_quantity = i6;
        this.tier_id = i7;
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
