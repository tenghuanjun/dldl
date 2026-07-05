package com.ishumei.O000O0000O0oO;

import android.text.TextUtils;
import android.util.Patterns;
import com.ishumei.O000O00000OoO.O000O00000oO.O000O00000oO;
import com.ishumei.O000O00000OoO.O000O0000O0oO;
import com.ishumei.O000O0000OOoO.O000O0000OoO;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000OoO {
    private static String O0000O000000oO = "POST";
    private static O000O00000OoO O00O0000o00O;
    private int O000O00000OoO;
    private int O000O00000oO;
    private int O000O0000Oo0O;
    private long O000O0000OoO;
    private ArrayList<String> O000O00000o0O = new ArrayList<>();
    private int O000O0000O0oO = 3;
    private int O000O0000OOoO = 2;
    private SSLContext O00O0000OooO = null;
    private TrustManager[] O00O0000o0O = null;
    private KeyStore O00O0000o0OO = null;

    public static class O0000O000000oO implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    /* JADX INFO: renamed from: com.ishumei.O000O0000O0oO.O000O00000OoO$O000O00000OoO, reason: collision with other inner class name */
    public static abstract class AbstractC0045O000O00000OoO<T> extends com.ishumei.O000O00000o0O.O000O00000OoO<T> {
        public O000O00000o0O O000O00000OoO;

        public AbstractC0045O000O00000OoO(boolean z, int i) {
            super(z, i);
            this.O000O00000OoO = null;
        }

        public abstract void O0000O000000oO(String str);

        public boolean O0000O000000oO(String str, int i) {
            if (!this.O000O00000OoO.O000O0000Oo0O || this.O000O00000OoO.O000O00000oO + 1 >= this.O000O00000OoO.O000O0000OoO) {
                if (this.O000O00000OoO != null && !TextUtils.isEmpty(str)) {
                    O000O00000oO.O0000O000000oO().O0000O000000oO(str, this.O000O00000OoO.O00O0000o00O);
                }
                return true;
            }
            this.O000O00000OoO.O000O00000oO++;
            O000O0000OoO.O0000O000000oO(this.O000O00000OoO.O0000O000000oO);
            this.O000O00000OoO.O0000O000000oO = null;
            this.O000O00000OoO.O000O0000OOoO.O0000O000000oO();
            return false;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            BufferedReader bufferedReader;
            Exception e;
            InputStream inputStream;
            StringBuilder sb;
            InputStream inputStream2 = null;
            if (this.O000O00000OoO.O00O0000OooO != null) {
                O0000O000000oO(this.O000O00000OoO.O00O0000OooO, 1);
                this.O000O00000OoO.O00O0000OooO = null;
                return;
            }
            if (this.O000O00000OoO.O0000O000000oO == null) {
                O0000O000000oO("HttpUrlConnection is null", 0);
                return;
            }
            try {
                int responseCode = this.O000O00000OoO.O0000O000000oO.getResponseCode();
                if (responseCode != 200) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("HttpTransport", "HttpTransport responseCode ( " + responseCode + ")");
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("responseCode: ");
                    sb2.append(responseCode);
                    O0000O000000oO(sb2.toString(), 2);
                    return;
                }
                try {
                    inputStream = this.O000O00000OoO.O0000O000000oO.getInputStream();
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        try {
                            try {
                                sb = new StringBuilder();
                            } catch (Exception e2) {
                                e = e2;
                                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("HttpTransport", "HttpTransport response content err: " + e);
                                O0000O000000oO("response content err: " + e, 3);
                                O000O0000OoO.O0000O000000oO((Closeable) inputStream);
                                O000O0000OoO.O0000O000000oO((Closeable) bufferedReader);
                                O000O0000OoO.O0000O000000oO(this.O000O00000OoO.O0000O000000oO);
                                return;
                            }
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Exception e3) {
                        bufferedReader = null;
                        e = e3;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = null;
                    }
                } catch (Exception e4) {
                    bufferedReader = null;
                    e = e4;
                    inputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                }
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    } else {
                        sb.append(line);
                    }
                    th = th;
                    inputStream2 = inputStream;
                    O000O0000OoO.O0000O000000oO((Closeable) inputStream2);
                    O000O0000OoO.O0000O000000oO((Closeable) bufferedReader);
                    O000O0000OoO.O0000O000000oO(this.O000O00000OoO.O0000O000000oO);
                    throw th;
                }
                if (!TextUtils.isEmpty(this.O000O00000OoO.O00O0000o0O)) {
                    com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO.O0000O000000oO().O0000O000000oO(O000O0000OoO.O000O0000OoO(this.O000O00000OoO.O00O0000o00O), this.O000O00000OoO.O00O0000o0O);
                }
                String string = sb.toString();
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("HttpTransport", "result: " + string);
                O0000O000000oO(string);
                O000O0000OoO.O0000O000000oO((Closeable) inputStream);
                O000O0000OoO.O0000O000000oO((Closeable) bufferedReader);
                O000O0000OoO.O0000O000000oO(this.O000O00000OoO.O0000O000000oO);
            } catch (Exception e5) {
                O000O0000OoO.O0000O000000oO(this.O000O00000OoO.O0000O000000oO);
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("HttpTransport", "HttpTransport getResponseCode failed: " + e5);
                O0000O000000oO(e5.getMessage(), 2);
            }
        }
    }

    public static class O000O00000o0O {
        public HttpURLConnection O0000O000000oO = null;
        public byte[] O000O00000OoO = null;
        public Map<String, String> O000O00000o0O = null;
        public int O000O00000oO = -1;
        public AbstractC0045O000O00000OoO O000O0000O0oO = null;
        public com.ishumei.O000O00000o0O.O000O00000OoO<O000O00000o0O> O000O0000OOoO = null;
        public boolean O000O0000Oo0O = false;
        public int O000O0000OoO = 0;
        public String O00O0000OooO = null;
        public String O00O0000o00O;
        public String O00O0000o0O;
    }

    public O000O00000OoO O0000O000000oO(com.ishumei.O000O0000O0oO.O0000O000000oO o0000O000000oO) {
        if (o0000O000000oO == null) {
            return null;
        }
        int length = o0000O000000oO.O000O0000OOoO().length;
        this.O000O00000OoO = o0000O000000oO.O000O00000oO();
        for (int i = 0; i < this.O000O0000O0oO; i++) {
            this.O000O00000o0O.add(o0000O000000oO.O000O0000O0oO());
        }
        for (int i2 = 0; i2 < length; i2++) {
            for (int i3 = 0; i3 < this.O000O0000OOoO; i3++) {
                this.O000O00000o0O.add(o0000O000000oO.O000O0000OOoO()[i2]);
            }
        }
        this.O000O00000oO = o0000O000000oO.O000O00000OoO() * 1000;
        this.O000O0000Oo0O = o0000O000000oO.O000O00000o0O() * 1000;
        this.O000O0000OoO = o0000O000000oO.O000O0000Oo0O() * 1000;
        if (1 == this.O000O00000OoO) {
            return this;
        }
        try {
            Certificate certificateGenerateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(O000O0000O0oO.O000O00000OoO));
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            keyStore.setCertificateEntry("smfp", certificateGenerateCertificate);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            this.O00O0000o0O = trustManagerFactory.getTrustManagers();
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            this.O00O0000OooO = sSLContext;
            sSLContext.init(null, this.O00O0000o0O, null);
            return this;
        } catch (Exception unused) {
            return null;
        }
    }

    public String O0000O000000oO(byte[] bArr, Map<String, String> map, String str) {
        return O0000O000000oO(bArr, map, str, false);
    }

    public String O0000O000000oO(byte[] bArr, Map<String, String> map, String str, boolean z) throws Throwable {
        InputStream inputStream;
        Closeable closeable;
        HttpURLConnection httpURLConnection;
        OutputStream outputStream;
        final String strO000O0000OoO;
        String strO0000O000000oO;
        HttpsURLConnection httpsURLConnection;
        SSLSocketFactory socketFactory;
        if (bArr == null || bArr.length == 0) {
            throw new IOException("data is null");
        }
        OutputStream outputStream2 = null;
        outputStream2 = null;
        HttpURLConnection httpURLConnection2 = null;
        try {
            strO000O0000OoO = O000O0000OoO.O000O0000OoO(str);
            strO0000O000000oO = com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO.O0000O000000oO().O0000O000000oO(strO000O0000OoO, z);
            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000OoO("HttpTransport", "IP of " + str + " : " + strO0000O000000oO);
            URL url = TextUtils.isEmpty(strO0000O000000oO) ? new URL(str) : new URL(Patterns.DOMAIN_NAME.matcher(str).replaceFirst(strO0000O000000oO));
            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000OoO("HttpTransport", "final URL: " + url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            try {
                if (this.O000O00000OoO == 0 && this.O00O0000o0O != null && this.O00O0000OooO != null) {
                    if (TextUtils.isEmpty(strO000O0000OoO) || Patterns.IP_ADDRESS.matcher(strO000O0000OoO).matches()) {
                        ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new O0000O000000oO());
                        httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                        socketFactory = this.O00O0000OooO.getSocketFactory();
                    } else {
                        ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new HostnameVerifier() { // from class: com.ishumei.O000O0000O0oO.O000O00000OoO.1
                            @Override // javax.net.ssl.HostnameVerifier
                            public boolean verify(String str2, SSLSession sSLSession) {
                                return HttpsURLConnection.getDefaultHostnameVerifier().verify(strO000O0000OoO, sSLSession);
                            }
                        });
                        httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                        socketFactory = this.O00O0000OooO.getSocketFactory();
                    }
                    httpsURLConnection.setSSLSocketFactory(socketFactory);
                }
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setRequestMethod(O0000O000000oO);
                httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
                httpURLConnection.setRequestProperty("Connection", "Close");
                httpURLConnection.setConnectTimeout(this.O000O00000oO);
                httpURLConnection.setReadTimeout(this.O000O0000Oo0O);
                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                httpURLConnection.connect();
                outputStream = httpURLConnection.getOutputStream();
            } catch (IOException e) {
                e = e;
                outputStream = null;
                inputStream = null;
            } catch (Throwable th) {
                th = th;
                inputStream = null;
                closeable = null;
            }
        } catch (IOException e2) {
            e = e2;
            outputStream = null;
            inputStream = null;
            closeable = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            closeable = null;
            httpURLConnection = null;
        }
        try {
            outputStream.write(bArr);
            outputStream.flush();
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("HttpTransport", str);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("HttpTransport", "responseCode ( " + responseCode + ")");
                throw new IOException("responseCode = " + responseCode);
            }
            InputStream inputStream2 = httpURLConnection.getInputStream();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream2));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    }
                    if (!TextUtils.isEmpty(strO0000O000000oO)) {
                        com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO.O0000O000000oO().O0000O000000oO(strO000O0000OoO, strO0000O000000oO);
                    }
                    String string = sb.toString();
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("HttpTransport", "result: " + string);
                    O000O0000OoO.O0000O000000oO((Closeable) outputStream);
                    O000O0000OoO.O0000O000000oO((Closeable) inputStream2);
                    O000O0000OoO.O0000O000000oO((Closeable) bufferedReader);
                    O000O0000OoO.O0000O000000oO(httpURLConnection);
                    return string;
                } catch (IOException e3) {
                    httpURLConnection2 = httpURLConnection;
                    inputStream = inputStream2;
                    e = e3;
                    closeable = bufferedReader;
                    try {
                        throw e;
                    } catch (Throwable th3) {
                        th = th3;
                        httpURLConnection = httpURLConnection2;
                        outputStream2 = outputStream;
                        O000O0000OoO.O0000O000000oO((Closeable) outputStream2);
                        O000O0000OoO.O0000O000000oO((Closeable) inputStream);
                        O000O0000OoO.O0000O000000oO(closeable);
                        O000O0000OoO.O0000O000000oO(httpURLConnection);
                        throw th;
                    }
                } catch (Throwable th4) {
                    outputStream2 = outputStream;
                    inputStream = inputStream2;
                    th = th4;
                    closeable = bufferedReader;
                    O000O0000OoO.O0000O000000oO((Closeable) outputStream2);
                    O000O0000OoO.O0000O000000oO((Closeable) inputStream);
                    O000O0000OoO.O0000O000000oO(closeable);
                    O000O0000OoO.O0000O000000oO(httpURLConnection);
                    throw th;
                }
            } catch (IOException e4) {
                closeable = null;
                httpURLConnection2 = httpURLConnection;
                inputStream = inputStream2;
                e = e4;
            } catch (Throwable th5) {
                closeable = null;
                outputStream2 = outputStream;
                inputStream = inputStream2;
                th = th5;
            }
        } catch (IOException e5) {
            e = e5;
            inputStream = null;
            closeable = inputStream;
            httpURLConnection2 = httpURLConnection;
            throw e;
        } catch (Throwable th6) {
            th = th6;
            inputStream = null;
            closeable = null;
            outputStream2 = outputStream;
            O000O0000OoO.O0000O000000oO((Closeable) outputStream2);
            O000O0000OoO.O0000O000000oO((Closeable) inputStream);
            O000O0000OoO.O0000O000000oO(closeable);
            O000O0000OoO.O0000O000000oO(httpURLConnection);
            throw th;
        }
    }

    public void O0000O000000oO(byte[] bArr, Map<String, String> map, AbstractC0045O000O00000OoO abstractC0045O000O00000OoO) {
        if (abstractC0045O000O00000OoO != null) {
            try {
                if (abstractC0045O000O00000OoO.O000O00000OoO == null) {
                    abstractC0045O000O00000OoO.O000O00000OoO = new O000O00000o0O();
                }
                abstractC0045O000O00000OoO.O000O00000OoO.O000O00000oO = 0;
                abstractC0045O000O00000OoO.O000O00000OoO.O000O00000OoO = bArr;
                abstractC0045O000O00000OoO.O000O00000OoO.O000O00000o0O = map;
                abstractC0045O000O00000OoO.O000O00000OoO.O000O0000Oo0O = true;
                abstractC0045O000O00000OoO.O000O00000OoO.O000O0000O0oO = abstractC0045O000O00000OoO;
                abstractC0045O000O00000OoO.O000O00000OoO.O000O0000OoO = this.O000O00000o0O.size();
                abstractC0045O000O00000OoO.O000O00000OoO.O00O0000o00O = this.O000O00000o0O.get(0);
                abstractC0045O000O00000OoO.O000O00000OoO.O000O0000OOoO = new com.ishumei.O000O00000o0O.O000O00000OoO<O000O00000o0O>(true, com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O0000O000000oO(), true, this.O000O0000OoO, false) { // from class: com.ishumei.O000O0000O0oO.O000O00000OoO.3
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public void run() {
                        O000O00000o0O o000O00000o0O = (O000O00000o0O) this.O000O0000OoO;
                        try {
                            if (o000O00000o0O == null) {
                                throw new Exception("sessionCache is null");
                            }
                            if (o000O00000o0O.O000O00000oO >= O000O00000OoO.this.O000O00000o0O.size()) {
                                return;
                            }
                            O000O00000OoO.this.O0000O000000oO(o000O00000o0O.O000O00000OoO, o000O00000o0O.O000O00000o0O, (String) O000O00000OoO.this.O000O00000o0O.get(o000O00000o0O.O000O00000oO), o000O00000o0O.O000O0000O0oO);
                        } catch (Exception e) {
                            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("HttpTransport", "transportWithRetry asyn failed: url: " + ((String) O000O00000OoO.this.O000O00000o0O.get(o000O00000o0O.O000O00000oO)) + " " + e);
                        }
                    }
                };
                abstractC0045O000O00000OoO.O000O00000OoO.O000O0000OOoO.O0000O000000oO(abstractC0045O000O00000OoO.O000O00000OoO);
            } catch (Exception e) {
                com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("HttpTransport", "transportWithRetry asyn failed: url: + " + this.O000O00000o0O.get(abstractC0045O000O00000OoO.O000O00000OoO.O000O00000oO) + " " + e);
                return;
            }
        }
        O0000O000000oO(bArr, map, this.O000O00000o0O.get(0), (AbstractC0045O000O00000OoO<?>) abstractC0045O000O00000OoO);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:(1:9)|10|(16:69|12|(1:14)|16|(1:18)(3:19|(1:21)|22)|23|71|24|(3:30|(1:36)(1:34)|35)|37|(3:39|(2:42|40)|77)|43|75|44|45|(2:61|62)(1:78))|15|16|(0)(0)|23|71|24|(6:26|28|30|(1:32)|36|35)|37|(0)|43|75|44|45|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0172, code lost:
    
        r11 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0173, code lost:
    
        r3 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0175, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0176, code lost:
    
        r1 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x017a, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x017b, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0072 A[Catch: all -> 0x017d, Exception -> 0x017f, TryCatch #0 {Exception -> 0x017f, blocks: (B:12:0x003b, B:16:0x0044, B:18:0x0072, B:23:0x008e, B:19:0x0078, B:21:0x0089), top: B:69:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0078 A[Catch: all -> 0x017d, Exception -> 0x017f, TryCatch #0 {Exception -> 0x017f, blocks: (B:12:0x003b, B:16:0x0044, B:18:0x0072, B:23:0x008e, B:19:0x0078, B:21:0x0089), top: B:69:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0122 A[Catch: Exception -> 0x017a, all -> 0x017d, TryCatch #2 {Exception -> 0x017a, blocks: (B:24:0x00a8, B:26:0x00ac, B:28:0x00b0, B:30:0x00b4, B:32:0x00ba, B:34:0x00c6, B:35:0x00da, B:36:0x00de, B:37:0x00f3, B:39:0x0122, B:40:0x012a, B:42:0x0130, B:43:0x0146), top: B:71:0x00a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x019b A[Catch: all -> 0x01bc, TRY_LEAVE, TryCatch #4 {all -> 0x01bc, blocks: (B:56:0x0182, B:58:0x019b), top: B:73:0x0182 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void O0000O000000oO(byte[] r11, java.util.Map<java.lang.String, java.lang.String> r12, java.lang.String r13, com.ishumei.O000O0000O0oO.O000O00000OoO.AbstractC0045O000O00000OoO<?> r14) {
        /*
            Method dump skipped, instruction units count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.O000O0000O0oO.O000O00000OoO.O0000O000000oO(byte[], java.util.Map, java.lang.String, com.ishumei.O000O0000O0oO.O000O00000OoO$O000O00000OoO):void");
    }
}
