package com.sqwan.liveshow.huya.trackaction.bean;

import com.google.sqgson.Gson;
import com.google.sqgson.GsonBuilder;
import com.google.sqgson.reflect.TypeToken;
import com.sqwan.liveshow.huya.engine.LiveshowManager;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DanmuBaseTrackBean {
    public String channel_id = LiveshowManager.getInstance().getPlatformID() + "";
    public String anchor_roomid = LiveshowManager.getInstance().getRoomId();
    public String anchor_name = LiveshowManager.getInstance().liveInfoEx.liveListInfo.nickName;
    public String anchor_id = LiveshowManager.getInstance().liveInfoEx.liveListInfo.uid + "";

    public HashMap<String, String> toMap() {
        return (HashMap) new GsonBuilder().enableComplexMapKeySerialization().create().fromJson(new Gson().toJson(this), new TypeToken<HashMap<String, String>>() { // from class: com.sqwan.liveshow.huya.trackaction.bean.DanmuBaseTrackBean.1
        }.getType());
    }
}
