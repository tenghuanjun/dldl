package com.duowan.live.common.packer.common;

import com.duowan.live.common.packer.walle.Support;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PackerCommon {
    public static final String BLOCK_MAGIC = "Packer Ng Sig V2";
    public static final int CHANNEL_BLOCK_ID = 2054712097;
    public static final String CHANNEL_KEY = "CHANNEL";
    public static final String SEP_KV = "∘";
    public static final String SEP_LINE = "∙";
    public static final String UTF8 = "UTF-8";

    public static String readChannel(File file) throws IOException {
        return readValue(file, "CHANNEL", 2054712097);
    }

    public static void writeChannel(File file, String str) throws IOException {
        writeValue(file, "CHANNEL", str, 2054712097);
    }

    static String readValue(File file, String str, int i) throws IOException {
        Map<String, String> values = readValues(file, i);
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.get(str);
    }

    static void writeValue(File file, String str, String str2, int i) throws IOException {
        HashMap map = new HashMap();
        map.put(str, str2);
        writeValues(file, map, i);
    }

    public static Map<String, String> readValues(File file, int i) throws IOException {
        return mapFromString(readString(file, i));
    }

    public static String readString(File file, int i) throws IOException {
        byte[] bytes = readBytes(file, i);
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return new String(bytes, "UTF-8");
    }

    public static byte[] readBytes(File file, int i) throws IOException {
        return readPayloadImpl(file, i);
    }

    public static void writeValues(File file, Map<String, String> map, int i) throws IOException {
        if (map == null || map.isEmpty()) {
            return;
        }
        HashMap map2 = new HashMap();
        Map<String, String> values = readValues(file, i);
        if (values != null) {
            map2.putAll(values);
        }
        map2.putAll(map);
        writeString(file, mapToString(map2), i);
    }

    public static void writeString(File file, String str, int i) throws IOException {
        writeBytes(file, str.getBytes("UTF-8"), i);
    }

    public static void writeBytes(File file, byte[] bArr, int i) throws IOException {
        writePayloadImpl(file, bArr, i);
    }

    static void writePayloadImpl(File file, byte[] bArr, int i) throws IOException {
        Support.writeBlock(file, i, wrapPayload(bArr));
    }

    static byte[] readPayloadImpl(File file, int i) throws IOException {
        int i2;
        ByteBuffer block = Support.readBlock(file, i);
        if (block == null) {
            return null;
        }
        byte[] bytes = "Packer Ng Sig V2".getBytes("UTF-8");
        byte[] bArr = new byte[bytes.length];
        block.get(bArr);
        if (Arrays.equals(bytes, bArr) && (i2 = block.getInt()) > 0) {
            byte[] bArr2 = new byte[i2];
            block.get(bArr2);
            if (block.getInt() == i2) {
                return bArr2;
            }
        }
        return null;
    }

    static ByteBuffer wrapPayload(byte[] bArr) throws UnsupportedEncodingException {
        byte[] bytes = "Packer Ng Sig V2".getBytes("UTF-8");
        int length = bytes.length;
        int length2 = bArr.length;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(((length + 4) * 2) + length2);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(bytes);
        byteBufferAllocate.putInt(length2);
        byteBufferAllocate.put(bArr);
        byteBufferAllocate.putInt(length2);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    public static String mapToString(Map<String, String> map) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("∘");
            sb.append(entry.getValue());
            sb.append("∙");
        }
        return sb.toString();
    }

    public static Map<String, String> mapFromString(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        HashMap map = new HashMap();
        for (String str2 : str.split("∙")) {
            String[] strArrSplit = str2.split("∘");
            if (strArrSplit.length == 2) {
                map.put(strArrSplit[0], strArrSplit[1]);
            }
        }
        return map;
    }
}
