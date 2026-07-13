package com.bytedance.downloader.core;

import com.bytedance.dns.DnsResolver;
import com.bytedance.downloader.core.g;
import com.lzy.okgo.model.HttpHeaders;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public class m extends a implements r {
    private static /* synthetic */ boolean B = true;
    private static int h;
    private boolean A;
    private final int i;
    private final t j;
    private final ExecutorService k;
    private final DownloadConfig l;
    private final List m;
    private final boolean[] n;
    private l o;
    private long p;
    private int q;
    private final int r;
    private final int s;
    private final int t;
    private long u;
    private int v;
    private int w;
    private String x;
    private int y;
    private boolean z;

    public m(DnsResolver dnsResolver, ExecutorService executorService, DownloadConfig downloadConfig, g gVar, t tVar) {
        super(dnsResolver, downloadConfig, gVar);
        int i = h;
        h = i + 1;
        this.i = i;
        this.m = new ArrayList();
        this.n = new boolean[1];
        this.p = 0L;
        this.v = 0;
        this.w = 0;
        this.x = "";
        this.y = 0;
        this.z = false;
        this.A = false;
        h.a(String.format(Locale.getDefault(), "id:%d,entity:%s", Integer.valueOf(i), gVar.toString()));
        this.k = executorService;
        this.l = downloadConfig;
        this.j = tVar;
        int retryCount = downloadConfig.getRetryCount();
        this.r = retryCount;
        this.q = retryCount;
        int retryInterval = downloadConfig.getRetryInterval();
        this.s = retryInterval;
        this.t = downloadConfig.getRetryMode();
        this.u = retryInterval;
        this.o = new l();
    }

    private long a(int i) {
        if (!B && i <= 0) {
            throw new AssertionError();
        }
        long fileSize = this.b.getFileSize() / ((long) i);
        return fileSize > 8 ? fileSize - (fileSize % 8) : fileSize;
    }

    private static String a(HttpURLConnection httpURLConnection) {
        String strNextToken = "";
        try {
            String headerField = httpURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_CONTENT_DISPOSITION);
            if (headerField != null) {
                for (String str : headerField.split(";")) {
                    if (str.toLowerCase().contains("filename")) {
                        try {
                            strNextToken = str.substring(str.indexOf(34) + 1, str.lastIndexOf(34));
                        } catch (Exception unused) {
                            strNextToken = str.substring(str.indexOf(61) + 1);
                        }
                    }
                }
            } else {
                StringTokenizer stringTokenizer = new StringTokenizer(httpURLConnection.getURL().getFile(), "/");
                while (stringTokenizer.hasMoreTokens()) {
                    strNextToken = stringTokenizer.nextToken();
                }
            }
        } catch (Exception e) {
            h.a(e);
        }
        return strNextToken;
    }

    private void a(int i, String str) {
        h.c(String.format(Locale.getDefault(), "%d - %s", Integer.valueOf(i), str));
        a(DownloadState.DownloadFailed);
        t tVar = this.j;
        if (tVar != null) {
            tVar.b(this, i, str);
        }
        if (i != -3) {
            b(i, str);
            return;
        }
        String strB = b(this.b.e());
        if (q.a(strB)) {
            b(i, str);
            return;
        }
        this.b.a("host_ip", strB);
        this.b.a("host_ip_source", a(this.b.e(), strB) ? "2" : "3");
        this.b.a(strB);
        a();
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
        this.b.a(aVarA.a(str).b(strB).c(q.a()).a());
        a(i, exc.getMessage());
    }

    private void a(DownloadState downloadState) {
        if (downloadState != this.b.b()) {
            h.a(String.format(Locale.getDefault(), "id:%d %s->%s", Integer.valueOf(this.i), this.b.b().toString(), downloadState.toString()));
            this.b.a(downloadState);
        }
    }

    private void b(int i, String str) {
        int i2 = this.q;
        if (i2 <= 0) {
            t tVar = this.j;
            if (tVar != null) {
                tVar.a(this, i, str);
                return;
            }
            return;
        }
        this.q = i2 - 1;
        h.a("Retry download task again!!!");
        Timer timer = new Timer();
        timer.schedule(new o(this, timer), this.u);
        int i3 = this.t;
        if (i3 > 0) {
            this.u += ((long) this.s) * ((long) i3);
        }
    }

    private void g() {
        int iK = k();
        if (this.y != iK) {
            this.y = iK;
            t tVar = this.j;
            if (tVar != null) {
                tVar.a(this, iK);
            }
        }
    }

    private synchronized void h() {
        if (this.b.b() != DownloadState.Downloaded && this.b.b() != DownloadState.DownloadFailed && this.b.b() != DownloadState.VerifyFailed && this.b.b() != DownloadState.Cancelled) {
            int iL = l();
            if (iL == 4) {
                a(DownloadState.Cancelled);
                t tVar = this.j;
                if (tVar != null) {
                    tVar.d(this);
                }
            } else if (iL == 5) {
                a(this.w, this.x);
            } else if (iL == 6) {
                if (!this.l.isSupportFileVerification()) {
                    a(DownloadState.Downloaded);
                    t tVar2 = this.j;
                    if (tVar2 != null) {
                        tVar2.c(this);
                    }
                } else if (this.b.b() != DownloadState.Verifying) {
                    a(DownloadState.Verifying);
                    t tVar3 = this.j;
                    if (tVar3 != null) {
                        tVar3.e(this);
                    }
                    this.k.submit(new Runnable() { // from class: com.bytedance.downloader.core.m$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() throws Throwable {
                            this.f$0.i();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009b A[Catch: CancellationException -> 0x013d, TryCatch #1 {CancellationException -> 0x013d, blocks: (B:3:0x0002, B:5:0x0044, B:8:0x0054, B:10:0x0066, B:11:0x0072, B:17:0x0092, B:19:0x009b, B:12:0x0076, B:13:0x007c, B:20:0x00a0, B:22:0x00a6, B:25:0x00b7, B:27:0x00c3, B:29:0x00cf, B:30:0x00f8, B:32:0x00fc, B:33:0x012d, B:16:0x008f), top: B:40:0x0002, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void i() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.downloader.core.m.i():void");
    }

    private long j() {
        Iterator it = this.b.a().iterator();
        long jF = 0;
        while (it.hasNext()) {
            jF += ((b) it.next()).f();
        }
        return jF;
    }

    private int k() {
        return Math.max(0, this.b.getFileSize() == 0 ? 100 : (int) ((j() / this.b.getFileSize()) * 100.0f));
    }

    private int l() {
        synchronized (this) {
            Iterator it = this.m.iterator();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (it.hasNext()) {
                int iB = ((c) it.next()).b();
                if (iB == 0 || iB == 1 || iB == 2 || iB == 3) {
                    i++;
                } else {
                    if (iB == 4) {
                        i2++;
                    } else if (iB != 5) {
                    }
                    i3++;
                }
            }
            if (i > 0) {
                return 2;
            }
            if (i2 > 0) {
                return 4;
            }
            return i3 > 0 ? 5 : 6;
        }
    }

    private int m() {
        if (this.b.getFileSize() > 1024) {
            return this.l.getMaxChunk();
        }
        return 1;
    }

    public final void a() {
        h.a(String.format(Locale.getDefault(), "id:%d", Integer.valueOf(this.i)));
        if (this.b.b() == DownloadState.NotStart || this.b.b() == DownloadState.DownloadFailed) {
            this.n[0] = false;
            this.w = 0;
            this.x = "";
            this.m.clear();
            this.k.submit(this);
        }
    }

    @Override // com.bytedance.downloader.core.r
    public final void a(b bVar) {
        h.a(String.format(Locale.getDefault(), "id:%d", Integer.valueOf(bVar.a())));
        g();
        h();
    }

    @Override // com.bytedance.downloader.core.r
    public final void a(b bVar, int i, String str) {
        h.a(String.format(Locale.getDefault(), "id:%d,err:%d", Integer.valueOf(bVar.a()), Integer.valueOf(i)));
        this.w = i;
        this.x = str;
        h();
    }

    public final void b() {
        h.a(String.format(Locale.getDefault(), "id:%d", Integer.valueOf(this.i)));
        if (this.b.b() == DownloadState.Prepare || this.b.b() == DownloadState.Downloading || this.b.b() == DownloadState.Verifying) {
            boolean[] zArr = this.n;
            if (zArr[0]) {
                return;
            }
            zArr[0] = true;
            for (int i = 0; i < this.m.size(); i++) {
                ((c) this.m.get(i)).a();
            }
            return;
        }
        if (this.b.b() == DownloadState.NotStart) {
            a(DownloadState.Cancelled);
            t tVar = this.j;
            if (tVar != null) {
                tVar.d(this);
            }
        }
    }

    @Override // com.bytedance.downloader.core.r
    public final void b(b bVar) {
        h.a(String.format(Locale.getDefault(), "id:%d", Integer.valueOf(bVar.a())));
        h();
    }

    public final g c() {
        return this.b;
    }

    public final DownloadResponse d() {
        DownloadResponse downloadResponse = new DownloadResponse(this.b);
        try {
            downloadResponse.setProgress(k());
            downloadResponse.setState(this.b.b());
            downloadResponse.putExtras(this.b.f());
            downloadResponse.putExtra(DownloadExtra.extraLastState, this.b.c().toString());
            downloadResponse.putExtra("retry_count", Integer.toString(this.r - this.q));
        } catch (ConcurrentModificationException e) {
            h.a(e);
        }
        return downloadResponse;
    }

    @Override // com.bytedance.downloader.core.r
    public final void e() {
        g();
    }

    @Override // com.bytedance.downloader.core.r
    public final void f() {
        if (this.b.b() == DownloadState.Downloading) {
            g();
            long j = j();
            this.o.a(j - this.p);
            this.p = j;
            l lVar = this.o;
            if (lVar != null) {
                this.j.a(this, lVar.a());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:266:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x06fa  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x07f4  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0801  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x08ef  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x08fc  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x09e9  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x09f6  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x0ae3  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x0af0  */
    /* JADX WARN: Removed duplicated region for block: B:548:0x0bdd  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x0bea  */
    /* JADX WARN: Removed duplicated region for block: B:595:0x0cd8  */
    /* JADX WARN: Removed duplicated region for block: B:598:0x0ce5  */
    /* JADX WARN: Removed duplicated region for block: B:639:0x0dc8  */
    /* JADX WARN: Removed duplicated region for block: B:642:0x0dd5  */
    /* JADX WARN: Removed duplicated region for block: B:752:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:754:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:756:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:758:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:760:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:762:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:764:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:766:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:779:? A[SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 3763
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.downloader.core.m.run():void");
    }
}
