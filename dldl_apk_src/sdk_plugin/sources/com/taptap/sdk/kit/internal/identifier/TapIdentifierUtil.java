package com.taptap.sdk.kit.internal.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.provider.Settings;
import com.sqwan.bugless.core.Constant;
import com.taptap.sdk.kit.internal.TapTapKit;
import com.taptap.sdk.kit.internal.identifier.content.TapGameUserIdUtil;
import com.taptap.sdk.kit.internal.identifier.content.TapOpenIdUtil;
import java.security.MessageDigest;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: TapIdentifierUtil.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0017\u001a\u00020\u0004J\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001d\u001a\u00020\u001b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/taptap/sdk/kit/internal/identifier/TapIdentifierUtil;", "", "()V", "SP_NAME", "", "androidId", Constant.DEVICE_ID, "installUUID", "sp", "Landroid/content/SharedPreferences;", "getSp", "()Landroid/content/SharedPreferences;", "sp$delegate", "Lkotlin/Lazy;", "uniqueDeviceId", "bytesToHexString", "src", "", "getAndroidID", "context", "Landroid/content/Context;", "getDeviceId", "getGameUserId", "getInstallUUID", "getOpenId", "getUniqueDeviceId", "setGameUserId", "", "gameUserId", "setOpenId", "openId", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapIdentifierUtil {
    private static final String SP_NAME = "TapSdkCore_SP";
    private static volatile String androidId;
    private static volatile String deviceId;
    public static final TapIdentifierUtil INSTANCE = new TapIdentifierUtil();

    /* JADX INFO: renamed from: sp$delegate, reason: from kotlin metadata */
    private static final Lazy sp = LazyKt.lazy(new Function0<SharedPreferences>() { // from class: com.taptap.sdk.kit.internal.identifier.TapIdentifierUtil$sp$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SharedPreferences invoke() {
            return TapTapKit.INSTANCE.getContext().getSharedPreferences("TapSdkCore_SP", 0);
        }
    });
    private static volatile String uniqueDeviceId = "";
    private static volatile String installUUID = "";

    private TapIdentifierUtil() {
    }

    private final SharedPreferences getSp() {
        Object value = sp.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-sp>(...)");
        return (SharedPreferences) value;
    }

    @JvmStatic
    public static final String getAndroidID(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (androidId != null) {
            return androidId;
        }
        try {
            androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception unused) {
        }
        return androidId;
    }

    public final String getUniqueDeviceId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            if (uniqueDeviceId.length() > 0) {
                return uniqueDeviceId;
            }
            long j = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            int iMyPid = Process.myPid();
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            sb.append('-');
            sb.append(iMyPid);
            String string = sb.toString();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("v1-");
            byte[] bytes = string.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            sb2.append(bytesToHexString(messageDigest.digest(bytes)));
            uniqueDeviceId = sb2.toString();
            return uniqueDeviceId;
        } catch (Throwable th) {
            th.printStackTrace();
            uniqueDeviceId = "unknown";
            return uniqueDeviceId;
        }
    }

    public final String getDeviceId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (deviceId != null) {
            return deviceId;
        }
        deviceId = DataUtils.getDeviceId(context);
        return deviceId;
    }

    public final synchronized String getInstallUUID() {
        boolean z = true;
        if (installUUID.length() > 0) {
            return installUUID;
        }
        String string = getSp().getString("tapsdk_install_uuid", null);
        String str = string;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (!z) {
            installUUID = string;
            return string;
        }
        String string2 = UUID.randomUUID().toString();
        getSp().edit().putString("tapsdk_install_uuid", string2).apply();
        installUUID = string2;
        return string2;
    }

    public final String getOpenId() {
        return TapOpenIdUtil.INSTANCE.getOpenId();
    }

    public final void setOpenId(String openId) {
        TapOpenIdUtil.INSTANCE.setOpenId$tap_common_release(openId);
    }

    public final String getGameUserId() {
        return TapGameUserIdUtil.INSTANCE.getGameUserId();
    }

    public final void setGameUserId(String gameUserId) {
        TapGameUserIdUtil.INSTANCE.setGameUserId$tap_common_release(gameUserId);
    }

    private final String bytesToHexString(byte[] src) {
        if (src == null) {
            return null;
        }
        if (src.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (byte b : src) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
