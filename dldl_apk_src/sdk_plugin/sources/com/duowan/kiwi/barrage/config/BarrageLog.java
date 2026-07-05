package com.duowan.kiwi.barrage.config;

import android.os.Process;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageLog {
    private static IBarrageLog sBarrageLogProxy = new DefaultLog();

    public interface IBarrageLog {
        void debug(String str, String str2);

        void debug(String str, String str2, Object... objArr);

        void error(String str, String str2);

        void error(String str, String str2, Throwable th);

        void error(String str, String str2, Object... objArr);

        void info(String str, String str2);

        void info(String str, String str2, Object... objArr);
    }

    public static void setBarrageLog(IBarrageLog iBarrageLog) {
        if (iBarrageLog != null) {
            sBarrageLogProxy = iBarrageLog;
        }
    }

    public static void debug(String str, String str2) {
        sBarrageLogProxy.debug(str, str2);
    }

    public static void debug(String str, String str2, Object... objArr) {
        sBarrageLogProxy.debug(str, str2, objArr);
    }

    public static void info(String str, String str2) {
        sBarrageLogProxy.info(str, str2);
    }

    public static void info(String str, String str2, Object... objArr) {
        sBarrageLogProxy.info(str, str2, objArr);
    }

    public static void error(String str, String str2) {
        sBarrageLogProxy.error(str, str2);
    }

    public static void error(String str, String str2, Object... objArr) {
        sBarrageLogProxy.error(str, str2, objArr);
    }

    public static void error(String str, String str2, Throwable th) {
        sBarrageLogProxy.error(str, str2, th);
    }

    public static class DefaultLog implements IBarrageLog {
        private static int sPid;

        @Override // com.duowan.kiwi.barrage.config.BarrageLog.IBarrageLog
        public void debug(String str, String str2) {
            doLog(3, str, str2, null, true);
        }

        @Override // com.duowan.kiwi.barrage.config.BarrageLog.IBarrageLog
        public void debug(String str, String str2, Object... objArr) {
            doLog(3, str, String.format(str2, objArr), null, true);
        }

        @Override // com.duowan.kiwi.barrage.config.BarrageLog.IBarrageLog
        public void info(String str, String str2) {
            doLog(4, str, str2, null, true);
        }

        @Override // com.duowan.kiwi.barrage.config.BarrageLog.IBarrageLog
        public void info(String str, String str2, Object... objArr) {
            doLog(4, str, String.format(str2, objArr), null, true);
        }

        @Override // com.duowan.kiwi.barrage.config.BarrageLog.IBarrageLog
        public void error(String str, String str2) {
            doLog(6, str, str2, null, true);
        }

        @Override // com.duowan.kiwi.barrage.config.BarrageLog.IBarrageLog
        public void error(String str, String str2, Object... objArr) {
            doLog(6, str, String.format(str2, objArr), null, true);
        }

        @Override // com.duowan.kiwi.barrage.config.BarrageLog.IBarrageLog
        public void error(String str, String str2, Throwable th) {
            doLog(6, str, str2, th, true);
        }

        private static void doLog(int i, String str, String str2, Throwable th, boolean z) {
            String logInfo;
            if (z) {
                logInfo = getLogInfo(str, str2, th);
            } else {
                logInfo = getLogInfo(str, str2, th, false);
            }
            logByLevelReal(i, logInfo, str);
        }

        static void logByLevelReal(int i, String str, String str2) {
            if (i == 2) {
                Log.v(str2, str);
                return;
            }
            if (i == 3) {
                Log.d(str2, str);
                return;
            }
            if (i == 4) {
                Log.i(str2, str);
            } else if (i == 5) {
                Log.w(str2, str);
            } else {
                if (i != 6) {
                    return;
                }
                Log.e(str2, str);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static java.lang.String getLogInfo(java.lang.String r8, java.lang.String r9, java.lang.Throwable r10, boolean r11) {
            /*
                if (r11 == 0) goto L20
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                java.lang.StackTraceElement[] r0 = r0.getStackTrace()
                r1 = 0
                if (r0 == 0) goto L13
                int r2 = r0.length
                r3 = 5
                if (r2 <= r3) goto L13
                r1 = r0[r3]
            L13:
                if (r1 == 0) goto L20
                java.lang.String r0 = r1.getFileName()
                int r1 = r1.getLineNumber()
                r3 = r0
                r4 = r1
                goto L25
            L20:
                java.lang.String r0 = ""
                r1 = 0
                r3 = r0
                r4 = 0
            L25:
                r2 = r8
                r5 = r9
                r6 = r10
                r7 = r11
                java.lang.String r8 = msgForTextLog(r2, r3, r4, r5, r6, r7)
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.duowan.kiwi.barrage.config.BarrageLog.DefaultLog.getLogInfo(java.lang.String, java.lang.String, java.lang.Throwable, boolean):java.lang.String");
        }

        private static String msgForTextLog(String str, String str2, int i, String str3, Throwable th, boolean z) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(str3);
            if (sPid == 0) {
                sPid = Process.myPid();
            }
            sb.append("(P:");
            sb.append(sPid);
            sb.append(")");
            sb.append("(T:");
            sb.append(Thread.currentThread().getName());
            sb.append("-");
            sb.append(Process.myTid());
            sb.append(")");
            sb.append("(C:");
            sb.append(str);
            sb.append(")");
            if (z) {
                sb.append("at (");
                sb.append(str2);
                sb.append(":");
                sb.append(i);
                sb.append(")");
            }
            if (th != null) {
                sb.append('\n');
                sb.append(Log.getStackTraceString(th));
            }
            return sb.toString();
        }

        private static String getLogInfo(String str, String str2, Throwable th) {
            if (sPid == 0) {
                sPid = Process.myPid();
            }
            StringBuilder sb = new StringBuilder(64);
            sb.append(str2);
            sb.append("(P:");
            sb.append(sPid);
            sb.append(")");
            sb.append("(T:");
            sb.append(Thread.currentThread().getName());
            sb.append("-");
            sb.append(Process.myTid());
            sb.append(")");
            sb.append("(C:");
            sb.append(objClassName(str));
            sb.append(")");
            if (th != null) {
                sb.append('\n');
                sb.append(Log.getStackTraceString(th));
            }
            return sb.toString();
        }

        private static String objClassName(Object obj) {
            if (obj == null) {
                return "Global";
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj instanceof Class) {
                return ((Class) obj).getSimpleName();
            }
            return obj.getClass().getSimpleName();
        }
    }
}
