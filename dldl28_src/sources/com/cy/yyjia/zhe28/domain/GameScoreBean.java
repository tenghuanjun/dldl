package com.cy.yyjia.zhe28.domain;

import com.tencent.connect.common.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: GameScoreBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u0016\u001a\u00020\u0006J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0006J\u0006\u0010\u001a\u001a\u00020\u0006J\u0006\u0010\u001b\u001a\u00020\u0006J\u0006\u0010\u001c\u001a\u00020\u0006J\u0006\u0010\u001d\u001a\u00020\u0006J\u0006\u0010\u001e\u001a\u00020\u0003J\t\u0010\u001f\u001a\u00020\u0006HÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/GameScoreBean;", "", "average", "", "star", "", "", "total_num", "(Ljava/lang/String;Ljava/util/Map;I)V", "getAverage", "()Ljava/lang/String;", "getStar", "()Ljava/util/Map;", "getTotal_num", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "getMax", "getRating", "", "getS1", "getS2", "getS3", "getS4", "getS5", "getScore", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class GameScoreBean {
    public static final int $stable = 8;
    private final String average;
    private final Map<String, Integer> star;
    private final int total_num;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GameScoreBean copy$default(GameScoreBean gameScoreBean, String str, Map map, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gameScoreBean.average;
        }
        if ((i2 & 2) != 0) {
            map = gameScoreBean.star;
        }
        if ((i2 & 4) != 0) {
            i = gameScoreBean.total_num;
        }
        return gameScoreBean.copy(str, map, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAverage() {
        return this.average;
    }

    public final Map<String, Integer> component2() {
        return this.star;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getTotal_num() {
        return this.total_num;
    }

    public final GameScoreBean copy(String average, Map<String, Integer> star, int total_num) {
        Intrinsics.checkNotNullParameter(average, "average");
        Intrinsics.checkNotNullParameter(star, "star");
        return new GameScoreBean(average, star, total_num);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GameScoreBean)) {
            return false;
        }
        GameScoreBean gameScoreBean = (GameScoreBean) other;
        return Intrinsics.areEqual(this.average, gameScoreBean.average) && Intrinsics.areEqual(this.star, gameScoreBean.star) && this.total_num == gameScoreBean.total_num;
    }

    public int hashCode() {
        return (((this.average.hashCode() * 31) + this.star.hashCode()) * 31) + this.total_num;
    }

    public String toString() {
        return "GameScoreBean(average=" + this.average + ", star=" + this.star + ", total_num=" + this.total_num + ")";
    }

    public GameScoreBean(String average, Map<String, Integer> star, int i) {
        Intrinsics.checkNotNullParameter(average, "average");
        Intrinsics.checkNotNullParameter(star, "star");
        this.average = average;
        this.star = star;
        this.total_num = i;
    }

    public final String getAverage() {
        return this.average;
    }

    public final Map<String, Integer> getStar() {
        return this.star;
    }

    public final int getTotal_num() {
        return this.total_num;
    }

    public final String getScore() {
        return StringsKt.replace$default(String.valueOf(Float.parseFloat(this.average) * 2), ".0", "", false, 4, (Object) null);
    }

    public final float getRating() {
        return Float.parseFloat(this.average) / 2;
    }

    public final int getMax() {
        int iIntValue = 0;
        for (int i = 1; i < 6; i++) {
            Integer num = this.star.get(String.valueOf(i));
            Intrinsics.checkNotNull(num);
            iIntValue += num.intValue();
        }
        return iIntValue;
    }

    public final int getS1() {
        Integer num = this.star.get("1");
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }

    public final int getS2() {
        Integer num = this.star.get("2");
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }

    public final int getS3() {
        Integer num = this.star.get("3");
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }

    public final int getS4() {
        Integer num = this.star.get(Constants.VIA_TO_TYPE_QZONE);
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }

    public final int getS5() {
        Integer num = this.star.get("5");
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }
}
