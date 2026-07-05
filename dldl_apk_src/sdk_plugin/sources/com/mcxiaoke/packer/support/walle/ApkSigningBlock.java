package com.mcxiaoke.packer.support.walle;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class ApkSigningBlock {
    private final List<ApkSigningPayload> payloads = new ArrayList();

    ApkSigningBlock() {
    }

    public final List<ApkSigningPayload> getPayloads() {
        return this.payloads;
    }

    public void addPayload(ApkSigningPayload apkSigningPayload) {
        this.payloads.add(apkSigningPayload);
    }

    public long writeTo(DataOutput dataOutput) throws IOException {
        long length = 24;
        for (int i = 0; i < this.payloads.size(); i++) {
            length += (long) (this.payloads.get(i).getByteBuffer().length + 12);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putLong(length);
        byteBufferAllocate.flip();
        dataOutput.write(byteBufferAllocate.array());
        for (int i2 = 0; i2 < this.payloads.size(); i2++) {
            ApkSigningPayload apkSigningPayload = this.payloads.get(i2);
            byte[] byteBuffer = apkSigningPayload.getByteBuffer();
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(8);
            byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate2.putLong(byteBuffer.length + 4);
            byteBufferAllocate2.flip();
            dataOutput.write(byteBufferAllocate2.array());
            ByteBuffer byteBufferAllocate3 = ByteBuffer.allocate(4);
            byteBufferAllocate3.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate3.putInt(apkSigningPayload.getId());
            byteBufferAllocate3.flip();
            dataOutput.write(byteBufferAllocate3.array());
            dataOutput.write(byteBuffer);
        }
        ByteBuffer byteBufferAllocate4 = ByteBuffer.allocate(8);
        byteBufferAllocate4.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate4.putLong(length);
        byteBufferAllocate4.flip();
        dataOutput.write(byteBufferAllocate4.array());
        ByteBuffer byteBufferAllocate5 = ByteBuffer.allocate(8);
        byteBufferAllocate5.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate5.putLong(2334950737559900225L);
        byteBufferAllocate5.flip();
        dataOutput.write(byteBufferAllocate5.array());
        ByteBuffer byteBufferAllocate6 = ByteBuffer.allocate(8);
        byteBufferAllocate6.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate6.putLong(3617552046287187010L);
        byteBufferAllocate6.flip();
        dataOutput.write(byteBufferAllocate6.array());
        return length;
    }
}
