package com.huya.mtp.multithreaddownload;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BufferedRandomAccessFile extends RandomAccessFile {
    static final long BuffMask_ = -65536;
    public static final int BuffSz_ = 65536;
    static final int LogBuffSz_ = 16;
    private static final String TAG = "BufferRandomAccessFile";
    private byte[] buff_;
    private long curr_;
    private boolean dirty_;
    private long diskPos_;
    private long hi_;
    private boolean hitEOF_;
    private long lo_;
    private boolean mLastWriteDisk;
    private long mLastWriteDiskLength;
    private long maxHi_;
    private String path_;
    private boolean syncNeeded_;

    public BufferedRandomAccessFile(File file, String str) throws IOException {
        this(file, str, 0);
    }

    public BufferedRandomAccessFile(File file, String str, int i) throws IOException {
        super(file, str);
        this.mLastWriteDiskLength = 0L;
        this.path_ = file.getAbsolutePath();
        init(i);
    }

    public BufferedRandomAccessFile(String str, String str2) throws IOException {
        this(str, str2, 0);
    }

    public BufferedRandomAccessFile(String str, String str2, int i) throws FileNotFoundException {
        super(str, str2);
        this.mLastWriteDiskLength = 0L;
        this.path_ = str;
        init(i);
    }

    private void init(int i) {
        this.dirty_ = false;
        this.hi_ = 0L;
        this.curr_ = 0L;
        this.lo_ = 0L;
        this.buff_ = i > 65536 ? new byte[i] : new byte[65536];
        this.maxHi_ = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        this.hitEOF_ = false;
        this.diskPos_ = 0L;
    }

    public boolean isLastWriteDisk() {
        return this.mLastWriteDisk;
    }

    public long getLastWriteDiskLength() {
        return this.mLastWriteDiskLength;
    }

    public String getPath() {
        return this.path_;
    }

    public void sync() throws IOException {
        if (this.syncNeeded_) {
            flush();
            getChannel().force(true);
            this.syncNeeded_ = false;
        }
    }

    @Override // java.io.RandomAccessFile, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        flush();
        this.buff_ = null;
        super.close();
    }

    public void flush() throws IOException {
        Log.i(TAG, "flush() method invoke");
        this.mLastWriteDisk = false;
        flushBuffer();
    }

    private void flushBuffer() throws IOException {
        if (this.dirty_) {
            long j = this.diskPos_;
            long j2 = this.lo_;
            if (j != j2) {
                super.seek(j2);
            }
            int i = (int) (this.curr_ - this.lo_);
            super.write(this.buff_, 0, i);
            this.diskPos_ = this.curr_;
            this.dirty_ = false;
            this.mLastWriteDisk = true;
            this.mLastWriteDiskLength = i;
        }
    }

    private int fillBuffer() throws IOException {
        int length = this.buff_.length;
        int i = 0;
        while (length > 0) {
            int i2 = super.read(this.buff_, i, length);
            if (i2 < 0) {
                break;
            }
            i += i2;
            length -= i2;
        }
        if (i < 0) {
            boolean z = i < this.buff_.length;
            this.hitEOF_ = z;
            if (z) {
                byte[] bArr = this.buff_;
                Arrays.fill(bArr, i, bArr.length, (byte) -1);
            }
        }
        this.diskPos_ += (long) i;
        return i;
    }

    @Override // java.io.RandomAccessFile
    public void seek(long j) throws IOException {
        if (j >= this.hi_ || j < this.lo_) {
            flushBuffer();
            this.lo_ = j;
            this.maxHi_ = ((long) this.buff_.length) + j;
            if (this.diskPos_ != j) {
                super.seek(j);
                this.diskPos_ = this.lo_;
            }
            this.hi_ = this.lo_ + ((long) fillBuffer());
        } else if (j < this.curr_) {
            flushBuffer();
        }
        this.curr_ = j;
    }

    @Override // java.io.RandomAccessFile
    public long getFilePointer() {
        return this.curr_;
    }

    @Override // java.io.RandomAccessFile
    public long length() throws IOException {
        return Math.max(this.curr_, super.length());
    }

    @Override // java.io.RandomAccessFile
    public int read() throws IOException {
        long j = this.curr_;
        if (j >= this.hi_) {
            if (this.hitEOF_) {
                return -1;
            }
            seek(j);
            if (this.curr_ == this.hi_) {
                return -1;
            }
        }
        byte[] bArr = this.buff_;
        long j2 = this.curr_;
        byte b = bArr[(int) (j2 - this.lo_)];
        this.curr_ = j2 + 1;
        return b & 255;
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.curr_;
        if (j >= this.hi_) {
            if (this.hitEOF_) {
                return -1;
            }
            seek(j);
            if (this.curr_ == this.hi_) {
                return -1;
            }
        }
        int iMin = Math.min(i2, (int) (this.hi_ - this.curr_));
        System.arraycopy(this.buff_, (int) (this.curr_ - this.lo_), bArr, i, iMin);
        this.curr_ += (long) iMin;
        return iMin;
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(int i) throws IOException {
        this.mLastWriteDisk = false;
        long j = this.curr_;
        long j2 = this.hi_;
        if (j >= j2) {
            if (this.hitEOF_ && j2 < this.maxHi_) {
                this.hi_ = j2 + 1;
            } else {
                seek(this.curr_);
                long j3 = this.curr_;
                long j4 = this.hi_;
                if (j3 == j4) {
                    this.hi_ = j4 + 1;
                }
            }
        }
        byte[] bArr = this.buff_;
        long j5 = this.curr_;
        bArr[(int) (j5 - this.lo_)] = (byte) i;
        this.curr_ = j5 + 1;
        this.dirty_ = true;
        this.syncNeeded_ = true;
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.mLastWriteDisk = false;
        while (i2 > 0) {
            int iWriteAtMost = writeAtMost(bArr, i, i2);
            i += iWriteAtMost;
            i2 -= iWriteAtMost;
            this.dirty_ = true;
            this.syncNeeded_ = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int writeAtMost(byte[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            long r0 = r5.curr_
            long r2 = r5.hi_
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L26
            boolean r0 = r5.hitEOF_
            if (r0 == 0) goto L15
            long r0 = r5.maxHi_
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 >= 0) goto L15
            r5.hi_ = r0
            goto L26
        L15:
            long r0 = r5.curr_
            r5.seek(r0)
            long r0 = r5.curr_
            long r2 = r5.hi_
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L26
            long r0 = r5.maxHi_
            r5.hi_ = r0
        L26:
            long r0 = r5.hi_
            long r2 = r5.curr_
            long r0 = r0 - r2
            int r1 = (int) r0
            int r8 = java.lang.Math.min(r8, r1)
            long r0 = r5.curr_
            long r2 = r5.lo_
            long r0 = r0 - r2
            int r1 = (int) r0
            byte[] r0 = r5.buff_
            java.lang.System.arraycopy(r6, r7, r0, r1, r8)
            long r6 = r5.curr_
            long r0 = (long) r8
            long r6 = r6 + r0
            r5.curr_ = r6
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.multithreaddownload.BufferedRandomAccessFile.writeAtMost(byte[], int, int):int");
    }
}
