package com.sq.sywebsocket.extensions.permessage_deflate;

import com.sq.sywebsocket.enums.Opcode;
import com.sq.sywebsocket.exceptions.InvalidDataException;
import com.sq.sywebsocket.exceptions.InvalidFrameException;
import com.sq.sywebsocket.extensions.CompressionExtension;
import com.sq.sywebsocket.extensions.ExtensionRequestData;
import com.sq.sywebsocket.extensions.IExtension;
import com.sq.sywebsocket.framing.ContinuousFrame;
import com.sq.sywebsocket.framing.DataFrame;
import com.sq.sywebsocket.framing.Framedata;
import com.sq.sywebsocket.framing.FramedataImpl1;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PerMessageDeflateExtension extends CompressionExtension {
    private static final int BUFFER_SIZE = 1024;
    private static final String CLIENT_MAX_WINDOW_BITS = "client_max_window_bits";
    private static final String CLIENT_NO_CONTEXT_TAKEOVER = "client_no_context_takeover";
    private static final String EXTENSION_REGISTERED_NAME = "permessage-deflate";
    private static final String SERVER_MAX_WINDOW_BITS = "server_max_window_bits";
    private static final String SERVER_NO_CONTEXT_TAKEOVER = "server_no_context_takeover";
    private static final byte[] TAIL_BYTES = {0, 0, -1, -1};
    private static final int clientMaxWindowBits = 32768;
    private static final int serverMaxWindowBits = 32768;
    private int threshold = 1024;
    private boolean serverNoContextTakeover = true;
    private boolean clientNoContextTakeover = false;
    private Map<String, String> requestedParameters = new LinkedHashMap();
    private Inflater inflater = new Inflater(true);
    private Deflater deflater = new Deflater(-1, true);

    @Override // com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public String toString() {
        return "PerMessageDeflateExtension";
    }

    public Inflater getInflater() {
        return this.inflater;
    }

    public void setInflater(Inflater inflater) {
        this.inflater = inflater;
    }

    public Deflater getDeflater() {
        return this.deflater;
    }

    public void setDeflater(Deflater deflater) {
        this.deflater = deflater;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public void setThreshold(int i) {
        this.threshold = i;
    }

    public boolean isServerNoContextTakeover() {
        return this.serverNoContextTakeover;
    }

    public void setServerNoContextTakeover(boolean z) {
        this.serverNoContextTakeover = z;
    }

    public boolean isClientNoContextTakeover() {
        return this.clientNoContextTakeover;
    }

    public void setClientNoContextTakeover(boolean z) {
        this.clientNoContextTakeover = z;
    }

    @Override // com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public void decodeFrame(Framedata framedata) throws InvalidDataException {
        if (framedata instanceof DataFrame) {
            if (framedata.getOpcode() == Opcode.CONTINUOUS && framedata.isRSV1()) {
                throw new InvalidDataException(1008, "RSV1 bit can only be set for the first frame.");
            }
            if (framedata.isRSV1()) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    decompress(framedata.getPayloadData().array(), byteArrayOutputStream);
                    if (this.inflater.getRemaining() > 0) {
                        this.inflater = new Inflater(true);
                        decompress(framedata.getPayloadData().array(), byteArrayOutputStream);
                    }
                    if (framedata.isFin()) {
                        decompress(TAIL_BYTES, byteArrayOutputStream);
                        if (this.clientNoContextTakeover) {
                            this.inflater = new Inflater(true);
                        }
                    }
                    if (framedata.isRSV1()) {
                        ((DataFrame) framedata).setRSV1(false);
                    }
                    ((FramedataImpl1) framedata).setPayload(ByteBuffer.wrap(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size()));
                } catch (DataFormatException e) {
                    throw new InvalidDataException(1008, e.getMessage());
                }
            }
        }
    }

    private void decompress(byte[] bArr, ByteArrayOutputStream byteArrayOutputStream) throws DataFormatException {
        this.inflater.setInput(bArr);
        byte[] bArr2 = new byte[1024];
        while (true) {
            int iInflate = this.inflater.inflate(bArr2);
            if (iInflate <= 0) {
                return;
            } else {
                byteArrayOutputStream.write(bArr2, 0, iInflate);
            }
        }
    }

    @Override // com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public void encodeFrame(Framedata framedata) {
        if (framedata instanceof DataFrame) {
            byte[] bArrArray = framedata.getPayloadData().array();
            if (bArrArray.length < this.threshold) {
                return;
            }
            if (!(framedata instanceof ContinuousFrame)) {
                ((DataFrame) framedata).setRSV1(true);
            }
            this.deflater.setInput(bArrArray);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int iDeflate = this.deflater.deflate(bArr, 0, 1024, 2);
                if (iDeflate <= 0) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, iDeflate);
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            int length = byteArray.length;
            if (framedata.isFin()) {
                if (endsWithTail(byteArray)) {
                    length -= TAIL_BYTES.length;
                }
                if (this.serverNoContextTakeover) {
                    this.deflater.end();
                    this.deflater = new Deflater(-1, true);
                }
            }
            ((FramedataImpl1) framedata).setPayload(ByteBuffer.wrap(byteArray, 0, length));
        }
    }

    private static boolean endsWithTail(byte[] bArr) {
        if (bArr.length < 4) {
            return false;
        }
        int length = bArr.length;
        int i = 0;
        while (true) {
            byte[] bArr2 = TAIL_BYTES;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr2[i] != bArr[(length - bArr2.length) + i]) {
                return false;
            }
            i++;
        }
    }

    @Override // com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public boolean acceptProvidedExtensionAsServer(String str) {
        for (String str2 : str.split(",")) {
            ExtensionRequestData extensionRequest = ExtensionRequestData.parseExtensionRequest(str2);
            if (EXTENSION_REGISTERED_NAME.equalsIgnoreCase(extensionRequest.getExtensionName())) {
                this.requestedParameters.putAll(extensionRequest.getExtensionParameters());
                if (this.requestedParameters.containsKey(CLIENT_NO_CONTEXT_TAKEOVER)) {
                    this.clientNoContextTakeover = true;
                }
                return true;
            }
        }
        return false;
    }

    @Override // com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public boolean acceptProvidedExtensionAsClient(String str) {
        for (String str2 : str.split(",")) {
            ExtensionRequestData extensionRequest = ExtensionRequestData.parseExtensionRequest(str2);
            if (EXTENSION_REGISTERED_NAME.equalsIgnoreCase(extensionRequest.getExtensionName())) {
                extensionRequest.getExtensionParameters();
                return true;
            }
        }
        return false;
    }

    @Override // com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public String getProvidedExtensionAsClient() {
        this.requestedParameters.put(CLIENT_NO_CONTEXT_TAKEOVER, "");
        this.requestedParameters.put(SERVER_NO_CONTEXT_TAKEOVER, "");
        return "permessage-deflate; server_no_context_takeover; client_no_context_takeover";
    }

    @Override // com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public String getProvidedExtensionAsServer() {
        StringBuilder sb = new StringBuilder();
        sb.append("permessage-deflate; server_no_context_takeover");
        sb.append(this.clientNoContextTakeover ? "; client_no_context_takeover" : "");
        return sb.toString();
    }

    @Override // com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public IExtension copyInstance() {
        return new PerMessageDeflateExtension();
    }

    @Override // com.sq.sywebsocket.extensions.CompressionExtension, com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public void isFrameValid(Framedata framedata) throws InvalidDataException {
        if ((framedata instanceof ContinuousFrame) && (framedata.isRSV1() || framedata.isRSV2() || framedata.isRSV3())) {
            throw new InvalidFrameException("bad rsv RSV1: " + framedata.isRSV1() + " RSV2: " + framedata.isRSV2() + " RSV3: " + framedata.isRSV3());
        }
        super.isFrameValid(framedata);
    }
}
