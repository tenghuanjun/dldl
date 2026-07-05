package com.sq.sywebsocket.framing;

import com.sq.sywebsocket.enums.Opcode;
import com.sq.sywebsocket.exceptions.InvalidDataException;
import com.sq.sywebsocket.util.Charsetfunctions;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TextFrame extends DataFrame {
    public TextFrame() {
        super(Opcode.TEXT);
    }

    @Override // com.sq.sywebsocket.framing.DataFrame, com.sq.sywebsocket.framing.FramedataImpl1
    public void isValid() throws InvalidDataException {
        super.isValid();
        if (!Charsetfunctions.isValidUTF8(getPayloadData())) {
            throw new InvalidDataException(1007, "Received text is no valid utf8 string!");
        }
    }
}
