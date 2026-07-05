package com.huya.security.hydeviceid;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NetworkBridge {
    private static final String CHARSET = "utf-8";
    private static final String TAG = "Network";
    private static final int TIMEOUT = 10000;

    public static String get(String str, Map<String, String> map) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&" : "?");
        byte[] bArrHttpRequest = httpRequest(sb.toString() + parseParams(map, CHARSET), null, false);
        if (bArrHttpRequest == null) {
            return "";
        }
        try {
            return new String(bArrHttpRequest, CHARSET);
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static String post(String str, Map<String, String> map) throws Throwable {
        byte[] bArrHttpRequest = httpRequest(str, parseParams(map, CHARSET).getBytes(), false);
        if (bArrHttpRequest == null) {
            return "";
        }
        try {
            return new String(bArrHttpRequest, CHARSET);
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static byte[] post(String str, byte[] bArr) {
        return httpRequest(str, bArr, true);
    }

    private static String parseParams(Map<String, String> map, String str) {
        if (map != null && !map.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    sb.append(URLEncoder.encode(entry.getKey(), str));
                    sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                    sb.append(URLEncoder.encode(entry.getValue(), str));
                    sb.append("&");
                }
                return sb.substring(0, sb.length() - 1);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] httpRequest(java.lang.String r4, byte[] r5, boolean r6) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r5 == 0) goto La
            int r1 = r5.length     // Catch: java.lang.Throwable -> L5 java.lang.Exception -> L7
            goto Lb
        L5:
            r4 = move-exception
            goto L62
        L7:
            r4 = move-exception
            r5 = r0
            goto L57
        La:
            r1 = 0
        Lb:
            r2 = 10000(0x2710, float:1.4013E-41)
            java.net.HttpURLConnection r4 = createConnection(r4, r1, r2, r6)     // Catch: java.lang.Throwable -> L5 java.lang.Exception -> L7
            if (r1 <= 0) goto L33
            r6 = 10240(0x2800, float:1.4349E-41)
            if (r1 <= r6) goto L23
            byte[] r5 = gzip(r5)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            int r1 = r5.length     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            java.lang.String r6 = "Content-Encoding"
            java.lang.String r2 = "gzip"
            r4.setRequestProperty(r6, r2)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
        L23:
            java.lang.String r6 = "Content-Length"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            r4.setRequestProperty(r6, r1)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            java.io.OutputStream r6 = r4.getOutputStream()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            r6.write(r5)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
        L33:
            int r5 = r4.getResponseCode()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 != r6) goto L49
            java.io.InputStream r5 = r4.getInputStream()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            byte[] r5 = read(r5)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
            if (r4 == 0) goto L48
            r4.disconnect()
        L48:
            return r5
        L49:
            if (r4 == 0) goto L5f
            r4.disconnect()
            goto L5f
        L4f:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L62
        L53:
            r5 = move-exception
            r3 = r5
            r5 = r4
            r4 = r3
        L57:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L60
            if (r5 == 0) goto L5f
            r5.disconnect()
        L5f:
            return r0
        L60:
            r4 = move-exception
            r0 = r5
        L62:
            if (r0 == 0) goto L67
            r0.disconnect()
        L67:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.security.hydeviceid.NetworkBridge.httpRequest(java.lang.String, byte[], boolean):byte[]");
    }

    private static byte[] gzip(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private static HttpURLConnection createConnection(String str, int i, int i2, boolean z) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(i2);
        httpURLConnection.setUseCaches(false);
        if (i > 0) {
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        return httpURLConnection;
    }

    private static byte[] read(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
