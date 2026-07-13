package com.cy.yyjia.zhe28.domain;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\u000e\u0010\u0015\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ<\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/BaseResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "code", "", "msg", "", "time", "data", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getCode", "()I", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMsg", "()Ljava/lang/String;", "getTime", "component1", "component2", "component3", "component4", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lcom/cy/yyjia/zhe28/domain/BaseResult;", "equals", "", "other", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class BaseResult<T> {
    public static final int $stable = 0;
    private final int code;
    private final T data;
    private final String msg;
    private final String time;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BaseResult copy$default(BaseResult baseResult, int i, String str, String str2, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = baseResult.code;
        }
        if ((i2 & 2) != 0) {
            str = baseResult.msg;
        }
        if ((i2 & 4) != 0) {
            str2 = baseResult.time;
        }
        if ((i2 & 8) != 0) {
            obj = baseResult.data;
        }
        return baseResult.copy(i, str, str2, obj);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTime() {
        return this.time;
    }

    public final T component4() {
        return this.data;
    }

    public final BaseResult<T> copy(int code, String msg, String time, T data) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(time, "time");
        return new BaseResult<>(code, msg, time, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BaseResult)) {
            return false;
        }
        BaseResult baseResult = (BaseResult) other;
        return this.code == baseResult.code && Intrinsics.areEqual(this.msg, baseResult.msg) && Intrinsics.areEqual(this.time, baseResult.time) && Intrinsics.areEqual(this.data, baseResult.data);
    }

    public int hashCode() {
        int iHashCode = ((((this.code * 31) + this.msg.hashCode()) * 31) + this.time.hashCode()) * 31;
        T t = this.data;
        return iHashCode + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        return "BaseResult(code=" + this.code + ", msg=" + this.msg + ", time=" + this.time + ", data=" + this.data + ")";
    }

    public BaseResult(int i, String msg, String time, T t) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(time, "time");
        this.code = i;
        this.msg = msg;
        this.time = time;
        this.data = t;
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

    public final T getData() {
        return this.data;
    }
}
