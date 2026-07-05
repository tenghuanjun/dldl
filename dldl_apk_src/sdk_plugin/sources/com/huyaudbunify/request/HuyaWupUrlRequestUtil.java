package com.huyaudbunify.request;

import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sq.tools.network.ContentType;
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
public class HuyaWupUrlRequestUtil {
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

    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0059 A[PHI: r3
  0x0059: PHI (r3v4 java.net.HttpURLConnection) = (r3v5 java.net.HttpURLConnection), (r3v6 java.net.HttpURLConnection) binds: [B:29:0x0057, B:21:0x0048] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] httpRequest(java.lang.String r3, byte[] r4, boolean r5) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r4 == 0) goto L9
            int r1 = r4.length     // Catch: java.lang.Throwable -> L5 java.lang.Exception -> L7
            goto La
        L5:
            r3 = move-exception
            goto L51
        L7:
            r3 = r0
            goto L57
        L9:
            r1 = 0
        La:
            r2 = 10000(0x2710, float:1.4013E-41)
            java.net.HttpURLConnection r3 = createConnection(r3, r1, r2, r5)     // Catch: java.lang.Throwable -> L5 java.lang.Exception -> L7
            if (r1 <= 0) goto L32
            r5 = 10240(0x2800, float:1.4349E-41)
            if (r1 <= r5) goto L22
            byte[] r4 = gzip(r4)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
            int r1 = r4.length     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
            java.lang.String r5 = "Content-Encoding"
            java.lang.String r2 = "gzip"
            r3.setRequestProperty(r5, r2)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
        L22:
            java.lang.String r5 = "Content-Length"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
            r3.setRequestProperty(r5, r1)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
            r5.write(r4)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
        L32:
            int r4 = r3.getResponseCode()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 != r5) goto L48
            java.io.InputStream r4 = r3.getInputStream()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
            byte[] r4 = read(r4)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4f
            if (r3 == 0) goto L47
            r3.disconnect()
        L47:
            return r4
        L48:
            if (r3 == 0) goto L5c
            goto L59
        L4b:
            r4 = move-exception
            r0 = r3
            r3 = r4
            goto L51
        L4f:
            goto L57
        L51:
            if (r0 == 0) goto L56
            r0.disconnect()
        L56:
            throw r3
        L57:
            if (r3 == 0) goto L5c
        L59:
            r3.disconnect()
        L5c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huyaudbunify.request.HuyaWupUrlRequestUtil.httpRequest(java.lang.String, byte[], boolean):byte[]");
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
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", z ? "application/multipart-formdata" : ContentType.FORM);
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
