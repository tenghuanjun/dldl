package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceTypeBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR6\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00138G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006 "}, d2 = {"Lcom/cy/yyjia/zhe28/domain/ServiceTypeBean;", "Landroidx/databinding/BaseObservable;", "id", "", "name", "", "(ILjava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "problem", "", "Lcom/cy/yyjia/zhe28/domain/ProblemBean;", "getProblem", "()Ljava/util/List;", "setProblem", "(Ljava/util/List;)V", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "component1", "component2", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ServiceTypeBean extends BaseObservable {
    public static final int $stable = 8;
    private final int id;
    private final String name;
    private List<ProblemBean> problem;
    private boolean selected;

    public static /* synthetic */ ServiceTypeBean copy$default(ServiceTypeBean serviceTypeBean, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = serviceTypeBean.id;
        }
        if ((i2 & 2) != 0) {
            str = serviceTypeBean.name;
        }
        return serviceTypeBean.copy(i, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final ServiceTypeBean copy(int id, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new ServiceTypeBean(id, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceTypeBean)) {
            return false;
        }
        ServiceTypeBean serviceTypeBean = (ServiceTypeBean) other;
        return this.id == serviceTypeBean.id && Intrinsics.areEqual(this.name, serviceTypeBean.name);
    }

    public int hashCode() {
        return (this.id * 31) + this.name.hashCode();
    }

    public String toString() {
        return "ServiceTypeBean(id=" + this.id + ", name=" + this.name + ")";
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public ServiceTypeBean(int i, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.id = i;
        this.name = name;
        this.problem = new ArrayList();
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }

    @Bindable
    public final List<ProblemBean> getProblem() {
        return this.problem;
    }

    public final void setProblem(List<ProblemBean> list) {
        this.problem = list;
        notifyPropertyChanged(78);
    }
}
