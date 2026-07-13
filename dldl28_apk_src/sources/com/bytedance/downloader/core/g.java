package com.bytedance.downloader.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends DownloadInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f355a;
    private DownloadState b;
    private DownloadState c;
    private String d;
    private final Map e;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final g f356a;
        private Exception b = null;
        private int c = -1;
        private long d = -1;
        private String e = "";
        private String f = "";
        private String g = "";
        private String h = "";
        private String i = "";
        private String j = "";
        private String k = "";

        public a(g gVar) {
            this.f356a = gVar;
        }

        public final a a(int i) {
            this.c = i;
            return this;
        }

        public final a a(long j) {
            this.d = j;
            return this;
        }

        public final a a(Exception exc) {
            this.b = exc;
            return this;
        }

        public final a a(String str) {
            this.e = str;
            return this;
        }

        public final Map a() {
            HashMap map = new HashMap();
            map.put(DownloadExtra.extraFileName, this.f356a.getFileName());
            map.put(DownloadExtra.extraSavePath, this.f356a.getSavePath());
            map.put("file_size", Long.toString(this.f356a.getFileSize()));
            Exception exc = this.b;
            if (exc != null) {
                map.put("original_exception", exc.getClass().toString());
                map.put("original_exception_message", this.b.getMessage());
                String strC = q.c(this.b.getMessage());
                if (!q.a(strC)) {
                    map.put("host_ip", strC);
                    map.put("host_ip_source", "0");
                }
            }
            int i = this.c;
            if (i > 0) {
                map.put("http_response_code", Integer.toString(i));
            }
            long j = this.d;
            if (j >= 0) {
                map.put("connect_duration", Long.toString(j));
            }
            if (!q.a(this.e)) {
                map.put("http_url", this.e);
            }
            if (!q.a(this.f)) {
                map.put("master_host", this.f);
            }
            if (!q.a(this.g)) {
                map.put("host", this.g);
            }
            if (!q.a(this.h)) {
                map.put("host_ip", this.h);
            }
            if (!q.a(this.i)) {
                map.put("host_ip_source", this.i);
            }
            if (!q.a(this.j)) {
                map.put("dns_cache_ip", this.j);
            }
            if (!q.a(this.k)) {
                map.put("stack_trace", this.k);
            }
            return map;
        }

        public final a b(String str) {
            this.j = str;
            return this;
        }

        public final a c(String str) {
            this.k = str;
            return this;
        }
    }

    public g(DownloadInfo downloadInfo) {
        super(downloadInfo);
        this.f355a = new ArrayList();
        this.b = DownloadState.NotStart;
        this.c = DownloadState.NotStart;
        this.d = "";
        this.e = new HashMap();
    }

    public final List a() {
        return this.f355a;
    }

    public final void a(DownloadState downloadState) {
        this.c = this.b;
        this.b = downloadState;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(String str, String str2) {
        if (!q.a(str) && !q.a(str2)) {
            this.e.put(str, str2);
        }
        if (q.a(str) || !q.a(str2)) {
            return;
        }
        this.e.remove(str);
    }

    public final void a(Map map) {
        this.e.putAll(map);
    }

    public final DownloadState b() {
        return this.b;
    }

    public final DownloadState c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        try {
            return new URI(getUrl()).getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    public final Map f() {
        return this.e;
    }
}
