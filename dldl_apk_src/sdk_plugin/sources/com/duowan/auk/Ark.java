package com.duowan.auk;

import android.os.Bundle;
import com.duowan.auk.module.ArkModule;
import com.duowan.auk.util.L;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Ark {
    private static Map<Class<? extends ArkModule>, ModuleInfo> msRunningModule = new HashMap();

    public static boolean startModule(Class<? extends ArkModule> cls) {
        return startModule(cls, new Bundle());
    }

    public static <T extends ArkModule> T getModule(Class<? extends ArkModule> cls) {
        ModuleInfo moduleInfo;
        if (cls == null || (moduleInfo = msRunningModule.get(cls)) == null) {
            return null;
        }
        return (T) moduleInfo.module;
    }

    public static boolean startModule(Class<? extends ArkModule> cls, Bundle bundle) {
        L.info(Ark.class, "start module: %s", cls.getSimpleName());
        if (msRunningModule.containsKey(cls)) {
            msRunningModule.get(cls).dependTime++;
        } else {
            try {
                ArkModule arkModuleNewInstance = cls.newInstance();
                ModuleInfo moduleInfo = new ModuleInfo();
                moduleInfo.module = arkModuleNewInstance;
                moduleInfo.dependTime = 1;
                msRunningModule.put(cls, moduleInfo);
                arkModuleNewInstance.setArguments(bundle);
                arkModuleNewInstance.onStart();
            } catch (IllegalAccessException unused) {
                ArkUtils.crashIfDebug("start %s module fail(IllegalAccessException)!", cls.getSimpleName());
                return false;
            } catch (InstantiationException unused2) {
                ArkUtils.crashIfDebug("start %s module fail(InstantiationException)!", cls.getSimpleName());
                return false;
            }
        }
        return true;
    }

    public static boolean stopModule(Class<? extends ArkModule> cls) {
        L.info(Ark.class, "stop module: %s", cls.getSimpleName());
        ModuleInfo moduleInfo = msRunningModule.get(cls);
        if (moduleInfo == null) {
            ArkUtils.crashIfDebug("stop null module: %s", cls.getSimpleName());
            return false;
        }
        moduleInfo.dependTime--;
        if (moduleInfo.dependTime == 0) {
            moduleInfo.module.onStop();
            msRunningModule.remove(cls);
        }
        return true;
    }

    private static class ModuleInfo {
        public int dependTime;
        public ArkModule module;

        private ModuleInfo() {
        }
    }
}
