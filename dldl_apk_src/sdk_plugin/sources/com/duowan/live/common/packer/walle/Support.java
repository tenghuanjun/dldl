package com.duowan.live.common.packer.walle;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Support {
    public static ByteBuffer readBlock(File file, int i) throws IOException {
        return PayloadReader.readBlock(file, i);
    }

    public static byte[] readBytes(File file, int i) throws IOException {
        return PayloadReader.readBytes(file, i);
    }

    public static void writeBlock(File file, int i, ByteBuffer byteBuffer) throws IOException {
        PayloadWriter.writeBlock(file, i, byteBuffer);
    }

    public static void writeBlock(File file, int i, byte[] bArr) throws IOException {
        PayloadWriter.writeBlock(file, i, bArr);
    }
}
