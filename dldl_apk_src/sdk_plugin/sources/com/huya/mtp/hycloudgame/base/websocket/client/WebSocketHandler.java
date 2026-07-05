package com.huya.mtp.hycloudgame.base.websocket.client;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.exception.NoAvailableNetworkException;
import com.huya.mtp.hycloudgame.base.exception.WSBaseException;
import com.huya.mtp.hycloudgame.base.listener.IMessageDispatcher;
import com.huya.mtp.hycloudgame.base.listener.ISocketStateListener;
import com.huya.mtp.hycloudgame.base.utils.NetworkUtil;
import com.huya.mtp.hycloudgame.base.websocket.ISocketStateMonitor;
import com.huya.mtp.hycloudgame.base.websocket.MasterWebSocketClient;
import com.huya.mtp.hycloudgame.base.websocket.WebSocketConfig;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.SSLContext;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketHandler extends Handler implements MasterWebSocketClient.SocketClientListener {
    private static final int MAX_PENDING_REQUEST_COUNT = 20;
    private static final int MAX_RETRY_COUNT = 3;
    public static final int MSG_CONNECT = 1;
    public static final int MSG_DESTROY = 5;
    public static final int MSG_DISCONNECT = 3;
    public static final int MSG_RECONNECT = 4;
    public static final int MSG_SEND = 2;
    private static final int RECONNECT_TIME_INTERVAL = 5000;
    public static final String TAG = "NetService-WebSocketHandler";
    private boolean mAutoReconnect;
    private WebSocketConfig mConfig;
    private AtomicBoolean mConnected;
    private Context mContext;
    private AtomicBoolean mIsSocketClose;
    private final Object mLock;
    private IMessageDispatcher mMessageDispatcher;
    private ISocketStateMonitor mMonitor;
    private OnMessageDispatchListener mOnMessageDispatchListener;
    private LinkedList<byte[]> mPendingList;
    private final Object mPendingLock;
    private int mRetryCount;
    private MasterWebSocketClient mSocketClient;
    private ISocketStateListener mStateListener;
    private String mUri;
    private WeakReference<WebSocketClient> mWrapper;

    public interface OnMessageDispatchListener {
        byte[] onRevMessage(ByteBuffer byteBuffer);
    }

    public void setOnMessageDispatchListener(OnMessageDispatchListener onMessageDispatchListener) {
        this.mOnMessageDispatchListener = onMessageDispatchListener;
    }

    public void setSocketStateListener(ISocketStateListener iSocketStateListener) {
        synchronized (this.mLock) {
            this.mStateListener = iSocketStateListener;
        }
    }

    public void setMessageDispatcher(IMessageDispatcher iMessageDispatcher) {
        synchronized (this.mLock) {
            this.mMessageDispatcher = iMessageDispatcher;
        }
    }

    public void checkNotifyInitCompleted() {
        synchronized (this.mLock) {
            if (this.mStateListener != null) {
                this.mStateListener.onSocketInitCompleted();
            }
            if (this.mMonitor != null) {
                MTPApi.LOGGER.info(TAG, "startHeartBeat");
                this.mMonitor.startHeartBeat();
            }
        }
    }

    WebSocketHandler(Context context, boolean z, Looper looper, WebSocketClient webSocketClient) {
        super(looper);
        this.mPendingLock = new Object();
        this.mLock = new Object();
        this.mConnected = new AtomicBoolean(false);
        this.mAutoReconnect = false;
        this.mMonitor = null;
        this.mConfig = null;
        this.mPendingList = new LinkedList<>();
        this.mStateListener = null;
        this.mRetryCount = 0;
        this.mIsSocketClose = new AtomicBoolean(false);
        this.mWrapper = new WeakReference<>(webSocketClient);
        this.mContext = context;
        this.mAutoReconnect = z;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i != 1) {
            if (i == 2) {
                send((byte[]) message.obj);
                return;
            }
            if (i == 3) {
                disconnect();
                return;
            }
            if (i != 4) {
                if (i != 5) {
                    return;
                }
                destroy();
                WeakReference<WebSocketClient> weakReference = this.mWrapper;
                if (weakReference == null || weakReference.get() == null) {
                    return;
                }
                this.mWrapper.get().releaseThread();
                return;
            }
        }
        Object[] objArr = (Object[]) message.obj;
        if (objArr == null) {
            return;
        }
        handleConnect((String) objArr[0], (ISocketStateMonitor) objArr[1], (WebSocketConfig) objArr[2]);
    }

    private void destroy() {
        synchronized (this.mPendingLock) {
            if (this.mPendingList != null) {
                this.mPendingList.clear();
                this.mPendingList = null;
            }
        }
        synchronized (this.mLock) {
            if (this.mMessageDispatcher != null) {
                this.mMessageDispatcher = null;
            }
            this.mStateListener = null;
            this.mMonitor = null;
            this.mUri = null;
            if (this.mSocketClient != null) {
                this.mSocketClient.destroy();
                this.mSocketClient = null;
            }
        }
        this.mConnected.set(false);
    }

    private void handleConnect(String str, ISocketStateMonitor iSocketStateMonitor, WebSocketConfig webSocketConfig) {
        if (TextUtils.isEmpty(str)) {
            ISocketStateListener iSocketStateListener = this.mStateListener;
            if (iSocketStateListener != null) {
                iSocketStateListener.onSocketError(1, new WSBaseException("uri is null"));
                return;
            }
            return;
        }
        this.mMonitor = iSocketStateMonitor;
        MasterWebSocketClient masterWebSocketClient = this.mSocketClient;
        if (masterWebSocketClient != null) {
            masterWebSocketClient.destroy();
            this.mSocketClient = null;
        }
        this.mIsSocketClose.set(false);
        this.mUri = str;
        MTPApi.LOGGER.info(TAG, "handleConnect mUri " + this.mUri);
        this.mSocketClient = new MasterWebSocketClient(this.mContext, this.mUri, webSocketConfig, this, this.mMonitor);
        try {
            synchronized (this) {
                if (!NetworkUtil.isNetworkAvailable(this.mContext)) {
                    handleConnectError(3, new NoAvailableNetworkException());
                } else {
                    if (this.mUri.indexOf("wss") == 0) {
                        try {
                            SSLContext.getDefault();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }
                    if (this.mSocketClient != null) {
                        this.mSocketClient.connect();
                    }
                }
            }
        } catch (Throwable th) {
            handleConnectError(2, th);
            MTPApi.LOGGER.error(TAG, th);
        }
    }

    public void disconnect() {
        this.mIsSocketClose.set(true);
        synchronized (this.mPendingLock) {
            if (this.mPendingList != null) {
                this.mPendingList.clear();
            }
        }
        if (this.mSocketClient != null) {
            synchronized (this) {
                if (this.mSocketClient != null) {
                    this.mSocketClient.close();
                }
            }
        }
        removeCallbacksAndMessages(null);
    }

    private void send(byte[] bArr) {
        if (!this.mConnected.get()) {
            synchronized (this.mPendingLock) {
                if (this.mPendingList != null) {
                    while (this.mPendingList.size() >= 20) {
                        this.mPendingList.removeFirst();
                    }
                    this.mPendingList.addLast(bArr);
                }
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
            th.printStackTrace();
            handleConnectError(2, th);
        }
    }

    private void handleDisconnect() {
        MTPApi.LOGGER.info(TAG, "WebSocketHandler handleDisconnect. ");
        if (this.mConnected.get()) {
            synchronized (this.mLock) {
                if (this.mStateListener != null) {
                    this.mStateListener.onSocketDisconnected();
                }
            }
        }
        MasterWebSocketClient masterWebSocketClient = this.mSocketClient;
        if (masterWebSocketClient != null) {
            masterWebSocketClient.destroy();
            this.mSocketClient = null;
        }
        this.mConnected.set(false);
    }

    private void handleConnectError(int i, Throwable th) {
        MTPApi.LOGGER.info(TAG, "WebSocketHandler handleConnectError. ");
        synchronized (this.mLock) {
            if (this.mStateListener != null) {
                this.mStateListener.onSocketError(i, th);
            }
        }
        MasterWebSocketClient masterWebSocketClient = this.mSocketClient;
        if (masterWebSocketClient != null) {
            masterWebSocketClient.destroy();
            this.mSocketClient = null;
        }
        this.mConnected.set(false);
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.MasterWebSocketClient.SocketClientListener
    public void onOpen() {
        MTPApi.LOGGER.info(TAG, "WebSocketHandler connected.");
        this.mConnected.set(true);
        this.mRetryCount = 0;
        synchronized (this.mLock) {
            if (this.mStateListener != null) {
                this.mStateListener.onSocketConnected();
            }
        }
        synchronized (this.mPendingLock) {
            if (this.mPendingList != null) {
                Iterator<byte[]> it = this.mPendingList.iterator();
                while (it.hasNext()) {
                    send(it.next());
                }
                this.mPendingList.clear();
            }
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.MasterWebSocketClient.SocketClientListener
    public void onMessage(ByteBuffer byteBuffer) {
        synchronized (this.mLock) {
            if (this.mMessageDispatcher != null && this.mOnMessageDispatchListener != null) {
                this.mMessageDispatcher.dispatchMessage(this.mOnMessageDispatchListener.onRevMessage(byteBuffer));
            }
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.MasterWebSocketClient.SocketClientListener
    public void onClose(int i, String str, boolean z) {
        MTPApi.LOGGER.info(TAG, "WebSocketHandler closed. Reason:" + str);
        handleDisconnect();
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.MasterWebSocketClient.SocketClientListener
    public void onError(Throwable th) {
        if (th != null && th.getMessage() != null) {
            MTPApi.LOGGER.info(TAG, "WebSocketHandler onError. Error:" + th.toString());
        }
        if (!NetworkUtil.isNetworkAvailable(this.mContext)) {
            MTPApi.LOGGER.info(TAG, "WebSocketHandler onError. network error");
            handleConnectError(3, new NoAvailableNetworkException());
            return;
        }
        if (this.mAutoReconnect && !this.mIsSocketClose.get() && this.mRetryCount <= 3) {
            MasterWebSocketClient masterWebSocketClient = this.mSocketClient;
            if (masterWebSocketClient != null) {
                masterWebSocketClient.destroy();
                this.mSocketClient = null;
            }
            this.mRetryCount++;
            removeMessages(4);
            synchronized (this.mLock) {
                if (this.mMonitor != null && this.mUri != null) {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 4;
                    messageObtain.obj = new Object[]{this.mUri, this.mMonitor, this.mConfig};
                    sendMessageDelayed(messageObtain, OAIDHelper.TIMEOUT);
                }
            }
            return;
        }
        if (this.mConnected.get()) {
            handleDisconnect();
        } else {
            handleConnectError(3, th);
        }
    }
}
