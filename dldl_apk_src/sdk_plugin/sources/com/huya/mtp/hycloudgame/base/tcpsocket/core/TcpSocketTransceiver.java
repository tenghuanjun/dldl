package com.huya.mtp.hycloudgame.base.tcpsocket.core;

import com.huya.mtp.api.MTPApi;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class TcpSocketTransceiver implements Runnable {
    private static final String TAG = "NetServiceTcpSocketTransceiver";
    protected InetAddress addr;
    protected DataInputStream in;
    protected DataOutputStream out;
    private volatile boolean runFlag;
    protected Socket socket;

    public abstract void onDisconnect(InetAddress inetAddress);

    public abstract void onReceive(InetAddress inetAddress, byte[] bArr, int i);

    public TcpSocketTransceiver(Socket socket) {
        this.socket = socket;
        this.addr = socket.getInetAddress();
        try {
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            this.runFlag = false;
        }
    }

    public InetAddress getInetAddress() {
        return this.addr;
    }

    public void start() {
        MTPApi.LOGGER.info(TAG, "start() called");
        this.runFlag = true;
        new Thread(this).start();
    }

    public void stop() {
        this.runFlag = false;
        try {
            synchronized (this) {
                if (this.socket != null && this.socket.isConnected() && !this.socket.isClosed()) {
                    this.in.close();
                    this.out.close();
                    this.socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MTPApi.LOGGER.error(TAG, "stop");
        }
    }

    public boolean send(byte[] bArr) {
        if (bArr == null) {
            MTPApi.LOGGER.error("NetServiceTcpSocketTransceiver:send() data == null");
            return false;
        }
        if (this.out == null) {
            MTPApi.LOGGER.error("NetServiceTcpSocketTransceiver:send() out == null");
            return false;
        }
        MTPApi.LOGGER.info(TAG, "send() called with: data = [" + bArr.length + "]");
        try {
            this.out.write(bArr);
            this.out.flush();
            return true;
        } catch (Exception e) {
            MTPApi.LOGGER.error("NetServiceTcpSocketTransceiver:send()", e);
            return false;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.runFlag) {
            synchronized (this) {
                if (this.socket != null && this.socket.isConnected() && !this.socket.isClosed()) {
                    try {
                        if (this.in != null && this.in.available() > 0) {
                            int iAvailable = this.in.available();
                            byte[] bArr = new byte[iAvailable];
                            this.in.read(bArr, 0, iAvailable);
                            onReceive(this.addr, bArr, iAvailable);
                        }
                    } catch (IOException e) {
                        this.runFlag = false;
                        MTPApi.LOGGER.error("NetServiceTcpSocketTransceiverSocketTransceiver run: ", e);
                    }
                }
            }
        }
        try {
            this.in.close();
            this.out.close();
            this.in = null;
            this.out = null;
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        onDisconnect(this.addr);
    }
}
