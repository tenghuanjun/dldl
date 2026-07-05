package com.huya.force.audioenginecapture;

import com.huya.force.log.ForceLog;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class AudioBuffer {
    private static final int CAPACITY = 40960;
    private static final String TAG = "AudioBuffer";
    private ByteBuffer mByteBuffer;
    private byte[] mBytes = new byte[8192];

    AudioBuffer() {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(CAPACITY);
        this.mByteBuffer = byteBufferAllocateDirect;
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
    }

    int updateBytes(int i, int i2) {
        int i3 = i2 - i;
        byte[] bArr = this.mBytes;
        if (i3 >= bArr.length) {
            int length = bArr.length;
            ForceLog.error(TAG, String.format(Locale.US, "bytes not enough. length=%d, coming=%d", Integer.valueOf(this.mBytes.length), Integer.valueOf(i3)));
            i3 = length;
        }
        this.mByteBuffer.position(i);
        this.mByteBuffer.limit(i2);
        this.mByteBuffer.get(this.mBytes, 0, i3);
        return i3;
    }

    byte[] getBytes() {
        return this.mBytes;
    }

    ByteBuffer getByteBuffer() {
        return this.mByteBuffer;
    }
}
