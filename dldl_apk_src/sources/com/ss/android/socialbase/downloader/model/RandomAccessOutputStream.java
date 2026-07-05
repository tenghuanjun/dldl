package com.ss.android.socialbase.downloader.model;

import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class RandomAccessOutputStream implements Closeable {
    private static final int MAX_FLUSH_BUFFER_SIZE = 131072;
    private static final int MIN_FLUSH_BUFFER_SIZE = 8192;
    private FileDescriptor fd;
    private BufferedOutputStream outputStream;
    private RandomAccessFile randomAccess;

    public RandomAccessOutputStream(File file, int i) throws BaseException {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            this.randomAccess = randomAccessFile;
            this.fd = randomAccessFile.getFD();
            if (i > 0) {
                if (i < 8192) {
                    i = 8192;
                } else if (i > 131072) {
                    i = 131072;
                }
                this.outputStream = new BufferedOutputStream(new FileOutputStream(this.randomAccess.getFD()), i);
                return;
            }
            this.outputStream = new BufferedOutputStream(new FileOutputStream(this.randomAccess.getFD()));
        } catch (IOException e) {
            throw new BaseException(DownloadErrorCode.ERROR_OUTPUT_STREAM_CREATE_IO, e);
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.outputStream.write(bArr, i, i2);
    }

    public void flushAndSync() throws IOException {
        BufferedOutputStream bufferedOutputStream = this.outputStream;
        if (bufferedOutputStream != null) {
            bufferedOutputStream.flush();
        }
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor != null) {
            fileDescriptor.sync();
        }
    }

    public void flush() throws IOException {
        BufferedOutputStream bufferedOutputStream = this.outputStream;
        if (bufferedOutputStream != null) {
            bufferedOutputStream.flush();
        }
    }

    public void sync() throws IOException {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor != null) {
            fileDescriptor.sync();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        DownloadUtils.safeClose(this.randomAccess, this.outputStream);
    }

    public void seek(long j) throws IOException {
        this.randomAccess.seek(j);
    }

    public void setLength(long j) throws IOException {
        this.randomAccess.setLength(j);
    }
}
