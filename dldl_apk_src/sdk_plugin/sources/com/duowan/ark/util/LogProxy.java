package com.duowan.ark.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import com.duowan.live.one.util.LogUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LogProxy {
    private static final Handler mLogHandler;
    private static Handler sDispatcherHandler;
    public static boolean sIsSnapshot;
    private static Logger mLog = new XLogger();
    private static Queue<LogInfo> sLogCacheQueue = new LinkedList();

    interface Logger {
        void logByLevel(int i, String str, String str2, String str3);
    }

    public static String getFullUELogName() {
        return "uncaught_exception.txt";
    }

    public static String getUELogName() {
        return "uncaught_exception.txt";
    }

    static {
        HandlerThread handlerThread = new HandlerThread("KLogThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        mLogHandler = handler;
        handler.post(new Runnable() { // from class: com.duowan.ark.util.LogProxy.1
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
            }
        });
        LogToES.mLogHandler = mLogHandler;
        XLogProxy.mHandler = mLogHandler;
        sDispatcherHandler = mLogHandler;
    }

    public static void init(boolean z, String str) {
        if (z) {
            return;
        }
        if (str.contains(":")) {
            str = str.substring(str.indexOf(":") + 1);
        }
        resetLogName(str + "-logs");
    }

    public static Handler getDispatcher() {
        return sDispatcherHandler;
    }

    public static void offer(LogInfo logInfo) {
        sLogCacheQueue.offer(logInfo);
    }

    public static LogInfo poll() {
        return sLogCacheQueue.poll();
    }

    public static boolean isCacheEmpty() {
        return sLogCacheQueue.isEmpty();
    }

    public static void resetRoot(File file) {
        XLogProxy.sRootDir = file;
        LogToES.sRootDir = file;
    }

    public static void resetLogPath(String str) {
        XLogProxy.sLogPath = str;
        LogToES.sLogPath = str;
    }

    public static void resetLogName(String str) {
        if (str != null) {
            XLogProxy.LOG_NAME = str;
        }
    }

    public static File getRoot() {
        return XLogProxy.sRootDir;
    }

    public static void flushToDisk() {
        XLogProxy.flushToDisk();
    }

    public static String getLogPath() {
        return XLogProxy.sLogPath;
    }

    public static String getFullLogName() {
        return XLogProxy.LOG_PATH;
    }

    public static String getLastLogName() {
        return XLogProxy.LAST_LOG_PATH;
    }

    public static String[] getFullLogNames(int i) {
        String str = XLogProxy.LOG_NAME + XLogProxy.XLOG;
        int i2 = 0;
        if (i <= 1) {
            return new String[]{str};
        }
        List<File> renamedLogFiles = getRenamedLogFiles();
        if (renamedLogFiles == null || renamedLogFiles.size() <= 0) {
            return new String[]{str};
        }
        int size = renamedLogFiles.size();
        Collections.sort(renamedLogFiles, new LogFileComparator(1));
        int i3 = i - 1;
        if (i3 < size) {
            size = i3;
        }
        String[] strArr = new String[size + 1];
        strArr[0] = str;
        while (i2 < size) {
            int i4 = i2 + 1;
            strArr[i4] = renamedLogFiles.get(i2).getName();
            i2 = i4;
        }
        return strArr;
    }

    public static String[] getFullLogNamesByTime(long j, long j2) {
        String str = XLogProxy.LOG_NAME + XLogProxy.XLOG;
        int i = 0;
        if (j > j2) {
            return new String[]{str};
        }
        List<File> renamedLogFiles = getRenamedLogFiles();
        if (renamedLogFiles == null || renamedLogFiles.size() <= 0) {
            return new String[]{str};
        }
        ArrayList arrayList = new ArrayList();
        for (File file : renamedLogFiles) {
            if (file.lastModified() >= j && file.lastModified() <= j2) {
                arrayList.add(file);
            }
        }
        int size = arrayList.size();
        String[] strArr = new String[size + 1];
        strArr[0] = str;
        while (i < size) {
            int i2 = i + 1;
            strArr[i2] = ((File) arrayList.get(i)).getName();
            i = i2;
        }
        return strArr;
    }

    public static List<File> getRenamedLogFiles() {
        return getRenamedXLogFiles();
    }

    public static List<File> getRenamedXLogFiles() {
        return getFiles(XLogProxy.getRootDir().getAbsolutePath() + XLogProxy.sLogPath, new RenamedLogFilenameFilter().setStartLable(LogUtils.PROCESS_XLOG).setEndLable(XLogProxy.XLOG).setLength(24));
    }

    public static List<File> getRenamedNonXLogFiles() {
        return getFiles(LogToES.getRootDir().getAbsolutePath() + LogToES.sLogPath, new RenamedLogFilenameFilter().setStartLable("logs.txt-").setEndLable(".bak").setLength(27));
    }

    private static List<File> getFiles(String str, FilenameFilter filenameFilter) {
        File[] fileArrListFiles = new File(str).listFiles(filenameFilter);
        if (fileArrListFiles != null) {
            return Arrays.asList(fileArrListFiles);
        }
        return null;
    }

    public static String getLogName() {
        return XLogProxy.LOG_NAME;
    }

    public static void setSysLogEnabled(boolean z) {
        XLogProxy.sysLogEnabled = z;
    }

    public static void close() {
        XLogProxy.close();
    }

    static void logByLevel(int i, String str, String str2, String str3) {
        Logger logger = mLog;
        if (logger != null) {
            logger.logByLevel(i, str, str2, str3);
        }
    }

    static void uncaughtException(String str, String str2, String str3) throws IOException {
        LogToES.writeLogToFileReal(LogToES.sLogPath, str3, str2 + str);
    }

    static class XLogger implements Logger {
        XLogger() {
        }

        @Override // com.duowan.ark.util.LogProxy.Logger
        public void logByLevel(int i, String str, String str2, String str3) {
            XLogProxy.logByLevel(i, str, str2, str3);
        }
    }
}
