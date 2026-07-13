package com.cy.yyjia.zhe28.domain;

import com.volcengine.common.contant.CommonConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AccountListBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0002\u0010\u000eJ\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J_\u0010\"\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\bHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0006HÖ\u0001J\t\u0010'\u001a\u00020\bHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014¨\u0006("}, d2 = {"Lcom/cy/yyjia/zhe28/domain/AccountListBean;", "", "account_list", "", "Lcom/cy/yyjia/zhe28/domain/DealBean;", CommonConstants.key_gameId, "", "icon", "", "name", "shortDesc", "star", "total", "type", "(Ljava/util/List;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;)V", "getAccount_list", "()Ljava/util/List;", "getGameId", "()I", "getIcon", "()Ljava/lang/String;", "getName", "getShortDesc", "getStar", "getTotal", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class AccountListBean {
    public static final int $stable = 8;
    private final List<DealBean> account_list;
    private final int gameId;
    private final String icon;
    private final String name;
    private final String shortDesc;
    private final int star;
    private final int total;
    private final String type;

    public final List<DealBean> component1() {
        return this.account_list;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getGameId() {
        return this.gameId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getShortDesc() {
        return this.shortDesc;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getStar() {
        return this.star;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getTotal() {
        return this.total;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final AccountListBean copy(List<DealBean> account_list, int gameId, String icon, String name, String shortDesc, int star, int total, String type) {
        Intrinsics.checkNotNullParameter(account_list, "account_list");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(shortDesc, "shortDesc");
        Intrinsics.checkNotNullParameter(type, "type");
        return new AccountListBean(account_list, gameId, icon, name, shortDesc, star, total, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccountListBean)) {
            return false;
        }
        AccountListBean accountListBean = (AccountListBean) other;
        return Intrinsics.areEqual(this.account_list, accountListBean.account_list) && this.gameId == accountListBean.gameId && Intrinsics.areEqual(this.icon, accountListBean.icon) && Intrinsics.areEqual(this.name, accountListBean.name) && Intrinsics.areEqual(this.shortDesc, accountListBean.shortDesc) && this.star == accountListBean.star && this.total == accountListBean.total && Intrinsics.areEqual(this.type, accountListBean.type);
    }

    public int hashCode() {
        return (((((((((((((this.account_list.hashCode() * 31) + this.gameId) * 31) + this.icon.hashCode()) * 31) + this.name.hashCode()) * 31) + this.shortDesc.hashCode()) * 31) + this.star) * 31) + this.total) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "AccountListBean(account_list=" + this.account_list + ", gameId=" + this.gameId + ", icon=" + this.icon + ", name=" + this.name + ", shortDesc=" + this.shortDesc + ", star=" + this.star + ", total=" + this.total + ", type=" + this.type + ")";
    }

    public AccountListBean(List<DealBean> account_list, int i, String icon, String name, String shortDesc, int i2, int i3, String type) {
        Intrinsics.checkNotNullParameter(account_list, "account_list");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(shortDesc, "shortDesc");
        Intrinsics.checkNotNullParameter(type, "type");
        this.account_list = account_list;
        this.gameId = i;
        this.icon = icon;
        this.name = name;
        this.shortDesc = shortDesc;
        this.star = i2;
        this.total = i3;
        this.type = type;
    }

    public final List<DealBean> getAccount_list() {
        return this.account_list;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getName() {
        return this.name;
    }

    public final String getShortDesc() {
        return this.shortDesc;
    }

    public final int getStar() {
        return this.star;
    }

    public final int getTotal() {
        return this.total;
    }

    public final String getType() {
        return this.type;
    }
}
