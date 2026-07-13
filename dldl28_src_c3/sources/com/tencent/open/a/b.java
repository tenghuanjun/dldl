package com.tencent.open.a;

import android.text.TextUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.open.log.SLog;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
class b implements a {
    private int a = 15000;
    private int b = 30000;
    private final String c;

    public b(String str) {
        this.c = str;
    }

    @Override // com.tencent.open.a.a
    public void a(long j, long j2) {
        if (j <= 0 || j2 <= 0) {
            return;
        }
        this.a = (int) j;
        this.b = (int) j2;
    }

    @Override // com.tencent.open.a.a
    public g a(String str, String str2) throws IOException {
        SLog.i("DefaultHttpServiceImpl", "get. ");
        if (!TextUtils.isEmpty(str2)) {
            int iIndexOf = str2.indexOf("?");
            if (iIndexOf == -1) {
                str = str + "?";
            } else if (iIndexOf != str.length() - 1) {
                str = str + "&";
            }
            str = str + str2;
        }
        return a(str, str2.length());
    }

    @Override // com.tencent.open.a.a
    public g a(String str, Map<String, String> map) throws IOException {
        SLog.i("DefaultHttpServiceImpl", "post. ");
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append('&');
            }
            sb.append(URLEncoder.encode(entry.getKey(), Constants.ENC_UTF_8));
            sb.append('=');
            sb.append(URLEncoder.encode(entry.getValue(), Constants.ENC_UTF_8));
        }
        String string = sb.toString();
        return a(str, string.length(), string);
    }

    @Override // com.tencent.open.a.a
    public g a(String str, Map<String, String> map, Map<String, byte[]> map2) throws IOException {
        if (map2 == null || map2.size() <= 0) {
            return a(str, map);
        }
        Iterator<Map.Entry<String, byte[]>> it = map2.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Map.Entry<String, byte[]> next = it.next();
        return a(str, map, next.getKey(), next.getValue());
    }

    private void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null) {
            return;
        }
        httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_USER_AGENT, this.c);
        httpURLConnection.setConnectTimeout(this.a);
        httpURLConnection.setReadTimeout(this.b);
        httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_ACCEPT_LANGUAGE, "zh-CN");
        httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_CONNECTION, "Keep-Alive");
        httpURLConnection.setRequestProperty("Charset", Constants.ENC_UTF_8);
    }

    private g a(String str, int i) throws Throwable {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        String str2;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setRequestMethod(Constants.HTTP_GET);
                a(httpURLConnection);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        try {
                            byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
                            while (true) {
                                int i2 = inputStream.read(bArr);
                                if (i2 != -1) {
                                    byteArrayOutputStream2.write(bArr, 0, i2);
                                } else {
                                    c cVar = new c(httpURLConnection, byteArrayOutputStream2.toString(), httpURLConnection.getContentLength(), i, httpURLConnection.getResponseCode(), "");
                                    a(byteArrayOutputStream2);
                                    a(inputStream);
                                    b(httpURLConnection);
                                    return cVar;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            a(byteArrayOutputStream);
                            a(inputStream);
                            b(httpURLConnection);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    String responseMessage = httpURLConnection.getResponseMessage();
                    if (responseMessage == null) {
                        str2 = "请求失败 code:" + httpURLConnection.getResponseCode();
                    } else {
                        str2 = responseMessage;
                    }
                    c cVar2 = new c(httpURLConnection, "", httpURLConnection.getContentLength(), i, httpURLConnection.getResponseCode(), str2);
                    a((Closeable) null);
                    a((Closeable) null);
                    b(httpURLConnection);
                    return cVar2;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            inputStream = null;
        }
    }

    private g a(String str, int i, String str2) throws Throwable {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                a(httpURLConnection);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/x-www-form-urlencoded");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), Constants.ENC_UTF_8);
                outputStreamWriter.write(str2);
                outputStreamWriter.flush();
                httpURLConnection.connect();
                int contentLength = httpURLConnection.getContentLength();
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                        try {
                            byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
                            while (true) {
                                int i2 = inputStream.read(bArr);
                                if (i2 != -1) {
                                    byteArrayOutputStream2.write(bArr, 0, i2);
                                } else {
                                    c cVar = new c(httpURLConnection, byteArrayOutputStream2.toString(), contentLength, i, httpURLConnection.getResponseCode(), "");
                                    a(byteArrayOutputStream2);
                                    a(inputStream);
                                    b(httpURLConnection);
                                    return cVar;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            a(byteArrayOutputStream);
                            a(inputStream);
                            b(httpURLConnection);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    String responseMessage = httpURLConnection.getResponseMessage();
                    if (responseMessage == null) {
                        responseMessage = "Unknown fail: " + httpURLConnection.getResponseCode();
                    }
                    c cVar2 = new c(httpURLConnection, "", 0, i, httpURLConnection.getResponseCode(), responseMessage);
                    a((Closeable) null);
                    a((Closeable) null);
                    b(httpURLConnection);
                    return cVar2;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            inputStream = null;
        }
    }

    public g a(String str, Map<String, String> map, String str2, byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutputStream dataOutputStream;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        DataOutputStream dataOutputStream2;
        int i;
        int responseCode;
        String string;
        InputStream inputStream2;
        ByteArrayOutputStream byteArrayOutputStream2;
        DataOutputStream dataOutputStream3;
        byte[] bArr2;
        Map<String, String> map2 = map;
        String str3 = Constants.ENC_UTF_8;
        SLog.i("DefaultHttpServiceImpl", "文件上传");
        String string2 = UUID.randomUUID().toString();
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "multipart/form-data;boundary=" + string2);
                try {
                    a(httpURLConnection);
                    httpURLConnection.connect();
                    dataOutputStream2 = new DataOutputStream(httpURLConnection.getOutputStream());
                    if (map2 != null) {
                        try {
                            if (map.size() > 0) {
                                Iterator<String> it = map.keySet().iterator();
                                while (it.hasNext()) {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    String strEncode = URLEncoder.encode(it.next(), str3);
                                    String strEncode2 = URLEncoder.encode(map2.get(strEncode), str3);
                                    stringBuffer.append("--").append(string2).append("\r\n");
                                    stringBuffer.append("Content-Disposition: form-data; name=\"").append(strEncode).append("\"").append("\r\n").append("\r\n");
                                    stringBuffer.append(strEncode2).append("\r\n");
                                    String string3 = stringBuffer.toString();
                                    SLog.i("DefaultHttpServiceImpl", strEncode + "=" + string3 + "##");
                                    dataOutputStream2.write(string3.getBytes());
                                    map2 = map;
                                    str3 = str3;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            dataOutputStream = dataOutputStream2;
                            byteArrayOutputStream = null;
                            inputStream = null;
                            a(dataOutputStream);
                            a(inputStream);
                            a(byteArrayOutputStream);
                            b(httpURLConnection);
                            throw th;
                        }
                    }
                    if (bArr == null || bArr.length <= 0) {
                        i = 0;
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("--");
                        stringBuffer2.append(string2);
                        stringBuffer2.append("\r\n");
                        stringBuffer2.append("Content-Disposition: form-data; name=\"" + str2 + "\"; filename=\"" + str2 + "\"\r\n");
                        StringBuilder sb = new StringBuilder();
                        sb.append("Content-Type: application/octet-stream; charset=UTF-8");
                        sb.append("\r\n");
                        stringBuffer2.append(sb.toString());
                        stringBuffer2.append("\r\n");
                        dataOutputStream2.write(stringBuffer2.toString().getBytes());
                        dataOutputStream2.write(bArr, 0, bArr.length);
                        dataOutputStream2.write("\r\n".getBytes());
                        byte[] bytes = ("--" + string2 + "--\r\n").getBytes();
                        dataOutputStream2.write(bytes);
                        int length = bytes.length;
                        dataOutputStream2.flush();
                        i = length;
                    }
                    try {
                        responseCode = httpURLConnection.getResponseCode();
                        SLog.i("DefaultHttpServiceImpl", responseCode + "");
                    } catch (Throwable th2) {
                        th = th2;
                        dataOutputStream = dataOutputStream2;
                        byteArrayOutputStream = null;
                        inputStream = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayOutputStream = null;
                    dataOutputStream = null;
                    inputStream = null;
                    a(dataOutputStream);
                    a(inputStream);
                    a(byteArrayOutputStream);
                    b(httpURLConnection);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            byteArrayOutputStream = null;
            dataOutputStream = null;
            httpURLConnection = null;
        }
        if (responseCode == 200) {
            InputStream inputStream3 = httpURLConnection.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bArr2 = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
                } catch (Throwable th6) {
                    th = th6;
                    inputStream = inputStream3;
                    dataOutputStream = dataOutputStream2;
                }
            } catch (Throwable th7) {
                th = th7;
                inputStream = inputStream3;
                dataOutputStream = dataOutputStream2;
                byteArrayOutputStream = null;
            }
            while (true) {
                int i2 = inputStream3.read(bArr2);
                if (i2 == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, i2);
                a(dataOutputStream);
                a(inputStream);
                a(byteArrayOutputStream);
                b(httpURLConnection);
                throw th;
            }
            string = byteArrayOutputStream.toString();
            inputStream2 = inputStream3;
            byteArrayOutputStream2 = byteArrayOutputStream;
        } else {
            string = httpURLConnection.getResponseCode() + "";
            inputStream2 = null;
            byteArrayOutputStream2 = null;
        }
        try {
            dataOutputStream3 = dataOutputStream2;
            try {
                c cVar = new c(httpURLConnection, string, httpURLConnection.getContentLength(), i, httpURLConnection.getResponseCode(), "");
                a(dataOutputStream3);
                a(inputStream2);
                a(byteArrayOutputStream2);
                b(httpURLConnection);
                return cVar;
            } catch (Throwable th8) {
                th = th8;
                InputStream inputStream4 = inputStream2;
                dataOutputStream = dataOutputStream3;
                byteArrayOutputStream = byteArrayOutputStream2;
                inputStream = inputStream4;
                a(dataOutputStream);
                a(inputStream);
                a(byteArrayOutputStream);
                b(httpURLConnection);
                throw th;
            }
        } catch (Throwable th9) {
            th = th9;
            dataOutputStream3 = dataOutputStream2;
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    private static void b(HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null) {
            return;
        }
        try {
            httpURLConnection.disconnect();
        } catch (Exception unused) {
        }
    }
}
