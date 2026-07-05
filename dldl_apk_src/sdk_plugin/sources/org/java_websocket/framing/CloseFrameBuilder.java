package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.util.Charsetfunctions;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class CloseFrameBuilder extends FramedataImpl1 implements CloseFrame {
    static final ByteBuffer emptybytebuffer = ByteBuffer.allocate(0);
    private int code;
    private String reason;

    public CloseFrameBuilder() {
        super(Framedata.Opcode.CLOSING);
        setFin(true);
    }

    public CloseFrameBuilder(int i) throws InvalidDataException {
        super(Framedata.Opcode.CLOSING);
        setFin(true);
        setCodeAndMessage(i, "");
    }

    public CloseFrameBuilder(int i, String str) throws InvalidDataException {
        super(Framedata.Opcode.CLOSING);
        setFin(true);
        setCodeAndMessage(i, str);
    }

    private void setCodeAndMessage(int i, String str) throws InvalidDataException {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        if (i == 1015) {
            i = 1005;
        } else {
            str2 = str;
        }
        if (i == 1005) {
            if (str2.length() > 0) {
                throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
            }
            return;
        }
        byte[] bArrUtf8Bytes = Charsetfunctions.utf8Bytes(str2);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt(i);
        byteBufferAllocate.position(2);
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(bArrUtf8Bytes.length + 2);
        byteBufferAllocate2.put(byteBufferAllocate);
        byteBufferAllocate2.put(bArrUtf8Bytes);
        byteBufferAllocate2.rewind();
        setPayload(byteBufferAllocate2);
    }

    private void initCloseCode() throws InvalidFrameException {
        this.code = 1005;
        ByteBuffer payloadData = super.getPayloadData();
        payloadData.mark();
        if (payloadData.remaining() >= 2) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            byteBufferAllocate.position(2);
            byteBufferAllocate.putShort(payloadData.getShort());
            byteBufferAllocate.position(0);
            int i = byteBufferAllocate.getInt();
            this.code = i;
            if (i == 1006 || i == 1015 || i == 1005 || i > 4999 || i < 1000 || i == 1004) {
                throw new InvalidFrameException("closecode must not be sent over the wire: " + this.code);
            }
        }
        payloadData.reset();
    }

    @Override // org.java_websocket.framing.CloseFrame
    public int getCloseCode() {
        return this.code;
    }

    private void initMessage() throws InvalidDataException {
        if (this.code == 1005) {
            this.reason = Charsetfunctions.stringUtf8(super.getPayloadData());
            return;
        }
        ByteBuffer payloadData = super.getPayloadData();
        int iPosition = payloadData.position();
        try {
            try {
                payloadData.position(payloadData.position() + 2);
                this.reason = Charsetfunctions.stringUtf8(payloadData);
            } catch (IllegalArgumentException e) {
                throw new InvalidFrameException(e);
            }
        } finally {
            payloadData.position(iPosition);
        }
    }

    @Override // org.java_websocket.framing.CloseFrame
    public String getMessage() {
        return this.reason;
    }

    @Override // org.java_websocket.framing.FramedataImpl1
    public String toString() {
        return super.toString() + "code: " + this.code;
    }

    @Override // org.java_websocket.framing.FramedataImpl1, org.java_websocket.framing.FrameBuilder
    public void setPayload(ByteBuffer byteBuffer) throws InvalidDataException {
        super.setPayload(byteBuffer);
        initCloseCode();
        initMessage();
    }

    @Override // org.java_websocket.framing.FramedataImpl1, org.java_websocket.framing.Framedata
    public ByteBuffer getPayloadData() {
        if (this.code == 1005) {
            return emptybytebuffer;
        }
        return super.getPayloadData();
    }
}
