package com.huya.mtp.hycloudgame.base.tcpsocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TcpSocketFactory extends SocketFactory {
    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        Socket socket = new Socket();
        socket.setTcpNoDelay(true);
        return socket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        Socket socketCreateSocket = createSocket();
        try {
            socketCreateSocket.connect(new InetSocketAddress(str, i));
            return socketCreateSocket;
        } catch (IOException e) {
            socketCreateSocket.close();
            throw e;
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        Socket socketCreateSocket = createSocket();
        try {
            socketCreateSocket.connect(new InetSocketAddress(inetAddress, i));
            return socketCreateSocket;
        } catch (IOException e) {
            socketCreateSocket.close();
            throw e;
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        Socket socketCreateSocket = createSocket();
        try {
            socketCreateSocket.bind(new InetSocketAddress(inetAddress, i2));
            socketCreateSocket.connect(new InetSocketAddress(str, i));
            return socketCreateSocket;
        } catch (IOException e) {
            socketCreateSocket.close();
            throw e;
        }
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        Socket socketCreateSocket = createSocket();
        try {
            socketCreateSocket.bind(new InetSocketAddress(inetAddress2, i2));
            socketCreateSocket.connect(new InetSocketAddress(inetAddress, i));
            return socketCreateSocket;
        } catch (IOException e) {
            socketCreateSocket.close();
            throw e;
        }
    }
}
