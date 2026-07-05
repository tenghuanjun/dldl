package com.igexin.b.a.c;

import android.content.Context;
import com.getui.gtc.base.log.Logger;
import com.igexin.b.a.d.g;
import com.igexin.base.util.InvokeUtil;
import com.igexin.push.config.e;
import com.igexin.push.core.p;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a {
    private static Logger a;
    private static boolean b;
    private static final List<String> c;

    static {
        ArrayList arrayList = new ArrayList();
        c = arrayList;
        arrayList.add(g.h);
        c.add("ScheduleQueue");
        c.add("SilentTimeTimerTask");
        if (b) {
            return;
        }
        synchronized (a.class) {
            if (!b) {
                Context contextFindAppContext = p.b;
                boolean z = true;
                if (contextFindAppContext == null && (contextFindAppContext = InvokeUtil.findAppContext()) == null) {
                    z = false;
                } else {
                    Logger logger = new Logger(contextFindAppContext);
                    a = logger;
                    logger.setGlobalTag("gtsdk");
                    a.setLogcatEnable(false);
                    a.setLogFileNameSuffix("GTSDK");
                    a.setStackOffset(1);
                    a.setFileEnableProperty("sdk.debug");
                }
                b = z;
            }
        }
    }

    private static void a() {
    }

    private static void a(String str) {
        Logger logger = a;
        if (logger != null) {
            logger.d(str);
        }
    }

    private static void a(String str, String str2) {
        if (str == null || c.contains(str)) {
            return;
        }
        a.logcat(2, null, str2, null);
    }

    public static void a(String str, Object... objArr) {
        if (a != null) {
            if (objArr.length > 0) {
                str = String.format(str, objArr);
            }
            a.filelog(1, null, str, null);
        }
    }

    public static void a(boolean z) {
        e.a(Boolean.valueOf(z));
        Logger logger = a;
        if (logger != null) {
            logger.setLogcatEnable(false);
            a.setFileEnableProperty("sdk.debug");
        }
    }

    private static void b(String str, String str2) {
        if (str == null || c.contains(str)) {
            return;
        }
        a.logcat(3, null, str2, null);
    }

    private static boolean b() {
        Context contextFindAppContext = p.b;
        if (contextFindAppContext == null && (contextFindAppContext = InvokeUtil.findAppContext()) == null) {
            return false;
        }
        Logger logger = new Logger(contextFindAppContext);
        a = logger;
        logger.setGlobalTag("gtsdk");
        a.setLogcatEnable(false);
        a.setLogFileNameSuffix("GTSDK");
        a.setStackOffset(1);
        a.setFileEnableProperty("sdk.debug");
        return true;
    }

    private static void c(String str, String str2) {
        if (str == null || c.contains(str)) {
            return;
        }
        a.logcat(4, null, str2, null);
    }

    private static void d(String str, String str2) {
        if (str == null || c.contains(str)) {
            return;
        }
        a.logcat(5, null, str2, null);
    }
}
