package com.mcxiaoke.packer.support.walle;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class PayloadWriter {

    interface ApkSigningBlockHandler {
        ApkSigningBlock handle(Map<Integer, ByteBuffer> map);
    }

    private PayloadWriter() {
    }

    public static void writeBlock(File file, int i, byte[] bArr) throws Throwable {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(bArr, 0, bArr.length);
        byteBufferAllocate.flip();
        writeBlock(file, i, byteBufferAllocate);
    }

    public static void writeBlock(File file, int i, ByteBuffer byteBuffer) throws Throwable {
        HashMap map = new HashMap();
        map.put(Integer.valueOf(i), byteBuffer);
        writeValues(file, map);
    }

    private static void writeValues(File file, final Map<Integer, ByteBuffer> map) throws Throwable {
        writeApkSigningBlock(file, new ApkSigningBlockHandler() { // from class: com.mcxiaoke.packer.support.walle.PayloadWriter.1
            @Override // com.mcxiaoke.packer.support.walle.PayloadWriter.ApkSigningBlockHandler
            public ApkSigningBlock handle(Map<Integer, ByteBuffer> map2) {
                Map map3 = map;
                if (map3 != null && !map3.isEmpty()) {
                    map2.putAll(map);
                }
                ApkSigningBlock apkSigningBlock = new ApkSigningBlock();
                for (Map.Entry<Integer, ByteBuffer> entry : map2.entrySet()) {
                    apkSigningBlock.addPayload(new ApkSigningPayload(entry.getKey().intValue(), entry.getValue()));
                }
                return apkSigningBlock;
            }
        });
    }

    static void writeApkSigningBlock(File file, ApkSigningBlockHandler apkSigningBlockHandler) throws Throwable {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                FileChannel channel = randomAccessFile.getChannel();
                long jFindZipCommentLength = ApkUtil.findZipCommentLength(channel);
                long jFindCentralDirStartOffset = ApkUtil.findCentralDirStartOffset(channel, jFindZipCommentLength);
                Pair<ByteBuffer, Long> pairFindApkSigningBlock = ApkUtil.findApkSigningBlock(channel, jFindCentralDirStartOffset);
                ByteBuffer first = pairFindApkSigningBlock.getFirst();
                long jLongValue = pairFindApkSigningBlock.getSecond().longValue();
                if (jFindCentralDirStartOffset == 0 || jLongValue == 0) {
                    throw new IOException("No APK Signature Scheme v2 block in APK Signing Block");
                }
                Map<Integer, ByteBuffer> mapFindIdValues = ApkUtil.findIdValues(first);
                if (mapFindIdValues.get(1896449818) == null) {
                    throw new IOException("No APK Signature Scheme v2 block in APK Signing Block");
                }
                ApkSigningBlock apkSigningBlockHandle = apkSigningBlockHandler.handle(mapFindIdValues);
                randomAccessFile.seek(jFindCentralDirStartOffset);
                byte[] bArr = new byte[(int) (channel.size() - jFindCentralDirStartOffset)];
                randomAccessFile.read(bArr);
                channel.position(jLongValue);
                long jWriteTo = apkSigningBlockHandle.writeTo(randomAccessFile);
                randomAccessFile.write(bArr);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                randomAccessFile.seek((channel.size() - jFindZipCommentLength) - 6);
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
                byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
                byteBufferAllocate.putInt((int) (((jWriteTo + jFindCentralDirStartOffset) + 8) - (jFindCentralDirStartOffset - jLongValue)));
                byteBufferAllocate.flip();
                randomAccessFile.write(byteBufferAllocate.array());
                V2Utils.close(channel);
                V2Utils.close(randomAccessFile);
            } catch (Throwable th) {
                th = th;
                V2Utils.close(null);
                V2Utils.close(randomAccessFile);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
        }
    }
}
