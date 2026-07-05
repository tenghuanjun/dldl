package com.taptap.sdk.kit.internal;

import android.util.Log;
import com.duowan.kiwi.base.smile.SmileConst;
import com.taptap.sdk.kit.internal.exception.TapInvalidDataException;
import com.taptap.sdk.kit.internal.exception.TapSdkException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: TapLogger.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0007J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J*\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J\u001c\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0007J\u001a\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0007J$\u0010\u0015\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/taptap/sdk/kit/internal/TapLogger;", "", "()V", "TAG", "", "enableLog", "", "getEnableLog", "()Z", "setEnableLog", "(Z)V", "buildWrapString", "msg", "e", "", "logd", "", SmileConst.KEY_ATTNAME, "loge", "logi", "logv", "logw", "needWrap", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapLogger {
    public static final TapLogger INSTANCE = new TapLogger();
    public static final String TAG = "TapTapSdk";
    private static boolean enableLog;

    @JvmStatic
    public static final void loge() {
        loge$default(null, null, null, 7, null);
    }

    @JvmStatic
    public static final void loge(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        loge$default(tag, null, null, 6, null);
    }

    @JvmStatic
    public static final void loge(String tag, String str) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        loge$default(tag, str, null, 4, null);
    }

    @JvmStatic
    public static final void logi(String str) {
        logi$default(null, str, 1, null);
    }

    private TapLogger() {
    }

    public final boolean getEnableLog() {
        return enableLog;
    }

    public final void setEnableLog(boolean z) {
        enableLog = z;
    }

    public static /* synthetic */ void loge$default(String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            str = TAG;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            th = null;
        }
        loge(str, str2, th);
    }

    @JvmStatic
    public static final void loge(String tag, String msg, Throwable e) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (e instanceof TapInvalidDataException ? true : e instanceof TapSdkException) {
            Log.e(tag, INSTANCE.buildWrapString(msg, e), e);
        } else {
            Log.e(tag, INSTANCE.buildWrapString(msg, e), e);
        }
    }

    public static /* synthetic */ void logw$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = TAG;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        logw(str, str2, z);
    }

    @JvmStatic
    public static final void logw(String tag, String msg, boolean needWrap) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        TapLogger tapLogger = INSTANCE;
        if (enableLog) {
            if (needWrap) {
                Log.w(tag, buildWrapString$default(tapLogger, msg, null, 2, null));
            } else {
                Log.w(tag, msg);
            }
        }
    }

    public static /* synthetic */ String buildWrapString$default(TapLogger tapLogger, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        return tapLogger.buildWrapString(str, th);
    }

    public final String buildWrapString(String msg, Throwable e) {
        List<String> listEmptyList;
        int iCoerceAtLeast;
        try {
            String str = msg;
            boolean z = true;
            if ((str == null || str.length() == 0) && e == null) {
                return "";
            }
            String message = e != null ? e.getMessage() : null;
            if (msg == null || (listEmptyList = StringsKt.lines(msg)) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            if (listEmptyList.isEmpty()) {
                iCoerceAtLeast = message != null ? message.length() : 0;
            } else {
                Iterator<T> it = listEmptyList.iterator();
                if (!it.hasNext()) {
                    throw new NoSuchElementException();
                }
                int length = ((String) it.next()).length();
                while (it.hasNext()) {
                    int length2 = ((String) it.next()).length();
                    if (length < length2) {
                        length = length2;
                    }
                }
                iCoerceAtLeast = RangesKt.coerceAtLeast(length, message != null ? message.length() : 0);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("┏━━━");
            for (int i = 0; i < iCoerceAtLeast; i++) {
                sb.append("━");
            }
            sb.append("━━━┓\n");
            for (String str2 : listEmptyList) {
                sb.append("┃   ");
                sb.append(str2);
                int length3 = iCoerceAtLeast - str2.length();
                for (int i2 = 0; i2 < length3; i2++) {
                    sb.append(" ");
                }
                sb.append("   ┃\n");
            }
            String str3 = message;
            if (str3 != null && str3.length() != 0) {
                z = false;
            }
            if (!z) {
                sb.append("┃   ");
                sb.append(message);
                int length4 = iCoerceAtLeast - message.length();
                for (int i3 = 0; i3 < length4; i3++) {
                    sb.append(" ");
                }
                sb.append("   ┃\n");
            }
            sb.append("┗━━━");
            for (int i4 = 0; i4 < iCoerceAtLeast; i4++) {
                sb.append("━");
            }
            sb.append("━━━┛");
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
            return string;
        } catch (Exception e2) {
            e2.printStackTrace();
            return msg == null ? "" : msg;
        }
    }

    public static /* synthetic */ void logi$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = TAG;
        }
        logi(str, str2);
    }

    @JvmStatic
    public static final void logi(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (enableLog) {
            Log.i(tag, AbstractJsonLexerKt.BEGIN_LIST + Thread.currentThread().getName() + "] " + msg);
        }
    }

    @JvmStatic
    public static final void logd(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (enableLog) {
            Log.d(tag, AbstractJsonLexerKt.BEGIN_LIST + Thread.currentThread().getName() + "] " + msg);
        }
    }

    @JvmStatic
    public static final void logd(String tag, Throwable e) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (enableLog) {
            String name = Thread.currentThread().getName();
            StringBuilder sb = new StringBuilder();
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(name);
            sb.append("] ");
            sb.append(e != null ? e.getMessage() : null);
            Log.d(tag, sb.toString(), e);
        }
    }

    @JvmStatic
    public static final void logd(String tag, String msg, Throwable e) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (enableLog) {
            Log.d(tag, AbstractJsonLexerKt.BEGIN_LIST + Thread.currentThread().getName() + "] " + msg, e);
        }
    }

    @JvmStatic
    public static final void logv(String tag, String msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (enableLog) {
            Log.v(tag, AbstractJsonLexerKt.BEGIN_LIST + Thread.currentThread().getName() + "] " + msg);
        }
    }
}
