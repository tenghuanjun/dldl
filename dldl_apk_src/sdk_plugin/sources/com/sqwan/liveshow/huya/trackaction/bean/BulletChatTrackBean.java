package com.sqwan.liveshow.huya.trackaction.bean;

import com.sqwan.liveshow.huya.trackaction.LiveshowTrackEvent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BulletChatTrackBean extends DanmuBaseTrackBean {
    public String content;
    public String speaking_position = "";
    public String forbidden_or_not = "";
    public String state = "";
    public String event = LiveshowTrackEvent.BULLET_CHAT.getEvent();
}
