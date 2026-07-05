package com.bytedance.pangle.f;

import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.c;
import com.bytedance.pangle.d;
import com.bytedance.pangle.i;
import com.bytedance.pangle.log.IZeusReporter;
import com.bytedance.pangle.plugin.PluginManager;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a extends c.a {
    private static volatile a a;

    public static a b() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    @Override // com.bytedance.pangle.c
    public final boolean a(String str) {
        return PluginManager.getInstance().checkPluginInstalled(str);
    }

    @Override // com.bytedance.pangle.c
    public final int b(String str) {
        return PluginManager.getInstance().getPlugin(str).getVersion();
    }

    @Override // com.bytedance.pangle.c
    public final boolean a(String str, String str2) {
        GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_PLUGIN_INSTALL, "start install pkg:" + str + ", path:" + str2);
        return PluginManager.getInstance().syncInstall(str, new File(str2));
    }

    @Override // com.bytedance.pangle.c
    public final void a(int i, d dVar) {
        Zeus.registerPluginStateListener(new c(dVar, i));
    }

    @Override // com.bytedance.pangle.c
    public final void a(int i) {
        ZeusPluginStateListener next;
        List<ZeusPluginStateListener> list = i.a().b;
        Iterator<ZeusPluginStateListener> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if ((next instanceof c) && ((c) next).a == i) {
                break;
            }
        }
        if (next != null) {
            list.remove(next);
        }
    }
}
