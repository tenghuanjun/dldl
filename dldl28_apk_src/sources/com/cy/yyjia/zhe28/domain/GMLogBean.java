package com.cy.yyjia.zhe28.domain;

import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import java.text.SimpleDateFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: GMLogBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u0006\u0010\u0017\u001a\u00020\u0005J\u0006\u0010\u0018\u001a\u00020\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GMLogBean;", "", "code", "", "msg", "", "task", MetricsSQLiteCacheKt.METRICS_COUNT, "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getCode", "()I", "getCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMsg", "()Ljava/lang/String;", "getTask", "time", "", "getTime", "()J", "setTime", "(J)V", "getShowText", "getTimeStr", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GMLogBean {
    public static final int $stable = 8;
    private final int code;
    private final Integer count;
    private final String msg;
    private final String task;
    private long time;

    public GMLogBean(int i, String msg, String str, Integer num) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.code = i;
        this.msg = msg;
        this.task = str;
        this.count = num;
        this.time = System.currentTimeMillis();
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMsg() {
        return this.msg;
    }

    public /* synthetic */ GMLogBean(int i, String str, String str2, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? 1 : num);
    }

    public final String getTask() {
        return this.task;
    }

    public final Integer getCount() {
        return this.count;
    }

    public final long getTime() {
        return this.time;
    }

    public final void setTime(long j) {
        this.time = j;
    }

    public final String getTimeStr() {
        String str = new SimpleDateFormat("hh:mm:ss").format(Long.valueOf(this.time));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final String getShowText() {
        if (this.code == 0) {
            return this.msg;
        }
        return "[" + this.task + StringUtils.SPACE + this.count + "] " + this.msg;
    }
}
