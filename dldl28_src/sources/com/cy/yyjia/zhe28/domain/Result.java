package com.cy.yyjia.zhe28.domain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Result.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/Result;", "", "code", "", "msg", "", "time", "data", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getCode", "()I", "getData", "()Ljava/lang/Object;", "getMsg", "()Ljava/lang/String;", "getTime", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Result {
    public static final int $stable = 8;
    private final int code;
    private final Object data;
    private final String msg;
    private final String time;

    public Result(int i, String msg, String time, Object obj) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(time, "time");
        this.code = i;
        this.msg = msg;
        this.time = time;
        this.data = obj;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final String getTime() {
        return this.time;
    }

    public final Object getData() {
        return this.data;
    }
}
