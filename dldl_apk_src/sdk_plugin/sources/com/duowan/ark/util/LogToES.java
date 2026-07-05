package com.duowan.ark.util;

import android.os.Environment;
import android.os.Handler;
import java.io.File;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
@Deprecated
class LogToES {
    private static final long DAY_DELAY = 432000000;
    private static final SimpleDateFormat LOG_FORMAT = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss.SSS");
    public static final int MAX_FILE_SIZE = 2;
    public static final String UE_LOG_NAME = "uncaught_exception.txt";
    public static Handler mLogHandler = null;
    public static String sLogPath = "/kiwi/logs";
    public static File sRootDir;

    LogToES() {
    }

    public static synchronized void writeLogToFileReal(String str, String str2, String str3) throws IOException {
        writeLogToFileReal(str, str2, str3, new Date(), true);
    }

    private static synchronized void writeLogToFileReal(String str, String str2, String str3, Date date, boolean z) throws IOException {
        String str4 = getRootDir().getPath() + str;
        File file = new File(str4);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str4 + File.separator + str2);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else if ((file2.length() >>> 20) > 2) {
            deleteOldLogs();
            file2.renameTo(new File(str4 + File.separator + str2 + new SimpleDateFormat("-MM-dd-kk-mm-ss").format(date) + ".bak"));
        }
        StringBuffer stringBuffer = new StringBuffer();
        LOG_FORMAT.format(date, stringBuffer, new FieldPosition(0));
        stringBuffer.append(' ');
        stringBuffer.append(str3);
        stringBuffer.append('\n');
        LogFileWriter logFileWriter = new LogFileWriter(file2.getAbsolutePath());
        logFileWriter.write(stringBuffer);
        if (z) {
            logFileWriter.flush();
        }
    }

    private static void deleteOldLogs() {
        File rootDir = getRootDir();
        if (rootDir.exists()) {
            File file = new File(rootDir.getAbsolutePath() + sLogPath);
            if (file.exists()) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        if (file2.getName().endsWith(".bak") && jCurrentTimeMillis - file2.lastModified() > DAY_DELAY) {
                            file2.delete();
                        }
                    }
                }
            }
        }
    }

    public static File getRootDir() {
        File file = sRootDir;
        return file == null ? Environment.getExternalStorageDirectory() : file;
    }
}
