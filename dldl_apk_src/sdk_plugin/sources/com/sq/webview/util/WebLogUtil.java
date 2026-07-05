package com.sq.webview.util;

import android.util.Log;
import com.sq.tool.logger.Printer;
import com.sq.tool.logger.SQLog;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebLogUtil {
    public static boolean sLogEnable = Log.isLoggable("sysdk.debug.log.webview", 3);
    private static Printer sPrinter;

    private static Printer printer() {
        if (sPrinter == null) {
            sPrinter = SQLog.m("【WEB_VIEW】");
        }
        return sPrinter;
    }

    public static void v(String msg) {
        if (sLogEnable) {
            printer().v(msg);
        }
    }

    public static void d(String msg) {
        if (sLogEnable) {
            printer().d(msg);
        }
    }

    public static void i(String msg) {
        if (sLogEnable) {
            printer().i(msg);
        }
    }

    public static void w(String msg) {
        if (sLogEnable) {
            printer().w(msg);
        }
    }

    public static void e(String msg) {
        if (sLogEnable) {
            printer().e(msg);
        }
    }

    public static void e(String msg, Throwable throwable) {
        if (sLogEnable) {
            printer().e(msg, throwable);
        }
    }

    public static void v(String tag, String msg) {
        if (sLogEnable) {
            SQLog.v(tag + msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sLogEnable) {
            SQLog.d(tag + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sLogEnable) {
            SQLog.i(tag + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sLogEnable) {
            SQLog.w(tag + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sLogEnable) {
            SQLog.e(tag + msg);
        }
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (sLogEnable) {
            SQLog.e(tag + msg, throwable);
        }
    }

    public static void v(String format, Object... args) {
        if (sLogEnable) {
            printer().v(format, args);
        }
    }

    public static void i(String format, Object... args) {
        if (sLogEnable) {
            printer().i(format, args);
        }
    }

    public static void w(String format, Object... args) {
        if (sLogEnable) {
            printer().w(format, args);
        }
    }

    public static void e(Throwable tr, String format, Object... args) {
        if (sLogEnable) {
            if (args != null) {
                format = String.format(Locale.US, format, args);
            }
            printer().e(format, tr);
        }
    }

    public static void wtf(String format, Object... args) {
        if (sLogEnable) {
            printer().wtf(format, args);
        }
    }
}
