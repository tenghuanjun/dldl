package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/AbResult;", "", "a", "", "b", "", "(ILjava/lang/String;)V", "getA", "()I", "getB", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class AbResult {
    public static final int $stable = 0;
    private final int a;
    private final String b;

    public static /* synthetic */ AbResult copy$default(AbResult abResult, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = abResult.a;
        }
        if ((i2 & 2) != 0) {
            str = abResult.b;
        }
        return abResult.copy(i, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getA() {
        return this.a;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getB() {
        return this.b;
    }

    public final AbResult copy(int a2, String b) {
        Intrinsics.checkNotNullParameter(b, "b");
        return new AbResult(a2, b);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AbResult)) {
            return false;
        }
        AbResult abResult = (AbResult) other;
        return this.a == abResult.a && Intrinsics.areEqual(this.b, abResult.b);
    }

    public int hashCode() {
        return (this.a * 31) + this.b.hashCode();
    }

    public String toString() {
        return "AbResult(a=" + this.a + ", b=" + this.b + ")";
    }

    public AbResult(int i, String b) {
        Intrinsics.checkNotNullParameter(b, "b");
        this.a = i;
        this.b = b;
    }

    public final int getA() {
        return this.a;
    }

    public final String getB() {
        return this.b;
    }
}
