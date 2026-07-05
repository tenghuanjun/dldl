package com.taptap.sdk.kit.internal.utils;

import android.content.SharedPreferences;
import com.taptap.sdk.kit.internal.TapTapKit;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KVUtil.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u0004J\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u000e2\b\b\u0002\u0010\f\u001a\u00020\u0004J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004J\"\u0010\u0011\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u0004J \u0010\u0015\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\f\u001a\u00020\u0004J\"\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004J\u0018\u0010\u0017\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/taptap/sdk/kit/internal/utils/KVUtil;", "", "()V", "DEFAULT_SP_FILE_DIR", "", "cache", "", "Landroid/content/SharedPreferences;", "getInt", "", "key", "defValue", "spFileDir", "getLong", "", "getOrCreate", "name", "getString", "putInt", "", "value", "putLong", "putString", "remove", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class KVUtil {
    private static final String DEFAULT_SP_FILE_DIR = "tap_sdk_sp";
    public static final KVUtil INSTANCE = new KVUtil();
    private static final Map<String, SharedPreferences> cache = new LinkedHashMap();

    private KVUtil() {
    }

    public static /* synthetic */ void putInt$default(KVUtil kVUtil, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = DEFAULT_SP_FILE_DIR;
        }
        kVUtil.putInt(str, i, str2);
    }

    public final void putInt(String key, int value, String spFileDir) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(spFileDir, "spFileDir");
        getOrCreate(spFileDir).edit().putInt(key, value).apply();
    }

    public static /* synthetic */ int getInt$default(KVUtil kVUtil, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            str2 = DEFAULT_SP_FILE_DIR;
        }
        return kVUtil.getInt(str, i, str2);
    }

    public final int getInt(String key, int defValue, String spFileDir) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(spFileDir, "spFileDir");
        return getOrCreate(spFileDir).getInt(key, defValue);
    }

    public static /* synthetic */ void putLong$default(KVUtil kVUtil, String str, long j, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = DEFAULT_SP_FILE_DIR;
        }
        kVUtil.putLong(str, j, str2);
    }

    public final void putLong(String key, long value, String spFileDir) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(spFileDir, "spFileDir");
        getOrCreate(spFileDir).edit().putLong(key, value).apply();
    }

    public static /* synthetic */ long getLong$default(KVUtil kVUtil, String str, long j, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        if ((i & 4) != 0) {
            str2 = DEFAULT_SP_FILE_DIR;
        }
        return kVUtil.getLong(str, j, str2);
    }

    public final long getLong(String key, long defValue, String spFileDir) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(spFileDir, "spFileDir");
        return getOrCreate(spFileDir).getLong(key, defValue);
    }

    public static /* synthetic */ void putString$default(KVUtil kVUtil, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = DEFAULT_SP_FILE_DIR;
        }
        kVUtil.putString(str, str2, str3);
    }

    public final void putString(String key, String value, String spFileDir) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(spFileDir, "spFileDir");
        getOrCreate(spFileDir).edit().putString(key, value).apply();
    }

    public static /* synthetic */ String getString$default(KVUtil kVUtil, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            str3 = DEFAULT_SP_FILE_DIR;
        }
        return kVUtil.getString(str, str2, str3);
    }

    public final String getString(String key, String defValue, String spFileDir) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValue, "defValue");
        Intrinsics.checkNotNullParameter(spFileDir, "spFileDir");
        String string = getOrCreate(spFileDir).getString(key, defValue);
        return string == null ? defValue : string;
    }

    public static /* synthetic */ String getString$default(KVUtil kVUtil, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = DEFAULT_SP_FILE_DIR;
        }
        return kVUtil.getString(str, str2);
    }

    public final String getString(String key, String spFileDir) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(spFileDir, "spFileDir");
        return getOrCreate(spFileDir).getString(key, null);
    }

    public static /* synthetic */ void remove$default(KVUtil kVUtil, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = DEFAULT_SP_FILE_DIR;
        }
        kVUtil.remove(str, str2);
    }

    public final void remove(String key, String spFileDir) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(spFileDir, "spFileDir");
        getOrCreate(spFileDir).edit().remove(key).apply();
    }

    private final synchronized SharedPreferences getOrCreate(String name) {
        SharedPreferences sharedPreferences;
        Map<String, SharedPreferences> map = cache;
        sharedPreferences = map.get(name);
        if (sharedPreferences == null) {
            sharedPreferences = TapTapKit.INSTANCE.getContext().getSharedPreferences(name, 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "TapTapKit.context.getSha…me, Context.MODE_PRIVATE)");
            map.put(name, sharedPreferences);
        }
        return sharedPreferences;
    }
}
