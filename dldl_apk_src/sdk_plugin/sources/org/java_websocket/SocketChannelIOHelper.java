package org.java_websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SocketChannelIOHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static boolean read(ByteBuffer byteBuffer, WebSocketImpl webSocketImpl, ByteChannel byteChannel) throws IOException {
        byteBuffer.clear();
        int i = byteChannel.read(byteBuffer);
        byteBuffer.flip();
        if (i != -1) {
            return i != 0;
        }
        webSocketImpl.eot();
        return false;
    }

    public static boolean readMore(ByteBuffer byteBuffer, WebSocketImpl webSocketImpl, WrappedByteChannel wrappedByteChannel) throws IOException {
        byteBuffer.clear();
        int more = wrappedByteChannel.readMore(byteBuffer);
        byteBuffer.flip();
        if (more == -1) {
            webSocketImpl.eot();
            return false;
        }
        return wrappedByteChannel.isNeedRead();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean batch(org.java_websocket.WebSocketImpl r3, java.nio.channels.ByteChannel r4) throws java.io.IOException {
        /*
            java.util.concurrent.BlockingQueue<java.nio.ByteBuffer> r0 = r3.outQueue
            java.lang.Object r0 = r0.peek()
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0
            r1 = 0
            if (r0 != 0) goto L1c
            boolean r0 = r4 instanceof org.java_websocket.WrappedByteChannel
            if (r0 == 0) goto L35
            r0 = r4
            org.java_websocket.WrappedByteChannel r0 = (org.java_websocket.WrappedByteChannel) r0
            boolean r2 = r0.isNeedWrite()
            if (r2 == 0) goto L36
            r0.writeMore()
            goto L36
        L1c:
            r4.write(r0)
            int r0 = r0.remaining()
            if (r0 <= 0) goto L26
            return r1
        L26:
            java.util.concurrent.BlockingQueue<java.nio.ByteBuffer> r0 = r3.outQueue
            r0.poll()
            java.util.concurrent.BlockingQueue<java.nio.ByteBuffer> r0 = r3.outQueue
            java.lang.Object r0 = r0.peek()
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0
            if (r0 != 0) goto L1c
        L35:
            r0 = 0
        L36:
            java.util.concurrent.BlockingQueue<java.nio.ByteBuffer> r2 = r3.outQueue
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L4d
            boolean r2 = r3.isFlushAndClose()
            if (r2 == 0) goto L4d
            monitor-enter(r3)
            r3.closeConnection()     // Catch: java.lang.Throwable -> L4a
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L4a
            goto L4d
        L4a:
            r4 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L4a
            throw r4
        L4d:
            r3 = 1
            if (r0 == 0) goto L58
            org.java_websocket.WrappedByteChannel r4 = (org.java_websocket.WrappedByteChannel) r4
            boolean r4 = r4.isNeedWrite()
            if (r4 != 0) goto L59
        L58:
            r1 = 1
        L59:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.SocketChannelIOHelper.batch(org.java_websocket.WebSocketImpl, java.nio.channels.ByteChannel):boolean");
    }

    public static void writeBlocking(WebSocketImpl webSocketImpl, ByteChannel byteChannel) throws InterruptedException, IOException {
        ByteBuffer byteBufferTake = webSocketImpl.outQueue.take();
        while (byteBufferTake.hasRemaining()) {
            byteChannel.write(byteBufferTake);
        }
    }
}
