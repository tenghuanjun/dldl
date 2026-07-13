package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR&\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/FilterBean;", "Landroidx/databinding/BaseObservable;", "name", "", "data", "(Ljava/lang/String;Ljava/lang/String;)V", "sort", "selected", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "getName", "getSelected", "()Z", "setSelected", "(Z)V", "getSort", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class FilterBean extends BaseObservable {
    public static final int $stable = 8;
    private final String data;
    private final String name;
    private boolean selected;
    private final String sort;

    public static /* synthetic */ FilterBean copy$default(FilterBean filterBean, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = filterBean.name;
        }
        if ((i & 2) != 0) {
            str2 = filterBean.data;
        }
        if ((i & 4) != 0) {
            str3 = filterBean.sort;
        }
        return filterBean.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSort() {
        return this.sort;
    }

    public final FilterBean copy(String name, String data, String sort) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(sort, "sort");
        return new FilterBean(name, data, sort);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FilterBean)) {
            return false;
        }
        FilterBean filterBean = (FilterBean) other;
        return Intrinsics.areEqual(this.name, filterBean.name) && Intrinsics.areEqual(this.data, filterBean.data) && Intrinsics.areEqual(this.sort, filterBean.sort);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.data.hashCode()) * 31) + this.sort.hashCode();
    }

    public String toString() {
        return "FilterBean(name=" + this.name + ", data=" + this.data + ", sort=" + this.sort + ")";
    }

    public final String getName() {
        return this.name;
    }

    public final String getData() {
        return this.data;
    }

    public final String getSort() {
        return this.sort;
    }

    public FilterBean(String name, String data, String sort) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(sort, "sort");
        this.name = name;
        this.data = data;
        this.sort = sort;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilterBean(String name, String data) {
        this(name, data, "");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FilterBean(String name, String data, String sort, boolean z) {
        this(name, data, sort);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(sort, "sort");
        setSelected(z);
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
