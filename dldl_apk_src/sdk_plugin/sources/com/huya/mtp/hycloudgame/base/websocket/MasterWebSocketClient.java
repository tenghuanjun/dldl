package com.huya.mtp.hycloudgame.base.websocket;

import android.content.Context;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hycloudgame.base.tcpsocket.TcpSocketFactory;
import com.huya.mtp.hycloudgame.base.websocket.wsmanager.WsManager;
import com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener.WsStatusListener;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.ByteString;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MasterWebSocketClient {
    private static final String TAG = "NetService-MasterWebSocketClient";
    private SocketClientListener mListener;
    private ISocketStateMonitor mMonitor;
    private WsStatusListener mWsStatusListener = new WsStatusListener() { // from class: com.huya.mtp.hycloudgame.base.websocket.MasterWebSocketClient.2
        @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener.WsStatusListener
        public void onOpen(Response response) {
            super.onOpen(response);
            MTPApi.LOGGER.info(MasterWebSocketClient.TAG, "WsStatusListener onOpen " + response);
            if (MasterWebSocketClient.this.mMonitor != null) {
                MasterWebSocketClient.this.mMonitor.start();
            }
            if (MasterWebSocketClient.this.mListener != null) {
                MasterWebSocketClient.this.mListener.onOpen();
            }
        }

        @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener.WsStatusListener
        public void onMessage(String str) {
            super.onMessage(str);
        }

        @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener.WsStatusListener
        public void onMessage(ByteString byteString) {
            super.onMessage(byteString);
            if (MasterWebSocketClient.this.mListener != null) {
                MasterWebSocketClient.this.mListener.onMessage(ByteBuffer.wrap(byteString.toByteArray()));
            }
        }

        @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener.WsStatusListener
        public void onReconnect() {
            super.onReconnect();
            MTPApi.LOGGER.info(MasterWebSocketClient.TAG, "WsStatusListener onReconnect  ");
        }

        @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener.WsStatusListener
        public void onClosing(int i, String str) {
            super.onClosing(i, str);
            MTPApi.LOGGER.info(MasterWebSocketClient.TAG, "WsStatusListener onClosing code: " + i + "; reson: " + str);
            MasterWebSocketClient.this.mListener = null;
        }

        @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener.WsStatusListener
        public void onClosed(int i, String str) {
            super.onClosed(i, str);
            MTPApi.LOGGER.info(MasterWebSocketClient.TAG, "WsStatusListener onClosed code: " + i + "; reson: " + str);
            if (MasterWebSocketClient.this.mListener != null) {
                MasterWebSocketClient.this.mListener.onClose(i, str, true);
            }
        }

        @Override // com.huya.mtp.hycloudgame.base.websocket.wsmanager.listener.WsStatusListener
        public void onFailure(Throwable th, Response response) {
            super.onFailure(th, response);
            MTPApi.LOGGER.info(MasterWebSocketClient.TAG, "WsStatusListener onFailure Throwable: " + th.toString() + "; response: " + response);
            if (MasterWebSocketClient.this.mListener != null) {
                MasterWebSocketClient.this.mListener.onError(th);
            }
        }
    };
    WsManager wsManager;

    public interface SocketClientListener {
        void onClose(int i, String str, boolean z);

        void onError(Throwable th);

        void onMessage(ByteBuffer byteBuffer);

        void onOpen();
    }

    public void connect() {
        if (this.wsManager != null) {
            MTPApi.LOGGER.info(TAG, "wsManager connect");
            this.wsManager.startConnect();
        }
    }

    public void send(byte[] bArr) {
        WsManager wsManager = this.wsManager;
        if (wsManager != null) {
            wsManager.sendMessage(ByteString.of(bArr));
        }
    }

    public MasterWebSocketClient(Context context, String str, WebSocketConfig webSocketConfig, SocketClientListener socketClientListener, ISocketStateMonitor iSocketStateMonitor) {
        this.mListener = socketClientListener;
        this.mMonitor = iSocketStateMonitor;
        WsManager.Builder builderClient = new WsManager.Builder(context).wsUrl(str.toString()).client(new OkHttpClient.Builder().socketFactory(new TcpSocketFactory()).retryOnConnectionFailure(false).sslSocketFactory(createSSLSocketFactory()).hostnameVerifier(new HostnameVerifier() { // from class: com.huya.mtp.hycloudgame.base.websocket.MasterWebSocketClient.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str2, SSLSession sSLSession) {
                return true;
            }
        }).build());
        if (webSocketConfig != null) {
            builderClient.setHeader(webSocketConfig.getHeader());
        }
        WsManager wsManagerBuild = builderClient.build();
        this.wsManager = wsManagerBuild;
        wsManagerBuild.setWsStatusListener(this.mWsStatusListener);
    }

    public void destroy() {
        MTPApi.LOGGER.info(TAG, "wsManager destroy");
        WsManager wsManager = this.wsManager;
        if (wsManager != null) {
            wsManager.setWsStatusListener(null);
            this.wsManager.stopConnect();
            this.wsManager.destroy();
        }
        ISocketStateMonitor iSocketStateMonitor = this.mMonitor;
        if (iSocketStateMonitor != null) {
            iSocketStateMonitor.stop();
            this.mMonitor = null;
        }
        this.mListener = null;
    }

    public void close() {
        MTPApi.LOGGER.info(TAG, "wsManager stopConnect");
        WsManager wsManager = this.wsManager;
        if (wsManager != null) {
            wsManager.stopConnect();
        }
        ISocketStateMonitor iSocketStateMonitor = this.mMonitor;
        if (iSocketStateMonitor != null) {
            iSocketStateMonitor.stop();
            this.mMonitor = null;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }

    static class TrustAllCerts implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        TrustAllCerts() {
        }
    }
}
