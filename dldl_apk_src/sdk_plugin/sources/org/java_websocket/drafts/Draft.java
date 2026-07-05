package org.java_websocket.drafts;

import com.sq.websocket_engine.WebSocketCenter;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public abstract class Draft {
    public static final byte[] FLASH_POLICY_REQUEST = Charsetfunctions.utf8Bytes("<policy-file-request/>\u0000");
    public static int INITIAL_FAMESIZE = 64;
    public static int MAX_FAME_SIZE = 1000;
    protected WebSocket.Role role = null;

    public enum CloseHandshakeType {
        NONE,
        ONEWAY,
        TWOWAY
    }

    public enum HandshakeState {
        MATCHED,
        NOT_MATCHED
    }

    public abstract HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidHandshakeException;

    public abstract HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) throws InvalidHandshakeException;

    public abstract Draft copyInstance();

    public abstract ByteBuffer createBinaryFrame(Framedata framedata);

    public abstract List<Framedata> createFrames(String str, boolean z);

    public abstract List<Framedata> createFrames(ByteBuffer byteBuffer, boolean z);

    public abstract CloseHandshakeType getCloseHandshakeType();

    public abstract ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException;

    public abstract HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException;

    public abstract void reset();

    public abstract List<Framedata> translateFrame(ByteBuffer byteBuffer) throws InvalidDataException;

    public static ByteBuffer readLine(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining());
        byte b = 48;
        while (byteBuffer.hasRemaining()) {
            byte b2 = byteBuffer.get();
            byteBufferAllocate.put(b2);
            if (b == 13 && b2 == 10) {
                byteBufferAllocate.limit(byteBufferAllocate.position() - 2);
                byteBufferAllocate.position(0);
                return byteBufferAllocate;
            }
            b = b2;
        }
        byteBuffer.position(byteBuffer.position() - byteBufferAllocate.position());
        return null;
    }

    public static String readStringLine(ByteBuffer byteBuffer) {
        ByteBuffer line = readLine(byteBuffer);
        if (line == null) {
            return null;
        }
        return Charsetfunctions.stringAscii(line.array(), 0, line.limit());
    }

    public static HandshakeBuilder translateHandshakeHttp(ByteBuffer byteBuffer, WebSocket.Role role) throws IncompleteHandshakeException, InvalidHandshakeException {
        HandshakeBuilder handshakeBuilder;
        String stringLine = readStringLine(byteBuffer);
        if (stringLine == null) {
            throw new IncompleteHandshakeException(byteBuffer.capacity() + 128);
        }
        String[] strArrSplit = stringLine.split(" ", 3);
        if (strArrSplit.length != 3) {
            throw new InvalidHandshakeException();
        }
        if (role == WebSocket.Role.CLIENT) {
            HandshakeImpl1Server handshakeImpl1Server = new HandshakeImpl1Server();
            HandshakeImpl1Server handshakeImpl1Server2 = handshakeImpl1Server;
            handshakeImpl1Server2.setHttpStatus(Short.parseShort(strArrSplit[1]));
            handshakeImpl1Server2.setHttpStatusMessage(strArrSplit[2]);
            handshakeBuilder = handshakeImpl1Server;
        } else {
            HandshakeImpl1Client handshakeImpl1Client = new HandshakeImpl1Client();
            handshakeImpl1Client.setResourceDescriptor(strArrSplit[1]);
            handshakeBuilder = handshakeImpl1Client;
        }
        String stringLine2 = readStringLine(byteBuffer);
        while (stringLine2 != null && stringLine2.length() > 0) {
            String[] strArrSplit2 = stringLine2.split(":", 2);
            if (strArrSplit2.length != 2) {
                throw new InvalidHandshakeException("not an http header");
            }
            handshakeBuilder.put(strArrSplit2[0], strArrSplit2[1].replaceFirst("^ +", ""));
            stringLine2 = readStringLine(byteBuffer);
        }
        if (stringLine2 != null) {
            return handshakeBuilder;
        }
        throw new IncompleteHandshakeException();
    }

    protected boolean basicAccept(Handshakedata handshakedata) {
        return handshakedata.getFieldValue("Upgrade").equalsIgnoreCase(WebSocketCenter.KEY_WEBSOCKET) && handshakedata.getFieldValue("Connection").toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, WebSocket.Role role) {
        return createHandshake(handshakedata, role, true);
    }

    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, WebSocket.Role role, boolean z) {
        StringBuilder sb = new StringBuilder(100);
        if (handshakedata instanceof ClientHandshake) {
            sb.append("GET ");
            sb.append(((ClientHandshake) handshakedata).getResourceDescriptor());
            sb.append(" HTTP/1.1");
        } else if (handshakedata instanceof ServerHandshake) {
            sb.append("HTTP/1.1 101 " + ((ServerHandshake) handshakedata).getHttpStatusMessage());
        } else {
            throw new RuntimeException("unknow role");
        }
        sb.append("\r\n");
        Iterator<String> itIterateHttpFields = handshakedata.iterateHttpFields();
        while (itIterateHttpFields.hasNext()) {
            String next = itIterateHttpFields.next();
            String fieldValue = handshakedata.getFieldValue(next);
            sb.append(next);
            sb.append(": ");
            sb.append(fieldValue);
            sb.append("\r\n");
        }
        sb.append("\r\n");
        byte[] bArrAsciiBytes = Charsetfunctions.asciiBytes(sb.toString());
        byte[] content = z ? handshakedata.getContent() : null;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((content == null ? 0 : content.length) + bArrAsciiBytes.length);
        byteBufferAllocate.put(bArrAsciiBytes);
        if (content != null) {
            byteBufferAllocate.put(content);
        }
        byteBufferAllocate.flip();
        return Collections.singletonList(byteBufferAllocate);
    }

    public Handshakedata translateHandshake(ByteBuffer byteBuffer) throws InvalidHandshakeException {
        return translateHandshakeHttp(byteBuffer, this.role);
    }

    public int checkAlloc(int i) throws InvalidDataException {
        if (i >= 0) {
            return i;
        }
        throw new InvalidDataException(1002, "Negative count");
    }

    public void setParseMode(WebSocket.Role role) {
        this.role = role;
    }
}
