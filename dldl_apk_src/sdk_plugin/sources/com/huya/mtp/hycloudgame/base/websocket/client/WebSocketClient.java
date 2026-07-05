package com.huya.mtp.hycloudgame.base.websocket.client;

import android.content.Context;
import android.os.HandlerThread;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hycloudgame.base.listener.IMessageDispatcher;
import com.huya.mtp.hycloudgame.base.listener.ISocketClient;
import com.huya.mtp.hycloudgame.base.listener.ISocketStateListener;
import com.huya.mtp.hycloudgame.base.websocket.ISocketStateMonitor;
import com.huya.mtp.hycloudgame.base.websocket.WebSocketConfig;
import com.huya.mtp.hycloudgame.base.websocket.client.WebSocketHandler;
import com.huya.mtp.hyns.api.ISocketPacket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketClient implements ISocketClient {
    private static final String TAG = "NetService-WebSocketClient";
    private static int sSequenceId;
    private WebSocketHandler mHandler;
    private final Object mHandlerLock;
    private ISocketStateMonitor mMonitor;
    private String mUrl;
    public HandlerThread mWorkThread;

    public WebSocketClient(Context context, boolean z) {
        Object obj = new Object();
        this.mHandlerLock = obj;
        synchronized (obj) {
            HandlerThread handlerThread = new HandlerThread("WebSocketChannelThread");
            this.mWorkThread = handlerThread;
            handlerThread.start();
            this.mHandler = new WebSocketHandler(context, z, this.mWorkThread.getLooper(), this);
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void destroy() {
        if (handlerIsNull()) {
            return;
        }
        synchronized (this.mHandlerLock) {
            this.mHandler.obtainMessage(5).sendToTarget();
        }
    }

    public void releaseThread() {
        synchronized (this.mHandlerLock) {
            if (this.mWorkThread != null && this.mHandler != null) {
                this.mHandler.removeCallbacksAndMessages(null);
                this.mWorkThread.quit();
                this.mHandler = null;
                this.mWorkThread = null;
            }
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void disconnect() {
        if (handlerIsNull()) {
            return;
        }
        this.mHandler.obtainMessage(3).sendToTarget();
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void setMessageDispatcher(IMessageDispatcher iMessageDispatcher) {
        if (handlerIsNull()) {
            return;
        }
        this.mHandler.setMessageDispatcher(iMessageDispatcher);
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public boolean connect(String str, String str2, ISocketStateMonitor iSocketStateMonitor) {
        return connect("ws://" + str + ":" + str2, iSocketStateMonitor);
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public boolean connect(String str, ISocketStateMonitor iSocketStateMonitor) {
        return connect(str, (WebSocketConfig) null, iSocketStateMonitor);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public boolean connect(String str, WebSocketConfig webSocketConfig, ISocketStateMonitor iSocketStateMonitor) {
        this.mUrl = str;
        this.mMonitor = iSocketStateMonitor;
        if (handlerIsNull()) {
            return false;
        }
        this.mHandler.removeMessages(1);
        this.mHandler.obtainMessage(1, new Object[]{this.mUrl, iSocketStateMonitor, webSocketConfig}).sendToTarget();
        return true;
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void sendTubeRequest(ISocketPacket iSocketPacket) {
        if (handlerIsNull()) {
            return;
        }
        this.mHandler.obtainMessage(2, iSocketPacket.getByteBuffer().array()).sendToTarget();
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void setSocketStateListener(ISocketStateListener iSocketStateListener) {
        if (handlerIsNull()) {
            return;
        }
        this.mHandler.setSocketStateListener(iSocketStateListener);
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void checkNotifyInitCompleted() {
        if (handlerIsNull()) {
            return;
        }
        this.mHandler.checkNotifyInitCompleted();
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void setMessageParseStrategy(WebSocketHandler.OnMessageDispatchListener onMessageDispatchListener) {
        if (handlerIsNull()) {
            return;
        }
        this.mHandler.setOnMessageDispatchListener(onMessageDispatchListener);
    }

    public void reConnect(String str, ISocketStateMonitor iSocketStateMonitor) {
        if (handlerIsNull()) {
            return;
        }
        MTPApi.LOGGER.info(TAG, "WebSocketClient reConnect. ");
        connect(str, iSocketStateMonitor);
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void reConnect() {
        if (handlerIsNull()) {
            return;
        }
        this.mHandler.removeMessages(4);
        connect(this.mUrl, this.mMonitor);
        MTPApi.LOGGER.info(TAG, "WebSocketClient reConnect. ");
    }

    private synchronized int getNextSequenceId() {
        int i;
        i = sSequenceId;
        sSequenceId = i + 1;
        return i;
    }

    private boolean handlerIsNull() {
        synchronized (this.mHandlerLock) {
            if (this.mWorkThread != null && this.mHandler != null) {
                return false;
            }
            MTPApi.LOGGER.error(TAG, "bug bug mHandler == null");
            return true;
        }
    }
}
