package com.sqwan.liveshow.huya.engine;

import com.huya.berry.client.HuyaBerry;
import com.huya.berry.client.customui.CustomUICallback;
import com.huya.berry.client.customui.model.LiveInfo;
import com.huya.berry.client.customui.model.LiveListInfo;
import com.sqwan.common.util.LogUtil;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveRoomRequestManager {
    private final String TAG = getClass().getSimpleName();

    public static class CustomUICallbackAdapter<T> {
        public void onFail() {
        }

        public void onSuccess(T t) {
        }

        public void onResult(boolean z, T t) {
            if (z) {
                onSuccess(t);
            } else {
                onFail();
            }
        }
    }

    public void getLiveListDataFirst(final CustomUICallbackAdapter<LiveListInfo> customUICallbackAdapter) {
        HuyaBerry.instance().getLiveListData(true, new CustomUICallback<LiveListInfo>() { // from class: com.sqwan.liveshow.huya.engine.LiveRoomRequestManager.1
            @Override // com.huya.berry.client.customui.CustomUICallback
            public void onResultCallback(int i, LiveListInfo liveListInfo) {
                LogUtil.i(LiveRoomRequestManager.this.TAG, "onResultCallback");
            }

            @Override // com.huya.berry.client.customui.CustomUICallback
            public void onResultListCallback(int i, List<LiveListInfo> list) {
                LogUtil.i(LiveRoomRequestManager.this.TAG, "onResultListCallback");
                if (i == 0 && list != null && !list.isEmpty()) {
                    LiveListInfo liveListInfo = list.get(0);
                    long j = liveListInfo.uid;
                    LogUtil.i(LiveRoomRequestManager.this.TAG, "channelId:" + j);
                    customUICallbackAdapter.onResult(true, liveListInfo);
                    return;
                }
                customUICallbackAdapter.onResult(false, null);
            }
        });
    }

    public void getLiveData(long j, final CustomUICallbackAdapter<LiveInfo> customUICallbackAdapter) {
        HuyaBerry.instance().getLiveData(j, new CustomUICallback<LiveInfo>() { // from class: com.sqwan.liveshow.huya.engine.LiveRoomRequestManager.2
            @Override // com.huya.berry.client.customui.CustomUICallback
            public void onResultListCallback(int i, List list) {
            }

            @Override // com.huya.berry.client.customui.CustomUICallback
            public void onResultCallback(int i, LiveInfo liveInfo) {
                if (i == 0 && liveInfo.roomId != 0) {
                    customUICallbackAdapter.onResult(true, liveInfo);
                } else {
                    customUICallbackAdapter.onResult(false, null);
                }
            }
        });
    }
}
