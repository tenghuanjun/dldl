package com.getui.gtc.base.util;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.ProcessSwitchContract;
import com.getui.gtc.base.publish.Broker;
import com.getui.gtc.base.publish.Subscriber;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class CommonUtil {

    static class CommonUtilSubscriber implements Subscriber {
        private static String getInstanceMethodName;

        static class InstanceHolder {
            private static final CommonUtilSubscriber instance = new CommonUtilSubscriber();

            private InstanceHolder() {
            }
        }

        private CommonUtilSubscriber() {
        }

        private Bundle createBundle() {
            Bundle bundle = new Bundle();
            bundle.putString(ProcessSwitchContract.CLASS_NAME, getClass().getName());
            bundle.putString(ProcessSwitchContract.GET_INSTANCE, getInstanceMethodName);
            return bundle;
        }

        public static CommonUtilSubscriber getInstance() {
            getInstanceMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            return InstanceHolder.instance;
        }

        public boolean isAppForeground() {
            try {
                if (CommonUtil.isMainProcess()) {
                    return GtcProvider.getForeActivities().size() > 0;
                }
                Bundle bundleCreateBundle = createBundle();
                bundleCreateBundle.putString(ProcessSwitchContract.METHOD_NAME, "base-1-1-1");
                return Broker.getInstance().subscribe(bundleCreateBundle).getBoolean(ProcessSwitchContract.METHOD_RETURN);
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // com.getui.gtc.base.publish.Subscriber
        public void receive(Bundle bundle, Bundle bundle2) {
            ArrayList arrayList = new ArrayList();
            try {
                try {
                    Throwable th = (Throwable) bundle2.getSerializable(ProcessSwitchContract.METHOD_EXCEPTION);
                    if (th != null) {
                        arrayList.add(th);
                    }
                    String string = bundle.getString(ProcessSwitchContract.METHOD_NAME);
                    if (TextUtils.isEmpty(string)) {
                        throw new RuntimeException("methodName missed");
                    }
                    byte b = -1;
                    if (string.hashCode() == -1969640451 && string.equals("base-1-1-1")) {
                        b = 0;
                    }
                    if (b == 0) {
                        bundle2.putBoolean(ProcessSwitchContract.METHOD_RETURN, isAppForeground());
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((Throwable) it.next()).printStackTrace();
                    }
                } catch (Throwable th2) {
                    arrayList.add(th2);
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ((Throwable) it2.next()).printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    ((Throwable) it3.next()).printStackTrace();
                }
                throw th3;
            }
        }
    }

    public static String getProcessName(Context context) {
        String processName = "";
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                processName = Application.getProcessName();
            } else {
                Method declaredMethod = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentProcessName", new Class[0]);
                declaredMethod.setAccessible(true);
                processName = (String) declaredMethod.invoke(null, new Object[0]);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return processName;
    }

    public static boolean isAppForeground() {
        try {
            return CommonUtilSubscriber.getInstance().isAppForeground();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isMainProcess() {
        return Process.myPid() == GtcProvider.pid;
    }

    @Deprecated
    public static boolean isMainProcess(Context context) {
        return isMainProcess();
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
