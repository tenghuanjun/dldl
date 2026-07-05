package com.aliyun.aliyunface.config;

import com.ss.android.socialbase.downloader.BuildConfig;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class SceneEnv {
    private String sceneCode = "";
    private String sceneType = BuildConfig.FLAVOR;

    public void setSceneCode(String str) {
        this.sceneCode = str;
    }

    public String getSceneCode() {
        return this.sceneCode;
    }

    public void setSceneType(String str) {
        this.sceneType = str;
    }

    public String getSceneType() {
        return this.sceneType;
    }

    public String toString() {
        return "SceneEnv{sceneCode='" + this.sceneCode + "', sceneType='" + this.sceneType + "'}";
    }
}
