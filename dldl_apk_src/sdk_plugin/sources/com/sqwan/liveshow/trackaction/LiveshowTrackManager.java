package com.sqwan.liveshow.trackaction;

import com.sqwan.common.mod.liveshow.IAudioLiveshowTrackManager;
import com.sqwan.common.mod.liveshow.LiveRadioEngine;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowTrackManager extends LiveshowTrackBaseManager implements IAudioLiveshowTrackManager {
    public static LiveshowTrackManager getInstance() {
        return (LiveshowTrackManager) LiveRadioEngine.getInstance().getLiveshowTrackManager();
    }

    public void turnOnAction(String str, String str2) {
        HashMap map = new HashMap(2);
        map.put("channel_id", str);
        map.put("anchor_id", str2);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.FM_TURN_ON, map);
    }

    public void turnOffAction(String str, String str2, String str3) {
        HashMap map = new HashMap(2);
        map.put("channel_id", str);
        map.put("anchor_id", str2);
        map.put("fm_time", str3);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.FM_TURN_OFF, map);
    }

    public void commentsAction(String str, String str2) {
        HashMap map = new HashMap(2);
        map.put("channel_id", str);
        map.put("anchor_id", str2);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.FM_COMMETNS, map);
    }
}
