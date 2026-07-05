package com.sq.libwebsocket.request;

import com.sq.sywebsocket.client.WebSocketClient;
import com.sq.sywebsocket.framing.PingFrame;
import com.sq.sywebsocket.framing.PongFrame;
import java.util.ArrayDeque;
import java.util.Queue;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PongRequest implements Request<PingFrame> {
    private static Queue<PongFrame> PONG_POOL = new ArrayDeque(7);
    private PingFrame pingFrame;

    PongRequest() {
    }

    @Override // com.sq.libwebsocket.request.Request
    public void setRequestData(PingFrame pingFrame) {
        this.pingFrame = pingFrame;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sq.libwebsocket.request.Request
    public PingFrame getRequestData() {
        return this.pingFrame;
    }

    @Override // com.sq.libwebsocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        PongFrame pongFrame = getPongFrame();
        PingFrame pingFrame = this.pingFrame;
        if (pingFrame != null) {
            pongFrame.setPayload(pingFrame.getPayloadData());
            this.pingFrame = null;
        } else {
            pongFrame.setPayload(null);
        }
        webSocketClient.sendFrame(pongFrame);
        offerPongFrame(pongFrame);
    }

    @Override // com.sq.libwebsocket.request.Request
    public void release() {
        RequestFactory.releasePongRequest(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        PingFrame pingFrame = this.pingFrame;
        objArr[1] = pingFrame == null ? AbstractJsonLexerKt.NULL : pingFrame.toString();
        return String.format("[@PongRequest%s,PingFrame:%s]", objArr);
    }

    private PongFrame getPongFrame() {
        PongFrame pongFramePoll = PONG_POOL.poll();
        return pongFramePoll == null ? new PongFrame() : pongFramePoll;
    }

    private void offerPongFrame(PongFrame pongFrame) {
        this.pingFrame = null;
        PONG_POOL.offer(pongFrame);
    }
}
