package com.sqnetwork.voly;

import android.os.SystemClock;
import android.util.Log;
import com.sq.tool.logger.Printer;
import com.sq.tool.logger.SQLog;
import com.sqwan.bugless.util.FileUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VolleyLog {
    public static String TAG = "Voly";
    private static Printer sPrinter;
    public static boolean DEBUG = Log.isLoggable("sysdk.debug.log.http", 3);
    public static boolean VERBOSE = Log.isLoggable("sysdk.debug.log.http", 2);
    public static boolean CALLER = Log.isLoggable("sysdk.debug.log.caller", 3);
    private static final String CLASS_NAME = VolleyLog.class.getName();

    private static Printer printer() {
        if (sPrinter == null) {
            sPrinter = SQLog.m("【Http】");
        }
        return sPrinter;
    }

    public static void setTag(String tag) {
        d("Changing log tag to %s", tag);
        TAG = tag;
        DEBUG = Log.isLoggable(tag, 2);
    }

    public static void v(String format, Object... args) {
        if (DEBUG) {
            printer().v(buildMessage(format, args));
        }
    }

    public static void d(String format, Object... args) {
        if (DEBUG) {
            printer().d(buildMessage(format, args));
        }
    }

    public static void i(String format, Object... args) {
        printer().i(buildMessage(format, args));
    }

    public static void w(String format, Object... args) {
        printer().w(buildMessage(format, args));
    }

    public static void w(Throwable tr, String format, Object... args) {
        printer().w(buildMessage(format, args), tr);
    }

    public static void e(String format, Object... args) {
        printer().e(buildMessage(format, args));
    }

    public static void e(Throwable tr, String format, Object... args) {
        printer().e(buildMessage(format, args), tr);
    }

    public static void wtf(String format, Object... args) {
        printer().wtf(buildMessage(format, args), new Object[0]);
    }

    public static void wtf(Throwable tr, String format, Object... args) {
        printer().wtf(buildMessage(format, args), tr);
    }

    private static String buildMessage(String format, Object... args) {
        String str;
        if (args != null) {
            format = String.format(Locale.US, format, args);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        if (!CALLER) {
            return String.format(Locale.US, "[%d] %s", Long.valueOf(Thread.currentThread().getId()), format);
        }
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str = "<unknown>";
                break;
            }
            if (!stackTrace[i].getClassName().equals(CLASS_NAME)) {
                String className = stackTrace[i].getClassName();
                String strSubstring = className.substring(className.lastIndexOf(46) + 1);
                str = strSubstring.substring(strSubstring.lastIndexOf(36) + 1) + FileUtil.FILE_EXTENSION_SEPARATOR + stackTrace[i].getMethodName();
                break;
            }
            i++;
        }
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str, format);
    }

    static class MarkerLog {
        public static final boolean ENABLED = VolleyLog.DEBUG;
        private static final long MIN_DURATION_FOR_LOGGING_MS = 0;
        private final List<Marker> mMarkers = new ArrayList();
        private boolean mFinished = false;

        MarkerLog() {
        }

        private static class Marker {
            public final String name;
            public final long thread;
            public final long time;

            public Marker(String name, long thread, long time) {
                this.name = name;
                this.thread = thread;
                this.time = time;
            }
        }

        public synchronized void add(String name, long threadId) {
            if (this.mFinished) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.mMarkers.add(new Marker(name, threadId, SystemClock.elapsedRealtime()));
        }

        public synchronized void finish(String header) {
            this.mFinished = true;
            long totalDuration = getTotalDuration();
            if (totalDuration > 0 && VolleyLog.VERBOSE) {
                long j = this.mMarkers.get(0).time;
                VolleyLog.d("(%-4d ms) %s", Long.valueOf(totalDuration), header);
                for (Marker marker : this.mMarkers) {
                    long j2 = marker.time;
                    VolleyLog.d("(+%-4d) [%2d] %s", Long.valueOf(j2 - j), Long.valueOf(marker.thread), marker.name);
                    j = j2;
                }
            }
        }

        protected void finalize() throws Throwable {
            if (this.mFinished) {
                return;
            }
            finish("Request on the loose");
            VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }

        private long getTotalDuration() {
            if (this.mMarkers.size() == 0) {
                return 0L;
            }
            return this.mMarkers.get(r2.size() - 1).time - this.mMarkers.get(0).time;
        }
    }
}
