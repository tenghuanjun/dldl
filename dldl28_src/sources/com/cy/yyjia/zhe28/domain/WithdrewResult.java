package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WithdrewResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WithdrewResult;", "", "msg", "", "tradeMoney", "(Ljava/lang/String;Ljava/lang/String;)V", "getMsg", "()Ljava/lang/String;", "getTradeMoney", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class WithdrewResult {
    public static final int $stable = 0;
    private final String msg;
    private final String tradeMoney;

    public static /* synthetic */ WithdrewResult copy$default(WithdrewResult withdrewResult, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = withdrewResult.msg;
        }
        if ((i & 2) != 0) {
            str2 = withdrewResult.tradeMoney;
        }
        return withdrewResult.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTradeMoney() {
        return this.tradeMoney;
    }

    public final WithdrewResult copy(String msg, String tradeMoney) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tradeMoney, "tradeMoney");
        return new WithdrewResult(msg, tradeMoney);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WithdrewResult)) {
            return false;
        }
        WithdrewResult withdrewResult = (WithdrewResult) other;
        return Intrinsics.areEqual(this.msg, withdrewResult.msg) && Intrinsics.areEqual(this.tradeMoney, withdrewResult.tradeMoney);
    }

    public int hashCode() {
        return (this.msg.hashCode() * 31) + this.tradeMoney.hashCode();
    }

    public String toString() {
        return "WithdrewResult(msg=" + this.msg + ", tradeMoney=" + this.tradeMoney + ")";
    }

    public WithdrewResult(String msg, String tradeMoney) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(tradeMoney, "tradeMoney");
        this.msg = msg;
        this.tradeMoney = tradeMoney;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final String getTradeMoney() {
        return this.tradeMoney;
    }
}
