package com.huya.mtp.hycloudgame.base.tcpsocket;

import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hycloudgame.base.listener.IMessageDispatcher;
import com.huya.mtp.hycloudgame.base.listener.ISocketClient;
import com.huya.mtp.hycloudgame.base.listener.ISocketStateListener;
import com.huya.mtp.hycloudgame.base.tcpsocket.core.ISocketClientListener;
import com.huya.mtp.hycloudgame.base.tcpsocket.core.ITcpClient;
import com.huya.mtp.hycloudgame.base.tcpsocket.core.TcpClientCore;
import com.huya.mtp.hycloudgame.base.websocket.ISocketStateMonitor;
import com.huya.mtp.hycloudgame.base.websocket.WebSocketConfig;
import com.huya.mtp.hycloudgame.base.websocket.client.WebSocketHandler;
import com.huya.mtp.hyns.api.ISocketPacket;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TcpSocketClient implements ISocketClient, ISocketClientListener {
    private static final int MAX_PENDING_REQUEST_COUNT = 20;
    private static final String TAG = "NetService-TcpSocketClient";
    private IMessageDispatcher mMessageDispatcher;
    private ITcpClient mSocketClient;
    private ISocketStateListener mStateListener;
    private AtomicBoolean mConnected = new AtomicBoolean(false);
    private final Object mPendingLock = new Object();
    private LinkedList<byte[]> mPendingList = new LinkedList<>();
    private final Object mStateListenerLock = new Object();
    private ISocketStateMonitor mMonitor = null;

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void reConnect() {
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void setMessageDispatcher(IMessageDispatcher iMessageDispatcher) {
        this.mMessageDispatcher = iMessageDispatcher;
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public boolean connect(String str, String str2, ISocketStateMonitor iSocketStateMonitor) {
        int i;
        MTPApi.LOGGER.info(TAG, "connect() called with: hostIP = [" + str + "], port = [" + str2 + "], monitor = [" + iSocketStateMonitor + "]");
        stopMonitor();
        this.mMonitor = iSocketStateMonitor;
        try {
            i = Integer.parseInt(str2);
        } catch (Exception e) {
            MTPApi.LOGGER.error("connect: ", e);
            i = 0;
        }
        if (this.mSocketClient == null) {
            this.mSocketClient = new TcpClientCore(this);
        }
        this.mSocketClient.connect(str, i);
        return true;
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public boolean connect(String str, WebSocketConfig webSocketConfig, ISocketStateMonitor iSocketStateMonitor) {
        throw new RuntimeException("not support tcp.");
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public boolean connect(String str, ISocketStateMonitor iSocketStateMonitor) {
        throw new RuntimeException("not support tcp.");
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void disconnect() {
        socketDisconnect();
        clear();
    }

    private void clear() {
        synchronized (this.mPendingLock) {
            this.mPendingList.clear();
        }
    }

    private void socketDisconnect() {
        if (this.mSocketClient != null) {
            synchronized (this) {
                if (this.mSocketClient != null) {
                    this.mSocketClient.disconnect();
                }
            }
        }
    }

    public void send(byte[] bArr) {
        if (!this.mConnected.get()) {
            synchronized (this.mPendingLock) {
                while (this.mPendingList.size() >= 20) {
                    this.mPendingList.removeFirst();
                }
                this.mPendingList.addLast(bArr);
            }
            return;
        }
        try {
            synchronized (this) {
                if (this.mSocketClient != null) {
                    this.mSocketClient.send(bArr);
                }
            }
        } catch (Throwable th) {
            MTPApi.LOGGER.error("NetService-TcpSocketClient:send: ", th);
            disconnect();
            this.mSocketClient = null;
            this.mConnected.set(false);
            ISocketStateListener iSocketStateListener = this.mStateListener;
            if (iSocketStateListener != null) {
                iSocketStateListener.onSocketError(2, th);
            }
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.ISocketClientListener
    public void onOpen() {
        MTPApi.LOGGER.info(TAG, "onOpen: ");
        this.mConnected.set(true);
        if (this.mMonitor != null) {
            MTPApi.LOGGER.info(TAG, "onOpen: mMonitor.start()");
            this.mMonitor.start();
        }
        synchronized (this.mStateListenerLock) {
            MTPApi.LOGGER.info(TAG, "onOpen: mStateListener " + this.mStateListener);
            if (this.mStateListener != null) {
                this.mStateListener.onSocketConnected();
            }
        }
        synchronized (this.mPendingLock) {
            Iterator<byte[]> it = this.mPendingList.iterator();
            while (it.hasNext()) {
                send(it.next());
            }
            this.mPendingList.clear();
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.ISocketClientListener
    public void onMessage(ByteBuffer byteBuffer) {
        IMessageDispatcher iMessageDispatcher = this.mMessageDispatcher;
        if (iMessageDispatcher != null) {
            iMessageDispatcher.dispatchMessage(byteBuffer.array());
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.ISocketClientListener
    public void onClose(int i, String str, boolean z) {
        stopMonitor();
        handleDisconnect();
    }

    @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.ISocketClientListener
    public void onError(Throwable th) {
        stopMonitor();
        handleDisconnect();
    }

    public void stopMonitor() {
        if (this.mMonitor != null) {
            MTPApi.LOGGER.info(TAG, "mMonitor.stop();");
            this.mMonitor.stop();
        }
    }

    private synchronized void handleDisconnect() {
        MTPApi.LOGGER.info(TAG, "handleDisconnect. ");
        if (this.mConnected.get()) {
            synchronized (this.mStateListenerLock) {
                if (this.mStateListener != null) {
                    this.mStateListener.onSocketDisconnected();
                }
            }
        }
        this.mSocketClient = null;
        this.mConnected.set(false);
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void setSocketStateListener(ISocketStateListener iSocketStateListener) {
        MTPApi.LOGGER.info(TAG, "setSocketStateListener: " + iSocketStateListener);
        synchronized (this.mStateListenerLock) {
            this.mStateListener = iSocketStateListener;
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void sendTubeRequest(ISocketPacket iSocketPacket) {
        send(iSocketPacket.getByteBuffer().array());
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void setMessageParseStrategy(WebSocketHandler.OnMessageDispatchListener onMessageDispatchListener) {
        throw new RuntimeException("not support tcp.");
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void destroy() {
        this.mStateListener = null;
        this.mMonitor = null;
    }

    @Override // com.huya.mtp.hycloudgame.base.listener.ISocketClient
    public void checkNotifyInitCompleted() {
        if (this.mMonitor != null) {
            MTPApi.LOGGER.info(TAG, "startHeartBeat");
            this.mMonitor.startHeartBeat();
        }
        synchronized (this.mStateListenerLock) {
            if (this.mStateListener != null) {
                this.mStateListener.onSocketInitCompleted();
            }
        }
    }
}
