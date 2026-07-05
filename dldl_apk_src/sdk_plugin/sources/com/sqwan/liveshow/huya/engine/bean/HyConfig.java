package com.sqwan.liveshow.huya.engine.bean;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class HyConfig {
    public String appId = "";
    public String appKey = "";
    public int gameId;
    public HyUiConfig hyUiConfig;

    public static class HyUiConfig {
        public boolean landscapeMode = true;

        public String toString() {
            return "HyUiConfig{landscapeMode=" + this.landscapeMode + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public String toString() {
        return "HyConfig{gameId=" + this.gameId + ", appId='" + this.appId + "', appKey='" + this.appKey + "', hyUiConfig=" + this.hyUiConfig + AbstractJsonLexerKt.END_OBJ;
    }
}
