package com.tencent.bugly.crashreport.crash.h5;

import android.webkit.JavascriptInterface;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class H5JavaScriptInterface {
    private static HashSet<Integer> a = new HashSet<>();
    private String b = null;
    private Thread c = null;
    private String d = null;
    private Map<String, String> e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(CrashReport.WebViewInterface webViewInterface) {
        String string = null;
        if (webViewInterface == null || a.contains(Integer.valueOf(webViewInterface.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        a.add(Integer.valueOf(webViewInterface.hashCode()));
        Thread threadCurrentThread = Thread.currentThread();
        h5JavaScriptInterface.c = threadCurrentThread;
        if (threadCurrentThread != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(ShellAdbUtils.COMMAND_LINE_END);
            for (int i = 2; i < threadCurrentThread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = threadCurrentThread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    sb.append(stackTraceElement.toString());
                    sb.append(ShellAdbUtils.COMMAND_LINE_END);
                }
            }
            string = sb.toString();
        }
        h5JavaScriptInterface.d = string;
        HashMap map = new HashMap();
        StringBuilder sb2 = new StringBuilder();
        sb2.append((Object) webViewInterface.getContentDescription());
        map.put("[WebView] ContentDescription", sb2.toString());
        h5JavaScriptInterface.e = map;
        return h5JavaScriptInterface;
    }

    private static a a(String str) {
        String string;
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                a aVar = new a();
                aVar.a = jSONObject.getString("projectRoot");
                if (aVar.a == null) {
                    return null;
                }
                aVar.b = jSONObject.getString("context");
                if (aVar.b == null) {
                    return null;
                }
                aVar.c = jSONObject.getString("url");
                if (aVar.c == null) {
                    return null;
                }
                aVar.d = jSONObject.getString("userAgent");
                if (aVar.d == null) {
                    return null;
                }
                aVar.e = jSONObject.getString("language");
                if (aVar.e == null) {
                    return null;
                }
                aVar.f = jSONObject.getString("name");
                if (aVar.f == null || aVar.f.equals(AbstractJsonLexerKt.NULL) || (string = jSONObject.getString("stacktrace")) == null) {
                    return null;
                }
                int iIndexOf = string.indexOf(ShellAdbUtils.COMMAND_LINE_END);
                if (iIndexOf < 0) {
                    x.d("H5 crash stack's format is wrong!", new Object[0]);
                    return null;
                }
                aVar.h = string.substring(iIndexOf + 1);
                aVar.g = string.substring(0, iIndexOf);
                int iIndexOf2 = aVar.g.indexOf(":");
                if (iIndexOf2 > 0) {
                    aVar.g = aVar.g.substring(iIndexOf2 + 1);
                }
                aVar.i = jSONObject.getString("file");
                if (aVar.f == null) {
                    return null;
                }
                aVar.j = jSONObject.getLong("lineNumber");
                if (aVar.j < 0) {
                    return null;
                }
                aVar.k = jSONObject.getLong("columnNumber");
                if (aVar.k < 0) {
                    return null;
                }
                x.a("H5 crash information is following: ", new Object[0]);
                x.a("[projectRoot]: " + aVar.a, new Object[0]);
                x.a("[context]: " + aVar.b, new Object[0]);
                x.a("[url]: " + aVar.c, new Object[0]);
                x.a("[userAgent]: " + aVar.d, new Object[0]);
                x.a("[language]: " + aVar.e, new Object[0]);
                x.a("[name]: " + aVar.f, new Object[0]);
                x.a("[message]: " + aVar.g, new Object[0]);
                x.a("[stacktrace]: \n" + aVar.h, new Object[0]);
                x.a("[file]: " + aVar.i, new Object[0]);
                x.a("[lineNumber]: " + aVar.j, new Object[0]);
                x.a("[columnNumber]: " + aVar.k, new Object[0]);
                return aVar;
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    @JavascriptInterface
    public void printLog(String str) {
        x.d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            x.d("Payload from JS is null.", new Object[0]);
            return;
        }
        String strB = z.b(str.getBytes());
        String str2 = this.b;
        if (str2 != null && str2.equals(strB)) {
            x.d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
            return;
        }
        this.b = strB;
        x.d("Handling JS exception ...", new Object[0]);
        a aVarA = a(str);
        if (aVarA == null) {
            x.d("Failed to parse payload.", new Object[0]);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        if (aVarA.a != null) {
            linkedHashMap2.put("[JS] projectRoot", aVarA.a);
        }
        if (aVarA.b != null) {
            linkedHashMap2.put("[JS] context", aVarA.b);
        }
        if (aVarA.c != null) {
            linkedHashMap2.put("[JS] url", aVarA.c);
        }
        if (aVarA.d != null) {
            linkedHashMap2.put("[JS] userAgent", aVarA.d);
        }
        if (aVarA.i != null) {
            linkedHashMap2.put("[JS] file", aVarA.i);
        }
        if (aVarA.j != 0) {
            linkedHashMap2.put("[JS] lineNumber", Long.toString(aVarA.j));
        }
        linkedHashMap.putAll(linkedHashMap2);
        linkedHashMap.putAll(this.e);
        linkedHashMap.put("Java Stack", this.d);
        Thread thread = this.c;
        if (aVarA != null) {
            InnerApi.postH5CrashAsync(thread, aVarA.f, aVarA.g, aVarA.h, linkedHashMap);
        }
    }
}
