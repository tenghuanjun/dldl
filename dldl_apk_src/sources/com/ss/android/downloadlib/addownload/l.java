package com.ss.android.downloadlib.addownload;

import android.os.SystemClock;
import com.ss.android.downloadad.api.constant.AdBaseConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class l {
    private static final String a = l.class.getSimpleName();

    private static class b {
        private static l a = new l();
    }

    private l() {
    }

    public static l a() {
        return b.a;
    }

    public void a(com.ss.android.downloadad.api.a.b bVar) {
        com.ss.android.downloadlib.g.l.a().a(a, "tryListenInstallFinish", "开始通过轮询线程监听安装完成事件");
        com.ss.android.downloadlib.d.a().c(new a(bVar));
    }

    private class a implements Runnable {
        private final com.ss.android.downloadad.api.a.b b;

        a(com.ss.android.downloadad.api.a.b bVar) {
            this.b = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    this.b.j(true);
                    l.this.b(this.b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                this.b.j(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.ss.android.downloadad.api.a.b bVar) {
        JSONObject jSONObject = new JSONObject();
        int iA = com.ss.android.downloadlib.g.e.a(bVar);
        int iB = com.ss.android.downloadlib.g.e.b(bVar);
        if (com.ss.android.downloadlib.g.e.e(bVar) && com.ss.android.downloadlib.g.e.d(bVar)) {
            com.ss.android.downloadlib.g.l.a().a(a, "tryListenInstallFinishEvent", "上层库开启二级线程轮询检测策略");
            a(bVar, iA, iB, jSONObject);
        } else {
            com.ss.android.downloadlib.g.l.a().a(a, "tryListenInstallFinishEvent", "采用原有默认轮询策略");
            b(bVar, 15, 20000, jSONObject);
        }
    }

    private void a(com.ss.android.downloadad.api.a.b bVar, int i, int i2, JSONObject jSONObject) {
        int iF;
        if (bVar.K() == 4) {
            iF = k.j().optInt("market_install_finish_check_time", AdBaseConstants.DEFAULT_BROADCAST_CHECK_TIME);
        } else {
            iF = com.ss.android.downloadlib.g.e.f(bVar);
        }
        int i3 = ((iF * 1000) / 20000) + 1;
        com.ss.android.downloadlib.g.l.a().a(a, "realListenInstallFinishEventOpt", "一级轮询次数，即广播生效期内的轮询次数为:" + i3);
        if (b(bVar, i3, 20000, jSONObject)) {
            return;
        }
        if (((long) iF) * 1000 < com.ss.android.downloadlib.g.e.g(bVar)) {
            com.ss.android.downloadlib.g.l.a().a(a, "tryListenInstallFinishEventOpt", "广播生效时间外，一级轮询完成且没有检测到安装完成事件，开始二级检测");
            if (b(bVar, i, i2, jSONObject)) {
                return;
            }
            com.ss.android.downloadlib.g.l.a().a(a, "tryListenInstallFinishEventOpt", "监听时间结束,依然没有监听到安装完成事件");
            return;
        }
        com.ss.android.downloadlib.g.l.a().a(a, "tryListenInstallFinishEventOpt", "一级轮询时间小于广播监听时间,且未监听到安装完成事件");
    }

    private boolean b(com.ss.android.downloadad.api.a.b bVar, int i, int i2, JSONObject jSONObject) {
        com.ss.android.downloadlib.g.l.a().a(a, "realListenInstallFinishEvent", "开始轮询检测,轮询时间间隔为" + i2 + ",轮询次数为" + i);
        long j = (long) i2;
        SystemClock.sleep(j);
        while (i > 0) {
            if (com.ss.android.downloadlib.g.m.b(bVar)) {
                com.ss.android.downloadlib.a.a().a(bVar.e());
                com.ss.android.downloadlib.g.l.a().a(a, "realListenInstallFinishEvent", "检测到安装成功，当前剩余的轮询次数为" + i);
                return true;
            }
            i--;
            if (i == 0) {
                return false;
            }
            SystemClock.sleep(j);
        }
        return false;
    }
}
