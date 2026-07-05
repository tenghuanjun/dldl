package com.huya.mtp.hyns.miniprogram.socket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.huya.mtp.api.LogApi;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hycloudgame.base.exception.WSBaseException;
import com.huya.mtp.hycloudgame.base.listener.ISocketClient;
import com.huya.mtp.hycloudgame.base.listener.ISocketStateListener;
import com.huya.mtp.hycloudgame.base.utils.NetworkUtil;
import com.huya.mtp.hycloudgame.base.websocket.client.WebSocketClient;
import com.huya.mtp.hycloudgame.base.websocket.client.WebSocketHandler;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.hyns.api.NSDnsApi;
import com.huya.mtp.hyns.api.UserId;
import com.huya.mtp.hyns.miniprogram.data.ProxySignalMonitorInfo;
import com.huya.mtp.hyns.miniprogram.data.ProxySignalSocketData;
import com.huya.mtp.hyns.miniprogram.jce.WebSocketCommand;
import com.huya.mtp.hyns.miniprogram.utils.ProxySignalSocketHelper;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sqwan.common.route.FunctionRouter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ProxySignalSocketManager {
    public static final String DEFAULT_PORT = "8085";
    public static final int HEART_BEAT_INTERVAL = 30000;
    private static final String TAG = "NetService-SocketManager";
    public static final String WSPROXY_HUYA_COM = "wsproxy.huya.com";
    public static final String WSPROXY_HUYA_COM_TEST = "test-wsproxy.huya.com";
    private static final AtomicInteger mCount = new AtomicInteger(1);
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private ProxySignalMessageDispatcher mMessageDispatcher;
    private ProxySignalSocketInitParam mParam;
    private NetworkConnectChangedReceiver mReceiver;
    private final ISocketClient mSocketClient;
    private ISocketStateListener mWebSocketStateListener;
    private Boolean mHasWSConnected = false;
    private boolean mIsConnecting = false;
    private ArrayList<ISocketStateListener> mWebSocketStateListeners = new ArrayList<>();
    private ArrayList<ProxySignalJceMsgListener> mJceRspListener = new ArrayList<>();
    private final Object mLock = new Object();
    private String mCurConnectUrl = "";

    public interface ISocketListener {
        void onHeartBeatTimeOut();
    }

    public interface NetworkStateListener {
        void onNetChange(boolean z);
    }

    public interface OnConnectHostCallBack {
    }

    public ProxySignalSocketManager(ProxySignalSocketInitParam proxySignalSocketInitParam, Context context) {
        this.mParam = proxySignalSocketInitParam;
        this.mSocketClient = new WebSocketClient(context, false);
        HandlerThread handlerThread = new HandlerThread(TAG + mCount.getAndIncrement());
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        initMsgDispatcher();
        initWebSocketListener();
        this.mSocketClient.setMessageParseStrategy(new WebSocketHandler.OnMessageDispatchListener() { // from class: com.huya.mtp.hyns.miniprogram.socket.ProxySignalSocketManager.1
            @Override // com.huya.mtp.hycloudgame.base.websocket.client.WebSocketHandler.OnMessageDispatchListener
            public byte[] onRevMessage(ByteBuffer byteBuffer) {
                ProxySignalSocketData proxySignalSocketData = new ProxySignalSocketData();
                proxySignalSocketData.readFrom(byteBuffer);
                return proxySignalSocketData.data;
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    }

    private void initMsgDispatcher() {
        ProxySignalMessageDispatcher proxySignalMessageDispatcher = new ProxySignalMessageDispatcher();
        this.mMessageDispatcher = proxySignalMessageDispatcher;
        proxySignalMessageDispatcher.setJceRspListener(new ProxySignalJceMsgListener() { // from class: com.huya.mtp.hyns.miniprogram.socket.ProxySignalSocketManager.2
            @Override // com.huya.mtp.hyns.miniprogram.socket.ProxySignalJceMsgListener
            public void onResponse(int i, byte[] bArr) {
                if (i == 1) {
                    MTPApi.LOGGER.info(ProxySignalSocketManager.TAG, "jce rsp HEART_BEART");
                }
                synchronized (ProxySignalSocketManager.this.mLock) {
                    if (ProxySignalSocketManager.this.mJceRspListener != null) {
                        Iterator it = ProxySignalSocketManager.this.mJceRspListener.iterator();
                        while (it.hasNext()) {
                            ((ProxySignalJceMsgListener) it.next()).onResponse(i, bArr);
                        }
                    }
                }
            }
        });
    }

    public void addJceRspListener(ProxySignalJceMsgListener proxySignalJceMsgListener) {
        synchronized (this.mLock) {
            this.mJceRspListener.add(proxySignalJceMsgListener);
        }
    }

    public void removeJceRspListener(ProxySignalJceMsgListener proxySignalJceMsgListener) {
        synchronized (this.mLock) {
            this.mJceRspListener.remove(proxySignalJceMsgListener);
        }
    }

    public void sendWebSocketPacket(byte[] bArr, int i) {
        LogApi logApi = MTPApi.LOGGER;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(bArr != null ? bArr.length : 0);
        objArr[1] = Integer.valueOf(i);
        logApi.info(TAG, "sendWebSocketPacket:%d, command:%d", objArr);
        if (this.mSocketClient == null || bArr == null) {
            return;
        }
        this.mSocketClient.sendTubeRequest(ProxySignalSocketHelper.getCommonPacket(bArr, i));
    }

    @Deprecated
    public void addSocketStateListener(ISocketStateListener iSocketStateListener) {
        ArrayList<ISocketStateListener> arrayList = this.mWebSocketStateListeners;
        if (arrayList == null || iSocketStateListener == null) {
            return;
        }
        arrayList.add(iSocketStateListener);
    }

    @Deprecated
    public void removeSocketStateListener(ISocketStateListener iSocketStateListener) {
        ArrayList<ISocketStateListener> arrayList = this.mWebSocketStateListeners;
        if (arrayList == null || iSocketStateListener == null) {
            return;
        }
        arrayList.remove(iSocketStateListener);
    }

    public void clearWebSocket() {
        synchronized (this.mLock) {
            if (this.mJceRspListener != null) {
                this.mJceRspListener.clear();
            }
        }
        ISocketClient iSocketClient = this.mSocketClient;
        if (iSocketClient != null) {
            iSocketClient.setMessageDispatcher(null);
            this.mSocketClient.disconnect();
        }
    }

    private void initWebSocketListener() {
        this.mWebSocketStateListener = new ISocketStateListener() { // from class: com.huya.mtp.hyns.miniprogram.socket.ProxySignalSocketManager.3
            @Override // com.huya.mtp.hycloudgame.base.listener.ISocketStateListener
            public void onSocketConnected() {
                ProxySignalSocketManager.this.mIsConnecting = false;
                ProxySignalSocketManager.this.mHasWSConnected = true;
                MTPApi.LOGGER.info(ProxySignalSocketManager.TAG, "wrapper onWebSocketConnected ");
                synchronized (ProxySignalSocketManager.this.mLock) {
                    if (ProxySignalSocketManager.this.mWebSocketStateListeners != null) {
                        Iterator it = ProxySignalSocketManager.this.mWebSocketStateListeners.iterator();
                        while (it.hasNext()) {
                            ((ISocketStateListener) it.next()).onSocketConnected();
                        }
                    }
                }
            }

            @Override // com.huya.mtp.hycloudgame.base.listener.ISocketStateListener
            public void onSocketDisconnected() {
                MTPApi.LOGGER.info(ProxySignalSocketManager.TAG, "wrapper onWebSocketDisconnected ");
                ProxySignalSocketManager.this.mCurConnectUrl = "";
                ProxySignalSocketManager.this.mIsConnecting = false;
                ProxySignalSocketManager.this.mHasWSConnected = false;
                synchronized (ProxySignalSocketManager.this.mLock) {
                    if (ProxySignalSocketManager.this.mWebSocketStateListeners != null) {
                        Iterator it = ProxySignalSocketManager.this.mWebSocketStateListeners.iterator();
                        while (it.hasNext()) {
                            ((ISocketStateListener) it.next()).onSocketDisconnected();
                        }
                    }
                }
            }

            @Override // com.huya.mtp.hycloudgame.base.listener.ISocketStateListener
            public void onSocketInitCompleted() {
                ProxySignalSocketManager.this.mHasWSConnected = true;
                MTPApi.LOGGER.info(ProxySignalSocketManager.TAG, "wrapper onSocketInitCompleted");
                synchronized (ProxySignalSocketManager.this.mLock) {
                    if (ProxySignalSocketManager.this.mWebSocketStateListeners != null) {
                        Iterator it = ProxySignalSocketManager.this.mWebSocketStateListeners.iterator();
                        while (it.hasNext()) {
                            ((ISocketStateListener) it.next()).onSocketInitCompleted();
                        }
                    }
                }
            }

            @Override // com.huya.mtp.hycloudgame.base.listener.ISocketStateListener
            public void onSocketError(int i, Throwable th) {
                if (th == null) {
                    th = new WSBaseException("throwable = null");
                }
                ProxySignalSocketManager.this.mHasWSConnected = false;
                ProxySignalSocketManager.this.mIsConnecting = false;
                ProxySignalSocketManager.this.mCurConnectUrl = "";
                MTPApi.LOGGER.info(ProxySignalSocketManager.TAG, "wrapper onSocketError errorCode = %d", Integer.valueOf(i));
                synchronized (ProxySignalSocketManager.this.mLock) {
                    if (ProxySignalSocketManager.this.mWebSocketStateListeners != null) {
                        Iterator it = ProxySignalSocketManager.this.mWebSocketStateListeners.iterator();
                        while (it.hasNext()) {
                            ((ISocketStateListener) it.next()).onSocketError(i, th);
                        }
                    }
                }
            }
        };
    }

    public boolean isHasConnected() {
        return this.mHasWSConnected.booleanValue();
    }

    public void connectForDns(final String str, final UserId userId, final String str2, boolean z, final ISocketStateListener iSocketStateListener) {
        final String str3 = z ? WSPROXY_HUYA_COM_TEST : WSPROXY_HUYA_COM;
        MTPApi.LOGGER.info(TAG, "MiniPgSocket Connect targetUrl:%s", str);
        runOnBackgroudThread(new Runnable() { // from class: com.huya.mtp.hyns.miniprogram.socket.ProxySignalSocketManager.4
            @Override // java.lang.Runnable
            public void run() {
                String[] hostByName = ((NSDnsApi) NS.get(NSDnsApi.class)).getHostByName(str3, 3000L, false);
                if (hostByName != null && hostByName.length > 0) {
                    MTPApi.LOGGER.info(ProxySignalSocketManager.TAG, "MiniPgSocket Connect HttpDns:%s", hostByName[0]);
                    ProxySignalSocketManager proxySignalSocketManager = ProxySignalSocketManager.this;
                    proxySignalSocketManager.connect(proxySignalSocketManager.getSocketUrl(hostByName[0], ProxySignalSocketManager.DEFAULT_PORT), str, userId, str2, iSocketStateListener);
                } else {
                    MTPApi.LOGGER.info(ProxySignalSocketManager.TAG, "MiniPg HttpDns ip not found.");
                    ProxySignalSocketManager.this.connectForLocalDns(str3, str, userId, str2);
                }
            }
        });
    }

    public void connectForLocalDns(final String str, final String str2, final UserId userId, final String str3) {
        runOnBackgroudThread(new Runnable() { // from class: com.huya.mtp.hyns.miniprogram.socket.ProxySignalSocketManager.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    InetAddress byName = InetAddress.getByName(str);
                    if (byName != null) {
                        ProxySignalSocketManager.this.connect(ProxySignalSocketManager.this.getSocketUrl(byName.getHostAddress(), ProxySignalSocketManager.DEFAULT_PORT), str2, userId, str3, (ISocketStateListener) null);
                        MTPApi.LOGGER.info(ProxySignalSocketManager.TAG, "MiniPgSocket Connect LocalDns IP:%s", byName.getHostAddress());
                        return;
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                MTPApi.LOGGER.info(ProxySignalSocketManager.TAG, "MiniPgSocket Connect DefaultUrl", str);
                ProxySignalSocketManager.this.connect(str, str2, userId, str3, (ISocketStateListener) null);
            }
        });
    }

    private void connect(String str, String str2, String str3, UserId userId, String str4) {
        MTPApi.LOGGER.info(TAG, "connectTube %s:%s", str, str2);
        connect(getSocketUrl(str, str2), str3, userId, str4, (ISocketStateListener) null);
    }

    private void runOnBackgroudThread(Runnable runnable) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            this.mHandler.post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connect(String str, String str2, UserId userId, String str3, ISocketStateListener iSocketStateListener) {
        if (this.mIsConnecting) {
            MTPApi.LOGGER.info(TAG, "hasConnecting, return.");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            MTPApi.LOGGER.info(TAG, "IP Url is NULL");
            return;
        }
        if (this.mHasWSConnected.booleanValue()) {
            if (!str.equals(this.mCurConnectUrl)) {
                disconnect();
                MTPApi.LOGGER.info(TAG, "hasConnected, disconnect the first one.");
            } else {
                MTPApi.LOGGER.info(TAG, "hasConnected, return.");
                return;
            }
        }
        this.mCurConnectUrl = str;
        this.mIsConnecting = true;
        if (this.mSocketClient != null) {
            this.mWebSocketStateListeners.clear();
            this.mSocketClient.setMessageDispatcher(this.mMessageDispatcher);
            this.mSocketClient.setSocketStateListener(this.mWebSocketStateListener);
            this.mWebSocketStateListeners.add(iSocketStateListener);
        }
        HashMap map = new HashMap();
        map.put("bizId", getEncodeString(String.valueOf(str3)));
        map.put("uid", String.valueOf(userId.lUid));
        map.put("token", getEncodeString(String.valueOf(userId.sToken)));
        map.put("cookie", getEncodeString(String.valueOf(userId.sCookie)));
        map.put(FunctionRouter.KEY_DATA, getEncodeString(String.valueOf(str2)));
        connectTube(getRqstUrl(str, map));
    }

    private void connectTube(String str) {
        MTPApi.LOGGER.info(TAG, "connectTube url:%s", str);
        try {
            this.mSocketClient.connect(str, new ProxySignalClientMonitor(new ProxySignalMonitorInfo(new WebSocketCommand(), 30000L, 1), this.mSocketClient));
        } catch (Exception e) {
            e.printStackTrace();
            MTPApi.LOGGER.error(TAG, e.toString());
            this.mIsConnecting = false;
        }
    }

    public void disconnect() {
        this.mIsConnecting = false;
        this.mCurConnectUrl = "";
        ISocketClient iSocketClient = this.mSocketClient;
        if (iSocketClient != null) {
            iSocketClient.disconnect();
        }
    }

    public void reConnectSocket() {
        MTPApi.LOGGER.info(TAG, "reConnectSocket");
        ISocketClient iSocketClient = this.mSocketClient;
        if (iSocketClient != null) {
            iSocketClient.reConnect();
        }
    }

    public void onDestroy() {
        disconnect();
        clearWebSocket();
        this.mHandlerThread.quit();
        this.mSocketClient.destroy();
        synchronized (this.mLock) {
            this.mWebSocketStateListeners.clear();
        }
        this.mJceRspListener.clear();
        this.mMessageDispatcher.clear();
        this.mMessageDispatcher = null;
        this.mWebSocketStateListener = null;
    }

    private static class NetworkConnectChangedReceiver extends BroadcastReceiver {
        private static final String TAG = "NetworkConnectChanged";
        private NetworkStateListener mNetworkStateListener;
        private boolean mHasNetWork = true;
        private String mNetType = "";
        private ArrayList<NetworkStateListener> mNetListeners = new ArrayList<>();

        private NetworkConnectChangedReceiver() {
        }

        public void addNetStateListener(NetworkStateListener networkStateListener) {
            if (networkStateListener != null) {
                this.mNetListeners.add(networkStateListener);
            }
        }

        public void removeNetStateListener(NetworkStateListener networkStateListener) {
            if (networkStateListener != null) {
                this.mNetListeners.remove(networkStateListener);
            }
        }

        public void clearNetStateListener() {
            this.mNetListeners.clear();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean zIsConnected = ProxySignalSocketManager.isConnected(context);
            String netWorkType = NetworkUtil.getNetWorkType(MTPApi.CONTEXT.getApplication());
            if (this.mHasNetWork != zIsConnected || !this.mNetType.equals(netWorkType)) {
                this.mHasNetWork = zIsConnected;
                this.mNetType = netWorkType;
                Iterator<NetworkStateListener> it = this.mNetListeners.iterator();
                while (it.hasNext()) {
                    it.next().onNetChange(this.mHasNetWork);
                }
            }
            this.mHasNetWork = zIsConnected;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    public static String getRqstUrl(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(str);
        boolean z = true;
        for (String str2 : map.keySet()) {
            if (str2 != null && map.get(str2) != null) {
                if (z) {
                    z = false;
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(str2);
                sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    private static String getEncodeString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSocketUrl(String str, String str2) {
        if (str != null && !str.contains(IDataSource.SCHEME_HTTP_TAG) && str.contains(":")) {
            return "ws://" + str;
        }
        return "ws://" + str + ":" + str2;
    }
}
