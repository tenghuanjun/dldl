package com.cy.yyjia.zhe28.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.lzy.okgo.cache.CacheEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SPUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004¨\u0006\u000b"}, d2 = {"Lcom/cy/yyjia/zhe28/util/SPUtil;", "", "()V", "getSPData", "", "context", "Landroid/content/Context;", CacheEntity.KEY, "setSPData", "", DBHelper.COL_VALUE, "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SPUtil {
    public static final int $stable = 0;
    public static final SPUtil INSTANCE = new SPUtil();

    private SPUtil() {
    }

    public final String getSPData(Context context, String key) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        return context.getSharedPreferences("user", 0).getString(key, "");
    }

    public final void setSPData(Context context, String key, String value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("user", 0).edit();
        editorEdit.putString(key, value);
        editorEdit.commit();
    }
}
