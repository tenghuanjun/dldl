package com.host;

import android.content.Context;
import com.plugin.core.ReflectionUtils;
import com.sqwan.msdk.SQwanCore;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class PluginExActivity extends PluginActivity {
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        hookResources(context);
    }

    private void hookResources(Context context) {
        ReflectionUtils.setFieldValue(context, "mResources", SQwanCore.getInstance().getResources(context.getResources()));
    }
}
