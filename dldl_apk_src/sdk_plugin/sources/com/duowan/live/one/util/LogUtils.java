package com.duowan.live.one.util;

import android.os.Environment;
import android.text.TextUtils;
import com.duowan.ark.util.XLogProxy;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.mtp.utils.ResourceUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LogUtils {
    public static final String ANR_STACKTRACE_FILENAME = "anr-stacktrace.txt";
    public static final String HY_SDK_MEDIA = "hysdkmedia-trans.txt";
    public static final String HY_TAFMGR = "hytafmgr.txt";
    public static final String LINK_HARD_DECODE_LOG = "ycmedia-app.txt";
    public static final String LOGEX = "logex.txt";
    public static final String[] LOGS_END_NAME;
    public static final String[] LOGS_FILENAME;
    public static final String LOG_ZIP_FILE_NAME = "logsZip_%s.zip";
    public static final String MAIN_XLOG = "logs_";
    public static final String MARS_LOGS = "mars-";
    public static final String MARS_XLOG = "mars.xlog";
    public static final String MEDIA_SDK_LOG_FILENAME = "mediaSdk-trans.txt";
    public static final String OLD_LOGS = "logs.txt";
    public static final String PROCESS_XLOG = "logs-";
    public static final String PUSH_SDK_LOG_FILENAME = "pushsvc_log.txt";
    public static final String REACT_LOG = "react_log.txt";
    public static final String REACT_LOGS = "react_log-";
    public static final String SYSTEM_ANR_TRACE = "system-anr-stacktrace.txt";
    public static final String TAG = "LogUtils";
    public static final String YYPLAYER_LOG_FILENAME = "yyplayer_core.txt";
    public static final String YYSDK_LOG_FILENAME;

    static {
        String str = "yysdk-" + ResourceUtils.getMetaValue(ArkValue.gContext, "YY_TOKEN") + ".txt";
        YYSDK_LOG_FILENAME = str;
        LOGS_FILENAME = new String[]{"uncaught_exception.txt", L.LOG_NAME, "logs.txt", MAIN_XLOG, PROCESS_XLOG, str, MEDIA_SDK_LOG_FILENAME, ANR_STACKTRACE_FILENAME, SYSTEM_ANR_TRACE, PUSH_SDK_LOG_FILENAME, YYPLAYER_LOG_FILENAME, HY_SDK_MEDIA, HY_TAFMGR, LOGEX, MARS_XLOG, MARS_LOGS, LINK_HARD_DECODE_LOG, REACT_LOG, REACT_LOGS};
        LOGS_END_NAME = new String[]{".bak", XLogProxy.XLOG};
    }

    public static String getLogsDir() {
        return Environment.getExternalStorageDirectory().getPath() + L.sLogPath;
    }

    public static String getCompressFileName(String str) {
        return getLogsDir() + File.separator + String.format(LOG_ZIP_FILE_NAME, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0101 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x011d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.File compressFile(java.util.List<java.io.File> r10, java.lang.String r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.one.util.LogUtils.compressFile(java.util.List, java.lang.String):java.io.File");
    }

    public static File getLogByTime(String str, long j, long j2, boolean z) {
        try {
            if (!TextUtils.isEmpty(str)) {
                String compressFileName = getCompressFileName(str);
                L.verbose(TAG, "getLogByTime logBeginTime %d ; logEndTime%d isReUpload:%s mFbId：%s,zipPath %s", Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(z), str, compressFileName);
                File file = new File(compressFileName);
                if (file.exists()) {
                    return file;
                }
                if (z) {
                    L.verbose("feedback", "file %s not exists,when reUpload ... ", compressFileName);
                    return null;
                }
            }
            return compressFile(getFullLogNamesByTime(LOGS_FILENAME, j, j2), str);
        } catch (Exception e) {
            L.error(TAG, "compress logs file error = " + e);
            return null;
        }
    }

    public static List<File> getFullLogNamesByTime(String[] strArr, long j, long j2) {
        L.info(TAG, "logBeginTime = " + j + " logEndTime = " + j2);
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append(getLogsDir());
        sb.append("/%s");
        String string = sb.toString();
        for (String str : strArr) {
            File file = new File(String.format(string, str));
            if (file.exists() && file.length() > 0) {
                L.debug(TAG, "list File name = %s", file.getName());
                arrayList.add(file);
            }
        }
        if (j > j2) {
            return arrayList;
        }
        List<File> renamedLogFiles = getRenamedLogFiles(strArr);
        if (renamedLogFiles == null || renamedLogFiles.size() <= 0) {
            L.info(TAG, "getRenamedLogFiles is null or size is 0");
            return arrayList;
        }
        for (File file2 : renamedLogFiles) {
            L.debug(TAG, "list File name = " + file2.getName() + " lastModified = " + file2.lastModified());
            if (file2.exists() && file2.lastModified() >= j && !arrayList.contains(file2)) {
                arrayList.add(file2);
            }
        }
        return arrayList;
    }

    public static List<File> getRenamedLogFiles(String[] strArr) {
        return getFiles(getLogsDir(), new LogsFilenameFilter().setStartLabel(strArr).setEndLabel(LOGS_END_NAME));
    }

    private static List<File> getFiles(String str, FilenameFilter filenameFilter) {
        File[] fileArrListFiles = new File(str).listFiles(filenameFilter);
        if (fileArrListFiles != null) {
            return Arrays.asList(fileArrListFiles);
        }
        return null;
    }

    public static class LogsFilenameFilter implements FilenameFilter {
        private String[] startLabels = null;
        private String[] endLabel = null;

        public LogsFilenameFilter setStartLabel(String[] strArr) {
            this.startLabels = strArr;
            return this;
        }

        public LogsFilenameFilter setEndLabel(String[] strArr) {
            this.endLabel = strArr;
            return this;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            boolean z;
            String[] strArr = this.startLabels;
            boolean z2 = true;
            if (strArr != null) {
                for (String str2 : strArr) {
                    if (str2 != null && str2.length() > 0 && str.startsWith(str2)) {
                        z = true;
                        break;
                    }
                }
                z = false;
            } else {
                z = false;
            }
            String[] strArr2 = this.endLabel;
            if (strArr2 != null) {
                for (String str3 : strArr2) {
                    if (str3 != null && str3.length() > 0 && str.endsWith(str3)) {
                        break;
                    }
                }
                z2 = false;
            } else {
                z2 = false;
            }
            return z & z2;
        }
    }
}
