package com.getui.gtc.base.log;

import android.content.Context;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.log.a.a;
import com.getui.gtc.base.log.d.b;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class Logger {
    public static final int DEBUG = 2;
    public static final int ERROR = 5;
    public static final int INFO = 3;
    public static final int VERBOSE = 1;
    public static final int WARN = 4;
    private a fileLogController;
    private com.getui.gtc.base.log.b.a fileLogDestnation;
    private com.getui.gtc.base.log.c.a fileLogFormatter;
    private com.getui.gtc.base.log.d.a logPrinter = new b();
    private com.getui.gtc.base.log.b.b logcatLogDestnation = new com.getui.gtc.base.log.b.b();
    private com.getui.gtc.base.log.c.b logcatLogFormatter = new com.getui.gtc.base.log.c.b(this.logcatLogDestnation);
    private com.getui.gtc.base.log.a.b logcatLogController = new com.getui.gtc.base.log.a.b(this.logcatLogFormatter);

    public Logger(Context context) {
        this.logPrinter.a(this.logcatLogController);
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            this.fileLogDestnation = new com.getui.gtc.base.log.b.a(applicationContext);
            this.fileLogFormatter = new com.getui.gtc.base.log.c.a(applicationContext, this.fileLogDestnation);
            this.fileLogController = new a(applicationContext, this.fileLogFormatter);
            this.logPrinter.a(this.fileLogController);
        }
    }

    public void addController(ILogController iLogController) {
        if (iLogController == null) {
            return;
        }
        this.logPrinter.a(iLogController);
    }

    public void d(String str) {
        this.logPrinter.a(2, null, str, null);
    }

    public void d(String str, String str2) {
        this.logPrinter.a(2, str, str2, null);
    }

    public void d(String str, String str2, Throwable th) {
        this.logPrinter.a(2, str, str2, th);
    }

    public void d(String str, Throwable th) {
        this.logPrinter.a(2, str, null, th);
    }

    public void d(Throwable th) {
        this.logPrinter.a(2, null, null, th);
    }

    public void e(String str) {
        this.logPrinter.a(5, null, str, null);
    }

    public void e(String str, String str2) {
        this.logPrinter.a(5, str, str2, null);
    }

    public void e(String str, String str2, Throwable th) {
        this.logPrinter.a(5, str, str2, th);
    }

    public void e(String str, Throwable th) {
        this.logPrinter.a(5, null, str, th);
    }

    public void e(Throwable th) {
        this.logPrinter.a(5, null, null, th);
    }

    public void filelog(int i, String str, String str2, Throwable th) {
        this.logPrinter.a(i | 32, str, str2, th);
    }

    public void i(String str) {
        this.logPrinter.a(3, null, str, null);
    }

    public void i(String str, String str2) {
        this.logPrinter.a(3, str, str2, null);
    }

    public void i(String str, String str2, Throwable th) {
        this.logPrinter.a(3, str, str2, th);
    }

    public void i(String str, Throwable th) {
        this.logPrinter.a(3, str, null, th);
    }

    public void i(Throwable th) {
        this.logPrinter.a(3, null, null, th);
    }

    public void logcat(int i, String str, String str2, Throwable th) {
        this.logPrinter.a(i | 16, str, str2, th);
    }

    public void removeController(ILogController iLogController) {
        this.logPrinter.b(iLogController);
    }

    public void setFileEnableProperty(String str) {
        a aVar = this.fileLogController;
        if (aVar != null) {
            aVar.a(str);
        }
    }

    public void setGlobalTag(String str) {
        this.logcatLogFormatter.a = str;
        com.getui.gtc.base.log.c.a aVar = this.fileLogFormatter;
        if (aVar != null) {
            aVar.a = str;
        }
    }

    public void setLogFileNameSuffix(String str) {
        com.getui.gtc.base.log.b.a aVar = this.fileLogDestnation;
        if (aVar != null) {
            aVar.b = aVar.c.getPackageName() + "-" + str + "-" + new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()) + ".log";
            StringBuilder sb = new StringBuilder();
            sb.append(GtcProvider.getSdcardPath());
            sb.append("/libs");
            aVar.a = new File(sb.toString(), aVar.b);
        }
    }

    public void setLogcatEnable(boolean z) {
        this.logcatLogController.a = z;
    }

    public void setStackOffset(int i) {
        int i2 = i + 8;
        this.logcatLogFormatter.b = i2;
        com.getui.gtc.base.log.c.a aVar = this.fileLogFormatter;
        if (aVar != null) {
            aVar.b = i2;
        }
    }

    public void v(String str) {
        this.logPrinter.a(1, null, str, null);
    }

    public void v(String str, String str2) {
        this.logPrinter.a(1, str, str2, null);
    }

    public void v(String str, String str2, Throwable th) {
        this.logPrinter.a(1, str, str2, th);
    }

    public void v(String str, Throwable th) {
        this.logPrinter.a(1, str, null, th);
    }

    public void v(Throwable th) {
        this.logPrinter.a(1, null, null, th);
    }

    public void w(String str) {
        this.logPrinter.a(4, null, str, null);
    }

    public void w(String str, String str2) {
        this.logPrinter.a(4, str, str2, null);
    }

    public void w(String str, String str2, Throwable th) {
        this.logPrinter.a(4, str, str2, th);
    }

    public void w(String str, Throwable th) {
        this.logPrinter.a(4, null, str, th);
    }

    public void w(Throwable th) {
        this.logPrinter.a(4, null, null, th);
    }
}
