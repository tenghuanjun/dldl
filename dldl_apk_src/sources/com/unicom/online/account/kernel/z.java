package com.unicom.online.account.kernel;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.bytedance.android.live.base.api.push.ILivePush;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class z {
    public static boolean a;
    public static boolean b;
    private static final HostnameVerifier c = new HostnameVerifier() { // from class: com.unicom.online.account.kernel.z.1
        @Override // javax.net.ssl.HostnameVerifier
        public final boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0230  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(android.content.Context r13, java.lang.String r14, java.lang.Object r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.online.account.kernel.z.a(android.content.Context, java.lang.String, java.lang.Object):java.lang.String");
    }

    private static String a(InputStream inputStream) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        byte[] bArr;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                try {
                    bArr = new byte[1024];
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused) {
                            return null;
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            byteArrayOutputStream = null;
            th = th3;
        }
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, i);
            th = th2;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception unused2) {
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
        String str = new String(byteArrayOutputStream.toByteArray());
        try {
            byteArrayOutputStream.close();
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception unused3) {
        }
        return str;
    }

    private static String a(String str) {
        if (!str.contains(":")) {
            return str;
        }
        return "[" + str + "]";
    }

    public final String a(Context context, String str, HashMap<String, String> map, Object obj) {
        String host;
        aa aaVar;
        String strReplaceFirst = str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            host = new URL(strReplaceFirst).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            host = "";
        }
        String str2 = host;
        if (str2.contains(n.e()) && b && a) {
            String strReplaceFirst2 = !TextUtils.isEmpty(d.a) ? strReplaceFirst.replaceFirst(n.e(), a(d.a)) : strReplaceFirst;
            b = false;
            strReplaceFirst = strReplaceFirst2;
        }
        c.b("\n■★■★■★■★■★■★■★■★■★■\n requestGetHttps() requestUrl - >" + strReplaceFirst.substring(0, 30) + "...\n ...\n ■★■★■★■★■★■★■★■★■★■\n");
        try {
            if (Build.VERSION.SDK_INT < 21) {
                if (e.b(context) == 1) {
                    new af();
                    af.a(context, str2);
                }
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, new SecureRandom());
                aaVar = new aa(sSLContext.getSocketFactory());
            } else {
                aaVar = null;
            }
            URL url = new URL(strReplaceFirst);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) ((obj == null || Build.VERSION.SDK_INT < 21) ? url.openConnection() : ((Network) obj).openConnection(url));
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(false);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setInstanceFollowRedirects(false);
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setConnectTimeout(10000);
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setHostnameVerifier(c);
            httpsURLConnection.setInstanceFollowRedirects(true);
            HttpURLConnection.setFollowRedirects(true);
            if (aaVar != null) {
                c.b("TAG\tsocketFactory!=null\n");
                httpsURLConnection.setSSLSocketFactory(aaVar);
            }
            if (map != null) {
                for (String str3 : map.keySet()) {
                    httpsURLConnection.setRequestProperty(str3, map.get(str3));
                }
            }
            httpsURLConnection.addRequestProperty("Connection", ILivePush.ClickType.CLOSE);
            c.b("TAG\thttpsURLConnection.connect();\n");
            httpsURLConnection.connect();
            c.b("connect cost:" + (System.currentTimeMillis() - jCurrentTimeMillis));
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            int responseCode = httpsURLConnection.getResponseCode();
            c.b("\n■★■★■★■★■★■★■★■★■★■\n requestGetHttps() statusCode - >" + responseCode + "\n ■★■★■★■★■★■★■★■★■★■\n");
            StringBuilder sb = new StringBuilder("response cost:");
            sb.append(System.currentTimeMillis() - jCurrentTimeMillis2);
            c.b(sb.toString());
            if (responseCode == 200) {
                String strA = a(httpsURLConnection.getInputStream());
                if (!TextUtils.isEmpty(strA)) {
                    return strA;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("code", 410012);
                jSONObject.put("msg", "outputStr isEmpty");
                jSONObject.put(com.alipay.sdk.packet.e.k, "requestUrl:".concat(String.valueOf(strReplaceFirst)));
                return jSONObject.toString();
            }
            if (responseCode != 302) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("code", 410010);
                jSONObject2.put("msg", "https statusCode NOK ".concat(String.valueOf(responseCode)));
                jSONObject2.put(com.alipay.sdk.packet.e.k, "requestUrl:".concat(String.valueOf(strReplaceFirst)));
                return jSONObject2.toString();
            }
            String headerField = httpsURLConnection.getHeaderField(HttpHeaders.LOCATION);
            c.b("redirectUrl is \n".concat(String.valueOf(headerField)));
            c.b("System.currentTimeMillis() is  \n" + System.currentTimeMillis());
            if (!TextUtils.isEmpty(headerField)) {
                return headerField.startsWith("https") ? a(context, headerField, null, obj) : a(context, headerField, obj);
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("code", 410013);
            jSONObject3.put("msg", "无跳转地址");
            jSONObject3.put(com.alipay.sdk.packet.e.k, str2);
            return jSONObject3.toString();
        } catch (Exception e2) {
            int iE = e.e();
            c.b("\n■★■★■★■★■★■★■★■★■★■\n iRetry = >" + iE + " \n   e-->" + e2 + "\n ■★■★■★■★■★■★■★■★■★■\n");
            e2.printStackTrace();
            String message = e2.getMessage();
            if (message == null || iE >= e.d()) {
                try {
                    c.b("catch (Exception e) is  ".concat(String.valueOf(e2)));
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("code", 410011);
                    jSONObject4.put("msg", "https异常 : ".concat(String.valueOf(message)));
                    jSONObject4.put(com.alipay.sdk.packet.e.k, "requestUrl->".concat(String.valueOf(strReplaceFirst)));
                    return jSONObject4.toString();
                } catch (Exception unused) {
                    return null;
                }
            }
            int iF = e.f();
            if (message.contains("resolve host")) {
                if (str2.contains(n.e()) || str2.contains(n.f())) {
                    c.b("resolve host error: retry->" + iF + " times \ne_getMessage=" + message);
                    b = true;
                    a = true;
                    if (!TextUtils.isEmpty(d.a)) {
                        strReplaceFirst = strReplaceFirst.replaceFirst(n.e(), a(d.a));
                    }
                    return a(context, strReplaceFirst, map, obj);
                }
            }
            if (message.contains("Failed to connect")) {
                c.b("Failed to connect error: retry->" + iF + " times \ne_getMessage=" + message);
                return a(context, strReplaceFirst, map, obj);
            }
            c.b("other  error: retry->" + iF + " times \ne_getMessage=" + message);
            return a(context, strReplaceFirst, map, obj);
        }
    }
}
