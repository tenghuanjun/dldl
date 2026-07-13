package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WxLoginBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/WxLoginBean;", "", "code", "", "(Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class WxLoginBean {
    public static final int $stable = 0;
    private final String code;

    public static /* synthetic */ WxLoginBean copy$default(WxLoginBean wxLoginBean, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = wxLoginBean.code;
        }
        return wxLoginBean.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    public final WxLoginBean copy(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        return new WxLoginBean(code);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof WxLoginBean) && Intrinsics.areEqual(this.code, ((WxLoginBean) other).code);
    }

    public int hashCode() {
        return this.code.hashCode();
    }

    public String toString() {
        return "WxLoginBean(code=" + this.code + ")";
    }

    public WxLoginBean(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        this.code = code;
    }

    public final String getCode() {
        return this.code;
    }
}
