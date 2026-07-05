package com.sqwan.base;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseEnginHandler {
    protected String TAG = getClass().getSimpleName();
    protected WeakReference<Activity> activityWeakReference;
    protected Context context;

    protected boolean isLiveshowTypeAudio() {
        return true;
    }

    protected boolean isLiveshowTypeHy() {
        return true;
    }

    protected Activity checkValid() {
        return this.activityWeakReference.get();
    }

    public void init(Context context) {
        if ((context instanceof Activity) && this.activityWeakReference == null) {
            this.activityWeakReference = new WeakReference<>((Activity) context);
            this.context = context.getApplicationContext();
        }
    }

    public void exit() {
        Activity activityCheckValid = checkValid();
        if (activityCheckValid == null) {
            return;
        }
        activityCheckValid.finish();
        System.exit(0);
    }
}
