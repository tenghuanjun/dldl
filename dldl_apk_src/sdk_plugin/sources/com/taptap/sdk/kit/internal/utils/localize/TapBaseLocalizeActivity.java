package com.taptap.sdk.kit.internal.utils.localize;

import android.app.Activity;
import android.content.Context;
import kotlin.Metadata;

/* JADX INFO: compiled from: TapBaseLocalizeActivity.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/kit/internal/utils/localize/TapBaseLocalizeActivity;", "Landroid/app/Activity;", "()V", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public class TapBaseLocalizeActivity extends Activity {
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TapLocalizeUtil.INSTANCE.wrapperContext(newBase));
    }
}
