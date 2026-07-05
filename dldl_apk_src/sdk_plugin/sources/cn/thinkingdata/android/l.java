package cn.thinkingdata.android;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import cn.thinkingdata.android.crash.CrashLogListener;
import cn.thinkingdata.android.utils.TDLog;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class l {
    private static l c;
    private final Context a;
    private boolean b;

    class a implements CrashLogListener {

        /* JADX INFO: renamed from: cn.thinkingdata.android.l$a$a, reason: collision with other inner class name */
        class C0006a implements ThinkingAnalyticsSDK.b {
            final /* synthetic */ String a;
            final /* synthetic */ File b;

            C0006a(a aVar, String str, File file) {
                this.a = str;
                this.b = file;
            }

            @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
            public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
                if (thinkingAnalyticsSDK.shouldTrackCrash()) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            if (this.a.getBytes("UTF-8").length > 16384) {
                                if (!TDPresetProperties.disableList.contains("#app_crashed_reason")) {
                                    jSONObject.put("#app_crashed_reason", new String(cn.thinkingdata.android.utils.h.a(this.a, 16384), "UTF-8"));
                                }
                            } else if (!TDPresetProperties.disableList.contains("#app_crashed_reason")) {
                                jSONObject.put("#app_crashed_reason", this.a);
                            }
                        } catch (UnsupportedEncodingException unused) {
                            if (this.a.length() > 8192 && !TDPresetProperties.disableList.contains("#app_crashed_reason")) {
                                jSONObject.put("#app_crashed_reason", this.a.substring(0, 8192));
                            }
                        }
                        thinkingAnalyticsSDK.trackAppCrashAndEndEvent(jSONObject);
                        this.b.delete();
                    } catch (JSONException unused2) {
                    }
                }
            }
        }

        a(l lVar) {
        }

        @Override // cn.thinkingdata.android.crash.CrashLogListener
        public void onFile(File file) {
            ThinkingAnalyticsSDK.allInstances(new C0006a(this, l.a(file), file));
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l lVar = l.this;
            lVar.a(lVar.a);
        }
    }

    class c implements CrashLogListener {

        class a implements ThinkingAnalyticsSDK.b {
            final /* synthetic */ String a;
            final /* synthetic */ File b;

            a(c cVar, String str, File file) {
                this.a = str;
                this.b = file;
            }

            @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
            public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
                if (thinkingAnalyticsSDK.shouldTrackCrash()) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            if (this.a.getBytes("UTF-8").length > 16384) {
                                if (!TDPresetProperties.disableList.contains("#app_crashed_reason")) {
                                    jSONObject.put("#app_crashed_reason", new String(cn.thinkingdata.android.utils.h.a(this.a, 16384), "UTF-8"));
                                }
                            } else if (!TDPresetProperties.disableList.contains("#app_crashed_reason")) {
                                jSONObject.put("#app_crashed_reason", this.a);
                            }
                        } catch (UnsupportedEncodingException unused) {
                            if (this.a.length() > 8192 && !TDPresetProperties.disableList.contains("#app_crashed_reason")) {
                                jSONObject.put("#app_crashed_reason", this.a.substring(0, 8192));
                            }
                        }
                        thinkingAnalyticsSDK.autoTrack("ta_app_crash", jSONObject);
                        this.b.delete();
                    } catch (JSONException unused2) {
                    }
                }
            }
        }

        c(l lVar) {
        }

        @Override // cn.thinkingdata.android.crash.CrashLogListener
        public void onFile(File file) {
            ThinkingAnalyticsSDK.allInstances(new a(this, l.a(file), file));
        }
    }

    private static class d implements Thread.UncaughtExceptionHandler {
        private final Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

        class a implements ThinkingAnalyticsSDK.b {
            final /* synthetic */ String a;

            a(d dVar, String str) {
                this.a = str;
            }

            @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
            public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
                if (thinkingAnalyticsSDK.shouldTrackCrash()) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            if (this.a.getBytes("UTF-8").length > 16384) {
                                if (!TDPresetProperties.disableList.contains("#app_crashed_reason")) {
                                    jSONObject.put("#app_crashed_reason", new String(cn.thinkingdata.android.utils.h.a(this.a, 16384), "UTF-8"));
                                }
                            } else if (!TDPresetProperties.disableList.contains("#app_crashed_reason")) {
                                jSONObject.put("#app_crashed_reason", this.a);
                            }
                        } catch (UnsupportedEncodingException unused) {
                            TDLog.d("ThinkingAnalytics.ExceptionHandler", "Exception occurred in getBytes. ");
                            if (this.a.length() > 8192 && !TDPresetProperties.disableList.contains("#app_crashed_reason")) {
                                jSONObject.put("#app_crashed_reason", this.a.substring(0, 8192));
                            }
                        }
                        thinkingAnalyticsSDK.trackAppCrashAndEndEvent(jSONObject);
                    } catch (JSONException unused2) {
                    }
                }
            }
        }

        d() {
            Thread.setDefaultUncaughtExceptionHandler(this);
        }

        private void a() {
            Process.killProcess(Process.myPid());
            System.exit(10);
        }

        private void a(Throwable th) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            do {
                th.printStackTrace(printWriter);
                th = th.getCause();
            } while (th != null);
            printWriter.close();
            ThinkingAnalyticsSDK.allInstances(new a(this, stringWriter.toString().replaceAll("(\r\n|\n\r|\n|\r)", "<br>")));
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            boolean z;
            Throwable cause = th;
            while (true) {
                if (cause == null) {
                    z = true;
                    break;
                } else {
                    if (cause instanceof n) {
                        z = false;
                        break;
                    }
                    cause = cause.getCause();
                }
            }
            if (z) {
                a(th);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.a;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            } else {
                a();
            }
        }
    }

    private l(Context context) {
        this.a = context.getApplicationContext();
    }

    static String a(File file) throws Throwable {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        stringBuffer.append(line);
                        stringBuffer.append(ShellAdbUtils.COMMAND_LINE_END);
                    } catch (IOException e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        return stringBuffer.toString();
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader2.close();
                String string = stringBuffer.toString();
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return string;
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        File[] fileArrListFiles;
        String str = context.getCacheDir().getAbsolutePath() + File.separator + "tacrash";
        c cVar = new c(this);
        File file = new File(str);
        if (!file.exists() || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            cVar.onFile(file2);
        }
    }

    static l b(Context context) {
        if (c == null) {
            if (context == null) {
                return null;
            }
            synchronized (d.class) {
                if (c == null) {
                    c = new l(context);
                }
            }
        }
        return c;
    }

    synchronized void a() {
        if (!this.b) {
            ArrayList arrayList = new ArrayList();
            try {
                Resources resources = this.a.getResources();
                arrayList.addAll(Arrays.asList(resources.getStringArray(resources.getIdentifier("TACrashConfig", "array", this.a.getPackageName()))));
            } catch (Exception unused) {
            }
            if (arrayList.isEmpty()) {
                new d();
            } else {
                a aVar = new a(this);
                new Thread(new b()).start();
                try {
                    Class<?> cls = Class.forName("cn.thinkingdata.android.crash.TACrash");
                    Object objInvoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                    cls.getMethod("init", Context.class).invoke(objInvoke, this.a);
                    cls.getMethod("enableLog", new Class[0]).invoke(objInvoke, new Object[0]);
                    if (arrayList.contains("java")) {
                        cls.getMethod("initJavaCrashHandler", Boolean.TYPE).invoke(objInvoke, true);
                    }
                    if (arrayList.contains("anr") || arrayList.contains("native")) {
                        cls.getMethod("initNativeCrashHandler", Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE).invoke(objInvoke, true, true, true, true);
                        if (arrayList.contains("anr")) {
                            cls.getMethod("initANRHandler", new Class[0]).invoke(objInvoke, new Object[0]);
                        }
                    }
                    cls.getMethod("initCrashLogListener", CrashLogListener.class).invoke(objInvoke, aVar);
                } catch (Exception unused2) {
                }
            }
            this.b = true;
        }
    }
}
