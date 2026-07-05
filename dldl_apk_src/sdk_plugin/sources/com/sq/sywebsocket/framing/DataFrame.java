package com.sq.sywebsocket.framing;

import com.sq.sywebsocket.enums.Opcode;
import com.sq.sywebsocket.exceptions.InvalidDataException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class DataFrame extends FramedataImpl1 {
    @Override // com.sq.sywebsocket.framing.FramedataImpl1
    public void isValid() throws InvalidDataException {
    }

    public DataFrame(Opcode opcode) {
        super(opcode);
    }
}
