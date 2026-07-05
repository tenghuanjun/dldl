package com.huya.component.crash;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.module.ArkModule;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.L;
import com.duowan.auk.util.LogToES;
import com.duowan.live.one.module.feedback.R;
import com.duowan.live.one.module.uploadLog.FeedBackHelper;
import com.duowan.live.one.util.LogUtils;
import com.duowan.live.one.util.NetworkUtil;
import com.duowan.live.one.util.ThreadPoolFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ExceptionModule extends ArkModule {
    private static final long CHECK_INTERVAL = 20000;
    private static final String COPY_ANR = "copyanr";
    private static final String REPORT_ANR = "reportanr";
    private static final String TAG = "ExceptionModule";
    private static final String UNCAUGHT_EXCEPTION = "UncaughtException";
    private Handler mExceptionHandler;
    private HandlerThread mExceptionThread = new HandlerThread(TAG);
    private Handler mMainHandler = new Handler(Looper.getMainLooper());
    private AtomicBoolean mIsChecking = new AtomicBoolean(false);
    private Runnable mCheckANR = new Runnable() { // from class: com.huya.component.crash.ExceptionModule.1
        @Override // java.lang.Runnable
        public void run() {
            ExceptionModule.this.mIsChecking.set(false);
        }
    };

    @Override // com.duowan.auk.module.ArkModule
    public void onStart() {
        super.onStart();
        this.mExceptionThread.start();
        this.mExceptionHandler = new Handler(this.mExceptionThread.getLooper());
        checkANR();
        reportCrashIfNeed();
        reportANRIfNeed();
    }

    @Override // com.duowan.auk.module.ArkModule
    public void onStop() {
        super.onStop();
    }

    private void reportANRIfNeed() {
        ThreadPoolFactory.run(new Runnable() { // from class: com.huya.component.crash.ExceptionModule.2
            @Override // java.lang.Runnable
            public void run() {
                boolean z = Config.getInstance(ArkValue.gContext).getBoolean(ExceptionModule.COPY_ANR, false);
                boolean z2 = Config.getInstance(ArkValue.gContext).getBoolean(ExceptionModule.REPORT_ANR, false);
                if (z) {
                    ExceptionModule.this.copySystemANRInfo();
                }
                if (z2) {
                    ExceptionModule.this.pSubmitWithLog("Application Not Responding");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkANR() {
        this.mExceptionHandler.postDelayed(new Runnable() { // from class: com.huya.component.crash.ExceptionModule.3
            @Override // java.lang.Runnable
            public void run() {
                if (ExceptionModule.this.mIsChecking.get()) {
                    ExceptionModule.this.writeStackTrace();
                    Config.getInstance(ArkValue.gContext).setBoolean(ExceptionModule.REPORT_ANR, true);
                    Config.getInstance(ArkValue.gContext).setBoolean(ExceptionModule.COPY_ANR, true);
                    return;
                }
                ExceptionModule.this.checkANR();
            }
        }, CHECK_INTERVAL);
        this.mIsChecking.set(true);
        this.mMainHandler.post(this.mCheckANR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeStackTrace() {
        L.info(TAG, "writeStackTrace");
        StackTraceElement[] stackTraceElementArr = Thread.getAllStackTraces().get(Looper.getMainLooper().getThread());
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        if (stackTraceElementArr == null) {
            return;
        }
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            sb.append(stackTraceElement.toString().toCharArray());
            sb.append('\n');
        }
        try {
            File file = new File(L.sLogPath + File.separator + LogUtils.ANR_STACKTRACE_FILENAME);
            if (file.exists()) {
                file.delete();
            }
            LogToES.writeLogToFileReal(L.sLogPath, LogUtils.ANR_STACKTRACE_FILENAME, sb.toString());
        } catch (IOException e) {
            L.error(this, e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copySystemANRInfo() {
        Config.getInstance(ArkValue.gContext).setBoolean(COPY_ANR, false);
        File file = new File("/data/anr/traces.txt");
        if (!file.exists()) {
            return;
        }
        try {
            File file2 = new File(LogUtils.getLogsDir() + File.separator + LogUtils.SYSTEM_ANR_TRACE);
            if (file2.exists()) {
                file2.delete();
            }
            if (!file2.createNewFile()) {
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (-1 == i) {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (IOException e) {
            L.error(this, e.toString());
        }
    }

    private String getExceptionName(String str) {
        if (TextUtils.isEmpty(str)) {
            return "unknown exception";
        }
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            iIndexOf = str.indexOf(10);
        }
        if (iIndexOf == -1 || iIndexOf > 100) {
            return str.substring(0, 100);
        }
        return str.substring(0, iIndexOf);
    }

    private void reportCrashIfNeed() {
        Config config = Config.getInstance(ArkValue.gContext);
        String string = config.getString(UNCAUGHT_EXCEPTION, "");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        if (!ArkValue.debuggable()) {
            pSubmitWithLog(getExceptionName(string));
        }
        config.setString(UNCAUGHT_EXCEPTION, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pSubmitWithLog(String str) {
        if (NetworkUtil.isWifiActive(ArkValue.gContext)) {
            Config.getInstance(ArkValue.gContext).setBoolean(REPORT_ANR, false);
            FeedBackHelper.sendFeedback(ArkValue.gContext.getResources().getString(R.string.feedback_prefix), str);
        }
    }
}
