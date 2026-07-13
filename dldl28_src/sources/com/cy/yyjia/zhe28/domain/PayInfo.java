package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PayInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PayInfo;", "", "msg", "", "paydata", "paytype", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMsg", "()Ljava/lang/String;", "getPaydata", "getPaytype", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PayInfo {
    public static final int $stable = 0;
    private final String msg;
    private final String paydata;
    private final String paytype;

    public PayInfo(String msg, String paydata, String paytype) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(paydata, "paydata");
        Intrinsics.checkNotNullParameter(paytype, "paytype");
        this.msg = msg;
        this.paydata = paydata;
        this.paytype = paytype;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final String getPaydata() {
        return this.paydata;
    }

    public final String getPaytype() {
        return this.paytype;
    }
}
