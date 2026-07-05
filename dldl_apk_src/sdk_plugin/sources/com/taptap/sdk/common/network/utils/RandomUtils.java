package com.taptap.sdk.common.network.utils;

import com.huya.mtp.hyns.report.NSPushReporter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* JADX INFO: compiled from: RandomUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/common/network/utils/RandomUtils;", "", "()V", "getRandomString", "", NSPushReporter.NS_PUSH_LENGTH_KEY, "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class RandomUtils {
    public static final RandomUtils INSTANCE = new RandomUtils();

    private RandomUtils() {
    }

    public final String getRandomString(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(Random.INSTANCE.nextInt(36)));
        }
        String string = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }
}
