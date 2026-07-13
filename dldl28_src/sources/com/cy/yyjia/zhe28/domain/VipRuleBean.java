package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VipRuleBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/VipRuleBean;", "", "develop_desc", "", "develop_rule", "level_desc", "reduce_level_rule", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDevelop_desc", "()Ljava/lang/String;", "getDevelop_rule", "getLevel_desc", "getReduce_level_rule", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class VipRuleBean {
    public static final int $stable = 0;
    private final String develop_desc;
    private final String develop_rule;
    private final String level_desc;
    private final String reduce_level_rule;

    public static /* synthetic */ VipRuleBean copy$default(VipRuleBean vipRuleBean, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = vipRuleBean.develop_desc;
        }
        if ((i & 2) != 0) {
            str2 = vipRuleBean.develop_rule;
        }
        if ((i & 4) != 0) {
            str3 = vipRuleBean.level_desc;
        }
        if ((i & 8) != 0) {
            str4 = vipRuleBean.reduce_level_rule;
        }
        return vipRuleBean.copy(str, str2, str3, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDevelop_desc() {
        return this.develop_desc;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDevelop_rule() {
        return this.develop_rule;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLevel_desc() {
        return this.level_desc;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getReduce_level_rule() {
        return this.reduce_level_rule;
    }

    public final VipRuleBean copy(String develop_desc, String develop_rule, String level_desc, String reduce_level_rule) {
        Intrinsics.checkNotNullParameter(develop_desc, "develop_desc");
        Intrinsics.checkNotNullParameter(develop_rule, "develop_rule");
        Intrinsics.checkNotNullParameter(level_desc, "level_desc");
        Intrinsics.checkNotNullParameter(reduce_level_rule, "reduce_level_rule");
        return new VipRuleBean(develop_desc, develop_rule, level_desc, reduce_level_rule);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VipRuleBean)) {
            return false;
        }
        VipRuleBean vipRuleBean = (VipRuleBean) other;
        return Intrinsics.areEqual(this.develop_desc, vipRuleBean.develop_desc) && Intrinsics.areEqual(this.develop_rule, vipRuleBean.develop_rule) && Intrinsics.areEqual(this.level_desc, vipRuleBean.level_desc) && Intrinsics.areEqual(this.reduce_level_rule, vipRuleBean.reduce_level_rule);
    }

    public int hashCode() {
        return (((((this.develop_desc.hashCode() * 31) + this.develop_rule.hashCode()) * 31) + this.level_desc.hashCode()) * 31) + this.reduce_level_rule.hashCode();
    }

    public String toString() {
        return "VipRuleBean(develop_desc=" + this.develop_desc + ", develop_rule=" + this.develop_rule + ", level_desc=" + this.level_desc + ", reduce_level_rule=" + this.reduce_level_rule + ")";
    }

    public VipRuleBean(String develop_desc, String develop_rule, String level_desc, String reduce_level_rule) {
        Intrinsics.checkNotNullParameter(develop_desc, "develop_desc");
        Intrinsics.checkNotNullParameter(develop_rule, "develop_rule");
        Intrinsics.checkNotNullParameter(level_desc, "level_desc");
        Intrinsics.checkNotNullParameter(reduce_level_rule, "reduce_level_rule");
        this.develop_desc = develop_desc;
        this.develop_rule = develop_rule;
        this.level_desc = level_desc;
        this.reduce_level_rule = reduce_level_rule;
    }

    public final String getDevelop_desc() {
        return this.develop_desc;
    }

    public final String getDevelop_rule() {
        return this.develop_rule;
    }

    public final String getLevel_desc() {
        return this.level_desc;
    }

    public final String getReduce_level_rule() {
        return this.reduce_level_rule;
    }
}
