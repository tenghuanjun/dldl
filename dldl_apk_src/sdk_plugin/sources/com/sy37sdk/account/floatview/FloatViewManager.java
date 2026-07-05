package com.sy37sdk.account.floatview;

import com.sq.websocket_engine.ARecInfMsg;
import com.sq.websocket_engine.ReqWrapperHandler;
import com.sq.websocket_engine.WebSocketEngine;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.account.floatview.data.RedDot;
import com.sy37sdk.account.floatview.request.FloatRequestManager;
import com.sy37sdk.account.floatview.request.bean.FetchFloatWindowRedDotRspBean;
import com.sy37sdk.account.floatview.request.websocket.factory.FloatWindowRedDotMsgBaseFactory;
import com.sy37sdk.account.floatview.request.websocket.factory.FloatWindowRedDotMsgPidGidFactory;
import com.sy37sdk.account.floatview.request.websocket.factory.FloatWindowRedDotMsgPlatformFactory;
import com.sy37sdk.account.floatview.request.websocket.factory.FloatWindowRedDotRecInfMsg;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatViewManager implements WebSocketEngine.WebSocketEngineCallback {
    private static final String TAG = "FloatViewManager";
    private static final FloatViewManager sInstance = new FloatViewManager();
    private FloatRequestManager floatRequestManager = new FloatRequestManager();
    private FloatWindowRedDotMsgBaseFactory mFloatWindowRedDotMsgPidGidFactory;
    private FloatWindowRedDotMsgBaseFactory mFloatWindowRedDotMsgPlatformFactory;
    private OnRecInfListener mOnRecInfListener;

    public interface OnRecInfListener {
        void onMsgInf(MenuConfig menuConfig);
    }

    public static FloatViewManager getInstance() {
        return sInstance;
    }

    private FloatViewManager() {
    }

    public void requestFloatWindowRedDotMsg() {
        LogUtil.i(TAG, "requestFloatWindowRedDotMsg");
        this.floatRequestManager.requestFloatWindowRedDotMsgPlatform(new ReqWrapperHandler.FinishListener<Boolean>() { // from class: com.sy37sdk.account.floatview.FloatViewManager.1
            @Override // com.sq.websocket_engine.ReqWrapperHandler.FinishListener
            public void on(Boolean bool) {
                LogUtil.i(FloatViewManager.TAG, "requestFloatWindowRedDotMsgPlatform：" + bool);
            }
        });
        this.floatRequestManager.requestFloatWindowRedDotMsgPidGid(new ReqWrapperHandler.FinishListener<Boolean>() { // from class: com.sy37sdk.account.floatview.FloatViewManager.2
            @Override // com.sq.websocket_engine.ReqWrapperHandler.FinishListener
            public void on(Boolean bool) {
                LogUtil.i(FloatViewManager.TAG, "requestFloatWindowRedDotMsgPidGid：" + bool);
            }
        });
    }

    public void bindFloatSocket(OnRecInfListener onRecInfListener) {
        LogUtil.i(TAG, "bindFloatSocket");
        this.mOnRecInfListener = onRecInfListener;
        release();
        if (this.mFloatWindowRedDotMsgPlatformFactory == null) {
            this.mFloatWindowRedDotMsgPlatformFactory = new FloatWindowRedDotMsgPlatformFactory();
            WebSocketEngine.getInstance().addARecInfMsgBaseFactory(this.mFloatWindowRedDotMsgPlatformFactory);
        }
        if (this.mFloatWindowRedDotMsgPidGidFactory == null) {
            this.mFloatWindowRedDotMsgPidGidFactory = new FloatWindowRedDotMsgPidGidFactory();
            WebSocketEngine.getInstance().addARecInfMsgBaseFactory(this.mFloatWindowRedDotMsgPidGidFactory);
        }
        WebSocketEngine.getInstance().registerWebSocketEngineCallback(this);
    }

    @Override // com.sq.websocket_engine.WebSocketEngine.WebSocketEngineCallback
    public void onAuth() {
        LogUtil.i(TAG, "onAuth");
        requestFloatWindowRedDotMsg();
    }

    @Override // com.sq.websocket_engine.WebSocketEngine.WebSocketEngineCallback
    public void onReceiveInf(ARecInfMsg aRecInfMsg) {
        handleMsgInf(aRecInfMsg);
    }

    private void handleMsgInf(ARecInfMsg aRecInfMsg) {
        FetchFloatWindowRedDotRspBean inf;
        if (!(aRecInfMsg instanceof FloatWindowRedDotRecInfMsg) || (inf = ((FloatWindowRedDotRecInfMsg) aRecInfMsg).getInf()) == null) {
            return;
        }
        RedDot redDot = new RedDot();
        redDot.setNum(inf.getNumber());
        redDot.setTitle(inf.getTitle());
        FloatViewDataManager.getInstance().updateRedDotNum(redDot);
        LogUtil.i(TAG, "FloatViewRedRecInfMsg：" + inf);
        MenuConfig menuConfig = new MenuConfig();
        menuConfig.title = inf.getTitle();
        menuConfig.warningType = inf.getWarningType();
        menuConfig.priority = inf.getPriority();
        menuConfig.warningMsg = inf.getWarningMsg();
        OnRecInfListener onRecInfListener = this.mOnRecInfListener;
        if (onRecInfListener != null) {
            onRecInfListener.onMsgInf(menuConfig);
        }
    }

    public void release() {
        WebSocketEngine.getInstance().unregisterWebSocketEngineCallback(this);
        if (this.mFloatWindowRedDotMsgPlatformFactory != null) {
            WebSocketEngine.getInstance().removeARecInfMsgBaseFactory(this.mFloatWindowRedDotMsgPlatformFactory);
            this.mFloatWindowRedDotMsgPlatformFactory = null;
        }
        if (this.mFloatWindowRedDotMsgPidGidFactory != null) {
            WebSocketEngine.getInstance().removeARecInfMsgBaseFactory(this.mFloatWindowRedDotMsgPidGidFactory);
            this.mFloatWindowRedDotMsgPidGidFactory = null;
        }
    }
}
