package com.igexin.push.f;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class q {
    public static final String a = "com.igexin.push.f.q";
    public static final String b = "utf-8";
    private static final String c = "POST";
    private static final String d = "GET";
    private static final String e = "GETUI";
    private static final int f = 30000;

    private static String a(InputStream inputStream, String str) throws Exception {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[256];
            while (true) {
                int i = bufferedReader.read(cArr);
                if (i <= 0) {
                    break;
                }
                stringWriter.write(cArr, 0, i);
            }
            return stringWriter.toString();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "utf-8";
        }
        for (String str2 : str.split(com.alipay.sdk.util.i.b)) {
            String strTrim = str2.trim();
            if (strTrim.startsWith("charset")) {
                String[] strArrSplit = strTrim.split("=", 2);
                return (strArrSplit.length != 2 || TextUtils.isEmpty(strArrSplit[1])) ? "utf-8" : strArrSplit[1].trim();
            }
        }
        return "utf-8";
    }

    private static String a(Map<String, String> map, String str) throws Exception {
        if (map == null || map.isEmpty()) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            str = "utf-8";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                if (z) {
                    sb.append(com.alipay.sdk.sys.a.b);
                } else {
                    z = true;
                }
                sb.append(key);
                sb.append("=");
                sb.append(URLEncoder.encode(value, str));
            }
        }
        return sb.toString();
    }

    private static HttpURLConnection a(URL url, String str, String str2) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(str);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        httpURLConnection.setRequestProperty("User-Agent", e);
        httpURLConnection.setRequestProperty("Content-Type", str2);
        httpURLConnection.setRequestProperty("HOST", url.getHost() + ":" + url.getPort());
        return httpURLConnection;
    }

    private static URL a(String str, String str2) throws Exception {
        StringBuilder sb;
        URL url = new URL(str);
        if (TextUtils.isEmpty(str2)) {
            return url;
        }
        if (TextUtils.isEmpty(url.getQuery())) {
            if (str.endsWith("?")) {
                sb = new StringBuilder();
            } else {
                sb = new StringBuilder();
                sb.append(str);
                str = "?";
            }
        } else if (str.endsWith(com.alipay.sdk.sys.a.b)) {
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder();
            sb.append(str);
            str = com.alipay.sdk.sys.a.b;
        }
        sb.append(str);
        sb.append(str2);
        return new URL(sb.toString());
    }

    private static URL a(String str, Map<String, String> map, String str2) throws Exception {
        return a(str, a(map, str2));
    }

    private static byte[] a(InputStream inputStream) throws Throwable {
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = bufferedInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    bufferedInputStream.close();
                } catch (IOException unused) {
                }
                return byteArray;
            } catch (Exception unused2) {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            bufferedInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
        }
    }

    private static byte[] a(String str, String str2, String str3) throws IOException {
        return ("Content-Disposition:form-data;name=\"" + str + "\"\r\nContent-Type:text/plain\r\n\r\n" + str2).getBytes(str3);
    }

    private static byte[] a(String str, String str2, byte[] bArr, int i, int i2) throws Exception {
        HttpURLConnection httpURLConnectionA;
        OutputStream outputStream = null;
        try {
            httpURLConnectionA = a(new URL(str), "POST", str2);
            try {
                try {
                    httpURLConnectionA.setConnectTimeout(i);
                    httpURLConnectionA.setReadTimeout(i2);
                    try {
                        outputStream = httpURLConnectionA.getOutputStream();
                        outputStream.write(bArr);
                        byte[] bArrA = a(httpURLConnectionA);
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (httpURLConnectionA != null) {
                            httpURLConnectionA.disconnect();
                        }
                        return bArrA;
                    } catch (Exception e2) {
                        throw e2;
                    }
                } catch (IOException e3) {
                    throw e3;
                }
            } catch (Throwable th) {
                th = th;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (httpURLConnectionA != null) {
                    httpURLConnectionA.disconnect();
                }
                throw th;
            }
        } catch (IOException e4) {
            throw e4;
        } catch (Throwable th2) {
            th = th2;
            httpURLConnectionA = null;
        }
    }

    private static byte[] a(String str, Map<String, String> map, int i, int i2) throws Exception {
        return a(str, map, "utf-8", i, i2);
    }

    private static byte[] a(String str, Map<String, String> map, int i, int i2, String str2) throws Exception {
        HttpURLConnection httpURLConnectionB = b(str, map, i, i2, str2);
        try {
            try {
                return a(httpURLConnectionB);
            } catch (Exception e2) {
                throw e2;
            }
        } finally {
            if (httpURLConnectionB != null) {
                httpURLConnectionB.disconnect();
            }
        }
    }

    private static byte[] a(String str, Map<String, String> map, String str2, int i, int i2) throws Exception {
        String strConcat = "application/x-www-form-urlencoded;charset=".concat(String.valueOf(str2));
        String strA = a(map, str2);
        byte[] bytes = new byte[0];
        if (strA != null) {
            bytes = strA.getBytes(str2);
        }
        return a(str, strConcat, bytes, i, i2);
    }

    private static byte[] a(String str, Map<String, String> map, Map<String, j> map2, int i, int i2) throws Exception {
        return (map2 == null || map2.isEmpty()) ? a(str, map, "utf-8", i, i2) : a(str, map, map2, "utf-8", i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0149 A[Catch: all -> 0x01de, Exception -> 0x01e0, TryCatch #9 {Exception -> 0x01e0, all -> 0x01de, blocks: (B:7:0x0043, B:8:0x0062, B:10:0x0068, B:11:0x009e, B:12:0x00a6, B:14:0x00ac, B:16:0x00bf, B:18:0x00c3, B:20:0x00cb, B:21:0x00d3, B:23:0x00d9, B:25:0x00df, B:28:0x00e5, B:30:0x00f3, B:32:0x00f7, B:57:0x013e, B:70:0x016c, B:60:0x0149, B:63:0x0154, B:66:0x015f, B:35:0x00ff, B:37:0x0105, B:39:0x010b, B:42:0x0113, B:44:0x011a, B:46:0x011f, B:48:0x0125, B:51:0x012e, B:53:0x0134, B:71:0x016e, B:74:0x018c, B:76:0x01b0, B:77:0x01b5), top: B:117:0x0043 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] a(java.lang.String r17, java.util.Map<java.lang.String, java.lang.String> r18, java.util.Map<java.lang.String, com.igexin.push.f.j> r19, java.lang.String r20, int r21, int r22) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 515
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.f.q.a(java.lang.String, java.util.Map, java.util.Map, java.lang.String, int, int):byte[]");
    }

    public static byte[] a(String str, byte[] bArr) throws Exception {
        return a(str, OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE, bArr, 10000, 10000);
    }

    private static byte[] a(HttpURLConnection httpURLConnection) throws Exception {
        return httpURLConnection.getErrorStream() == null ? a(httpURLConnection.getInputStream()) : b(httpURLConnection).getBytes();
    }

    private static String b(HttpURLConnection httpURLConnection) throws Exception {
        String strA = a(httpURLConnection.getErrorStream(), a(httpURLConnection.getContentType()));
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        throw new IOException(httpURLConnection.getResponseCode() + ":" + httpURLConnection.getResponseMessage());
    }

    private static HttpURLConnection b(String str, Map<String, String> map, int i, int i2, String str2) throws Exception {
        HttpURLConnection httpURLConnectionA = a(a(str, a(map, str2)), "GET", "application/x-www-form-urlencoded;charset=".concat(String.valueOf(str2)));
        httpURLConnectionA.setConnectTimeout(i);
        httpURLConnectionA.setReadTimeout(i2);
        return httpURLConnectionA;
    }

    private static byte[] b(String str, String str2, String str3) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Content-Disposition:form-data;name=\"");
        sb.append("dp_data");
        sb.append("\";filename=\"");
        if (TextUtils.isEmpty(str)) {
            str = "filename";
        }
        sb.append(str);
        sb.append("\"\r\nContent-Type:");
        sb.append(str2);
        sb.append("\r\n\r\n");
        return sb.toString().getBytes(str3);
    }

    private static byte[] b(String str, Map<String, String> map, int i, int i2) throws Exception {
        return a(str, map, i, i2, "utf-8");
    }

    private static byte[] c(String str, Map<String, String> map, int i, int i2) throws Exception {
        HttpURLConnection httpURLConnectionB = b(str, map, i, i2, "utf-8");
        try {
            try {
                return a(httpURLConnectionB);
            } catch (Exception e2) {
                throw e2;
            }
        } finally {
            if (httpURLConnectionB != null) {
                httpURLConnectionB.disconnect();
            }
        }
    }
}
