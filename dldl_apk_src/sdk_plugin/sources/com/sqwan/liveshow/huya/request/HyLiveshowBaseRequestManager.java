package com.sqwan.liveshow.huya.request;

import android.content.Context;
import com.sdk.sq.net.RequestErrorCode;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.websocket_engine.NetworkConst;
import com.sq.websocket_engine.WebSocketCenter;
import com.sqnetwork.voly.NoConnectionError;
import com.sqwan.common.mod.CommonConfigs;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class HyLiveshowBaseRequestManager {
    protected final Context mContext;

    public HyLiveshowBaseRequestManager(Context context) {
        this.mContext = context;
    }

    protected long getCurrentTime() {
        return CommonConfigs.getInstance().getCurrentTime();
    }

    protected <T> void get(SqRequest sqRequest, SqHttpCallback<T> sqHttpCallback, Class<T> cls) {
        if (WebSocketCenter.getInstance().isLocalConnected()) {
            sqRequest.get(sqHttpCallback, cls);
        } else if (sqHttpCallback != null) {
            sqHttpCallback.onFailure(RequestErrorCode.ERROR_CONNECTION, NetworkConst.tips_no_network, new NoConnectionError());
        }
    }

    protected <T> void post(SqRequest sqRequest, SqHttpCallback<T> sqHttpCallback, Class<T> cls) {
        if (WebSocketCenter.getInstance().isLocalConnected()) {
            sqRequest.post(sqHttpCallback, cls);
        } else if (sqHttpCallback != null) {
            sqHttpCallback.onFailure(RequestErrorCode.ERROR_CONNECTION, NetworkConst.tips_no_network, new NoConnectionError());
        }
    }
}
