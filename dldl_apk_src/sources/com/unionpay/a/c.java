package com.unionpay.a;

import com.unionpay.utils.j;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public final class c {
    private String a = null;
    private InputStream b = null;
    private d c;
    private String d;

    public c(d dVar, String str) {
        this.c = null;
        this.c = dVar;
        this.d = str;
    }

    /* JADX WARN: Finally extract failed */
    public final int a() {
        HttpURLConnection httpURLConnection;
        j.a("uppay", "HttpConn.connect() +++");
        d dVar = this.c;
        int i = 1;
        try {
            try {
                if (dVar == null) {
                    j.c("uppay", "params==null!!!");
                    return 1;
                }
                try {
                    try {
                        try {
                            URL urlA = dVar.a();
                            if ("https".equals(urlA.getProtocol().toLowerCase())) {
                                httpURLConnection = (HttpsURLConnection) urlA.openConnection();
                                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(new a(this.d).a().getSocketFactory());
                            } else {
                                httpURLConnection = (HttpURLConnection) urlA.openConnection();
                            }
                            httpURLConnection.setRequestMethod(this.c.b());
                            httpURLConnection.setReadTimeout(60000);
                            httpURLConnection.setConnectTimeout(30000);
                            httpURLConnection.setInstanceFollowRedirects(true);
                            httpURLConnection.setUseCaches(false);
                            HashMap mapD = this.c.d();
                            if (mapD != null) {
                                for (String str : mapD.keySet()) {
                                    httpURLConnection.setRequestProperty(str, (String) mapD.get(str));
                                }
                            }
                            String strB = this.c.b();
                            byte b = -1;
                            int iHashCode = strB.hashCode();
                            if (iHashCode != 70454) {
                                if (iHashCode == 2461856 && strB.equals("POST")) {
                                    b = 1;
                                }
                            } else if (strB.equals("GET")) {
                                b = 0;
                            }
                            if (b == 1) {
                                httpURLConnection.setDoOutput(true);
                                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                                outputStreamWriter.write(this.c.c());
                                outputStreamWriter.flush();
                                outputStreamWriter.close();
                            }
                            httpURLConnection.connect();
                            if (httpURLConnection.getResponseCode() == 200) {
                                InputStream inputStream = httpURLConnection.getInputStream();
                                this.b = inputStream;
                                if (inputStream != null) {
                                    this.a = com.unionpay.utils.b.a(inputStream, "UTF-8");
                                    i = 0;
                                }
                            } else if (httpURLConnection.getResponseCode() == 401) {
                                i = 8;
                            } else if (httpURLConnection.getResponseCode() == 404) {
                                i = 22;
                            } else {
                                j.c("uppay", "http status code:" + httpURLConnection.getResponseCode());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (this.b != null) {
                            }
                        }
                    } catch (SSLHandshakeException e2) {
                        e2.printStackTrace();
                        i = 4;
                        if (this.b != null) {
                        }
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                    if (this.b != null) {
                    }
                } catch (IllegalStateException e4) {
                    e4.printStackTrace();
                    if (this.b != null) {
                    }
                }
                if (this.b != null) {
                    this.b.close();
                }
            } catch (Exception unused) {
            }
            j.a("uppay", "HttpConn.connect() ---");
            return i;
        } catch (Throwable th) {
            try {
                if (this.b != null) {
                    this.b.close();
                }
            } catch (Exception unused2) {
            }
            throw th;
        }
    }

    public final String b() {
        return this.a;
    }
}
