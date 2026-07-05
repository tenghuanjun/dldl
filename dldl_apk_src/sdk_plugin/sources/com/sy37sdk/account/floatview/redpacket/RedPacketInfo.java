package com.sy37sdk.account.floatview.redpacket;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RedPacketInfo {
    public String imgLocalPath;
    public String imgUrl;
    public String jumpLink;
    public WebViewConfig webViewConfig;

    public static class WebViewConfig {
        public int height;
        public String pop_url;
        public int width;

        public String toString() {
            return "WebViewConfig{pop_url='" + this.pop_url + "', width='" + this.width + "', height='" + this.height + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public String toString() {
        return "RedPacketInfo{imgLocalPath='" + this.imgLocalPath + "', imgUrl='" + this.imgUrl + "', jumpLink='" + this.jumpLink + "', webViewConfig=" + this.webViewConfig + AbstractJsonLexerKt.END_OBJ;
    }
}
