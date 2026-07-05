package com.huya.mtp.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EnvVarApiDelegate implements EnvVarApi {
    private EnvVarApi mEnvVarApi;

    public EnvVarApiDelegate() {
    }

    public EnvVarApiDelegate(EnvVarApi envVarApi) {
        this.mEnvVarApi = envVarApi;
    }

    public void setEnvVarApi(EnvVarApi envVarApi) {
        this.mEnvVarApi = envVarApi;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public String getVersionName() {
        EnvVarApi envVarApi = this.mEnvVarApi;
        if (envVarApi != null) {
            return envVarApi.getVersionName();
        }
        return null;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public int getVersionCode() {
        EnvVarApi envVarApi = this.mEnvVarApi;
        if (envVarApi != null) {
            return envVarApi.getVersionCode();
        }
        return 0;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public int getHotFixVersionCode() {
        EnvVarApi envVarApi = this.mEnvVarApi;
        if (envVarApi != null) {
            return envVarApi.getHotFixVersionCode();
        }
        return 0;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public String getChannelName() {
        EnvVarApi envVarApi = this.mEnvVarApi;
        if (envVarApi != null) {
            return envVarApi.getChannelName();
        }
        return null;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public boolean isTestEnv() {
        EnvVarApi envVarApi = this.mEnvVarApi;
        if (envVarApi != null) {
            return envVarApi.isTestEnv();
        }
        return false;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public boolean isSnapshot() {
        EnvVarApi envVarApi = this.mEnvVarApi;
        if (envVarApi != null) {
            return envVarApi.isSnapshot();
        }
        return false;
    }

    @Override // com.huya.mtp.api.EnvVarApi
    public boolean isDebuggable() {
        EnvVarApi envVarApi = this.mEnvVarApi;
        if (envVarApi != null) {
            return envVarApi.isDebuggable();
        }
        return false;
    }
}
