package com.ss.android.downloadlib.addownload;

import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class f {
    private final ConcurrentHashMap<String, String> a;
    private final ConcurrentHashMap<String, String> b;

    public static class a {
        private static f a = new f();
    }

    public static f a() {
        return a.a;
    }

    private f() {
        this.a = new ConcurrentHashMap<>();
        this.b = new ConcurrentHashMap<>();
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || this.b.containsKey(str2)) {
            return;
        }
        this.b.put(str2, str);
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str) || this.b.isEmpty() || !this.b.containsKey(str)) {
            return null;
        }
        String strC = c(str);
        if (this.a.containsValue(strC)) {
            for (Map.Entry<String, String> entry : this.a.entrySet()) {
                if (TextUtils.equals(entry.getValue(), strC)) {
                    String str2 = this.b.get(entry.getKey());
                    this.b.put(str, str2);
                    if (!this.a.containsKey(str)) {
                        this.a.put(str, strC);
                    }
                    return str2;
                }
            }
        }
        return this.b.get(str);
    }

    public String a(DownloadModel downloadModel) {
        String strC = c(downloadModel.getDownloadUrl());
        if (strC == null || TextUtils.isEmpty(strC)) {
            return null;
        }
        String strMd5Hex = DownloadUtils.md5Hex(strC + downloadModel.getPackageName());
        this.b.put(downloadModel.getDownloadUrl(), strMd5Hex);
        return strMd5Hex;
    }

    private String c(String str) {
        try {
            Uri uri = Uri.parse(str);
            String scheme = uri.getScheme();
            String lastPathSegment = uri.getLastPathSegment();
            if (!TextUtils.equals("https", scheme) || !lastPathSegment.endsWith(".apk")) {
                return null;
            }
            this.a.put(str, lastPathSegment);
            return lastPathSegment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b(String str) {
        Iterator<Map.Entry<String, String>> it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            if (TextUtils.equals(next.getValue(), str)) {
                it.remove();
                this.a.remove(next.getKey());
            }
        }
    }
}
