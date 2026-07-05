package com.sq.sywebsocket;

import com.sq.sywebsocket.drafts.Draft;
import com.sq.sywebsocket.enums.Opcode;
import com.sq.sywebsocket.enums.ReadyState;
import com.sq.sywebsocket.framing.Framedata;
import com.sq.sywebsocket.protocols.IProtocol;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Collection;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface WebSocket {
    void close();

    void close(int i);

    void close(int i, String str);

    void closeConnection(int i, String str);

    <T> T getAttachment();

    Draft getDraft();

    InetSocketAddress getLocalSocketAddress();

    IProtocol getProtocol();

    ReadyState getReadyState();

    InetSocketAddress getRemoteSocketAddress();

    String getResourceDescriptor();

    SSLSession getSSLSession() throws IllegalArgumentException;

    boolean hasBufferedData();

    boolean hasSSLSupport();

    boolean isClosed();

    boolean isClosing();

    boolean isFlushAndClose();

    boolean isOpen();

    void send(String str);

    void send(ByteBuffer byteBuffer);

    void send(byte[] bArr);

    void sendFragmentedFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z);

    void sendFrame(Framedata framedata);

    void sendFrame(Collection<Framedata> collection);

    void sendPing();

    <T> void setAttachment(T t);
}
