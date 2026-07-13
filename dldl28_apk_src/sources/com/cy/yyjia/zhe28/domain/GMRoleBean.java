package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.volcengine.common.contant.CommonConstants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GMRoleBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0002\u0010\nJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0004HÆ\u0003JE\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u0004HÆ\u0001J\u0013\u0010 \u001a\u00020\u00112\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\u0006\u0010#\u001a\u00020\u0004J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR&\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118G@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f¨\u0006'"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GMRoleBean;", "Landroidx/databinding/BaseObservable;", "Ljava/io/Serializable;", CommonConstants.key_accountId, "", "accountName", "serviceId", "serviceCode", "roleId", "roleName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccountId", "()Ljava/lang/String;", "getAccountName", "getRoleId", "getRoleName", DBHelper.COL_VALUE, "", "selected", "getSelected", "()Z", "setSelected", "(Z)V", "getServiceCode", "getServiceId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "getShowText", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GMRoleBean extends BaseObservable implements Serializable {
    public static final int $stable = 8;
    private final String accountId;
    private final String accountName;
    private final String roleId;
    private final String roleName;
    private boolean selected;
    private final String serviceCode;
    private final String serviceId;

    public static /* synthetic */ GMRoleBean copy$default(GMRoleBean gMRoleBean, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gMRoleBean.accountId;
        }
        if ((i & 2) != 0) {
            str2 = gMRoleBean.accountName;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = gMRoleBean.serviceId;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = gMRoleBean.serviceCode;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = gMRoleBean.roleId;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = gMRoleBean.roleName;
        }
        return gMRoleBean.copy(str, str7, str8, str9, str10, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccountId() {
        return this.accountId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccountName() {
        return this.accountName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getServiceId() {
        return this.serviceId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getServiceCode() {
        return this.serviceCode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getRoleId() {
        return this.roleId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getRoleName() {
        return this.roleName;
    }

    public final GMRoleBean copy(String accountId, String accountName, String serviceId, String serviceCode, String roleId, String roleName) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(serviceId, "serviceId");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        Intrinsics.checkNotNullParameter(roleId, "roleId");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        return new GMRoleBean(accountId, accountName, serviceId, serviceCode, roleId, roleName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GMRoleBean)) {
            return false;
        }
        GMRoleBean gMRoleBean = (GMRoleBean) other;
        return Intrinsics.areEqual(this.accountId, gMRoleBean.accountId) && Intrinsics.areEqual(this.accountName, gMRoleBean.accountName) && Intrinsics.areEqual(this.serviceId, gMRoleBean.serviceId) && Intrinsics.areEqual(this.serviceCode, gMRoleBean.serviceCode) && Intrinsics.areEqual(this.roleId, gMRoleBean.roleId) && Intrinsics.areEqual(this.roleName, gMRoleBean.roleName);
    }

    public int hashCode() {
        return (((((((((this.accountId.hashCode() * 31) + this.accountName.hashCode()) * 31) + this.serviceId.hashCode()) * 31) + this.serviceCode.hashCode()) * 31) + this.roleId.hashCode()) * 31) + this.roleName.hashCode();
    }

    public String toString() {
        return "GMRoleBean(accountId=" + this.accountId + ", accountName=" + this.accountName + ", serviceId=" + this.serviceId + ", serviceCode=" + this.serviceCode + ", roleId=" + this.roleId + ", roleName=" + this.roleName + ")";
    }

    public final String getAccountId() {
        return this.accountId;
    }

    public final String getAccountName() {
        return this.accountName;
    }

    public final String getServiceId() {
        return this.serviceId;
    }

    public final String getServiceCode() {
        return this.serviceCode;
    }

    public final String getRoleId() {
        return this.roleId;
    }

    public final String getRoleName() {
        return this.roleName;
    }

    public GMRoleBean(String accountId, String accountName, String serviceId, String serviceCode, String roleId, String roleName) {
        Intrinsics.checkNotNullParameter(accountId, "accountId");
        Intrinsics.checkNotNullParameter(accountName, "accountName");
        Intrinsics.checkNotNullParameter(serviceId, "serviceId");
        Intrinsics.checkNotNullParameter(serviceCode, "serviceCode");
        Intrinsics.checkNotNullParameter(roleId, "roleId");
        Intrinsics.checkNotNullParameter(roleName, "roleName");
        this.accountId = accountId;
        this.accountName = accountName;
        this.serviceId = serviceId;
        this.serviceCode = serviceCode;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Bindable
    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
        notifyPropertyChanged(94);
    }

    public final String getShowText() {
        return this.roleName + "[" + this.serviceId + "服-" + this.serviceCode + "]";
    }
}
