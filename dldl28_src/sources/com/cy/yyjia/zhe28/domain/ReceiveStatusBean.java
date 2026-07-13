package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReceiveStatusBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR&\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00058G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ReceiveStatusBean;", "Landroidx/databinding/BaseObservable;", "id", "", "isCollected", "", "name", "", "(IZLjava/lang/String;)V", "getId", "()I", "()Z", "getName", "()Ljava/lang/String;", "selected", "getSelected", "setSelected", "(Z)V", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ReceiveStatusBean extends BaseObservable {
    public static final int $stable = 8;
    private final int id;
    private final boolean isCollected;
    private final String name;
    private boolean selected;

    public static /* synthetic */ ReceiveStatusBean copy$default(ReceiveStatusBean receiveStatusBean, int i, boolean z, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = receiveStatusBean.id;
        }
        if ((i2 & 2) != 0) {
            z = receiveStatusBean.isCollected;
        }
        if ((i2 & 4) != 0) {
            str = receiveStatusBean.name;
        }
        return receiveStatusBean.copy(i, z, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsCollected() {
        return this.isCollected;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final ReceiveStatusBean copy(int id, boolean isCollected, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new ReceiveStatusBean(id, isCollected, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReceiveStatusBean)) {
            return false;
        }
        ReceiveStatusBean receiveStatusBean = (ReceiveStatusBean) other;
        return this.id == receiveStatusBean.id && this.isCollected == receiveStatusBean.isCollected && Intrinsics.areEqual(this.name, receiveStatusBean.name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public int hashCode() {
        int i = this.id * 31;
        boolean z = this.isCollected;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return ((i + r1) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "ReceiveStatusBean(id=" + this.id + ", isCollected=" + this.isCollected + ", name=" + this.name + ")";
    }

    public final int getId() {
        return this.id;
    }

    public final boolean isCollected() {
        return this.isCollected;
    }

    public final String getName() {
        return this.name;
    }

    public ReceiveStatusBean(int i, boolean z, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = i;
        this.isCollected = z;
        this.name = name;
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
