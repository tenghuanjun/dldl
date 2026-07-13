package com.volcengine.common.plugin;

import android.util.Log;
import android.util.Pair;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.common.contant.CommonErrorCode;
import com.volcengine.common.innerapi.PluginService;
import com.volcengine.j.k;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
class b {
    private final ClassLoader a;
    private final String b;
    private final String c;

    private b(ClassLoader classLoader, String str, String str2) {
        this.a = classLoader;
        this.b = str;
        this.c = str2;
    }

    private Pair<Integer, String> a(PluginConfig pluginConfig, Map<String, String> map) {
        String str;
        Pair<Integer, String> pair;
        try {
            k.a(this.a, pluginConfig.check_plugin_dex_ready_class);
            SDKContext.getMonitorService().reportCategory(CommonConstants.event_checkPluginSucceed, Collections.singletonMap(CommonConstants.KEY_PLUGIN_NAME, pluginConfig.plugin_name));
            str = "";
            pair = null;
        } catch (Throwable th) {
            String stackTraceString = Log.getStackTraceString(th);
            Pair<Integer, String> pair2 = CommonErrorCode.ERROR_CHECK_PLUGIN_FAILED;
            com.volcengine.i.b bVarA = new com.volcengine.i.b().a(((Integer) pair2.first).intValue()).a((String) pair2.second).b("error").c(stackTraceString).a(CommonConstants.KEY_PLUGIN_NAME, pluginConfig.plugin_name);
            for (String str2 : map.keySet()) {
                bVarA.a(str2, map.get(str2));
            }
            SDKContext.getMonitorService().reportCategory(CommonConstants.event_checkPluginFailed, bVarA.a());
            str = stackTraceString;
            pair = pair2;
        }
        StringBuilder sb = new StringBuilder("checkPluginLoaded: ret:");
        sb.append(pair == null);
        sb.append(", msg: ");
        sb.append(str);
        AcLog.v(PluginService.TAG_PLUGIN, sb.toString());
        return pair;
    }

    public static b a(c cVar) {
        return new b(cVar.e(), cVar.b(), cVar.f());
    }

    private void a(PluginConfig pluginConfig) throws IllegalAccessException, NoSuchFieldException, IOException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        AcLog.v(PluginService.TAG_PLUGIN, "installPlugin: >>> " + pluginConfig.plugin_name);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = pluginConfig.dex_list.iterator();
        while (it.hasNext()) {
            arrayList.add(new File(this.b + it.next()));
        }
        k.a(this.a, new File(this.b), arrayList, Collections.singletonList(new File(this.c)));
        AcLog.v(PluginService.TAG_PLUGIN, "installPlugin: <<< " + pluginConfig.plugin_name + ", cost: " + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    public Pair<Integer, String> b(PluginConfig pluginConfig, Map<String, String> map) {
        try {
            SDKContext.getMonitorService().reportCategory(CommonConstants.event_injectPlugin, Collections.singletonMap(CommonConstants.KEY_PLUGIN_NAME, pluginConfig.plugin_name));
            a(pluginConfig);
            SDKContext.getMonitorService().reportCategory(CommonConstants.event_injectPluginSucceed, Collections.singletonMap(CommonConstants.KEY_PLUGIN_NAME, pluginConfig.plugin_name));
            return a(pluginConfig, map);
        } catch (Throwable th) {
            String stackTraceString = Log.getStackTraceString(th);
            AcLog.e(PluginService.TAG_PLUGIN, stackTraceString);
            Pair<Integer, String> pair = CommonErrorCode.ERROR_INJECT_DEX_FAILED;
            SDKContext.getMonitorService().reportCategory(CommonConstants.event_injectPluginFailed, new com.volcengine.i.b().a(CommonConstants.KEY_PLUGIN_NAME, pluginConfig.plugin_name).a(((Integer) pair.first).intValue()).a((String) pair.second).c(stackTraceString).b("error").a());
            return pair;
        }
    }
}
