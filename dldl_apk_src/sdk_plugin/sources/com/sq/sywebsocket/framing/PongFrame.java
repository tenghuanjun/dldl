package com.sq.sywebsocket.framing;

import com.sq.sywebsocket.enums.Opcode;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PongFrame extends ControlFrame {
    public PongFrame() {
        super(Opcode.PONG);
    }

    public PongFrame(PingFrame pingFrame) {
        super(Opcode.PONG);
        setPayload(pingFrame.getPayloadData());
    }
}
