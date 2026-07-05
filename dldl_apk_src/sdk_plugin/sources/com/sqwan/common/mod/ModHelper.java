package com.sqwan.common.mod;

import com.sqwan.common.mod.config.IConfigMod;
import com.sqwan.common.mod.plugin.IPluginMod;
import com.sqwan.common.mod.track.ITrackMod;
import com.sqwan.common.mod.track.ITrackMod2;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ModHelper {
    public static <T extends IModBase> T get(Class<T> cls) {
        return (T) ModManager.getInstance().getMod(cls);
    }

    public static IPluginMod getPluginMod() {
        return (IPluginMod) ModManager.getInstance().getMod(IPluginMod.class);
    }

    public static ITrackMod getTrackMod() {
        return (ITrackMod) ModManager.getInstance().getMod(ITrackMod.class);
    }

    public static ITrackMod2 getTrackMod2() {
        return (ITrackMod2) ModManager.getInstance().getMod(ITrackMod2.class);
    }

    public static IConfigMod getConfig() {
        return (IConfigMod) ModManager.getInstance().getMod(IConfigMod.class);
    }
}
