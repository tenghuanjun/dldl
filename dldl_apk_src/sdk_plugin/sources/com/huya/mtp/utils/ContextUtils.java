package com.huya.mtp.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ContextUtils {
    private static Application sApplication;

    public static Activity getActivity(Context context) {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 10 || context == null) {
                return null;
            }
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
            i = i2;
        }
    }

    public static Service getService(Context context) {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 10 || context == null) {
                return null;
            }
            if (context instanceof Service) {
                return (Service) context;
            }
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
            i = i2;
        }
    }

    public static Application getApplication(Context context) {
        Application application = sApplication;
        if (application != null) {
            return application;
        }
        if (context instanceof Application) {
            sApplication = (Application) context;
        } else {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext instanceof Application) {
                sApplication = (Application) applicationContext;
            } else {
                Activity activity = getActivity(context);
                if (activity != null) {
                    sApplication = activity.getApplication();
                } else {
                    Service service = getService(context);
                    if (service != null) {
                        sApplication = service.getApplication();
                    }
                }
            }
        }
        return sApplication;
    }
}
