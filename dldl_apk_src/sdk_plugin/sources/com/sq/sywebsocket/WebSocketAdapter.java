package com.sq.sywebsocket;

import com.sq.sywebsocket.drafts.Draft;
import com.sq.sywebsocket.exceptions.InvalidDataException;
import com.sq.sywebsocket.framing.Framedata;
import com.sq.sywebsocket.framing.PingFrame;
import com.sq.sywebsocket.framing.PongFrame;
import com.sq.sywebsocket.handshake.ClientHandshake;
import com.sq.sywebsocket.handshake.HandshakeImpl1Server;
import com.sq.sywebsocket.handshake.ServerHandshake;
import com.sq.sywebsocket.handshake.ServerHandshakeBuilder;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class WebSocketAdapter implements WebSocketListener {
    private PingFrame pingFrame;

    @Override // com.sq.sywebsocket.WebSocketListener
    public void onWebsocketHandshakeReceivedAsClient(WebSocket webSocket, ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidDataException {
    }

    @Override // com.sq.sywebsocket.WebSocketListener
    public void onWebsocketHandshakeSentAsClient(WebSocket webSocket, ClientHandshake clientHandshake) throws InvalidDataException {
    }

    @Override // com.sq.sywebsocket.WebSocketListener
    public void onWebsocketPong(WebSocket webSocket, Framedata framedata) {
    }

    @Override // com.sq.sywebsocket.WebSocketListener
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket webSocket, Draft draft, ClientHandshake clientHandshake) throws InvalidDataException {
        return new HandshakeImpl1Server();
    }

    @Override // com.sq.sywebsocket.WebSocketListener
    public void onWebsocketPing(WebSocket webSocket, Framedata framedata) {
        webSocket.sendFrame(new PongFrame((PingFrame) framedata));
    }

    @Override // com.sq.sywebsocket.WebSocketListener
    public PingFrame onPreparePing(WebSocket webSocket) {
        if (this.pingFrame == null) {
            this.pingFrame = new PingFrame();
        }
        return this.pingFrame;
    }
}
