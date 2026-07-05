package com.huya.mtp.utils.env;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EnvVarParams {
    String extCfgPath = null;
    boolean hasSettestEnv = false;
    boolean isTestEnv = false;
    boolean hasSetForceRelease = false;
    boolean forceRelease = false;
    String channelName = null;
    int hotFixVersionCode = -1;

    public final EnvVarParams setExtCfgPath(String str) {
        this.extCfgPath = str;
        return this;
    }

    public final EnvVarParams setIsTestEnv(boolean z) {
        this.hasSettestEnv = true;
        this.isTestEnv = z;
        return this;
    }

    public final EnvVarParams setForceRelease(boolean z) {
        this.hasSetForceRelease = true;
        this.forceRelease = z;
        return this;
    }

    public final EnvVarParams setChannelName(String str) {
        this.channelName = str;
        return this;
    }

    public final EnvVarParams setHotFixVersionCode(int i) {
        this.hotFixVersionCode = i;
        return this;
    }
}
