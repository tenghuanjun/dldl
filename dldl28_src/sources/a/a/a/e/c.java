package a.a.a.e;

import com.lzy.okgo.model.HttpHeaders;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: compiled from: RealRequest.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    /* JADX WARN: Can't wrap try/catch for region: R(9:73|60|3|(4:78|4|75|5)|(6:7|64|8|(4:9|(1:11)(1:80)|72|45)|12|13)(1:16)|17|70|18|(3:23|72|45)(1:82)) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0081, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0082, code lost:
    
        r7.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ce A[Catch: IOException -> 0x00d2, TRY_ENTER, TRY_LEAVE, TryCatch #6 {IOException -> 0x00d2, blocks: (B:23:0x0087, B:41:0x00ce), top: B:73:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r7, java.lang.String r8, a.a.a.e.d r9) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a.e.c.a(java.lang.String, java.lang.String, a.a.a.e.d):void");
    }

    public final HttpURLConnection a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        } catch (Exception e) {
            e = e;
        }
        try {
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod(str2);
            httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/json;charset=utf-8");
            return httpURLConnection;
        } catch (Exception e2) {
            e = e2;
            httpURLConnection2 = httpURLConnection;
            a.a.a.g.b.a("RealRequest", "getHttpURLConnection error:" + e.getMessage());
            e.printStackTrace();
            return httpURLConnection2;
        }
    }
}
