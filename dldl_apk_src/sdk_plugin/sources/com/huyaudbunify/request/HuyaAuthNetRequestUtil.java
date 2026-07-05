package com.huyaudbunify.request;

import com.huyaudbunify.inter.IIHttpCallBack;
import com.sq.tools.network.ContentType;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaAuthNetRequestUtil {
    /* JADX INFO: Access modifiers changed from: private */
    public void post(String str, byte[] bArr, int i, boolean z, IIHttpCallBack iIHttpCallBack) {
        String str2 = z ? ContentType.JSON : "application/x-wup";
        byte[] bytes = new byte[0];
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestProperty("Content-Type", str2);
            httpURLConnection.addRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.connect();
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(bArr);
            dataOutputStream.flush();
            dataOutputStream.close();
            if (httpURLConnection.getResponseCode() == 200) {
                bytes = streamToString(httpURLConnection.getInputStream()).getBytes();
            }
            iIHttpCallBack.onResponse(bytes, i, 0, httpURLConnection.getResponseCode());
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            iIHttpCallBack.onResponse(bytes, i, 0, -1);
        }
    }

    public String streamToString(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return new String(byteArrayOutputStream.toByteArray());
                }
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public void sendRequestPost(final String str, final byte[] bArr, final int i, final boolean z, final IIHttpCallBack iIHttpCallBack) {
        new Thread(new Runnable() { // from class: com.huyaudbunify.request.HuyaAuthNetRequestUtil.1
            @Override // java.lang.Runnable
            public void run() {
                HuyaAuthNetRequestUtil.this.post(str, bArr, i, z, iIHttpCallBack);
            }
        }).start();
    }

    private static long expires(String str) {
        return (Long.valueOf(str).longValue() * 1000) + System.currentTimeMillis();
    }
}
