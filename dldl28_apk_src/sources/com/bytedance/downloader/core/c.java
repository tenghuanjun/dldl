package com.bytedance.downloader.core;

import com.bytedance.dns.DnsResolver;
import com.bytedance.downloader.core.g;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends a {
    private final b h;
    private final r i;
    private final l j;
    private final int k;
    private int l;

    public c(DnsResolver dnsResolver, DownloadConfig downloadConfig, g gVar, b bVar, r rVar) {
        super(dnsResolver, downloadConfig, gVar);
        this.l = 0;
        this.h = bVar;
        this.k = downloadConfig.getBandwidthLimit();
        this.i = rVar;
        this.j = new l();
    }

    private void a(int i) {
        h.a(String.format(Locale.getDefault(), "id:%d, state:%d", Integer.valueOf(this.h.a()), Integer.valueOf(i)));
        this.l = i;
    }

    private void a(int i, String str, String str2, int i2, long j, Exception exc) {
        h.c(exc.getMessage());
        g.a aVar = new g.a(this.b);
        if (q.a(str2)) {
            str2 = c(str);
        }
        String strB = b(str2);
        g.a aVarA = aVar.a(exc).a(i2).a(System.currentTimeMillis() - j);
        if (q.a(str)) {
            str = "about:blank";
        }
        Map mapA = aVarA.a(str).b(strB).c(q.a()).a();
        mapA.put(DownloadExtra.extraChunkState, Integer.toString(this.l));
        this.b.a(mapA);
        a(5);
        r rVar = this.i;
        if (rVar != null) {
            rVar.a(this.h, i, exc.getMessage());
        }
    }

    public final void a() {
        int i = this.l;
        if (i == 0 || i == 1 || i == 2) {
            a(3);
        }
    }

    public final int b() {
        return this.l;
    }

    /*  JADX ERROR: Type inference failed with stack overflow
        jadx.core.utils.exceptions.JadxOverflowException
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    @Override // java.lang.Runnable
    public final void run() {
        /*
            Method dump skipped, instruction units count: 3092
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.downloader.core.c.run():void");
    }
}
