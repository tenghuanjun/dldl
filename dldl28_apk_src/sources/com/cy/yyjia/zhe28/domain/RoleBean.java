package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import com.volcengine.common.contant.CommonConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoleBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR&\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f8G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/RoleBean;", "Landroidx/databinding/BaseObservable;", "id", "", "name", "", "roleName", "serviceCode", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getRoleName", "selected", "", "getSelected", "()Z", "setSelected", "(Z)V", "getServiceCode", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class RoleBean extends BaseObservable {
    public static final int $stable = 8;

    @SerializedName(alternate = {CommonConstants.key_accountId}, value = "id")
    private final int id;
    private final String name;
    private final String roleName;
    private boolean selected;
    private final String serviceCode;

    public static /* synthetic */ RoleBean copy$default(RoleBean roleBean, int i, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = roleBean.id;
        }
        if ((i2 & 2) != 0) {
            str = roleBean.name;
        }
        if ((i2 & 4) != 0) {
            str2 = roleBean.roleName;
        }
        if ((i2 & 8) != 0) {
            str3 = roleBean.serviceCode;
        }
        return roleBean.copy(i, str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRoleName() {
        return this.roleName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getServiceCode() {
        return this.serviceCode;
    }

    public final RoleBean copy(int id, String name, String roleName, String serviceCode) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        return new RoleBean(id, name, roleName, serviceCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoleBean)) {
            return false;
        }
        RoleBean roleBean = (RoleBean) other;
        return this.id == roleBean.id && Intrinsics.areEqual(this.name, roleBean.name) && Intrinsics.areEqual(this.roleName, roleBean.roleName) && Intrinsics.areEqual(this.serviceCode, roleBean.serviceCode);
    }

    public int hashCode() {
        return (((((this.id * 31) + this.name.hashCode()) * 31) + this.roleName.hashCode()) * 31) + this.serviceCode.hashCode();
    }

    public String toString() {
        return "RoleBean(id=" + this.id + ", name=" + this.name + ", roleName=" + this.roleName + ", serviceCode=" + this.serviceCode + ")";
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getRoleName() {
        return this.roleName;
    }

    public final String getServiceCode() {
        return this.serviceCode;
    }

    public RoleBean(int i, String name, String roleName, String serviceCode) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        this.id = i;
        this.name = name;
        this.roleName = roleName;
        this.serviceCode = serviceCode;
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
