package com.huya.mtp.hycloudgame.base.tcpsocket.core;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huya.mtp.api.MTPApi;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TcpClientCore implements Runnable, ITcpClient {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final String TAG = "NetServiceTcpSocketClient";
    private String hostIP;
    private Handler mHandler;
    private ISocketClientListener mListener;
    private int port;
    private TcpSocketTransceiver transceiver;
    private boolean connect = false;
    private ByteBuffer cacheBuffer = ByteBuffer.allocate(1024);

    public TcpClientCore(ISocketClientListener iSocketClientListener) {
        this.mListener = iSocketClientListener;
    }

    @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.ITcpClient
    public void connect(String str, int i) {
        MTPApi.LOGGER.info(TAG, "connect() called with: hostIP = [" + str + "], port = [" + i + "]");
        this.hostIP = str;
        this.port = i;
        new Thread(this).start();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(this.hostIP, this.port), 10000);
            socket.setSoTimeout(10000);
            TcpSocketTransceiver tcpSocketTransceiver = new TcpSocketTransceiver(socket) { // from class: com.huya.mtp.hycloudgame.base.tcpsocket.core.TcpClientCore.1
                @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.TcpSocketTransceiver
                public void onReceive(InetAddress inetAddress, byte[] bArr, int i) {
                    MTPApi.LOGGER.debug(TcpClientCore.TAG, "onReceive() buffer:" + TcpClientCore.this.cacheBuffer.position() + " length = [" + i + "] ");
                    if (TcpClientCore.this.mListener != null) {
                        try {
                            TcpStickPackage.processBytes(bArr, i, TcpClientCore.this.cacheBuffer, TcpClientCore.this.mListener);
                        } catch (Exception e) {
                            e.printStackTrace();
                            MTPApi.LOGGER.error("NetServiceTcpSocketClient:onReceive: ", e);
                        }
                    }
                }

                @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.TcpSocketTransceiver
                public void onDisconnect(InetAddress inetAddress) {
                    TcpClientCore.this.connect = false;
                    if (TcpClientCore.this.mListener != null) {
                        TcpClientCore.this.mListener.onClose(0, "normal", false);
                    }
                }
            };
            this.transceiver = tcpSocketTransceiver;
            tcpSocketTransceiver.start();
            Looper.prepare();
            this.mHandler = new Handler(Looper.myLooper()) { // from class: com.huya.mtp.hycloudgame.base.tcpsocket.core.TcpClientCore.2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    try {
                        if (TcpClientCore.this.getTransceiver() != null) {
                            TcpClientCore.this.getTransceiver().send(message.getData().getByteArray("input"));
                        } else {
                            MTPApi.LOGGER.error("NetServiceTcpSocketClientgetTransceiver()==null");
                        }
                    } catch (Exception e) {
                        MTPApi.LOGGER.error("NetServiceTcpSocketClienthandleMessage ", e);
                    }
                }
            };
            this.connect = true;
            if (this.mListener != null) {
                this.mListener.onOpen();
            }
            Looper.loop();
        } catch (Exception e) {
            MTPApi.LOGGER.error("NetServiceTcpSocketClient:socket run hostIP = [" + this.hostIP + "], port = [" + this.port + "]", e);
            ISocketClientListener iSocketClientListener = this.mListener;
            if (iSocketClientListener != null) {
                iSocketClientListener.onError(e);
            }
        }
        MTPApi.LOGGER.info(TAG, "run: finish");
    }

    @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.ITcpClient
    public void disconnect() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        synchronized (this) {
            if (this.transceiver != null) {
                this.transceiver.stop();
                this.transceiver = null;
            }
        }
    }

    @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.ITcpClient
    public boolean isConnected() {
        return this.connect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TcpSocketTransceiver getTransceiver() {
        if (isConnected()) {
            return this.transceiver;
        }
        return null;
    }

    @Override // com.huya.mtp.hycloudgame.base.tcpsocket.core.ITcpClient
    public boolean send(byte[] bArr) {
        if (getTransceiver() == null) {
            MTPApi.LOGGER.error(TAG, "send: getTransceiver() == null");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putByteArray("input", bArr);
        Message message = new Message();
        message.setData(bundle);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendMessage(message);
            return true;
        }
        MTPApi.LOGGER.error(TAG, "mHandler not ready, will miss message");
        return false;
    }
}
