package com.tencent.open;

import android.net.Uri;
import android.webkit.WebView;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class b {
    protected HashMap<String, C0196b> a = new HashMap<>();

    /* JADX INFO: compiled from: ProGuard */
    public static class a {
        protected WeakReference<WebView> a;
        protected long b;
        protected String c;

        public a(WebView webView, long j, String str) {
            this.a = new WeakReference<>(webView);
            this.b = j;
            this.c = str;
        }

        public void a(Object obj) {
            String string;
            WebView webView = this.a.get();
            if (webView == null) {
                return;
            }
            if (obj instanceof String) {
                string = "'" + ((Object) ((String) obj).replace("\\", "\\\\").replace("'", "\\'")) + "'";
            } else if (!(obj instanceof Number) && !(obj instanceof Long) && !(obj instanceof Integer) && !(obj instanceof Double) && !(obj instanceof Float) && !(obj instanceof Boolean)) {
                string = "'undefined'";
            } else {
                string = obj.toString();
            }
            webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':0,'result':" + string + "});");
        }

        public void a() {
            WebView webView = this.a.get();
            if (webView == null) {
                return;
            }
            webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':1,'result':'no such method'})");
        }

        public void a(String str) {
            WebView webView = this.a.get();
            if (webView != null) {
                webView.loadUrl("javascript:" + str);
            }
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ProGuard */
    public static class C0196b {
        public boolean customCallback() {
            return false;
        }

        public void call(String str, List<String> list, a aVar) {
            Method method;
            Object objInvoke;
            Method[] declaredMethods = getClass().getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    method = null;
                    break;
                }
                method = declaredMethods[i];
                if (method.getName().equals(str) && method.getParameterTypes().length == list.size()) {
                    break;
                } else {
                    i++;
                }
            }
            if (method == null) {
                if (aVar != null) {
                    aVar.a();
                    return;
                }
                return;
            }
            try {
                int size = list.size();
                if (size == 0) {
                    objInvoke = method.invoke(this, null);
                } else if (size == 1) {
                    objInvoke = method.invoke(this, list.get(0));
                } else if (size == 2) {
                    objInvoke = method.invoke(this, list.get(0), list.get(1));
                } else if (size == 3) {
                    objInvoke = method.invoke(this, list.get(0), list.get(1), list.get(2));
                } else if (size == 4) {
                    objInvoke = method.invoke(this, list.get(0), list.get(1), list.get(2), list.get(3));
                } else if (size == 5) {
                    objInvoke = method.invoke(this, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
                } else {
                    objInvoke = method.invoke(this, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
                }
                Class<?> returnType = method.getReturnType();
                SLog.d("openSDK_LOG.JsBridge", "-->call, result: " + objInvoke + " | ReturnType: " + returnType.getName());
                if (!"void".equals(returnType.getName()) && returnType != Void.class) {
                    if (aVar == null || !customCallback()) {
                        return;
                    }
                    aVar.a(objInvoke != null ? objInvoke.toString() : null);
                    return;
                }
                if (aVar != null) {
                    aVar.a((Object) null);
                }
            } catch (Exception e) {
                SLog.e("openSDK_LOG.JsBridge", "-->handler call mehtod ex. targetMethod: " + method, e);
                if (aVar != null) {
                    aVar.a();
                }
            }
        }
    }

    public void a(C0196b c0196b, String str) {
        this.a.put(str, c0196b);
    }

    public void a(String str, String str2, List<String> list, a aVar) {
        SLog.v("openSDK_LOG.JsBridge", "getResult---objName = " + str + " methodName = " + str2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode(list.get(i), Constants.ENC_UTF_8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        C0196b c0196b = this.a.get(str);
        if (c0196b != null) {
            SLog.d("openSDK_LOG.JsBridge", "call----");
            c0196b.call(str2, list, aVar);
        } else {
            SLog.d("openSDK_LOG.JsBridge", "not call----objName NOT FIND");
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    public boolean a(WebView webView, String str) {
        SLog.v("openSDK_LOG.JsBridge", "-->canHandleUrl---url = " + str);
        if (str == null || !Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 6) {
            return false;
        }
        String str2 = (String) arrayList.get(2);
        String str3 = (String) arrayList.get(3);
        List<String> listSubList = arrayList.subList(4, arrayList.size() - 1);
        a aVar = new a(webView, 4L, str);
        webView.getUrl();
        a(str2, str3, listSubList, aVar);
        return true;
    }
}
