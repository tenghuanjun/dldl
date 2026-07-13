package com.mobile.auth.z;

import android.content.Context;
import android.net.Network;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.lzy.okgo.model.HttpHeaders;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.tencent.connect.common.Constants;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f827a = false;
    public static boolean b = false;

    class a implements HostnameVerifier {
        a() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public final boolean verify(String str, SSLSession sSLSession) {
            try {
                if (!TextUtils.isEmpty(str) && sSLSession != null) {
                    try {
                        return u.a(str, ((X509Certificate) sSLSession.getPeerCertificates()[0]).getSubjectDN().getName());
                    } catch (SSLPeerUnverifiedException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return false;
            } catch (Throwable th) {
                ExceptionProcessor.processException(th);
                return false;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x004e A[Catch: Exception -> 0x0051, TRY_LEAVE, TryCatch #7 {Exception -> 0x0051, blocks: (B:32:0x0049, B:34:0x004e), top: B:47:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0049 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.io.InputStream r6) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2f
            r1.<init>()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2f
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L46
        La:
            int r3 = r6.read(r2)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L46
            r4 = -1
            if (r3 == r4) goto L16
            r4 = 0
            r1.write(r2, r4, r3)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L46
            goto La
        L16:
            byte[] r2 = r1.toByteArray()     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L46
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L46
            r3.<init>(r2)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L46
            r1.close()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3a
            if (r6 == 0) goto L27
            r6.close()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3a
        L27:
            return r3
        L28:
            r2 = move-exception
            goto L31
        L2a:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L47
        L2f:
            r2 = move-exception
            r1 = r0
        L31:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L3c
            r1.close()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L45
            goto L3c
        L3a:
            r6 = move-exception
            goto L42
        L3c:
            if (r6 == 0) goto L45
            r6.close()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L45
            goto L45
        L42:
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r6)
        L45:
            return r0
        L46:
            r0 = move-exception
        L47:
            if (r1 == 0) goto L4c
            r1.close()     // Catch: java.lang.Exception -> L51
        L4c:
            if (r6 == 0) goto L51
            r6.close()     // Catch: java.lang.Exception -> L51
        L51:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.z.n.a(java.io.InputStream):java.lang.String");
    }

    private static String a(String str) {
        try {
            if (!str.contains(":")) {
                return str;
            }
            return "[" + str + "]";
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public final String a(Context context, String str, HashMap<String, String> map, Object obj) {
        HttpsURLConnection httpsURLConnection;
        String queryParameter;
        String strReplaceFirst = str;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String host = "";
            try {
                host = new URL(strReplaceFirst).getHost();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (host.contains(e.d()) && b && f827a) {
                String strReplaceFirst2 = !TextUtils.isEmpty(u.f835a) ? strReplaceFirst.replaceFirst(e.d(), a(u.f835a)) : strReplaceFirst;
                b = false;
                strReplaceFirst = strReplaceFirst2;
            }
            try {
                URL url = new URL(strReplaceFirst);
                httpsURLConnection = (HttpsURLConnection) (obj != null ? ((Network) obj).openConnection(url) : url.openConnection());
            } catch (Exception e2) {
                e = e2;
                httpsURLConnection = null;
            }
            try {
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(false);
                httpsURLConnection.setUseCaches(false);
                httpsURLConnection.setInstanceFollowRedirects(false);
                httpsURLConnection.setReadTimeout(10000);
                httpsURLConnection.setConnectTimeout(10000);
                httpsURLConnection.setRequestMethod(Constants.HTTP_GET);
                httpsURLConnection.setHostnameVerifier(new a());
                p.a();
                httpsURLConnection.setInstanceFollowRedirects(true);
                HttpURLConnection.setFollowRedirects(true);
                if (map != null) {
                    for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); it = it) {
                        String next = it.next();
                        httpsURLConnection.setRequestProperty(next, map.get(next));
                    }
                }
                httpsURLConnection.addRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, "close");
                t.c("TAG\thttpsURLConnection.connect();\n");
                httpsURLConnection.connect();
                t.c("connect cost:" + (System.currentTimeMillis() - jCurrentTimeMillis));
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                int responseCode = httpsURLConnection.getResponseCode();
                t.c("response cost:" + (System.currentTimeMillis() - jCurrentTimeMillis2));
                String string = httpsURLConnection.getURL().toString();
                if (string.contains("ret_url")) {
                    queryParameter = Uri.parse(new String(Base64.decode(Uri.parse(string).getQueryParameter("ret_url"), 0))).getQueryParameter("seq");
                    t.c("seq = " + queryParameter + "\nstatusCode = " + responseCode);
                } else {
                    queryParameter = "seqAndroidEmpty";
                }
                if (responseCode == 200) {
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    String str2 = new String(a(inputStream));
                    httpsURLConnection.disconnect();
                    inputStream.close();
                    if (!TextUtils.isEmpty(str2)) {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (TextUtils.isEmpty(jSONObject.optString("seq"))) {
                            jSONObject.put("seq", queryParameter);
                        }
                        return jSONObject.toString();
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("code", 410012);
                    jSONObject2.put("msg", "outputStr isEmpty");
                    jSONObject2.put("seq", queryParameter);
                    jSONObject2.put("data", "requestUrl:".concat(String.valueOf(strReplaceFirst)));
                    return jSONObject2.toString();
                }
                if (responseCode != 302) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("code", 410010);
                    jSONObject3.put("msg", "https statusCode NOK ".concat(String.valueOf(responseCode)));
                    jSONObject3.put("data", "requestUrl:".concat(String.valueOf(strReplaceFirst)));
                    return jSONObject3.toString();
                }
                String str3 = new String(httpsURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION));
                t.c("statusCode == 302, redirectUrl is \n".concat(str3));
                t.c("System.currentTimeMillis() is  \n" + System.currentTimeMillis());
                httpsURLConnection.getInputStream().close();
                httpsURLConnection.disconnect();
                if (TextUtils.isEmpty(str3)) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("code", 410013);
                    jSONObject4.put("msg", "无跳转地址");
                    jSONObject4.put("data", host);
                    return jSONObject4.toString();
                }
                if (str3.startsWith("https")) {
                    return a(context, str3, map, obj);
                }
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("code", 410013);
                jSONObject5.put("msg", "无法跳转HTTP地址");
                jSONObject5.put("data", host);
                return jSONObject5.toString();
            } catch (Exception e3) {
                e = e3;
                try {
                    httpsURLConnection.getInputStream().close();
                    httpsURLConnection.disconnect();
                } catch (Exception unused) {
                }
                int iE = v.e();
                t.c("\n■★■★■★■★■★■★■★■★■★■\n iRetry = >" + iE + " \n   e-->" + e + "\n ■★■★■★■★■★■★■★■★■★■\n");
                e.printStackTrace();
                String message = e.getMessage();
                if (message == null || iE >= v.d()) {
                    try {
                        t.c("catch (Exception e) is  ".concat(String.valueOf(e)));
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put("code", 410011);
                        jSONObject6.put("msg", "https异常 : ".concat(String.valueOf(message)));
                        jSONObject6.put("data", "requestUrl->".concat(String.valueOf(strReplaceFirst)));
                        return jSONObject6.toString();
                    } catch (Exception unused2) {
                        return null;
                    }
                }
                int iF = v.f();
                if (message.contains("resolve host") && (host.contains(e.d()) || host.contains(e.e()))) {
                    t.c("resolve host error: retry->" + iF + " times \ne_getMessage=" + message);
                    if (b && f827a && !TextUtils.isEmpty(u.f835a)) {
                        strReplaceFirst = strReplaceFirst.replaceFirst(e.d(), a(u.f835a));
                    }
                    return a(context, strReplaceFirst, map, obj);
                }
                if (message.contains("Failed to connect")) {
                    t.c("Failed to connect error: retry->" + iF + " times \ne_getMessage=" + message);
                    return a(context, strReplaceFirst, map, obj);
                }
                t.c("other  error: retry->" + iF + " times \ne_getMessage=" + message);
                return a(context, strReplaceFirst, map, obj);
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }
}
