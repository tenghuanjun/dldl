package com.getui.gtc.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.publish.Broker;
import com.getui.gtc.base.publish.Publisher;
import com.getui.gtc.base.util.ScheduleQueue;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class GtcProvider extends ContentProvider implements Publisher {

    @SuppressLint({"StaticFieldLeak"})
    private static Context context;
    public static int pid;
    private static String sdcardPath;
    private static Set<String> foreActivities = new HashSet();
    private static Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() { // from class: com.getui.gtc.base.GtcProvider.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
            try {
                GtcProvider.foreActivities.add(activity.getComponentName().getClassName());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            try {
                GtcProvider.foreActivities.remove(activity.getComponentName().getClassName());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };

    public static Context context() {
        return context;
    }

    public static Set<String> getForeActivities() {
        return foreActivities;
    }

    public static String getSdcardPath() {
        return sdcardPath;
    }

    public static void setContext(Context context2) {
        if (context2 != null) {
            context = context2.getApplicationContext();
            sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        return publish(bundle);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        try {
            context = getContext();
            pid = Process.myPid();
            ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.base.GtcProvider.2
                @Override // java.lang.Runnable
                public final void run() {
                    SecureCryptTools.getInstance();
                }
            });
            sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            ((Application) context).unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
            ((Application) context).registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // com.getui.gtc.base.publish.Publisher
    public Bundle publish(Bundle bundle) {
        return Broker.getInstance().publish(bundle);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
