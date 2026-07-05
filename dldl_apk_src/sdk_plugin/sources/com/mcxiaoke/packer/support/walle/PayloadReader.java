package com.mcxiaoke.packer.support.walle;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class PayloadReader {
    private PayloadReader() {
    }

    public static byte[] readBytes(File file, int i) throws Throwable {
        ByteBuffer block = readBlock(file, i);
        if (block == null) {
            return null;
        }
        return V2Utils.getBytes(block);
    }

    public static ByteBuffer readBlock(File file, int i) throws Throwable {
        Map<Integer, ByteBuffer> allBlocks = readAllBlocks(file);
        if (allBlocks == null) {
            return null;
        }
        return allBlocks.get(Integer.valueOf(i));
    }

    private static Map<Integer, ByteBuffer> readAllBlocks(File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        FileChannel channel = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                channel = randomAccessFile.getChannel();
                Map<Integer, ByteBuffer> mapFindIdValues = ApkUtil.findIdValues(ApkUtil.findApkSigningBlock(channel).getFirst());
                V2Utils.close(channel);
                V2Utils.close(randomAccessFile);
                return mapFindIdValues;
            } catch (Throwable th) {
                th = th;
                V2Utils.close(channel);
                V2Utils.close(randomAccessFile);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
        }
    }
}
