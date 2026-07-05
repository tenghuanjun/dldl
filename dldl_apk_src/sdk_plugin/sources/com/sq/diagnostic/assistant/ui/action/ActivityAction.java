package com.sq.diagnostic.assistant.ui.action;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ActivityAction {
    Activity getActivity();

    Context getContext();

    void startActivity(Intent intent);

    void startActivity(Class<? extends Activity> cls);

    /* JADX INFO: renamed from: com.sq.diagnostic.assistant.ui.action.ActivityAction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Activity $default$getActivity(ActivityAction _this) {
            Context context = _this.getContext();
            while (!(context instanceof Activity)) {
                if (!(context instanceof ContextWrapper) || (context = ((ContextWrapper) context).getBaseContext()) == null) {
                    return null;
                }
            }
            return (Activity) context;
        }

        public static void $default$startActivity(ActivityAction _this, Intent intent) {
            if (!(_this.getContext() instanceof Activity)) {
                intent.addFlags(268435456);
            }
            _this.getContext().startActivity(intent);
        }
    }
}
