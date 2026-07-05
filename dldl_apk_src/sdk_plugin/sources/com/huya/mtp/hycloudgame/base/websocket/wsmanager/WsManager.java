package com.huya.mtp.hycloudgame.base.websocket.wsmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hycloudgame.base.websocket.wsmanager.WsStatus;
import com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener.WsStatusListener;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WsManager implements IWsManager {
    private static final int RECONNECT_INTERVAL = 10000;
    private static final long RECONNECT_MAX_TIME = 120000;
    private Context mContext;
    private HashMap<String, String> mHeader;
    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    private WebSocket mWebSocket;
    private WsStatusListener wsStatusListener;
    private String wsUrl;
    String TAG = "NetService-WsManager";
    private int mCurrentStatus = -1;
    private boolean isNeedReconnect = true;
    private Handler wsHandler = new Handler(Looper.getMainLooper());
    private int reconnectCount = 0;
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private Runnable reconnectRunnable = new Runnable() { // from class: com.huya.mtp.hycloudgame.base.websocket.wsmanager.WsManager.1
        @Override // java.lang.Runnable
        public void run() {
            if (WsManager.this.wsStatusListener != null) {
                WsManager.this.wsStatusListener.onReconnect();
            }
            WsManager.this.buildConnect();
        }
    };
    private WebSocketListener mWebSocketListener = new WebSocketListener() { // from class: com.huya.mtp.hycloudgame.base.websocket.wsmanager.WsManager.2
        @Override // okhttp3.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            MTPApi.LOGGER.info(WsManager.this.TAG, WsManager.this.TAG + " --- onOpen");
            if (WsManager.this.mWebSocket == webSocket) {
                WsManager.this.mCurrentStatus = 1;
                WsManager.this.connected();
                if (WsManager.this.wsStatusListener != null) {
                    WsManager.this.wsStatusListener.onOpen(response);
                    return;
                }
                return;
            }
            webSocket.close(1000, WsStatus.TIP.NORMAL_CLOSE);
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, ByteString byteString) {
            if (WsManager.this.wsStatusListener != null) {
                WsManager.this.wsStatusListener.onMessage(byteString);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            if (WsManager.this.wsStatusListener != null) {
                WsManager.this.wsStatusListener.onMessage(str);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onClosing(WebSocket webSocket, int i, String str) {
            MTPApi.LOGGER.info(WsManager.this.TAG, WsManager.this.TAG + " --- onClosing reason = " + str);
            if (WsManager.this.wsStatusListener != null) {
                WsManager.this.wsStatusListener.onClosing(i, str);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i, String str) {
            MTPApi.LOGGER.info(WsManager.this.TAG, WsManager.this.TAG + " --- onClosed reason = " + str);
            if (WsManager.this.wsStatusListener != null) {
                WsManager.this.wsStatusListener.onClosed(i, str);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            WsManager.this.tryReconnect();
            if (WsManager.this.wsStatusListener != null) {
                WsManager.this.wsStatusListener.onFailure(th, response);
            }
        }
    };
    private Lock mLock = new ReentrantLock();

    private void cancelReconnect() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryReconnect() {
    }

    public WsManager(Builder builder) {
        this.mContext = builder.mContext;
        this.wsUrl = builder.wsUrl;
        this.mHeader = builder.mHeader;
        this.mOkHttpClient = builder.mOkHttpClient;
    }

    public void destroy() {
        this.mContext = null;
        this.wsStatusListener = null;
    }

    private void initWebSocket() {
        MTPApi.LOGGER.debug(this.TAG, "initWebSocket");
        if (this.mOkHttpClient == null) {
            this.mOkHttpClient = new OkHttpClient.Builder().retryOnConnectionFailure(true).build();
        }
        if (this.mRequest == null) {
            Request.Builder builderUrl = new Request.Builder().url(this.wsUrl);
            HashMap<String, String> map = this.mHeader;
            if (map != null) {
                for (String str : map.keySet()) {
                    builderUrl.addHeader(str, this.mHeader.get(str));
                }
            }
            this.mRequest = builderUrl.build();
        }
        try {
            this.mLock.lockInterruptibly();
            try {
                if (this.mWebSocket == null) {
                    this.mOkHttpClient.dispatcher().cancelAll();
                    this.mWebSocket = this.mOkHttpClient.newWebSocket(this.mRequest, this.mWebSocketListener);
                }
                this.mLock.unlock();
            } catch (Throwable th) {
                this.mLock.unlock();
                throw th;
            }
        } catch (InterruptedException unused) {
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.IWsManager
    public WebSocket getWebSocket() {
        return this.mWebSocket;
    }

    public void setWsStatusListener(WsStatusListener wsStatusListener) {
        this.wsStatusListener = wsStatusListener;
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.IWsManager
    public boolean isWsConnected() {
        return this.mCurrentStatus == 1;
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.IWsManager
    public int getCurrentStatus() {
        return this.mCurrentStatus;
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.IWsManager
    public void startConnect() {
        MTPApi.LOGGER.debug(this.TAG, "startConnect");
        this.isNeedReconnect = true;
        buildConnect();
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.IWsManager
    public void stopConnect() {
        MTPApi.LOGGER.debug(this.TAG, "stopConnect");
        this.isNeedReconnect = false;
        disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connected() {
        MTPApi.LOGGER.debug(this.TAG, "connected");
        cancelReconnect();
    }

    private void disconnect() {
        if (this.mCurrentStatus == -1) {
            return;
        }
        MTPApi.LOGGER.debug(this.TAG, "disconnect");
        cancelReconnect();
        OkHttpClient okHttpClient = this.mOkHttpClient;
        if (okHttpClient != null) {
            okHttpClient.dispatcher().cancelAll();
        }
        if (this.mWebSocket != null) {
            try {
                this.mLock.lockInterruptibly();
                try {
                    if (!this.mWebSocket.close(1000, WsStatus.TIP.NORMAL_CLOSE) && this.wsStatusListener != null) {
                        setUpdateListTimer(1000);
                    }
                    this.mLock.unlock();
                } catch (Throwable th) {
                    this.mLock.unlock();
                    throw th;
                }
            } catch (InterruptedException unused) {
            }
        }
        this.mCurrentStatus = -1;
    }

    private void setUpdateListTimer(int i) {
        if (this.mTimer != null) {
            return;
        }
        this.mTimer = new Timer();
        TimerTask timerTask = new TimerTask() { // from class: com.huya.mtp.hycloudgame.base.websocket.wsmanager.WsManager.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                WsManager.this.clearUpdateListTimer();
                boolean z = (WsManager.this.mCurrentStatus == 1) | (WsManager.this.mCurrentStatus == 0);
                WsManager wsManager = WsManager.this;
                if ((!z && !(!wsManager.isNetworkConnected(wsManager.mContext))) && WsManager.this.wsStatusListener != null) {
                    WsManager.this.wsStatusListener.onClosed(1001, WsStatus.TIP.ABNORMAL_CLOSE);
                }
            }
        };
        this.mTimerTask = timerTask;
        this.mTimer.schedule(timerTask, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdateListTimer() {
        TimerTask timerTask = this.mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.mTimerTask = null;
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void buildConnect() {
        if (((this.mCurrentStatus == 1) | (this.mCurrentStatus == 0)) || (true ^ isNetworkConnected(this.mContext))) {
            return;
        }
        MTPApi.LOGGER.debug(this.TAG, "buildConnect");
        this.mCurrentStatus = 0;
        initWebSocket();
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.IWsManager
    public boolean sendMessage(String str) {
        return send(str);
    }

    @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.IWsManager
    public boolean sendMessage(ByteString byteString) {
        return send(byteString);
    }

    private boolean send(Object obj) {
        WebSocket webSocket = this.mWebSocket;
        boolean zSend = false;
        if (webSocket != null && this.mCurrentStatus == 1) {
            if (obj instanceof String) {
                zSend = webSocket.send((String) obj);
            } else if (obj instanceof ByteString) {
                zSend = webSocket.send((ByteString) obj);
            }
            if (!zSend) {
                tryReconnect();
            }
        }
        return zSend;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    public static final class Builder {
        private Context mContext;
        private HashMap<String, String> mHeader;
        private OkHttpClient mOkHttpClient;
        private String wsUrl;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder wsUrl(String str) {
            this.wsUrl = str;
            return this;
        }

        public Builder client(OkHttpClient okHttpClient) {
            this.mOkHttpClient = okHttpClient;
            return this;
        }

        public Builder setHeader(HashMap<String, String> map) {
            this.mHeader = map;
            return this;
        }

        public WsManager build() {
            return new WsManager(this);
        }
    }
}
