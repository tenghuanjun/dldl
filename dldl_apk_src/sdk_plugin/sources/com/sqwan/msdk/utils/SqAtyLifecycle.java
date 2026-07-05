package com.sqwan.msdk.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.sq.push.service.SqPushService;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.util.ActivityLifecycleAdapter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqAtyLifecycle {
    private SqAtyLifecycle() {
    }

    public static void onApplication(Context context) {
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(new ActivityLifecycleAdapter() { // from class: com.sqwan.msdk.utils.SqAtyLifecycle.1
                @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    try {
                        SqPushService.getInstance().onCreate(activity, bundle);
                    } catch (Throwable unused) {
                    }
                }
            });
            return;
        }
        SQLog.w(context + "实例不是Application, 无法监听Activity");
    }
}
