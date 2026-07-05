package com.sqwan.common.route;

import android.app.Activity;
import android.os.Bundle;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface FunctionRouter {
    public static final String KEY_DATA = "data";
    public static final String TAG = "【Router】";

    public interface Func {
        public static final String FUNC_UNIVERSAL_ALI_PAY = "func_universal_ali_pay";
    }

    void call(Activity activity, String str, Bundle bundle);
}
