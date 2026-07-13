package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginChangeBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/LoginChangeBean;", "", "()V", "loginString", "", "getLoginString", "()Ljava/lang/String;", "setLoginString", "(Ljava/lang/String;)V", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LoginChangeBean {
    public static final int $stable = 8;
    private String loginString = "";

    public final String getLoginString() {
        return this.loginString;
    }

    public final void setLoginString(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.loginString = str;
    }
}
