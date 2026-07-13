package com.cy.yyjia.zhe28.domain;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\t\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0002\u0010\tR\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/PageBean;", ExifInterface.GPS_DIRECTION_TRUE, "", "current_page", "", "last_page", "list", "", "total_num", "(IILjava/util/List;I)V", "getCurrent_page", "()I", "getLast_page", "getList", "()Ljava/util/List;", "getTotal_num", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class PageBean<T> {
    public static final int $stable = 8;

    @SerializedName(alternate = {"current"}, value = "current_page")
    private final int current_page;

    @SerializedName(alternate = {"pageCount"}, value = "last_page")
    private final int last_page;

    @SerializedName(alternate = {"data"}, value = "list")
    private final List<T> list;

    @SerializedName(alternate = {MetricsSQLiteCacheKt.METRICS_COUNT, "total"}, value = "total_num")
    private final int total_num;

    public PageBean(int i, int i2, List<T> list, int i3) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.current_page = i;
        this.last_page = i2;
        this.list = list;
        this.total_num = i3;
    }

    public final int getCurrent_page() {
        return this.current_page;
    }

    public final int getLast_page() {
        return this.last_page;
    }

    public final List<T> getList() {
        return this.list;
    }

    public final int getTotal_num() {
        return this.total_num;
    }
}
