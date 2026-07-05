package com.bytedance.pangle.e;

import android.os.SystemClock;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.IZeusReporter;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.i;
import com.bytedance.pangle.util.l;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class f {

    interface a {
        boolean a(String str, int i);
    }

    public static void a() {
        GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_DEX_OPT, "start");
        if (GlobalParam.getInstance().isCloseBgDex2oat()) {
            return;
        }
        if ((i.e() || i.g() || i.b()) && com.bytedance.pangle.d.d.a(Zeus.getAppApplication())) {
            GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_DEX_OPT, "post");
            com.bytedance.pangle.d.e.a(new Runnable() { // from class: com.bytedance.pangle.e.f.1
                @Override // java.lang.Runnable
                public final void run() {
                    GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_DEX_OPT, "exec");
                    SystemClock.sleep(GlobalParam.getInstance().getDexOptDelayTime());
                    f.b();
                }
            });
        }
    }

    public static synchronized void b() {
        a cVar;
        Map<String, ?> all = b.a(Zeus.getAppApplication()).getAll();
        if (all.size() > 0) {
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                ZeusLogger.i(ZeusLogger.TAG_LOAD, "fullDex2oat start:" + entry.getKey());
                if (i.g()) {
                    cVar = new e();
                } else if (i.e()) {
                    cVar = new d();
                } else if (i.b()) {
                    cVar = new c();
                } else {
                    cVar = new a() { // from class: com.bytedance.pangle.e.f.2
                        @Override // com.bytedance.pangle.e.f.a
                        public final boolean a(String str, int i) {
                            return true;
                        }
                    };
                }
                if (cVar.a(entry.getKey(), ((Integer) entry.getValue()).intValue())) {
                    b.a(Zeus.getAppApplication()).edit().remove(entry.getKey()).apply();
                    ZeusLogger.i(ZeusLogger.TAG_LOAD, "fullDex2oat success:" + entry.getKey());
                    l.a().b(entry.getKey(), ((Integer) entry.getValue()).intValue(), true);
                    ZeusLogger.i(ZeusLogger.TAG_LOAD, "fullDex2oat markDexOptState:" + entry.getKey());
                } else {
                    ZeusLogger.i(ZeusLogger.TAG_LOAD, "fullDex2oat failed:" + entry.getKey());
                }
            }
        }
    }
}
