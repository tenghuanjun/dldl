package org.java_websocket.client;

import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.java_websocket.SocketChannelIOHelper;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WrappedByteChannel;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public abstract class WebSocketClient extends WebSocketAdapter implements Runnable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private SocketChannel channel;
    private CountDownLatch closeLatch;
    private WebSocketImpl conn;
    private CountDownLatch connectLatch;
    private Draft draft;
    private Map<String, String> headers;
    private InetSocketAddress proxyAddress;
    private Thread readthread;
    private int timeout;
    protected URI uri;
    private ByteChannel wrappedchannel;
    private Thread writethread;
    private WebSocketClientFactory wsfactory;

    public interface WebSocketClientFactory extends WebSocketFactory {
        ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey, String str, int i) throws IOException;
    }

    public abstract void onClose(int i, String str, boolean z);

    public void onCloseInitiated(int i, String str) {
    }

    public void onClosing(int i, String str, boolean z) {
    }

    public abstract void onError(Exception exc);

    public abstract void onMessage(String str);

    public void onMessage(ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(ServerHandshake serverHandshake);

    @Override // org.java_websocket.WebSocketListener
    public final void onWriteDemand(WebSocket webSocket) {
    }

    public WebSocketClient(URI uri) {
        this(uri, new Draft_10());
    }

    public WebSocketClient(URI uri, Draft draft) {
        this(uri, draft, null, 0);
    }

    public WebSocketClient(URI uri, Draft draft, Map<String, String> map, int i) {
        this.uri = null;
        this.conn = null;
        this.channel = null;
        this.wrappedchannel = null;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.timeout = 0;
        this.wsfactory = new DefaultWebSocketClientFactory(this);
        this.proxyAddress = null;
        if (uri == null) {
            throw new IllegalArgumentException();
        }
        if (draft == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        }
        this.uri = uri;
        this.draft = draft;
        this.headers = map;
        this.timeout = i;
        try {
            SocketChannel socketChannelOpenSocketChannel = SelectorProvider.provider().openSocketChannel();
            this.channel = socketChannelOpenSocketChannel;
            socketChannelOpenSocketChannel.configureBlocking(true);
        } catch (IOException e) {
            this.channel = null;
            onWebsocketError(null, e);
        }
        SocketChannel socketChannel = this.channel;
        if (socketChannel == null) {
            WebSocketImpl webSocketImpl = (WebSocketImpl) this.wsfactory.createWebSocket(this, draft, (Socket) null);
            this.conn = webSocketImpl;
            webSocketImpl.close(-1, "Failed to create or configure SocketChannel.");
            return;
        }
        this.conn = (WebSocketImpl) this.wsfactory.createWebSocket(this, draft, socketChannel.socket());
    }

    public URI getURI() {
        return this.uri;
    }

    public Draft getDraft() {
        return this.draft;
    }

    public void connect() {
        if (this.writethread != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        Thread thread = new Thread(this);
        this.writethread = thread;
        thread.start();
    }

    public boolean connectBlocking() throws InterruptedException {
        connect();
        this.connectLatch.await();
        return this.conn.isOpen();
    }

    public void close() {
        if (this.writethread != null) {
            this.conn.close(1000);
        }
    }

    public void closeBlocking() throws InterruptedException {
        close();
        this.closeLatch.await();
    }

    public void send(String str) throws NotYetConnectedException {
        this.conn.send(str);
    }

    public void send(byte[] bArr) throws NotYetConnectedException {
        this.conn.send(bArr);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.writethread == null) {
            this.writethread = Thread.currentThread();
        }
        interruptableRun();
    }

    private final void interruptableRun() {
        String host;
        int port;
        if (this.channel == null) {
            return;
        }
        try {
            if (this.proxyAddress != null) {
                host = this.proxyAddress.getHostName();
                port = this.proxyAddress.getPort();
            } else {
                host = this.uri.getHost();
                port = getPort();
            }
            this.channel.connect(new InetSocketAddress(host, port));
            WebSocketImpl webSocketImpl = this.conn;
            ByteChannel byteChannelCreateProxyChannel = createProxyChannel(this.wsfactory.wrapChannel(this.channel, null, host, port));
            this.wrappedchannel = byteChannelCreateProxyChannel;
            webSocketImpl.channel = byteChannelCreateProxyChannel;
            this.timeout = 0;
            sendHandshake();
            Thread thread = new Thread(new WebsocketWriteThread());
            this.readthread = thread;
            thread.start();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(WebSocketImpl.RCVBUF);
            while (this.channel.isOpen()) {
                try {
                    if (SocketChannelIOHelper.read(byteBufferAllocate, this.conn, this.wrappedchannel)) {
                        this.conn.decode(byteBufferAllocate);
                    } else {
                        this.conn.eot();
                    }
                    if (this.wrappedchannel instanceof WrappedByteChannel) {
                        WrappedByteChannel wrappedByteChannel = (WrappedByteChannel) this.wrappedchannel;
                        if (wrappedByteChannel.isNeedRead()) {
                            while (SocketChannelIOHelper.readMore(byteBufferAllocate, this.conn, wrappedByteChannel)) {
                                this.conn.decode(byteBufferAllocate);
                            }
                            this.conn.decode(byteBufferAllocate);
                        }
                    }
                } catch (IOException unused) {
                    this.conn.eot();
                    return;
                } catch (CancelledKeyException unused2) {
                    this.conn.eot();
                    return;
                } catch (RuntimeException e) {
                    onError(e);
                    this.conn.closeConnection(1006, e.getMessage());
                    return;
                }
            }
        } catch (ClosedByInterruptException e2) {
            onWebsocketError(null, e2);
        } catch (Exception e3) {
            onWebsocketError(this.conn, e3);
            this.conn.closeConnection(-1, e3.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPort() {
        int port = this.uri.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.uri.getScheme();
        if (scheme.equals("wss")) {
            return 443;
        }
        if (scheme.equals("ws")) {
            return 80;
        }
        throw new RuntimeException("unkonow scheme" + scheme);
    }

    private void sendHandshake() throws InvalidHandshakeException {
        String path = this.uri.getPath();
        String query = this.uri.getQuery();
        if (path == null || path.length() == 0) {
            path = "/";
        }
        if (query != null) {
            path = path + "?" + query;
        }
        int port = getPort();
        StringBuilder sb = new StringBuilder();
        sb.append(this.uri.getHost());
        sb.append(port != 80 ? ":" + port : "");
        String string = sb.toString();
        HandshakeImpl1Client handshakeImpl1Client = new HandshakeImpl1Client();
        handshakeImpl1Client.setResourceDescriptor(path);
        handshakeImpl1Client.put("Host", string);
        Map<String, String> map = this.headers;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                handshakeImpl1Client.put(entry.getKey(), entry.getValue());
            }
        }
        this.conn.startHandshake(handshakeImpl1Client);
    }

    public WebSocket.READYSTATE getReadyState() {
        return this.conn.getReadyState();
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(str);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(byteBuffer);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        this.connectLatch.countDown();
        onOpen((ServerHandshake) handshakedata);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        this.connectLatch.countDown();
        this.closeLatch.countDown();
        Thread thread = this.readthread;
        if (thread != null) {
            thread.interrupt();
        }
        onClose(i, str, z);
    }

    @Override // org.java_websocket.WebSocketListener
    public final void onWebsocketError(WebSocket webSocket, Exception exc) {
        onError(exc);
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(i, str);
    }

    @Override // org.java_websocket.WebSocketListener
    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(i, str, z);
    }

    public WebSocket getConnection() {
        return this.conn;
    }

    public final void setWebSocketFactory(WebSocketClientFactory webSocketClientFactory) {
        this.wsfactory = webSocketClientFactory;
    }

    public final WebSocketFactory getWebSocketFactory() {
        return this.wsfactory;
    }

    @Override // org.java_websocket.WebSocketListener
    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        SocketChannel socketChannel = this.channel;
        if (socketChannel != null) {
            return (InetSocketAddress) socketChannel.socket().getLocalSocketAddress();
        }
        return null;
    }

    @Override // org.java_websocket.WebSocketListener
    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        SocketChannel socketChannel = this.channel;
        if (socketChannel != null) {
            return (InetSocketAddress) socketChannel.socket().getLocalSocketAddress();
        }
        return null;
    }

    public class DefaultClientProxyChannel extends AbstractClientProxyChannel {
        public DefaultClientProxyChannel(ByteChannel byteChannel) {
            super(byteChannel);
        }

        @Override // org.java_websocket.client.AbstractClientProxyChannel
        public String buildHandShake() {
            StringBuilder sb = new StringBuilder();
            String host = WebSocketClient.this.uri.getHost();
            sb.append("CONNECT ");
            sb.append(host);
            sb.append(":");
            sb.append(WebSocketClient.this.getPort());
            sb.append(" HTTP/1.1\n");
            sb.append("Host: ");
            sb.append(host);
            sb.append(ShellAdbUtils.COMMAND_LINE_END);
            return sb.toString();
        }
    }

    private class WebsocketWriteThread implements Runnable {
        private WebsocketWriteThread() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("WebsocketWriteThread");
            while (!Thread.interrupted()) {
                try {
                    SocketChannelIOHelper.writeBlocking(WebSocketClient.this.conn, WebSocketClient.this.wrappedchannel);
                } catch (IOException unused) {
                    WebSocketClient.this.conn.eot();
                    return;
                } catch (InterruptedException unused2) {
                    return;
                }
            }
        }
    }

    public ByteChannel createProxyChannel(ByteChannel byteChannel) {
        return this.proxyAddress != null ? new DefaultClientProxyChannel(byteChannel) : byteChannel;
    }

    public void setProxy(InetSocketAddress inetSocketAddress) {
        this.proxyAddress = inetSocketAddress;
    }
}
