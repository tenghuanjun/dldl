package com.sq.sywebsocket.framing;

import com.sq.sywebsocket.enums.Opcode;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PingFrame extends ControlFrame {
    public PingFrame() {
        super(Opcode.PING);
    }
}
