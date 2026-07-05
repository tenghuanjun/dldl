package com.sqwan.liveshow.huya.trackaction;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public enum LiveshowTrackEvent {
    BUILTIN_LIVE_ICON("builtin_live_icon", "入口点击"),
    BUILTIN_LIVE_WATCH("builtin_live_watch", "进入直播间"),
    BUILTIN_LIVE_OFF("builtin_live_off", "退出直播间"),
    BULLET_CHAT("bullet_chat", "弹幕发言"),
    BUILTIN_LIVE_HALF_OFF("builtin_live_half_off", "退出半屏模式直播间"),
    BUILTIN_LIVE_HALF_WATCH("builtin_live_half_watch", "进入半屏模式直播间"),
    BUILTIN_LIVE_LISTENING("builtin_live_listening", "进入仅听声音模式"),
    BUILTIN_LIVE_LISTENING_OFF("builtin_live_listening_off", "退出仅听声音模式");

    private String event;
    private String name;

    LiveshowTrackEvent(String str, String str2) {
        this.event = str;
        this.name = str2;
    }

    public String getEvent() {
        return this.event;
    }

    public String getName() {
        return this.name;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "LiveshowTrackEvent{event='" + this.event + "', name='" + this.name + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
