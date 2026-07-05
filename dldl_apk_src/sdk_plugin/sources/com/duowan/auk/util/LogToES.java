package com.duowan.auk.util;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LogToES {
    private static final long DAY_DELAY = 432000000;
    private static final SimpleDateFormat FILE_NAME_FORMAT;
    private static final String[] LOGCAT_CMD;
    private static final SimpleDateFormat LOG_FORMAT = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss.SSS");
    public static final String LOG_NAME = "logs.txt";
    public static final int MAX_BUFFER_COUNT = 15;
    public static final int MAX_FILE_SIZE = 2;
    public static final int MAX_LOG_MSG_QUEUE_SIZE = 1000;
    public static final String NATIVE_BAK_LOG_NAME = "native_crash.bak";
    public static final String NATIVE_LOG_NAME = "native_crash.txt";
    public static final String UE_LOG_NAME = "uncaught_exception.txt";
    public static ConcurrentLinkedQueue<LogMsg> logMsgQueue = null;
    public static Runnable logRunnable = null;
    private static final Handler mLogHandler;
    public static AtomicInteger sLogMsgQueueSize = null;
    public static String sLogPath = ".auk.logs";
    public static File sRootDir;

    public static class LogMsg {
        public Date date;
        public String fileName;
        public String msg;
        public String path;
    }

    static {
        HandlerThread handlerThread = new HandlerThread("GlobalStartupThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        mLogHandler = handler;
        handler.post(new Runnable() { // from class: com.duowan.auk.util.LogToES.1
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
            }
        });
        LogFileWriter.mBufferCount = 15;
        logMsgQueue = new ConcurrentLinkedQueue<>();
        sLogMsgQueueSize = new AtomicInteger(0);
        logRunnable = null;
        FILE_NAME_FORMAT = new SimpleDateFormat("MM-dd_HH-mm-ss");
        LOGCAT_CMD = new String[]{"logcat", "-d", "-v", "time"};
    }

    public static void writeLogToFile(String str, String str2, String str3) {
        LogMsg logMsg = new LogMsg();
        logMsg.path = str;
        logMsg.fileName = str2;
        logMsg.msg = str3;
        logMsg.date = new Date();
        if (sLogMsgQueueSize.get() > 1000) {
            return;
        }
        logMsgQueue.add(logMsg);
        sLogMsgQueueSize.incrementAndGet();
        if (logRunnable == null) {
            Runnable runnable = new Runnable() { // from class: com.duowan.auk.util.LogToES.2
                @Override // java.lang.Runnable
                public void run() {
                    LogMsg logMsgPoll = LogToES.logMsgQueue.poll();
                    if (logMsgPoll != null) {
                        LogToES.sLogMsgQueueSize.decrementAndGet();
                    }
                    while (logMsgPoll != null) {
                        try {
                            LogToES.writeLogToFileReal(logMsgPoll.path, logMsgPoll.fileName, logMsgPoll.msg, logMsgPoll.date, false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        logMsgPoll = LogToES.logMsgQueue.poll();
                        if (logMsgPoll != null) {
                            LogToES.sLogMsgQueueSize.decrementAndGet();
                        }
                    }
                    LogToES.logRunnable = null;
                }
            };
            logRunnable = runnable;
            mLogHandler.post(runnable);
        }
    }

    public static synchronized void writeLogToFileReal(String str, String str2, String str3) throws IOException {
        writeLogToFileReal(str, str2, str3, new Date(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void writeLogToFileReal(String str, String str2, String str3, Date date, boolean z) throws IOException {
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

    public static void writeAllLogsToFile() {
        new Thread(new Runnable() { // from class: com.duowan.auk.util.LogToES.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Date date = new Date();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(LogToES.LOGCAT_CMD).getInputStream()), 1024);
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                        sb.append(System.getProperty("line.separator"));
                    }
                    bufferedReader.close();
                    Log.i("yy", "all logs: " + sb.toString());
                    String str = LogToES.getRootDir().getAbsolutePath() + LogToES.sLogPath;
                    File file = new File(str);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(str + File.separator + LogToES.FILE_NAME_FORMAT.format(date) + ".log");
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    fileOutputStream.write(sb.toString().getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("yy", "writeAllLogsToFile " + e.toString());
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File getRootDir() {
        File file = sRootDir;
        return file == null ? Environment.getExternalStorageDirectory() : file;
    }

    public static synchronized void flushToDisk() {
        try {
            LogFileWriter.flushAll();
        } catch (Exception e) {
            L.error(LogToES.class.getName(), (Throwable) e);
        }
    }
}
