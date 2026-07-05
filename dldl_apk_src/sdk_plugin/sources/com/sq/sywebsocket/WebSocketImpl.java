package com.sq.sywebsocket;

import com.sq.sywebsocket.drafts.Draft;
import com.sq.sywebsocket.drafts.Draft_6455;
import com.sq.sywebsocket.enums.CloseHandshakeType;
import com.sq.sywebsocket.enums.HandshakeState;
import com.sq.sywebsocket.enums.Opcode;
import com.sq.sywebsocket.enums.ReadyState;
import com.sq.sywebsocket.enums.Role;
import com.sq.sywebsocket.exceptions.IncompleteHandshakeException;
import com.sq.sywebsocket.exceptions.InvalidDataException;
import com.sq.sywebsocket.exceptions.InvalidHandshakeException;
import com.sq.sywebsocket.exceptions.LimitExceededException;
import com.sq.sywebsocket.exceptions.WebsocketNotConnectedException;
import com.sq.sywebsocket.framing.Framedata;
import com.sq.sywebsocket.framing.PingFrame;
import com.sq.sywebsocket.handshake.ClientHandshake;
import com.sq.sywebsocket.handshake.ClientHandshakeBuilder;
import com.sq.sywebsocket.handshake.Handshakedata;
import com.sq.sywebsocket.handshake.ServerHandshake;
import com.sq.sywebsocket.interfaces.ISSLChannel;
import com.sq.sywebsocket.protocols.IProtocol;
import com.sq.sywebsocket.server.WebSocketServer;
import com.sq.sywebsocket.util.Charsetfunctions;
import com.sy37sdk.order.third.union.UnionPayWay;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.net.ssl.SSLSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketImpl implements WebSocket {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_PORT = 80;
    public static final int DEFAULT_WSS_PORT = 443;
    public static final int RCVBUF = 16384;
    private Object attachment;
    private ByteChannel channel;
    private Integer closecode;
    private Boolean closedremotely;
    private String closemessage;
    private Draft draft;
    private boolean flushandclosestate;
    private ClientHandshake handshakerequest;
    public final BlockingQueue<ByteBuffer> inQueue;
    private SelectionKey key;
    private List<Draft> knownDrafts;
    private long lastPong;
    private final Logger log;
    public final BlockingQueue<ByteBuffer> outQueue;
    private volatile ReadyState readyState;
    private String resourceDescriptor;
    private Role role;
    private final Object synchronizeWriteObject;
    private ByteBuffer tmpHandshakeBytes;
    private WebSocketServer.WebSocketWorker workerThread;
    private final WebSocketListener wsl;

    public WebSocketImpl(WebSocketListener webSocketListener, List<Draft> list) {
        this(webSocketListener, (Draft) null);
        this.role = Role.SERVER;
        if (list == null || list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            this.knownDrafts = arrayList;
            arrayList.add(new Draft_6455());
            return;
        }
        this.knownDrafts = list;
    }

    public WebSocketImpl(WebSocketListener webSocketListener, Draft draft) {
        this.log = LoggerFactory.getLogger((Class<?>) WebSocketImpl.class);
        this.flushandclosestate = false;
        this.readyState = ReadyState.NOT_YET_CONNECTED;
        this.draft = null;
        this.tmpHandshakeBytes = ByteBuffer.allocate(0);
        this.handshakerequest = null;
        this.closemessage = null;
        this.closecode = null;
        this.closedremotely = null;
        this.resourceDescriptor = null;
        this.lastPong = System.nanoTime();
        this.synchronizeWriteObject = new Object();
        if (webSocketListener == null || (draft == null && this.role == Role.SERVER)) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.outQueue = new LinkedBlockingQueue();
        this.inQueue = new LinkedBlockingQueue();
        this.wsl = webSocketListener;
        this.role = Role.CLIENT;
        if (draft != null) {
            this.draft = draft.copyInstance();
        }
    }

    public void decode(ByteBuffer byteBuffer) {
        this.log.trace("process({}): ({})", Integer.valueOf(byteBuffer.remaining()), byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining()));
        if (this.readyState != ReadyState.NOT_YET_CONNECTED) {
            if (this.readyState == ReadyState.OPEN) {
                decodeFrames(byteBuffer);
            }
        } else {
            if (!decodeHandshake(byteBuffer) || isClosing() || isClosed()) {
                return;
            }
            if (byteBuffer.hasRemaining()) {
                decodeFrames(byteBuffer);
            } else if (this.tmpHandshakeBytes.hasRemaining()) {
                decodeFrames(this.tmpHandshakeBytes);
            }
        }
    }

    private boolean decodeHandshake(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        Handshakedata handshakedataTranslateHandshake;
        if (this.tmpHandshakeBytes.capacity() == 0) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.tmpHandshakeBytes.remaining() < byteBuffer.remaining()) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.tmpHandshakeBytes.capacity() + byteBuffer.remaining());
                this.tmpHandshakeBytes.flip();
                byteBufferAllocate.put(this.tmpHandshakeBytes);
                this.tmpHandshakeBytes = byteBufferAllocate;
            }
            this.tmpHandshakeBytes.put(byteBuffer);
            this.tmpHandshakeBytes.flip();
            byteBuffer2 = this.tmpHandshakeBytes;
        }
        byteBuffer2.mark();
        try {
            try {
            } catch (InvalidHandshakeException e) {
                this.log.trace("Closing due to invalid handshake", (Throwable) e);
                close(e);
            }
        } catch (IncompleteHandshakeException e2) {
            if (this.tmpHandshakeBytes.capacity() == 0) {
                byteBuffer2.reset();
                int preferredSize = e2.getPreferredSize();
                if (preferredSize == 0) {
                    preferredSize = byteBuffer2.capacity() + 16;
                }
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(preferredSize);
                this.tmpHandshakeBytes = byteBufferAllocate2;
                byteBufferAllocate2.put(byteBuffer);
            } else {
                ByteBuffer byteBuffer3 = this.tmpHandshakeBytes;
                byteBuffer3.position(byteBuffer3.limit());
                ByteBuffer byteBuffer4 = this.tmpHandshakeBytes;
                byteBuffer4.limit(byteBuffer4.capacity());
            }
        }
        if (this.role == Role.SERVER) {
            if (this.draft == null) {
                Iterator<Draft> it = this.knownDrafts.iterator();
                while (it.hasNext()) {
                    Draft draftCopyInstance = it.next().copyInstance();
                    try {
                        draftCopyInstance.setParseMode(this.role);
                        byteBuffer2.reset();
                        handshakedataTranslateHandshake = draftCopyInstance.translateHandshake(byteBuffer2);
                    } catch (InvalidHandshakeException unused) {
                    }
                    if (!(handshakedataTranslateHandshake instanceof ClientHandshake)) {
                        this.log.trace("Closing due to wrong handshake");
                        closeConnectionDueToWrongHandshake(new InvalidDataException(1002, "wrong http function"));
                        return false;
                    }
                    ClientHandshake clientHandshake = (ClientHandshake) handshakedataTranslateHandshake;
                    if (draftCopyInstance.acceptHandshakeAsServer(clientHandshake) == HandshakeState.MATCHED) {
                        this.resourceDescriptor = clientHandshake.getResourceDescriptor();
                        try {
                            write(draftCopyInstance.createHandshake(draftCopyInstance.postProcessHandshakeResponseAsServer(clientHandshake, this.wsl.onWebsocketHandshakeReceivedAsServer(this, draftCopyInstance, clientHandshake))));
                            this.draft = draftCopyInstance;
                            open(clientHandshake);
                            return true;
                        } catch (InvalidDataException e3) {
                            this.log.trace("Closing due to wrong handshake. Possible handshake rejection", (Throwable) e3);
                            closeConnectionDueToWrongHandshake(e3);
                            return false;
                        } catch (RuntimeException e4) {
                            this.log.error("Closing due to internal server error", (Throwable) e4);
                            this.wsl.onWebsocketError(this, e4);
                            closeConnectionDueToInternalServerError(e4);
                            return false;
                        }
                    }
                }
                if (this.draft == null) {
                    this.log.trace("Closing due to protocol error: no draft matches");
                    closeConnectionDueToWrongHandshake(new InvalidDataException(1002, "no draft matches"));
                }
                return false;
            }
            Handshakedata handshakedataTranslateHandshake2 = this.draft.translateHandshake(byteBuffer2);
            if (!(handshakedataTranslateHandshake2 instanceof ClientHandshake)) {
                this.log.trace("Closing due to protocol error: wrong http function");
                flushAndClose(1002, "wrong http function", false);
                return false;
            }
            ClientHandshake clientHandshake2 = (ClientHandshake) handshakedataTranslateHandshake2;
            if (this.draft.acceptHandshakeAsServer(clientHandshake2) == HandshakeState.MATCHED) {
                open(clientHandshake2);
                return true;
            }
            this.log.trace("Closing due to protocol error: the handshake did finally not match");
            close(1002, "the handshake did finally not match");
            return false;
        }
        if (this.role == Role.CLIENT) {
            this.draft.setParseMode(this.role);
            Handshakedata handshakedataTranslateHandshake3 = this.draft.translateHandshake(byteBuffer2);
            if (!(handshakedataTranslateHandshake3 instanceof ServerHandshake)) {
                this.log.trace("Closing due to protocol error: wrong http function");
                flushAndClose(1002, "wrong http function", false);
                return false;
            }
            ServerHandshake serverHandshake = (ServerHandshake) handshakedataTranslateHandshake3;
            if (this.draft.acceptHandshakeAsClient(this.handshakerequest, serverHandshake) == HandshakeState.MATCHED) {
                try {
                    this.wsl.onWebsocketHandshakeReceivedAsClient(this, this.handshakerequest, serverHandshake);
                    open(serverHandshake);
                    return true;
                } catch (InvalidDataException e5) {
                    this.log.trace("Closing due to invalid data exception. Possible handshake rejection", (Throwable) e5);
                    flushAndClose(e5.getCloseCode(), e5.getMessage(), false);
                    return false;
                } catch (RuntimeException e6) {
                    this.log.error("Closing since client was never connected", (Throwable) e6);
                    this.wsl.onWebsocketError(this, e6);
                    flushAndClose(-1, e6.getMessage(), false);
                    return false;
                }
            }
            this.log.trace("Closing due to protocol error: draft {} refuses handshake", this.draft);
            close(1002, "draft " + this.draft + " refuses handshake");
        }
        return false;
    }

    private void decodeFrames(ByteBuffer byteBuffer) {
        try {
            for (Framedata framedata : this.draft.translateFrame(byteBuffer)) {
                this.log.trace("matched frame: {}", framedata);
                this.draft.processFrame(this, framedata);
            }
        } catch (LimitExceededException e) {
            if (e.getLimit() == Integer.MAX_VALUE) {
                this.log.error("Closing due to invalid size of frame", (Throwable) e);
                this.wsl.onWebsocketError(this, e);
            }
            close(e);
        } catch (InvalidDataException e2) {
            this.log.error("Closing due to invalid data in frame", (Throwable) e2);
            this.wsl.onWebsocketError(this, e2);
            close(e2);
        }
    }

    private void closeConnectionDueToWrongHandshake(InvalidDataException invalidDataException) {
        write(generateHttpResponseDueToError(UnionPayWay.ERROR_INVALID_RESULT));
        flushAndClose(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    private void closeConnectionDueToInternalServerError(RuntimeException runtimeException) {
        write(generateHttpResponseDueToError(500));
        flushAndClose(-1, runtimeException.getMessage(), false);
    }

    private ByteBuffer generateHttpResponseDueToError(int i) {
        String str = i != 404 ? "500 Internal Server Error" : "404 WebSocket Upgrade Failure";
        return ByteBuffer.wrap(Charsetfunctions.asciiBytes("HTTP/1.1 " + str + "\r\nContent-Type: text/html\r\nServer: TooTallNate Java-WebSocket\r\nContent-Length: " + (str.length() + 48) + "\r\n\r\n<html><head></head><body><h1>" + str + "</h1></body></html>"));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003f A[Catch: InvalidDataException -> 0x0051, all -> 0x0086, TRY_LEAVE, TryCatch #2 {InvalidDataException -> 0x0051, blocks: (B:17:0x002d, B:21:0x0039, B:23:0x003f, B:20:0x0034), top: B:44:0x002d, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void close(int r6, java.lang.String r7, boolean r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.sq.sywebsocket.enums.ReadyState r0 = r5.readyState     // Catch: java.lang.Throwable -> L86
            com.sq.sywebsocket.enums.ReadyState r1 = com.sq.sywebsocket.enums.ReadyState.CLOSING     // Catch: java.lang.Throwable -> L86
            if (r0 == r1) goto L84
            com.sq.sywebsocket.enums.ReadyState r0 = r5.readyState     // Catch: java.lang.Throwable -> L86
            com.sq.sywebsocket.enums.ReadyState r1 = com.sq.sywebsocket.enums.ReadyState.CLOSED     // Catch: java.lang.Throwable -> L86
            if (r0 == r1) goto L84
            com.sq.sywebsocket.enums.ReadyState r0 = r5.readyState     // Catch: java.lang.Throwable -> L86
            com.sq.sywebsocket.enums.ReadyState r1 = com.sq.sywebsocket.enums.ReadyState.OPEN     // Catch: java.lang.Throwable -> L86
            r2 = 0
            if (r0 != r1) goto L67
            r0 = 1006(0x3ee, float:1.41E-42)
            if (r6 != r0) goto L21
            com.sq.sywebsocket.enums.ReadyState r8 = com.sq.sywebsocket.enums.ReadyState.CLOSING     // Catch: java.lang.Throwable -> L86
            r5.readyState = r8     // Catch: java.lang.Throwable -> L86
            r5.flushAndClose(r6, r7, r2)     // Catch: java.lang.Throwable -> L86
            monitor-exit(r5)
            return
        L21:
            com.sq.sywebsocket.drafts.Draft r1 = r5.draft     // Catch: java.lang.Throwable -> L86
            com.sq.sywebsocket.enums.CloseHandshakeType r1 = r1.getCloseHandshakeType()     // Catch: java.lang.Throwable -> L86
            com.sq.sywebsocket.enums.CloseHandshakeType r3 = com.sq.sywebsocket.enums.CloseHandshakeType.NONE     // Catch: java.lang.Throwable -> L86
            if (r1 == r3) goto L63
            if (r8 != 0) goto L39
            com.sq.sywebsocket.WebSocketListener r1 = r5.wsl     // Catch: java.lang.RuntimeException -> L33 com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            r1.onWebsocketCloseInitiated(r5, r6, r7)     // Catch: java.lang.RuntimeException -> L33 com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            goto L39
        L33:
            r1 = move-exception
            com.sq.sywebsocket.WebSocketListener r3 = r5.wsl     // Catch: com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            r3.onWebsocketError(r5, r1)     // Catch: com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
        L39:
            boolean r1 = r5.isOpen()     // Catch: com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            if (r1 == 0) goto L63
            com.sq.sywebsocket.framing.CloseFrame r1 = new com.sq.sywebsocket.framing.CloseFrame     // Catch: com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            r1.<init>()     // Catch: com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            r1.setReason(r7)     // Catch: com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            r1.setCode(r6)     // Catch: com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            r1.isValid()     // Catch: com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            r5.sendFrame(r1)     // Catch: com.sq.sywebsocket.exceptions.InvalidDataException -> L51 java.lang.Throwable -> L86
            goto L63
        L51:
            r1 = move-exception
            org.slf4j.Logger r3 = r5.log     // Catch: java.lang.Throwable -> L86
            java.lang.String r4 = "generated frame is invalid"
            r3.error(r4, r1)     // Catch: java.lang.Throwable -> L86
            com.sq.sywebsocket.WebSocketListener r3 = r5.wsl     // Catch: java.lang.Throwable -> L86
            r3.onWebsocketError(r5, r1)     // Catch: java.lang.Throwable -> L86
            java.lang.String r1 = "generated frame is invalid"
            r5.flushAndClose(r0, r1, r2)     // Catch: java.lang.Throwable -> L86
        L63:
            r5.flushAndClose(r6, r7, r8)     // Catch: java.lang.Throwable -> L86
            goto L7b
        L67:
            r0 = -3
            if (r6 != r0) goto L6f
            r6 = 1
            r5.flushAndClose(r0, r7, r6)     // Catch: java.lang.Throwable -> L86
            goto L7b
        L6f:
            r0 = 1002(0x3ea, float:1.404E-42)
            if (r6 != r0) goto L77
            r5.flushAndClose(r6, r7, r8)     // Catch: java.lang.Throwable -> L86
            goto L7b
        L77:
            r6 = -1
            r5.flushAndClose(r6, r7, r2)     // Catch: java.lang.Throwable -> L86
        L7b:
            com.sq.sywebsocket.enums.ReadyState r6 = com.sq.sywebsocket.enums.ReadyState.CLOSING     // Catch: java.lang.Throwable -> L86
            r5.readyState = r6     // Catch: java.lang.Throwable -> L86
            r6 = 0
            r5.tmpHandshakeBytes = r6     // Catch: java.lang.Throwable -> L86
            monitor-exit(r5)
            return
        L84:
            monitor-exit(r5)
            return
        L86:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.sywebsocket.WebSocketImpl.close(int, java.lang.String, boolean):void");
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void close(int i, String str) {
        close(i, str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0061 A[Catch: all -> 0x006f, TryCatch #2 {, blocks: (B:3:0x0001, B:7:0x0009, B:11:0x0013, B:12:0x0017, B:14:0x001b, B:15:0x0020, B:17:0x0024, B:26:0x0051, B:30:0x005d, B:32:0x0061, B:33:0x0066, B:29:0x0058, B:20:0x002b, B:22:0x0031, B:24:0x003d, B:25:0x0045), top: B:43:0x0001, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void closeConnection(int r4, java.lang.String r5, boolean r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.sq.sywebsocket.enums.ReadyState r0 = r3.readyState     // Catch: java.lang.Throwable -> L6f
            com.sq.sywebsocket.enums.ReadyState r1 = com.sq.sywebsocket.enums.ReadyState.CLOSED     // Catch: java.lang.Throwable -> L6f
            if (r0 != r1) goto L9
            monitor-exit(r3)
            return
        L9:
            com.sq.sywebsocket.enums.ReadyState r0 = r3.readyState     // Catch: java.lang.Throwable -> L6f
            com.sq.sywebsocket.enums.ReadyState r1 = com.sq.sywebsocket.enums.ReadyState.OPEN     // Catch: java.lang.Throwable -> L6f
            if (r0 != r1) goto L17
            r0 = 1006(0x3ee, float:1.41E-42)
            if (r4 != r0) goto L17
            com.sq.sywebsocket.enums.ReadyState r0 = com.sq.sywebsocket.enums.ReadyState.CLOSING     // Catch: java.lang.Throwable -> L6f
            r3.readyState = r0     // Catch: java.lang.Throwable -> L6f
        L17:
            java.nio.channels.SelectionKey r0 = r3.key     // Catch: java.lang.Throwable -> L6f
            if (r0 == 0) goto L20
            java.nio.channels.SelectionKey r0 = r3.key     // Catch: java.lang.Throwable -> L6f
            r0.cancel()     // Catch: java.lang.Throwable -> L6f
        L20:
            java.nio.channels.ByteChannel r0 = r3.channel     // Catch: java.lang.Throwable -> L6f
            if (r0 == 0) goto L51
            java.nio.channels.ByteChannel r0 = r3.channel     // Catch: java.io.IOException -> L2a java.lang.Throwable -> L6f
            r0.close()     // Catch: java.io.IOException -> L2a java.lang.Throwable -> L6f
            goto L51
        L2a:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()     // Catch: java.lang.Throwable -> L6f
            if (r1 == 0) goto L45
            java.lang.String r1 = r0.getMessage()     // Catch: java.lang.Throwable -> L6f
            java.lang.String r2 = "Broken pipe"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Throwable -> L6f
            if (r1 == 0) goto L45
            org.slf4j.Logger r1 = r3.log     // Catch: java.lang.Throwable -> L6f
            java.lang.String r2 = "Caught IOException: Broken pipe during closeConnection()"
            r1.trace(r2, r0)     // Catch: java.lang.Throwable -> L6f
            goto L51
        L45:
            org.slf4j.Logger r1 = r3.log     // Catch: java.lang.Throwable -> L6f
            java.lang.String r2 = "Exception during channel.close()"
            r1.error(r2, r0)     // Catch: java.lang.Throwable -> L6f
            com.sq.sywebsocket.WebSocketListener r1 = r3.wsl     // Catch: java.lang.Throwable -> L6f
            r1.onWebsocketError(r3, r0)     // Catch: java.lang.Throwable -> L6f
        L51:
            com.sq.sywebsocket.WebSocketListener r0 = r3.wsl     // Catch: java.lang.RuntimeException -> L57 java.lang.Throwable -> L6f
            r0.onWebsocketClose(r3, r4, r5, r6)     // Catch: java.lang.RuntimeException -> L57 java.lang.Throwable -> L6f
            goto L5d
        L57:
            r4 = move-exception
            com.sq.sywebsocket.WebSocketListener r5 = r3.wsl     // Catch: java.lang.Throwable -> L6f
            r5.onWebsocketError(r3, r4)     // Catch: java.lang.Throwable -> L6f
        L5d:
            com.sq.sywebsocket.drafts.Draft r4 = r3.draft     // Catch: java.lang.Throwable -> L6f
            if (r4 == 0) goto L66
            com.sq.sywebsocket.drafts.Draft r4 = r3.draft     // Catch: java.lang.Throwable -> L6f
            r4.reset()     // Catch: java.lang.Throwable -> L6f
        L66:
            r4 = 0
            r3.handshakerequest = r4     // Catch: java.lang.Throwable -> L6f
            com.sq.sywebsocket.enums.ReadyState r4 = com.sq.sywebsocket.enums.ReadyState.CLOSED     // Catch: java.lang.Throwable -> L6f
            r3.readyState = r4     // Catch: java.lang.Throwable -> L6f
            monitor-exit(r3)
            return
        L6f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.sywebsocket.WebSocketImpl.closeConnection(int, java.lang.String, boolean):void");
    }

    protected void closeConnection(int i, boolean z) {
        closeConnection(i, "", z);
    }

    public void closeConnection() {
        if (this.closedremotely == null) {
            throw new IllegalStateException("this method must be used in conjunction with flushAndClose");
        }
        closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void closeConnection(int i, String str) {
        closeConnection(i, str, false);
    }

    public synchronized void flushAndClose(int i, String str, boolean z) {
        if (this.flushandclosestate) {
            return;
        }
        this.closecode = Integer.valueOf(i);
        this.closemessage = str;
        this.closedremotely = Boolean.valueOf(z);
        this.flushandclosestate = true;
        this.wsl.onWriteDemand(this);
        try {
            this.wsl.onWebsocketClosing(this, i, str, z);
        } catch (RuntimeException e) {
            this.log.error("Exception in onWebsocketClosing", (Throwable) e);
            this.wsl.onWebsocketError(this, e);
        }
        if (this.draft != null) {
            this.draft.reset();
        }
        this.handshakerequest = null;
    }

    public void eot() {
        if (this.readyState == ReadyState.NOT_YET_CONNECTED) {
            closeConnection(-1, true);
            return;
        }
        if (this.flushandclosestate) {
            closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
            return;
        }
        if (this.draft.getCloseHandshakeType() == CloseHandshakeType.NONE) {
            closeConnection(1000, true);
            return;
        }
        if (this.draft.getCloseHandshakeType() == CloseHandshakeType.ONEWAY) {
            if (this.role == Role.SERVER) {
                closeConnection(1006, true);
                return;
            } else {
                closeConnection(1000, true);
                return;
            }
        }
        closeConnection(1006, true);
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void close(int i) {
        close(i, "", false);
    }

    public void close(InvalidDataException invalidDataException) {
        close(invalidDataException.getCloseCode(), invalidDataException.getMessage(), false);
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void send(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        send(this.draft.createFrames(str, this.role == Role.CLIENT));
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void send(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        send(this.draft.createFrames(byteBuffer, this.role == Role.CLIENT));
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void send(byte[] bArr) {
        send(ByteBuffer.wrap(bArr));
    }

    private void send(Collection<Framedata> collection) {
        if (!isOpen()) {
            throw new WebsocketNotConnectedException();
        }
        if (collection == null) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList();
        for (Framedata framedata : collection) {
            this.log.trace("send frame: {}", framedata);
            arrayList.add(this.draft.createBinaryFrame(framedata));
        }
        write(arrayList);
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void sendFragmentedFrame(Opcode opcode, ByteBuffer byteBuffer, boolean z) {
        send(this.draft.continuousFrame(opcode, byteBuffer, z));
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void sendFrame(Collection<Framedata> collection) {
        send(collection);
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void sendFrame(Framedata framedata) {
        send(Collections.singletonList(framedata));
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void sendPing() throws NullPointerException {
        PingFrame pingFrameOnPreparePing = this.wsl.onPreparePing(this);
        if (pingFrameOnPreparePing == null) {
            throw new NullPointerException("onPreparePing(WebSocket) returned null. PingFrame to sent can't be null.");
        }
        sendFrame(pingFrameOnPreparePing);
    }

    @Override // com.sq.sywebsocket.WebSocket
    public boolean hasBufferedData() {
        return !this.outQueue.isEmpty();
    }

    public void startHandshake(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException {
        this.handshakerequest = this.draft.postProcessHandshakeRequestAsClient(clientHandshakeBuilder);
        this.resourceDescriptor = clientHandshakeBuilder.getResourceDescriptor();
        try {
            this.wsl.onWebsocketHandshakeSentAsClient(this, this.handshakerequest);
            write(this.draft.createHandshake(this.handshakerequest));
        } catch (InvalidDataException unused) {
            throw new InvalidHandshakeException("Handshake data rejected by client.");
        } catch (RuntimeException e) {
            this.log.error("Exception in startHandshake", (Throwable) e);
            this.wsl.onWebsocketError(this, e);
            throw new InvalidHandshakeException("rejected because of " + e);
        }
    }

    private void write(ByteBuffer byteBuffer) {
        this.log.trace("write({}): {}", Integer.valueOf(byteBuffer.remaining()), byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array()));
        this.outQueue.add(byteBuffer);
        this.wsl.onWriteDemand(this);
    }

    private void write(List<ByteBuffer> list) {
        synchronized (this.synchronizeWriteObject) {
            Iterator<ByteBuffer> it = list.iterator();
            while (it.hasNext()) {
                write(it.next());
            }
        }
    }

    private void open(Handshakedata handshakedata) {
        this.log.trace("open using draft: {}", this.draft);
        this.readyState = ReadyState.OPEN;
        try {
            this.wsl.onWebsocketOpen(this, handshakedata);
        } catch (RuntimeException e) {
            this.wsl.onWebsocketError(this, e);
        }
    }

    @Override // com.sq.sywebsocket.WebSocket
    public boolean isOpen() {
        return this.readyState == ReadyState.OPEN;
    }

    @Override // com.sq.sywebsocket.WebSocket
    public boolean isClosing() {
        return this.readyState == ReadyState.CLOSING;
    }

    @Override // com.sq.sywebsocket.WebSocket
    public boolean isFlushAndClose() {
        return this.flushandclosestate;
    }

    @Override // com.sq.sywebsocket.WebSocket
    public boolean isClosed() {
        return this.readyState == ReadyState.CLOSED;
    }

    @Override // com.sq.sywebsocket.WebSocket
    public ReadyState getReadyState() {
        return this.readyState;
    }

    public void setSelectionKey(SelectionKey selectionKey) {
        this.key = selectionKey;
    }

    public SelectionKey getSelectionKey() {
        return this.key;
    }

    public String toString() {
        return super.toString();
    }

    @Override // com.sq.sywebsocket.WebSocket
    public InetSocketAddress getRemoteSocketAddress() {
        return this.wsl.getRemoteSocketAddress(this);
    }

    @Override // com.sq.sywebsocket.WebSocket
    public InetSocketAddress getLocalSocketAddress() {
        return this.wsl.getLocalSocketAddress(this);
    }

    @Override // com.sq.sywebsocket.WebSocket
    public Draft getDraft() {
        return this.draft;
    }

    @Override // com.sq.sywebsocket.WebSocket
    public void close() {
        close(1000);
    }

    @Override // com.sq.sywebsocket.WebSocket
    public String getResourceDescriptor() {
        return this.resourceDescriptor;
    }

    long getLastPong() {
        return this.lastPong;
    }

    public void updateLastPong() {
        this.lastPong = System.nanoTime();
    }

    public WebSocketListener getWebSocketListener() {
        return this.wsl;
    }

    @Override // com.sq.sywebsocket.WebSocket
    public <T> T getAttachment() {
        return (T) this.attachment;
    }

    @Override // com.sq.sywebsocket.WebSocket
    public boolean hasSSLSupport() {
        return this.channel instanceof ISSLChannel;
    }

    @Override // com.sq.sywebsocket.WebSocket
    public SSLSession getSSLSession() {
        if (!hasSSLSupport()) {
            throw new IllegalArgumentException("This websocket uses ws instead of wss. No SSLSession available.");
        }
        return ((ISSLChannel) this.channel).getSSLEngine().getSession();
    }

    @Override // com.sq.sywebsocket.WebSocket
    public IProtocol getProtocol() {
        Draft draft = this.draft;
        if (draft == null) {
            return null;
        }
        if (!(draft instanceof Draft_6455)) {
            throw new IllegalArgumentException("This draft does not support Sec-WebSocket-Protocol");
        }
        return ((Draft_6455) draft).getProtocol();
    }

    @Override // com.sq.sywebsocket.WebSocket
    public <T> void setAttachment(T t) {
        this.attachment = t;
    }

    public ByteChannel getChannel() {
        return this.channel;
    }

    public void setChannel(ByteChannel byteChannel) {
        this.channel = byteChannel;
    }

    public WebSocketServer.WebSocketWorker getWorkerThread() {
        return this.workerThread;
    }

    public void setWorkerThread(WebSocketServer.WebSocketWorker webSocketWorker) {
        this.workerThread = webSocketWorker;
    }
}
