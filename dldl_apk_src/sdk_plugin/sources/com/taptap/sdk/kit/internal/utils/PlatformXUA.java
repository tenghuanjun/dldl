package com.taptap.sdk.kit.internal.utils;

import com.taptap.sdk.kit.internal.TapLogger;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlatformXUA.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/kit/internal/utils/PlatformXUA;", "", "()V", "TAG", "", "sdkArtifact", "trackSDKArtifact", "getTrackSDKArtifact$annotations", "getTrackSDKArtifact", "()Ljava/lang/String;", "trackUA", "getTrackUA$annotations", "getTrackUA", "xuaMap", "", "addUA", "", "key", "value", "addUAInner", "setSDKArtifact", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PlatformXUA {
    private static final String TAG = "TapPlatformXUA";
    public static final PlatformXUA INSTANCE = new PlatformXUA();
    private static Map<String, String> xuaMap = new LinkedHashMap();
    private static String sdkArtifact = "Android";

    @JvmStatic
    public static /* synthetic */ void getTrackSDKArtifact$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getTrackUA$annotations() {
    }

    private PlatformXUA() {
    }

    @JvmStatic
    public static final void addUA(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        TapLogger.logd(TAG, "addUA key: " + key + ", value: " + value);
        xuaMap.put(key, value);
    }

    @JvmStatic
    public static final void addUAInner(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        addUA(key, value);
    }

    @JvmStatic
    public static final void setSDKArtifact(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        TapLogger.logd(TAG, "setSDKArtifact value: " + value);
        sdkArtifact = value;
    }

    public static final String getTrackSDKArtifact() {
        return sdkArtifact;
    }

    public static final String getTrackUA() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : xuaMap.entrySet()) {
            sb.append(entry.getKey());
            sb.append("/");
            sb.append(entry.getValue());
            sb.append(" ");
        }
        sb.append("TapSDK-Android/");
        sb.append("4.5.7");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
        return string;
    }
}
