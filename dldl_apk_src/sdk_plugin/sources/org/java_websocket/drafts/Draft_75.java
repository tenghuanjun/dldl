package org.java_websocket.drafts;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.NotSendableException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class Draft_75 extends Draft {
    public static final byte CR = 13;
    public static final byte END_OF_FRAME = -1;
    public static final byte LF = 10;
    public static final byte START_OF_FRAME = 0;
    protected ByteBuffer currentFrame;
    protected boolean readingState = false;
    private boolean inframe = false;
    protected List<Framedata> readyframes = new LinkedList();
    private final Random reuseableRandom = new Random();

    @Override // org.java_websocket.drafts.Draft
    public Draft.HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) {
        return (clientHandshake.getFieldValue("WebSocket-Origin").equals(serverHandshake.getFieldValue("Origin")) && basicAccept(serverHandshake)) ? Draft.HandshakeState.MATCHED : Draft.HandshakeState.NOT_MATCHED;
    }

    @Override // org.java_websocket.drafts.Draft
    public Draft.HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) {
        if (clientHandshake.hasFieldValue("Origin") && basicAccept(clientHandshake)) {
            return Draft.HandshakeState.MATCHED;
        }
        return Draft.HandshakeState.NOT_MATCHED;
    }

    @Override // org.java_websocket.drafts.Draft
    public ByteBuffer createBinaryFrame(Framedata framedata) {
        if (framedata.getOpcode() != Framedata.Opcode.TEXT) {
            throw new RuntimeException("only text frames supported");
        }
        ByteBuffer payloadData = framedata.getPayloadData();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(payloadData.remaining() + 2);
        byteBufferAllocate.put((byte) 0);
        payloadData.mark();
        byteBufferAllocate.put(payloadData);
        payloadData.reset();
        byteBufferAllocate.put((byte) -1);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // org.java_websocket.drafts.Draft
    public List<Framedata> createFrames(ByteBuffer byteBuffer, boolean z) {
        throw new RuntimeException("not yet implemented");
    }

    @Override // org.java_websocket.drafts.Draft
    public List<Framedata> createFrames(String str, boolean z) {
        FramedataImpl1 framedataImpl1 = new FramedataImpl1();
        try {
            framedataImpl1.setPayload(ByteBuffer.wrap(Charsetfunctions.utf8Bytes(str)));
            framedataImpl1.setFin(true);
            framedataImpl1.setOptcode(Framedata.Opcode.TEXT);
            framedataImpl1.setTransferemasked(z);
            return Collections.singletonList(framedataImpl1);
        } catch (InvalidDataException e) {
            throw new NotSendableException(e);
        }
    }

    @Override // org.java_websocket.drafts.Draft
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException {
        clientHandshakeBuilder.put("Upgrade", "WebSocket");
        clientHandshakeBuilder.put("Connection", "Upgrade");
        if (!clientHandshakeBuilder.hasFieldValue("Origin")) {
            clientHandshakeBuilder.put("Origin", "random" + this.reuseableRandom.nextInt());
        }
        return clientHandshakeBuilder;
    }

    @Override // org.java_websocket.drafts.Draft
    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException {
        serverHandshakeBuilder.setHttpStatusMessage("Web Socket Protocol Handshake");
        serverHandshakeBuilder.put("Upgrade", "WebSocket");
        serverHandshakeBuilder.put("Connection", clientHandshake.getFieldValue("Connection"));
        serverHandshakeBuilder.put("WebSocket-Origin", clientHandshake.getFieldValue("Origin"));
        serverHandshakeBuilder.put("WebSocket-Location", "ws://" + clientHandshake.getFieldValue("Host") + clientHandshake.getResourceDescriptor());
        return serverHandshakeBuilder;
    }

    protected List<Framedata> translateRegularFrame(ByteBuffer byteBuffer) throws InvalidDataException {
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            if (b == 0) {
                if (this.readingState) {
                    return null;
                }
                this.readingState = true;
            } else if (b == -1) {
                if (!this.readingState) {
                    return null;
                }
                ByteBuffer byteBuffer2 = this.currentFrame;
                if (byteBuffer2 != null) {
                    byteBuffer2.flip();
                    FramedataImpl1 framedataImpl1 = new FramedataImpl1();
                    framedataImpl1.setPayload(this.currentFrame);
                    framedataImpl1.setFin(true);
                    framedataImpl1.setOptcode(this.inframe ? Framedata.Opcode.CONTINUOUS : Framedata.Opcode.TEXT);
                    this.readyframes.add(framedataImpl1);
                    this.currentFrame = null;
                    byteBuffer.mark();
                }
                this.readingState = false;
                this.inframe = false;
            } else {
                if (!this.readingState) {
                    return null;
                }
                ByteBuffer byteBuffer3 = this.currentFrame;
                if (byteBuffer3 == null) {
                    this.currentFrame = createBuffer();
                } else if (!byteBuffer3.hasRemaining()) {
                    this.currentFrame = increaseBuffer(this.currentFrame);
                }
                this.currentFrame.put(b);
            }
        }
        if (this.readingState) {
            FramedataImpl1 framedataImpl12 = new FramedataImpl1();
            this.currentFrame.flip();
            framedataImpl12.setPayload(this.currentFrame);
            framedataImpl12.setFin(false);
            framedataImpl12.setOptcode(this.inframe ? Framedata.Opcode.CONTINUOUS : Framedata.Opcode.TEXT);
            this.inframe = true;
            this.readyframes.add(framedataImpl12);
        }
        List<Framedata> list = this.readyframes;
        this.readyframes = new LinkedList();
        this.currentFrame = null;
        return list;
    }

    @Override // org.java_websocket.drafts.Draft
    public List<Framedata> translateFrame(ByteBuffer byteBuffer) throws InvalidDataException {
        List<Framedata> listTranslateRegularFrame = translateRegularFrame(byteBuffer);
        if (listTranslateRegularFrame != null) {
            return listTranslateRegularFrame;
        }
        throw new InvalidDataException(1002);
    }

    @Override // org.java_websocket.drafts.Draft
    public void reset() {
        this.readingState = false;
        this.currentFrame = null;
    }

    @Override // org.java_websocket.drafts.Draft
    public Draft.CloseHandshakeType getCloseHandshakeType() {
        return Draft.CloseHandshakeType.NONE;
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(INITIAL_FAMESIZE);
    }

    public ByteBuffer increaseBuffer(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.capacity() * 2);
        byteBufferAllocate.put(byteBuffer);
        return byteBufferAllocate;
    }

    @Override // org.java_websocket.drafts.Draft
    public Draft copyInstance() {
        return new Draft_75();
    }
}
