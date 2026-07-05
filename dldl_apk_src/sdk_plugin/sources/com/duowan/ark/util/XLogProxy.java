package com.duowan.ark.util;

import android.os.Environment;
import android.os.Handler;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class XLogProxy {
    private static final int DELETE_SCHEDULE_DELAY = 5000;
    public static int MAX_FILE_SIZE = 4;
    private static final int MAX_ONCE_DELETE_FILE_COUNT = 10;
    private static final int NONSNAPSHOT_DELETE_FILE_THRESHOLD = 10;
    private static final int NONSNAPSHOT_MIN_KEEP_COUNT = 3;
    private static final int SNAPSHOT_DELETE_FILE_THRESHOLD = 30;
    private static final int SNAPSHOT_MIN_KEEP_COUNT = 20;
    private static int currentRenamedFileCount = 0;
    private static final int msMaxCount = 4000;
    public static String sLogPath = "/kiwi/logs";
    public static File sRootDir;
    public static String LOG_NAME = "logs";
    public static final String XLOG = ".xlog";
    public static String LOG_PATH = LOG_NAME + XLOG;
    public static String LAST_LOG_NAME = "logs-last";
    public static String LAST_LOG_PATH = LAST_LOG_NAME + XLOG;
    public static boolean sysLogEnabled = true;
    public static Handler mHandler = null;
    private static long msMaxWait = 3000;
    private static volatile String mCurrentLog = null;
    private static volatile boolean isFilesDeleting = false;

    XLogProxy() {
    }

    static /* synthetic */ int access$210() {
        int i = currentRenamedFileCount;
        currentRenamedFileCount = i - 1;
        return i;
    }

    static {
        Log.setLogImp(new Xlog());
        loadLibrary();
        Xlog.setConsoleLogOpen(false);
        List<File> renamedXLogFiles = LogProxy.getRenamedXLogFiles();
        currentRenamedFileCount = renamedXLogFiles != null ? renamedXLogFiles.size() : 0;
    }

    static void loadLibrary() {
        for (int i = 0; i < 3; i++) {
            try {
                System.loadLibrary("stlport_shared");
                System.loadLibrary("marsxlog");
                return;
            } catch (Exception e) {
                android.util.Log.e("XLogProxy", "loadLibrary error,", e);
            }
        }
    }

    static void logByLevel(int i, String str, String str2, String str3) {
        if (!str3.equals(mCurrentLog)) {
            switchOutputFile(str3);
        } else {
            renameFileIfNeed();
        }
        if (str.length() > 4000) {
            int i2 = 0;
            while (i2 < str.length()) {
                int length = (str.length() - i2 > 4000 ? 4000 : str.length() - i2) + i2;
                logByLevelReal(i, str.substring(i2, length), str2);
                i2 = length;
            }
            return;
        }
        logByLevelReal(i, str, str2);
    }

    static void logByLevelReal(int i, String str, String str2) {
        if (i == 2) {
            if (sysLogEnabled) {
                android.util.Log.v(str2, str);
                return;
            }
            return;
        }
        if (i == 3) {
            if (sysLogEnabled) {
                android.util.Log.d(str2, str);
            }
            Log.d(str2, str);
            return;
        }
        if (i == 4) {
            if (sysLogEnabled) {
                android.util.Log.i(str2, str);
            }
            Log.i(str2, str);
        } else if (i == 5) {
            if (sysLogEnabled) {
                android.util.Log.w(str2, str);
            }
            Log.w(str2, str);
        } else {
            if (i != 6) {
                return;
            }
            if (sysLogEnabled) {
                android.util.Log.e(str2, str);
            }
            Log.e(str2, str);
        }
    }

    private static void renameFileIfNeed() throws Throwable {
        File file = new File(getRootDir().getAbsolutePath() + sLogPath + File.separator + mCurrentLog + XLOG);
        if (!file.exists() || (file.length() >>> 20) < MAX_FILE_SIZE) {
            return;
        }
        renameReal(file);
        deleteOldFileIfNeed();
    }

    private static void renameReal(File file) throws Throwable {
        if (file.exists()) {
            String parent = file.getParent();
            String strReplace = file.getName().replace(XLOG, "");
            Log.appenderClose();
            File file2 = new File(parent + File.separator + strReplace + new SimpleDateFormat("-MM-dd-kk-mm-ss").format(new Date()) + XLOG);
            file.renameTo(file2);
            copyFile(file2.getAbsolutePath(), file2.getParent() + File.separator + LAST_LOG_PATH);
            StringBuilder sb = new StringBuilder();
            sb.append("appenderOpen renameReal filename ");
            sb.append(strReplace);
            android.util.Log.e("XLogProxy", sb.toString());
            Xlog.appenderOpen(1, 1, "", parent, strReplace);
            currentRenamedFileCount++;
        }
    }

    public static boolean copyFile(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                File file = new File(str);
                File file2 = new File(str2);
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Exception unused) {
                    fileOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                }
            } catch (Exception unused2) {
                return false;
            }
        } catch (Exception unused3) {
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception unused4) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream == null) {
                return false;
            }
            fileOutputStream.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Exception unused5) {
                    throw th;
                }
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private static void switchOutputFile(String str) {
        closeIfNeed();
        String str2 = getRootDir().getAbsolutePath() + sLogPath;
        File file = new File(str2);
        if (!file.exists() && !file.mkdirs()) {
            android.util.Log.e("XLogProxy", "make dir failed !!! dir " + str2);
        }
        android.util.Log.e("XLogProxy", "appenderOpen switchOutputFile file name " + str);
        Xlog.appenderOpen(1, 1, "", str2, str);
        mCurrentLog = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void closeIfNeed() {
        if (mCurrentLog != null) {
            Log.appenderClose();
            mCurrentLog = null;
        }
    }

    public static void flushToDisk() {
        if (mCurrentLog != null) {
            Log.appenderFlush(true);
        }
    }

    public static void close() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        mHandler.post(new Runnable() { // from class: com.duowan.ark.util.XLogProxy.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    XLogProxy.closeIfNeed();
                } catch (Throwable unused) {
                    XLogProxy.loadLibrary();
                    try {
                        XLogProxy.closeIfNeed();
                    } catch (Throwable th) {
                        android.util.Log.e("XLogProxy", "close error ", th);
                    }
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(msMaxWait, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            android.util.Log.e("XLogProxy", "close error ", e);
        }
        android.util.Log.e("XLogProxy", "close finish");
    }

    public static File getRootDir() {
        File file = sRootDir;
        return file == null ? Environment.getExternalStorageDirectory() : file;
    }

    private static void deleteOldFileIfNeed() {
        if (isFilesDeleting || !isOverThreshold()) {
            return;
        }
        final int i = LogProxy.sIsSnapshot ? 20 : 3;
        List<File> renamedXLogFiles = LogProxy.getRenamedXLogFiles();
        if (renamedXLogFiles == null || renamedXLogFiles.size() <= i) {
            return;
        }
        isFilesDeleting = true;
        mHandler.post(new Runnable() { // from class: com.duowan.ark.util.XLogProxy.2
            @Override // java.lang.Runnable
            public void run() {
                List<File> renamedXLogFiles2 = LogProxy.getRenamedXLogFiles();
                if (renamedXLogFiles2 == null) {
                    boolean unused = XLogProxy.isFilesDeleting = false;
                    return;
                }
                int unused2 = XLogProxy.currentRenamedFileCount = renamedXLogFiles2.size();
                if (XLogProxy.currentRenamedFileCount <= i) {
                    boolean unused3 = XLogProxy.isFilesDeleting = false;
                    return;
                }
                Collections.sort(renamedXLogFiles2, new LogFileComparator(0));
                if (XLogProxy.currentRenamedFileCount <= i + 10) {
                    deleteFiles(renamedXLogFiles2, XLogProxy.currentRenamedFileCount - i);
                    boolean unused4 = XLogProxy.isFilesDeleting = false;
                } else {
                    deleteFiles(renamedXLogFiles2, 10);
                    XLogProxy.mHandler.postDelayed(this, OAIDHelper.TIMEOUT);
                }
            }

            private void deleteFiles(List<File> list, int i2) {
                for (int i3 = 0; i3 < i2; i3++) {
                    if (list.get(i3).delete()) {
                        XLogProxy.access$210();
                    }
                }
            }
        });
    }

    private static boolean isOverThreshold() {
        return currentRenamedFileCount >= (LogProxy.sIsSnapshot ? 30 : 10);
    }
}
