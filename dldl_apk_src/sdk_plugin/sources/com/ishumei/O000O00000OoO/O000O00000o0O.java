package com.ishumei.O000O00000OoO;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.huya.mtp.hyns.report.NSPushReporter;
import com.huya.statistics.core.StatisticsContent;
import com.ishumei.O000O0000OOoO.O000O0000Oo0O;
import com.ishumei.O000O0000OOoO.O000O0000OoO;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sqwan.bugless.core.Constant;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000o0O {
    private static HandlerThread O0000O000000oO = null;
    private static Handler O000O00000OoO = null;
    private static boolean O000O00000o0O = false;
    private static boolean O000O00000oO = true;
    private static String O000O0000O0oO = "";
    private static String O000O0000OOoO;

    public static void O0000O000000oO(String str) {
        O000O0000OOoO = str;
    }

    public static void O0000O000000oO(Throwable th) {
        try {
            HashMap map = new HashMap();
            map.put(StatisticsContent.SDKVER, "2.8.4");
            map.put(Constant.DEV_MODEL, Build.MODEL);
            map.put(NSPushReporter.NS_PUSH_OSVER_KEY, Build.VERSION.RELEASE);
            map.put("org", O000O0000O0oO);
            map.put("e", O000O00000OoO(th));
            O0000O000000oO(map);
        } catch (Throwable unused) {
        }
    }

    private static void O0000O000000oO(final Map<String, String> map) {
        if (O000O00000oO) {
            if (!O000O00000o0O) {
                synchronized (O000O00000o0O.class) {
                    if (!O000O00000o0O) {
                        HandlerThread handlerThread = new HandlerThread("exception upload thread");
                        O0000O000000oO = handlerThread;
                        handlerThread.setDaemon(true);
                        O0000O000000oO.start();
                        O000O00000OoO = new Handler(O0000O000000oO.getLooper());
                        O000O00000o0O = true;
                    }
                }
            }
            if (map == null || map.size() == 0) {
                return;
            }
            O000O00000OoO.post(new Runnable() { // from class: com.ishumei.O000O00000OoO.O000O00000o0O.1
                @Override // java.lang.Runnable
                public void run() {
                    HttpURLConnection httpURLConnection;
                    HttpURLConnection httpURLConnection2 = null;
                    try {
                        try {
                            StringBuilder sb = new StringBuilder(O000O00000o0O.O000O0000OOoO);
                            for (Map.Entry entry : map.entrySet()) {
                                String strO000O0000O0oO = O000O0000Oo0O.O000O0000O0oO((String) entry.getValue());
                                sb.append("&");
                                sb.append((String) entry.getKey());
                                sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                                sb.append(O000O0000OoO.O000O0000O0oO(strO000O0000O0oO));
                            }
                            httpURLConnection = (HttpURLConnection) new URL(sb.toString()).openConnection();
                            try {
                                httpURLConnection.setRequestMethod("GET");
                                httpURLConnection.setRequestProperty("Connection", "close");
                                httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
                                httpURLConnection.setConnectTimeout(2000);
                                httpURLConnection.setReadTimeout(5000);
                                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000OoO("ExceptionTracker", "response " + httpURLConnection.getResponseCode());
                            } catch (Throwable th) {
                                th = th;
                                httpURLConnection2 = httpURLConnection;
                                try {
                                    com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000OoO("ExceptionTracker", "upload failed");
                                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(th);
                                    if (httpURLConnection2 == null) {
                                        return;
                                    } else {
                                        httpURLConnection2.disconnect();
                                    }
                                } catch (Throwable th2) {
                                    if (httpURLConnection2 != null) {
                                        try {
                                            httpURLConnection2.disconnect();
                                        } catch (Exception unused) {
                                        }
                                    }
                                    throw th2;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    } catch (Exception unused2) {
                    }
                }
            });
        }
    }

    public static void O0000O000000oO(boolean z) {
        O000O00000oO = z;
    }

    private static String O000O00000OoO(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            do {
                th.printStackTrace(printWriter);
                th = th.getCause();
            } while (th != null);
            printWriter.close();
            String string = stringWriter.toString();
            return string.length() > 4096 ? string.substring(0, 4096) : string;
        } catch (Exception unused) {
            return "";
        }
    }

    public static void O000O00000OoO(String str) {
        O000O0000O0oO = str;
    }
}
