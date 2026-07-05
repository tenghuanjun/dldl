package com.sqnetwork.voly.toolbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ByteArrayPool {
    protected static final Comparator<byte[]> BUF_COMPARATOR = new Comparator<byte[]>() { // from class: com.sqnetwork.voly.toolbox.ByteArrayPool.1
        @Override // java.util.Comparator
        public int compare(byte[] lhs, byte[] rhs) {
            return lhs.length - rhs.length;
        }
    };
    private final List<byte[]> mBuffersByLastUse = new ArrayList();
    private final List<byte[]> mBuffersBySize = new ArrayList(64);
    private int mCurrentSize = 0;
    private final int mSizeLimit;

    public ByteArrayPool(int sizeLimit) {
        this.mSizeLimit = sizeLimit;
    }

    public synchronized byte[] getBuf(int len) {
        for (int i = 0; i < this.mBuffersBySize.size(); i++) {
            byte[] bArr = this.mBuffersBySize.get(i);
            if (bArr.length >= len) {
                this.mCurrentSize -= bArr.length;
                this.mBuffersBySize.remove(i);
                this.mBuffersByLastUse.remove(bArr);
                return bArr;
            }
        }
        return new byte[len];
    }

    public synchronized void returnBuf(byte[] buf) {
        if (buf != null) {
            if (buf.length <= this.mSizeLimit) {
                this.mBuffersByLastUse.add(buf);
                int iBinarySearch = Collections.binarySearch(this.mBuffersBySize, buf, BUF_COMPARATOR);
                if (iBinarySearch < 0) {
                    iBinarySearch = (-iBinarySearch) - 1;
                }
                this.mBuffersBySize.add(iBinarySearch, buf);
                this.mCurrentSize += buf.length;
                trim();
            }
        }
    }

    private synchronized void trim() {
        while (this.mCurrentSize > this.mSizeLimit) {
            byte[] bArrRemove = this.mBuffersByLastUse.remove(0);
            this.mBuffersBySize.remove(bArrRemove);
            this.mCurrentSize -= bArrRemove.length;
        }
    }
}
