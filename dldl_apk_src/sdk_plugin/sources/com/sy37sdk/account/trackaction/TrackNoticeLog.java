package com.sy37sdk.account.trackaction;

import android.content.Context;
import com.sq.touch.CollectCallback;
import com.sq.touch.IGetEngineInterface;
import com.sq.touch.IGetUserInterface;
import com.sq.touch.TTAction;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sy37sdk.account.AccountCache;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TrackNoticeLog {
    public static void noticeLog(final Context context) {
        if (AccountCache.getTouch(context)) {
            TTAction.getInstance().setGetUserInterface(new IGetUserInterface() { // from class: com.sy37sdk.account.trackaction.TrackNoticeLog.1
                @Override // com.sq.touch.IGetUserInterface
                public String getUid() {
                    return AccountCache.getUserid(context);
                }
            });
            TTAction.getInstance().setGetEngineInterface(new IGetEngineInterface() { // from class: com.sy37sdk.account.trackaction.TrackNoticeLog.2
                @Override // com.sq.touch.IGetEngineInterface
                public String getEngine() {
                    return "";
                }
            });
            TTAction.getInstance().initCollect(context, new CollectCallback() { // from class: com.sy37sdk.account.trackaction.TrackNoticeLog.3
                @Override // com.sq.touch.CollectCallback
                public void onFail() {
                }

                @Override // com.sq.touch.CollectCallback
                public void onSuccess(Map map) {
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.TOUCH, (Map<String, String>) map);
                }
            });
        }
    }
}
