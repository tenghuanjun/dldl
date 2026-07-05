package com.huya.component.crash;

import android.os.Process;
import com.duowan.auk.ArkValue;
import com.duowan.auk.helper.FileStorage;
import com.duowan.auk.util.L;
import com.huya.live.utils.DigitUtil;
import com.huya.mtp.utils.FileUtils;
import com.sqwan.bugless.util.DateUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final int CRASH_LIMIT = 5;
    private static final String KEY_CRASH_COUNT = "crash_count";
    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    public void init() {
        this.mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) throws Throwable {
        notifyJavaCrash();
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultCrashHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    public void notifyJavaCrash() throws Throwable {
        setCrashCount(getCrashCount() + 1);
    }

    public void notifyNaviteCrash() throws Throwable {
        setCrashCount(getCrashCount() + 1);
    }

    private static String todayKey() {
        return new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE).format(new Date());
    }

    public static int getCrashCount() {
        String strTrim = FileUtils.getTxtFileContent(ArkValue.gContext, getConfigFile(todayKey())).trim();
        L.debug("getCrashCount " + strTrim);
        return DigitUtil.parseInt(strTrim, 0);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0041 -> B:27:0x0044). Please report as a decompilation issue!!! */
    public static void setCrashCount(int i) throws Throwable {
        FileOutputStream fileOutputStream;
        String strValueOf = String.valueOf(i);
        String configFile = getConfigFile(todayKey());
        new File(configFile).getParentFile().mkdirs();
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(new File(configFile));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e) {
                e = e;
            }
            try {
                fileOutputStream.write(strValueOf.getBytes());
                fileOutputStream.close();
            } catch (Exception e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    private static String getConfigFile(String str) {
        File rootDir = FileStorage.getInstance().getRootDir(FileStorage.Location.SDCard);
        return (rootDir != null ? rootDir.getAbsolutePath() : "") + "/temp/crash/" + str;
    }
}
